package scripts.fc.missions.fc_knights_sword.tasks;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;

public abstract class SquireDialogue extends Task
{
	private static final long serialVersionUID = 4993369322914713282L;
	
	private static final Positionable TILE = new RSTile(2978, 3342, 0);
	private static final int RADIUS = 10;
	
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(TILE) > RADIUS)
			return Travel.webWalkTo(TILE, FCConditions.withinDistanceOfTile(TILE, RADIUS));
		
		return new NpcDialogue("Talk-to", "Squire", 10, 0,1,0,0).execute();
	}
	
}
