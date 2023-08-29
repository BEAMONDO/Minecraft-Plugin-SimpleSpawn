package davigamer161.simplespawn.comandos;

import java.util.List;

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
		    if(jugador.hasPermission("simplespawn.spawn") && config.getString(poth).equals("true")){
                Economy econ = plugin.getEconomy();
        			double dinero = econ.getBalance(jugador);
       				int precio = Integer.valueOf(config.getString("Config.spawn-price"));
                    if(config.contains("Config.spawn.x")){
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
							List<String> mensaje = messages.getStringList("Messages.spawn.no-money-to-teleport");
							    for(int i=0;i<mensaje.size();i++){
								    String texto = mensaje.get(i);
								    jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
							    }
						    }
                        }
                    return true;
                    }else{
                        spawnNoExistMethod(jugador);
                    }
            }else if(jugador.hasPermission("simplespawn.spawn")){
                if(config.contains("Config.spawn.x")){
                    teleportMethod(jugador);
                    spawnMethod(jugador);
                    }else{
                        spawnNoExistMethod(jugador);
                    }
            }else{
                noPermMethod(jugador);
            }
        }else if(args.length == 1){
            String poth = "Config.pay-to-spawn";
			if(jugador.hasPermission("simplespawn.spawn.others") && config.getString(poth).equals("true")){
                if(config.contains("Config.spawn.x")){
                Economy econ = plugin.getEconomy();
        		double dinero = econ.getBalance(jugador);
       			int precio = Integer.valueOf(config.getString("Config.spawn-price"));
                Player target = Bukkit.getPlayer(args[0]);
                if(jugador.hasPermission("simplespawn.econ.exempt.others")){
                    teleportMethod(target);
                    spawnMethod(target);
                    String path = "Config.spawn-message";
                    if(config.getString(path).equals("true")){
                        List<String> mensaje = messages.getStringList("Messages.spawn.teleport-others");
                        for(int i=0;i<mensaje.size();i++){
                            String texto = mensaje.get(i);
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                        }
                    }
                }else if(dinero >=precio){
                    econ.withdrawPlayer(jugador, precio);
                    teleportMethod(target);
                    spawnMethod(target);
                    String path = "Config.spawn-message";
                    if(config.getString(path).equals("true")){
                        List<String> mensaje = messages.getStringList("Messages.spawn.teleport-others");
                        for(int i=0;i<mensaje.size();i++){
                            String texto = mensaje.get(i);
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                        }
                    }
                }else{
                    String path = "Config.spawn-message";
			        if(config.getString(path).equals("true")){
			            List<String> mensaje = messages.getStringList("Messages.spawn.no-money-to-teleport-others");
				        for(int i=0;i<mensaje.size();i++){
					        String texto = mensaje.get(i);
					        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
				        }
                    }
                }
            }else{
                spawnNoExistMethod(jugador);
            }
			}else if(jugador.hasPermission("simplespawn.spawn.others")){
            Player target = Bukkit.getPlayer(args[0]);
            if(config.contains("Config.spawn.x")){
                    teleportMethod(target);
			        spawnMethod(target);
                    String path = "Config.spawn-message";
                    if(config.getString(path).equals("true")){
                        List<String> mensaje = messages.getStringList("Messages.spawn.teleport-others");
                        for(int i=0;i<mensaje.size();i++){
                            String texto = mensaje.get(i);
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
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
            List<String> mensaje = messages.getStringList("Messages.spawn.teleport");
            for(int i=0;i<mensaje.size();i++){
                String texto = mensaje.get(i);
                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
            }
        }
	}
    private void teleportMethod(Player jugador){
                    FileConfiguration config = plugin.getConfig();
		            double x = Double.valueOf(config.getString("Config.spawn.x"));
                    double y = Double.valueOf(config.getString("Config.spawn.y"));
                    double z = Double.valueOf(config.getString("Config.spawn.z"));
                    float yaw = Float.valueOf(config.getString("Config.spawn.yaw"));
                    float pitch = Float.valueOf(config.getString("Config.spawn.pitch"));
                    World world = plugin.getServer().getWorld(config.getString("Config.spawn.world"));
                    Location l = new Location(world, x, y, z, yaw, pitch);
                    jugador.teleport(l);
	}
    private void spawnNoExistMethod(Player jugador){
                        FileConfiguration config = plugin.getConfig();
                        FileConfiguration messages = plugin.getMessages();
                        String path = "Config.spawn-message";
                        if(config.getString(path).equals("true")){
                            List<String> mensaje = messages.getStringList("Messages.spawn.no-exist");
                            for(int i=0;i<mensaje.size();i++){
                                String texto = mensaje.get(i);
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                            }
                        }
	}
	private void noPermMethod(Player jugador){
		FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
		String path = "Config.no-perm-message";
		if(config.getString(path).equals("true")){
			List<String> mensaje = messages.getStringList("Messages.no-perm");
			for(int i=0;i<mensaje.size();i++){
				String texto = mensaje.get(i);
				jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
			}
		}
	}
}





