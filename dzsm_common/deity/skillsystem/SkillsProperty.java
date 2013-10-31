package deity.skillsystem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import deity.skillsystem.api.ISkill;

public class SkillsProperty implements IExtendedEntityProperties
{
	public BiMap<String, Skill> skills = HashBiMap.create();
	
	@Override
	public void init(Entity entity, World world)
	{
		for (ISkill skill : SkillRegistry.values())
		{
			if (skills.containsKey(skill.getName().toLowerCase())) 
				continue;

			skills.put(skill.getName().toLowerCase(), new Skill(entity, skill));
		}
	}

	public SkillsProperty()
	{
	}

	public static SkillsProperty get(EntityPlayer owner)
	{
		return (SkillsProperty) owner.getExtendedProperties(DefaultProps.ID);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound prop = new NBTTagCompound();
		
		prop.setInteger("count",  skills.size());
		
		for(Skill skill : skills.values())
		{
			skill.save(prop);
		}
		
		compound.setTag(DefaultProps.ID, prop);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound prop = (NBTTagCompound)compound.getTag(DefaultProps.ID);
		
		int count = prop.getInteger("count");
		
		for (int i = 0; i < count; i++)
		{
			Skill skill = new Skill(prop);
			
			if (skill.config == null)
				continue;
			
			skills.put(skill.config.getName().toLowerCase(), skill);
		}
	}
}