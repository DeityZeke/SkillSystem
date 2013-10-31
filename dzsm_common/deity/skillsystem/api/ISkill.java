package deity.skillsystem.api;

public interface ISkill
{
	public String getName();
	public String getIcon();

	public int maxLevel();
	public int maxSoftLevel();

	public boolean doExpGainMessage();
	public boolean doLevelingEffects();
	public boolean doLevelingMessage();
	
	// To-Do: level requirement registers
}