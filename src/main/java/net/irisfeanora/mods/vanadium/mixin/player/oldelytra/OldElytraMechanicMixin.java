package net.irisfeanora.mods.vanadium.mixin.player.oldelytra;

import net.irisfeanora.mods.vanadium.VanadiumMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

// restore 1.15 elytra/levitation interactions

@Mixin(PlayerEntity.class)
public abstract class OldElytraMechanicMixin extends LivingEntity {

	protected OldElytraMechanicMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	abstract void startFallFlying();

	@Shadow
	PlayerAbilities abilities;

	@Overwrite
	public boolean checkFallFlying() {
		if(!this.onGround && !this.isFallFlying() && !this.isSubmergedInWater()){
			ItemStack chestItem = this.getEquippedStack(EquipmentSlot.CHEST);

			if(chestItem.isOf(Items.ELYTRA) && ElytraItem.isUsable(chestItem)) {
				this.startFallFlying();
				return true;
			}
		}

		return false;
	}
}
