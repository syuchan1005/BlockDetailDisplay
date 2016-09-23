package com.github.syuchan1005.blockdetaildisplay;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by syuchan on 2016/09/20.
 */
public class Util {
	private static String OBSPCG;
	private static String NMSPCG;

	static class CraftPlayer {
		private static Class CLASS;
		private static Method GETHANDLE;
		private static Field PLAYERCONNECTION;
		private static Method SENDPACKET;
	}

	static class Packet {
		private static Class CLASS;
		private static Constructor PLAYOUTCHAT;
	}

	static class Chat {
		private static Constructor COMPONENTTEXT;
		private static Class IBASECOMPONENT;
	}

	public static void init() throws ReflectiveOperationException {
		OBSPCG = Bukkit.getServer().getClass().getPackage().getName();
		NMSPCG = OBSPCG.replaceAll("org.bukkit.craftbukkit", "net.minecraft.server");
		CraftPlayer.CLASS = Class.forName(OBSPCG + ".entity.CraftPlayer");
		CraftPlayer.GETHANDLE = CraftPlayer.CLASS.getMethod("getHandle");
		CraftPlayer.PLAYERCONNECTION = CraftPlayer.GETHANDLE.getReturnType().getField("playerConnection");
		Packet.CLASS = Class.forName(NMSPCG + ".Packet");
		CraftPlayer.SENDPACKET = CraftPlayer.PLAYERCONNECTION.getType().getMethod("sendPacket", Packet.CLASS);
		Chat.COMPONENTTEXT = Class.forName(NMSPCG + ".ChatComponentText").getConstructor(String.class);
		Chat.IBASECOMPONENT = Class.forName(NMSPCG + ".IChatBaseComponent");
		Packet.PLAYOUTCHAT = Class.forName(NMSPCG + ".PacketPlayOutChat").getConstructor(Chat.IBASECOMPONENT, byte.class);
	}


	public static void sendActionBar(Player player, String message) throws ReflectiveOperationException {
		Object packet = Packet.PLAYOUTCHAT.newInstance(Chat.COMPONENTTEXT.newInstance(message), (byte) 2);
		Util.sendPacket(player, packet);
	}

	public static void sendPacket(Player player, Object packet) throws ReflectiveOperationException {
		Object craftPlayer = CraftPlayer.CLASS.cast(player);
		Object playerConnection = CraftPlayer.PLAYERCONNECTION.get(CraftPlayer.GETHANDLE.invoke(craftPlayer));
		CraftPlayer.SENDPACKET.invoke(playerConnection, packet);
	}

}
