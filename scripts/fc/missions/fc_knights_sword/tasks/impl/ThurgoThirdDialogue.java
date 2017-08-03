package scripts.fc.missions.fc_knights_sword.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.FutureTaskPreparer;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_knights_sword.data.KSTasks;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;
import scripts.fc.missions.fc_knights_sword.tasks.ThurgoDialogue;

public class ThurgoThirdDialogue extends ThurgoDialogue implements ItemsRequiredTask, FutureTaskPreparer
{
	private static final long serialVersionUID = 1L;

	@Override
	public ItemsRequiredTask[] getFutureTasks()
	{
		return new ItemsRequiredTask[]
		{
			(ItemsRequiredTask)KSTasks.MINE_BLURITE.TASK, 
			(ItemsRequiredTask)KSTasks.THURGO_FOURTH_DIALOGUE.TASK
		};
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, KSItemReqs.PORTRAIT)
		};
	}

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.THURGO_THIRD_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Thurgo third dialogue";
	}

}
