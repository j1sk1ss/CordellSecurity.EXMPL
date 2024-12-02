package org.cordell.anizotti.cordellSecurity.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.cordell.anizotti.cordellSecurity.CordellSecurity;

import java.util.List;
import java.util.Random;


public class LoginListener implements Listener {

    private static final String defaultMessage = "Also - this is CordellSecurity plugin here!";
    private static final List<String> cordellSecurityList = List.of(
            "Hello Bob!", "I`m kitty - Meow!", "You should not pass.", "Cordell - best company ever",
            "Default denied text", "What's your name?", "Pig says oink", "Anizotti city is better then Los-Aidaros",
            "Make sure, that your IP whitelisted, Bob!"
    );

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        var player    = e.getPlayer();
        var firstAddr = e.getAddress();
        var ipString  = firstAddr.getHostAddress();
        var nickname  = player.getName();

        System.out.println("Player " + nickname + " try to logged in under host: " + ipString);

        try {
            var title = cordellSecurityList.get(new Random().nextInt(cordellSecurityList.size())) + "\n" + defaultMessage;
            var ip = CordellSecurity.playerWhiteList.getString(nickname);
            if (ip == null) {
                System.out.println("Player IP not found!");
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST,  title);
                return;
            }

            if (!ip.equals(ipString) && !ip.equals("any")) {
                System.out.println("Player IP not allowed!");
                e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, title);
            }

            player.sendMessage("You are logged in under host: " + ip);
        } catch (Exception ex) {
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "I don`t know you! Or I broken...\nAnyway you don`t pass.\n" + defaultMessage);
            throw new RuntimeException(ex);
        }
    }
}
