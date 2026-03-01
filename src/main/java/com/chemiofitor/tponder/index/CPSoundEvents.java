package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.simibubi.create.AllSoundEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CPSoundEvents {
    public static final Map<ResourceLocation, AllSoundEvents.SoundEntry> ALL = new HashMap<>();

    public static final AllSoundEvents.SoundEntry
            PULPING = create("pulping").subtitle("Pulping noises")
			.playExisting(SoundEvents.GILDED_BLACKSTONE_BREAK, .125f, .5f)
			.playExisting(SoundEvents.NETHERRACK_BREAK, .125f, .5f)
			.category(SoundSource.BLOCKS)
			.build();

    public static AllSoundEvents.SoundEntryBuilder create(String id) {
        return new AllSoundEvents.SoundEntryBuilder(CreatePaper.asResource(id)) {
            @Override
            public AllSoundEvents.SoundEntry build() {
                AllSoundEvents.SoundEntry entry =
                        wrappedEvents.isEmpty() ? new CPSoundEvents.CustomSoundEntry(id, variants, subtitle, category, attenuationDistance)
                                : new CPSoundEvents.WrappedSoundEntry(id, subtitle, wrappedEvents, category, attenuationDistance);
                ALL.put(entry.getId(), entry);
                return entry;
            }
        };
    }

    public static void prepare() {
        for (AllSoundEvents.SoundEntry entry : ALL.values())
            entry.prepare();
    }

    public static void register(RegisterEvent event) {
        event.register(Registries.SOUND_EVENT, helper -> {
            for (AllSoundEvents.SoundEntry entry : ALL.values())
                entry.register(helper);
        });
    }

    public static CPSoundEvents.SoundEntryProvider provider(DataGenerator generator) {
        return new CPSoundEvents.SoundEntryProvider(generator);
    }

    public static class SoundEntryProvider implements DataProvider {

        private PackOutput output;

        public SoundEntryProvider(DataGenerator generator) {
            output = generator.getPackOutput();
        }

        @Override
        public CompletableFuture<?> run(CachedOutput cache) {
            return generate(output.getOutputFolder(), cache);
        }

        @Override
        public String getName() {
            return "Create Paper's Custom Sounds";
        }

        public CompletableFuture<?> generate(Path path, CachedOutput cache) {
            path = path.resolve("assets/cpaper");
            JsonObject json = new JsonObject();
            ALL.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        entry.getValue()
                                .write(json);
                    });
            return DataProvider.saveStable(cache, json, path.resolve("sounds.json"));
        }

    }

    public static void provideLang(BiConsumer<String, String> consumer) {
        for (AllSoundEvents.SoundEntry entry : ALL.values())
            if (entry.hasSubtitle())
                consumer.accept(entry.getSubtitleKey(), entry.getSubtitle());
    }

    private static class WrappedSoundEntry extends AllSoundEvents.SoundEntry {

        private final List<AllSoundEvents.ConfiguredSoundEvent> wrappedEvents;
        private final List<CPSoundEvents.WrappedSoundEntry.CompiledSoundEvent> compiledEvents;

        public WrappedSoundEntry(ResourceLocation id, String subtitle,
                                 List<AllSoundEvents.ConfiguredSoundEvent> wrappedEvents, SoundSource category, int attenuationDistance) {
            super(id, subtitle, category, attenuationDistance);
            this.wrappedEvents = wrappedEvents;
            compiledEvents = new ArrayList<>();
        }

        @Override
        public void prepare() {
            for (int i = 0; i < wrappedEvents.size(); i++) {
                AllSoundEvents.ConfiguredSoundEvent wrapped = wrappedEvents.get(i);
                ResourceLocation location = getIdOf(i);
                RegistryObject<SoundEvent> event = RegistryObject.create(location, ForgeRegistries.SOUND_EVENTS);
                compiledEvents.add(new CPSoundEvents.WrappedSoundEntry.CompiledSoundEvent(event, wrapped.volume(), wrapped.pitch()));
            }
        }

        @Override
        public void register(RegisterEvent.RegisterHelper<SoundEvent> helper) {
            for (CPSoundEvents.WrappedSoundEntry.CompiledSoundEvent compiledEvent : compiledEvents) {
                ResourceLocation location = compiledEvent.event().getId();
                if (location != null) {
                    helper.register(location, SoundEvent.createVariableRangeEvent(location));
                }
            }
        }

        @Override
        public SoundEvent getMainEvent() {
            return compiledEvents.get(0)
                    .event().get();
        }

        protected ResourceLocation getIdOf(int i) {
            return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), i == 0 ? id.getPath() : id.getPath() + "_compounded_" + i);
        }

        @Override
        public void write(JsonObject json) {
            for (int i = 0; i < wrappedEvents.size(); i++) {
                AllSoundEvents.ConfiguredSoundEvent event = wrappedEvents.get(i);
                JsonObject entry = new JsonObject();
                JsonArray list = new JsonArray();
                JsonObject s = new JsonObject();
                s.addProperty("name", event.event()
                        .get()
                        .getLocation()
                        .toString());
                s.addProperty("type", "event");
                if (attenuationDistance != 0)
                    s.addProperty("attenuation_distance", attenuationDistance);
                list.add(s);
                entry.add("sounds", list);
                if (i == 0 && hasSubtitle())
                    entry.addProperty("subtitle", getSubtitleKey());
                json.add(getIdOf(i).getPath(), entry);
            }
        }

        @Override
        public void play(Level world, Player entity, double x, double y, double z, float volume, float pitch) {
            for (CPSoundEvents.WrappedSoundEntry.CompiledSoundEvent event : compiledEvents) {
                world.playSound(entity, x, y, z, event.event().get(), category, event.volume() * volume,
                        event.pitch() * pitch);
            }
        }

        @Override
        public void playAt(Level world, double x, double y, double z, float volume, float pitch, boolean fade) {
            for (CPSoundEvents.WrappedSoundEntry.CompiledSoundEvent event : compiledEvents) {
                world.playLocalSound(x, y, z, event.event().get(), category, event.volume() * volume,
                        event.pitch() * pitch, fade);
            }
        }

        private record CompiledSoundEvent(RegistryObject<SoundEvent> event, float volume, float pitch) {
        }

    }

    private static class CustomSoundEntry extends AllSoundEvents.SoundEntry {

        protected List<ResourceLocation> variants;
        protected RegistryObject<SoundEvent> event;

        public CustomSoundEntry(ResourceLocation id, List<ResourceLocation> variants, String subtitle,
                                SoundSource category, int attenuationDistance) {
            super(id, subtitle, category, attenuationDistance);
            this.variants = variants;
        }

        @Override
        public void prepare() {
            event = RegistryObject.create(id, ForgeRegistries.SOUND_EVENTS);
        }

        @Override
        public void register(RegisterEvent.RegisterHelper<SoundEvent> helper) {
            ResourceLocation location = event.getId();
            if (location != null) {
                helper.register(location, SoundEvent.createVariableRangeEvent(location));
            }
        }

        @Override
        public SoundEvent getMainEvent() {
            return event.get();
        }

        @Override
        public void write(JsonObject json) {
            JsonObject entry = new JsonObject();
            JsonArray list = new JsonArray();

            JsonObject s = new JsonObject();
            s.addProperty("name", id.toString());
            s.addProperty("type", "file");
            if (attenuationDistance != 0)
                s.addProperty("attenuation_distance", attenuationDistance);
            list.add(s);

            for (ResourceLocation variant : variants) {
                s = new JsonObject();
                s.addProperty("name", variant.toString());
                s.addProperty("type", "file");
                if (attenuationDistance != 0)
                    s.addProperty("attenuation_distance", attenuationDistance);
                list.add(s);
            }

            entry.add("sounds", list);
            if (hasSubtitle())
                entry.addProperty("subtitle", getSubtitleKey());
            json.add(id.getPath(), entry);
        }

        @Override
        public void play(Level world, Player entity, double x, double y, double z, float volume, float pitch) {
            world.playSound(entity, x, y, z, event.get(), category, volume, pitch);
        }

        @Override
        public void playAt(Level world, double x, double y, double z, float volume, float pitch, boolean fade) {
            world.playLocalSound(x, y, z, event.get(), category, volume, pitch, fade);
        }

    }

}
