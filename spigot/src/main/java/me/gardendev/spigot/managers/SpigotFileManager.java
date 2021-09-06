package me.gardendev.spigot.managers;

import me.gardendev.spigot.SpigotPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpigotFileManager extends YamlConfiguration {

    private final String fileName;
    private final File file;
    private final SpigotPlugin plugin;

    public SpigotFileManager(SpigotPlugin plugin, String fileName, String fileExtension, File folder) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(folder, fileName.endsWith(fileExtension) ? "" : ".yml");
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

        }

    }




}
