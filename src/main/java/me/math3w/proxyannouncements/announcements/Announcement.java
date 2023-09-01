package me.math3w.proxyannouncements.announcements;

import me.math3w.proxyannouncements.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class Announcement {
    private final String name;
    private final List<String> lines;
    private final String actionbar;
    private final Title title;

    public Announcement(String name, List<String> lines, String actionbar, Title title) {
        this.name = name;
        this.lines = lines;
        this.actionbar = actionbar;
        this.title = title;
    }

    public Announcement(ProxyServer proxyServer, String name, Configuration configuration) {
        /*
        configuration.contains("title") ? proxyServer.createTitle().title(new TextComponent(Utils.colorize(configuration.getString("title.title"))))
                        .subTitle(new TextComponent(Utils.colorize(configuration.getString("title.subtitle"))))
                        .fadeIn(configuration.getInt("title.fadein"))
                        .fadeOut(configuration.getInt("title.fadeout"))
                        .stay(configuration.getInt("title.duration")) : null
         */
        this(name,
                configuration.getStringList("lines"),
                configuration.contains("actionbar") ? configuration.getString("actionbar") : null,
                configuration.contains("title") ? proxyServer.createTitle().title(new TextComponent(Utils.colorize(configuration.getString("title.title"))))
                        .subTitle(new TextComponent(Utils.colorize(configuration.getString("title.subtitle"))))
                        .fadeIn(configuration.getInt("title.fadein"))
                        .fadeOut(configuration.getInt("title.fadeout"))
                        .stay(configuration.getInt("title.duration")) : null);
    }

    public void sendAnnouncement(ProxiedPlayer player) {
        for (String line : lines) {
            String colorizedLine = Utils.colorize(line);
            player.sendMessage(colorizedLine);
        }

        if (actionbar != null) {
            player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.colorize(actionbar)));
        }

        if (title != null) {
            player.sendTitle(title);
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }
}
