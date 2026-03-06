package net.yigitguven.simplybiomes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FumaroleBlock extends Block {
    public FumaroleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            double x = pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.3D;
            double y = pos.getY() + 1.0D;
            double z = pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.3D;
            level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
        }
    }
}
