package kz.hxncus.mc.advancedapi.bukkit.inventory.marker;

import kz.hxncus.mc.advancedapi.api.bukkit.inventory.marker.ItemMarker;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

public class UnavailableItemMarker implements ItemMarker {
	@Override
	public ItemStack markItem(@NonNull final ItemStack item) {
		return item;
	}
	
	@Override
	public ItemStack unmarkItem(@NonNull final ItemStack item) {
		return item;
	}
	
	@Override
	public boolean isItemMarked(@NonNull final ItemStack item) {
		return false;
	}
}