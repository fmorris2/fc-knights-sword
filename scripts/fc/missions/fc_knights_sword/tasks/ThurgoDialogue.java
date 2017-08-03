package scripts.fc.missions.fc_knights_sword.tasks;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;

public abstract class ThurgoDialogue extends Task
{
	private static final long serialVersionUID = -967414109579531122L;
	
	private static final Positionable TILE = new RSTile(3000, 3145, 0);
	private static final int RADIUS = 10;
	
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(TILE) > RADIUS)
			return Travel.webWalkTo(TILE, FCConditions.withinDistanceOfTile(TILE, RADIUS));
		
		NpcDialogue dialogue = new NpcDialogue("Talk-to", "Thurgo", 10, 1,1);
		dialogue.setIgnoreChatName(true);
		return dialogue.execute();
	}
}
