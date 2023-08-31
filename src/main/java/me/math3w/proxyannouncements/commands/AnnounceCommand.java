package me.math3w.proxyannouncements.commands;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import me.math3w.proxyannouncements.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AnnounceCommand extends Command {
    private final ProxyAnnouncements proxyAnnouncements;

    public AnnounceCommand(ProxyAnnouncements proxyAnnouncements) {
        super("announce", "bungeecord.command.announce");
        this.proxyAnnouncements = proxyAnnouncements;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String rawMessage = String.join(" ", args);
        String colorizedMessage = Utils.colorize(rawMessage);

        for (ServerInfo server : proxyAnnouncements.getProxy().getServers().values()) {
            for (ProxiedPlayer player : server.getPlayers()) {
                player.sendMessage(colorizedMessage);
            }
        }
    }
}
