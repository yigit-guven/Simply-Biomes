package net.yigitguven.simplybiomes.worldgen;

import net.minecraft.resources.ResourceLocation;
import net.yigitguven.simplybiomes.SimplyBiomes;
import terrablender.api.Regions;

public class SimplyTerraBlender {
    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(SimplyBiomes.MODID, "overworld"), 100));
    }
}
