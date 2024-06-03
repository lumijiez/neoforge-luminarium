package com.lumijiez.luminarium;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(Luminarium.MODID)
public class Luminarium
{
    public static final String MODID = "luminarium";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final DeferredBlock<Block> RADIANT_ORE = BLOCKS.registerSimpleBlock("radiant_ore", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredItem<BlockItem> RADIANT_ORE_ITEM = ITEMS.registerSimpleBlockItem("radiant_ore", RADIANT_ORE);


    public static final DeferredItem<Item> LUMINARIUM_PIE = ITEMS.registerSimpleItem("luminarium_pie", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LUMINARIUM_TAB = CREATIVE_MODE_TABS.register("luminarium_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.luminarium"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> LUMINARIUM_PIE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(LUMINARIUM_PIE.get());
                output.accept(RADIANT_ORE_ITEM.get());
            }).build());

    public Luminarium(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        //NeoForge.EVENT_BUS.register(this);
    }
}
