package me.loganbwde.Clan;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import me.loganbwde.cmd.CmdMain;
import me.loganbwde.lst.OnBreak;
import me.loganbwde.lst.OnChat;
import me.loganbwde.lst.OnDamage;
import me.loganbwde.lst.OnInvClick;
import me.loganbwde.lst.OnInvClose;
import me.loganbwde.lst.OnJoin;
import me.loganbwde.lst.OnKill;
import me.loganbwde.lst.OnMove;
import me.loganbwde.lst.OnPlace;
import me.loganbwde.lst.OnRespawn;
import me.loganbwde.util.Api;
import me.loganbwde.util.ClanManager;
import me.loganbwde.util.FileManager;
import me.loganbwde.util.MessagesManager;
import me.loganbwde.util.MySQL;
import me.loganbwde.util.PermissionsManager;
import me.loganbwde.util.ScoreboardManager;
import me.loganbwde.util.ShopManager;
import me.loganbwde.util.WarsManager;
import net.milkbowl.vault.economy.Economy;

public class main extends JavaPlugin
{
    private FileManager fman;
    private MessagesManager msgman;
    private PermissionsManager pman;
    private ClanManager clman;
    private ScoreboardManager scman;
    private MySQL sql;
    private ShopManager shman;
    private WarsManager wsman;
    public Api api;

    public String tablename = "justclan_clans";
    public String tablename2 = "justclan_players";
    public String tablename3 = "justclan_locations";
    private Economy economy;
    public Team Team1;
    public Team Team2;
    private int task;

    public HashMap<String, String> inwars = new HashMap<String, String>();
    public HashMap<String, String> killedwars = new HashMap<String, String>();
    public HashMap<String, String> inedit = new HashMap<String, String>();

    public ArrayList<String> matedit = new ArrayList<String>();
    public ArrayList<String> amountedit = new ArrayList<String>();
    public ArrayList<String> nameedit = new ArrayList<String>();
    public ArrayList<String> enchedit = new ArrayList<String>();
    public ArrayList<String> costedit = new ArrayList<String>();
    
    public ArrayList<String> chat = new ArrayList<String>();

    public Statement statement;

    @Override
    public void onEnable()
    {
        setup();

        BukkitRunnable r = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                try
                {
                    sql.openConnection();
                    statement = sql.getConnection();
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        };

        r.runTaskAsynchronously(this);

        try
        {
            statement = sql.getConnection();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), owner VARCHAR(25), tag VARCHAR(25), points FLOAT(25), rank FLOAT(25), home VARCHAR(1000), member1 VARCHAR(25), member2 VARCHAR(25), member3 VARCHAR(25), member4 VARCHAR(25), member5 VARCHAR(25), member6 VARCHAR(25), member7 VARCHAR(25), member8 VARCHAR(25), member9 VARCHAR(25), member10 VARCHAR(25), member11 VARCHAR(25), member12 VARCHAR(25), member13 VARCHAR(25), member14 VARCHAR(25), member15 VARCHAR(25));");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename2 + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), uuid VARCHAR(100), clan VARCHAR(25), kills FLOAT(25), deaths FLOAT(25), points FLOAT(25), rank VARCHAR(25));");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename3 + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), location1 VARCHAR(1000), location2 VARCHAR(1000), spawn1 VARCHAR(1000), spawn2 VARCHAR(1000), end VARCHAR(1000));");
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }

        scman.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', fman.getConfigEntrys().get("Basic.prefix") + " Plugin activated."));

        if (Boolean.parseBoolean(fman.getConfigEntrys().get("Basic.scoreboard")) == true)
        {
            if (!Bukkit.getScheduler().isCurrentlyRunning(task))
            {
                task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
                {
                    @Override
                    public void run()
                    {
                        scman.updateSB();
                        scman.TabAkt();
                    }
                }, 20, 20);
            }
        }
    }

    @Override
    public void onDisable()
    {
        if (Boolean.parseBoolean(fman.getConfigEntrys().get("Basic.scoreboard")) == true)
        {
            for (Team a : scman.getScoreboard().getTeams())
            {
                a.unregister();
            }
        }

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', fman.getConfigEntrys().get("Basic.prefix") + " Plugin deactivated."));
    }
    
    private void setup()
    {
        setupEconomy();
        
        fman = new FileManager(this);
        msgman = new MessagesManager(this);
        pman = new PermissionsManager(this);
        clman = new ClanManager(this);
        scman = new ScoreboardManager(this);
        sql = new MySQL(this);
        shman = new ShopManager(this);
        wsman = new WarsManager(this);
        api = new Api(this);
        
        new OnBreak(this);
        new OnChat(this);
        new OnDamage(this);
        new OnInvClick(this);
        new OnInvClose(this);
        new OnJoin(this);
        new OnKill(this);
        new OnMove(this);
        new OnPlace(this);
        new OnRespawn(this);
        
        CmdMain cmdmain = new CmdMain(this);
        getCommand("clan").setExecutor(cmdmain);
        getCommand("clreload").setExecutor(cmdmain);
    }
    

    private boolean setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null)
        {
            return false;
        }
        economy = (Economy) rsp.getProvider();
        return economy != null;
    }
    
    public FileManager getFileManager()
    {
        return fman;
    }

    public MessagesManager getMessagesManager()
    {
        return msgman;
    }

    public PermissionsManager getPermissionsManager()
    {
        return pman;
    }

    public MySQL getSQL()
    {
        return sql;
    }
    
    public ClanManager getClanManager()
    {
        return clman;
    }
    
    public Economy getEcononmy()
    {
        return economy;
    }
    
    public ScoreboardManager getScoreboardManager()
    {
        return scman;
    }
    
    public ShopManager getShopManager()
    {
        return shman;
    }
    
    public WarsManager getWarsManager()
    {
        return wsman;
    }
    
    public Api getApi()
    {
        return api;
    }
}
