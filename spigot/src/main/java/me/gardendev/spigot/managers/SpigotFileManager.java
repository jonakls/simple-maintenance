package me.gardendev.spigot.managers;

import me.gardendev.spigot.SpigotPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SpigotFileManager extends YamlConfiguration {

    private final String fileName;
    private final File file;
    private final SpigotPlugin plugin;

    public SpigotFileManager(SpigotPlugin plugin, String fileName, String fileExtension, File folder) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : ".yml");
        this.file = new File(folder, fileName);
        create();
    }

    public SpigotFileManager(SpigotPlugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    public SpigotFileManager(SpigotPlugin plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    public void create(){
        try {
            if (this.file.exists()) {
                load(file);
                save(file);
                return;
            }

            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                save(file);
            }
            load(file);

        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Creation of Configuration " + this.fileName + " failed." + e.getMessage());
        }
    }

    public void reload() {
        try {
            load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().warning("Reload of Configuration " + this.fileName + " failed." + e.getMessage());
        }
    }

    public void save() {
        try {
            save(this.file);
        } catch (IOException e) {
            plugin.getLogger().warning("Save of Configuration " + this.fileName + " failed." + e.getMessage());
        }
    }

    @Override
    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(super.getString(path)));
    }

    @Override
    public List<String> getStringList(String path) {
        List<String> list = super.getStringList(path);
        list.forEach(line -> ChatColor.translateAlternateColorCodes('&', line));
        return list;
    }
}
