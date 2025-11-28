package superherczeg.smoked;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

  public static final RegistryKey<ItemGroup> SMOKED_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,
      Identifier.of(Smoked.MOD_ID, "group"));

  public static void initialize() {
    Registry.register(Registries.ITEM_GROUP, SMOKED_GROUP_KEY,
        FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CIGARETTE))
            .displayName(Text.translatable("itemGroup." + Smoked.MOD_ID + ".group"))
            .entries((context, entries) -> {
              ItemStack red = new ItemStack(ModItems.VAPE);
              red.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(0xFF0000));
              entries.add(red);

              ItemStack green = new ItemStack(ModItems.VAPE);
              green.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(59009));
              entries.add(green);

              ItemStack purple = new ItemStack(ModItems.VAPE);
              purple.set(DataComponentTypes.DYED_COLOR, new DyedColorComponent(-59009));
              entries.add(purple);

              entries.add(ModItems.CIGARETTE);
            })
            .build());
  }
}
