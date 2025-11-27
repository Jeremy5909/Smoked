package superherczeg.smoked;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smoked implements ModInitializer {
  public static final String MOD_ID = "smoked";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModItems.initialize();
  }
}
