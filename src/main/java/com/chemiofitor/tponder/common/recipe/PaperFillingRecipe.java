package com.chemiofitor.tponder.common.recipe;

import com.google.gson.JsonObject;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class PaperFillingRecipe extends FillingRecipe {
    public PaperFillingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(params);
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level p_77569_2_) {
        if (!inv.getItem(0).getOrCreateTag().getString("type").isEmpty())
            return false;
        return ingredients.get(0).test(inv.getItem(0));
    }

    @Override
    public void readAdditional(JsonObject json) {
        super.readAdditional(json);
        String paper = GsonHelper.getAsString(json, "paper");
        results.get(0).getStack().getOrCreateTag().putString("type", paper);
    }

    @Override
    public void writeAdditional(JsonObject json) {
        super.writeAdditional(json);
        String paper = results.get(0).getStack().getOrCreateTag().getString("type");
        json.addProperty("paper", paper);
    }

    @Override
    public void readAdditional(FriendlyByteBuf buffer) {
        super.readAdditional(buffer);
        String paper = buffer.readUtf();
        results.get(0).getStack().getOrCreateTag().putString("type", paper);
    }

    @Override
    public void writeAdditional(FriendlyByteBuf buffer) {
        super.writeAdditional(buffer);

        buffer.writeUtf(results.get(0).getStack().getOrCreateTag().getString("type"));
    }
}
