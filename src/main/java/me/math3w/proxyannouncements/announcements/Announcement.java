package me.math3w.proxyannouncements.announcements;

import me.math3w.proxyannouncements.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class Announcement {
    private final String name;
    private final List<String> lines;

    public Announcement(String name, List<String> lines) {
        this.name = name;
        this.lines = lines;
    }

    public Announcement(String name, Configuration configuration) {
        this(name, configuration.getStringList("lines"));
    }

    public void sendAnnouncement(ProxiedPlayer player) {
        for (String line : lines) {
            String colorizedLine = Utils.colorize(line);
            player.sendMessage(colorizedLine);
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }
}
