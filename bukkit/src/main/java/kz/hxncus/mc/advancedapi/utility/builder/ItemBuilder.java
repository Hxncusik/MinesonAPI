package kz.hxncus.mc.advancedapi.utility.builder;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Class Item builder.
 *
 * @author Hxncus
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class ItemBuilder {
	private final ItemStack itemStack;
	private ItemMeta itemMeta;
	
	/**
	 * Instantiates a new Item builder.
	 *
	 * @param itemStack the item stack
	 */
	public ItemBuilder(final ItemStack itemStack) {
		this.itemStack = itemStack;
		this.itemMeta = itemStack.getItemMeta();
	}
	
	/**
	 * Instantiates a new Item builder.
	 *
	 * @param type the type
	 */
	public ItemBuilder(final Material type) {
		this.itemStack = new ItemStack(type);
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	/**
	 * Instantiates a new Item builder.
	 *
	 * @param type   the type
	 * @param amount the amount
	 */
	public ItemBuilder(final Material type, final int amount) {
		this.itemStack = new ItemStack(type, amount);
		this.itemMeta = this.itemStack.getItemMeta();
	}
	
	/**
	 * Display name an item builder.
	 *
	 * @param name the name
	 * @return the item builder
	 */
	public ItemBuilder setDisplayName(final String name) {
		this.itemMeta.setDisplayName(name);
		return this;
	}
	
	/**
	 * Lore string item builder.
	 *
	 * @param lore the lore
	 * @return the item builder
	 */
	public ItemBuilder setLore(final List<String> lore) {
		this.itemMeta.setLore(lore);
		return this;
	}
	
	/**
	 * Lore item builder.
	 *
	 * @param lore the lore
	 * @return the item builder
	 */
	public ItemBuilder setLore(final String lore) {
		this.itemMeta.setLore(Collections.singletonList(lore));
		return this;
	}
	
	/**
	 * Lore item builder.
	 *
	 * @param lore the lore
	 * @return the item builder
	 */
	public ItemBuilder setLore(final String... lore) {
		this.itemMeta.setLore(Arrays.asList(lore));
		return this;
	}
	
	/**
	 * Add lore item builder.
	 *
	 * @param line the line
	 * @return the item builder
	 */
	public ItemBuilder addLore(final String line) {
		final List<String> lore = this.itemMeta.getLore();
		if (lore == null) {
			this.itemMeta.setLore(Collections.singletonList(line));
		} else {
			lore.add(line);
		}
		return this;
	}
	
	/**
	 * Add lore item builder.
	 *
	 * @param lines the lines
	 * @return the item builder
	 */
	@SuppressWarnings("TypeMayBeWeakened")
	public ItemBuilder addLore(final List<String> lines) {
		final List<String> lore = this.itemMeta.getLore();
		if (lore == null) {
			this.itemMeta.setLore(lines);
		} else {
			lore.addAll(lines);
		}
		return this;
	}
	
	/**
	 * Add lore item builder.
	 *
	 * @param lines the lines
	 * @return the item builder
	 */
	public ItemBuilder addLore(final String... lines) {
		for (String line : lines) {
			addLore(line);
		}
		return this;
	}
	
	/**
	 * Sets material.
	 *
	 * @param material the material
	 * @return the material
	 */
	public ItemBuilder setType(final Material material) {
		this.itemStack.setType(material);
		if (this.itemMeta == null) {
			this.itemMeta = this.itemStack.getItemMeta();
		}
		return this;
	}
	
	/**
	 * Sets material.
	 *
	 * @param material the material
	 * @return the material
	 */
	public ItemBuilder setMaterial(final Material material) {
		return this.setType(material);
	}
	
	/**
	 * Amount item builder.
	 *
	 * @param amount the amount
	 * @return the item builder
	 */
	public ItemBuilder setAmount(final int amount) {
		this.itemStack.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setQuantity(final int quantity) {
		this.itemStack.setAmount(quantity);
		return this;
	}
	
	/**
	 * Color item builder.
	 *
	 * @param color the color
	 * @return the item builder
	 */
	public ItemBuilder color(final int color) {
		return this.color(Color.fromRGB(color));
	}
	
	/**
	 * Color item builder.
	 *
	 * @param color the color
	 * @return the item builder
	 */
	public ItemBuilder color(@NonNull final Color color) {
		if (this.itemMeta instanceof LeatherArmorMeta) {
			((LeatherArmorMeta) this.itemMeta).setColor(color);
		} else if (this.itemStack.getType()
		                         .name()
		                         .endsWith("_wool")) {
			final DyeColor dyeColor = DyeColor.getByColor(color);
			if (dyeColor == null) {
				return this;
			}
			this.setData(dyeColor.getWoolData());
		}
		return this;
	}
	
	
	/**
	 * Sets data.
	 *
	 * @param data the data
	 * @return the data
	 */
	public ItemBuilder setData(final int data) {
		this.itemStack.setDurability((short) data);
		return this;
	}
	
	/**
	 * Color item builder.
	 *
	 * @param color the color
	 * @return the item builder
	 */
	public ItemBuilder color(@NonNull final DyeColor color) {
		return this.color(color.getColor());
	}
	
	/**
	 * Enchant item builder.
	 *
	 * @param enchantment the enchantment
	 * @param level       the level
	 * @param override    the override
	 * @return the item builder
	 */
	public ItemBuilder addEnchant(final Enchantment enchantment, final int level, final boolean override) {
		this.itemMeta.addEnchant(enchantment, level, override);
		return this;
	}
	
	/**
	 * Enchant item builder.
	 *
	 * @param enchantment the enchantment
	 * @param level       the level
	 * @return the item builder
	 */
	public ItemBuilder addEnchant(final Enchantment enchantment, final int level) {
		return this.addEnchant(enchantment, level, true);
	}
	
	/**
	 * Remove enchant item builder.
	 *
	 * @param enchantment the enchantment
	 * @return the item builder
	 */
	public ItemBuilder removeEnchant(final Enchantment enchantment) {
		this.itemMeta.removeEnchant(enchantment);
		return this;
	}
	
	/**
	 * Clear enchants item builder.
	 *
	 * @return the item builder
	 */
	public ItemBuilder clearEnchants() {
		this.itemMeta.getEnchants()
		             .forEach((enchantment, level) -> this.itemMeta.removeEnchant(enchantment));
		return this;
	}
	
	/**
	 * Flags item builder.
	 *
	 * @return the item builder
	 */
	public ItemBuilder addAllFlags() {
		return this.addFlags(ItemFlag.values());
	}
	
	/**
	 * Flags item builder.
	 *
	 * @param flags the flags
	 * @return the item builder
	 */
	public ItemBuilder addFlags(final ItemFlag... flags) {
		this.itemMeta.addItemFlags(flags);
		return this;
	}
	
	/**
	 * Remove flags item builder.
	 *
	 * @return the item builder
	 */
	public ItemBuilder clearFlags() {
		return this.removeFlags(ItemFlag.values());
	}
	
	/**
	 * Remove flags item builder.
	 *
	 * @param flags the flags
	 * @return the item builder
	 */
	public ItemBuilder removeFlags(final ItemFlag... flags) {
		this.itemMeta.removeItemFlags(flags);
		return this;
	}
	
	/**
	 * Sets pdc.
	 *
	 * @param <T>           the type parameter
	 * @param namespacedKey the namespaced key
	 * @param pdt           the pdt
	 * @param value         the value
	 * @return the pdc
	 */
	public <T, Z> ItemBuilder setPDC(final NamespacedKey namespacedKey, final PersistentDataType<T, Z> pdt, final Z value) {
		this.itemMeta.getPersistentDataContainer()
		             .set(namespacedKey, pdt, value);
		return this;
	}
	
	/**
	 * Gets pdc.
	 *
	 * @param <T>           the type parameter
	 * @param namespacedKey the namespaced key
	 * @param pdt           the pdt
	 * @return the pdc
	 */
	public <T, Z> Z getPDC(final NamespacedKey namespacedKey, final PersistentDataType<T, Z> pdt) {
		return this.itemMeta.getPersistentDataContainer()
		                    .get(namespacedKey, pdt);
	}
	
	/**
	 * Gets or default pdc.
	 *
	 * @param <T>           the type parameter
	 * @param namespacedKey the namespaced key
	 * @param pdt           the pdt
	 * @param def           the def
	 * @return the or default pdc
	 */
	public <T, Z> Z getOrDefaultPDC(final NamespacedKey namespacedKey, final PersistentDataType<T, Z> pdt, final Z def) {
		final Z value = this.itemMeta.getPersistentDataContainer()
		                             .get(namespacedKey, pdt);
		return value == null ? def : value;
	}
	
	/**
	 * Has pdc boolean.
	 *
	 * @param <T>           the type parameter
	 * @param namespacedKey the namespaced key
	 * @param pdt           the pdt
	 * @return the boolean
	 */
	public <T> boolean hasPDC(final NamespacedKey namespacedKey, final PersistentDataType<T, T> pdt) {
		return this.itemMeta.getPersistentDataContainer()
		                    .has(namespacedKey, pdt);
	}
	
	/**
	 * Remove pdc item builder.
	 *
	 * @param namespacedKey the namespaced key
	 * @return the item builder
	 */
	public ItemBuilder removePDC(final NamespacedKey namespacedKey) {
		this.itemMeta.getPersistentDataContainer()
		             .remove(namespacedKey);
		return this;
	}
	
	/**
	 * Gets pdc keys.
	 *
	 * @return the pdc keys
	 */
	public Set<NamespacedKey> getPDCKeys() {
		return this.itemMeta.getPersistentDataContainer()
		                    .getKeys();
	}
	
	/**
	 * Meta item builder.
	 *
	 * @param <T>          the type parameter
	 * @param metaClass    the meta-class
	 * @param metaConsumer the meta-consumer
	 * @return the item builder
	 */
	public <T extends ItemMeta> ItemBuilder meta(final Class<T> metaClass, final Consumer<? super T> metaConsumer) {
		if (metaClass.isInstance(this.itemMeta)) {
			metaConsumer.accept(metaClass.cast(this.itemMeta));
		}
		return this;
	}
	
	/**
	 * Build item stack.
	 *
	 * @return the item stack
	 */
	public ItemStack build() {
		this.itemStack.setItemMeta(this.itemMeta);
		return this.itemStack;
	}
}