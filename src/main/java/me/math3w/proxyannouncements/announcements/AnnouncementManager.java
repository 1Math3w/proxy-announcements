package me.math3w.proxyannouncements.announcements;

import me.math3w.proxyannouncements.ProxyAnnouncements;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.config.Configuration;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AnnouncementManager {
    private final ProxyAnnouncements proxyAnnouncements;
    private final List<Announcement> announcements;
    private final int interval;
    private final boolean random;
    private final Set<String> servers;
    private final ScheduledTask scheduledTask;
    private int currentAnnouncement = 0;

    public AnnouncementManager(ProxyAnnouncements proxyAnnouncements, List<Announcement> announcements, int interval, boolean random, Set<String> servers) {
        this.proxyAnnouncements = proxyAnnouncements;
        this.announcements = announcements;
        this.interval = interval;
        this.random = random;
        this.servers = servers.contains("*") ? proxyAnnouncements.getProxy().getServers().keySet() : servers;

        Random randomObject = new Random();
        scheduledTask = proxyAnnouncements.getProxy().getScheduler().schedule(proxyAnnouncements, () -> {
            int announcementIndex = random ? randomObject.nextInt(announcements.size()) : currentAnnouncement;
            Announcement announcement = announcements.get(announcementIndex);

            sendAnnouncement(announcement);

            currentAnnouncement++;
            if (currentAnnouncement >= announcements.size()) {
                currentAnnouncement = 0;
            }
        }, interval, interval, TimeUnit.SECONDS);
    }

    public AnnouncementManager(ProxyAnnouncements proxyAnnouncements, Configuration configuration, Configuration announcementsConfiguration) {
        this(proxyAnnouncements,
                announcementsConfiguration.getKeys().stream()
                        .map(announcementName -> new Announcement(proxyAnnouncements.getProxy(), announcementName, announcementsConfiguration.getSection(announcementName)))
                        .toList(),
                configuration.getInt("interval"),
                configuration.getBoolean("random"),
                new HashSet<>(configuration.getStringList("servers")));
    }

    public void sendAnnouncement(Announcement announcement) {
        servers.stream().map(serverName -> proxyAnnouncements.getProxy().getServerInfo(serverName)).forEach(server -> {
            for (ProxiedPlayer player : server.getPlayers()) {
                announcement.sendAnnouncement(player);
            }
        });
    }

    public Optional<Announcement> getAnnouncement(String name) {
        return announcements.stream().filter(announcement -> announcement.getName().equals(name)).findAny();
    }

    public void stopScheduler() {
        scheduledTask.cancel();
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
