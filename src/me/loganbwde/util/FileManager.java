package me.loganbwde.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.loganbwde.Clan.main;

public class FileManager
{
    private main m;
    private File config;
    private FileConfiguration configuration;

    private File database;
    private FileConfiguration dataconfig;

    private File shop;
    private FileConfiguration shopconfig;

    private FileConfiguration language;
    private FileConfiguration language_en;
    private FileConfiguration language_de;
    private String langFolder;
    private File lang_en;
    private File lang_de;

    private HashMap<String, String> entrys = new HashMap<>();
    private HashMap<String, String> entrysmsg = new HashMap<>();

    public FileManager(main main)
    {
        m = main;
        loadFiles();
    }

    public void loadFiles()
    {
        config = new File(m.getDataFolder(), "Config.yml");
        configuration = YamlConfiguration.loadConfiguration(config);
        if (!new File(m.getDataFolder(), "Config.yml").exists())
        {
            saveDefaultConf();
        }
        else
        {
            saveConf();
        }

        database = new File(m.getDataFolder(), "Database.yml");
        dataconfig = YamlConfiguration.loadConfiguration(database);
        if (!new File(m.getDataFolder(), "Database.yml").exists())
        {
            saveDefaultDatabase();
        }
        else
        {
            saveDatabase();
        }

        shop = new File(m.getDataFolder(), "Shop.yml");
        shopconfig = YamlConfiguration.loadConfiguration(shop);
        if (!new File(m.getDataFolder(), "Shop.yml").exists())
        {
            saveDefaultShop();
        }
        else
        {
            saveShop();
        }

        langFolder = m.getDataFolder().getPath().replaceAll("\\\\", "/") + "/language/";

        lang_en = new File(langFolder, "en.yml");
        language_en = YamlConfiguration.loadConfiguration(lang_en);

        if (!new File(langFolder, "en.yml").exists())
        {
            saveDefaultEn();
        }
        else
        {
            saveEn();
        }

        lang_de = new File(langFolder, "de.yml");
        language_de = YamlConfiguration.loadConfiguration(lang_de);

        if (!new File(langFolder, "de.yml").exists())
        {
            saveDefaultDe();
        }
        else
        {
            saveDe();
        }

        if (getConfigEntrys().get("Basic.lang").contentEquals("en"))
        {
            language = YamlConfiguration.loadConfiguration(lang_en);
        }
        else
        {
            if (getConfigEntrys().get("Basic.lang").contentEquals("de"))
            {
                language = YamlConfiguration.loadConfiguration(lang_de);
            }
        }
    }

    private void saveDefaultConf()
    {
        InputStream defConfigStream3 = m.getResource("Config.yml");
        if (defConfigStream3 != null)
        {
            @SuppressWarnings("deprecation")
            YamlConfiguration defConfig3 = YamlConfiguration.loadConfiguration(defConfigStream3);

            configuration.setDefaults(defConfig3);
            configuration.options().copyDefaults(true);
        }
        saveConf();
    }

    private void saveDefaultShop()
    {
        InputStream defConfigStream4 = m.getResource("Shop.yml");
        if (defConfigStream4 != null)
        {
            @SuppressWarnings("deprecation")
            YamlConfiguration defConfig4 = YamlConfiguration.loadConfiguration(defConfigStream4);

            shopconfig.setDefaults(defConfig4);
            shopconfig.options().copyDefaults(true);
        }
        saveShop();
    }

    private void saveDefaultDatabase()
    {
        InputStream defConfigStream3 = m.getResource("Database.yml");
        if (defConfigStream3 != null)
        {
            @SuppressWarnings("deprecation")
            YamlConfiguration defConfig3 = YamlConfiguration.loadConfiguration(defConfigStream3);

            defConfig3.addDefault("Database." + ".Host", "localhost");
            defConfig3.addDefault("Database." + ".Port", 3306);
            defConfig3.addDefault("Database." + ".User", "user");
            defConfig3.addDefault("Database." + ".Password", "password");
            defConfig3.addDefault("Database." + ".Database", "database");

            dataconfig.setDefaults(defConfig3);
            dataconfig.options().copyDefaults(true);
        }
        saveDatabase();
    }

    public void saveDefaultEn()
    {
        InputStream defConfigStream = m.getResource("en.yml");
        if (defConfigStream != null)
        {
            @SuppressWarnings("deprecation")
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            language_en.setDefaults(defConfig);
            language_en.options().copyDefaults(true);
        }
        saveEn();
    }

