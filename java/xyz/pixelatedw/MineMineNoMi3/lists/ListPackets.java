package xyz.pixelatedw.MineMineNoMi3.lists;

import cpw.mods.fml.relauncher.Side;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;

public class ListPackets 
{
	public static void init()
	{
		WyNetworkHelper.registerMessage(PacketSync.ServerHandler.class, PacketSync.class, 1, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketSync.ClientHandler.class, PacketSync.class, 4, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketPlayer.ServerHandler.class, PacketPlayer.class, 2, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketPlayer.ClientHandler.class, PacketPlayer.class, 5, Side.CLIENT);
		WyNetworkHelper.registerMessage(PacketWorld.ServerHandler.class, PacketWorld.class, 3, Side.SERVER);
		WyNetworkHelper.registerMessage(PacketParticles.ClientHandler.class, PacketParticles.class, 6, Side.CLIENT);
	} 
	
}
