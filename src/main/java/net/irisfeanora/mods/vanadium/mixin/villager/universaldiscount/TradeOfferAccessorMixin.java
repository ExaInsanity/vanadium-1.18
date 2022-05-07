package net.irisfeanora.mods.vanadium.mixin.villager.universaldiscount;

import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TradeOffer.class)
public interface TradeOfferAccessorMixin {

	@Accessor("firstBuyItem")
	public void setFirstBuyItem(ItemStack firstBuyItem);
}
