package net.yigitguven.simplybiomes.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yigitguven.simplybiomes.SimplyBiomes;

import java.util.function.Supplier;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        SimplyBiomes.MODID);

        public static final RegistryObject<Block> VOLCANIC_ASH = registerBlock("volcanic_ash",
                        () -> new net.yigitguven.simplybiomes.block.VolcanicAshBlock(
                                        BlockBehaviour.Properties.copy(Blocks.SNOW).mapColor(MapColor.COLOR_BLACK)
                                                        .strength(0.1f).sound(SoundType.SNOW)));

        public static final RegistryObject<Block> SCORIA = registerBlock("scoria",
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_BLACK)
                                        .strength(1.5f, 6.0f).sound(SoundType.STONE)));

        public static final RegistryObject<Block> PUMICE = registerBlock("pumice",
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                                        .strength(1.0f, 4.0f).sound(SoundType.STONE)));

        public static final RegistryObject<Block> BLACK_SAND = registerBlock("black_sand",
                        () -> new FallingBlock(
                                        BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.COLOR_BLACK)
                                                        .strength(0.5f).sound(SoundType.SAND)));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
                ModItems.ITEMS.register(name, () -> new net.minecraft.world.item.BlockItem(block.get(),
                                new net.minecraft.world.item.Item.Properties()));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