    public void saveDefaultDe()
    {
        InputStream defConfigStream = m.getResource("de.yml");
        if (defConfigStream != null)
        {
            @SuppressWarnings("deprecation")
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            language_de.setDefaults(defConfig);
            language_de.options().copyDefaults(true);
        }
        saveDe();
    }

    public void saveEn()
    {
        try
        {
            language_en.save(lang_en);
        }
        catch (Exception localException)
        {
        }
    }

    public void saveDe()
    {
        try
        {
            language_de.save(lang_de);
        }
        catch (Exception localException)
        {
        }
    }

    public void saveDatabase()
    {
        try
        {
            dataconfig.save(database);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveConf()
    {
        try
        {
            configuration.save(config);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveShop()
    {
        try
        {
            shopconfig.save(shop);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public FileConfiguration getShop()
    {
        return shopconfig;
    }

    public FileConfiguration getDatabase()
    {
        return dataconfig;
    }

    public FileConfiguration getConfig()
    {
        return configuration;
    }

    public HashMap<String, String> getConfigEntrys()
    {
        entrys.clear();
        ConfigurationSection c = configuration.getConfigurationSection("Basic.");
        for (String st : c.getKeys(false))
        {
            String path = c.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c2 = configuration.getConfigurationSection("PvP.");
        for (String st : c2.getKeys(false))
        {
            String path = c2.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c3 = configuration.getConfigurationSection("ClanWars.");
        for (String st : c3.getKeys(false))
        {
            String path = c3.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c4 = configuration.getConfigurationSection("ClanCreate.");
        for (String st : c4.getKeys(false))
        {
            String path = c4.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c5_1 = configuration.getConfigurationSection("Chat.withclan.");
        for (String st : c5_1.getKeys(false))
        {
            String path = c5_1.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }
        
        ConfigurationSection c5_2 = configuration.getConfigurationSection("Chat.noclan.");
        for (String st : c5_2.getKeys(false))
        {
            String path = c5_2.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }
        
        ConfigurationSection c5_3 = configuration.getConfigurationSection("Chat.clanchat.");
        for (String st : c5_3.getKeys(false))
        {
            String path = c5_3.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c6 = configuration.getConfigurationSection("RankColour.");
        for (String st : c6.getKeys(false))
        {
            String path = c6.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c7 = configuration.getConfigurationSection("RankLevel.");
        for (String st : c7.getKeys(false))
        {
            String path = c7.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c8 = configuration.getConfigurationSection("MemberAmount.");
        for (String st : c8.getKeys(false))
        {
            String path = c8.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c9 = configuration.getConfigurationSection("ClanRank.Newbie.");
        for (String st : c9.getKeys(false))
        {
            String path = c9.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c10 = configuration.getConfigurationSection("ClanRank.Member.");
        for (String st : c10.getKeys(false))
        {
            String path = c10.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }

        ConfigurationSection c11 = configuration.getConfigurationSection("ClanRank.Elite.");
        for (String st : c11.getKeys(false))
        {
            String path = c11.getCurrentPath() + "." + st;
            entrys.put(path, configuration.getString(path));
        }
        return entrys;
    }

    public HashMap<String, String> getMessageEntrys()
    {
        entrysmsg.clear();
        ConfigurationSection c = language.getConfigurationSection("Messages.");
        for (String st : c.getKeys(false))
        {
            String path = c.getCurrentPath() + "." + st;
            entrysmsg.put(path, language.getString(path));
        }

        ConfigurationSection c2 = language.getConfigurationSection("Menu.");
        for (String st : c2.getKeys(false))
        {
            String path = c2.getCurrentPath() + "." + st;
            entrysmsg.put(path, language.getString(path));
        }

        ConfigurationSection c3 = language.getConfigurationSection("MenuAdmin.");
        for (String st : c3.getKeys(false))
        {
            String path = c3.getCurrentPath() + "." + st;
            entrysmsg.put(path, language.getString(path));
        }

        ConfigurationSection c4 = language.getConfigurationSection("ClanMenu.");
        for (String st : c4.getKeys(false))
        {
            String path = c4.getCurrentPath() + "." + st;
            entrysmsg.put(path, language.getString(path));
        }

        ConfigurationSection c5 = language.getConfigurationSection("Shop.");
        for (String st : c5.getKeys(false))
        {
            String path = c5.getCurrentPath() + "." + st;
            entrysmsg.put(path, language.getString(path));
        }
        return entrysmsg;
    }
}
