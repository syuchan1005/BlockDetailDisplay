package com.github.syuchan1005.blockdetaildisplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

/**
 * Created by syuchan on 2016/09/21.
 */
public class MoveRunnable extends BukkitRunnable {
	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			Block targetBlock = player.getTargetBlock(Collections.singleton(Material.AIR), 7);
			Material type = targetBlock.getType();
			if (type == Material.AIR) return;
			try {
				Util.sendActionBar(player, type.name() + " (#" + type.getId() + "/" + targetBlock.getData() + ")");
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
		}
	}
}
