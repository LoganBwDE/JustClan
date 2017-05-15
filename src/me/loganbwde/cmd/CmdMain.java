package me.loganbwde.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;
import me.loganbwde.cmd.admin.CmdAdmin;
import me.loganbwde.cmd.admin.CmdAdminCreateWars;
import me.loganbwde.cmd.admin.CmdAdminDelete;
import me.loganbwde.cmd.admin.CmdAdminInfo;
import me.loganbwde.cmd.admin.CmdAdminKick;
import me.loganbwde.cmd.admin.CmdAdminList;
import me.loganbwde.cmd.admin.CmdAdminRankup;
import me.loganbwde.cmd.admin.CmdAdminSetEndSpawn;
import me.loganbwde.cmd.admin.CmdAdminSetPoints;
import me.loganbwde.cmd.admin.CmdAdminSetSpawn1;
import me.loganbwde.cmd.admin.CmdAdminSetSpawn2;
import me.loganbwde.cmd.admin.CmdAdminSetloc1;
import me.loganbwde.cmd.admin.CmdAdminSetloc2;
import me.loganbwde.cmd.admin.CmdReload;
import me.loganbwde.cmd.normal.CmdAccept;
import me.loganbwde.cmd.normal.CmdApplyRank;
import me.loganbwde.cmd.normal.CmdChat;
import me.loganbwde.cmd.normal.CmdClan;
import me.loganbwde.cmd.normal.CmdCreate;
import me.loganbwde.cmd.normal.CmdDelete;
import me.loganbwde.cmd.normal.CmdDeny;
import me.loganbwde.cmd.normal.CmdHome;
import me.loganbwde.cmd.normal.CmdInfo;
import me.loganbwde.cmd.normal.CmdInvite;
import me.loganbwde.cmd.normal.CmdKick;
import me.loganbwde.cmd.normal.CmdLeave;
import me.loganbwde.cmd.normal.CmdNext;
import me.loganbwde.cmd.normal.CmdPay;
import me.loganbwde.cmd.normal.CmdPoints;
import me.loganbwde.cmd.normal.CmdRank;
import me.loganbwde.cmd.normal.CmdRankup;
import me.loganbwde.cmd.normal.CmdSethome;
import me.loganbwde.cmd.normal.CmdStartWars;
import me.loganbwde.cmd.normal.CmdTop;
import me.loganbwde.cmd.normal.CmdWars;

public class CmdMain implements CommandExecutor
{
    private CmdClan cmd_clan;
    private CmdNext cmd_next;
    private CmdInfo cmd_info;
    private CmdCreate cmd_create;
    private CmdLeave cmd_leave;
    private CmdInvite cmd_invite;
    private CmdDelete cmd_delete;
    private CmdAccept cmd_accept;
    private CmdDeny cmd_deny;
    private CmdKick cmd_kick;
    private CmdPay cmd_pay;
    private CmdTop cmd_top;
    private CmdHome cmd_home;
    private CmdSethome cmd_sethome;
    private CmdRank cmd_rank;
    private CmdApplyRank cmd_applyrank;
    private CmdStartWars cmd_startwars;
    private CmdWars cmd_wars;
    private CmdChat cmd_chat;
    private CmdPoints cmd_points;
    private CmdRankup cmd_rankup;
    
    private CmdAdmin cmd_admin;
    private CmdReload cmd_reload;
    private CmdAdminInfo cmd_admin_info;
    private CmdAdminList cmd_admin_list;
    private CmdAdminDelete cmd_admin_delete;
    private CmdAdminKick cmd_admin_kick;
    private CmdAdminRankup cmd_admin_rankup;
    private CmdAdminSetloc1 cmd_admin_setloc1;
    private CmdAdminSetloc2 cmd_admin_setloc2;
    private CmdAdminSetSpawn1 cmd_admin_setspawn1;
    private CmdAdminSetSpawn2 cmd_admin_setspawn2;
    private CmdAdminSetEndSpawn cmd_admin_setendspawn;
    private CmdAdminCreateWars cmd_admin_createwars;
    private CmdAdminSetPoints cmd_admin_setpoints;
    
    private main m;

    public CmdMain(main main)
    {
        m = main;
        registerCmds();
    }

