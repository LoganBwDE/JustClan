package me.loganbwde.util;

import java.util.List;

import me.loganbwde.Clan.main;

public class Api
{
    private main m;
    public Api(main main)
    {
        m = main;
    }
    
    public void createClan(String clanName,String clanOwner,String clanTag)
    {
        m.getClanManager().ClanCreate(clanName, clanOwner, clanTag);
    }
    
    public String getClanName(String playerName)
    {
        return m.getClanManager().getClan(playerName);
    }
    
    public String getClanTag(String playerName)
    {
        return m.getClanManager().getClanTag(playerName);
    }
    
    public String getClanOwner(String clanName)
    {
        return m.getClanManager().getClanOwner(clanName);
    }
    
    public List<String> getClanMembers(String clanName)
    {
        return m.getClanManager().getMember(clanName);
    }
    
    public boolean checkHaveClan(String playerName)
    {
        return m.getClanManager().HaveClan(playerName);
    }
}
