package com.chemiofitor.tponder.mixin;

import com.chemiofitor.tponder.data.RemovedRecipes;
import com.chemiofitor.tponder.data.accessor.RecipesGetter;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class BanRecipes implements RecipesGetter {
    @Redirect(
            remap = false,
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;"
            )
    )
    public <K, V> ImmutableMap.Builder<K, V> ban(
            ImmutableMap.Builder<K, V> instance, K key, V value
    ) {
        if (key instanceof ResourceLocation resource) {
            for (ResourceLocation remove : RemovedRecipes.LIST) {
                if (remove.equals(resource)) {
                    return instance;
                }
            }
        }
        return instance.put(key, value);
    }

    @Accessor("recipes")
    @Override
    public abstract Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> cpaper$recipes();
}
