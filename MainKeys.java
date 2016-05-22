package MineMineNoMi3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import MineMineNoMi3.Network.PacketDispatcher;
import MineMineNoMi3.Network.Packets.PacketPlayerSERVER;

public class MainKeys 
{

	public static KeyBinding guiPlayer;
	    
	public static void init() 
	{
		guiPlayer = new KeyBinding("Main GUI", Keyboard.KEY_R, "Mine Mine no Mi Keys");
		ClientRegistry.registerKeyBinding(guiPlayer);
	}
	    
	public static boolean isShiftKeyDown()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);		
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) 
	{
		if(guiPlayer.isPressed())
		{
        	Minecraft minecraft = Minecraft.getMinecraft();
        	EntityPlayer player = minecraft.thePlayer; 
        	WorldClient world = minecraft.theWorld;  
        	player.openGui(Main.getMineMineNoMi(), 1, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        	
        	PacketDispatcher.sendToServer(new PacketPlayerSERVER("forcesync"));
		}
	}
	
}
