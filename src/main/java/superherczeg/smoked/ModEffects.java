package superherczeg.smoked;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import superherczeg.smoked.effects.Buzz;

public class ModEffects {
  public static final RegistryEntry<StatusEffect> BUZZ = register("buzz", new Buzz());

  public static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
    Identifier id = Identifier.of(Smoked.MOD_ID, name);
    RegistryKey<StatusEffect> key = RegistryKey.of(RegistryKeys.STATUS_EFFECT, id);

    return Registry.registerReference(Registries.STATUS_EFFECT, key, statusEffect);
  }

  public static void initialize() {
  }
}
