package davigamer161.simplespawn.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import davigamer161.simplespawn.SimpleSpawn;
import net.milkbowl.vault.economy.Economy;

public class ComandoSpawn implements CommandExecutor{

    private SimpleSpawn plugin;
    public ComandoSpawn(SimpleSpawn plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args){
    if(sender instanceof Player){
	    FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
	    Player jugador = (Player) sender;
		if(args.length == 0){
            String poth = "Config.pay-to-spawn";
            if(jugador.hasPermission("simplespawn.spawn")){
                if(config.contains("Config.spawn-location.x")){
                    if(config.getString(poth).equals("true")){
                        Economy econ = plugin.getEconomy();
                        double dinero = econ.getBalance(jugador);
                        int precio = Integer.valueOf(config.getString("Config.spawn-price"));
                            if(jugador.hasPermission("simplespawn.econ.exempt")){
                                teleportMethod(jugador);
                                spawnMethod(jugador);
                            }else if(dinero >=precio){
                                econ.withdrawPlayer(jugador, precio);
                                teleportMethod(jugador);
                                spawnMethod(jugador);
                            }else{
                                String path = "Config.spawn-message";
                                if(config.getString(path).equals("true")){
                                String mensaje = messages.getString("Messages.spawn.no-enought-money");
                                    jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                                }
                            }
                    }else{
                        teleportMethod(jugador);
                        spawnMethod(jugador);
                    }
                }else{
                    spawnNoExistMethod(jugador);
                }
            }else{
                noPermMethod(jugador);
            }
        }else if(args.length == 1){
            String poth = "Config.pay-to-spawn";
            if(jugador.hasPermission("simplespawn.spawn.others")){
                if(config.contains("Config.spawn-location.x")){
                    if(config.getString(poth).equals("true")){
                        Economy econ = plugin.getEconomy();
                        double dinero = econ.getBalance(jugador);
                        int precio = Integer.valueOf(config.getString("Config.spawn-price"));
                        Player target = Bukkit.getPlayer(args[0]);
                        if(jugador.hasPermission("simplespawn.econ.exempt.others")){
                            teleportMethod(target);
                            spawnMethod(target);
                            String path = "Config.spawn-message";
                            if(config.getString(path).equals("true")){
                                String mensaje = messages.getString("Messages.spawn.teleport-others");
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                            }
                        }else if(dinero >=precio){
                        econ.withdrawPlayer(jugador, precio);
                        teleportMethod(target);
                        spawnMethod(target);
                        String path = "Config.spawn-message";
                            if(config.getString(path).equals("true")){
                                String mensaje = messages.getString("Messages.spawn.teleport-others");
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                            }
                        }else{
                            String path = "Config.spawn-message";
                            if(config.getString(path).equals("true")){
                                String mensaje = messages.getString("Messages.spawn.no-enought-money-others");
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                            }
                        }
                    }else{
                        Player target = Bukkit.getPlayer(args[0]);
                        teleportMethod(target);
                        spawnMethod(target);
                        String path = "Config.spawn-message";
                        if(config.getString(path).equals("true")){
                        String mensaje = messages.getString("Messages.spawn.teleport-others");
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                        }
                    }
                }else{
                    spawnNoExistMethod(jugador);
                }
            }else{
                noPermMethod(jugador);
            }
        }
    }
	return false;
	}
    private void spawnMethod(Player jugador){
		FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
		String path = "Config.spawn-message";
    	if(config.getString(path).equals("true")){
            String mensaje = messages.getString("Messages.spawn.teleport");
            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
        }
	}
    private void teleportMethod(Player jugador){
            FileConfiguration config = plugin.getConfig();
	        double x = Double.valueOf(config.getString("Config.spawn-location.x"));
            double y = Double.valueOf(config.getString("Config.spawn-location.y"));
            double z = Double.valueOf(config.getString("Config.spawn-location.z"));
            float yaw = Float.valueOf(config.getString("Config.spawn-location.yaw"));
            float pitch = Float.valueOf(config.getString("Config.spawn-location.pitch"));
            World world = plugin.getServer().getWorld(config.getString("Config.spawn-location.world"));
            Location l = new Location(world, x, y, z, yaw, pitch);
            jugador.teleport(l);
	}
    private void spawnNoExistMethod(Player jugador){
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
        String path = "Config.spawn-message";
        if(config.getString(path).equals("true")){
            String mensaje = messages.getString("Messages.spawn.no-exist");
            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
        }
	}
	private void noPermMethod(Player jugador){
		FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
		String path = "Config.no-perm-message";
		if(config.getString(path).equals("true")){
			String mensaje = messages.getString("Messages.no-perm");
			jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
		}
	}
}