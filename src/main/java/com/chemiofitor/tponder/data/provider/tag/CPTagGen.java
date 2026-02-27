package com.chemiofitor.tponder.data.provider.tag;

import com.chemiofitor.tponder.CreatePaper;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import java.util.function.Function;

public abstract class CPTagGen<T> {
    protected final Provider<T> prov;

    public CPTagGen(RegistrateTagsProvider.IntrinsicImpl<T> provIn, Function<T, ResourceKey<T>> resourceKeyFunction) {
        prov = new Provider<>(provIn, resourceKeyFunction);
    }

    public record Provider<T>(RegistrateTagsProvider<T> provider, Function<T, ResourceKey<T>> keyExtractor) {
        public TagGen.CreateTagAppender<T> tag(TagKey<T> tag) {
            TagBuilder tagbuilder = getOrCreateRawBuilder(tag);
            return new TagGen.CreateTagAppender<>(tagbuilder, keyExtractor, CreatePaper.MOD_ID);
        }

        public TagBuilder getOrCreateRawBuilder(TagKey<T> tag) {
            return provider.addTag(tag).getInternalBuilder();
        }
    }
}
