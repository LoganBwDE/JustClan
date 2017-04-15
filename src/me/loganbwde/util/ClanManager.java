package me.loganbwde.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.loganbwde.Clan.main;

public class ClanManager
{
    private ArrayList<String> members = new ArrayList<>();
    private main m;
    private HashMap<String, String> invites = new HashMap<>();
    public ClanManager(main main)
    {
        m = main;
    }
    
    public HashMap<String, String> getInvites()
    {
        return invites;
    }
    
    public boolean HaveClan(String p)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();

            if (rs.getString("clan").isEmpty())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (NullPointerException | SQLException e)
        {
        }
        return false;
    }
    
    @SuppressWarnings("unused")
    public void ClanCreate(String clan, String owner, String tag)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + owner + "';");
            rs.next();
            String ow = rs.getString("name");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET name = '" + owner + "' WHERE name ='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET uuid = '" + Bukkit.getPlayer(owner).getUniqueId().toString() + "' WHERE name ='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan = '" + clan + "' WHERE name ='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET kills = '" + "0" + "' WHERE name='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET deaths = '" + "0" + "' WHERE name='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET points = '" + "0" + "' WHERE name='" + owner + "';");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET rank = '" + "0" + "' WHERE name='" + owner + "';");
        }
        catch (Exception e)
        {}
        try
        {
            try
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE owner='" + owner + "';");
                rs.next();
                String ow = rs.getString("name");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET name = '" + clan + "' WHERE owner='" + owner + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET owner = '" + owner + "' WHERE owner='" + owner + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET tag = '" + tag + "' WHERE owner='" + owner + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET points = '" + "0" + "' WHERE owner='" + owner + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + "0" + "' WHERE owner='" + owner + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename + " SET home = '" + "" + "' WHERE owner='" + owner + "';");
                for (int i = 1; i <= 15; i++)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + "" + "' WHERE owner='" + owner + "';");
                }
            }
            catch (NullPointerException | SQLException ex)
            {
                m.statement.executeUpdate("INSERT INTO " + m.tablename + " (`name`, `owner`, `tag`, `points`, `rank`, `home`, `member1`, `member2`, `member3`, `member4`, `member5`, `member6`, `member7`, `member8`, `member9`, `member10`, `member11`, `member12`, `member13`, `member14`, `member15`) VALUES ('" + clan + "', '" + owner + "', '" + tag + "', '" + "0" + "', '" + "0" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "', '" + "" + "');");
            }

        }
        catch (SQLException e)
        {}
    }
    
    public String getClan(String p)
    {
        String clanName = null;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            clanName = rs.getString("clan");
        }
        catch (SQLException e)
        {}
        return clanName;
    }
    
    public String getClanByOwner(String owner)
    {
        String clanName = null;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE owner='" + owner + "';");
            rs.next();
            clanName = rs.getString("name");
        }
        catch (SQLException e)
        {}
        return clanName;
    }
    

    public void remClan(String clan, String p)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + clan + "';");
            rs.next();
            for (int i = 1; i <= 15; i++)
            {
                if (!rs.getString("member" + i).isEmpty())
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan = '" + "" + "' WHERE name ='" + rs.getString("member" + i) + "';");
                    m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET rank = '" + "" + "' WHERE name ='" + rs.getString("member" + i) + "';");
                }
                m.statement.executeUpdate("DELETE FROM " + m.tablename + " WHERE name='" + clan + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan = '" + "" + "' WHERE name ='" + p + "';");
                m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET rank = '" + "" + "' WHERE name ='" + p + "';");
            }
        }
        catch (SQLException e)
        {
            // e.printStackTrace();
        }

    }
    
    public boolean isOwner(String p)
    {
        String clan = "";
        String owner = "";
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            clan = rs.getString("clan");
            
            rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + clan + "';");
            rs.next();
            owner = rs.getString("owner");
        }
        catch (SQLException e)
        {}
        if (owner.matches(p))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String getClanTag(String p)
    {
        String clanTag = "";
        String clanName = getClan(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + clanName + "';");
            rs.next();
            clanTag = rs.getString("tag");
        }
        catch (SQLException e)
        {}
        return clanTag;
    }
    
    public String getClanOwner(String cl)
    {
        String owner = null;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            owner = rs.getString("owner");
        }
        catch (SQLException e)
        {}
        return owner;
    }   
    
    public ArrayList<String> getMember(String cl)
    {
        try
        {
            members.clear();
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            for (int i = 1; i <= 15; i++)
            {
                if (!rs.getString("member" + i).isEmpty())
                {
                    members.add(rs.getString("member" + i));
                }
            }
        }
        catch (SQLException | NullPointerException e)
        {
        }
        return members;
    }
    
    public int getClanSize(String p)
    {
        String cl = getClan(p);
        int rank = 0;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            rank = rs.getInt("rank");
        }
        catch (SQLException e)
        {}
        int amount = m.getFileManager().getConfig().getInt(("MemberAmount.Lvl" +  rank));
        return amount;
    }

    public double getKD(String p)
    {
        double kd = 0;
        double kills = 0;
        double deaths = 0;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            kills = rs.getInt("kills");
            deaths = rs.getInt("deaths");
        }
        catch (SQLException e)
        {}
        if (kills == 0)
        {
            kd = 0;
        }
        else
        {
            if (deaths == 0)
            {
                kd = kills;
            }
            else
            {
                kd = (kills) / (deaths);
            }
        }
        return kd;
    }

    public int getKill(String p)
    {
        int kills = 0;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            kills = rs.getInt("kills");
        }
        catch (SQLException e)
        {}
        return kills;
    }

    public int getDeath(String p)
    {
        int deaths = 0;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            deaths = rs.getInt("deaths");
        }
        catch (SQLException e)
        {}
        return deaths;
    }

    public float getClanKD(String p)
    {
        double allKD = 0;
        double kills = getClanKill(p);
        double deaths = getClanDeath(p);
        if (kills == 0)
        {
            allKD = 0;
        }
        else
        {
            if (deaths == 0)
            {
                allKD = kills;
            }
            else
            {
                allKD = (kills) / (deaths);
            }
        }
        float allKDN = (float) (((int) (allKD * 100)) / 100.0);
        return allKDN;
    }

    public int getClanKill(String p)
    {
        int allKill = 0;
        String cl = getClan(p);
        ArrayList<String> me = new ArrayList<String>();
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                me.add(rs.getString("member" + i));
            }
        }
        catch (SQLException e)
        {}

        for (int i = 0; i < me.size(); i++)
        {
            try
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + me.get(i) + "';");
                rs.next();
                allKill = allKill + rs.getInt("kills");
            }
            catch (SQLException e)
            {}
        }
        allKill = allKill + getKill(getClanOwner(getClan(p)));
        return allKill;
    }

    public int getClanDeath(String p)
    {
        int allDeath = 0;

        String cl = getClan(p);
        ArrayList<String> me = new ArrayList<String>();
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                me.add(rs.getString("member" + i));
            }
        }
        catch (SQLException e)
        {}

        for (int i = 0; i < me.size(); i++)
        {
            try
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + me.get(i) + "';");
                rs.next();
                allDeath = allDeath + rs.getInt("deaths");
            }
            catch (SQLException e)
            {}
        }
        allDeath = allDeath + getDeath(getClanOwner(getClan(p)));
        return allDeath;
    }
    
    public int getClanRank(String p)
    {
        int rank = 0;
        String cl = getClan(p);
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                rank = rs.getInt("rank");
            }
        }
        catch (SQLException e)
        {}
        return rank;
    }

    public int getClanLevel(String p)
    {     
        int level = 0;
        String cl = getClan(p);
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                level = rs.getInt("points");
            }
        }
        catch (SQLException e)
        {}
        return level;
    }
    
    public int getNeedLevel(String p)
    {
        String cl = getClan(p);
        int rank = 0;
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            rank = rs.getInt("rank");
        }
        catch (SQLException e)
        {}
        if(rank < 6)
        {
            return m.getFileManager().getConfig().getInt(("RankLevel.Lvl" +  rank + 1));
        }
        else
        {
            return 0;
        }
    }
    
    public void leaveClan(String cl, String p)
    {
        ArrayList<String> me = getMember(cl);
        me.remove(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            int j = 0;
            for (int i = 1; i <= 15; i++)
            {
                if (i < 15)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + me.get(j) + "' WHERE name='" + cl + "';");
                    j++;
                }
                if (i == 15)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + "" + "' WHERE name='" + cl + "';");
                }
            }
        }
        catch (SQLException e)
        {}

        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan = '" + "" + "' WHERE name='" + p + "';");
        }
        catch (SQLException e)
        {}
    }
    
    public void invClan(String invited, String inviter)
    {
        invites.put(invited, inviter);
    }
    
    public void ClanRevoke(String p, String pl)
    {
        if (invites.containsKey(p))
        {
            invites.remove(p);
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(p), m.getFileManager().getMessageEntrys().get("Messages.claninvrevoke2").replace("%player%", pl));
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(pl), m.getFileManager().getMessageEntrys().get("Messsages.claninvrevoke"));
        }
        else
        {
            m.getMessagesManager().sendMessage(Bukkit.getPlayer(pl), m.getFileManager().getMessageEntrys().get("Messages.notinv2").replace("%player%", p));
        }
    }
    

    public void joinClan(String cl, String p)
    {
        invites.remove(p);
        boolean joined = false;
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                if (rs.getString("member" + i).isEmpty() && joined == false)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + p + "' WHERE name ='" + cl + "';");
                    m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan = '" + cl + "' WHERE name ='" + p + "';");
                    joined = true;
                }
            }

        }
        catch (SQLException e)
        {}
    }

    public void ClanKick(String cl, String p)
    {
        ArrayList<String> me = getMember(cl);
        me.remove(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            int j = 0;
            for (int i = 1; i <= me.size(); i++)
            {
                if (me.get(j) != null)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + me.get(j) + "' WHERE name='" + cl + "';");
                }
                j++;
            }
            int size = me.size();
            size++;
            for (int i = size; i <= 15; i++)
            {
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET member" + i + " = '" + "" + "' WHERE name='" + cl + "';");
                }
            }
            for (int i = 1; i <= 15; i++)
            {
                rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
                rs.next();
                m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET clan= '" + "" + "' WHERE name='" + p + "';");
            }
        }
        catch (SQLException e)
        {}
        Player pl = Bukkit.getPlayer(p);
        if (pl != null)
        {
            m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.youwerekicked"));
        }
    }
    
    public int getPlayerLevel(String p)
    {
        int level = 0;
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
                rs.next();
                level = rs.getInt("points");
            }
        }
        catch (SQLException e)
        {}
        return level;
    }

    public void ClanPay(int level, String cl)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            level = level + rs.getInt("points");
            if (level < 0)
            {
                level = 0;
            }
            m.statement.executeUpdate("UPDATE " + m.tablename + " SET points = '" + level + "' WHERE name='" + cl + "';");
        }
        catch (SQLException e)
        {}
    }

    public void removePoints(String player, int points)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + player + "';");
            rs.next();
            if(points < 0)
            {
                points = rs.getInt("points") + points;
            }
            else
            {
                points = rs.getInt("points") - points;
            }
            if (points < 0)
            {
                points = 0;
            }
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET points = '" + points + "' WHERE name='" + player + "';");
        }
        catch (SQLException e)
        {}
    }
    

    public String getClanTagByOwner(String p)
    {
        String clanTag = null;
        String clanName = getClanByOwner(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + clanName + "';");
            rs.next();
            clanTag = rs.getString("tag");
        }
        catch (SQLException e)
        {}
        return clanTag;
    }
    

    public int getClanLevelByOwner(String p)
    {
        int level = 0;
        String cl = getClanByOwner(p);
        try
        {
            for (int i = 1; i <= 15; i++)
            {
                ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                rs.next();
                level = rs.getInt("points");
            }
        }
        catch (SQLException e)
        {}
        return level;
    } 
    

    public Location getClanHome(String p)
    {
        Location loc = null;
        String l;
        String cl = getClan(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            l = rs.getString("home");

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
    

    public void setClanHome(String p, Location loc)
    {
        String cl = getClan(p);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            String l = "";
            l += loc.getWorld().getName();
            l += ",";
            l += String.valueOf(loc.getBlockX());
            l += ",";
            l += String.valueOf(loc.getBlockY());
            l += ",";
            l += String.valueOf(loc.getBlockZ());
            m.statement.executeUpdate("UPDATE " + m.tablename + " SET home = '" + l + "' WHERE name='" + cl + "';");
        }
        catch (SQLException e)
        {}
    }
    
    public ArrayList<String> getRank(Player p)
    {
        ArrayList<String> rank = new ArrayList<String>();
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p.getName() + "';");
            rs.next();
            try
            {
                if (rs.getString("rank").equalsIgnoreCase("") || rs.getString("rank") == null)
                {
                    m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET rank = '" + "norank" + "' WHERE name='" + p.getName() + "';");
                    rank.add("norank");
                }
                else
                {
                    rank.add(rs.getString("rank")); // rankName
                }
            }
            catch (NullPointerException ex)
            {
            }
        } 
        catch (SQLException e)
        {}
        try
        {
            if (rank.get(0).equalsIgnoreCase("") || rank.get(0) == null || rank.get(0).equalsIgnoreCase("norank"))
            {
                rank.add("Newbie");
            }
            else
            {
                if (rank.get(0).equalsIgnoreCase("Newbie"))
                {
                    rank.add("Member");
                }
                else
                {
                    if (rank.get(0).equalsIgnoreCase("Member"))
                    {
                        rank.add("Elite");
                    }
                    else
                    {
                        if (rank.get(0).equalsIgnoreCase("Elite"))
                        {
                            rank.add("nonextrank");
                        }
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException exe)
        {}

        rank.add(String.valueOf(m.getConfig().getInt("ClanRank." + "." + rank.get(1) + ".Points"))); // rankPoints
        rank.add(String.valueOf(m.getConfig().getInt("ClanRank." + "." + rank.get(1) + ".Kills"))); // rankKills
        rank.add(m.getConfig().getString("ClanRank." + "." + rank.get(0) + ".Color")); // rankColorNow
        rank.add(m.getConfig().getString("ClanRank." + "." + rank.get(1) + ".Color")); // rankColorNext
        return rank;
    }

    public void nextRank(Player p)
    {
        ArrayList<String> rank = getRank(p);
        String nextrank = rank.get(1);
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p.getName() + "';");
            rs.next();
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET rank = '" + nextrank + "' WHERE name='" + p.getName() + "';");
        }
        catch (SQLException e)
        {}
    }

    public ArrayList<String> getAllClans()
    {
        ArrayList<String> clans = new ArrayList<String>();
        try
        {   
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " ORDER BY name DESC");
            while (rs.next() == true)
            {
                clans.add(rs.getString("name"));
            }
        }
        catch (SQLException e)
        {}
        return clans;
    }
    

    public boolean ClanExists(String cl)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
            rs.next();
            if (!rs.getString("name").isEmpty())
            {
                return true;
            }
        }
        catch (NullPointerException | SQLException e)
        {}
        return false;
    }
    

    public boolean PlayerExists(String player)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + player + "';");
            rs.next();

            if (!rs.getString("name").isEmpty())
            {
                return true;
            }
        }
        catch (NullPointerException | SQLException e)
        {}
        return false;
    }
    
    public void removeClanPoints(String player, int points)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + player + "';");
            rs.next();
            points = points + rs.getInt("points");
            if (points < 0)
            {
                points = 0;
            }
            m.statement.executeUpdate("UPDATE " + m.tablename + " SET points = '" + points + "' WHERE name='" + player + "';");
        }
        catch (SQLException e)
        {}
    }

    public void addPoints(String player, int points)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + player + "';");
            rs.next();
            points = points + rs.getInt("points");
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET points = '" + points + "' WHERE name='" + player + "';");
        }
        catch (SQLException e)
        {}
    }

    public void addClanPoints(String player, int points)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + player + "';");
            rs.next();
            points = points + rs.getInt("points");
            m.statement.executeUpdate("UPDATE " + m.tablename + " SET points = '" + points + "' WHERE name='" + player + "';");
        }
        catch (SQLException e)
        {}
    }
    
    public void addLevel(Player p, Player p2)
    {
        Inventory inv = p.getInventory();
        int items = 1;
        for (ItemStack item : inv.getContents())
        {
            if (item != null && item.getType() != Material.AIR)
            {
                items++;
            }
        }

        for (ItemStack item : p.getInventory().getArmorContents())
        {
            if (item != null && item.getType() != Material.AIR)
            {
                items++;
            }
        }

        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p2.getName() + "';");
            rs.next();
            int points = rs.getInt("points");
            points = points + items;
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET points='" + points + "' WHERE name='" + p2.getName() + "';");
        }
        catch (SQLException e)
        {}
    }

    public void addKill(String p)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            int kills = rs.getInt("kills");
            kills++;
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET kills = '" + kills + "' WHERE name='" + p + "';");
        }
        catch (SQLException e)
        {}
    }

    public void addDeath(String p)
    {
        try
        {
            ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename2 + " WHERE name='" + p + "';");
            rs.next();
            int deaths = rs.getInt("deaths");
            deaths++;
            m.statement.executeUpdate("UPDATE " + m.tablename2 + " SET deaths = '" + deaths + "' WHERE name='" + p + "';");
        }
        catch (SQLException e)
        {}
    }
    
    public void Rank(String cl, String p)
    {
        int rank = getClanRank(p);
        int level = getClanLevel(p);
        boolean rankup = false;
        Player pl = Bukkit.getPlayer(p);
        if (rank == 0)
        {
            if (level >= m.getFileManager().getConfig().getInt("RankLevel.Lvl1"))
            {
                rank = 1;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl1");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }
        if (rank == 1)
        {
            if (level >=  m.getFileManager().getConfig().getInt("RankLevel.Lvl2"))
            {
                rank = 2;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl2");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }
        if (rank == 2)
        {
            if (level >=  m.getFileManager().getConfig().getInt("RankLevel.Lvl3"))
            {
                rank = 3;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl3");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }
        if (rank == 3)
        {
            if (level >=  m.getFileManager().getConfig().getInt("RankLevel.Lvl4"))
            {
                rank = 4;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl4");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }
        if (rank == 4)
        {
            if (level >=  m.getFileManager().getConfig().getInt("RankLevel.Lvl5"))
            {
                rank = 5;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl5");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }
        if (rank == 5)
        {
            if (level >=  m.getFileManager().getConfig().getInt("RankLevel.Lvl6"))
            {
                rank = 6;
                level = level -  m.getFileManager().getConfig().getInt("RankLevel.Lvl6");
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET level = '" + level + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                try
                {
                    ResultSet rs = m.statement.executeQuery("SELECT * FROM " + m.tablename + " WHERE name='" + cl + "';");
                    rs.next();
                    m.statement.executeUpdate("UPDATE " + m.tablename + " SET rank = '" + rank + "' WHERE name='" + cl + "';");
                }
                catch (SQLException e)
                {}
                m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.clanrankup"));
                rankup = true;
            }
        }

        if (rankup == false)
        {
            m.getMessagesManager().sendMessage(pl, m.getFileManager().getMessageEntrys().get("Messages.notenoughclanpoints"));
        }
    }
    

    public String getTagColour(String p)
    {
        if (getClanKD(p) <= 1)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl0");
        }
        if (getClanKD(p) <= 2)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl1");
        }
        if (getClanKD(p) <= 3)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl2");
        }
        if (getClanKD(p) <= 4)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl3");
        }
        if (getClanKD(p) <= 5)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl4");
        }
        if (getClanKD(p) <= 6)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl5");
        }
        if (getClanKD(p) > 6)
        {
            return m.getFileManager().getConfigEntrys().get("RankColour.Lvl6");
        }
        return "&f";
    }


}
