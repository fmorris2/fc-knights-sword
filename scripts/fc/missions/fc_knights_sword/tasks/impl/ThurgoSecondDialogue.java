package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.ThurgoDialogue;

public class ThurgoSecondDialogue extends ThurgoDialogue
{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.THURGO_SECOND_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Thurgo second dialogue";
	}

}
