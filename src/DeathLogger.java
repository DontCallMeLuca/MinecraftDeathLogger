package me.luca.deathlogger;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathLogger extends JavaPlugin {

    public static DeathLogger Instance;

    @Override
    public void onEnable() {
        Instance = this;

        getServer().getPluginManager().registerEvents(new EventListener(), Instance);
        getServer().getLogger().info("Death Logger Loaded Successfully");
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Death Logger Unloaded Successfully");
    }
}
