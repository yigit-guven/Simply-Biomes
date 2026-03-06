package net.yigitguven.simplybiomes.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.yigitguven.simplybiomes.SimplyBiomes;

public class ModBiomes {
    public static final ResourceKey<Biome> VOLCANIC_DESERT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(SimplyBiomes.MODID, "volcanic_desert"));
}
