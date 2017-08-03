package scripts.fc.missions.fc_knights_sword.tasks.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.items.FCItem;
import scripts.fc.api.skills.mining.MiningUtils;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.worldhopping.FCInGameHopper;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.quest.BankBool;
import scripts.fc.framework.task.FutureTaskPreparer;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.framework.task.SpaceRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_knights_sword.data.KSTasks;
import scripts.fc.missions.fc_knights_sword.data.reqs.KSItemReqs;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;

public class MineBlurite extends Task implements SpaceRequiredTask, ItemsRequiredTask, FutureTaskPreparer
{
	private static final long serialVersionUID = 1L;
	private static final Positionable MINING_TILE = new RSTile(3058, 9564, 0), SAFE_TILE = new RSTile(3056, 9562, 0);
	private static final int RADIUS = 3;
	
	@Override
	public ItemsRequiredTask[] getFutureTasks()
	{
		return new ItemsRequiredTask[]
		{
			(ItemsRequiredTask)KSTasks.THURGO_FOURTH_DIALOGUE.TASK
		};
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		List<FCItem> items = new ArrayList<>();
		items.add(new FCItem(1, false, MiningUtils.getBestUsablePick(true).getItemId()));
		if(Inventory.getCount(KSItemReqs.TROUT) <= 0)
		{
			int troutInBank = BankBool.bankObserver.getCount(KSItemReqs.TROUT);		
			items.add(new FCItem(troutInBank > 6 ? 6 : troutInBank, false, KSItemReqs.TROUT));
		}
		return items.toArray(new FCItem[items.size()]);
	}

	@Override
	public int getSpaceRequired()
	{
		return 1;
	}

	@Override
	public boolean execute()
	{
		if(Combat.isUnderAttack() || Combat.getAttackingEntities().length > 0 || Player.getRSPlayer().isInCombat())
		{
			General.println("Escaping combat... Going to safe tile...");
			if(Travel.webWalkTo(SAFE_TILE) && Timing.waitCondition(FCConditions.withinDistanceOfTile(SAFE_TILE, 1), 1200))
				return FCTiming.waitCondition(() -> !Combat.isUnderAttack(), 4000);
		}
		else if(Player.getPosition().distanceTo(SAFE_TILE) <= 1 && areHostilesNear())
		{
			General.println("Hostiles are near... need to hop...");
			return hop();
		}
		else if(Player.getPosition().distanceTo(MINING_TILE) > RADIUS)
			return Travel.webWalkTo(MINING_TILE);
		else if(Player.getAnimation() != -1)
			General.println("Waiting for mining to complete...");
		else
		{
			General.println("Mining rocks...");
			return new ClickObject("Mine", "Rocks", 6).execute() && FCTiming.waitCondition(() -> Combat.isUnderAttack() || Player.getAnimation() != -1, 3200);
		}
		
		return false;
	}
	
	private boolean hop()
	{
		boolean success = FCInGameHopper.hop(WorldHopper.getRandomWorld(WorldHopper.isMembers(WorldHopper.getWorld())));
		if(!success)
		{
			General.println("Waiting for combat cooldown...");
			General.sleep(2400, 4800);
		}
		
		return success;
	}
	
	private boolean areHostilesNear()
	{
		return Arrays.stream(NPCs.find("Ice warrior", "Ice giant"))
				.anyMatch(n -> n.getPosition().distanceTo(MINING_TILE) < 5);		
	}

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.MINE_BLURITE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Mine blurite";
	}

}
