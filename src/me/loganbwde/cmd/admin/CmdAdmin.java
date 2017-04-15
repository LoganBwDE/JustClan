package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdmin
{
    private main m;
    public CmdAdmin(main main)
    {
        m = main;
    }
    
    public void menu(Player p)
    {
        m.getMessagesManager().sendMessageNoPrefix(p, "&4[======================&aAdmin&4=====================]");
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin : " + m.getFileManager().getMessageEntrys().get("MenuAdmin.clanadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin info <Clan> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.claninfoadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin delete <Clan> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.clandeleteadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin kick <Clan> <Player> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.clankickadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin rankup <Clan> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.clanrankupadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin createwars <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.createwars"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setwarslocation1 <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setwarsloc1"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setwarslocation2 <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setwarsloc2"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setwarsspawn1 <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setwarsspawn1"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setwarsspawn2 <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setwarsspawn2"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setendspawn <WarName> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setendspawn"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin setpoints <Clan/Player> <Points> : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.setpoints"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&c/clan admin list : " +  m.getFileManager().getMessageEntrys().get("MenuAdmin.clanlistadmin"));
        m.getMessagesManager().sendMessageNoPrefix(p, "&4[===================================================]");
    }
}
