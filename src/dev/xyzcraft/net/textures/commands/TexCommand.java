/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.xyzcraft.net.textures.commands;

import dev.xyzcraft.net.util.MacCommand;
import dev.xyzcraft.net.util.MacCommandStatus;
import net.xyzcraft.dev.textures.XYZTextures;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 *
 * @author Joey
 */
public class TexCommand extends MacCommand {
    public TexCommand(XYZTextures tx) {
        super(tx);
    }
    @Override
    public MacCommandStatus onCommand(Player cs, Command cmnd, String label, String[] args) {
        if (args.length < 1) {
            sendHelp(cs);
            return MacCommandStatus.SUCESSFUL;
        }
        if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("none") || args[0].equalsIgnoreCase("remove")) {
            Integer pref = 0;
            this.plugin.database.setUserPrefrence(cs, pref);
            cs.kickPlayer(ChatColor.AQUA + "For your texture pack to reload, you must leave the game, and rejoin.");
            return MacCommandStatus.SUCESSFUL;
        }
        if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("sd")) {
            Integer pref = 1;
            this.plugin.database.setUserPrefrence(cs, pref);
            cs.sendMessage(ChatColor.RED + "Activating the SD Texture Pack");
            cs.setTexturePack(this.plugin.getConfig().getString("textures.reg"));
            return MacCommandStatus.SUCESSFUL;
        }
        if (args[0].equalsIgnoreCase("HD") || args[0].equalsIgnoreCase("highdef")) {
            Integer pref = 2;
            this.plugin.database.setUserPrefrence(cs, pref);
            cs.sendMessage(ChatColor.RED + "Activating the HD Texture Pack");
            cs.setTexturePack(this.plugin.getConfig().getString("textures.hd"));
            return MacCommandStatus.SUCESSFUL;
        }
        sendHelp(cs);
        return MacCommandStatus.SUCESSFUL;
    }
    public void sendHelp(Player cs) {
        cs.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------------------------");
        cs.sendMessage(ChatColor.DARK_GREEN + "XYZ " + ChatColor.GREEN + "textures");
        cs.sendMessage(ChatColor.GRAY + "   /tex off " + ChatColor.YELLOW + "-" + ChatColor.DARK_GRAY + " turns off the texture pack");
        cs.sendMessage(ChatColor.RED + "   /tex on" + ChatColor.YELLOW + " - " + ChatColor.DARK_RED + "turns on the SD texture pack");
        cs.sendMessage(ChatColor.LIGHT_PURPLE + "   /tex hd" + ChatColor.YELLOW + " - " + ChatColor.DARK_PURPLE + "turns on the HD texture pack " + ChatColor.GRAY + "(requires HD texture patch)");
        cs.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------------------------");
    }
    
}
