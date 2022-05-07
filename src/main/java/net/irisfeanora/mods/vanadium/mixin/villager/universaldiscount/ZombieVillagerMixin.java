package net.irisfeanora.mods.vanadium.mixin.villager.universaldiscount;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerMixin {

	@Shadow
	public NbtCompound offerData;

	@Inject(
			method = "Lnet/minecraft/entity/mob/ZombieVillagerEntity;finishConversion(Lnet/minecraft/server/world/ServerWorld;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/passive/VillagerEntity;setExperience(I)V"
			),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void universallyReducePrices(ServerWorld world, CallbackInfo ci, VillagerEntity entity)
	{
		if(this.offerData == null) {
			return;
		}

		TradeOfferList offers = entity.getOffers();

		for(int i = 0; i < offers.size(); i++) {
			TradeOffer offer = offers.get(i);

			ItemStack itemStack = offer.getOriginalFirstBuyItem();

			int newCount = itemStack.getCount() - 7;

			itemStack.setCount(Math.max(newCount, 1));

			((TradeOfferAccessorMixin)offer).setFirstBuyItem(itemStack);

			offers.set(i, offer);
		}
	}
}
