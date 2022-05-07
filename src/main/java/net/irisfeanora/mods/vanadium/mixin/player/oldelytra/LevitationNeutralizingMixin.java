package net.irisfeanora.mods.vanadium.mixin.player.oldelytra;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LevitationNeutralizingMixin extends Entity {

	public LevitationNeutralizingMixin(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	public abstract boolean isFallFlying();

	@Redirect(
			method = "Lnet/minecraft/entity/LivingEntity;travel(Lnet/minecraft/util/math/Vec3d;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z",
					ordinal = 2
			)
	)
	public boolean disableLevitationMovementWhenFlying(LivingEntity entity, StatusEffect effect) {
		if(isFallFlying()) {
			return false;
		} else {
			return entity.hasStatusEffect(effect);
		}
	}

	@Redirect(
			method = "Lnet/minecraft/entity/LivingEntity;tickFallFlying()V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"
			)
	)
	public boolean suppressLevitationDetectionWhenFlying(LivingEntity entity, StatusEffect effect) {
		return false;
	}
}
