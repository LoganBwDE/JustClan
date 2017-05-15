package me.loganbwde.cmd.admin;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdAdminInfo
{
    private main m;
    public CmdAdminInfo(main main)
    {
        m = main;
    }
    
    public void menu(Player p,String cl)
    {
        if (m.getClanManager().ClanExists(cl))
        {
            String pName = m.getClanManager().getClanOwner(cl);

            for(int i = 0;i <= 20;i++)
            {
                if(m.getFileManager().getMessageEntrys().get("Menu.admininfo." + i) != null)
                {
                    m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.admininfo." + i)
                    .replaceAll("%clan%", m.getClanManager().getClan(pName))
                    .replaceAll("%tag%", m.getClanManager().getClanTag(pName))
                    .replaceAll("%owner%", m.getClanManager().getClanOwner(m.getClanManager().getClan(pName)))
                    .replaceAll("%members%", m.getClanManager().getMember(m.getClanManager().getClan(pName)).toString())
                    .replaceAll("%size%", String.valueOf(m.getClanManager().getClanSize(pName)))
                    .replaceAll("%kills%", String.valueOf(m.getClanManager().getClanKill(pName)))
                    .replaceAll("%deaths%", String.valueOf(m.getClanManager().getClanDeath(pName)))
                    .replaceAll("%kd%", String.valueOf(m.getClanManager().getClanKD(pName)))
                    .replaceAll("%points%", String.valueOf(m.getClanManager().getClanLevel(pName)))
                    .replaceAll("%nextpoints%", String.valueOf(m.getClanManager().getNeedLevel(pName)))
                    .replaceAll("%rank%", String.valueOf(m.getClanManager().getClanRank(pName))));
                }
            }
        }
        else
        {
            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notexist"));
        }
    }
}
