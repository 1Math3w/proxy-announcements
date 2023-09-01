package me.math3w.proxyannouncements;

import me.math3w.proxyannouncements.announcements.AnnouncementManager;
import me.math3w.proxyannouncements.commands.AnnounceCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProxyAnnouncements extends Plugin {
    private Configuration config;
    private Configuration announcementsConfig;
    private AnnouncementManager announcementManager;

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand(this));

        config = loadConfig("config");
        announcementsConfig = loadConfig("announcements");
        announcementManager = new AnnouncementManager(this, config, announcementsConfig);
    }

    public AnnouncementManager getAnnouncementManager() {
        return announcementManager;
    }

    private Configuration loadConfig(String configName) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File configFile = new File(getDataFolder(), configName + ".yml");

        if (!configFile.exists()) {
            try (InputStream in = getResourceAsStream(configName + ".yml")) {
                FileOutputStream outputStream = new FileOutputStream(configFile);
                in.transferTo(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), configName + ".yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
