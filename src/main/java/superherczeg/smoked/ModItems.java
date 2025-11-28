package superherczeg.smoked;

import java.util.function.Function;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import superherczeg.smoked.items.Smokable;

public class ModItems {
  public static final Item CIGARETTE = register("cigarette", Smokable::new,
      new Item.Settings().maxDamage(10));
  public static final Item VAPE = register("vape", Smokable::new,
      new Item.Settings().component(ModComponents.SmokeParticle, "light").maxDamage(10));

  public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
    Identifier id = Identifier.of(Smoked.MOD_ID, name);
    RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
    Item item = itemFactory.apply(settings.registryKey(key));

    return Registry.register(Registries.ITEM, key, item);
  }

  public static void initialize() {
  }
}
