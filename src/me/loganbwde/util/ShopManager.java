package me.loganbwde.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.loganbwde.Clan.main;

public class ShopManager
{
    private main m; 
    private HashMap<String,String> back = new HashMap<String, String>();
    public ShopManager(main main)
    {
        m = main;
    }
    public void openShop(Player p)
    {
        String shopname = m.getFileManager().getConfigEntrys().get("Basic.shopname");
        Inventory inv;
        if (p.hasPermission("Clan.Admin"))
        {
            inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', shopname));

            inv.setItem(16, getItemsSpecial().get(30));
            inv.setItem(14, getItemsSword().get(30));
            inv.setItem(12, getItemsBow().get(30));
            inv.setItem(10, getItemsArmor().get(30));
        }
        else
        {
            inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', shopname));
        }

        inv.setItem(1, getItemsArmor().get(29));
        inv.setItem(3, getItemsBow().get(29));
        inv.setItem(5, getItemsSword().get(29));
        inv.setItem(7, getItemsSpecial().get(29));

        p.openInventory(inv);
    }

    public void openShopArmor(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Armor.main.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsArmor().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openShopBow(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Bow.main.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsBow().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openShopSword(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Sword.main.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsSword().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openShopSpecial(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Special.main.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsSpecial().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openAdminArmor(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Armor.admin.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsArmor().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openAdminBow(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Bow.admin.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsBow().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openAdminSword(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Sword.admin.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsSword().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    public void openAdminSpecial(Player p)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Special.admin.Name")));
        try
        {
            for (int i = 0; i <= 17; i++)
            {
                inv.setItem(i, getItemsSpecial().get(i));
            }
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        p.openInventory(inv);
    }

    @SuppressWarnings("deprecation")
    public HashMap<Integer, ItemStack> getItemsArmor()
    {
        HashMap<Integer, ItemStack> list = new HashMap<Integer, ItemStack>();
        for (int i = -2; i <= 8; i++)
        {
            if (i == -1)
            {
                ItemStack item = null;
                if(m.getFileManager().getShop().getString("Armor.main.Extra") != null)
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor.main.Material")), m.getFileManager().getShop().getInt("Armor.main.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Armor.main.Extra"));
                }
                else
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor.main.Material")), m.getFileManager().getShop().getInt("Armor.main.Amount"));
                }
                ItemMeta item_meta = null;
                item_meta = item.getItemMeta();
                if (m.getFileManager().getShop().getString("Armor.main.Name") != null)
                {
                    item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Armor.main.Name")));
                }
                if (m.getFileManager().getShop().getString("Armor.main.Enchantment") != null)
                {
                    String[] arg = m.getFileManager().getShop().getString("Armor.main.Enchantment").split(",");
                    String[] level = m.getFileManager().getShop().getString("Armor.main.EnchantmentLevel").split(",");
                    for (int j = 0; j < 24; j++)
                    {
                        try
                        {
                            if (!arg[j].isEmpty() && !level[j].isEmpty())
                            {
                                Enchantment ench = Enchantment.getByName(arg[j]);
                                int l = Integer.parseInt(level[j]);
                                item_meta.addEnchant(ench, l, true);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex)
                        {
                        }
                    }
                }
                item.setItemMeta(item_meta);
                list.put(29, item);
            }
            else
            {
                if (i == -2)
                {
                    ItemStack item = null;
                    if(m.getFileManager().getShop().getString("Armor.admin.Extra") != null)
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor.admin.Material")), m.getFileManager().getShop().getInt("Armor.admin.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Armor.admin.Extra"));
                    }
                    else
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor.admin.Material")), m.getFileManager().getShop().getInt("Armor.admin.Amount"));
                    }
                    ItemMeta item_meta = null;
                    item_meta = item.getItemMeta();
                    if (m.getFileManager().getShop().getString("Armor.admin.Name") != null)
                    {
                        item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Armor.admin.Name")));
                    }
                    if (m.getFileManager().getShop().getString("Armor.admin.Enchantment") != null)
                    {
                        String[] arg = m.getFileManager().getShop().getString("Armor.admin.Enchantment").split(",");
                        String[] level = m.getFileManager().getShop().getString("Armor.admin.EnchantmentLevel").split(",");
                        for (int j = 0; j < 24; j++)
                        {
                            try
                            {
                                if (!arg[j].isEmpty() && !level[j].isEmpty())
                                {
                                    Enchantment ench = Enchantment.getByName(arg[j]);
                                    int l = Integer.parseInt(level[j]);
                                    item_meta.addEnchant(ench, l, true);
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException ex)
                            {
                            }
                        }
                    }
                    item.setItemMeta(item_meta);
                    list.put(30, item);
                }
                else
                {
                    if (m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Material") != null)
                    {
                        ItemStack item = null;
                        if(m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Extra") != null)
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Armor." + "slot" + i + "." + ".Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Armor." + "slot" + i + "." + ".Extra"));
                        }
                        else
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Armor." + "slot" + i + "." + ".Amount"));
                        }
                        ItemMeta item_meta = null;
                        item_meta = item.getItemMeta();
                        if (m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Name") != null)
                        {
                            item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Name")));
                        }
                        if (m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Enchantment") != null)
                        {
                            String[] arg = m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".Enchantment").split(",");
                            String[] level = m.getFileManager().getShop().getString("Armor." + "slot" + i + "." + ".EnchantmentLevel").split(",");
                            for (int j = 0; j < 24; j++)
                            {
                                try
                                {
                                    if (!arg[j].isEmpty() && !level[j].isEmpty())
                                    {
                                        Enchantment ench = Enchantment.getByName(arg[j]);
                                        int l = Integer.parseInt(level[j]);
                                        item_meta.addEnchant(ench, l, true);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException ex)
                                {
                                }
                            }
                        }
                        ArrayList<String> item_lore = new ArrayList<String>();
                        item_lore.add(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.cost") + ": " + m.getFileManager().getShop().getInt("Armor." + "slot" + i + "." + ".Cost")));
                        item_meta.setLore(item_lore);
                        item.setItemMeta(item_meta);
                        list.put(i, item);
                    }
                }
            }
        }
        for (int i = 9; i <= 17; i++)
        {
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14);
            ItemMeta wool_meta = null;
            wool_meta = wool.getItemMeta();
            wool_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
            wool.setItemMeta(wool_meta);
            list.put(i, wool);
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    public HashMap<Integer, ItemStack> getItemsBow()
    {
        HashMap<Integer, ItemStack> list = new HashMap<Integer, ItemStack>();
        for (int i = -2; i <= 8; i++)
        {
            if (i == -1)
            {
                ItemStack item = null;
                if(m.getFileManager().getShop().getString("Bow.main.Extra") != null)
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow.main.Material")), m.getFileManager().getShop().getInt("Bow.main.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Bow.main.Extra"));
                }
                else
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow.main.Material")), m.getFileManager().getShop().getInt("Bow.main.Amount"));
                }
                ItemMeta item_meta = null;
                item_meta = item.getItemMeta();
                if (m.getFileManager().getShop().getString("Bow.main.Name") != null)
                {
                    item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Bow.main.Name")));
                }
                if (m.getFileManager().getShop().getString("Bow.main.Enchantment") != null)
                {
                    String[] arg = m.getFileManager().getShop().getString("Bow.main.Enchantment").split(",");
                    String[] level = m.getFileManager().getShop().getString("Bow.main.EnchantmentLevel").split(",");
                    for (int j = 0; j < 24; j++)
                    {
                        try
                        {
                            if (!arg[j].isEmpty() && !level[j].isEmpty())
                            {
                                Enchantment ench = Enchantment.getByName(arg[j]);
                                int l = Integer.parseInt(level[j]);
                                item_meta.addEnchant(ench, l, true);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex)
                        {
                        }
                    }
                }
                item.setItemMeta(item_meta);
                list.put(29, item);
            }
            else
            {
                if (i == -2)
                {
                    ItemStack item = null;
                    if(m.getFileManager().getShop().getString("Bow.admin.Extra") != null)
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow.admin.Material")), m.getFileManager().getShop().getInt("Bow.admin.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Bow.admin.Extra"));
                    }
                    else
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow.admin.Material")), m.getFileManager().getShop().getInt("Bow.admin.Amount"));
                    }ItemMeta item_meta = null;
                    item_meta = item.getItemMeta();
                    if (m.getFileManager().getShop().getString("Bow.admin.Name") != null)
                    {
                        item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Bow.admin.Name")));
                    }
                    if (m.getFileManager().getShop().getString("Bow.admin.Enchantment") != null)
                    {
                        String[] arg = m.getFileManager().getShop().getString("Bow.admin.Enchantment").split(",");
                        String[] level = m.getFileManager().getShop().getString("Bow.admin.EnchantmentLevel").split(",");
                        for (int j = 0; j < 24; j++)
                        {
                            try
                            {
                                if (!arg[j].isEmpty() && !level[j].isEmpty())
                                {
                                    Enchantment ench = Enchantment.getByName(arg[j]);
                                    int l = Integer.parseInt(level[j]);
                                    item_meta.addEnchant(ench, l, true);
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException ex)
                            {
                            }
                        }
                    }
                    item.setItemMeta(item_meta);
                    list.put(30, item);
                }
                else
                {
                    if (m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Material") != null)
                    {
                        ItemStack item = null;
                        if(m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Extra") != null)
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Bow." + "slot" + i + "." + ".Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Bow." + "slot" + i + "." + ".Extra"));
                        }
                        else
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Bow." + "slot" + i + "." + ".Amount"));
                        }
                        ItemMeta item_meta = null;
                        item_meta = item.getItemMeta();
                        if (m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Name") != null)
                        {
                            item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Name")));
                        }
                        if (m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Enchantment") != null)
                        {
                            String[] arg = m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".Enchantment").split(",");
                            String[] level = m.getFileManager().getShop().getString("Bow." + "slot" + i + "." + ".EnchantmentLevel").split(",");
                            for (int j = 0; j < 24; j++)
                            {
                                try
                                {
                                    if (!arg[j].isEmpty() && !level[j].isEmpty())
                                    {
                                        Enchantment ench = Enchantment.getByName(arg[j]);
                                        int l = Integer.parseInt(level[j]);
                                        item_meta.addEnchant(ench, l, true);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException ex)
                                {
                                }
                            }
                        }
                        ArrayList<String> item_lore = new ArrayList<String>();
                        item_lore.add(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.cost") + ": " + m.getFileManager().getShop().getInt("Bow." + "slot" + i + "." + ".Cost")));
                        item_meta.setLore(item_lore);
                        item.setItemMeta(item_meta);
                        list.put(i, item);
                    }
                }
            }
        }
        for (int i = 9; i <= 17; i++)
        {
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14);
            ItemMeta wool_meta = null;
            wool_meta = wool.getItemMeta();
            wool_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
            wool.setItemMeta(wool_meta);
            list.put(i, wool);
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    public HashMap<Integer, ItemStack> getItemsSword()
    {
        HashMap<Integer, ItemStack> list = new HashMap<Integer, ItemStack>();
        for (int i = -2; i <= 8; i++)
        {
            if (i == -1)
            {
                ItemStack item = null;
                if(m.getFileManager().getShop().getString("Sword.main.Extra") != null)
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword.main.Material")), m.getFileManager().getShop().getInt("Sword.main.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Sword.main.Extra"));
                }
                else
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword.main.Material")), m.getFileManager().getShop().getInt("Sword.main.Amount"));
                }
                ItemMeta item_meta = null;
                item_meta = item.getItemMeta();
                if (m.getFileManager().getShop().getString("Sword.main.Name") != null)
                {
                    item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Sword.main.Name")));
                }
                if (m.getFileManager().getShop().getString("Sword.main.Enchantment") != null)
                {
                    String[] arg = m.getFileManager().getShop().getString("Sword.main.Enchantment").split(",");
                    String[] level = m.getFileManager().getShop().getString("Sword.main.EnchantmentLevel").split(",");
                    for (int j = 0; j < 24; j++)
                    {
                        try
                        {
                            if (!arg[j].isEmpty() && !level[j].isEmpty())
                            {
                                Enchantment ench = Enchantment.getByName(arg[j]);
                                int l = Integer.parseInt(level[j]);
                                item_meta.addEnchant(ench, l, true);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex)
                        {
                        }
                    }
                }
                item.setItemMeta(item_meta);
                list.put(29, item);
            }
            else
            {
                if (i == -2)
                {
                    ItemStack item = null;
                    if(m.getFileManager().getShop().getString("Sword.admin.Extra") != null)
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword.admin.Material")), m.getFileManager().getShop().getInt("Sword.admin.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Sword.admin.Extra"));
                    }
                    else
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword.admin.Material")), m.getFileManager().getShop().getInt("Sword.admin.Amount"));
                    }
                    ItemMeta item_meta = null;
                    item_meta = item.getItemMeta();
                    if (m.getFileManager().getShop().getString("Sword.admin.Name") != null)
                    {
                        item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Sword.admin.Name")));
                    }
                    if (m.getFileManager().getShop().getString("Sword.admin.Enchantment") != null)
                    {
                        String[] arg = m.getFileManager().getShop().getString("Sword.admin.Enchantment").split(",");
                        String[] level = m.getFileManager().getShop().getString("Sword.admin.EnchantmentLevel").split(",");
                        for (int j = 0; j < 24; j++)
                        {
                            try
                            {
                                if (!arg[j].isEmpty() && !level[j].isEmpty())
                                {
                                    Enchantment ench = Enchantment.getByName(arg[j]);
                                    int l = Integer.parseInt(level[j]);
                                    item_meta.addEnchant(ench, l, true);
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException ex)
                            {
                            }
                        }
                    }
                    item.setItemMeta(item_meta);
                    list.put(30, item);
                }
                else
                {
                    if (m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Material") != null)
                    {
                        ItemStack item = null;
                        if(m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Extra") != null)
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Sword." + "slot" + i + "." + ".Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Sword." + "slot" + i + "." + ".Extra"));
                        }
                        else
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Sword." + "slot" + i + "." + ".Amount"));
                        }
                        ItemMeta item_meta = null;
                        item_meta = item.getItemMeta();
                        if (m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Name") != null)
                        {
                            item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Name")));
                        }
                        if (m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Enchantment") != null)
                        {
                            String[] arg = m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".Enchantment").split(",");
                            String[] level = m.getFileManager().getShop().getString("Sword." + "slot" + i + "." + ".EnchantmentLevel").split(",");
                            for (int j = 0; j < 24; j++)
                            {
                                try
                                {
                                    if (!arg[j].isEmpty() && !level[j].isEmpty())
                                    {
                                        Enchantment ench = Enchantment.getByName(arg[j]);
                                        int l = Integer.parseInt(level[j]);
                                        item_meta.addEnchant(ench, l, true);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException ex)
                                {
                                }
                            }
                        }
                        ArrayList<String> item_lore = new ArrayList<String>();
                        item_lore.add(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.cost") + ": " + m.getFileManager().getShop().getInt("Sword." + "slot" + i + "." + ".Cost")));
                        item_meta.setLore(item_lore);
                        item.setItemMeta(item_meta);
                        list.put(i, item);
                    }
                }
            }
        }
        for (int i = 9; i <= 17; i++)
        {
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14);
            ItemMeta wool_meta = null;
            wool_meta = wool.getItemMeta();
            wool_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
            wool.setItemMeta(wool_meta);
            list.put(i, wool);
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    public HashMap<Integer, ItemStack> getItemsSpecial()
    {
        HashMap<Integer, ItemStack> list = new HashMap<Integer, ItemStack>();
        for (int i = -2; i <= 8; i++)
        {
            if (i == -1)
            {
                ItemStack item = null;
                if(m.getFileManager().getShop().getString("Special.main.Extra") != null)
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special.main.Material")), m.getFileManager().getShop().getInt("Special.main.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Special.main.Extra"));
                }
                else
                {
                    item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special.main.Material")), m.getFileManager().getShop().getInt("Special.main.Amount"));
                }
                ItemMeta item_meta = null;
                item_meta = item.getItemMeta();
                if (m.getFileManager().getShop().getString("Special.main.Name") != null)
                {
                    item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Special.main.Name")));
                }
                if (m.getFileManager().getShop().getString("Special.main.Enchantment") != null)
                {
                    String[] arg = m.getFileManager().getShop().getString("Special.main.Enchantment").split(",");
                    String[] level = m.getFileManager().getShop().getString("Special.main.EnchantmentLevel").split(",");
                    for (int j = 0; j < 24; j++)
                    {
                        try
                        {
                            if (!arg[j].isEmpty() && !level[j].isEmpty())
                            {
                                Enchantment ench = Enchantment.getByName(arg[j]);
                                int l = Integer.parseInt(level[j]);
                                item_meta.addEnchant(ench, l, true);
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException ex)
                        {
                        }
                    }
                }
                item.setItemMeta(item_meta);
                list.put(29, item);
            }
            else
            {
                if (i == -2)
                {
                    ItemStack item = null;
                    if(m.getFileManager().getShop().getString("Special.admin.Extra") != null)
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special.admin.Material")), m.getFileManager().getShop().getInt("Special.admin.Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Special.admin.Extra"));
                    }
                    else
                    {
                        item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special.admin.Material")), m.getFileManager().getShop().getInt("Special.admin.Amount"));
                    }
                    ItemMeta item_meta = null;
                    item_meta = item.getItemMeta();
                    if (m.getFileManager().getShop().getString("Special.admin.Name") != null)
                    {
                        item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Special.admin.Name")));
                    }
                    
                    if (m.getFileManager().getShop().getString("Special.admin.Enchantment") != null)
                    {
                        String[] arg = m.getFileManager().getShop().getString("Special.admin.Enchantment").split(",");
                        String[] level = m.getFileManager().getShop().getString("Special.admin.EnchantmentLevel").split(",");
                        for (int j = 0; j < 24; j++)
                        {
                            try
                            {
                                if (!arg[j].isEmpty() && !level[j].isEmpty())
                                {
                                    Enchantment ench = Enchantment.getByName(arg[j]);
                                    int l = Integer.parseInt(level[j]);
                                    item_meta.addEnchant(ench, l, true);
                                }
                            }
                            catch (ArrayIndexOutOfBoundsException ex)
                            {
                            }
                        }
                    }
                    item.setItemMeta(item_meta);
                    list.put(30, item);
                }
                else
                {
                    if (m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Material") != null)
                    {
                        ItemStack item = null;
                        if(m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Extra") != null)
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Special." + "slot" + i + "." + ".Amount"),(short) 0,(byte) m.getFileManager().getShop().getInt("Special." + "slot" + i + "." + ".Extra"));
                        }
                        else
                        {
                            item = new ItemStack(Material.getMaterial(m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Material")), m.getFileManager().getShop().getInt("Special." + "slot" + i + "." + ".Amount"));
                        }
                        ItemMeta item_meta = null;
                        item_meta = item.getItemMeta();
                        if (m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Name") != null)
                        {
                            item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Name")));
                        }
                        if (m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Enchantment") != null)
                        {
                            String[] arg = m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".Enchantment").split(",");
                            String[] level = m.getFileManager().getShop().getString("Special." + "slot" + i + "." + ".EnchantmentLevel").split(",");
                            for (int j = 0; j < 24; j++)
                            {
                                try
                                {
                                    if (!arg[j].isEmpty() && !level[j].isEmpty())
                                    {
                                        Enchantment ench = Enchantment.getByName(arg[j]);
                                        int l = Integer.parseInt(level[j]);
                                        item_meta.addEnchant(ench, l, true);
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException ex)
                                {
                                }
                            }
                        }
                        ArrayList<String> item_lore = new ArrayList<String>();
                        item_lore.add(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.cost") + ": " + m.getFileManager().getShop().getInt("Special." + "slot" + i + "." + ".Cost")));
                        item_meta.setLore(item_lore);
                        item.setItemMeta(item_meta);
                        list.put(i, item);
                    }
                }
            }
        }
        for (int i = 9; i <= 17; i++)
        {
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14);
            ItemMeta wool_meta = null;
            wool_meta = wool.getItemMeta();
            wool_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
            wool.setItemMeta(wool_meta);
            list.put(i, wool);
        }
        return list;
    }

    public boolean checkPoints(Player p, int neededpoints)
    {
        int points = m.getClanManager().getPlayerLevel(p.getName());
        if (points >= neededpoints)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public void openEdit(Player p, String shop, int slot)
    {
        Inventory inv;
        inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.selected").replace("%shop%", shop).replace("%slot%", String.valueOf(slot))));

        ItemStack item0 = new ItemStack(Material.WORKBENCH);
        ItemMeta item0_meta = null;
        item0_meta = item0.getItemMeta();
        item0_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.editmaterial")));
        item0.setItemMeta(item0_meta);

        ItemStack item1 = new ItemStack(Material.EMPTY_MAP);
        ItemMeta item1_meta = null;
        item1_meta = item1.getItemMeta();
        item1_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.editmaterialamount")));
        item1.setItemMeta(item1_meta);

        ItemStack item2 = new ItemStack(Material.NAME_TAG);
        ItemMeta item2_meta = null;
        item2_meta = item2.getItemMeta();
        item2_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.editname")));
        item2.setItemMeta(item2_meta);

        ItemStack item3 = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta item3_meta = null;
        item3_meta = item3.getItemMeta();
        item3_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.editenchantment")));
        item3.setItemMeta(item3_meta);

        ItemStack item4 = new ItemStack(Material.DIAMOND);
        ItemMeta item4_meta = null;
        item4_meta = item4.getItemMeta();
        item4_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.editcost")));
        item4.setItemMeta(item4_meta);

        for (int i = 9; i <= 17; i++)
        {
            ItemStack wool = new ItemStack(Material.WOOL, 1, (short) 0, (byte) 14);
            ItemMeta wool_meta = null;
            wool_meta = wool.getItemMeta();
            wool_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
            wool.setItemMeta(wool_meta);
            inv.setItem(i, wool);
        }

        inv.setItem(0, item0);
        inv.setItem(2, item1);
        inv.setItem(4, item2);
        inv.setItem(6, item3);
        inv.setItem(8, item4);
        p.openInventory(inv);

        String shsl = shop + "," + slot;

        m.inedit.put(p.getName(), shsl);
    }
    public HashMap<String, String> getBack()
    {
        return back;
    }
}
