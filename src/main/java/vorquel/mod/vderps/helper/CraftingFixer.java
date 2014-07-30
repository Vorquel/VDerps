package vorquel.mod.vderps.helper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CraftingFixer {
	
	public static CraftingFixer that = new CraftingFixer();
	
	private boolean crafted = false;
	
	@SubscribeEvent
	public void detectCrafted(ItemCraftedEvent event) {
		Log.info("Item Crafted");
		crafted = true;
	}
	
	@SubscribeEvent
	public void removeItems(PlayerTickEvent event) {
		if(!crafted) return;
		Log.info("Player Tick");
		crafted = false;
	}
}
