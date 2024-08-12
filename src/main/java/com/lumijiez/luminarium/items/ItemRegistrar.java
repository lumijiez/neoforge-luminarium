package com.lumijiez.luminarium.items;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.lumijiez.luminarium.blocks.BlockRegistrar.EXAMPLE_BLOCK;

public class ItemRegistrar {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Luminarium.MODID);
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));


    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
}
