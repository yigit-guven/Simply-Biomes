package net.yigitguven.simplybiomes.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.yigitguven.simplybiomes.registry.ModBlocks;

public class VolcanoFeature extends Feature<NoneFeatureConfiguration> {
    public VolcanoFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Randomize size: height between 10 and 20, radius slightly larger
        int height = 10 + random.nextInt(11);
        int radius = height + 2 + random.nextInt(5);

        // Find the surface
        BlockPos pos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, origin);

        BlockState scoria = ModBlocks.SCORIA.get().defaultBlockState();
        BlockState obsidian = Blocks.OBSIDIAN.defaultBlockState();
        BlockState lava = Blocks.LAVA.defaultBlockState();

        // Generate the cone
        for (int y = 0; y < height; y++) {
            double currentRadius = radius * (1.0 - (double) y / height);
            int intRadius = (int) Math.ceil(currentRadius);

            for (int x = -intRadius; x <= intRadius; x++) {
                for (int z = -intRadius; z <= intRadius; z++) {
                    double distSq = x * x + z * z;
                    if (distSq <= currentRadius * currentRadius) {
                        BlockPos buildPos = pos.offset(x, y, z);

                        // Internal vent (vertical shaft)
                        if (distSq <= 4.0) { // Vent radius of 2
                            if (y == height - 1) {
                                level.setBlock(buildPos, lava, 2); // Crater pool
                            } else if (y < height - 1) {
                                level.setBlock(buildPos, lava, 2); // Magma tube
                            }
                        } else {
                            // Shell of the volcano
                            if (random.nextFloat() < 0.2) {
                                level.setBlock(buildPos, obsidian, 2);
                            } else {
                                level.setBlock(buildPos, scoria, 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
