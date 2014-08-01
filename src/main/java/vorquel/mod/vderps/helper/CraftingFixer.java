package vorquel.mod.vderps.helper;

import java.util.ArrayDeque;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vorquel.mod.vderps.item.ItemVD;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class CraftingFixer {
	
	public static CraftingFixer that = new CraftingFixer();
	
	private HashMap<EntityPlayer, ArrayDeque<Item>> toRemove = new HashMap();
	
	private CraftingFixer() {}
	
	@SubscribeEvent
	public void detectCrafting(ItemCraftedEvent event) {
		if(event.player==null) return;
		if(!(event.player instanceof EntityPlayerMP)) return;
		if(!(event.crafting.getItem() instanceof ItemVD)) return;
		if(!toRemove.containsKey(event.player)) {
			ArrayDeque<Item> deque = new ArrayDeque();
			toRemove.put(event.player, deque);
		}
		for(int i=0; i<event.craftMatrix.getSizeInventory(); ++i) {
			ItemStack stack = event.craftMatrix.getStackInSlot(i);
			if(stack == null) continue;
			if(!stack.getItem().hasContainerItem(stack)) continue;
			toRemove.get(event.player).add(stack.getItem().getContainerItem());
		}
	}
	
	@SubscribeEvent
	public void removeItem(PlayerTickEvent event) {
		if(event.side.equals(Side.CLIENT)) return;
		if(!toRemove.containsKey(event.player)) return;
		if(toRemove.get(event.player).isEmpty()) return;
		Item item = toRemove.get(event.player).peek();
		if(event.player.inventory.hasItem(item))
			if(event.player.inventory.consumeInventoryItem(item))
				toRemove.get(event.player).remove();
	}
}
