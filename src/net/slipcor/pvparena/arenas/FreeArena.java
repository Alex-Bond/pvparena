package net.slipcor.pvparena.arenas;

/*
 * Free Fight Arena class
 * 
 * author: slipcor
 * 
 * version: v0.3.1 - New Arena! FreeFight
 * 
 * history:
 *
 *     v0.3.1 - New Arena! FreeFight
 * 
 */

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.slipcor.pvparena.PVPArenaPlugin;
import net.slipcor.pvparena.managers.LanguageManager;
import net.slipcor.pvparena.managers.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

public class FreeArena extends Arena{
	public FreeArena(String sName) {
		super();

		this.name = sName;
		this.configFile = new File("plugins/pvparena/config.free_" + name + ".yml");
		
		new File("plugins/pvparena").mkdir();
		if (!(configFile.exists()))
			try {
				configFile.createNewFile();
			} catch (Exception e) {
				PVPArenaPlugin.lang.log_error("filecreateerror","config.free_" + name);
			}

		parseConfig("free");
		
		this.teamkilling = true;
		this.manuallyselectteams = false;
		this.woolhead = false;
		this.forceeven = false;
	}
	
	@Override
	public Boolean isSetup() {
		Configuration config = new Configuration(configFile);
		config.load();
		if (config.getKeys("coords") == null) {
			return Boolean.valueOf(false);
		}

		List<String> list = config.getKeys("coords");
		if (!list.contains("spectator"))
			return false;
		if (!list.contains("lounge"))
			return false;
		if (!list.contains("exit"))
			return false;
		Iterator<String> iter = list.iterator();
		int spawns = 0;
		while (iter.hasNext()) {
			String s = iter.next();
			if (s.startsWith("spawn"))
				spawns++;
		}
		if (spawns > 3) {
			return Boolean.valueOf(true);
		}

		return Boolean.valueOf(false);
	}
	
	@Override
	public boolean checkEnd() {
		if (fightUsersTeam.size() > 1) {
			return false;
		}
		
		Set<String> set = fightUsersTeam.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			
			Player z = Bukkit.getServer().getPlayer(o.toString());
			StatsManager.addWinStat(z, "");
			loadPlayer(z, sTPwin);
			giveRewards(z); // if we are the winning team, give reward!
			fightUsersClass.remove(z.getName());
		}
		reset();
		return true;
	}
	
	@Override
	public void chooseColor(Player player) {
		if (!(fightUsersTeam.containsKey(player.getName()))) {
			goToWaypoint(player, "lounge");
			fightUsersTeam.put(player.getName(), "free");
			Arena.tellPlayer(player, PVPArenaPlugin.lang.parse("youjoinedfree"));
			tellEveryoneExcept(player, PVPArenaPlugin.lang.parse("playerjoinedfree", player.getName()));

		} else {
			Arena.tellPlayer(player, PVPArenaPlugin.lang.parse("alreadyjoined"));
		}
	}
	
	@Override
	boolean parseAdminCommand(String[] args, Player player) {
		if (args[0].equalsIgnoreCase("lounge")) {
			setCoords(player, "lounge");
			tellPlayer(player, PVPArenaPlugin.lang.parse("setlounge"));
		} else if (args[0].equalsIgnoreCase("exit")) {
			setCoords(player, "exit");
			tellPlayer(player, PVPArenaPlugin.lang.parse("setexit"));
		} else if (args[0].equalsIgnoreCase("spectator")) {
			setCoords(player, "spectator");
			tellPlayer(player, PVPArenaPlugin.lang.parse("setspectator"));
		} else if (args[0].equalsIgnoreCase("forcestop")) {
			if (fightInProgress) {
				forcestop();
				tellPlayer(player, PVPArenaPlugin.lang.parse("forcestop"));
			} else {
				tellPlayer(player, PVPArenaPlugin.lang.parse("nofight"));
			}
		} else if (args[0].startsWith("spawn")) {
			setCoords(player, args[0]);
			tellPlayer(player, PVPArenaPlugin.lang.parse("setspawn", args[0]));
		} else {
			tellPlayer(player, PVPArenaPlugin.lang.parse("invalidcmd","501"));
			return false;
		}
		return true;
	}
}
