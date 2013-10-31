package deity.skillsystem.handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import deity.skillsystem.SkillCore;

public class KeyPress extends KeyHandler
{
	public EnumSet<TickType> tick = EnumSet.of(TickType.CLIENT);
	public static boolean pressed = false;

	public KeyPress(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel()
	{
		return "SkillBind";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		pressed = !pressed;

		if (tickEnd && kb.keyCode == Keyboard.KEY_S && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			player.openGui(SkillCore.instance, 0, player.worldObj, player.serverPosX, player.serverPosY, player.serverPosZ);
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		pressed = !pressed;
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return tick;
	}
}