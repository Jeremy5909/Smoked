package superherczeg.smoked;

import java.util.function.Function;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import superherczeg.smoked.items.Cigarette;
import superherczeg.smoked.items.Vape;

public class ModItems {
  public static final Item CIGARETTE = register("cigarette", Cigarette::new, new Item.Settings());
  public static final Item VAPE = register("vape", Vape::new, new Item.Settings());

  public static Item register(String name, Item.Settings settings) {
    Identifier id = Identifier.of(Smoked.MOD_ID, name);
    RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

    return Registry.register(Registries.ITEM, key, new Item(settings.registryKey(key)));
  }

  public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
    Identifier id = Identifier.of(Smoked.MOD_ID, name);
    RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
    Item item = itemFactory.apply(settings.registryKey(key));

    return Registry.register(Registries.ITEM, key, item);
  }

  public static void initialize() {
  }
}
