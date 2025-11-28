package superherczeg.smoked.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import superherczeg.smoked.Smoked;

public class Buzz extends StatusEffect {
  public Buzz() {
    super(StatusEffectCategory.BENEFICIAL, 0x6bf3ff);
    this.addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, Identifier.of(Smoked.MOD_ID, "buzz_speed"), 0.1,
        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    this.addAttributeModifier(EntityAttributes.JUMP_STRENGTH, Identifier.of(Smoked.MOD_ID, "buzz_jump"), 0.1,
        EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
  }

  // @Override
  // public boolean canApplyUpdateEffect(int duration, int amplifier) {
  // return true;
  // }

}
