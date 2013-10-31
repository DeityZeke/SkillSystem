package deity.skillsystem;

import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.FMLLog;
import deity.skillsystem.api.ISkill;

public class SkillRegistry
{
	static BiMap<String, ISkill> registry = HashBiMap.create();

	public static int count()
	{
		return registry.size();
	}

	public static void register(ISkill skill)
	{
		if (registry.containsKey(skill.getName()) || registry.inverse().containsKey(skill))
		{
			FMLLog.warning("Registry already contains an instance of " + skill.getName(), SkillCore.instance);
			return;
		}

		registry.put(skill.getName().toLowerCase(), skill);
	}
	
	public static ISkill getISkill(String skill)
	{
		if (registry.containsKey(skill.toLowerCase()))
			return registry.get(skill.toLowerCase());
		
		return null;
	}

	public static Set<ISkill> values()
	{
		return registry.values();
	}
}