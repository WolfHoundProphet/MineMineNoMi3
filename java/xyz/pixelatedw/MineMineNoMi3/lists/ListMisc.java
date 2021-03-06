package xyz.pixelatedw.MineMineNoMi3.lists;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.DimensionManager;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.NewBlock;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyRegistry;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockBarrier;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockDarkness;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockDemonPoison;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockDenDenMushi;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockEnchantmentTable2;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockKage;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockOpe;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockOpeMid;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockPoison;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialAxe;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialEisen;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialFire;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialImpact;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialMilky;
import xyz.pixelatedw.MineMineNoMi3.blocks.dials.BlockDialReject;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityOpe;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMiBox;
import xyz.pixelatedw.MineMineNoMi3.items.BellyPouch;
import xyz.pixelatedw.MineMineNoMi3.items.CharacterCreator;
import xyz.pixelatedw.MineMineNoMi3.items.Cola;
import xyz.pixelatedw.MineMineNoMi3.items.Heart;
import xyz.pixelatedw.MineMineNoMi3.items.ItemAbilityWeapon;
import xyz.pixelatedw.MineMineNoMi3.items.ItemCoreArmor;
import xyz.pixelatedw.MineMineNoMi3.items.UltraCola;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialAxe;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialEisen;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialFire;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialImpact;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialMilky;
import xyz.pixelatedw.MineMineNoMi3.items.dials.DialReject;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.Flintlock;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.ItemCoreWeapon;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.Kabuto;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.KujaBow;
import xyz.pixelatedw.MineMineNoMi3.world.WorldProviderScenarioArena;

public class ListMisc 
{	
	public static Block Ope = new BlockOpe();
	public static Block OpeMid = new BlockOpeMid();
	public static Block KairosekiBlock = new NewBlock(Material.rock).setHardness(10);
	public static Block KairosekiOre = new NewBlock(Material.rock)
	{
		public int quantityDropped(Random random) {return 2 + random.nextInt(3);}
		public Item getItemDropped(int id, Random rand, int fortune) {return Kairoseki;}
	}.setHardness(10); 
	public static Block EnchantmentTable = new BlockEnchantmentTable2();
	public static Block DenDenMushi = new BlockDenDenMushi();
	public static Block SkyBlock = new NewBlock(Material.cloth);
	public static Block Barrier = new BlockBarrier();
	public static Block Poison = new BlockPoison();
	public static Block DemonPoison = new BlockDemonPoison();
	public static BlockCustomSpawner CustomSpawner = new BlockCustomSpawner();
	public static Block Darkness = new BlockDarkness();
	public static Block KageBlock = new BlockKage();
	public static Block DialEisenBlock = new BlockDialEisen();
	public static Block DialFireBlock = new BlockDialFire();
	public static Block DialAxeBlock = new BlockDialAxe();
	public static Block DialImpactBlock = new BlockDialImpact();
	public static Block DialMilkyBlock = new BlockDialMilky();
	public static Block DialFlashBlock = null;
	public static Block DialRejectBlock = new BlockDialReject();
	
	public static Item CharacterCreator = new CharacterCreator();
	public static Item Kairoseki = new Item(); 
	public static Item DenseKairoseki = new Item();
	public static Item BlackMetal = new Item();
	public static Item Shadow = new Item();
	public static Item Heart = new Heart().setMaxStackSize(1);
	public static Item BellyPouch = new BellyPouch().setMaxStackSize(1);
	public static Item Key = new Item();
	public static Item Box1;
	public static Item Box2;
	public static Item Box3;
	
	public static Item DialEisen = new DialEisen();
	public static Item DialFire = new DialFire();
	public static Item DialAxe = new DialAxe();	
	public static Item DialImpact = new DialImpact();	
	public static Item DialMilky = new DialMilky();	
	public static Item DialFlash = null;	
	public static Item DialReject = new DialReject();	
	
