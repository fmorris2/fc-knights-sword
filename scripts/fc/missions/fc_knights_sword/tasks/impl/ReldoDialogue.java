package scripts.fc.missions.fc_knights_sword.tasks.impl;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_knights_sword.data.settings.KSSettings;

public class ReldoDialogue extends Task
{
	private static final long serialVersionUID = 5427700579996398141L;

	private static final Positionable TILE = new RSTile(3210, 3494, 0);
	private static final int RADIUS = 10;
	
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(TILE) > RADIUS)
			return Travel.webWalkTo(TILE, FCConditions.withinDistanceOfTile(TILE, RADIUS));
		
		return new NpcDialogue("Talk-to", "Reldo", 10, 3).execute();
	}

	@Override
	public boolean shouldExecute()
	{
		return KSSettings.RELDO_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Reldo dialogue";
	}

}
