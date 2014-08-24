package vorquel.mod.vderps.recipe

import net.minecraft.block.Block
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.world.World

//TODO this whole thing
class IconedBlockRecipe(stack: ItemStack, params: AnyRef*) extends IRecipe {

  private val output: Block = null
  private val input: Array[Item] = null
  private val width: Int = 0
  private val height: Int = 0

  def this(block: Block, params: AnyRef*) {
    this(new ItemStack(block), params)
  }

  def matches(inventory: InventoryCrafting, world: World): Boolean = {
    false
  }

  def getCraftingResult(p_77572_1_ : InventoryCrafting): ItemStack = {
    null
  }

  def getRecipeSize: Int = {
    input.length
  }

  def getRecipeOutput: ItemStack = {
    new ItemStack(output)
  }
}
