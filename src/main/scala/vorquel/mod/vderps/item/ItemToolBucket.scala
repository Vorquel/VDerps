package vorquel.mod.vderps.item

import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.MovingObjectPosition
import net.minecraft.util.MovingObjectPosition.MovingObjectType
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidRegistry
import vorquel.mod.vderps.helper.Log

object ItemToolBucket extends ItemVD("toolBucket", "drainer", "waterInf", "cobbleInf", "stoneInf", "bridger") {
  this.maxStackSize = 1

  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, xIn: Float, yIn: Float, zIn: Float): Boolean = {
    if(world.isRemote) return true
    if(!player.capabilities.allowEdit) return false
    stack.getItemDamage match {
      case 0 => useDrainer(world, player)
      case 1 => useWaterInf(world, player, x, y, z, side)
      case 2 => placeBlock(Blocks.cobblestone, world, player, x, y, z, side)
      case 3 => placeBlock(Blocks.stone, world, player, x, y, z, side)
      case 4 => useBridger(world, player, x, y, z, xIn, yIn, zIn)
      case _ =>
        Log.error(String.format("Item %s not yet implemented", getUnlocalizedName(stack)))
        false
    }
  }

  private def useDrainer(world: World, player: EntityPlayer): Boolean = {
    val position: MovingObjectPosition = getMovingObjectPositionFromPlayer(world, player, true)
    if(position == null) return false
    if(position.typeOfHit != MovingObjectType.BLOCK) return false
    val x: Int = position.blockX
    val y: Int = position.blockY
    val z: Int = position.blockZ
    val block: Block = world.getBlock(x, y, z)
    if(FluidRegistry.lookupFluidForBlock(block) == null) return false
    placeBlock(Blocks.air, world, player, x, y, z, -1)
  }

  private def useWaterInf(world: World, player: EntityPlayer, x: Int, y: Int, z: Int, side: Int): Boolean = {
    if(world.provider.isHellWorld) {
      world.playSoundEffect((x.asInstanceOf[Float] + 0.5F).asInstanceOf[Double], (y.asInstanceOf[Float] + 0.5F).asInstanceOf[Double], (z.asInstanceOf[Float] + 0.5F).asInstanceOf[Double], "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat - world.rand.nextFloat) * 0.8F)
      return false
    }
    placeBlock(Blocks.flowing_water, world, player, x, y, z, side)
  }

  private def placeBlock(block: Block, world: World, player: EntityPlayer, x: Int, y: Int, z: Int, side: Int): Boolean = {
    if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) {
      side match {
        case -1 =>
        case 0 => y -= 1
        case 1 => y += 1
        case 2 => z -= 1
        case 3 => z += 1
        case 4 => x -= 1
        case 5 => x += 1
        case _ => Log.warn(String.format("Attempted to place block on invalid side %d.", side))
      }
      if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false
    }
    if(!world.canPlaceEntityOnSide(block, x, y, z, false, side, null, new ItemStack(block))) return false
    world.playSoundEffect(x.asInstanceOf[Double] + 0.5, y.asInstanceOf[Double] + 0.5, z.asInstanceOf[Double] + 0.5, block.stepSound.func_150496_b, (block.stepSound.getVolume + 1.0F) / 2.0F, block.stepSound.getPitch * 0.8F)
    world.setBlock(x, y, z, block)
  }

  private def useBridger(world: World, player: EntityPlayer, x: Int, y: Int, z: Int, xIn: Float, yIn: Float, zIn: Float): Boolean = {
    if(world.isRemote) return true
    Log.info(String.format("xIn: %.3f", xIn))
    Log.info(String.format("yIn: %.3f", yIn))
    Log.info(String.format("zIn: %.3f", zIn))
    true
  }
}
