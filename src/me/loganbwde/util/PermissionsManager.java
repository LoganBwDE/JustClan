package me.loganbwde.util;

import org.bukkit.entity.Player;

import me.loganbwde.Clan.main;

public class PermissionsManager
{
    @SuppressWarnings("unused")
    private main m;

    public PermissionsManager(main main)
    {
        m = main;
    }

    public boolean checkPermission(Player p, String cmd)
    {
        if (p.hasPermission("clan." + cmd) || p.hasPermission("clan.base"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkAdminPermission(Player p, String cmd)
    {
        if (p.hasPermission("clan.admin" + cmd) || p.hasPermission("clan.admin"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
