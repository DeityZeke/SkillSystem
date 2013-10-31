package deity.skillsystem.api;

import net.minecraft.block.Block;

public interface IHarvestSkill extends ISkill
{
	public void use(Block block);
}
