package net.slipcor.pvparena.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import net.slipcor.pvparena.PVPArena;
import net.slipcor.pvparena.managers.Statistics;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * language manager class
 * 
 * -
 * 
 * provides methods to display configurable texts
 * 
 * @author slipcor
 * 
 * @version v0.8.11
 * 
 */
public class Language {
	static Map<String, Object> lang = null; // game language map
	static Map<String, Object> log = null; // log language map

	/**
	 * create a language manager instance
	 */
	public static void init(String s) {
		s = s.equals("en") ? "" : "_" + s;
		new File("plugins/pvparena").mkdir();
		File configFile = new File("plugins/pvparena/lang" + s + ".yml");
		if (!(configFile.exists()))
			try {
				configFile.createNewFile();
			} catch (Exception e) {
				Bukkit.getLogger().severe(
						"[PVP Arena] Error when creating language file.");
			}

		YamlConfiguration config = new YamlConfiguration();
		try {
			config.load(configFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidConfigurationException e1) {
			e1.printStackTrace();
		}

		config.addDefault("log.matnotfound", "Unrecognized material: %1%");
		config.addDefault("log.enabled", "enabled (version %1%)");
		config.addDefault("log.disabled", "disabled (version %1%)");
		config.addDefault("log.startTracker",
				"Plugin tracking enabled. Set stats: false inside the main config to disable.");
		config.addDefault("log.stopTracker",
				"Plugin tracking disabled. See you soon?");
		config.addDefault("log.notupdating",
				"Updates deactivated. Please check dev.bukkit for updates.");
		config.addDefault("log.updating", "Checking for updates...");
		config.addDefault("log.warn", "%1%");

		config.addDefault("lang.playerleave", "%1% has left the fight!");
		config.addDefault("lang.youleave", "You have left the fight!");
		config.addDefault("lang.dropitem", "Not so fast! No Cheating!");
		config.addDefault("lang.usepatoexit",
				"Please use '/pa leave' to exit the fight!");
		config.addDefault("lang.pos1", "First position set.");
		config.addDefault("lang.pos2", "Second position set.");
		config.addDefault("lang.classperms",
				"You don't have permission for that class.");
		config.addDefault("lang.waitequal",
				"Waiting for the teams to have equal player number!");
		config.addDefault("lang.ready", "%1% team is ready!");
		config.addDefault("lang.begin", "Let the fight begin!");
		config.addDefault("lang.killedbylives",
				"%1% has been killed by %2%! %3% lives remaining.");
		config.addDefault("lang.killedby",
				"%1% has been killed by %2%!");
		config.addDefault("lang.onlyplayers",
				"Only players may access this command!");
		config.addDefault("lang.arenadisabled",
				"Arena disabled, please try again later!");
		config.addDefault("lang.arenanotsetup",
				"All waypoints must be set up first. %1%");
		config.addDefault("lang.permjoin",
				"You don't have permission to join the arena!");
		config.addDefault("lang.alreadyjoined", "You already joined!");
		config.addDefault("lang.fightinprogress",
				"A fight is already in progress!");
		config.addDefault("lang.youjoined", "Welcome! You are on team %1%");
		config.addDefault("lang.playerjoined", "%1% has joined team %2%");
		config.addDefault("lang.nopermto", "You don't have permission to %1%");
		config.addDefault("lang.enable", "enable");
		config.addDefault("lang.disable", "disable");
		config.addDefault("lang.join", "join");
		config.addDefault("lang.reload", "reload");
		config.addDefault("lang.enabled", "Enabled!");
		config.addDefault("lang.disabled", "Disabled!");
		config.addDefault("lang.admin", "administrate");
		config.addDefault("lang.reloaded", "Config reloaded!");
		config.addDefault("lang.noplayer", "No player in the PVP arena.");
		config.addDefault("lang.players", "Players");
		config.addDefault("lang.notreadyplayers", "Players not ready");
		config.addDefault(
				"lang.specwelcome",
				"Welcome to the spectator's area! /pa bet [name] [amount] to bet on team or player");
		config.addDefault("lang.setspectator", "Spectator spawn set.");
		config.addDefault("lang.setexit", "Exit spawn set.");
		config.addDefault("lang.settype", "Arena %1% now has type %2%.");
		config.addDefault("lang.forcestop",
				"You have forced the fight to stop.");
		config.addDefault("lang.nofight", "There is no fight in progress.");
		config.addDefault("lang.invalidcmd", "Invalid command (%1%)");
		config.addDefault("lang.regionset", "Setting region enabled.");
		config.addDefault("lang.regionsaved", "Region saved.");
		config.addDefault("lang.regionnotremoved", "There is no region setup.");
		config.addDefault("lang.awarded", "You have been awarded %1%");
		config.addDefault("lang.invfull",
				"Your inventory was full. You did not receive all rewards!");
		config.addDefault("lang.arenaexists", "Arena already exists!");
		config.addDefault("lang.arenanotexists", "Arena does not exist: %1%");
		config.addDefault("lang.regionalreadybeingset",
				"A region is already being created: %1%");
		config.addDefault("lang.regionnotbeingset",
				"A region is not being created!");
		config.addDefault("lang.notinarena", "You are not part of an arena!");
		config.addDefault("lang.arenas", "Available arenas: %1%");
		config.addDefault("lang.setup", "setup an arena");
		config.addDefault("lang.create", "create an arena");
		config.addDefault("lang.created", "arena '%1%' created!");
		config.addDefault("lang.remove", "remove an arena");
		config.addDefault("lang.removed", "arena '%1%' removed!");
		config.addDefault("lang.setspawn", "Spawn set: %1%");
		config.addDefault("lang.loungeset", "Lounge set: %1%");
		config.addDefault("lang.selectteam",
				"You must select a team to join! /pa [arenaname] [team]");
		config.addDefault("lang.notselectteam",
				"You cannot select a team to join! /pa [arenaname]");
		config.addDefault("lang.joinrange",
				"You are too far away to join this arena!");
		config.addDefault("lang.teamhaswon", "%1% are the Champions!");
		config.addDefault("lang.notsameworld",
				"Not in the same world as the arena (%1%)!");

		config.addDefault("lang.checkregionerror",
				"Waiting for a running arena to finish!");

		config.addDefault("lang.notready", "At least one player is not ready!");
		config.addDefault("lang.notready1", "You are alone in the arena!");
		config.addDefault("lang.notready2", "Your team is alone in the arena!");
		config.addDefault("lang.notready3", "A team is missing players!");
		config.addDefault("lang.notready4", "The arena is missing players!");
		config.addDefault("lang.notready5",
				"At least one player has not chosen a class!");

		config.addDefault("lang.teamfull", "team %1% is full!");
		config.addDefault("lang.arenafull", "arena is full!");
		config.addDefault("lang.classfull", "class is full!");

		config.addDefault("lang.errorcustomflag",
				"Error! Arena is not of type ctf.");
		config.addDefault("lang.errorcustompumpkin",
				"Error! Arena is not of type pumpkin.");
		config.addDefault("lang.errorspawnfree",
				"Error! Arena is of type free. Use 'spawnX' where X is a digit or letter!");
		config.addDefault("lang.errorloungefree",
				"Error! Arena is not of type free. Use '[teamname]lounge'");

		config.addDefault("lang.notjoinregion",
				"You are not in the join region! Move there to join!");

		config.addDefault("lang.edit", "edit an arena");
		config.addDefault("lang.teleport", "teleport to an arena spawn");
		config.addDefault("lang.edittrue", "Enabled edit mode for arena: %1%");
		config.addDefault("lang.editfalse", "Disabled edit mode for arena: %1%");
		
		config.addDefault("lang.unknownarena", "Unknown arena");
		config.addDefault("lang.unknowncmd", "Unknown command");

		config.addDefault("lang.insidevehicle",
				"You cannot join while on a vehicle!");
		config.addDefault("lang.startingin",
				"Enough players ready. Starting in %1% seconds!");

		config.addDefault("lang.invalidstattype",
				"Invalid statistics type: %1%");
		config.addDefault("lang.set",
				"set a config node");
		

		config.addDefault("lang.classpreview",
				"You are now previewing the class %1%");
		config.addDefault("lang.classsaved",
				"Class saved: %1%");
		config.addDefault("lang.classremoved",
				"Class removed: %1%");
		config.addDefault("lang.classunknown",
				"Class unknown: %1%");

		config.addDefault("lang.arenatypeunknown",
				"Arena Type '%1%' unknown. consult the forums: http://goo.gl/KyJzo");
		config.addDefault("lang.arenaregionshapeunknown",
				"Arena Shape '%1%' unknown. consult the forums: http://goo.gl/IfLOh");
		config.addDefault("lang.arenateamunknown",
				"Arena Team '%1%' unknown!");

		for (Statistics.type t : Statistics.type.values()) {
			config.addDefault("lang." + t, t.getName());
		}

		/**
		 * death causes : "player was killed by ****"
		 */

		config.addDefault("lang.BLOCK_EXPLOSION".toLowerCase(), "an explosion");
		config.addDefault("lang.CONTACT".toLowerCase(), "a cactus");
		config.addDefault("lang.CUSTOM".toLowerCase(), "Herobrine");
		config.addDefault("lang.DROWNING".toLowerCase(), "water");
		config.addDefault("lang.ENTITY_EXPLOSION".toLowerCase(), "a creeper");
		config.addDefault("lang.FALL".toLowerCase(), "gravity");
		config.addDefault("lang.FIRE".toLowerCase(), "a fire");
		config.addDefault("lang.FIRE_TICK".toLowerCase(), "fire");
		config.addDefault("lang.LAVA".toLowerCase(), "lava");
		config.addDefault("lang.LIGHTNING".toLowerCase(), "Thor");
		config.addDefault("lang.MAGIC".toLowerCase(), "Magical Powers");
		config.addDefault("lang.POISON".toLowerCase(), "Poison");
		config.addDefault("lang.PROJECTILE".toLowerCase(),
				"something he didn't see coming");
		config.addDefault("lang.STARVATION".toLowerCase(), "hunger");
		config.addDefault("lang.SUFFOCATION".toLowerCase(), "lack of air");
		config.addDefault("lang.SUICIDE".toLowerCase(), "self");
		config.addDefault("lang.VOID".toLowerCase(), "the Void");

		config.addDefault("lang.spawnremoved", "Spawn removed: %1%");
		config.addDefault("lang.regionremoved", "Region removed: %1%");
		config.addDefault("lang.nocampregion", "You are in a NOCAMP region. Move!");
		config.addDefault("lang.deathregion", "You entered a DEATH region. Goodbye!");
		config.addDefault("lang.youescaped", "You escaped the battlefield. Goodbye!");

		config.addDefault("lang.args", "Wrong number of arguments (%1% instead of %2%)");

		config.addDefault("lang.chatteam", "You now talk to your team!");
		config.addDefault("lang.chatpublic", "You now talk to the public!");

		config.addDefault("lang.select2",
				"Select two points before trying to save.");
		config.addDefault("lang.whitelist",
				"Whitelist");
		config.addDefault("lang.whitelistclear",
				"Whitelist cleared.");
		config.addDefault("lang.whitelistadd",
				"Added to whitelist: %1%");
		config.addDefault("lang.whitelistremove",
				"Removed from whitelist: %1%");
		config.addDefault("lang.blacklist",
				"Blacklist");
		config.addDefault("lang.blacklistclear",
				"Blacklist cleared.");
		config.addDefault("lang.blacklistadd",
				"Added to blacklist: %1%");
		config.addDefault("lang.blacklistremove",
				"Removed from blacklist: %1%");

		config.addDefault("lang.joinarena",
				"Arena is starting! Type /pa %1% to join!");
		config.addDefault("lang.warmingup",
				"Warming up... stand by...");
		config.addDefault("lang.countdowninterrupt",
				"Countdown interrupted! Hit the ready block!");

		config.addDefault("lang.startinginexact",
				"Starting in %1%!");
		config.addDefault("lang.warmingupexact",
				"Warming up... %1%!");
		config.addDefault("lang.resetexact",
				"The arena will reset in %1%!");
		config.addDefault("lang.endingexact",
				"The match will end in %1%!");
		config.addDefault("lang.seconds",
				"seconds");
		config.addDefault("lang.minutes",
				"minutes");
		
		PVPArena.instance.getAmm().initLanguage(config);
		PVPArena.instance.getAtm().initLanguage(config);

		config.options().copyDefaults(true);
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// write contents to maps
		lang = (Map<String, Object>) config.getConfigurationSection("lang")
				.getValues(true);
		log = (Map<String, Object>) config.getConfigurationSection("log")
				.getValues(true);

	}

	/**
	 * read a node from the config and return its value
	 * 
	 * @param s
	 *            the node name
	 * @return the node string
	 */
	public static String parse(String s) {
		return StringParser.colorize((String) lang.get(s)); // hand over map value
	}

	/**
	 * read a node from the config and return its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg
	 *            a string to replace
	 * @return the replaced node string
	 */
	public static String parse(String s, String arg) {
		String var = (String) lang.get(s);
		return StringParser.colorize(var.replace("%1%", arg)); // hand over replaced map value
	}

	/**
	 * read a node from the config and return its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg1
	 *            a string to replace
	 * @param arg2
	 *            a string to replace
	 * @return the replaced node string
	 */
	public static String parse(String s, String arg1, String arg2) {
		String var = ((String) lang.get(s)).replace("%2%", arg2);
		return StringParser.colorize(var.replace("%1%", arg1)); // hand over replaced map value
	}

	/**
	 * read a node from the config and return its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg1
	 *            a string to replace
	 * @param arg2
	 *            a string to replace
	 * @param arg3
	 *            a string to replace
	 * @return the replaced node string
	 */
	public static String parse(String s, String arg1, String arg2, String arg3) {
		String var = ((String) lang.get(s)).replace("%2%", arg2);
		var = var.replace("%3%", arg3);
		return StringParser.colorize(var.replace("%1%", arg1)); // hand over replaced map value
	}

	/**
	 * read a node from the config and log its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg
	 *            a string to replace
	 */
	public static void log_error(String s, String arg) {
		String var = (String) log.get(s);
		Bukkit.getLogger().severe("[PVP Arena] " + var.replace("%1%", arg));
		// log replaced map value
	}

	/**
	 * read a node from the config and log its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg
	 *            a string to replace
	 */
	public static void log_warning(String s, String arg) {
		String var = (String) log.get(s);
		Bukkit.getLogger().warning("[PVP Arena] " + var.replace("%1%", arg));
		// log replaced map value
	}

	/**
	 * read a node from the config and log its value after replacing
	 * 
	 * @param s
	 *            the node name
	 * @param arg
	 *            a string to replace
	 */
	public static void log_info(String s, String arg) {
		String var = (String) log.get(s);
		Bukkit.getLogger().info("[PVP Arena] " + var.replace("%1%", arg));
		// log replaced map value
	}

	/**
	 * read a node from the config and log its value
	 * 
	 * @param s
	 *            the node name
	 */
	public static void log_info(String s) {
		String var = (String) log.get(s);
		Bukkit.getLogger().info("[PVP Arena] " + var);
		// log map value
	}
}
