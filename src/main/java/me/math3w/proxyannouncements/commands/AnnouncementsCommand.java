package me.math3w.proxyannouncements.commands;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class AnnouncementsCommand extends Command {
    private final ProxyAnnouncements proxyAnnouncements;

    public AnnouncementsCommand(ProxyAnnouncements proxyAnnouncements) {
        super("proxyannouncements", "bungeecord.command.proxyannouncements", "announcements");
        this.proxyAnnouncements = proxyAnnouncements;
    }

    private static void sendHelpMessage(CommandSender sender) {
        TextComponent message = new TextComponent("/proxyannouncements reload\n/proxyannouncements unload\n/proxyannouncements load");
        message.setColor(ChatColor.RED);
        sender.sendMessage(message);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sendHelpMessage(sender);
            return;
        }

        switch (args[0]) {
            case "reload" -> {
                proxyAnnouncements.reload();
                TextComponent textComponent = new TextComponent("ProxyAnnouncements were succesfully reloaded");
                textComponent.setColor(ChatColor.GREEN);
                sender.sendMessage(textComponent);
            }
            case "unload" -> {
                proxyAnnouncements.unload();
                TextComponent textComponent = new TextComponent("ProxyAnnouncements were succesfully unloaded");
                textComponent.setColor(ChatColor.GREEN);
                sender.sendMessage(textComponent);
            }
            case "load" -> {
                proxyAnnouncements.load();
                TextComponent textComponent = new TextComponent("ProxyAnnouncements were succesfully loaded");
                textComponent.setColor(ChatColor.GREEN);
                sender.sendMessage(textComponent);
            }
            default -> sendHelpMessage(sender);
        }
    }
}
