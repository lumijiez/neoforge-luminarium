package com.lumijiez.luminarium.data;

import com.lumijiez.luminarium.Luminarium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class LumRecipeProvider extends RecipeProvider {
    public LumRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Luminarium.LUMINARIUM_PIE)
                .requires(Items.PUMPKIN_PIE)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_item", has(Items.GOLD_INGOT))
                .save(pRecipeOutput);
    }
}
