package superherczeg.smoked.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Cigarette extends Item {
  public Cigarette(Settings settings) {
    super(settings);
  }

  public Cigarette(RegistryKey<Item> registryKey) {
    super(new Item.Settings().registryKey(registryKey));
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

  @Override
  public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
    if (world.isClient()) {
      Vec3d pos = user.getCameraPosVec(1.0F).add(user.getRotationVec(1.0F).multiply(0.5));
      // Spawn a bunch of smoke particles
      for (int i = 0; i < 10; i++) {
        double offsetX = (world.random.nextDouble() - 0.5) * 0.2;
        double offsetY = world.random.nextDouble() * 0.2;
        double offsetZ = (world.random.nextDouble() - 0.5) * 0.2;

        world.addParticleClient(
            ParticleTypes.SMOKE,
            pos.x + offsetX,
            pos.y + offsetY,
            pos.z + offsetZ,
            0.0,
            0.05,
            0.0);
      }

    }

    return stack;
  }

}
