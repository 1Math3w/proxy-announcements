package me.math3w.proxyannouncements.commands;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
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
        if (args.length < 1) {
            TextComponent message = new TextComponent("/announce <announcement-name>");
            message.setColor(ChatColor.RED);
            sender.sendMessage(message);
            return;
        }

        String announcementName = args[0];
        proxyAnnouncements.getAnnouncementManager().getAnnouncement(announcementName).ifPresentOrElse(announcement -> {
            for (ServerInfo server : proxyAnnouncements.getProxy().getServers().values()) {
                for (ProxiedPlayer player : server.getPlayers()) {
                    announcement.sendAnnouncement(player);
                }
            }
        }, () -> {
            TextComponent message = new TextComponent("Invalid announcement name!");
            message.setColor(ChatColor.RED);
            sender.sendMessage(message);
        });
    }
}
