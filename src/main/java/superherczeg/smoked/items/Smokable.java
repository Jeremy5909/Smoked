package superherczeg.smoked.items;

import superherczeg.smoked.ModComponents;
import superherczeg.smoked.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Smokable extends Item {
  public Smokable(Settings settings) {
    super(settings);
  }

  @Override
  public int getMaxUseTime(ItemStack stack, LivingEntity user) {
    return 4096;
  }

  @Override
  public ActionResult use(World world, PlayerEntity user, Hand hand) {
    user.setCurrentHand(hand);
    return ActionResult.CONSUME;
  }

  @Override
  public UseAction getUseAction(ItemStack stack) {
    return UseAction.BOW;
  }

  protected SimpleParticleType getSmokeParticle(ItemStack stack) {
    switch (stack.getOrDefault(ModComponents.SmokeParticle, "none")) {
      case "dark":
        return ParticleTypes.SMOKE;
      case "light":
        return ParticleTypes.CAMPFIRE_COSY_SMOKE;
      default:
        return ParticleTypes.SMOKE;
    }
  }

  public void spawnParticles(World world, LivingEntity user, SimpleParticleType particleType, double distance,
      double xSpread, double ySpread, double zSpread, double xVelSpread, double yVelSpread, double zVelSpread) {
    Vec3d pos = user.getCameraPosVec(1.0F)
        .add(user.getRotationVec(1.0F).multiply(distance));

    double offsetX = (world.random.nextDouble() - 0.5) * xSpread;
    double offsetY = (world.random.nextDouble() - 0.5) * ySpread;
    double offsetZ = (world.random.nextDouble() - 0.5) * zSpread;

    double xVel = (world.random.nextDouble() - 0.5) * xVelSpread;
    double yVel = (world.random.nextDouble() - 0.5) * yVelSpread;
    double zVel = (world.random.nextDouble() - 0.5) * zVelSpread;

    world.addParticleClient(
        particleType,
        pos.x + offsetX,
        pos.y + offsetY,
        pos.z + offsetZ,
        xVel, yVel, zVel);
  }

  int ticks_smoked = 0;

  @Override
  public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
    int ticksUsed = stack.getMaxUseTime(user) - remainingUseTicks;

    if (!world.isClient()) {

      // Every 2 seconds
      if (ticksUsed % 40 == 0) {
        user.addStatusEffect(
            new StatusEffectInstance(StatusEffects.SATURATION));

        int currentDuration = 0;
        if (user.hasStatusEffect(ModEffects.BUZZ)) {
          currentDuration = user.getStatusEffect(ModEffects.BUZZ).getDuration();
        }
        // Add 8 seconds for every 2 seconds held
        int newDuration = currentDuration + 20 * 8;
        user.addStatusEffect(
            new StatusEffectInstance(ModEffects.BUZZ, newDuration));

      }
      // Every 4 seconds on average
    } else if (world.random.nextInt(20 * 4) == 0) {
      spawnParticles(world, user, getSmokeParticle(stack), 0.4, 0.1, 0.1, 0.1, 0.0, 0.01, 0.0);
    }

  }

  @Override
  public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
    if (world.isClient()) {

      Vec3d pos = user.getCameraPosVec(1.0F)
          .add(user.getRotationVec(1.0F).multiply(0.5));

      int heldTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
      int damageAmount = Math.max(1, heldTicks / 20);
      int particleCount = heldTicks / 4;

      stack.damage(damageAmount, (PlayerEntity) user);

      for (int i = 0; i < particleCount; i++) {
        spawnParticles(world, user, getSmokeParticle(stack), 0.5, 0.5, 0.5, 0.2, 0.03, 0.03, 0.03);
      }
    }

    return true;
  }

}
