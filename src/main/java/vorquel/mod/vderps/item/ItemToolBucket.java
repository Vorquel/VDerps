package vorquel.mod.vderps.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import vorquel.mod.vderps.helper.Log;


public class ItemToolBucket extends ItemMultiVD {
	
	public ItemToolBucket() {
		super("toolBucket", "drainer", "waterInf", "cobbleInf", "stoneInf", "bridger");
		this.maxStackSize = 1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xIn, float yIn, float zIn) {
		if(world.isRemote) return true;
		if(!player.capabilities.allowEdit) return false;
		switch(stack.getItemDamage()) {
		case 0:
			return useDrainer(world, player);
		case 1:
			return useWaterInf(world, player, x, y, z, side);
		case 2:
			return placeBlock(Blocks.cobblestone, world, player, x, y, z, side);
		case 3:
			return placeBlock(Blocks.stone, world, player, x, y, z, side);
		case 4:
			return useBridger(world, player, x, y, z, xIn, yIn, zIn);
		default:
			Log.error(String.format("Item %s not yet implemented", getUnlocalizedName(stack)));
			return false;
		}
	}
	
	private boolean useDrainer(World world, EntityPlayer player) {
		MovingObjectPosition position = getMovingObjectPositionFromPlayer(world, player, true);
		if(position == null) return false;
		if(position.typeOfHit != MovingObjectType.BLOCK) return false;
		int x = position.blockX;
		int y = position.blockY;
		int z = position.blockZ;
		Block block = world.getBlock(x, y, z);
		if(FluidRegistry.lookupFluidForBlock(block) == null) return false;
		return placeBlock(Blocks.air, world, player, x, y, z, -1);
	}

	private boolean useWaterInf(World world, EntityPlayer player, int x, int y, int z, int side) {
		if(world.provider.isHellWorld) {
			world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			for (int l = 0; l < 8; ++l) {
				world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
			}
			return false;
		}
		return placeBlock(Blocks.flowing_water, world, player, x, y, z, side);
	}
	
	private boolean placeBlock(Block block, World world, EntityPlayer player, int x, int y, int z, int side) {
		if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) {
			switch(side) {
				case -1: break;
				case 0: --y; break;
				case 1: ++y; break;
				case 2: --z; break;
				case 3: ++z; break;
				case 4: --x; break;
				case 5: ++x; break;
				default: Log.warn(String.format("Attempted to place block on invalid side %d.", side));
			}
			if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) return false;
		}
		if(!world.canPlaceEntityOnSide(block, x, y, z, false, side, null, new ItemStack(block))) return false;
		world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
		return world.setBlock(x, y, z, block);
	}
	
	private boolean useBridger(World world, EntityPlayer player, int x, int y, int z, float xIn, float yIn, float zIn) {
		if(world.isRemote) return true;
		Log.info(String.format("xIn: %.3f", xIn));
		Log.info(String.format("yIn: %.3f", yIn));
		Log.info(String.format("zIn: %.3f", zIn));
		return true;
	}
}
