package com.lumijiez.luminarium.items;

import com.lumijiez.luminarium.Luminarium;
import com.lumijiez.luminarium.blocks.LBlocks;
import com.lumijiez.luminarium.items.custom.OreDetector;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Luminarium.MODID);

    public static final DeferredItem<Item> LUMINARIUM_PIE =
            ITEMS.registerSimpleItem("luminarium_pie", new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(1)
                            .saturationModifier(2f)
                            .build()));

    public static final DeferredItem<Item> RAW_LUMINARIUM_ORE =
            ITEMS.registerSimpleItem("raw_luminarium_ore", new Item.Properties()
                    .stacksTo(64)
                    .fireResistant());

    public static final DeferredItem<Item> LUMINARIUM_INGOT =
            ITEMS.registerSimpleItem("luminarium_ingot", new Item.Properties()
                    .stacksTo(64)
                    .fireResistant());

    public static final DeferredItem<BlockItem> RADIANT_ORE_ITEM =
            ITEMS.registerSimpleBlockItem("radiant_ore", LBlocks.RADIANT_ORE);

    public static ItemStack icon() {
        return LUMINARIUM_PIE.get().getDefaultInstance();
    }

    public static void addItems(CreativeModeTab.Output output) {
        ITEMS.getEntries().forEach(item -> output.accept(item.get()));
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    //public static final DeferredItem<BlockItem> WAND_WIREFRAME_ITEM = ITEMS.registerSimpleBlockItem("wand_wireframe", LumBlockController.WAND_WIREFRAME);
    //public static final DeferredItem<Item> COBBLE_WAND = ITEMS.register("cobble_wand", CobbleWand::new);
    public static final DeferredItem<Item> ORE_DETECTOR = ITEMS.register("ore_detector", OreDetector::new);
}
