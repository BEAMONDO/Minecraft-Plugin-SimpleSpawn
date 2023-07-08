package davigamer161.simplespawn;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import davigamer161.simplespawn.comandos.ComandoSpawn;
import davigamer161.simplespawn.comandos.ComandoPrincipal;
import davigamer161.simplespawn.comandos.ComandoSetSpawn;

public class SimpleSpawn extends JavaPlugin{
    public String rutaConfig;
    PluginDescriptionFile pdffile;
    public String version;
    public String nombre;

    public SimpleSpawn(){
      this.pdffile = this.getDescription();
      this.version = this.pdffile.getVersion();
      this.nombre = ChatColor.RED+"["+ChatColor.YELLOW+this.pdffile.getName()+ChatColor.RED+"] "+ChatColor.WHITE;
    }
    //---------------------Para cuando se activa el plugin----------------------------------//
    //------------------------------Desde aqui-----------------------------//
    public void onEnable(){
      Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"<------------------------------------>");
	    Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.WHITE+"Enabled, ("+ChatColor.GREEN+"Version: "+ChatColor.AQUA+version+ChatColor.WHITE+")");
	    Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.GOLD+"Thanks for use my plugin :)");
	    Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.YELLOW+"Made by "+ChatColor.LIGHT_PURPLE+"davigamer161");
      Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"<------------------------------------>");
      registrarComandos();
      registrarConfig();
    }
    //------------------------------Hasta aqui-----------------------------//



    //------------------Para cuando se desactiva el plugin----------------------------------//
    //------------------------------Desde aqui-----------------------------//
    public void onDisable(){
	    Bukkit.getConsoleSender().sendMessage(nombre+ChatColor.WHITE+"Disabled, ("+ChatColor.GREEN+"Version: "+ChatColor.AQUA+version+ChatColor.WHITE+")");
    }
    //------------------------------Hasta aqui-----------------------------//

    

    //-----------------------Para registrar comandos----------------------------------------//
    //------------------------------Desde aqui-----------------------------//
    public void registrarComandos(){
        this.getCommand("spawn").setExecutor(new ComandoSpawn(this));
        this.getCommand("setspawn").setExecutor(new ComandoSetSpawn(this));
        this.getCommand("simplespawn").setExecutor(new ComandoPrincipal(this));
      }
    //------------------------------Hasta aqui-----------------------------//


      
    //--------------------------Para crear config.yml--------------------------------------//
    //------------------------------Desde aqui-----------------------------//
    public void registrarConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
          this.getConfig().options().copyDefaults(true);
          saveConfig();
        }
      }
    //------------------------------Hasta aqui-----------------------------//
}