package davigamer161.simplespawn.comandos;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import davigamer161.simplespawn.SimpleSpawn;

public class ComandoPrincipal implements CommandExecutor{

    private SimpleSpawn plugin;

    public ComandoPrincipal(SimpleSpawn plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)){
            FileConfiguration config = plugin.getConfig();
            List<String> mensaje = config.getStringList("Config.console-error-text");
                for(int i=0;i<mensaje.size();i++){
                    String texto = mensaje.get(i);
                    Bukkit.getConsoleSender().sendMessage(texto);
                }
        }
        else{
            Player jugador = (Player) sender;
            FileConfiguration config = plugin.getConfig();
        if(args.length > 0){     
//-------------------------------------Comando version----------------------------------------------------------//
//----------------------------------------Desde aqui---------------------------------------//
    if(args[0].equalsIgnoreCase("version")){
        if(sender instanceof Player && (jugador.hasPermission("simplespawn.version"))){
            String path = "Config.version-message";
            if(config.getString(path).equals("true")){
                List<String> mensaje = config.getStringList("Config.version-text");
                for(int i=0;i<mensaje.size();i++){
                    String texto = mensaje.get(i);
                    jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                }
            }
            return true;
        }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.version"))){
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
//----------------------------------------Hasta aqui---------------------------------------//
    
                

//---------------------------------------Comando help--------------------------------------------------------//
//----------------------------------------Desde aqui---------------------------------------//
                else if(args[0].equalsIgnoreCase("help")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.help"))){
                        String path = "Config.help-message";
                        if(config.getString(path).equals("true")){
                            List<String> mensaje = config.getStringList("Config.help-text");
                            for(int i=0;i<mensaje.size();i++){
                                String texto = mensaje.get(i);
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                            }
                        }
                        return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.help"))){
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
 //----------------------------------------Hasta aqui---------------------------------------//

                

 //--------------------------------------Comando reload---------------------------------------------------------//
 //----------------------------------------Desde aqui---------------------------------------//
                else if(args[0].equalsIgnoreCase("reload")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.reload"))){
                        String path = "Config.reload-message";
                        plugin.reloadConfig();
                        if(config.getString(path).equals("true")){
                            List<String> mensaje = config.getStringList("Config.reload-text");
                            for(int i=0;i<mensaje.size();i++){
                                String texto = mensaje.get(i);
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                            }
                        }
                        return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.reload"))){
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
 //----------------------------------------Hasta aqui---------------------------------------//

            }else{
                if(sender instanceof Player && (jugador.hasPermission("simplespawn.help"))){
                String path = "Config.command-no-argument";
                if(config.getString(path).equals("true")){
                    List<String> mensaje = config.getStringList("Config.command-no-argument-text");
                    for(int i=0;i<mensaje.size();i++){
                        String texto = mensaje.get(i);
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                    }
                }
                return true;
            }else if(sender instanceof Player && !(jugador.hasPermission("simplespawn.help"))){
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