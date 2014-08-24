package vorquel.mod.vderps.handler

import java.util

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.{PlayerEvent, TickEvent}
import cpw.mods.fml.relauncher.Side
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraft.item.{Item, ItemStack}
import vorquel.mod.vderps.item.ItemToolBucket

object CraftingFixer {

  private val toRemove = new util.HashMap[EntityPlayer, util.ArrayDeque[Item]]

  @SubscribeEvent
  def detectCrafting(event: PlayerEvent.ItemCraftedEvent) {
    if(event.player == null) return
    if(!event.player.isInstanceOf[EntityPlayerMP]) return
    if(event.crafting.getItem != ItemToolBucket) return
    if(!toRemove.containsKey(event.player)) {
      val deque = new util.ArrayDeque[Item]
      toRemove.put(event.player, deque)
    }
    {
      for(i <- 0 until event.craftMatrix.getSizeInventory) {
        val stack: ItemStack = event.craftMatrix.getStackInSlot(i)
        if(stack != null && stack.getItem.hasContainerItem(stack))
          toRemove.get(event.player).add(stack.getItem.getContainerItem)
      }
    }
  }

  @SubscribeEvent
  def removeItem(event: TickEvent.PlayerTickEvent) {
    if(event.side == Side.CLIENT) return
    if(!toRemove.containsKey(event.player)) return
    if(toRemove.get(event.player).isEmpty) return
    val item: Item = toRemove.get(event.player).peek
    if(event.player.inventory.hasItem(item))
      if(event.player.inventory.consumeInventoryItem(item))
        toRemove.get(event.player).remove()
  }
}