    private void registerCmds()
    {
        cmd_clan = new CmdClan(m);
        cmd_next = new CmdNext(m);
        cmd_info = new CmdInfo(m);
        cmd_create = new CmdCreate(m);
        cmd_leave = new CmdLeave(m);
        cmd_invite = new CmdInvite(m);
        cmd_delete = new CmdDelete(m);
        cmd_accept = new CmdAccept(m);
        cmd_deny = new CmdDeny(m);
        cmd_kick = new CmdKick(m);
        cmd_pay = new CmdPay(m);
        cmd_top = new CmdTop(m);
        cmd_home = new CmdHome(m);
        cmd_sethome = new CmdSethome(m);
        cmd_rank = new CmdRank(m);
        cmd_applyrank = new CmdApplyRank(m);
        cmd_startwars = new CmdStartWars(m);
        cmd_wars = new CmdWars(m);
        cmd_chat = new CmdChat(m);
        cmd_rankup = new CmdRankup(m);
        cmd_points = new CmdPoints(m);
        
        cmd_admin = new CmdAdmin(m);
        cmd_reload = new CmdReload(m);
        cmd_admin_info = new CmdAdminInfo(m);
        cmd_admin_list = new CmdAdminList(m);
        cmd_admin_delete = new CmdAdminDelete(m);
        cmd_admin_kick = new CmdAdminKick(m);
        cmd_admin_rankup = new CmdAdminRankup(m);
        cmd_admin_setloc1 = new CmdAdminSetloc1(m);
        cmd_admin_setloc2 = new CmdAdminSetloc2(m);
        cmd_admin_setspawn1 = new CmdAdminSetSpawn1(m);
        cmd_admin_setspawn2 = new CmdAdminSetSpawn2(m);
        cmd_admin_setendspawn = new CmdAdminSetEndSpawn(m);
        cmd_admin_createwars = new CmdAdminCreateWars(m);
        cmd_admin_setpoints = new CmdAdminSetPoints(m);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args)
    {
        if (sender instanceof Player)
        {
            Player p = (Player) sender;
            String name = p.getName();
            
            if(cmd.getName().equalsIgnoreCase("clreload"))
            {
                if(m.getPermissionsManager().checkAdminPermission(p, "reload"))
                {
                    cmd_reload.reload(p);
                }
                else
                {
                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                }
            }
            if(cmd.getName().equalsIgnoreCase("clan"))
            {
                if (args.length == 0)
                {
                    if(m.getPermissionsManager().checkPermission(p, "Menu"))
                    {
                        cmd_clan.menu(p);
                    }
                    else
                    {
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                    }
                }
                if (args.length == 1)
                {
                    
                    if(args[0].equalsIgnoreCase("admin"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, "admin"))
                        {
                            cmd_admin.menu(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("next"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, "Menu"))
                        {
                            cmd_next.menu(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    if(args[0].equalsIgnoreCase("info"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_info.menu(p);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("leave"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == false)
                                {
                                    cmd_leave.leave(p);      
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, (m.getFileManager().getMessageEntrys().get("Messages.leavenotdelete")).replace("%player%", name));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("delete"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                    cmd_delete.delete(p);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("top"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            cmd_top.top(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("home"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_home.home(p);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("sethome"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                    cmd_sethome.sethome(p);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("rank"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == false)
                                {
                                    cmd_rank.rank(p);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.rankowner"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("points"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            cmd_points.points(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("shop"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            m.getShopManager().openShop(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("rankup"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                    cmd_rankup.rankup(p);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("chat"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {        
                                cmd_chat.chat(p);
                            }   
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                }
                if (args.length == 2)
                {
                    if(args[0].equalsIgnoreCase("invite"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_invite.invite(p,args[1]);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("accept"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_accept.accept(p,args[1]);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("deny"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_deny.deny(p,args);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("kick"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_kick.kick(p,args);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("pay"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_pay.pay(p, args);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("list"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[0]))
                        {
                            cmd_admin_list.list(p);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    if(args[0].equalsIgnoreCase("create"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getFileManager().getConfigEntrys().get("Basic.useTag") == "false")
                            {
                                if(args[1].length() <= Integer.valueOf(m.getFileManager().getConfigEntrys().get("Basic.nameSize")))
                                {
                                    cmd_create.create(p,args[1]);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.nametoolong"));
                                }
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                }
                if (args.length == 3)
                {
                    if(args[0].equalsIgnoreCase("create"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getFileManager().getConfigEntrys().get("Basic.useTag") == "true")
                            {
                                if(args[1].length() <= Integer.valueOf(m.getFileManager().getConfigEntrys().get("Basic.nameSize")))
                                {
                                    if(args[2].length() <= Integer.valueOf(m.getFileManager().getConfigEntrys().get("Basic.tagSize")))
                                    {
                                        cmd_create.create(p,args[1],args[2]);
                                    }
                                    else
                                    {
                                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.tagtoolong"));
                                    }
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.nametoolong"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.tagsnotenabled"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                   
                    if(args[0].equalsIgnoreCase("invite"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                cmd_invite.invite(p, args);
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("applyrank"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                   cmd_applyrank.applyRankOwner(p, args);
                                }
                                else
                                {
                                    cmd_applyrank.applyRankNormal(p);
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                                       
                    if(args[0].equalsIgnoreCase("startwars"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                    cmd_startwars.startWars(p,args);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("wars"))
                    {
                        if(m.getPermissionsManager().checkPermission(p, args[0]))
                        {
                            if(m.getClanManager().HaveClan(name) == true)
                            {
                                if(m.getClanManager().isOwner(name) == true)
                                {
                                    cmd_wars.wars(p,args);
                                }
                                else
                                {
                                    m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notyourclan"));
                                }
                            }
                            else
                            {
                                m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noclan"));
                            }
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("info"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_info.menu(p, args[2]);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("delete"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_delete.delete(p,args[2]);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("kick"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_kick.kick(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("rankup"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_rankup.rankup(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setwarslocation1"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setloc1.setloc(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setwarslocation2"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setloc2.setloc(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setwarsspawn1"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setspawn1.setspawn(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setwarsspawn2"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setspawn2.setspawn(p,args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setendspawn"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setendspawn.setspawn(p, args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }  
                    
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("createwars"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_createwars.create(p, args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }  
                }
                if (args.length == 4)
                {
                    if(args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("setpoints"))
                    {
                        if(m.getPermissionsManager().checkAdminPermission(p, args[1]))
                        {
                            cmd_admin_setpoints.setpoints(p, args);
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.noperm"));
                        }
                    }  
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(sender, m.getFileManager().getMessageEntrys().get("Messages.console"));
        }
        return true;
    }
}
