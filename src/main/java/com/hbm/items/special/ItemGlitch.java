package com.hbm.items.special;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.effect.EntityVortex;
import com.hbm.entity.missile.EntityMIRV;
import com.hbm.entity.projectile.EntityBoxcar;
import com.hbm.explosion.ExplosionChaos;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;
import com.hbm.lib.ModDamageSource;
import com.hbm.main.MainRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemGlitch extends Item {

	public ItemGlitch(String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.maxStackSize = 1;
        this.setMaxDamage(1);
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		stack.damageItem(5, player);
		
		if(!world.isRemote)
			switch(itemRand.nextInt(31)) {
			case 0:
				player.sendMessage(new TextComponentTranslation("Sorry nothing."));
				break;
			case 1:
				player.sendMessage(new TextComponentTranslation("Prometheus was punished by the gods by giving the gift of knowledge to man. He was cast into the bowels of the earth and pecked by birds."));
				break;
			case 2:
				player.attackEntityFrom(ModDamageSource.radiation, 1000);
				break;
			case 3:
				player.attackEntityFrom(ModDamageSource.boxcar, 1000);
				break;
			case 4:
				player.attackEntityFrom(ModDamageSource.blackhole, 1000);
				break;
			case 5:
				player.inventory.addItemStackToInventory(new ItemStack(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 6:
				for(int i = 0; i < 3; i++)
					player.inventory.addItemStackToInventory(new ItemStack(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 7:
				for(int i = 0; i < 10; i++)
					player.inventory.addItemStackToInventory(new ItemStack(ModBlocks.block_meteor_treasure.getItemDropped(ModBlocks.block_meteor_treasure.getDefaultState(), itemRand, 0)));
				break;
			case 8:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.ammo_container, 10));
				player.sendMessage(new TextComponentTranslation("Oh, and by the way: The polaroid shifts reality. Things can be different if the polaroid is broken."));
				break;
			case 9:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.nuke_advanced_kit, 1));
				break;
			case 10:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.nuke_starter_kit, 1));
				break;
			case 11:
				EntityBoxcar pip = new EntityBoxcar(world);
				pip.posX = player.posX;
				pip.posY = player.posY + 50;
				pip.posZ = player.posZ;
				world.spawnEntity(pip);
				break;
			case 12:
				for(int i = 0; i < 10; i++) {
					EntityBoxcar pippo = new EntityBoxcar(world);
					pippo.posX = player.posX + itemRand.nextGaussian() * 25;
					pippo.posY = player.posY + 50;
					pippo.posZ = player.posZ + itemRand.nextGaussian() * 25;
					world.spawnEntity(pippo);
				}
				break;
			case 13:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.gun_revolver_pip));
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.bottle_rad));
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.geiger_counter));
				player.sendMessage(new TextComponentTranslation("Have some free stuff. You'll need it for that one cryptic achievement."));
				player.sendMessage(new TextComponentTranslation("§4Sororicide"));
				break;
			case 14:
				player.inventory.dropAllItems();
				ExplosionChaos.burn(world, new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ), 5);
				break;
			case 15:
				for(int i = 0; i < 36; i++)
					player.inventory.addItemStackToInventory(new ItemStack(Blocks.DIRT, 64));
				break;
			case 16:
				player.sendMessage(new TextComponentTranslation("v yvxr lbhe nggvghqr!"));
				break;
			case 17:
				player.sendMessage(new TextComponentTranslation("89% of magic tricks are not magic. Technically, they are sorcery."));
				break;
			case 18:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.gun_lever_action));
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.ammo_20gauge, 12));
				player.sendMessage(new TextComponentTranslation("Here ya go."));
				break;
			case 19:
				player.sendMessage(new TextComponentTranslation("Ë"));
				break;
			case 20:
				player.sendMessage(new TextComponentTranslation("Good day, I am text"));
				break;
			case 21:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.missile_nuclear));
				player.sendMessage(new TextComponentTranslation("73616d706c652074657874!"));
				break;
			case 22:
				player.sendMessage(new TextComponentTranslation("Budget cuts, no effect for you."));
				break;
			case 23:
				player.sendMessage(new TextComponentTranslation("oof"));
				break;
			case 24:
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("Tank!"));
				break;
			case 25:
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("More devastating than a falling boxcar!"));
				break;
			case 26:
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60 * 20, 9));
				player.sendMessage(new TextComponentTranslation("Ha!"));
				break;
			case 27:
				EntityVortex vortex = new EntityVortex(world, 2.5F);
				vortex.posX = player.posX;
				vortex.posY = player.posY - 15;
				vortex.posZ = player.posZ;
				world.spawnEntity(vortex);
				break;
			case 28:
				EntityMIRV mirv = new EntityMIRV(world);
				mirv.posX = player.posX;
				mirv.posY = player.posY + 100;
				mirv.posZ = player.posZ;
				world.spawnEntity(mirv);
				player.sendMessage(new TextComponentTranslation("Watch your head!"));
				break;
			case 29:
				ExplosionLarge.spawnBurst(world, player.posX, player.posY, player.posZ, 27, 3);
				player.sendMessage(new TextComponentTranslation("Bam!"));
				break;
			case 30:
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.plate_saturnite));
				player.sendMessage(new TextComponentTranslation("It's dangerous to go alone, take this!"));
				break;
			}
		
		player.inventoryContainer.detectAndSendChanges();
		
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		list.add("It's a gamble!");
		list.add("");
		switch(MainRegistry.polaroidID) {
		case 1: 
			list.add("Click-click-click!");
			break;
		case 2: 
			list.add("Creek!");
			break;
		case 3: 
			list.add("Bzzzt!");
			break;
		case 4: 
			list.add("TS staring off into space.");
			break;
		case 5: 
			list.add("BANG!!");
			break;
		case 6: 
			list.add("Woop!");
			break;
		case 7: 
			list.add("Poow!");
			break;
		case 8: 
			list.add("Pft!");
			break;
		case 9: 
			list.add("GF fgnevat bss vagb fcnpr.");
			break;
		case 10: 
			list.add("Backup memory #8 on 1.44 million bytes.");
			break;
		case 11: 
			list.add("PTANG!");
			break;
		case 12: 
			list.add("Bzzt-zrrt!");
			break;
		case 13: 
			list.add("Clang, click-brrthththrtrtrtrtrtr!");
			break;
		case 14: 
			list.add("KABLAM!");
			break;
		case 15: 
			list.add("PLENG!");
			break;
		case 16: 
			list.add("Wheeeeeeee-");
			break;
		case 17: 
			list.add("Thump.");
			break;
		case 18: 
			list.add("BANG! Choo-chooo! B A N G ! ! !");
			break;
		}
	}
}
