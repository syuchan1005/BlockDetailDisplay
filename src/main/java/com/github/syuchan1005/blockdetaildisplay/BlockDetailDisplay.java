package com.github.syuchan1005.blockdetaildisplay;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class BlockDetailDisplay extends JavaPlugin {
	EasyRegister easyRegister;

	@Override
	public void onEnable() {
		try {
			easyRegister = new EasyRegister(this);
			easyRegister.registerCommand("blockdetaildisplay", "TestCommand", "/<command> <Text>", "", "You don't have Permission.", "bdd");
			Util.init();
		} catch (ReflectiveOperationException | IOException e) {
			e.printStackTrace();
		}
	}

	@EasyRegister.AddCommand(Command = "blockdetaildisplay")
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String[] args) {
		try {
			Util.sendActionBar(((Player) sender), args[0]);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		return true;
	}
}
