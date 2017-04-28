package me.loganbwde.util;

import java.util.List;

import me.loganbwde.Clan.main;

public class Api
{
    private static main m;
    public Api(main main)
    {
        m = main;
    }
    
    public static void createClan(String clanName,String clanOwner,String clanTag)
    {
        m.getClanManager().ClanCreate(clanName, clanOwner, clanTag);
    }
    
    public static String getClanName(String playerName)
    {
        return m.getClanManager().getClan(playerName);
    }
    
    public static String getClanTag(String playerName)
    {
        return m.getClanManager().getClanTag(playerName);
    }
    
    public static String getClanOwner(String clanName)
    {
        return m.getClanManager().getClanOwner(clanName);
    }
    
    public static List<String> getClanMembers(String clanName)
    {
        return m.getClanManager().getMember(clanName);
    }
    
    public static boolean checkHaveClan(String playerName)
    {
        return m.getClanManager().HaveClan(playerName);
    }
}
