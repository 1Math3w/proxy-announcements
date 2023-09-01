package me.math3w.proxyannouncements.announcements;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import net.md_5.bungee.config.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AnnouncementManager {
    private final ProxyAnnouncements proxyAnnouncements;
    private final List<Announcement> announcements;
    private final int interval;
    private final boolean random;
    private final Set<String> servers;

    public AnnouncementManager(ProxyAnnouncements proxyAnnouncements, List<Announcement> announcements, int interval, boolean random, Set<String> servers) {
        this.proxyAnnouncements = proxyAnnouncements;
        this.announcements = announcements;
        this.interval = interval;
        this.random = random;
        this.servers = servers.contains("*") ? proxyAnnouncements.getProxy().getServers().keySet() : servers;
    }

    public AnnouncementManager(ProxyAnnouncements proxyAnnouncements, Configuration configuration, Configuration announcementsConfiguration) {
        this(proxyAnnouncements,
                announcementsConfiguration.getKeys().stream()
                        .map(announcementName -> new Announcement(announcementName, announcementsConfiguration.getSection(announcementName)))
                        .toList(),
                configuration.getInt("interval"),
                configuration.getBoolean("random"),
                new HashSet<>(configuration.getStringList("servers")));
    }

    public Optional<Announcement> getAnnouncement(String name) {
        return announcements.stream().filter(announcement -> announcement.getName().equals(name)).findAny();
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public int getInterval() {
        return interval;
    }

    public boolean isRandom() {
        return random;
    }

    public Set<String> getServers() {
        return servers;
    }
}
