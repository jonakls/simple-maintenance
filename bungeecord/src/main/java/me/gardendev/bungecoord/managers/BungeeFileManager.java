package me.gardendev.bungecoord.managers;

import me.gardendev.bungecoord.BungeeCordPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class BungeeFileManager {

    private final String fileName;
    private final File file;
    private final BungeeCordPlugin plugin;
    private Configuration configuration;

    public BungeeFileManager(BungeeCordPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        create();
    }

    public void create() {
        if(!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdirs();
        }

        if (!this.file.exists()) {
            try {
                InputStream in = this.plugin.getResourceAsStream(this.fileName);
                Files.copy(in, this.file.toPath());
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Error trying to upload file " + this.fileName);
                e.printStackTrace();
            }
            return;
        }
        this.load();
    }

    public void load() {
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
