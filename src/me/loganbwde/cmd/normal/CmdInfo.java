package me.loganbwde.cmd.normal;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class CmdInfo
{
    private main m;
    public CmdInfo(main main)
    {
        m = main;
    }
    
    public void menu(Player p)
    {
        String pName = p.getName();
        for(int i = 0;i <= 20;i++)
        {
            if(m.getFileManager().getMessageEntrys().get("Menu.info." + i) != null)
            {
                m.getMessagesManager().sendMessageNoPrefix(p, m.getFileManager().getMessageEntrys().get("Menu.info." + i)
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
}
