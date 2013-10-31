package deity.skillsystem.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import deity.skillsystem.DefaultProps;
import deity.skillsystem.SkillsProperty;

public class EventSubs
{
	@ForgeSubscribe
	public void entityConstruction(EntityEvent.EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			event.entity.registerExtendedProperties(DefaultProps.ID, new SkillsProperty());
		}
	}
}