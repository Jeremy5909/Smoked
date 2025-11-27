package superherczeg.smoked.items;

import net.minecraft.entity.LivingEntity;
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

public class Cigarette extends Item {
  public SimpleParticleType SMOKE_PARTICLE_TYPE = ParticleTypes.SMOKE;
  public UseAction USE_ACTION = UseAction.BOW;

  public Cigarette(Settings settings) {
    super(settings.maxDamage(10));
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
    return USE_ACTION;
  }

  @Override
  public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
    if (world.isClient()) {
      if (world.random.nextInt(40) == 0) {
        Vec3d pos = user.getCameraPosVec(1.0F)
            .add(user.getRotationVec(1.0F).multiply(0.4));

        double offsetX = (world.random.nextDouble() - 0.5) * 0.1;
        double offsetY = world.random.nextDouble() * 0.1;
        double offsetZ = (world.random.nextDouble() - 0.5) * 0.1;

        world.addParticleClient(
            SMOKE_PARTICLE_TYPE,
            pos.x + offsetX,
            pos.y + offsetY,
            pos.z + offsetZ,
            0.0, 0.01, 0.0);

      }
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
        double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
        double offsetY = (world.random.nextDouble() - 0.5) * 0.5;
        double offsetZ = (world.random.nextDouble() - 0.5) * 0.2;

        double velX = (world.random.nextDouble() - 0.5) * 0.03; // slight drift
        double velY = world.random.nextDouble() * 0.03; // slow upward motion
        double velZ = (world.random.nextDouble() - 0.5) * 0.03; // slight drift

        world.addParticleClient(
            SMOKE_PARTICLE_TYPE,
            pos.x + offsetX,
            pos.y + offsetY,
            pos.z + offsetZ,
            velX,
            velY,
            velZ);
      }
    }

    return true;
  }

}
