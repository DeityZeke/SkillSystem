package deity.skillsystem.api;

import net.minecraft.entity.Entity;

public interface ICombatSkill extends ISkill
{
	public void use(Entity against);
}