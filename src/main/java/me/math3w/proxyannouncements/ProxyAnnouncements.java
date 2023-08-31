package me.math3w.proxyannouncements;

import me.math3w.proxyannouncements.commands.AnnounceCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class ProxyAnnouncements extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new AnnounceCommand(this));
    }
}
