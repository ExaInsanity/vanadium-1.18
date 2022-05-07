package net.irisfeanora.mods.vanadium.mixin.villager.nodemand;

import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TradeOffer.class)
public class NoDemandMixin {

	@Shadow
	protected int demandBonus;

	@Overwrite
	public void updatePriceOnDemand() {
		this.demandBonus = 0;
	}
}
