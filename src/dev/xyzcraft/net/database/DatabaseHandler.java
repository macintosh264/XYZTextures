/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.xyzcraft.net.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;


/**
 *
 * @author Joey
 */
public class DatabaseHandler extends MacDatabaseEngine {
    public DatabaseHandler(String url,String username, String password) throws SQLException {
        super(url,username,password);
    }
    /*
     * 0 = Off
     * 1 = REG
     * 2 = HD
     */
    public void setUserPrefrence(Player user, Integer pref) {
        String query;
        if (getPref(user) == -1) {
            query = "INSERT INTO user_prefs (username,pref) VALUES (" + user.getName() + "," + pref.toString();
        }
        else {
            query = "UPDATE user_prefs SET pref=" + pref.toString() + " WHERE user=" + user.getName();
        }
        try {
            this.executeMySql(query);
        }
        catch (Exception ex) {
            
        }
    }
    public Integer getPref(Player user) {
        try {
            String query = "SELECT * FROM user_prefs WHERE username='" + user.getName() + "'";
            ResultSet set = this.executeMySql(query);
            if (set.getFetchSize() <= 0) {
                return -1;
            }
            return set.getInt("pref");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