	public static Item Bullets = new Item(); 
	public static Item KairosekiBullets = new Item();
	public static Item KujaArrow = new Item();
	public static Item PopGreen = new Item();
	public static Item Cola = new Cola();
	public static Item UltraCola = new UltraCola();
	
	public static Item MarineHelm = new ItemCoreArmor("marine", ID.ARMORMAT_USELESS, 0);
	public static Item MarineChestplate = new ItemCoreArmor("marine", ID.ARMORMAT_USELESS, 1);
	public static Item MarineLeggings = new ItemCoreArmor("marine", ID.ARMORMAT_USELESS, 2);
	public static Item MarineBoots = new ItemCoreArmor("marine", ID.ARMORMAT_USELESS, 3);
	 
	public static Item PirateChestplate = new ItemCoreArmor("pirate", ID.ARMORMAT_USELESS, 1);
	public static Item PirateLeggings = new ItemCoreArmor("pirate", ID.ARMORMAT_USELESS, 2);
	public static Item PirateBoots = new ItemCoreArmor("pirate", ID.ARMORMAT_USELESS, 3);

	public static Item ColaBackpack = new ItemCoreArmor("colabackpack", ID.ARMORMAT_COLABACKPACK, 1);
	
	public static Item Flintlock = new Flintlock().setMaxStackSize(1).setFull3D();
	public static Item Kabuto = new Kabuto("kabuto").setMaxStackSize(1).setFull3D();
	public static Item KuroKabuto = new Kabuto("kurokabuto").setMaxStackSize(1).setFull3D();
	public static Item GingaPachinko = new Kabuto("gingapachinko").setMaxStackSize(1).setFull3D();
	public static Item GreenKujaBow = new KujaBow("green").setMaxStackSize(1).setFull3D();
	public static Item BlueKujaBow = new KujaBow("blue").setMaxStackSize(1).setFull3D();
	public static Item RedKujaBow = new KujaBow("red").setMaxStackSize(1).setFull3D();
	public static ItemCoreWeapon MarineSword = new ItemCoreWeapon(5).setMaxDamage(300);
	public static ItemCoreWeapon PirateCutlass = new ItemCoreWeapon(5).setMaxDamage(300);
	public static ItemCoreWeapon Pipe = new ItemCoreWeapon(4).setMaxDamage(200);
	public static ItemCoreWeapon Scissors = new ItemCoreWeapon(6);
	public static ItemCoreWeapon Kikoku = new ItemCoreWeapon(8);
	public static ItemCoreWeapon Kiribachi = new ItemCoreWeapon(6);
	public static ItemCoreWeapon Yoru = new ItemCoreWeapon(10);
	public static ItemCoreWeapon Biseto = new ItemCoreWeapon(8);
	public static ItemCoreWeapon Hook = new ItemCoreWeapon(6);
	public static ItemCoreWeapon Umbrella = new ItemCoreWeapon(3);
	public static ItemCoreWeapon Jitte = new ItemCoreWeapon(7).setMaxDamage(500);
	public static ItemCoreWeapon BoStick = new ItemCoreWeapon(6);
	public static ItemCoreWeapon Hammer5t = new ItemCoreWeapon(1);
	public static ItemCoreWeapon Hammer10t = new ItemCoreWeapon(1);
	public static ItemCoreWeapon Hammer100t = new ItemCoreWeapon(1);
	public static ItemCoreWeapon Tonfa = new ItemCoreWeapon(4);
	public static ItemCoreWeapon Knife1 = new ItemCoreWeapon(3);	
	public static ItemCoreWeapon Knife2 = new ItemCoreWeapon(3);	
	public static ItemCoreWeapon WadoIchimonji = new ItemCoreWeapon(8);
	public static ItemCoreWeapon Kitetsu = new ItemCoreWeapon(8);
	public static ItemCoreWeapon Shusui = new ItemCoreWeapon(8);
	
