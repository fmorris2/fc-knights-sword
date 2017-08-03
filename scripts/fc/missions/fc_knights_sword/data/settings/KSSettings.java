package scripts.fc.missions.fc_knights_sword.data.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scripts.fc.framework.quest.InvBankBool;
import scripts.fc.framework.quest.InvBankBool.TYPE;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.QuestBool;
import scripts.fc.framework.quest.QuestState;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.missions.fc_knights_sword.FCKnightsSword;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;

public enum KSSettings
{
	START_QUEST
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 0, true, Order.EQUALS)
		)
	),
	
	RELDO_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 1, true, Order.EQUALS)
		)
	),
	
	THURGO_FIRST_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 2, true, Order.EQUALS)
		)
	),
	
	THURGO_SECOND_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 3, true, Order.EQUALS)
		)
	),
	
	SQUIRE_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 4, true, Order.EQUALS)
		)
	),
	
	GET_PORTRAIT
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 5, true, Order.EQUALS),
			new InvBankBool(KSItemReqs.PORTRAIT, 1, TYPE.NOT_IN_EITHER, true)
		)
	),
	
	THURGO_THIRD_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 5, true, Order.EQUALS),
			new InvBankBool(KSItemReqs.PORTRAIT, 1, TYPE.IN_ONE, true)
		)
	),
	
	MINE_BLURITE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 6, true, Order.EQUALS),
			new InvBankBool(KSItemReqs.BLURITE, 1, TYPE.IN_ONE, false),
			new InvBankBool(KSItemReqs.BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true)
		)
	),
	
	THURGO_FOURTH_DIALOGUE
	(
		new QuestState
		(
				new SettingBool(FCKnightsSword.SETTING, 6, true, Order.EQUALS),
				new InvBankBool(KSItemReqs.BLURITE, 1, TYPE.IN_ONE, true),
				new InvBankBool(KSItemReqs.BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true)
		)
	),
	
	TURN_IN_QUEST
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 6, true, Order.EQUALS),
			new InvBankBool(KSItemReqs.BLURITE_SWORD, 1, TYPE.IN_ONE, true)
		)
	),
	
	QUEST_COMPLETE
	(
		new QuestState
		(
			new SettingBool(FCKnightsSword.SETTING, 7, true, Order.EQUALS)
		)
	);
	
	private QuestState[] states;
	
	KSSettings(QuestState... states)
	{
		this.states = states;
	}
	
	public boolean isValid()
	{
		return Arrays.stream(states).allMatch(s -> s.validate());
	}
	
	public QuestBool[] getBools()
	{
		List<QuestBool> bools = new ArrayList<>();
		Arrays.stream(states).forEach(s -> Arrays.stream(s.getBools()).forEach(b -> bools.add(b)));
		return bools.toArray(new QuestBool[bools.size()]);
	}
}
