package plasticraft.entity;

import plasticraft.PlastiCraft;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityClone extends EntityLiving implements IInventory{
	
	private boolean printed;
	private ItemStack[] items;
	
	@Override
	public boolean interact(EntityPlayer player){
		if(!this.worldObj.isRemote){
			FMLNetworkHandler.openGui(player, PlastiCraft.instance, 1, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
		}
		return true;
	}
	
	public EntityClone(World par1World, boolean printed) {
		super(par1World);
		this.printed = printed;
		items = new ItemStack[2];
	}

	@Override
	public int getSizeInventory() {
		if(printed){
			return 2;
		}
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return items[var1];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		if(itemstack != null){
			if(itemstack.stackSize <= count){
				this.setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		this.setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		items[i] = stack;
		
		if(stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = this.getInventoryStackLimit();
			this.items[i] = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return "Clone";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5) <= 64;
		}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

}