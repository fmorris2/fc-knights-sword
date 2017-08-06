package scripts.fc.missions.fc_knights_sword.data.reqs;

import org.tribot.api2007.Skills.SKILLS;

import scripts.fc.api.skills.GatheringMode;
import scripts.fc.api.skills.ProgressionType;
import scripts.fc.framework.WorldHopSettings;
import scripts.fc.framework.goal.impl.SkillGoal;
import scripts.fc.framework.mission.Mission;
import scripts.fc.framework.requirement.SkillRequirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fcmining.FCMining;

public class KSMiningReq extends SkillRequirement
{
	private static final SKILLS SKILL = SKILLS.MINING;
	private static final int REQ = 10;
	
	public KSMiningReq(FCMissionScript script)
	{
		super(script, SKILL, REQ, new Mission[]{getMission(script)});
	}
	
	private static Mission getMission(FCMissionScript script)
	{
		WorldHopSettings hopSettings = new WorldHopSettings(25, 20, false);
		hopSettings.resourceStolen = 30;
		
		return new FCMining(script, true, true, hopSettings, null, GatheringMode.BANK, null, ProgressionType.EXPERIENCE, new SkillGoal(SKILL, REQ));
	}

}
