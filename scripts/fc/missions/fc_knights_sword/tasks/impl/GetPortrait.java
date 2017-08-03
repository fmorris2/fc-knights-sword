package scripts.fc.missions.fc_knights_sword.tasks.impl;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.worldhopping.FCInGameHopper;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.SpaceRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;

public class GetPortrait extends Task implements SpaceRequiredTask
{
	private static final long serialVersionUID = 1L;
	private static final Positionable TILE = new RSTile(2983, 3334, 2);
	private static final int RADIUS = 5, VYVIN_MIN_Y = 3337;
	private static final Filter<RSNPC> IN_ROOM_FILTER = inRoomFilter();
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(TILE) > RADIUS)
			return Travel.webWalkTo(TILE);
		else if(vyvinIsWatching())
			return hop();
		
		return getPortrait();
	}
	
	private boolean getPortrait()
	{
		General.println("Getting portrait...");
		if(cupboardIsOpen())
		{
			General.println("Searching cupboard...");
			if(new ClickObject("Search", "Cupboard", 10).execute() && FCTiming.waitCondition(() -> DialogueThread.areDialogueInterfacesUp(), 4500))
			{
				DialogueThread.doClickToContinue();
				return Timing.waitCondition(FCConditions.inventoryContains("Portrait"), 4500);
			}
		}
		
		General.println("Opening cupboard...");
		return new ClickObject("Open", "Cupboard", 10).execute() && FCTiming.waitCondition(() -> cupboardIsOpen(), 4500);
	}
	
	private boolean cupboardIsOpen()
	{
		return Objects.find(10, Filters.Objects.nameEquals("Cupboard").combine(Filters.Objects.actionsContains("Search"), false)).length > 0;
	}
	
	private boolean vyvinIsWatching()
	{
		return NPCs.findNearest(Filters.NPCs.nameEquals("Sir Vyvin").combine(IN_ROOM_FILTER, false)).length > 0;
	}
	
	private boolean hop()
	{
		General.println("Hopping worlds to escape Sir Vyvin's view...");
		return FCInGameHopper.hop(WorldHopper.getRandomWorld(WorldHopper.isMembers(WorldHopper.getWorld())));
	}
	
	private static Filter<RSNPC> inRoomFilter()
	{
		return new Filter<RSNPC>()
		{
			@Override
			public boolean accept(RSNPC n)
			{
				return n.getPosition().getY() < VYVIN_MIN_Y;
			}	
		};
	}

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.GET_PORTRAIT.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Get portrait";
	}

	@Override
	public int getSpaceRequired()
	{
		return 1;
	}

}