	public static ItemAbilityWeapon IceSaber = new ItemAbilityWeapon(9);
	public static ItemAbilityWeapon AmaNoMurakumo = new ItemAbilityWeapon(9);
	public static ItemAbilityWeapon NoroNoroBeamSword = new ItemAbilityWeapon(5);
	public static ItemAbilityWeapon DoruDoruArtsKen = new ItemAbilityWeapon(6);
	public static ItemAbilityWeapon BlueSword = new ItemAbilityWeapon(8);
	public static ItemAbilityWeapon TabiraYuki = new ItemAbilityWeapon(8);
	
	public static void init()
	{
		DimensionManager.registerProviderType(ID.DIMENSION_ID_SCENARIOARENA, WorldProviderScenarioArena.class, true);
		DimensionManager.registerDimension(ID.DIMENSION_ID_SCENARIOARENA, ID.DIMENSION_ID_SCENARIOARENA);
		
 		Box1 = new AkumaNoMiBox(1).setMaxStackSize(1);
 		Box2 = new AkumaNoMiBox(2).setMaxStackSize(1);
 		Box3 = new AkumaNoMiBox(3).setMaxStackSize(1);
		
		addITEM(Kairoseki							, "Kairoseki"				, ListCreativeTabs.tabMisc);
		addITEM(DenseKairoseki						, "Dense Kairoseki"			, ListCreativeTabs.tabMisc);
		addITEM(BlackMetal							, "Black Metal"				, ListCreativeTabs.tabMisc);
		addITEM(Shadow								, "Shadow"					, ListCreativeTabs.tabMisc);
		addITEM(Heart								, "Heart"					, null);
		addITEM(Cola								, "Cola"					, ListCreativeTabs.tabMisc);
		addITEM(UltraCola							, "Ultra Cola"				, ListCreativeTabs.tabMisc);
		addITEM(BellyPouch							, "Belly Pouch"				, ListCreativeTabs.tabMisc);
		addITEM(Key									, "Key"						, ListCreativeTabs.tabMisc);
		addITEM(Box1								, "Wooden Box"				, ListCreativeTabs.tabMisc);
		addITEM(Box2								, "Iron Box"				, ListCreativeTabs.tabMisc);
		addITEM(Box3								, "Golden Box"				, ListCreativeTabs.tabMisc);
		
 		addITEM(KujaArrow							, "Kuja Arrow"				, ListCreativeTabs.tabWeapons);
 		addITEM(PopGreen							, "Pop Green"				, ListCreativeTabs.tabWeapons);
 		addITEM(Bullets								, "Bullets"					, ListCreativeTabs.tabWeapons);
 		addITEM(KairosekiBullets					, "Kairoseki Bullets"		, ListCreativeTabs.tabWeapons);
 		
 		addITEM(MarineHelm							, "Marine Helmet"			, ListCreativeTabs.tabWeapons);
 		addITEM(MarineChestplate					, "Marine Chestplate"		, ListCreativeTabs.tabWeapons);
 		addITEM(MarineLeggings						, "Marine Leggings"			, ListCreativeTabs.tabWeapons);
 		addITEM(MarineBoots							, "Marine Boots"			, ListCreativeTabs.tabWeapons);
 	
 		addITEM(PirateChestplate					, "Pirate Chestplate"		, ListCreativeTabs.tabWeapons);
 		addITEM(PirateLeggings						, "Pirate Leggings"			, ListCreativeTabs.tabWeapons);
 		addITEM(PirateBoots							, "Pirate Boots"			, ListCreativeTabs.tabWeapons);
		
 		addITEM(ColaBackpack						, "Cola Backpack"			, ListCreativeTabs.tabWeapons);
 		
		addITEM(GreenKujaBow						, "Green Kuja Bow"			, ListCreativeTabs.tabWeapons);
		addITEM(BlueKujaBow							, "Blue Kuja Bow"			, ListCreativeTabs.tabWeapons);
		addITEM(RedKujaBow							, "Red Kuja Bow"			, ListCreativeTabs.tabWeapons);
		addITEM(Flintlock							, "Flintlock"				, ListCreativeTabs.tabWeapons);
		addITEM(Kabuto								, "Kabuto"					, ListCreativeTabs.tabWeapons);	
		addITEM(KuroKabuto							, "Kuro Kabuto"				, ListCreativeTabs.tabWeapons);
		addITEM(GingaPachinko						, "Ginga Pachinko"			, ListCreativeTabs.tabWeapons);
 		addITEM(MarineSword							, "Marine Sword"			, ListCreativeTabs.tabWeapons); 	
 		addITEM(PirateCutlass						, "Pirate Cutlass"			, ListCreativeTabs.tabWeapons); 	
 		addITEM(Pipe								, "Pipe"					, ListCreativeTabs.tabWeapons); 	
 		addITEM(Scissors							, "Scissors"				, ListCreativeTabs.tabWeapons); 	
 		addITEM(Kikoku								, "Kikoku"					, ListCreativeTabs.tabWeapons); 	
 		addITEM(Kiribachi							, "Kiribachi"				, ListCreativeTabs.tabWeapons); 
 		addITEM(Yoru								, "Yoru"					, ListCreativeTabs.tabWeapons); 
 		addITEM(Biseto								, "Biseto"					, ListCreativeTabs.tabWeapons); 
 		addITEM(Hook								, "Hook"					, ListCreativeTabs.tabWeapons);	
 		addITEM(Umbrella							, "Umbrella"				, ListCreativeTabs.tabWeapons);	
		addITEM(Jitte								, "Jitte"					, ListCreativeTabs.tabWeapons);	
		addITEM(BoStick								, "Bo Staff"				, ListCreativeTabs.tabWeapons);
		addITEM(Hammer5t							, "5t Hammer"				, ListCreativeTabs.tabWeapons);
		addITEM(Hammer10t							, "10t Hammer"				, ListCreativeTabs.tabWeapons);
		addITEM(Hammer100t							, "100t Hammer"				, ListCreativeTabs.tabWeapons);
		addITEM(Tonfa								, "Tonfa"					, ListCreativeTabs.tabWeapons);
		addITEM(Knife1								, "Mihawk's Knife"			, ListCreativeTabs.tabWeapons);
		addITEM(Knife2								, "Ace's Knife"				, ListCreativeTabs.tabWeapons);
		addITEM(WadoIchimonji						, "Wado Ichimonji"			, ListCreativeTabs.tabWeapons);		
		addITEM(Kitetsu								, "Kitetsu"					, ListCreativeTabs.tabWeapons);		
		addITEM(Shusui								, "Shusui"					, ListCreativeTabs.tabWeapons);	

		addITEM(IceSaber							, "Ice Saber"				, null);	
		addITEM(AmaNoMurakumo						, "Ama no Murakumo"			, null);	
		addITEM(NoroNoroBeamSword					, "Noro Noro Beam Sword"	, null);	
		addITEM(DoruDoruArtsKen						, "Doru Doru Arts : Ken"	, null);
		addITEM(BlueSword							, "Blue Sword"				, null);
		addITEM(TabiraYuki							, "Tabira Yuki"				, null);
		
 		addITEM(CharacterCreator					, "Character Creator"		, ListCreativeTabs.tabMisc);  
		
 		addITEM(DialEisen							, "Eisen Dial"				, ListCreativeTabs.tabMisc);
 		addITEM(DialFire							, "Flame Dial"				, ListCreativeTabs.tabMisc);
 		addITEM(DialAxe								, "Axe Dial"				, ListCreativeTabs.tabMisc);
 		addITEM(DialImpact							, "Impact Dial"				, ListCreativeTabs.tabMisc);
 		addITEM(DialMilky							, "Milky Dial"				, ListCreativeTabs.tabMisc);
 		addITEM(DialReject							, "Reject Dial"				, ListCreativeTabs.tabMisc);
 		
 		addBLOCK(Ope				, "Ope"					, Float.POSITIVE_INFINITY		, null							, null);
 		addBLOCK(OpeMid				, "Ope Mid"				, Float.POSITIVE_INFINITY		, TileEntityOpe.class			, null);
 		addBLOCK(KairosekiOre		, "Kairoseki Ore"		, 3.5F							, null							, ListCreativeTabs.tabMisc);
 		addBLOCK(KairosekiBlock		, "Kairoseki Block"		, 3.5F							, null							, ListCreativeTabs.tabMisc);
 		addBLOCK(EnchantmentTable	, "Kairoseki Table"		, 3.5F							, null							, ListCreativeTabs.tabMisc);
 		addBLOCK(DenDenMushi		, "Den Den Mushi"		, 3.5F							, null							, ListCreativeTabs.tabMisc);
 		addBLOCK(SkyBlock			, "Sky Block"			, 1.5F							, null							, ListCreativeTabs.tabMisc);
 		addBLOCK(Barrier			, "Crash Barrier"		, Float.POSITIVE_INFINITY		, null							, null);
 		addBLOCK(Poison				, "Poison"				, 1.5F							, null							, null);
 		addBLOCK(DemonPoison		, "Demon Poison"		, 1.5F							, null							, null);
 		addBLOCK(CustomSpawner		, "Custom Spawner"		, Float.POSITIVE_INFINITY		, TileEntityCustomSpawner.class	, null);
 		addBLOCK(Darkness			, "Darkness"			, Float.POSITIVE_INFINITY		, null							, null);
 		addBLOCK(KageBlock			, "Kage Block"			, Float.POSITIVE_INFINITY		, null							, null);
 		addBLOCK(DialEisenBlock		, "Eisen Dial Block"	, .3F							, null							, null);
 		addBLOCK(DialFireBlock		, "Flame Dial Block"	, .3F							, null							, null);
 		addBLOCK(DialAxeBlock		, "Axe Dial Block"		, .3F							, null							, null);
 		addBLOCK(DialImpactBlock	, "Impact Dial Block"	, .3F							, null							, null);
 		addBLOCK(DialMilkyBlock		, "Milky Dial Block"	, .3F							, null							, null);
 		addBLOCK(DialRejectBlock	, "Reject Dial Block"	, .3F							, null							, null);
 				
 		WyRegistry.registerName("race.n/a.name", "N/A");
 		WyRegistry.registerName("faction.n/a.name", "N/A");
 		WyRegistry.registerName("style.n/a.name", "N/A");
 		
 		WyRegistry.registerName("race.human.name", "Human");
 		WyRegistry.registerName("race.fishman.name", "Fishman");
 		WyRegistry.registerName("race.cyborg.name", "Cyborg");
 			
 		WyRegistry.registerName("faction.pirate.name", "Pirate");
 		WyRegistry.registerName("faction.marine.name", "Marine");
 		WyRegistry.registerName("faction.bountyhunter.name", "Bounty Hunter");
 		
 		WyRegistry.registerName("style.swordsman.name", "Swordsman");
 		WyRegistry.registerName("style.sniper.name", "Sniper");
 		WyRegistry.registerName("style.doctor.name", "Medic");
 		
 		WyRegistry.registerName(ID.LANG_GUI_FACTION, "Faction");
 		WyRegistry.registerName(ID.LANG_GUI_RACE, "Race");
 		WyRegistry.registerName(ID.LANG_GUI_STYLE, "Style");
 		WyRegistry.registerName(ID.LANG_GUI_ABILITIES, "Abilities");
 		WyRegistry.registerName("gui.epithet.name", "Epithets");
 			
	}   
	 
	private static void addITEM(Item item,String localizedName, CreativeTabs tab)
	{	
		WyRegistry.registerItem(item, localizedName, tab);
		Values.miscItems.add(item);
	}
	
	private static void addBLOCK(Block block, String localizedName, float hard, Class<? extends TileEntity> tile, CreativeTabs tab)
	{	
		WyRegistry.registerBlock(block, localizedName, hard, tab, tile);
		Values.miscBlocks.add(block);
	}
}
