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

object ItemToolBucket extends ItemMultiVD("toolBucket", "drainer", "waterInf", "cobbleInf", "stoneInf", "bridger") {
  this.maxStackSize = 1

  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, xIn: Float, yIn: Float, zIn: Float): Boolean = {
    if(world.isRemote) return true
    if(!player.capabilities.allowEdit) return false
    stack.getItemDamage match {
      case 0 => useDrainer(world, player)
      case 1 => useWaterInf(world, player, x, y, z, side)
      case 2 => placeBlock(Blocks.cobblestone, world, player, x, y, z, side)
      case 3 => placeBlock(Blocks.stone, world, player, x, y, z, side)
      case 4 => useBridger(world, player, x, y, z, xIn, yIn, zIn, side)
      case _ =>
        Log.error(s"Item ${getUnlocalizedName(stack)} not yet implemented")
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
      world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat - world.rand.nextFloat) * 0.8F)
      return false
    }
    placeBlock(Blocks.flowing_water, world, player, x, y, z, side)
  }

  private def placeBlock(block: Block, world: World, player: EntityPlayer, x: Int, y: Int, z: Int, side: Int): Boolean = {
    if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z))
      side match {
        case -1 =>
        case 0 => return placeBlock(block, world, player, x, y-1, z, -1)
        case 1 => return placeBlock(block, world, player, x, y+1, z, -1)
        case 2 => return placeBlock(block, world, player, x, y, z-1, -1)
        case 3 => return placeBlock(block, world, player, x, y, z+1, -1)
        case 4 => return placeBlock(block, world, player, x-1, y, z, -1)
        case 5 => return placeBlock(block, world, player, x+1, y, z, -1)
        case s => Log.warn(s"Attempted to place block on invalid side $s.")
      }
    if(!world.canPlaceEntityOnSide(block, x, y, z, false, side, null, new ItemStack(block))) return false
    world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, block.stepSound.func_150496_b, (block.stepSound.getVolume + 1.0F) / 2.0F, block.stepSound.getPitch * 0.8F)
    world.setBlock(x, y, z, block)
  }

  private def useBridger(world: World, player: EntityPlayer, x: Int, y: Int, z: Int, xIn: Float, yIn: Float, zIn: Float, side: Int): Boolean = {
    if(world.isRemote) return true
    val block: Block = world.getBlock(x,y,z)
    if(block.hasTileEntity(world,x,y,z)) return false
    val lo = .25
    val hi = .75
    //if(blah blah blah) {do stuff} //TODO check for block in inventory here
    if((xIn>lo && xIn<hi && yIn>lo && yIn<hi) || (xIn>lo && xIn<hi && zIn>lo && zIn<hi) || (yIn>lo && yIn<hi && zIn>lo && zIn<hi))
      return tower(world, player, block, x, y, z, side)
    val (pair, sides) =
    side match {
      case 0 | 1 => ((xIn, zIn), (4,5,2,3))
      case 2 | 3 => ((xIn, yIn), (4,5,0,1))
      case 4 | 5 => ((yIn, zIn), (0,1,2,3))
      case s => Log.error(s"Invalid side $s found in bridger."); return false
    }
    if(pair._1 > pair._2) {
      if(pair._1 > 1-pair._2) tower(world, player, block, x, y, z, sides._1)
      else                    tower(world, player, block, x, y, z, sides._4)
    } else {
      if(pair._1 > 1-pair._2) tower(world, player, block, x, y, z, sides._3)
      else                    tower(world, player, block, x, y, z, sides._2)
    }
  }

  private def tower(world: World, player: EntityPlayer, block: Block, x: Int, y: Int, z: Int, side: Int, range: Int = 16): Boolean = {
    if(range == 0) return false
    val otherBlock: Block = world.getBlock(x,y,z)
    if(otherBlock == block)
      side match {
        case 0 => return tower(world, player, block, x, y+1, z, side, range-1)
        case 1 => return tower(world, player, block, x, y-1, z, side, range-1)
        case 2 => return tower(world, player, block, x, y, z+1, side, range-1)
        case 3 => return tower(world, player, block, x, y, z-1, side, range-1)
        case 4 => return tower(world, player, block, x+1, y, z, side, range-1)
        case 5 => return tower(world, player, block, x-1, y, z, side, range-1)
        case s => Log.error(s"Invalid side $s found in bridger."); return false
      }
    if(otherBlock.isReplaceable(world, x, y, z)) {
      placeBlock(block, world, player, x, y, z, -1)
      //TODO remove block from inventory
    } else false
  }
}
