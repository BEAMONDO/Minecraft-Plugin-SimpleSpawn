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

public class ComandoSpawn implements CommandExecutor{

    private SimpleSpawn plugin;

    public ComandoSpawn(SimpleSpawn plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args){
    if(sender instanceof Player){
	    FileConfiguration config = plugin.getConfig();
	    Player jugador = (Player) sender;
		if(args.length == 0){
		    if(jugador.hasPermission("simplespawn.spawn")){
                if(config.contains("Config.spawn.x")){
                    double x = Double.valueOf(config.getString("Config.spawn.x"));
                    double y = Double.valueOf(config.getString("Config.spawn.y"));
                    double z = Double.valueOf(config.getString("Config.spawn.z"));
                    float yaw = Float.valueOf(config.getString("Config.spawn.yaw"));
                    float pitch = Float.valueOf(config.getString("Config.spawn.pitch"));
                    World world = plugin.getServer().getWorld(config.getString("Config.spawn.world"));
                    Location l = new Location(world, x, y, z, yaw, pitch);
                    jugador.teleport(l);
                    return true;
                    }else{
                        String path = "Config.spawn-message";
                        if(config.getString(path).equals("true")){
                            List<String> mensaje = config.getStringList("Config.spawn-not-exist-text");
                            for(int i=0;i<mensaje.size();i++){
                                String texto = mensaje.get(i);
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                            }
                        }
                    }
            }else{
                String path = "Config.spawn-message";
                if(config.getString(path).equals("true")){
                    List<String> mensaje = config.getStringList("Config.no-perm-text");
                    for(int i=0;i<mensaje.size();i++){
                        String texto = mensaje.get(i);
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                    }
                }
            }
        }else if(args.length == 1){
			if(jugador.hasPermission("simplespawn.spawn.others")){
            Player target = Bukkit.getPlayer(args[0]);
            if(config.contains("Config.spawn.x")){
		        if(target.hasPermission("simplespawn.spawn")){
                    double x = Double.valueOf(config.getString("Config.spawn.x"));
                    double y = Double.valueOf(config.getString("Config.spawn.y"));
                    double z = Double.valueOf(config.getString("Config.spawn.z"));
                    float yaw = Float.valueOf(config.getString("Config.spawn.yaw"));
                    float pitch = Float.valueOf(config.getString("Config.spawn.pitch"));
                    World world = plugin.getServer().getWorld(config.getString("Config.spawn.world"));
                    Location l = new Location(world, x, y, z, yaw, pitch);
                    jugador.teleport(l);
			        String path = "Config.spawn-message";
			        if(config.getString(path).equals("true")){
			            List<String> mensaje = config.getStringList("Config.spawn-text");
				        for(int i=0;i<mensaje.size();i++){
					        String texto = mensaje.get(i);
					        target.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
				        }
                    }
                }else{
                    String path = "Config.spawn-message";
                    if(config.getString(path).equals("true")){
                        List<String> mensaje = config.getStringList("Config.no-perm-spawn-others-text");
                        for(int i=0;i<mensaje.size();i++){
                            String texto = mensaje.get(i);
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version).replaceAll("%target%", target.getName())));
                        }
                    }
                }
            }else{
                String path = "Config.spawn-message";
                if(config.getString(path).equals("true")){
                    List<String> mensaje = config.getStringList("Config.spawn-not-exist-text");
                    for(int i=0;i<mensaje.size();i++){
                        String texto = mensaje.get(i);
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                    }
                }
            }
			}else{
				String path = "Config.no-perm";
				if(config.getString(path).equals("true")){
					List<String> mensaje = config.getStringList("Config.no-perm-text");
					for(int i=0;i<mensaje.size();i++){
						String texto = mensaje.get(i);
						jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
					}
				}
			}
		}
	}
	return false;
    }
}
