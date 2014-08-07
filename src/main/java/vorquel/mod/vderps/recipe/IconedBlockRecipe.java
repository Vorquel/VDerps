package vorquel.mod.vderps.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class IconedBlockRecipe implements IRecipe {
	
	private Block output;
	private Item[] input;
	private int width;
	private int height;
	
	public IconedBlockRecipe(Block block, Object... params) {this(new ItemStack(block), params);}
	public IconedBlockRecipe(ItemStack stack, Object... params) {
		// TODO
	}
	
	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getRecipeSize() {
		return input.length;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(output);
	}
}
