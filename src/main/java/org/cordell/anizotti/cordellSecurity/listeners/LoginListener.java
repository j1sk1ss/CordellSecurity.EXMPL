package org.cordell.anizotti.cordellSecurity.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.cordell.anizotti.cordellSecurity.CordellSecurity;


public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        System.out.println("[AnizottiSecurity] Player " + e.getPlayer().getName() + " try to logged in");

        var player = e.getPlayer();
        var firstAddr = e.getAddress();
        var ipString = firstAddr.getHostAddress();
        var nickname = player.getName();

        try {
            var ip = CordellSecurity.playerWhiteList.getString(nickname);
            if (ip == null) {
                System.out.println("[CordellSecurity] Player IP not found!");
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Who are you?\nAlso - this is CordellSecurity plugin here!");
                return;
            }

            if (!ip.equals(ipString)) {
                System.out.println("[CordellSecurity] Player IP not allowed!");
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "IP is wrong for provided nickname!\nAlso - this is CordellSecurity plugin here!");
            }

            player.sendMessage("[Cordell Security] You are logged in under host: " + ip);
        } catch (Exception ex) {
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "I don`t know you! Or I broken...\nAnyway you don`t pass.");
            throw new RuntimeException(ex);
        }
    }
}
