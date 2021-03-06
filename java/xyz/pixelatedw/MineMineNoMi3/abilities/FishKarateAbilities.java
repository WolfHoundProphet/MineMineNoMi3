package xyz.pixelatedw.MineMineNoMi3.abilities;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.FishKarateProjectiles;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class FishKarateAbilities 
{
	public static Ability UCHIMIZU = new Uchimizu();
	public static Ability SOSHARK = new Soshark();
	public static Ability KACHIAGEHAISOKU = new KachiageHaisoku();
	public static Ability SAMEHADASHOTEI = new SamehadaShotei();
	public static Ability KARAKUSAGAWARASEIKEN = new KarakusagawaraSeiken();
	
	public static Ability[] abilitiesArray = new Ability[] {UCHIMIZU, SOSHARK, SAMEHADASHOTEI, KARAKUSAGAWARASEIKEN, KACHIAGEHAISOKU};
	
	public static class Uchimizu extends Ability
	{
		public Uchimizu() 
		{
			super(ListAttributes.UCHIMIZU); 
		}
			
		public void use(EntityPlayer player)
		{
			this.projectile = new FishKarateProjectiles.Uchimizu(player.worldObj, player, ListAttributes.UCHIMIZU);
			super.use(player);
		}
	}
	
	public static class Soshark extends Ability
	{
		public Soshark() 
		{
			super(ListAttributes.SOSHARK); 
		}
			
		public void use(EntityPlayer player)
		{
			this.projectile = new FishKarateProjectiles.Soshark(player.worldObj, player, ListAttributes.SOSHARK);
			super.use(player);
		}
	}
	
	public static class KachiageHaisoku extends Ability
	{
		public KachiageHaisoku() 
		{
			super(ListAttributes.KACHIAGEHAISOKU); 
		}
			
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			super.hitEntity(player, target);
			int damage = 10;
			if(player.isInsideOfMaterial(Material.water))
				damage = 40;
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
		}
	}
	
	public static class SamehadaShotei extends Ability
	{
		public SamehadaShotei() 
		{
			super(ListAttributes.SAMEHADASHOTEI); 
		}
		
		public void duringPassive(EntityPlayer player, int passiveTimer) 
		{
			WyNetworkHelper.sendTo(new PacketParticles("samehada", player), (EntityPlayerMP) player);
		}
	}
	
	public static class KarakusagawaraSeiken extends Ability
	{
		public KarakusagawaraSeiken() 
		{
			super(ListAttributes.KARAKUSAGAWARASEIKEN); 
		}
			
		public void use(EntityPlayer player)
		{	
			if(!this.isOnCooldown)
			{
				for(EntityLivingBase elb : WyHelper.getEntitiesNear(player, 10))
				{
					elb.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				}
				super.use(player);
			}			
		}
	}
}
