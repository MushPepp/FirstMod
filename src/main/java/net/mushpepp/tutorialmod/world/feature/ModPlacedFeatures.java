package net.mushpepp.tutorialmod.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mushpepp.tutorialmod.TutorialMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TutorialMod.MOD_ID);

    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED = PLACED_FEATURES.register("zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(),
                    commonOrePlacement(7,//veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-30), VerticalAnchor.aboveBottom(40))))
            );
    public static final RegistryObject<PlacedFeature> END_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("end_zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(),
                    commonOrePlacement(7,//veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-30), VerticalAnchor.aboveBottom(40))))
    );
    public static final RegistryObject<PlacedFeature> NETHER_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("nether_zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(),
                    commonOrePlacement(7,//veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-30), VerticalAnchor.aboveBottom(40))))
    );


    public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier1, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), placementModifier);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
