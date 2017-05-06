package me.loganbwde.lst;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.loganbwde.Clan.main;
import net.md_5.bungee.api.ChatColor;

public class OnInvClick implements Listener
{
    private main m;
    public OnInvClick(main main)
    {
        m = main;
        m.getServer().getPluginManager().registerEvents(this, m);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e)
    {
        Inventory inv = e.getInventory();
        ItemStack click = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        FileConfiguration shop = m.getFileManager().getShop();
        HashMap<String,String> back = m.getShopManager().getBack();
        try
        {
            if (click.getType() == Material.WOOL)
            {
                if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', "&cBack"))))
                {
                    if (back.get(p.getName()).equals("Main") || back.get(p.getName()).equals("Admin"))
                    {
                        m.getShopManager().openShop(p);
                    }
                    if (back.get(p.getName()).equals("ArmorAdmin"))
                    {
                        if (back.containsKey(e.getWhoClicked().getName()))
                        {
                            back.remove(e.getWhoClicked().getName());
                        }
                        back.put(e.getWhoClicked().getName(), "Main");
                        m.getShopManager().openAdminArmor(p);
                    }
                    if (back.get(p.getName()).equals("BowAdmin"))
                    {
                        if (back.containsKey(e.getWhoClicked().getName()))
                        {
                            back.remove(e.getWhoClicked().getName());
                        }
                        back.put(e.getWhoClicked().getName(), "Main");
                        m.getShopManager().openAdminBow(p);
                    }
                    if (back.get(p.getName()).equals("SwordAdmin"))
                    {
                        if (back.containsKey(e.getWhoClicked().getName()))
                        {
                            back.remove(e.getWhoClicked().getName());
                        }
                        back.put(e.getWhoClicked().getName(), "Main");
                        m.getShopManager().openAdminSword(p);
                    }
                    if (back.get(p.getName()).equals("SpecialAdmin"))
                    {
                        if (back.containsKey(e.getWhoClicked().getName()))
                        {
                            back.remove(e.getWhoClicked().getName());
                        }
                        back.put(e.getWhoClicked().getName(), "Main");
                        m.getShopManager().openAdminSpecial(p);
                    }
                }
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getConfigEntrys().get("Basic.shopname"))))
            {
                e.setCancelled(true);
                if (click.getType() == Material.getMaterial(shop.getString("Armor.main.Material")))
                {
                    if (shop.getString("Armor.main.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Armor.main.Name")))))
                        {
                            m.getShopManager().openShopArmor((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openShopArmor((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Main");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Bow.main.Material")))
                {
                    if (shop.getString("Bow.main.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Bow.main.Name")))))
                        {
                            m.getShopManager().openShopBow((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openShopBow((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Main");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Sword.main.Material")))
                {
                    if (shop.getString("Sword.main.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Sword.main.Name")))))
                        {
                            m.getShopManager().openShopSword((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openShopSword((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Main");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Special.main.Material")))
                {
                    if (shop.getString("Special.main.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Special.main.Name")))))
                        {
                            m.getShopManager().openShopSpecial((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openShopSpecial((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Main");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Armor.admin.Material")))
                {
                    if (shop.getString("Armor.admin.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Armor.admin.Name")))))
                        {
                            m.getShopManager().openAdminArmor((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openAdminArmor((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Admin");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Bow.admin.Material")))
                {
                    if (shop.getString("Bow.admin.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Bow.admin.Name")))))
                        {
                            m.getShopManager().openAdminBow((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openAdminBow((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Admin");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Sword.admin.Material")))
                {
                    if (shop.getString("Sword.admin.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Sword.admin.Name")))))
                        {
                            m.getShopManager().openAdminSword((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openAdminSword((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Admin");
                }

                if (click.getType() == Material.getMaterial(shop.getString("Special.admin.Material")))
                {
                    if (shop.getString("Special.admin.Name") != null)
                    {
                        if (click.getItemMeta().getDisplayName().equals((ChatColor.translateAlternateColorCodes('&', shop.getString("Special.admin.Name")))))
                        {
                            m.getShopManager().openAdminSpecial((Player) e.getWhoClicked());
                        }
                    }
                    else
                    {
                        m.getShopManager().openAdminSpecial((Player) e.getWhoClicked());
                    }
                    if (back.containsKey(e.getWhoClicked().getName()))
                    {
                        back.remove(e.getWhoClicked().getName());
                    }
                    back.put(e.getWhoClicked().getName(), "Admin");
                }
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Armor.admin.Name"))))
            {
                if (back.containsKey(e.getWhoClicked().getName()))
                {
                    back.remove(e.getWhoClicked().getName());
                }
                back.put(e.getWhoClicked().getName(), "ArmorAdmin");
                if (e.getSlot() <= 8)
                {
                    m.getShopManager().openEdit((Player) e.getWhoClicked(), "Armor", e.getSlot());
                }
                e.setCancelled(true);
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Bow.admin.Name"))))
            {
                if (back.containsKey(e.getWhoClicked().getName()))
                {
                    back.remove(e.getWhoClicked().getName());
                }
                back.put(e.getWhoClicked().getName(), "BowAdmin");
                if (e.getSlot() <= 8)
                {
                    m.getShopManager().openEdit((Player) e.getWhoClicked(), "Bow", e.getSlot());
                }
                e.setCancelled(true);
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Sword.admin.Name"))))
            {
                if (back.containsKey(e.getWhoClicked().getName()))
                {
                    back.remove(e.getWhoClicked().getName());
                }
                back.put(e.getWhoClicked().getName(), "SwordAdmin");
                if (e.getSlot() <= 8)
                {
                    m.getShopManager().openEdit((Player) e.getWhoClicked(), "Sword", e.getSlot());
                }
                e.setCancelled(true);
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Special.admin.Name"))))
            {
                if (back.containsKey(e.getWhoClicked().getName()))
                {
                    back.remove(e.getWhoClicked().getName());
                }
                back.put(e.getWhoClicked().getName(), "SpecialAdmin");
                if (e.getSlot() <= 8)
                {
                    m.getShopManager().openEdit((Player) e.getWhoClicked(), "Special", e.getSlot());
                }
                e.setCancelled(true);
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Armor.main.Name"))))
            {
                e.setCancelled(true);
                try
                {
                    if (m.getShopManager().getItemsArmor().get(e.getSlot()).isSimilar(e.getCurrentItem()) && e.getSlot() <= 8)
                    {
                        if (m.getShopManager().checkPoints((Player) e.getWhoClicked(), shop.getInt("Armor." + "slot" + e.getSlot() + "." + ".Cost")))
                        {
                            m.getClanManager().removePoints(e.getWhoClicked().getName(), shop.getInt("Armor." + "slot" + e.getSlot() + "." + ".Cost"));
                            ItemStack item = m.getShopManager().getItemsArmor().get(e.getSlot());
                            ItemMeta item_meta = item.getItemMeta();
                            item_meta.setLore(new ArrayList<String>());
                            item.setItemMeta(item_meta);
                            e.getWhoClicked().getInventory().addItem(item);
                            String points = String.valueOf(shop.getInt("Armor." + "slot" + e.getSlot() + "." + ".Cost"));
                            String pointsleft = String.valueOf(m.getClanManager().getPlayerLevel(e.getWhoClicked().getName()));
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.boughtitem").replace("%points%", points).replace("%pointsleft%", pointsleft));
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughlvl"));
                        }
                    }
                }
                catch (NullPointerException ex)
                {
                }
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Bow.main.Name"))))
            {
                e.setCancelled(true);
                try
                {
                    if (m.getShopManager().getItemsBow().get(e.getSlot()).isSimilar(e.getCurrentItem()) && e.getSlot() <= 8)
                    {
                        if (m.getShopManager().checkPoints((Player) e.getWhoClicked(), shop.getInt("Bow." + "slot" + e.getSlot() + "." + ".Cost")))
                        {
                            m.getClanManager().removePoints(e.getWhoClicked().getName(), shop.getInt("Bow." + "slot" + e.getSlot() + "." + ".Cost"));
                            ItemStack item = m.getShopManager().getItemsBow().get(e.getSlot());
                            ItemMeta item_meta = item.getItemMeta();
                            item_meta.setLore(new ArrayList<String>());
                            item.setItemMeta(item_meta);
                            e.getWhoClicked().getInventory().addItem(item);
                            String points = String.valueOf(shop.getInt("Bow." + "slot" + e.getSlot() + "." + ".Cost"));
                            String pointsleft = String.valueOf(m.getClanManager().getPlayerLevel(e.getWhoClicked().getName()));
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.boughtitem").replace("%points%", points).replace("%pointsleft%", pointsleft));
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughlvl"));
                        }
                    }
                }
                catch (NullPointerException ex)
                {
                }
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Sword.main.Name"))))
            {
                e.setCancelled(true);
                try
                {
                    if (m.getShopManager().getItemsSword().get(e.getSlot()).isSimilar(e.getCurrentItem()) && e.getSlot() <= 8)
                    {
                        if (m.getShopManager().checkPoints((Player) e.getWhoClicked(), shop.getInt("Sword." + "slot" + e.getSlot() + "." + ".Cost")))
                        {
                            m.getClanManager().removePoints(e.getWhoClicked().getName(), shop.getInt("Sword." + "slot" + e.getSlot() + "." + ".Cost"));
                            ItemStack item = m.getShopManager().getItemsSword().get(e.getSlot());
                            ItemMeta item_meta = item.getItemMeta();
                            item_meta.setLore(new ArrayList<String>());
                            item.setItemMeta(item_meta);
                            e.getWhoClicked().getInventory().addItem(item);
                            String points = String.valueOf(shop.getInt("Sword." + "slot" + e.getSlot() + "." + ".Cost"));
                            String pointsleft = String.valueOf(m.getClanManager().getPlayerLevel(e.getWhoClicked().getName()));
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.boughtitem").replace("%points%", points).replace("%pointsleft%", pointsleft));
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughlvl"));
                        }
                    }
                }
                catch (NullPointerException ex)
                {
                }
            }

            if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', shop.getString("Special.main.Name"))))
            {
                e.setCancelled(true);
                try
                {
                    if (m.getShopManager().getItemsSpecial().get(e.getSlot()).isSimilar(e.getCurrentItem()) && e.getSlot() <= 8)
                    {
                        if (m.getShopManager().checkPoints((Player) e.getWhoClicked(), shop.getInt("Special." + "slot" + e.getSlot() + "." + ".Cost")))
                        {
                            m.getClanManager().removePoints(e.getWhoClicked().getName(), shop.getInt("Special." + "slot" + e.getSlot() + "." + ".Cost"));
                            ItemStack item = m.getShopManager().getItemsSpecial().get(e.getSlot());
                            ItemMeta item_meta = item.getItemMeta();
                            item_meta.setLore(new ArrayList<String>());
                            item.setItemMeta(item_meta);
                            e.getWhoClicked().getInventory().addItem(item);
                            String points = String.valueOf(shop.getInt("Special." + "slot" + e.getSlot() + "." + ".Cost"));
                            String pointsleft = String.valueOf(m.getClanManager().getPlayerLevel(e.getWhoClicked().getName()));
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.boughtitem").replace("%points%", points).replace("%pointsleft%", pointsleft));
                        }
                        else
                        {
                            m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.notenoughlvl"));
                        }
                    }
                }
                catch (NullPointerException ex)
                {
                }
            }

            if (m.inedit.containsKey(e.getWhoClicked().getName()))
            {
                String shsl = m.inedit.get(e.getWhoClicked().getName());
                String[] args = shsl.split(",");
                if (inv.getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', m.getFileManager().getMessageEntrys().get("Shop.selected").replace("%shop%", args[0]).replace("%slot%", args[1]))))
                {
                    e.setCancelled(true);
                    if (click.getType() == Material.WORKBENCH)
                    {
                        m.matedit.add(e.getWhoClicked().getName());
                        m.amountedit.remove(e.getWhoClicked().getName());
                        m.nameedit.remove(e.getWhoClicked().getName());
                        m.enchedit.remove(e.getWhoClicked().getName());
                        m.costedit.remove(e.getWhoClicked().getName());
                        e.getWhoClicked().closeInventory();
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.matedit"));
                    }

                    if (click.getType() == Material.EMPTY_MAP)
                    {
                        m.amountedit.add(e.getWhoClicked().getName());
                        m.matedit.remove(e.getWhoClicked().getName());
                        m.nameedit.remove(e.getWhoClicked().getName());
                        m.enchedit.remove(e.getWhoClicked().getName());
                        m.costedit.remove(e.getWhoClicked().getName());
                        e.getWhoClicked().closeInventory();
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.amountedit"));
                    }

                    if (click.getType() == Material.NAME_TAG)
                    {
                        m.nameedit.add(e.getWhoClicked().getName());
                        m.amountedit.remove(e.getWhoClicked().getName());
                        m.matedit.remove(e.getWhoClicked().getName());
                        m.enchedit.remove(e.getWhoClicked().getName());
                        m.costedit.remove(e.getWhoClicked().getName());
                        e.getWhoClicked().closeInventory();
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.nameedit"));
                    }

                    if (click.getType() == Material.ENCHANTMENT_TABLE)
                    {
                        m.enchedit.add(e.getWhoClicked().getName());
                        m.amountedit.remove(e.getWhoClicked().getName());
                        m.nameedit.remove(e.getWhoClicked().getName());
                        m.matedit.remove(e.getWhoClicked().getName());
                        m.costedit.remove(e.getWhoClicked().getName());
                        e.getWhoClicked().closeInventory();
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.enchedit"));
                    }

                    if (click.getType() == Material.DIAMOND)
                    {
                        m.costedit.add(e.getWhoClicked().getName());
                        m.matedit.remove(e.getWhoClicked().getName());
                        m.amountedit.remove(e.getWhoClicked().getName());
                        m.nameedit.remove(e.getWhoClicked().getName());
                        m.enchedit.remove(e.getWhoClicked().getName());
                        e.getWhoClicked().closeInventory();
                        m.getMessagesManager().sendMessage(p, m.getFileManager().getMessageEntrys().get("Messages.costedit"));
                    }
                }
            }
        }
        catch (NullPointerException ex)
        {
        }
    }
}
