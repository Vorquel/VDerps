package vorquel.mod.vderps.helper

import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import vorquel.mod.vderps.block.{BlockFurnaceMulti, BlockLandingPad, BlockVD}
import vorquel.mod.vderps.handler.{LandingPadHandler, CraftingFixer}
import vorquel.mod.vderps.item.{ItemBootsBucket, ItemToolBucket, ItemVD}

object Init {

  def items() {
    Log.trace("Init.items()")
    registerItem(ItemBootsBucket)
    registerItem(ItemToolBucket)
  }

  private def registerItem(item: ItemVD) {
    GameRegistry.registerItem(item, item.itemName)
  }

  def blocks() {
    Log.trace("Init.blocks()")
    registerBlock(BlockFurnaceMulti)
    registerBlock(BlockLandingPad)
  }

  private def registerBlock(block: BlockVD) {
    GameRegistry.registerBlock(block, block.blockName)
  }

  def recipes() {
    Log.trace("Init.recipes()")
    GameRegistry.addShapedRecipe(ItemBootsBucket, "a", "b", "c", 'a', Items.leather_boots, 'b', Items.golden_boots, 'c', Items.bucket)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 0), "a", "b", 'a', Blocks.dirt, 'b', Items.bucket)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 0), "a", "b", 'a', Blocks.sand, 'b', Items.bucket)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 0), "a", "b", 'a', Blocks.gravel, 'b', Items.bucket)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 1), "a a", " b ", 'a', Items.water_bucket, 'b', Items.bucket)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 2), "a b", " c ", 'a', Items.water_bucket, 'b', Items.lava_bucket, 'c', Blocks.cobblestone)
    GameRegistry.addShapedRecipe(new ItemStack(ItemToolBucket, 1, 3), "a b", " c ", 'a', new ItemStack(ItemToolBucket, 1, 1), 'b', Items.lava_bucket, 'c', Blocks.stone)
  }

  def handlers() {
    Log.trace("Init.handlers()")
    FMLCommonHandler.instance.bus.register(CraftingFixer)
    MinecraftForge.EVENT_BUS.register(LandingPadHandler)
  }
}
