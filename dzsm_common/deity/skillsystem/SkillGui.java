package deity.skillsystem;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class SkillGui extends GuiScreen
{
	EntityPlayer player;

	public SkillGui(EntityPlayer player)
	{
		this.player = player;
	}
}
