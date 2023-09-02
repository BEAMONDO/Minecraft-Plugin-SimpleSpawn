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
    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)){
            FileConfiguration messagess = plugin.getMessages();
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    plugin.reloadConfig();
                    plugin.reloadMessages();
                    String mensaje = messagess.getString("Messages.reload");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                }     
            }else{
                FileConfiguration messages = plugin.getMessages();
                String mensaje = messages.getString("Messages.console-error");
                Bukkit.getConsoleSender().sendMessage(mensaje);
            }
        }else{
            Player jugador = (Player) sender;
            FileConfiguration config = plugin.getConfig();
            FileConfiguration messages = plugin.getMessages();
            String path = "Config.no-perm-message";
            if(args.length > 0){     
//-------------------------------------Comando version----------------------------------------------------------//
//----------------------------------------Desde aqui---------------------------------------//
                if(args[0].equalsIgnoreCase("version")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.version"))){
                        String mensaje = messages.getString("Messages.version");
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                        return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.version"))){
                        if(config.getString(path).equals("true")){
                            String mensaje = messages.getString("Messages.no-perm");
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                        }
                    }
                }
//----------------------------------------Hasta aqui---------------------------------------//
    
                

//---------------------------------------Comando help--------------------------------------------------------//
//----------------------------------------Desde aqui---------------------------------------//
                else if(args[0].equalsIgnoreCase("help")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.help"))){
                            List<String> mensaje = messages.getStringList("Messages.help");
                            for(int i=0;i<mensaje.size();i++){
                                String texto = mensaje.get(i);
                                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', texto.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                            }
                        return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.help"))){
                        if(config.getString(path).equals("true")){
                            String mensaje = messages.getString("Messages.no-perm");
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                        }
                    }
                }
 //----------------------------------------Hasta aqui---------------------------------------//

                

 //--------------------------------------Comando reload---------------------------------------------------------//
 //----------------------------------------Desde aqui---------------------------------------//
                else if(args[0].equalsIgnoreCase("reload")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.reload"))){
                    plugin.reloadConfig();
                    plugin.reloadMessages();
                    String mensaje = messages.getString("Messages.reload");
                    jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                    return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.reload"))){
                        if(config.getString(path).equals("true")){
                            String mensaje = messages.getString("Messages.no-perm");
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                        } 
                    }
                }
 //----------------------------------------Hasta aqui---------------------------------------//



 //---------------------------------------Comando plugin--------------------------------------------------------//
//----------------------------------------Desde aqui---------------------------------------//
                else if(args[0].equalsIgnoreCase("plugin")){
                    if(sender instanceof Player && (jugador.hasPermission("simplespawn.plugin"))){
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.nombre+" &ehttps://www.spigotmc.org/resources/111086/"));
                        return true;
                    }if(sender instanceof Player && !(jugador.hasPermission("simplespawn.plugin"))){
                        if(config.getString(path).equals("true")){
                            String mensaje = messages.getString("Messages.no-perm");
                            jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                        }
                    }
                }
 //----------------------------------------Hasta aqui---------------------------------------//

            }else{
                if(sender instanceof Player && (jugador.hasPermission("simplespawn.help"))){
                    String mensaje = messages.getString("Messages.command-no-argument");
                    jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                return true;
                }else if(sender instanceof Player && !(jugador.hasPermission("simplespawn.help"))){
                    if(config.getString(path).equals("true")){
                        String mensaje = messages.getString("Messages.no-perm");
                        jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', mensaje.replaceAll("%player%", jugador.getName()).replaceAll("%plugin%", plugin.nombre).replaceAll("%version%", plugin.version)));
                    }
                }
            }
        }
        return false;
    }
}