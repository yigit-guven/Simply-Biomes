package net.yigitguven.simplybiomes.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yigitguven.simplybiomes.SimplyBiomes;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, SimplyBiomes.MODID);

    public static final RegistryObject<CreativeModeTab> SIMPLY_BIOMES_TAB = CREATIVE_MODE_TABS.register(
            "simply_biomes_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.SCORIA.get()))
                    .title(Component.translatable("creativetab.simply_biomes"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.VOLCANIC_ASH.get());
                        output.accept(ModBlocks.SCORIA.get());
                        output.accept(ModBlocks.PUMICE.get());
                        output.accept(ModBlocks.BLACK_SAND.get());
                        output.accept(ModItems.ASH.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
