package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.ThurgoDialogue;

public class ThurgoFirstDialogue extends ThurgoDialogue implements ItemsRequiredTask
{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.THURGO_FIRST_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Thurgo first dialogue";
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, KSItemReqs.REDBERRY_PIE)
		};
	}

}
