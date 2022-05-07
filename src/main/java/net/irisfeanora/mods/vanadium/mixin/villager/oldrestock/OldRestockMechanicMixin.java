package net.irisfeanora.mods.vanadium.mixin.villager.oldrestock;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VillagerEntity.class)
public abstract class OldRestockMechanicMixin extends MerchantEntity {

	public OldRestockMechanicMixin(EntityType<? extends MerchantEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	public abstract void updateDemandBonus();

	@Shadow
	public abstract VillagerData getVillagerData();

	@Shadow
	public abstract void craftBread();

	@Shadow
	public long lastRestockTime;

	@Overwrite
	public void restock() {
		this.updateDemandBonus();

		for(TradeOffer offer : this.getOffers()) {
			offer.resetUses();
		}

		if(this.getVillagerData().getProfession() == VillagerProfession.FARMER) {
			this.craftBread();
		}

		this.lastRestockTime = this.world.getTime();
	}
}
