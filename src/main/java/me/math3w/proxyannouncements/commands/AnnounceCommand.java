package me.math3w.proxyannouncements.commands;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import me.math3w.proxyannouncements.announcements.AnnouncementManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
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
        AnnouncementManager announcementManager = proxyAnnouncements.getAnnouncementManager();
        announcementManager.getAnnouncement(announcementName).ifPresentOrElse(announcement -> {
            announcementManager.sendAnnouncement(announcement);
            sender.sendMessage(new TextComponent(ChatColor.GRAY + "Successfully announced " + ChatColor.RED + announcementName +
                    ChatColor.GRAY + " to servers " + ChatColor.RED + String.join(", ", announcementManager.getServers())));
        }, () -> {
            TextComponent message = new TextComponent("Invalid announcement name!");
            message.setColor(ChatColor.RED);
            sender.sendMessage(message);
        });
    }
}
