package com.hbm.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public interface IItemHUD {

	void renderHUD(RenderGameOverlayEvent.Pre event, ElementType type, EntityPlayer player, ItemStack stack, EnumHand hand);

}