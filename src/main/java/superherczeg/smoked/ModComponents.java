package superherczeg.smoked;

import com.mojang.serialization.Codec;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModComponents {
  public static final ComponentType<String> SmokeParticle = register("smoke_particle",
      ComponentType.<String>builder().codec(Codec.STRING).build());
  public static final ComponentType<Integer> HitInterval = register("hit_interval",
      ComponentType.<Integer>builder().codec(Codec.INT).build());
  public static final ComponentType<Integer> HitLength = register("hit_length",
      ComponentType.<Integer>builder().codec(Codec.INT).build());

  public static <T> ComponentType<T> register(String name, ComponentType<T> componentType) {
    Identifier id = Identifier.of(Smoked.MOD_ID, name);
    RegistryKey<ComponentType<?>> key = RegistryKey.of(RegistryKeys.DATA_COMPONENT_TYPE, id);

    return Registry.register(Registries.DATA_COMPONENT_TYPE, key, componentType);
  }

  public static void initialize() {

  }
}
