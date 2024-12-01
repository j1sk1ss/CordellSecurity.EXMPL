package org.cordell.anizotti.cordellSecurity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.cordell.anizotti.cordellSecurity.listeners.LoginListener;
import org.cordell.com.cordelldb.manager.Manager;

public final class CordellSecurity extends JavaPlugin {

    public static Manager playerWhiteList;

    @Override
    public void onEnable() {
        System.out.println("Cordell Security 2.0 Enabled");
        playerWhiteList = new Manager("player_whitelist.txt");
        Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Cordell Security Disabled");
    }
}
