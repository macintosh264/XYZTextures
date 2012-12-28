/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.xyzcraft.net.textures.listeners;

import net.xyzcraft.dev.textures.XYZTextures;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Joey
 */
public class PlayerListener implements Listener {
    private XYZTextures plugin;
    public PlayerListener(XYZTextures ts) {
        this.plugin = ts;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        Integer pref = this.plugin.database.getPref(ev.getPlayer());
        Player pl = ev.getPlayer();
        if (pref == -1) {
            pl.sendMessage(ChatColor.GREEN + "Do /tex to change your texture pack, and choose between HD and SD!");
        }
        if (pref == 1) {
            pl.setTexturePack(this.plugin.getConfig().getString("textures.reg"));
        }
        if (pref == 2) {
            pl.setTexturePack(this.plugin.getConfig().getString("textures.hd"));
        }
    } 
}
