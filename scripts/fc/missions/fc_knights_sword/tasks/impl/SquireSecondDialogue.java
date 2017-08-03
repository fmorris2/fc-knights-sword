package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.SquireDialogue;

public class SquireSecondDialogue extends SquireDialogue
{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.SQUIRE_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Squire second dialogue";
	}

}
