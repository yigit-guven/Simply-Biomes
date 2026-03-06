package net.yigitguven.simplybiomes.worldgen;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.yigitguven.simplybiomes.registry.ModBlocks;

public class ModSurfaceRules {
        public static SurfaceRules.RuleSource makeRules() {
                return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.VOLCANIC_DESERT),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                                SurfaceRules.state(ModBlocks.BLACK_SAND.get().defaultBlockState())));
        }

        private static SurfaceRules.RuleSource makeStateRule(Block block) {
                return SurfaceRules.state(block.defaultBlockState());
        }
}
