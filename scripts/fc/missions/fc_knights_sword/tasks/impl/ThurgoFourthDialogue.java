package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.ThurgoDialogue;

public class ThurgoFourthDialogue extends ThurgoDialogue implements ItemsRequiredTask
{
	private static final long serialVersionUID = 1L;

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, KSItemReqs.BLURITE),
			new FCItem(2, false, KSItemReqs.IRON_BAR)
		};
	}

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.THURGO_FOURTH_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Thurgo fourth dialogue";
	}

}
