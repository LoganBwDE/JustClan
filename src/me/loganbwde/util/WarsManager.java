package me.loganbwde.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class WarsManager
{
    private  Map<String, String> loc = new HashMap<String, String>();
    private  Map<String, String> wars = new HashMap<String, String>();
    private main m;
    public WarsManager(main main)
    {
        m = main;
    }
    public ArrayList<String> getWarsLocations()
    {
        ArrayList<String> locations = new ArrayList<String>();
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " ORDER BY name DESC");
            while (rs.next() == true)
            {
                locations.add(rs.getString("name"));
            }
        }
        catch (SQLException e)
        {}
        return locations;
    }

    public void sendWarsInvite(String clansend, String claninvited, String location)
    {
        String ownersend = m.getClanManager().getClanOwner(clansend);
        String ownerinvited = m.getClanManager().getClanOwner(claninvited);
        
        if (loc.containsValue(location))
        {
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(ownersend), m.getFileManager().getMessageEntrys().get("Messages.warsinuse"));
        }
        else
        {
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(ownersend), m.getFileManager().getMessageEntrys().get("Messages.warsinvited").replace("%clan%", claninvited));
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(ownerinvited), m.getFileManager().getMessageEntrys().get("Messages.warswasinvited").replace("%clan%", clansend));
            wars.put(claninvited, clansend);
            loc.put(claninvited, location);
        }
    }
    
    public void acceptWars(String clan)
    {
        String clansend = wars.get(clan);
        String owner = m.getClanManager().getClanOwner(clansend);
        if (Bukkit.getOnlinePlayers().contains(owner) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(owner)))
        {
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(owner), m.getFileManager().getMessageEntrys().get("Messages.warsaccepted"));
        }
        startWars(clansend, clan, loc.get(clan));
    }

    public void denyWars(String clan)
    {
        String clansend = wars.get(clan);
        String owner = m.getClanManager().getClanOwner(clansend);
        if (Bukkit.getOnlinePlayers().contains(owner) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(owner)))
        {
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(owner), m.getFileManager().getMessageEntrys().get("Messages.warsdenyd"));
        }
        wars.remove(clan);
    }

    public void startWars(String clan1, String clan2, String wars)
    {
        // Ab hier teleportieren und Spiel in die Wege leiten also Timer, Chat
        // Ausgabe gegen wen etc.
        ArrayList<String> member1 = m.getClanManager().getMember(clan1);
        ArrayList<String> member2 = m.getClanManager().getMember(clan2);

        m.Team1 = (m.getScoreboardManager().getScoreboard().getTeam("Team1") == null ? m.getScoreboardManager().getScoreboard().registerNewTeam("Team1") : m.getScoreboardManager().getScoreboard().getTeam("Team1"));
        m.Team2 = (m.getScoreboardManager().getScoreboard().getTeam("Team2") == null ? m.getScoreboardManager().getScoreboard().registerNewTeam("Team2") : m.getScoreboardManager().getScoreboard().getTeam("Team2"));

        for (int i = 0; i < member1.size(); i++)
        {
            if (Bukkit.getOnlinePlayers().contains(member1.get(i)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(member1.get(i))))
            {
                Location loc1 = this.getSpawnLocation1(wars);
                Bukkit.getPlayer(member1.get(i)).teleport(loc1);
                m.inwars.put(member1.get(i), wars);
                m.Team1.addEntry(member1.get(i));
            }
        }
        for (int i = 0; i < member2.size(); i++)
        {
            if (Bukkit.getOnlinePlayers().contains(member2.get(i)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(member2.get(i))))
            {
                Location loc2 = this.getSpawnLocation2(wars);
                Bukkit.getPlayer(member2.get(i)).teleport(loc2);
                m.inwars.put(member2.get(i), wars);
                m.Team2.addEntry(member2.get(i));
            }
        }

        if (Bukkit.getOnlinePlayers().contains(m.getClanManager().getClanOwner(clan1)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(m.getClanManager().getClanOwner(clan1))))
        {
            Location loc1 = this.getSpawnLocation1(wars);
            Bukkit.getPlayer(m.getClanManager().getClanOwner(clan1)).teleport(loc1);
            m.inwars.put(m.getClanManager().getClanOwner(clan1), wars);
            m.Team1.addEntry(m.getClanManager().getClanOwner(clan1));
        }

        if (Bukkit.getOnlinePlayers().contains(m.getClanManager().getClanOwner(clan2)) || Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(m.getClanManager().getClanOwner(clan2))))
        {
            Location loc2 = this.getSpawnLocation2(wars);
            Bukkit.getPlayer(m.getClanManager().getClanOwner(clan2)).teleport(loc2);
            m.inwars.put(m.getClanManager().getClanOwner(clan2), wars);
            m.Team2.addEntry(m.getClanManager().getClanOwner(clan2));
        }
    } 
    
    public Location getSpawnLocation1(String wars)
    {
        Location loc = null;
        String l;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + wars + "';");
            rs.next();
            l = rs.getString("spawn1");

            String[] arg = l.split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++)
            {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }

            loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
        }
        catch (SQLException e)
        {}
        return loc;
    }

    public Location getSpawnLocation2(String wars)
    {
        Location loc = null;
        String l;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + wars + "';");
            rs.next();
            l = rs.getString("spawn2");
                
            String[] arg = l.split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++)
            {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }

            loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
        }
        catch (SQLException e)
        {}
        return loc;
    }
    
    public Location getEndLocation(String wars)
    {
        Location loc = null;
        String l;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + wars + "';");
            rs.next();
            l = rs.getString("end");

            String[] arg = l.split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++)
            {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }
            loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
        }
        catch (SQLException e)
        {}
        return loc;
    }

    public Location getWarsLocation1(String wars)
    {
        Location loc = null;
        String l;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + wars + "';");
            rs.next();
            l = rs.getString("location1");

            String[] arg = l.split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++)
            {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }

            loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
        }
        catch (SQLException e)
        {}
        return loc;
    }

    public Location getWarsLocation2(String wars)
    {
        Location loc = null;
        String l;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename3 + " WHERE name='" + wars + "';");
            rs.next();
            l = rs.getString("location2");

            String[] arg = l.split(",");
            double[] parsed = new double[3];
            for (int a = 0; a < 3; a++)
            {
                parsed[a] = Double.parseDouble(arg[a + 1]);
            }

            loc = new Location(Bukkit.getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
        }
        catch (SQLException e)
        {}
        return loc;
    }
    
    public boolean checkWarsOver(String killer, String killed)
    {
        boolean over = false;
        if (m.Team1.hasEntry(killed))
        {
            m.Team1.removeEntry(killed);
            if (m.Team1.getEntries().size() == 0)
            {
                over = true;
            }
        }
        else
        {
            if (m.Team2.hasEntry(killed))
            {
                m.Team2.removeEntry(killed);
                if (m.Team2.getEntries().size() == 0)
                {
                    over = true;
                }
            }
        }
        
        if(over)
        {
            boolean member1 = false;
            for (int i = 0; i < m.getClanManager().getMember(m.getClanManager().getClan(killer)).size(); i++)
            {
                Player pl = Bukkit.getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(killer)).get(i));
                if (pl != null)
                {
                    m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
                    member1 = true;
                }
            }
            if (member1 == true)
            {
                Player po = Bukkit.getPlayer(m.getClanManager().getClanOwner(m.getClanManager().getClan(killer)));
                if (po != null)
                {
                    m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(Bukkit.getPlayer(killer), m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
            }

            boolean member2 = false;
            for (int i = 0; i < m.getClanManager().getMember(m.getClanManager().getClan(killed)).size(); i++)
            {
                Player pl = Bukkit.getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(killed)).get(i));
                if (pl != null)
                {
                    m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
                    member2 = true;
                }
            }
            if (member2 == true)
            {
                Player po = Bukkit.getPlayer(m.getClanManager().getClanOwner(m.getClanManager().getClan(killed)));
                if (po != null)
                {
                    m.getMessagesManager().sendMessage(po, m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(Bukkit.getPlayer(killer), m.getFileManager().getMessageEntrys().get("Messages.teamwon").replace("%clanwon%", m.getClanManager().getClan(killer).replace("%clanlose%", m.getClanManager().getClan(killed))));
            }

            m.getClanManager().ClanPay(Integer.valueOf(m.getFileManager().getConfigEntrys().get("Clanwars.pointslose")), m.getClanManager().getClan(killed));
            m.getClanManager().ClanPay(Integer.valueOf(m.getFileManager().getConfigEntrys().get("Clanwars.pointswin")), m.getClanManager().getClan(killer));
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String checkPlayer(Player p, String location)
    {
        Location loc1 = getWarsLocation1(location);
        Location loc2 = getWarsLocation2(location);

        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();

        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();

        int bx = p.getLocation().getBlockX();
        int by = p.getLocation().getBlockY();
        int bz = p.getLocation().getBlockZ();

        if (y1 > y2)
        {
            int savey1 = y1;
            int savex1 = x1;
            int savez1 = z1;
            int savey2 = y2;
            int savex2 = x2;
            int savez2 = z2;

            y1 = savey2;
            y2 = savey1;
            x1 = savex2;
            x2 = savex1;
            z1 = savez2;
            z2 = savez1;
        }

        if (y1 <= by && by <= y2 && x2 <= bx && bx <= x1 && z1 <= bz && bz <= z2)
        {
            if (bx == x1)
            {
                return "x1";
            }
            else
            {
                if (bx == x2)
                {
                    return "x2";
                }
                else
                {
                    if (bz == z1)
                    {
                        return "z1";
                    }
                    else
                    {
                        if (bz == z2)
                        {
                            return "z2";
                        }
                        else
                        {
                            return "";
                        }
                    }
                }
            }
        }
        else
        {
            if (y1 <= by && by <= y2 && x2 <= bx && bx <= x1 && z2 <= bz && bz <= z1)
            {
                if (bx == x1)
                {
                    return "x1";
                }
                else
                {
                    if (bx == x2)
                    {
                        return "x2";
                    }
                    else
                    {
                        if (bz == z1)
                        {
                            return "z2";
                        }
                        else
                        {
                            if (bz == z2)
                            {
                                return "z1";
                            }
                            else
                            {
                                return "";
                            }
                        }
                    }
                }
            }
            else
            {
                if (y1 <= by && by <= y2 && x1 <= bx && bx <= x2 && z2 <= bz && bz <= z1)
                {
                    if (bx == x1)
                    {
                        return "x2";
                    }
                    else
                    {
                        if (bx == x2)
                        {
                            return "x1";
                        }
                        else
                        {
                            if (bz == z1)
                            {
                                return "z2";
                            }
                            else
                            {
                                if (bz == z2)
                                {
                                    return "z1";
                                }
                                else
                                {
                                    return "";
                                }
                            }
                        }
                    }
                }
                else
                {
                    if (y1 <= by && by <= y2 && x1 <= bx && bx <= x2 && z1 <= bz && bz <= z2)
                    {
                        if (bx == x1)
                        {
                            return "x2";
                        }
                        else
                        {
                            if (bx == x2)
                            {
                                return "x1";
                            }
                            else
                            {
                                if (bz == z1)
                                {
                                    return "z1";
                                }
                                else
                                {
                                    if (bz == z2)
                                    {
                                        return "z2";
                                    }
                                    else
                                    {
                                        return "";
                                    }
                                }
                            }
                        }
                    }
                    else
                    {

                    }
                }
            }
        }
        return "";
    }
    public Map<String, String> getWars()
    {
        return wars;
    }
    public Map<String, String> getLocation()
    {
        return loc;
    }

   
}
