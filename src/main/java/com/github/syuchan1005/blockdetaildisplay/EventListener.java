package com.github.syuchan1005.blockdetaildisplay;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created by syuchan on 2016/09/20.
 */
@EasyRegister.AddListener
public class EventListener implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
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
