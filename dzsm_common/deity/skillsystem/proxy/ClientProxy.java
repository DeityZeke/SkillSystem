package deity.skillsystem.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import deity.skillsystem.Skill;
import deity.skillsystem.handlers.KeyPress;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);

		KeyBinding[] keys = { new KeyBinding("Skill GUI", Keyboard.KEY_S), };
		boolean[] repeat = { false, false };
		KeyBindingRegistry.registerKeyBinding(new KeyPress(keys, repeat));
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}

	@Override
	public void syncSkill(EntityPlayer owner, Skill skill)
	{
	}

	@Override
	public void syncSkill(World world, Skill skill)
	{
	}
}