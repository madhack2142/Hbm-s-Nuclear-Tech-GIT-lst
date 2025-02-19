package api.hbm.energy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IBatteryItem {

	void chargeBattery(ItemStack stack, long i);
	void setCharge(ItemStack stack, long i);
	void dischargeBattery(ItemStack stack, long i);
	long getCharge(ItemStack stack);
	long getMaxCharge();
	long getChargeRate();
	long getDischargeRate();
	
	/** Returns a string for the NBT tag name of the long storing power */
	default String getChargeTagName() {
		return "charge";
	}

	/** Returns a string for the NBT tag name of the long storing power */
	static String getChargeTagName(ItemStack stack) {
		return ((IBatteryItem) stack.getItem()).getChargeTagName();
	}

	/** Returns an empty battery stack from the passed ItemStack, the original won't be modified */
	static ItemStack emptyBattery(ItemStack stack) {
		if(stack != null && stack.getItem() instanceof IBatteryItem) {
			String keyName = getChargeTagName(stack);
			ItemStack stackOut = stack.copy();
			NBTTagCompound tag;
			if(stack.hasTagCompound())
				tag = stack.getTagCompound();
			else
				tag = new NBTTagCompound();
			tag.setLong(keyName, 0);
			stackOut.setTagCompound(tag);
			return stackOut.copy();
		}
		return null;
	}

	/** Returns an empty battery stack from the passed Item */
	static ItemStack emptyBattery(Item item) {
		return item instanceof IBatteryItem ? emptyBattery(new ItemStack(item)) : null;
	}
}
