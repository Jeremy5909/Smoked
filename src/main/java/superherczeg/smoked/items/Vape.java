package superherczeg.smoked.items;

import net.minecraft.item.consume.UseAction;
import net.minecraft.particle.ParticleTypes;

public class Vape extends Cigarette {
  public Vape(Settings settings) {
    super(settings);
    this.SMOKE_PARTICLE_TYPE = ParticleTypes.CAMPFIRE_COSY_SMOKE;
    this.USE_ACTION = UseAction.TOOT_HORN;
  }

}
