package scripts.fc.missions.fc_knights_sword.data.reqs;

import scripts.fc.api.skills.mining.data.Pickaxe;
import scripts.fc.framework.quest.InvBankBool;
import scripts.fc.framework.quest.InvBankBool.TYPE;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.framework.requirement.item.ItemRequirement;
import scripts.fc.framework.requirement.item.ReqItem;
import scripts.fc.framework.requirement.item.SingleReqItem;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fc_knights_sword.FCKnightsSword;

public class KSItemReqs extends ItemRequirement
{
	public static final int REDBERRY_PIE = 2325, IRON_BAR = 2351, TROUT = 333, PORTRAIT = 666,
				BLURITE = 668, BLURITE_SWORD = 667;
	
	public KSItemReqs(FCMissionScript script)
	{
		super(script);
	}

	@Override
	public ReqItem[] getReqItems()
	{
		return new ReqItem[]
		{
			//USABLE PICKAXE - IF WE DON'T HAVE IT, RESORT TO BUYING AN IRON PICK
			pickaxeReq()
				.when
				(
					new SettingBool(FCKnightsSword.SETTING, 6, true, Order.BEFORE_EQUALS)
					.and(new InvBankBool(BLURITE, 1, TYPE.NOT_IN_EITHER, true))
					.and(new InvBankBool(BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true))
				),
				
			//REDBERRY PIE
			new SingleReqItem(REDBERRY_PIE, 1, true, true)
				.when(new SettingBool(FCKnightsSword.SETTING, 2, true, Order.BEFORE_EQUALS)),
				
			//IRON BARS
			new SingleReqItem(IRON_BAR, 2, true, true)
				.when
				(
					new SettingBool(FCKnightsSword.SETTING, 6, true, Order.BEFORE_EQUALS)
					.and(new InvBankBool(BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true))
				),
			
			//TROUT
			new SingleReqItem(TROUT, 6, true, true)
				.when
				(
					new SettingBool(FCKnightsSword.SETTING, 6, true, Order.BEFORE_EQUALS)
					.and(new InvBankBool(BLURITE, 1, TYPE.NOT_IN_EITHER, true))
					.and(new InvBankBool(BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true))
					.and(new InvBankBool(TROUT, 3, TYPE.NOT_IN_EITHER, true))
				),
			
		};
	}
	
	private ReqItem pickaxeReq()
	{
		ReqItem req = new SingleReqItem(Pickaxe.BRONZE.getItemId(), 1, false, false);
		for(int i = 1; i < Pickaxe.DRAGON.ordinal(); i++)
			req = req.or(new SingleReqItem(Pickaxe.values()[i].getItemId(), 1, i == Pickaxe.IRON.ordinal(), i == Pickaxe.IRON.ordinal()));
		
		return req.when(new InvBankBool(BLURITE, 1, TYPE.NOT_IN_EITHER, true).and(new InvBankBool(BLURITE_SWORD, 1, TYPE.NOT_IN_EITHER, true)));
			
	}

}
