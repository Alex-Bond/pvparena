package net.slipcor.pvparena.commands;

import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.core.Language;
import net.slipcor.pvparena.core.Config.CFG;
import net.slipcor.pvparena.core.Language.MSG;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * <pre>PVP Arena GAMEMODE Command class</pre>
 * 
 * A command to set the arena game mode
 * 
 * @author slipcor
 * 
 * @version v0.9.0
 */

public class PAA_GameMode extends PAA__Command {

	public PAA_GameMode() {
		super(new String[] {});
	}

	@Override
	public void commit(Arena arena, CommandSender sender, String[] args) {
		if (!this.hasPerms(sender, arena)) {
			return;
		}
		
		if (!this.argCountValid(sender, arena, args, new Integer[]{1})) {
			return;
		}
		
		//                                   args[0]
		// usage: /pa {arenaname} gamemode [gamemode]
		
		// game modes: free , team
		
		if (args[0].toLowerCase().startsWith("free")) {
			arena.setFree(true);
			arena.msg(sender, Language.parse(MSG.GAMEMODE_FREE, arena.getName()));
			arena.getArenaConfig().set(CFG.GENERAL_TYPE, "free");
		} else {
			arena.setFree(false);
			arena.msg(sender, Language.parse(MSG.GAMEMODE_TEAM, arena.getName()));
			arena.getArenaConfig().set(CFG.GENERAL_TYPE, "none");
		}

		arena.getArenaConfig().save();
		PAA_Reload cmd = new PAA_Reload();
		cmd.commit(arena, Bukkit.getConsoleSender(), new String[0]);
		
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}
}