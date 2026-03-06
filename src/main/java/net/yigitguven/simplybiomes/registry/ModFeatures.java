package net.yigitguven.simplybiomes.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yigitguven.simplybiomes.SimplyBiomes;
import net.yigitguven.simplybiomes.worldgen.feature.VolcanoFeature;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
            SimplyBiomes.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VOLCANO = FEATURES.register("volcano",
            () -> new VolcanoFeature(NoneFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
