package me.loganbwde.util;

import org.bukkit.entity.Player;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.loganbwde.Clan.main;

public class OnPlaceHolder
{  
    public static void register(final main m)
    {
        PlaceholderAPI.registerPlaceholder(m, "justclan_name", new PlaceholderReplacer() 
        {
            @Override
            public String onPlaceholderReplace(PlaceholderReplaceEvent e) 
            {
                Player p = e.getPlayer();
                if(m.getClanManager().HaveClan(p.getName()))
                {
                    return m.getClanManager().getClan(p.getName());
                }
                else
                {
                    return "None";
                }
            }
        });
        
        PlaceholderAPI.registerPlaceholder(m, "justclan_tag", new PlaceholderReplacer() 
        {
            @Override
            public String onPlaceholderReplace(PlaceholderReplaceEvent e) 
            {
                Player p = e.getPlayer();
                if(m.getClanManager().HaveClan(p.getName()))
                {
                    return m.getClanManager().getClanTag(p.getName());
                }
                else
                {
                    return "None";
                }
            }
        });
    }

}
