package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.SquireDialogue;

public class TurnInQuest extends SquireDialogue implements ItemsRequiredTask
{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.TURN_IN_QUEST.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Turn in quest";
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, KSItemReqs.BLURITE_SWORD)
		};
	}

}
