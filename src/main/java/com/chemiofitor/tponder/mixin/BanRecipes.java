package com.chemiofitor.tponder.mixin;

import com.chemiofitor.tponder.data.RemovedRecipes;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeManager.class)
public class BanRecipes {
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
}
