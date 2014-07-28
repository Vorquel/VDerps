package vorquel.mod.vderps.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vorquel.mod.vderps.helper.Log;


public class ItemToolBucket extends ItemMultiVD {
	
	public ItemToolBucket() {
		super("toolBucket", "drainer", "waterInf", "cobbleInf", "stoneInf");
		this.maxStackSize = 1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		switch(stack.getItemDamage()) {
		case 2:
			return placeBlock(Blocks.cobblestone, player, world, x, y, z, side);
		case 3:
			return placeBlock(Blocks.stone, player, world, x, y, z, side);
		default:
			Log.error(String.format("Item %s not yet implemented", getUnlocalizedName(stack)));
			return false;
		}
	}
	
	private boolean placeBlock(Block block, EntityPlayer player, World world, int x, int y, int z, int side) {
		if(world.isRemote) return true;
		if(!player.capabilities.allowEdit) return false;
		if(!world.getBlock(x, y, z).isReplaceable(world, x, y, z)) {
			switch(side) {
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
}
