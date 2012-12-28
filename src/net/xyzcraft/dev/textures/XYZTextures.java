/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xyzcraft.dev.textures;

import dev.xyzcraft.net.database.DatabaseHandler;
import dev.xyzcraft.net.textures.commands.TexCommand;
import dev.xyzcraft.net.textures.listeners.PlayerListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Joey
 */
public class XYZTextures extends JavaPlugin {
    public boolean db = false;
    public DatabaseHandler database = null;
    @Override
    public void onEnable() {
        /*
         * Write config
         */
        this.saveDefaultConfig();
        /*
         * Enable commands
         */
        getCommand("tex").setExecutor(new TexCommand(this));
        /*
         * Enable Database
         */
        String url = "jdbc:mysql://" + this.getConfig().getString("mysql.host") + ":" + this.getConfig().getInt("mysql.port") + "/" + this.getConfig().getString("mysql.database");
        try {
            database = new DatabaseHandler(url,this.getConfig().getString("mysql.username"),this.getConfig().getString("mysql.password"));
            db = true;
        } catch (SQLException ex) {
            Logger.getLogger(XYZTextures.class.getName()).log(Level.SEVERE, null, ex);
            database = null;
            db = false;
        }
        /*
         * Add Event Listners
         */
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
