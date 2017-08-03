package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.SquireDialogue;

public class StartQuest extends SquireDialogue
{
	private static final long serialVersionUID = 2033016167675928780L;

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.START_QUEST.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Start quest";
	}

}
