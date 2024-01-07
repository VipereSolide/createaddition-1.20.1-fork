package com.mrh0.createaddition.index;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import com.mrh0.createaddition.CreateAddition;
import com.mrh0.createaddition.blocks.alternator.AlternatorBlock;
import com.mrh0.createaddition.blocks.barbed_wire.BarbedWireBlock;
import com.mrh0.createaddition.blocks.cake.CACakeBlock;
import com.mrh0.createaddition.blocks.connector.LargeConnectorBlock;
import com.mrh0.createaddition.blocks.connector.SmallConnectorBlock;
import com.mrh0.createaddition.blocks.connector.SmallLightConnectorBlock;
import com.mrh0.createaddition.blocks.crops.HarmfulPlantBlock;
import com.mrh0.createaddition.blocks.digital_adapter.DigitalAdapterBlock;
import com.mrh0.createaddition.blocks.digital_adapter.DigitalAdapterDisplaySource;
import com.mrh0.createaddition.blocks.digital_adapter.DigitalAdapterBlockItem;
import com.mrh0.createaddition.blocks.modular_accumulator.*;
import com.mrh0.createaddition.blocks.portable_energy_interface.PortableEnergyInterfaceBlock;
import com.mrh0.createaddition.blocks.portable_energy_interface.PortableEnergyInterfaceMovement;
import com.mrh0.createaddition.energy.NodeMovementBehaviour;
import com.mrh0.createaddition.blocks.creative_energy.CreativeEnergyBlock;
import com.mrh0.createaddition.blocks.electric_motor.ElectricMotorBlock;
import com.mrh0.createaddition.blocks.liquid_blaze_burner.LiquidBlazeBurnerBlock;
import com.mrh0.createaddition.blocks.redstone_relay.RedstoneRelayBlock;
import com.mrh0.createaddition.blocks.rolling_mill.RollingMillBlock;
import com.mrh0.createaddition.blocks.tesla_coil.TeslaCoilBlock;
import com.mrh0.createaddition.config.Config;
import com.mrh0.createaddition.item.BiomassPelletBlock;
import com.simibubi.create.AllMovementBehaviours;
import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CABlocks {

	static {
		CreateAddition.REGISTRATE.setCreativeTab(CACreativeModeTabs.MAIN_TAB);
	}

	public static final BlockEntry<ElectricMotorBlock> ELECTRIC_MOTOR = CreateAddition.REGISTRATE.block("electric_motor", ElectricMotorBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.tag(AllBlockTags.SAFE_NBT.tag) //Dono what this tag means (contraption safe?).
			.transform(BlockStressDefaults.setCapacity(Config.MAX_STRESS.get()/256f))
			.transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(0, 256)))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<AlternatorBlock> ALTERNATOR = CreateAddition.REGISTRATE.block("alternator", AlternatorBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.transform(BlockStressDefaults.setImpact(Config.MAX_STRESS.get()/256f))
			.tag(AllBlockTags.SAFE_NBT.tag) //Dono what this tag means (contraption safe?).
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<RollingMillBlock> ROLLING_MILL = CreateAddition.REGISTRATE.block("rolling_mill", RollingMillBlock::new)
			.initialProperties(SharedProperties::stone)
			.transform(BlockStressDefaults.setImpact(Config.ROLLING_MILL_STRESS.get()))
			.tag(AllBlockTags.SAFE_NBT.tag) //Dono what this tag means (contraption safe?).
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<CreativeEnergyBlock> CREATIVE_ENERGY = CreateAddition.REGISTRATE.block("creative_energy", CreativeEnergyBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.item()
			.properties(p -> p.rarity(Rarity.EPIC))
			.transform(customItemModel())
			.register();

	public static final BlockEntry<SmallConnectorBlock> SMALL_CONNECTOR = CreateAddition.REGISTRATE.block("connector",  SmallConnectorBlock::new)
			.initialProperties(SharedProperties::stone)
			.onRegister(AllMovementBehaviours.movementBehaviour(new NodeMovementBehaviour()))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<SmallLightConnectorBlock> SMALL_LIGHT_CONNECTOR = CreateAddition.REGISTRATE.block("small_light_connector",  SmallLightConnectorBlock::new)
			.initialProperties(SharedProperties::stone)
			.onRegister(AllMovementBehaviours.movementBehaviour(new NodeMovementBehaviour()))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<LargeConnectorBlock> LARGE_CONNECTOR = CreateAddition.REGISTRATE.block("large_connector",  LargeConnectorBlock::new)
			.initialProperties(SharedProperties::stone)
			.onRegister(AllMovementBehaviours.movementBehaviour(new NodeMovementBehaviour()))
			.item()
			.transform(customItemModel())
			.register();

	/*public static final BlockEntry<AccumulatorBlock> ACCUMULATOR = CreateAddition.REGISTRATE.block("accumulator",  AccumulatorBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.onRegister(AllMovementBehaviours.movementBehaviour(new NodeMovementBehaviour()))
			.item()
			//.tab()
			.transform(customItemModel())
			.register();*/

	public static final BlockEntry<RedstoneRelayBlock> REDSTONE_RELAY = CreateAddition.REGISTRATE.block("redstone_relay",  RedstoneRelayBlock::new)
			.initialProperties(SharedProperties::stone)
			.onRegister(AllMovementBehaviours.movementBehaviour(new NodeMovementBehaviour()))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<CACakeBlock> CHOCOLATE_CAKE = CreateAddition.REGISTRATE.block("chocolate_cake",  CACakeBlock::new)
			.initialProperties(() -> Blocks.CAKE)
			.properties(props -> props.sound(SoundType.WOOL).strength(0.5f))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<CACakeBlock> HONEY_CAKE = CreateAddition.REGISTRATE.block("honey_cake",  CACakeBlock::new)
			.initialProperties(() -> Blocks.CAKE)
			.properties(props -> props.sound(SoundType.WOOL).strength(0.5f))
			.item()
			.transform(customItemModel())
			.register();

	/*public static final BlockEntry<HarmfulPlantBlock> HARMFUL_PLANT = CreateAddition.REGISTRATE.block("harmful_plant",  HarmfulPlantBlock::new)
			.initialProperties(Material.PLANT)
			.properties(props -> props.sound(SoundType.CROP).strength(0.5f))
			.item()
			.transform(customItemModel())
			.register();*/

	public static final BlockEntry<BarbedWireBlock> BARBED_WIRE = CreateAddition.REGISTRATE.block("barbed_wire",  BarbedWireBlock::new)
			.initialProperties(() -> Blocks.COBWEB)
			.properties(props -> props.noCollission().requiresCorrectToolForDrops().strength(4.0F))
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<TeslaCoilBlock> TESLA_COIL = CreateAddition.REGISTRATE.block("tesla_coil",  TeslaCoilBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.item(AssemblyOperatorBlockItem::new)
			.transform(customItemModel())
			.register();

	public static final BlockEntry<ModularAccumulatorBlock> MODULAR_ACCUMULATOR = CreateAddition.REGISTRATE.block("modular_accumulator",  ModularAccumulatorBlock::regular)
			.initialProperties(SharedProperties::softMetal)
			.properties(BlockBehaviour.Properties::noOcclusion)
			.onRegister(AllMovementBehaviours.movementBehaviour(new ModularAccumulatorMovement()))
			.onRegister(connectedTextures(ModularAccumulatorCTBehaviour::new))
			.onRegister(assignDataBehaviour(new ModularAccumulatorDisplaySource(), "modular_accumulator"))
			//.onRegister(assignDataBehaviour(ForgeEnergyDisplaySource.INSTANCE, "forge_energy"))
			.addLayer(() -> RenderType::cutoutMipped)
			.item(ModularAccumulatorBlockItem::new)
			.transform(customItemModel())
			.register();

	public static final BlockEntry<LiquidBlazeBurnerBlock> LIQUID_BLAZE_BURNER = CreateAddition.REGISTRATE.block("liquid_blaze_burner",  LiquidBlazeBurnerBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.properties(p -> p.mapColor(DyeColor.GRAY))
			.properties(p -> p.lightLevel(BlazeBurnerBlock::getLight))
			.transform(pickaxeOnly())
			.addLayer(() -> RenderType::cutoutMipped)
			.blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
			.register();

	public static final BlockEntry<PortableEnergyInterfaceBlock> PORTABLE_ENERGY_INTERFACE = CreateAddition.REGISTRATE.block("portable_energy_interface",  PortableEnergyInterfaceBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.onRegister(AllMovementBehaviours.movementBehaviour(new PortableEnergyInterfaceMovement()))
			.addLayer(() -> RenderType::cutoutMipped)
			.item()
			.transform(customItemModel())
			.register();

	/*public static final BlockEntry<CasingBlock> COPPER_WIRE_CASING = REGISTRATE.block("copper_wire_casing", CasingBlock::new)
			.properties(p -> p.color(MaterialColor.PODZOL))
			.transform(BuilderTransformers.casing(() -> CASpriteShifts.COPPER_WIRE_CASING))
			.register();*/

	public static final BlockEntry<Block> BIOMASS_PALLET = CreateAddition.REGISTRATE.block("biomass_pellet_block", Block::new)
			.initialProperties(() -> Blocks.DRIED_KELP_BLOCK)
			.properties(p -> p.mapColor(MapColor.COLOR_GREEN))
			.item(BiomassPelletBlock::new)
			.transform(customItemModel())
			.register();

	public static final BlockEntry<DigitalAdapterBlock> DIGITAL_ADAPTER = CreateAddition.REGISTRATE.block("digital_adapter",  DigitalAdapterBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.onRegister(assignDataBehaviour(new DigitalAdapterDisplaySource(), "digital_adapter"))
			.properties(p -> p.mapColor(DyeColor.GRAY))
			.item(DigitalAdapterBlockItem::new)
			.transform(customItemModel())
			.register();

	public static void register() {

	}
}
