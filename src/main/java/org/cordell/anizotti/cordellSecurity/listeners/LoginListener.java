package org.cordell.anizotti.anizottiSecurity.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.cordell.anizotti.anizottiSecurity.AnizottiSecurity;


public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        System.out.println("[AnizottiSecurity] Player " + e.getPlayer().getName() + " try to logged in");

        var player = e.getPlayer();
        var firstAddr = e.getAddress();
        var ipString = firstAddr.getHostAddress();
        var nickname = player.getName();

        try {
            if (!AnizottiSecurity.playerWhiteList.getString(nickname).equals(ipString)) {
                System.out.println("[AnizottiSecurity] Player IP not allowed!");
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "IP is wrong for provided nickname!\nWho are you?\nAlso - this is AnizottiSecurity plugin here!");
            }
        } catch (Exception ex) {
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "I don`t know you! Or I broken...\nAnyway you don`t pass.");
            throw new RuntimeException(ex);
        }
    }
}
