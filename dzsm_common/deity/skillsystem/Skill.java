package deity.skillsystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import deity.skillsystem.api.ISkill;

public class Skill
{
	public final Entity owner;
	public final ISkill config;
	
	public Skill(Entity owner, ISkill config)
	{
		this.owner = owner;
		this.config = config;
	}
	
	public void save(NBTTagCompound prop)
	{
		prop.setInteger("owner",  owner.entityId);
		
		prop.setString("name", config.getName());
	}
	
	public Skill(NBTTagCompound prop)
	{
		int skillOwner = prop.getInteger("owner");
		owner = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(skillOwner);
		
		String name = prop.getString("name");
		config = SkillRegistry.getISkill(name);
	}
}