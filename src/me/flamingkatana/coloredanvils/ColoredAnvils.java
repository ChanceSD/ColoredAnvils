package me.flamingkatana.coloredanvils;

import java.util.Map;
import org.bukkit.plugin.PluginDescriptionFile;

public class ColoredAnvils extends org.bukkit.plugin.java.JavaPlugin {
	private static ColoredAnvils plugin;
	private static boolean permissions;
	private static boolean permissionsForNonNameChanges;
	private static boolean filters;
	private static String filterMessage;

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been enabled!");
		plugin = this;
		getServer().getPluginManager().registerEvents(new RepairListener(), this);
		saveDefaultConfig();
		updateConfig();
		permissions = getConfig().getBoolean("Use Permissions");
		permissionsForNonNameChanges = getConfig().getBoolean("Use_Permissions_If_Not_Changing_Name");
		filters = getConfig().getBoolean("Filter_Enabled");
		filterMessage = getConfig().getString("Filter_Message");
		getLogger().info("Permissions for " + pdfFile.getName() + " are " + (usingPermissions() ? "enabled" : "disabled") + ".");
	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		getLogger().info(pdfFile.getName() + " v" + pdfFile.getVersion() + " has been disabled!");
	}

	public static ColoredAnvils getPlugin() {
		return plugin;
	}

	public static boolean usingPermissions() {
		return permissions;
	}

	public static boolean usingPermissionsForNonNameChanges() {
		return permissionsForNonNameChanges;
	}

	public static boolean usingFilters() {
		return filters;
	}

	public static String getFilterMessage() {
		return filterMessage;
	}

	public static void updateConfig() {
		Map<String, Object> map = getPlugin().getConfig().getValues(false);
		if (!map.containsKey("Use_Permissions_If_Not_Changing_Name"))
			getPlugin().getConfig().set("Use_Permissions_If_Not_Changing_Name", Boolean.valueOf(false));
		if (!map.containsKey("Filter_Enabled"))
			getPlugin().getConfig().set("Filter_Enabled", Boolean.valueOf(false));
		if (!map.containsKey("Filter"))
			getPlugin().getConfig().set("Filter", new String[] { "Filter", "Example" });
		if (!map.containsKey("Filter_Message"))
			getPlugin().getConfig().set("Filter_Message", "Your item cannot contain the word ");
		getPlugin().saveConfig();
	}
}
