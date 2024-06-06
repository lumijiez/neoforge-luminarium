package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.items.LItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class LRecipeProvider extends RecipeProvider {
    public LRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Luminarium.LUMINARIUM_PIE)
//                .pattern("a b")
//                .define('a', Items.PUMPKIN_PIE.asItem())
//                .define('b', Items.GOLD_INGOT.asItem())
//                .unlockedBy("has_item", has(Items.GOLD_INGOT))
//                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, LItems.LUMINARIUM_PIE)
                .requires(Items.PUMPKIN_PIE)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item", has(Items.GOLD_INGOT))
                .save(pRecipeOutput);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LItems.RADIANT_ORE_ITEM, LItems.RAW_LUMINARIUM_ORE), RecipeCategory.MISC, LItems.LUMINARIUM_INGOT, 10f, 200)
                .unlockedBy("has_item", has(LItems.RADIANT_ORE_ITEM))
                .save(pRecipeOutput);

    }
}
