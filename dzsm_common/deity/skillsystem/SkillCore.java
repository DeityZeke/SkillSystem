package deity.skillsystem;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import deity.skillsystem.handlers.Packets;
import deity.skillsystem.proxy.CommonProxy;

@Mod(modid = DefaultProps.ID, name = DefaultProps.NAME, version = DefaultProps.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { DefaultProps.CHANNEL, }, packetHandler = Packets.class)
public class SkillCore
{
	@Instance
	public static SkillCore instance;

	@SidedProxy(clientSide = "deity.skillsystem.proxy.ClientProxy", serverSide = "deity.skillsystem.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}