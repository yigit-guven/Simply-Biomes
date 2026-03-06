package net.yigitguven.simplybiomes;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yigitguven.simplybiomes.registry.ModBlocks;
import net.yigitguven.simplybiomes.registry.ModCreativeModeTabs;
import net.yigitguven.simplybiomes.registry.ModItems;
import org.slf4j.Logger;

@Mod(SimplyBiomes.MODID)
public class SimplyBiomes {
    public static final String MODID = "simply-biomes";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SimplyBiomes(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
