package scripts.fc.missions.fc_knights_sword.data;

import java.util.Arrays;

import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_knights_sword.tasks.impl.GetPortrait;
import scripts.fc.missions.fc_knights_sword.tasks.impl.MineBlurite;
import scripts.fc.missions.fc_knights_sword.tasks.impl.ReldoDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.SquireSecondDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.StartQuest;
import scripts.fc.missions.fc_knights_sword.tasks.impl.ThurgoFirstDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.ThurgoFourthDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.ThurgoSecondDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.ThurgoThirdDialogue;
import scripts.fc.missions.fc_knights_sword.tasks.impl.TurnInQuest;

public enum KSTasks
{
	START_QUEST(new StartQuest()),
	RELDO_DIALOGUE(new ReldoDialogue()),
	THURGO_FIRST_DIALOGUE(new ThurgoFirstDialogue()),
	THURGO_SECOND_DIALOGUE(new ThurgoSecondDialogue()),
	SQUIRE_SECOND_DIALOGUE(new SquireSecondDialogue()),
	GET_PORTRAIT(new GetPortrait()),
	THURGO_THIRD_DIALOGUE(new ThurgoThirdDialogue()),
	MINE_BLURITE(new MineBlurite()),
	THURGO_FOURTH_DIALOGUE(new ThurgoFourthDialogue()),
	TURN_IN_QUEST(new TurnInQuest());
	
	public final Task TASK;
	KSTasks(Task t)
	{
		TASK = t;
	}
	
	public static Task[] getTasks()
	{
		return Arrays.stream(values())
				.map(ks -> ks.TASK)
				.toArray(Task[]::new);
	}
}