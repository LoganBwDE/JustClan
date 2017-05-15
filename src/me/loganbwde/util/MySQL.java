package me.loganbwde.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import me.loganbwde.Clan.main;

import java.sql.Connection;

public class MySQL
{
    private String host;
    private int port;
    private String user;
    private String password;
    private String database;
    private main m;
    private Connection connection;
    private Statement statement;

    public MySQL(main main)
    {
        m = main;
        m.getFileManager().getDatabase().addDefault("Database." + ".Host", "localhost");
        m.getFileManager().getDatabase().addDefault("Database." + ".Port", 3306);
        m.getFileManager().getDatabase().addDefault("Database." + ".User", "user");
        m.getFileManager().getDatabase().addDefault("Database." + ".Password", "password");
        m.getFileManager().getDatabase().addDefault("Database." + ".Database", "database");

        m.getFileManager().saveDatabase();
        m.getFileManager().loadFiles();

        host = m.getFileManager().getDatabase().getString("Database." + ".Host");
        port = m.getFileManager().getDatabase().getInt("Database." + ".Port");
        user = m.getFileManager().getDatabase().getString("Database." + ".User");
        password = m.getFileManager().getDatabase().getString("Database." + ".Password");
        database = m.getFileManager().getDatabase().getString("Database." + ".Database");
    }

    public void openConnection() throws SQLException, ClassNotFoundException
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                return;
            }

            synchronized (this)
            {
                if (connection != null && !connection.isClosed())
                {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
        }
    }

    public Statement getConnection()
    {
        try
        {
            openConnection();
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getConfigEntrys().get("Basic.prefix") + " &cMySQL-Connection failed. Please check your Data and restart your server."));
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getConfigEntrys().get("Basic.prefix") + " &cPLUGIN WILL BE DISABLED NOW."));
            Bukkit.getPluginManager().disablePlugin(m);
        }
        return statement;
    }
}
