package be.ilyaan.autoannouncer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public final class Autoannouncer extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getLogger().info("Autoannouncer enabled.");
        String color = getConfig().getString("MessageColor");
        ChatColor.translateAlternateColorCodes('&', color);
        long interval = getConfig().getLong("Interval") * 20; // Convert seconds to ticks
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                List<String> messages = getConfig().getStringList("Messages");
                String message = messages.get(new Random().nextInt(messages.size()));
                Bukkit.getServer().broadcastMessage( ChatColor.translateAlternateColorCodes('&',message));
            }
        }, 0, interval);

    }

    @Override
    public void onDisable() {
        getLogger().info("Autoannouncer disabled.");
    }
}
