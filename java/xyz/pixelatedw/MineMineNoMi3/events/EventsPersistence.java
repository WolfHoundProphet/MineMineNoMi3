package xyz.pixelatedw.MineMineNoMi3.events;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.BusoshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.KenbunshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;
import xyz.pixelatedw.MineMineNoMi3.items.ItemCoreArmor;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEffects;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class EventsPersistence
{

	/*
	 * onEntityUpdate 
	 * > Job boosts 
	 * > Fall damage nullification for Gomu & Bane 
	 * > Poison placement for Doku 
	 * > Doku nullification of poison damage 
	 * > Extra HP 
	 * > Fishman swimming boost 
	 * > Nullification of swimming for DF users 
	 * > Kilo gliding & falling
	 * > Buso Haki Timer
	 * 
	 * onEntityDeath 
	 * > Removing abilities when the user dies 
	 * > Doriki, Bounty & Belly rewards for killing players/mobs
	 * 
	 * onEntityAttackEvent 
	 * > Lava/Fire damage nullification for Mera & Magu 
	 * > Logia protection
	 * 
	 * onEntityJoinWorld 
	 * > Syncing between dimensions 
	 * > Gives a creation book for users without job/race/faction
	 * 
	 * onDorikiGained 
	 * > Rewards the user with rokushiki/fishman karate based on doriki
	 */	
	
	/** XXX onEntityUpdate */
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			ItemStack heldItem = player.getHeldItem();				
						
			if (heldItem != null)
			{				
				if(heldItem.getItem() == ListMisc.Umbrella && player.worldObj.getBlock((int)player.posX, (int)player.posY - 4, (int)player.posZ) == Blocks.air && !player.capabilities.isCreativeMode)
					player.motionY = -0.05;
				
				/*if(props.getUsedFruit().equals("kilokilo") && heldItem.getItem() == ListAbilities.KILOPRESS)
				{
					if (props.getKilo())
					{
						player.motionY -= 3;
						player.fallDistance = 0;
					}
					if ( !props.getKilo() && !heldItem.getTagCompound().getBoolean("use") )
						player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 20, true));
				}*/
			}
			
			if(!props.getUsedFruit().equals("N/A") && !player.worldObj.isRemote)
			{
				if( WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) || DevilFruitsHelper.hasKairosekiItem(player) || player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock))
						|| player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) || (player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water 
						|| player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water))) )
				{
					if(DevilFruitsHelper.hasKairosekiItem(player))
						player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 48, 0));
					else
						player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 0));
					for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
					{
						if(props.getAbilityFromSlot(i) != null && !props.getAbilityFromSlot(i).equals("n/a") && !props.getAbilityFromSlot(i).isDisabled())
						{ 
							props.getAbilityFromSlot(i).setCooldownActive(true);
							props.getAbilityFromSlot(i).disable(player, true);
						}			
					}
				}
				else
				{
					for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
					{
						if(props.getAbilityFromSlot(i) != null && !props.getAbilityFromSlot(i).equals("n/a") && props.getAbilityFromSlot(i).isDisabled())
						{ 
							props.getAbilityFromSlot(i).setCooldownActive(false);
							props.getAbilityFromSlot(i).disable(player, false);
						}			
					}					
				}
				
			}
						
			if(props.getUsedFruit().equals("hiehie"))
			{
				if( !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) 
						&& !player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) 
						&& !player.isInsideOfMaterial(Material.water) )
				{
					final EntityLivingBase finalPlayer = player;
					for (int x1 = -1; x1 < 2; x1++) 
					for (int y1 = -1; y1 < 0; y1++) 
					for (int z1 = -1; z1 < 2; z1++) 
					{
						Sphere.generate((int) player.posX - 1 + x1, (int) player.posY + y1, (int) player.posZ + z1, 1, new ISphere()
						{						
							public void call(int x, int y, int z)
							{
								if(finalPlayer.worldObj.getBlock(x, y, z) == Blocks.water)
									finalPlayer.worldObj.setBlock(x, y ,z, Blocks.ice);
							}
						});
					}
				}
			}
			
			if( !WyHelper.isBlockNearby(player, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) && !DevilFruitsHelper.hasKairosekiItem(player) 
					&& !player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) && !player.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre)) 
					&& !player.isInsideOfMaterial(Material.water) )
			{
				for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
				{					
					if(props.getAbilityFromSlot(i) != null && !props.getAbilityFromSlot(i).equals("n/a") && props.getAbilityFromSlot(i).isRepeating())
					{ 					
						props.getAbilityFromSlot(i).duringRepeater(player);
					}				
				}
			}
			
			if (props.getUsedFruit().equals("gomugomu") || props.getUsedFruit().equals("banebane") || props.isLogia())
				player.fallDistance = 0;

			if(props.getUsedFruit().equals("gomugomu"))
			{
				if(props.getGear() == 2)
				{
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 25, 1, false));
					if(!player.worldObj.isRemote)
			    		WyNetworkHelper.sendTo(new PacketParticles("gearSecond", player), (EntityPlayerMP) player);
				}
				else if(props.getGear() == 4)
				{
					player.addPotionEffect(new PotionEffect(Potion.jump.id, 25, 2, false));
				}
			}
			
			if (props.getUsedFruit().equals("dokudoku"))
			{
				if (player.isPotionActive(Potion.poison.id))
					player.removePotionEffect(Potion.poison.id);
			}
			
			if ( (player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water))))
			{ 
				if (!props.getUsedFruit().equals("N/A"))
				{
					if(!player.capabilities.isCreativeMode)
						player.motionY -= 5;				
				}				

				if (props.getRace().equals(ID.RACE_FISHMAN) && props.getUsedFruit().equals("N/A"))
				{
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 1));

					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.2D;	
						player.motionZ /= 1.2D;
					}
					else
					{
						player.motionX *= 1.2D;
						player.motionZ *= 1.2D;
					}
				}
			}
			
			if(player.isInsideOfMaterial(Material.lava) && !player.capabilities.isCreativeMode)
			{
				if (props.getUsedFruit().equals("magumagu"))
				{
					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.9D;
						player.motionZ /= 1.9D;
					}
					else
					{
						player.motionX *= 1.9D;
						player.motionZ *= 1.9D;
					}
				}
			}	
			
			boolean hasColaBackpack = false;
			
			for(ItemStack armorStack : player.inventory.armorInventory)
			{
				if(armorStack != null && armorStack.getItem() instanceof ItemCoreArmor && ((ItemCoreArmor)armorStack.getItem()).getName().equals("colabackpack") )
				{
					hasColaBackpack = true;
				}
			}
			
			if(props.getRace().equals(ID.RACE_CYBORG))
			{
				if(hasColaBackpack && !props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() + 200);
					props.setColaBackpack(true);
					
			    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
			    		WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", 1);
			    	
					if(!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				else if(!hasColaBackpack && props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() - 200);
					
					if(props.getCola() > props.getMaxCola())
						props.setCola(props.getMaxCola());
					
					props.setColaBackpack(false);
					
			    	if(!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
			    		WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", -1);
			    	
					if(!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				
			}
			
			
			if(props.getTempPreviousAbility().equals("geppo") || props.getTempPreviousAbility().equals("soranomichi"))
			{
				if(!player.onGround && player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) == Blocks.air)
					player.fallDistance = 0;
				else
				{
					props.setTempPreviousAbility("N/A");
				}
			}
			
			if(props.hasHakiActive())
				props.addHakiTimer();
			else
			{
				if(props.getHakiTimer() > 0)
					props.decHakiTimer();
			}
			
			if(props.getHakiTimer() > 1000)
			{
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 0));
				if(props.getHakiTimer() > 1500 + (props.getDoriki() / 15))
				{
					player.attackEntityFrom(DamageSource.generic, Integer.MAX_VALUE);
				}
			}
			
		}
	}	
	
	/** XXX onLivingDeath */
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setYamiPower(false);

			for(int i = 0; i < 8; i++)
			{
				if(props.getAbilityFromSlot(i) != null)
					props.getAbilityFromSlot(i).reset();
			}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}

		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			EntityLivingBase target = event.entityLiving;

			IAttributeInstance attrAtk = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage);
			IAttributeInstance attrHP = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);

			int rng = player.worldObj.rand.nextInt(3) + 1;
			int plusBounty = 0, plusBelly = 0;		
			double plusDoriki = 0;
			
			boolean targetPlayer = false;
			
			if (target instanceof EntityPlayer)
			{
				ExtendedEntityStats targetprops = ExtendedEntityStats.get(target);

				plusDoriki = (targetprops.getDoriki() / 4) + rng;
				plusBounty = (targetprops.getBounty() / 2) + rng;
				plusBelly = targetprops.getBelly();
				
				targetPlayer = true;
			}
			else
			{
				if (props.getFaction().equals(ID.FACTION_MARINE) && target instanceof MarineData)
					return;
				
				if(target instanceof EntityNewMob)
				{				
					/*plusDoriki = ((EntityNewMob) target).getDorikiPower() + rng;*/
					if((props.getDoriki() / 100) > ((EntityNewMob) target).getDorikiPower())
					{
						plusDoriki = 1 / ( (props.getDoriki() / 100) - ((EntityNewMob) target).getDorikiPower() );
						if(plusDoriki < 1)
							plusDoriki = 1;
					}
					else
						plusDoriki = ((EntityNewMob) target).getDorikiPower();
							
					plusBounty = (((EntityNewMob) target).getDorikiPower() * 2) + rng;	
					plusBelly = ((EntityNewMob) target).getBellyInPockets() + rng;	
					
			    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			    		WyTelemetry.addStat("defeated_" + WyHelper.getFancyName(target.getClass().getSimpleName()).replace("entity", ""), 1);
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{	
						double i = attrAtk.getAttributeValue();
						double j = attrHP.getAttributeValue();		

						plusDoriki = (int) Math.round(((i + j) / 10) / Math.PI) + rng;
						plusBounty = (int) Math.round((i + j) / 10) + rng;
						plusBelly = 1;
					
					}
					else
					{
						plusDoriki = 0;
						plusBounty = 0;
						plusBelly = 1;
					}
				}
					
				if (plusDoriki > 0)
				{
					if (props.getDoriki() + plusDoriki < Values.MAX_DORIKI )
					{
						props.alterDoriki((int) Math.round(plusDoriki));
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}
					
				if (props.getFaction().equals(ID.FACTION_PIRATE) || props.getFaction().equals(ID.FACTION_REVOLUTIONARY))
					if (plusBounty > 0)
						if (props.getBounty() + plusBounty < Values.MAX_GENERAL)
							props.alterBounty(plusBounty);
				
				if(props.getBelly() + plusBelly < Values.MAX_GENERAL)
					props.alterBelly(plusBelly);
						
			}
			
	    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
	    	{
	    		if(!targetPlayer)
	    		{
		    		WyTelemetry.addStat("dorikiEarnedFromEntities", (int) Math.round(plusDoriki));
		    		WyTelemetry.addStat("bellyEarnedFromEntities", plusBelly);
		    		WyTelemetry.addStat("bountyEarnedFromEntities", plusBounty);
	    		}
	    		else
	    		{
		    		WyTelemetry.addStat("dorikiEarnedFromPlayers", (int) Math.round((ExtendedEntityStats.get(target).getDoriki() - ExtendedEntityStats.get(target).getDorikiFromCommand()) / 4));
		    		WyTelemetry.addStat("bellyEarnedFromPlayers", plusBelly - ExtendedEntityStats.get(target).getBellyFromCommand());
		    		WyTelemetry.addStat("bountyEarnedFromPlayers", (int) Math.round((ExtendedEntityStats.get(target).getBounty() - ExtendedEntityStats.get(target).getBountyFromCommand()) / 2));
	    		}
	    	}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
	}

	/** XXX onEntityAttackEvent */
	@SubscribeEvent
	public void onEntityAttackEvent(LivingAttackEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		Entity sourceOfDamage = event.source.getSourceOfDamage();
		ExtendedEntityStats props = ExtendedEntityStats.get(entity);

		for (int i = -2; i <= 2; i++)
			for (int j = -2; j <= 2; j++)
				for (int k = -2; k <= 2; k++)
					if (entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.KairosekiOre || entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.KairosekiBlock)
						return;
		
		if (sourceOfDamage instanceof EntityPlayer)
		{			
			ExtendedEntityStats propz = ExtendedEntityStats.get((EntityPlayer) sourceOfDamage);
			ItemStack heldItem = ((EntityPlayer) sourceOfDamage).getHeldItem();

			if(!sourceOfDamage.worldObj.isRemote && heldItem == null)
			{		
				for(int i = 0; i < propz.countAbilitiesInHotbar(); i++)
				{	
					if(propz.getAbilityFromSlot(i) != null && !propz.getAbilityFromSlot(i).equals("n/a") && !propz.getAbilityFromSlot(i).isOnCooldown() 
							&& propz.getAbilityFromSlot(i).getAttribute().isPassive() && propz.getAbilityFromSlot(i).isPassiveActive())
					{							
						if(propz.getAbilityFromSlot(i).getAttribute().isPunch())
						{							
							propz.getAbilityFromSlot(i).hitEntity((EntityPlayer) sourceOfDamage, entity);
						}
					}
				}
			}
			
			if(heldItem != null && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			{
				boolean hasKairosekiWeapon = heldItem.isItemEnchanted() && EnchantmentHelper.getEnchantmentLevel(ListEffects.kairoseki.effectId, heldItem) > 0;
				boolean hasHaki = propz.hasBusoHakiActive();
	
				if (entity instanceof EntityPlayer)
				{
					if (props.isLogia())
						if (!hasHaki && !hasKairosekiWeapon)
							event.setCanceled(true);
				}
				else
				{
					if (entity instanceof EntityNewMob)
					{
						if (((EntityNewMob) entity).isLogia())
							if (!hasHaki && !hasKairosekiWeapon)
								event.setCanceled(true);
					}
					else
					{
						// Possible mods/plugins support ?
					}					
				}
			}		
		}

		if (sourceOfDamage instanceof EntityLivingBase && !(sourceOfDamage instanceof EntityPlayer) && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
		{
			boolean hasKairosekiWeapon;
			boolean hasHaki;

			if (sourceOfDamage instanceof EntityNewMob)
				hasHaki = ((EntityNewMob) sourceOfDamage).hasBusoHaki();
			else
				hasHaki = false;

			if (props.isLogia())
				if (!hasHaki)
					event.setCanceled(true);

		}
		
		if (sourceOfDamage instanceof EntityArrow && props.isLogia() && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
				event.setCanceled(true);

		if(sourceOfDamage instanceof AbilityProjectile && ((AbilityProjectile)sourceOfDamage).getAttribute().getAttributeName().equals("Bullet") && props.isLogia() && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			event.setCanceled(true);
		
		if(event.source.isExplosion() && props.isLogia() && MainConfig.enableLogiaInvulnerability && !this.kairosekiChecks(entity))
			event.setCanceled(true);
		
		if (event.entityLiving instanceof EntityPlayer)
		{
			if (props.getUsedFruit().equals("meramera") && (event.source.equals(DamageSource.inFire) || event.source.equals(DamageSource.onFire)))
			{
				entity.extinguish();
				event.setCanceled(true);
			}
			if (props.getUsedFruit().equals("magumagu")
					&& (event.source.equals(DamageSource.inFire) || event.source.equals(DamageSource.onFire) || event.source.equals(DamageSource.lava)))
			{
				entity.extinguish();
				event.setCanceled(true);
			} 
		}
	}
	
	private boolean kairosekiChecks(EntityLivingBase entity)
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer entityP = (EntityPlayer) entity;
			return WyHelper.isBlockNearby(entityP, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) 
					|| DevilFruitsHelper.hasKairosekiItem(entityP) || entityP.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) 
					|| entityP.inventory.hasItem( Item.getItemFromBlock(ListMisc.KairosekiOre));
		}
		else
		{
			return WyHelper.isBlockNearby(entity, 3, ListMisc.KairosekiBlock, ListMisc.KairosekiOre);
		}
	}
	
	/** XXX onEntityJoinWorld */
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);			
			
			if (!player.worldObj.isRemote)
			{				
				if(ID.DEV_EARLYACCESS)
				{					
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/cs2cv9ezaatzgd3/earlyaccess.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						boolean flag = false;
						
						while(scanner.hasNextLine())
						{
							String uuid = scanner.nextLine();
							
							if(player.getUniqueID().toString().equals(uuid) || player.getDisplayName().equals(uuid))
							{
								flag = true;
								break;
							}													
						}
						
						if(!flag)
							((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");														
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");						
						e.printStackTrace();
					}				
				}
				
				if (props.getRace().equals("N/A") && props.getFaction().equals("N/A") && props.getFightStyle().equals("N/A") && !player.inventory.hasItemStack(new ItemStack(ListMisc.CharacterCreator)))
					player.inventory.addItemStackToInventory(new ItemStack(ListMisc.CharacterCreator, 1));
				
				if(props.getUsedFruit() != null && !props.getUsedFruit().equals("N/A"))
				{
					String model = "";
					String fullModel = "";
					if(props.getUsedFruit().equals("ushiushibison"))
					{
						model = "bison";
						fullModel = "model" + model;
					}
					
					ItemStack yamiFruit = new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, "yamiyaminomi"));
					ItemStack df = new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, props.getUsedFruit().replace(model, "") + "nomi" + fullModel));
					
					props.clearDevilFruitAbilities();
					
					if(props.hasYamiPower())
					{
						for(Ability a : ((AkumaNoMi)yamiFruit.getItem()).abilities)
						{
							if(!WyHelper.verifyIfAbilityIsBanned(a))
								props.addDevilFruitAbility(a);
						}
					}
					
					for(Ability a : ((AkumaNoMi)df.getItem()).abilities)
						if(!WyHelper.verifyIfAbilityIsBanned(a))
							props.addDevilFruitAbility(a);

					for(int i = 0; i < props.countAbilitiesInHotbar(); i++)
					{
						if(props.getAbilityFromSlot(i) != null)
						{
							if(WyHelper.verifyIfAbilityIsBanned(props.getAbilityFromSlot(i)))
								props.setAbilityInSlot(i, null);
						}
					}
				}
					
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				
				if(MainConfig.enableUpdateMsg)
				{
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/3io0vaqiqaoabnh/version.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						
						while(scanner.hasNextLine())
						{
							String[] parts = scanner.nextLine().split("\\-");
	
							if(ID.PROJECT_MCVERSION.equals(parts[0]))
							{
								String cloudVersion = parts[1].replace(".", "");
								String localVersion = ID.PROJECT_VERSION.replace(".", "");
								
								if(Integer.parseInt(localVersion) < Integer.parseInt(cloudVersion))
								{
									ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/builds.php"));
									
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + parts[1] + " is now available !").setChatStyle(updateStyle) );
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/builds.php]").setChatStyle(updateStyle) );
								}
							}					
						}
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				
			}		
		}
	}
	
	/** XXX onClonePlayer */
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e) 
	{
		if(e.wasDeath) 
		{
			if(MainConfig.enableKeepIEEPAfterDeath.equals("full"))
			{
				NBTTagCompound compound = new NBTTagCompound();
				ExtendedEntityStats.get(e.original).saveNBTData(compound);
				ExtendedEntityStats props = ExtendedEntityStats.get(e.entityPlayer);
				props.loadNBTData(compound);
				
				if(e.entityPlayer != null && MainConfig.enableExtraHearts)		
				{
					IAttributeInstance maxHp = e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
								
					if(props.getDoriki() / 100 <= 20)
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					else
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
				}
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("auto"))
			{
				ExtendedEntityStats props = ExtendedEntityStats.get(e.original);
				NBTTagCompound compound = new NBTTagCompound();
				
				String faction = props.getFaction();
				String race = props.getRace();
				String fightStyle = props.getFightStyle();
				String crew = props.getCrew();			
				
				props.resetNBTData(compound);
				props.loadNBTData(compound);
								
				props.setFaction(faction);
				props.setRace(race);
				props.setFightStyle(fightStyle);
				props.setCrew(crew);
				
				props.setMaxCola(100);
				props.setCola(props.getMaxCola());
				
				props.saveNBTData(compound);
								
				ExtendedEntityStats.get(e.entityPlayer).loadNBTData(compound);
			}
		}
	}

	/** XXX onDorikiGained */
	@SubscribeEvent
	public void onDorikiGained(DorikiEvent event)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(event.player);
		
		if (event.props.getRace().equals(ID.RACE_HUMAN))
		{			
			ability(event.player, 500, RokushikiAbilities.SORU);
			ability(event.player, 1500, RokushikiAbilities.TEKKAI);
			ability(event.player, 3000, RokushikiAbilities.SHIGAN);
			ability(event.player, 4500, RokushikiAbilities.GEPPO);
			ability(event.player, 5000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 6000, RokushikiAbilities.KAMIE);
			ability(event.player, 8500, RokushikiAbilities.RANKYAKU);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
			//HAOSHOKU - 9000 + other			
		}
		else if (event.props.getRace().equals(ID.RACE_FISHMAN))
		{
			ability(event.player, 800, FishKarateAbilities.UCHIMIZU);
			ability(event.player, 2000, FishKarateAbilities.SOSHARK);
			ability(event.player, 2500, FishKarateAbilities.KACHIAGEHAISOKU);
			ability(event.player, 3000, FishKarateAbilities.SAMEHADASHOTEI);
			ability(event.player, 4000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARASEIKEN);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
		}
		else if(event.props.getRace().equals(ID.RACE_CYBORG))
		{
			ability(event.player, 5500, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 8500, HakiAbilities.BUSOSHOKUHAKI);
		}
		
		if(event.player != null && MainConfig.enableExtraHearts)		
		{
			IAttributeInstance maxHp = event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
						
			if(props.getDoriki() / 100 <= 20)
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
			else
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
		}
	}	

	private void ability(EntityPlayer player, int doriki, Ability ability)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
			
		if(ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
		{
			if (props.getDoriki() >= doriki && !props.hasHakiAbility(ability))
				props.addHakiAbility(ability);
			if (props.getDoriki() < doriki && props.hasHakiAbility(ability))
				props.removeHakiAbility(ability);
		}
		else
		{
			if (props.getDoriki() >= doriki && !props.hasRacialAbility(ability))
				props.addRacialAbility(ability);
			if (props.getDoriki() < doriki && props.hasRacialAbility(ability))
				props.removeRacialAbility(ability);	
		}
	}	
	
}
