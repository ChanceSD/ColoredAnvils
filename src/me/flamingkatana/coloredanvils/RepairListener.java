package me.flamingkatana.coloredanvils;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements org.bukkit.event.Listener {
	@EventHandler
	public void onAnvilGUIClick(InventoryClickEvent event) {
		if ((event.getInventory().getType() == InventoryType.ANVIL) && ((event.getWhoClicked() instanceof Player))) {
			Player player = (Player) event.getWhoClicked();
			AnvilInventory inv = (AnvilInventory) event.getInventory();
			AnvilTask task = AnvilTask.getTask(inv);
			if (task == null)
				task = new AnvilTask(inv, player);
			if (event.getRawSlot() == 2) {
				ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv);
				List<String> illegalWords = FilterHandler.getIllegalWordsInItemName(translatedItem);
				if (illegalWords.size() != 0) {
					event.setCancelled(true);
					player.setExp(player.getExp());
					for (String word : illegalWords)
						player.sendMessage(ChatColor.RED + ColoredAnvils.getFilterMessage() + ChatColor.BOLD + word + ChatColor.RED + ".");
				}
				event.setCurrentItem(translatedItem);
			}
		}
	}
}
