package me.loganbwde.cmd.normal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdInvite
{
    private main m;
    public CmdInvite(main main)
    {
        m = main;
    }
    
    public void invite(Player p, String arg)
    {
        String name = p.getName();
        int clansize = m.getClanManager().getClanSize(name);
        if (clansize > m.getClanManager().getMember(m.getClanManager().getClan(name)).size())
        {
            if (!arg.equals(name))
            {                         
                Player p2 = Bukkit.getServer().getPlayer(arg);
                boolean on = false;
                if (p2 != null)
                {
                    on = true;
                }
                if (on == true)
                {
                    if (m.getClanManager().getMember(m.getClanManager().getClan(name)).contains(p2.getName()))
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan2").replace("%player%", arg));
                    }
                    else
                    {
                        m.getClanManager().invClan(arg, name);
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.claninv1").replace("%player%", p2.getName()));
                        m.getMessagesManager().sendMessage(p2, m.getFileManager().getMessageEntrys().get("Messages.claninv2").replace("%clan%", m.getClanManager().getClan(name)));
                    }
                }
                else
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messsages.notonline").replace("%player%", arg));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan"));
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clanfull"));
        }
    }
    
    public void invite(Player p, String[] args)
    {
        String name = p.getName();
        try
        {
            int clansize = m.getClanManager().getClanSize(name);
            if (clansize > m.getClanManager().getMember(m.getClanManager().getClan(name)).size())
            {
                if (!args[1].equals(name) && !args[2].equals(name))
                {
                    if (args[1].equalsIgnoreCase("revoke"))
                    {
                        m.getClanManager().ClanRevoke(args[2], name);
                    }
                    else
                    {                            
                        Player p2 = Bukkit.getServer().getPlayer(args[1]);
                        boolean on = false;
                        if (p2 != null)
                        {
                            on = true;
                        }
                        if (on == true)
                        {
                            if (m.getClanManager().getMember(m.getClanManager().getClan(name)).contains(p2.getName()))
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan2").replace("%player%", args[1]));
                            }
                            else
                            {
                                m.getClanManager().invClan(args[1], name);
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.claninv1").replace("%player%", p2.getName()));
                                m.getMessagesManager().sendMessage(p2, m.getFileManager().getMessageEntrys().get("Messages.claninv2").replace("%clan%", m.getClanManager().getClan(name)));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notonline").replace("%player%", args[1]));
                        }
                    }
                }
                else
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan"));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clanfull"));
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            int clansize = m.getClanManager().getClanSize(name);
            if (clansize > m.getClanManager().getMember(m.getClanManager().getClan(name)).size())
            {
                if (!args[1].equals(name))
                {
                    if (args[1].equalsIgnoreCase("revoke"))
                    {
                        m.getClanManager().ClanRevoke(args[2], name);
                    }
                    else
                    {                            
                        Player p2 = Bukkit.getServer().getPlayer(args[1]);
                        boolean on = false;
                        if (p2 != null)
                        {
                            on = true;
                        }
                        if (on == true)
                        {
                            if (m.getClanManager().getMember(m.getClanManager().getClan(name)).contains(p2.getName()))
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan2").replace("%player%", args[1]));
                            }
                            else
                            {
                                m.getClanManager().invClan(args[1], name);
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.claninv1").replace("%player%", p2.getName()));
                                m.getMessagesManager().sendMessage(p2, m.getFileManager().getMessageEntrys().get("Messages.claninv2").replace("%clan%", m.getClanManager().getClan(name)));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notonline").replace("%player%", args[1]));
                        }
                    }
                }
                else
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.alreadyinclan"));
                }
            }
            else
            {
                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.clanfull"));
            }
        }
    }
}
