package me.loganbwde.lst;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobProgression;

import me.loganbwde.Clan.main;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class OnChat implements Listener
{
    private main m;
    public OnChat(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }
    
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e)
    {
        FileConfiguration shop = m.getFileManager().getShop();
        Player p = e.getPlayer();
        if (m.matedit.contains(e.getPlayer().getName()))
        {
            e.setCancelled(true);
            try
            {
                Material mat = Material.getMaterial(e.getMessage().toUpperCase());
                String shsl = m.inedit.get(e.getPlayer().getName());
                String[] args = shsl.split(",");
                shop.set(args[0] + "." + "slot" + args[1] + "." + ".Material", mat.name().toUpperCase());
                m.getFileManager().saveShop();
                m.matedit.remove(e.getPlayer().getName());
                m.inedit.remove(e.getPlayer().getName());
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.itemupdated"));
                m.getShopManager().openShop(e.getPlayer());
            }
            catch (NullPointerException ex)
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.matnotavailable"));
                m.matedit.remove(e.getPlayer().getName());
                m.inedit.remove(e.getPlayer().getName());
            }
        }
        else
        {
            if (m.amountedit.contains(e.getPlayer().getName()))
            {
                e.setCancelled(true);
                try
                {
                    int amount = Integer.parseInt(e.getMessage());
                    if (amount <= 64 && amount > 0)
                    {
                        String shsl = m.inedit.get(e.getPlayer().getName());
                        String[] args = shsl.split(",");
                        shop.set(args[0] + "." + "slot" + args[1] + "." + ".Amount", amount);
                        m.getFileManager().saveShop();
                        m.amountedit.remove(e.getPlayer().getName());
                        m.inedit.remove(e.getPlayer().getName());
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.itemupdated"));
                        m.getShopManager().openShop(e.getPlayer());
                    }
                    else
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.novalidnumber"));
                        m.amountedit.remove(e.getPlayer().getName());
                        m.inedit.remove(e.getPlayer().getName());
                    }
                }
                catch (NumberFormatException nfx)
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.novalidnumber"));
                    m.amountedit.remove(e.getPlayer().getName());
                    m.inedit.remove(e.getPlayer().getName());
                }
            }
            else
            {
                if (m.nameedit.contains(e.getPlayer().getName()))
                {
                    e.setCancelled(true);
                    String shsl = m.inedit.get(e.getPlayer().getName());
                    String[] args = shsl.split(",");
                    shop.set(args[0] + "." + "slot" + args[1] + "." + ".Name", e.getMessage());
                    m.getFileManager().saveShop();
                    m.nameedit.remove(e.getPlayer().getName());
                    m.inedit.remove(e.getPlayer().getName());
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.itemupdated"));
                    m.getShopManager().openShop(e.getPlayer());
                }
                else
                {
                    if (m.enchedit.contains(e.getPlayer().getName()))
                    {
                        e.setCancelled(true);
                        ArrayList<String> enchantments = new ArrayList<String>();
                        ArrayList<String> level = new ArrayList<String>();
                        String[] en = e.getMessage().toUpperCase().split(",");
                        boolean wrongench = false;
                        for (int i = 0; i < en.length; i++)
                        {
                            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15 || i == 17 || i == 19)
                            {
                                level.add(en[i]);
                            }
                            else
                            {
                                if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12 || i == 14 || i == 16 || i == 18 || i == 20)
                                {
                                    Enchantment enchantment = Enchantment.getByName(en[i]);
                                    if (enchantment != null)
                                    {
                                        enchantments.add(en[i]);
                                    }
                                    else
                                    {
                                        wrongench = true;
                                    }
                                }
                            }
                        }
                        if (wrongench == false)
                        {
                            String lvl = "";
                            String ench = "";
                            boolean firstlvl = true;
                            boolean firstench = true;
                            for (int j = 0; j < enchantments.size(); j++)
                            {
                                if (firstench)
                                {
                                    ench = enchantments.get(j);
                                    firstench = false;
                                }
                                else
                                {
                                    ench = ench + "," + enchantments.get(j);
                                }
                            }

                            for (int j = 0; j < level.size(); j++)
                            {
                                if (firstlvl)
                                {
                                    lvl = level.get(j);
                                    firstlvl = false;
                                }
                                else
                                {
                                    lvl = lvl + "," + level.get(j);
                                }
                            }
                            String shsl = m.inedit.get(e.getPlayer().getName());
                            String[] args = shsl.split(",");
                            shop.set(args[0] + "." + "slot" + args[1] + "." + ".Enchantment", ench);
                            shop.set(args[0] + "." + "slot" + args[1] + "." + ".EnchantmentLevel", lvl);
                            m.getFileManager().saveShop();
                            m.enchedit.remove(e.getPlayer().getName());
                            m.inedit.remove(e.getPlayer().getName());
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.itemupdated"));
                            m.getShopManager().openShop(e.getPlayer());
                        }
                        else
                        {
                            m.enchedit.remove(e.getPlayer().getName());
                            m.inedit.remove(e.getPlayer().getName());
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.novalidenchantment"));
                        }
                    }
                    else
                    {
                        if (m.costedit.contains(e.getPlayer().getName()))
                        {
                            e.setCancelled(true);
                            try
                            {
                                int cost = Integer.parseInt(e.getMessage());
                                String shsl = m.inedit.get(e.getPlayer().getName());
                                String[] args = shsl.split(",");
                                shop.set(args[0] + "." + "slot" + args[1] + "." + ".Cost", cost);
                                m.getFileManager().saveShop();
                                m.costedit.remove(e.getPlayer().getName());
                                m.inedit.remove(e.getPlayer().getName());
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.itemupdated"));
                                m.getShopManager().openShop(e.getPlayer());
                            }
                            catch (NumberFormatException nfx)
                            {
                                m.costedit.remove(e.getPlayer().getName());
                                m.inedit.remove(e.getPlayer().getName());
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.novalidnumber"));
                            }
                        }
                        else
                        {
                            ClanChat(e);
                        }
                    }
                }
            }
        }
    }
    
    public void ClanChat(AsyncPlayerChatEvent e)
    {
        try
        {
            Player p = e.getPlayer();
            String pName = p.getCustomName();
            if (pName == null)
            {
                pName = p.getDisplayName();
            }
            if (Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("Basic.builtInChat")))
            {
                String format = "";
                boolean hasformat = false;
                if(m.getClanManager().HaveClan(pName))
                {
                    boolean clanChat = false;
                    if (e.getMessage().startsWith(m.getFileManager().getConfigEntrys().get("Basic.chatprefix")))
                    {
                        if (Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("Chat.clanchat.use")))
                        {
                            format = m.getFileManager().getConfigEntrys().get("Chat.clanchat.format");
                            clanChat = true;
                            hasformat = true;
                        }
                    }
                    else
                    {
                        if (Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("Chat.withclan.use")))
                        {
                            format = m.getFileManager().getConfigEntrys().get("Chat.withclan.format");
                            hasformat = true;
                        }
                    }
                    if(hasformat)
                    {
                        if(checkPex())
                        {
                            PermissionUser user = PermissionsEx.getUser(p);
                            String prefix = user.getPrefix();
                            String suffix = user.getSuffix();
                            format = format.replaceAll("<prefix>", prefix).replaceAll("<suffix>", suffix);
                        }
                        if(checkJobs())
                        {
                            String job = "";
                            List<JobProgression> jobs = Jobs.getPlayerManager().getJobsPlayer(p).getJobProgression();
                            if (!jobs.isEmpty())
                            {
                                boolean first = false;
                                for (JobProgression OneJob : jobs)
                                {
                                    if (first == false)
                                    {
                                        job = OneJob.getJob().getName();
                                        first = true;
                                    }
                                }
                            }
                            format = format.replaceAll("<job>", job);
                        }
                        String tag = m.getClanManager().getTagColour(pName) + m.getClanManager().getClanTag(pName);
                        String clan = m.getClanManager().getClan(pName);
                        if(clanChat || m.chat.contains(pName))
                        {
                            if(m.getFileManager().getConfigEntrys().get("Chat.clanchat.range").equalsIgnoreCase("global"))
                            {
                                format = format.replaceAll("<tag>", tag).replaceAll("<player>", "%1$s").replaceAll("<clan>", clan).replaceAll("<message>", "%2$s");
                                boolean member = false;
                                for (int i = 0; i < m.getClanManager().getMember(m.getClanManager().getClan(pName)).size(); i++)
                                {
                                    Player pl = Bukkit.getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(pName)).get(i));
                                    if (pl != null)
                                    {
                                        m.getMessagesManager().sendMessageClan(pl, format);
                                        member = true;
                                    }
                                }
                                if (member == true)
                                {
                                    Player po = Bukkit.getPlayer(m.getClanManager().getClanOwner(m.getClanManager().getClan(pName)));
                                    if (po != null)
                                    {
                                        m.getMessagesManager().sendMessageClan(po, format);
                                    }
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessageClan(p, m.getFileManager().getMessageEntrys().get("Messages.nooneonline"));
                                }
                                e.setCancelled(true);
                            }
                            else
                            {
                                format = format.replaceAll("<tag>", tag).replaceAll("<player>", pName).replaceAll("<clan>", clan).replaceAll("<message>", e.getMessage());
                                boolean member = false;
                                for (int i = 0; i < m.getClanManager().getMember(m.getClanManager().getClan(pName)).size(); i++)
                                {
                                    Player pl = Bukkit.getPlayer((String) m.getClanManager().getMember(m.getClanManager().getClan(pName)).get(i));
                                    if (pl != null)
                                    {
                                        member = true;
                                        if(pl.getLocation().distance(p.getLocation()) <= Integer.parseInt(m.getFileManager().getConfigEntrys().get("Chat.clanchat.rangedistance")))
                                        {
                                            m.getMessagesManager().sendMessageClan(pl, format);
                                        }
                                    }
                                }
                                if (member == true)
                                {
                                    Player po = Bukkit.getPlayer(m.getClanManager().getClanOwner(m.getClanManager().getClan(pName)));
                                    if (po != null)
                                    {
                                        if(po.getLocation().distance(p.getLocation()) <= Integer.parseInt(m.getFileManager().getConfigEntrys().get("Chat.clanchat.rangedistance")))
                                        {
                                            m.getMessagesManager().sendMessageClan(po, format);
                                        }
                                    }
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessageClan(p, m.getFileManager().getMessageEntrys().get("Messages.nooneonline"));
                                }
                                e.setCancelled(true);
                            }
                        }
                        else
                        {
                            if(m.getFileManager().getConfigEntrys().get("Chat.withclan.range").equalsIgnoreCase("global"))
                            {
                                format = format.replaceAll("<tag>", tag).replaceAll("<player>", "%1$s").replaceAll("<clan>", clan).replaceAll("<message>", "%2$s");
                                setFormat(e,format);
                            }
                            else
                            {
                                format = format.replaceAll("<tag>", tag).replaceAll("<player>", pName).replaceAll("<clan>", clan).replaceAll("<message>", e.getMessage());
                                int distance = Integer.parseInt(m.getFileManager().getConfigEntrys().get("Chat.withclan.rangedistance"));
                                for(Entity entity : p.getNearbyEntities(distance, distance, distance))
                                {
                                    if(entity instanceof Player)
                                    {
                                        if(p.getLocation().distance(entity.getLocation()) <= distance)
                                        {
                                            Player pn = (Player) entity;
                                            m.getMessagesManager().sendMessageNoPrefix(pn, format);
                                        }
                                    }
                                }
                                m.getMessagesManager().sendMessageNoPrefix(p, format);
                                e.setCancelled(true);
                            }
                        }
                    }
                    else
                    {
                        if(e.getFormat().contains("<clan>"))
                        {
                            if(m.getClanManager().HaveClan(pName))
                            {
                                e.setFormat(e.getFormat().replaceAll("<clan>", m.getClanManager().getClan(pName)));
                            }
                            else
                            {
                                e.setFormat(e.getFormat().replaceAll("<clan>", ""));
                            }
                        }
                        if(e.getFormat().contains("<clantag>"))
                        {
                            if(m.getClanManager().HaveClan(pName))
                            {
                                e.setFormat(e.getFormat().replaceAll("<clantag>", m.getClanManager().getClanTag(m.getClanManager().getClan(pName))));
                            }
                            else
                            {
                                e.setFormat(e.getFormat().replaceAll("<clantag>", ""));
                            }
                        }
                    }
                }
                else
                {
                    if (Boolean.parseBoolean(m.getFileManager().getConfigEntrys().get("Chat.noclan.use")))
                    {
                        format = m.getFileManager().getConfigEntrys().get("Chat.noclan.format");
                        hasformat = true;
                    }
                    if(hasformat)
                    {
                        if(checkPex())
                        {
                            PermissionUser user = PermissionsEx.getUser(p);
                            String prefix = user.getPrefix();
                            String suffix = user.getSuffix();
                            format = format.replaceAll("<prefix>", prefix).replaceAll("<suffix>", suffix);
                        }
                        if(checkJobs())
                        {
                            String job = "";
                            List<JobProgression> jobs = Jobs.getPlayerManager().getJobsPlayer(p).getJobProgression();
                            if (!jobs.isEmpty())
                            {
                                boolean first = false;
                                for (JobProgression OneJob : jobs)
                                {
                                    if (first == false)
                                    {
                                        job = OneJob.getJob().getName();
                                        first = true;
                                    }
                                }
                            }
                            format = format.replaceAll("<job>", job);
                        }
                        if(m.getFileManager().getConfigEntrys().get("Chat.noclan.range").equalsIgnoreCase("global"))
                        {
                            format = format.replaceAll("<player>", "%1$s").replaceAll("<message>", "%2$s");
                            setFormat(e,format);  
                        }
                        else
                        {
                            format = format.replaceAll("<player>", pName).replaceAll("<message>", e.getMessage());
                            int distance = Integer.parseInt(m.getFileManager().getConfigEntrys().get("Chat.noclan.rangedistance"));
                            for(Entity entity : p.getNearbyEntities(distance, distance, distance))
                            {
                                if(entity instanceof Player)
                                {
                                    if(p.getLocation().distance(entity.getLocation()) <= distance)
                                    {
                                        Player pn = (Player) entity;
                                        m.getMessagesManager().sendMessageNoPrefix(pn, format);
                                    }
                                }
                            }
                            m.getMessagesManager().sendMessageNoPrefix(p, format);
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private void setFormat(AsyncPlayerChatEvent e,String format)
    {
        e.setFormat(ChatColor.translateAlternateColorCodes('&', format));
    }
    
    private boolean checkPex()
    {
        if(Bukkit.getPluginManager().getPlugin("PermissionsEx") != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean checkJobs()
    {
        if(Bukkit.getPluginManager().getPlugin("Jobs") != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
