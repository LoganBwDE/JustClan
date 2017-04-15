package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdChat
{
    private main m;
    public CmdChat(main main)
    {
        m = main;
    }
    
    public void chat(Player p)
    {
        String name = p.getName();
        if (m.chat.contains(name))
        {
            m.chat.remove(name);
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.nomorechat"));
        }
        else
        {
            m.chat.add(name);
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.nowchat"));
        }
    }
}
