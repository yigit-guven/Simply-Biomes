package net.yigitguven.simplybiomes;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.yigitguven.simplybiomes.registry.ModBlocks;
import net.yigitguven.simplybiomes.registry.ModCreativeModeTabs;
import net.yigitguven.simplybiomes.registry.ModFeatures;
import net.yigitguven.simplybiomes.registry.ModItems;
import net.yigitguven.simplybiomes.worldgen.ModSurfaceRules;
import net.yigitguven.simplybiomes.worldgen.SimplyTerraBlender;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.yigitguven.simplybiomes.worldgen.ModBiomes;
import terrablender.api.SurfaceRuleManager;
import org.slf4j.Logger;

@Mod(SimplyBiomes.MODID)
public class SimplyBiomes {
    public static final String MODID = "simplybiomes";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SimplyBiomes() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFeatures.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        net.minecraftforge.fml.ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SimplyTerraBlender.registerRegions();
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, SimplyBiomes.MODID,
                    ModSurfaceRules.makeRules());
        });
    }

    @SubscribeEvent
    public void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel level) {
            level.players().forEach(player -> {
                if (level.random.nextInt(100) == 0) {
                    int x = player.getBlockX() + level.random.nextInt(32) - 16;
                    int z = player.getBlockZ() + level.random.nextInt(32) - 16;
                    BlockPos pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, new BlockPos(x, 0, z));

                    if (level.getBiome(pos).is(ModBiomes.VOLCANIC_DESERT)) {
                        if (level.getBlockState(pos).isAir()
                                && ModBlocks.VOLCANIC_ASH.get().defaultBlockState().canSurvive(level, pos)) {
                            level.setBlockAndUpdate(pos, ModBlocks.VOLCANIC_ASH.get().defaultBlockState());
                        }
                    }
                }
            });
        }
    }
}
