package deity.skillsystem.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import deity.skillsystem.Skill;
import deity.skillsystem.SkillCore;
import deity.skillsystem.handlers.EventSubs;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EventSubs());
		NetworkRegistry.instance().registerGuiHandler(SkillCore.instance, new deity.skillsystem.handlers.GuiRequest());
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}

	public void syncSkill(EntityPlayer owner, Skill skill)
	{

	}

	public void syncSkill(World world, Skill skill)
	{
		Object[] list = world.playerEntities.toArray();
		for (int i = 0; i < list.length; i++)
			syncSkill((EntityPlayer) list[i], skill);
	}
}