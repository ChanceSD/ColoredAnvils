package me.flamingkatana.coloredanvils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class FilterHandler {
	public static List<String> getIllegalWords() {
		if (!ColoredAnvils.usingFilters()) {
			return new ArrayList<>();
		}
		return ColoredAnvils.getPlugin().getConfig().getStringList("Filter");
	}

	public static List<String> getIllegalWordsInString(String string) {
		ArrayList<String> caughtWords = new ArrayList<>();
		String s = string.toLowerCase();
		for (String illegalWord : getIllegalWords()) {
			illegalWord = illegalWord.toLowerCase();
			if (s.contains(illegalWord))
				caughtWords.add(illegalWord);
		}
		return caughtWords;
	}

	public static List<String> getIllegalWordsInItemName(ItemStack item) {
		if ((item == null) || (!item.hasItemMeta()) || (!item.getItemMeta().hasDisplayName()))
			return new ArrayList<>();
		return getIllegalWordsInString(item.getItemMeta().getDisplayName());
	}
}
