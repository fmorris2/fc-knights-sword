package scripts.fc.missions.fc_knights_sword;

import java.util.Arrays;
import java.util.LinkedList;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.quest.QuestScriptManager;
import scripts.fc.framework.requirement.Requirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.framework.threads.FCFoodThread;
import scripts.fc.missions.fc_knights_sword.data.KSTasks;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSMiningReq;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;

public class FCKnightsSword extends QuestScriptManager
{
	public static final String QUEST_NAME = "The Knight's Sword";
	public static final int SETTING = 122;
	private final FCFoodThread FOOD_THREAD;
	
	public FCKnightsSword(FCMissionScript fcScript)
	{
		super(fcScript);
		FOOD_THREAD = new FCFoodThread(60, 65, new FCItem(1, false, KSItemReqs.TROUT));
		FOOD_THREAD.start();
	}

	@Override
	public boolean canStart()
	{
		return true;
	}

	@Override
	public boolean hasReachedEndingCondition()
	{
		return KSSettings.QUEST_COMPLETE.isValid();
	}

	@Override
	public String getMissionName()
	{
		return "FC Knight's Sword";
	}

	@Override
	public String getEndingMessage()
	{
		return "FC Knight's Sword has ended";
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[]{};
	}

	@Override
	public void resetStatistics()
	{}

	@Override
	public Requirement[] getRequirements()
	{
		return new Requirement[]{new KSMiningReq(missionScript), new KSItemReqs(missionScript)};
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(KSTasks.getTasks()));
	}
	
	public String toString()
	{
		return QUEST_NAME;
	}

	@Override
	public int getQuestPointReward() {
		return 1;
	}

}
