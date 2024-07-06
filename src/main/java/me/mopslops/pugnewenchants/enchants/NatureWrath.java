package me.mopslops.pugnewenchants.enchants;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.EnumSet;

public class NatureWrath extends EnchantmentWrapper {
    public static final Enchantment NATURE_WRATH;
    private static final Map<Integer, Integer> levelToDuration = new HashMap<>();
    private static final Map<Integer, Integer> levelToStrength = new HashMap<>();

    static {
        // Инициализация продолжительности и прочности лиан для каждого уровня зачарования
        levelToDuration.put(1, 10); // Примерная продолжительность в тиках для уровня 1
        levelToDuration.put(2, 20); // Примерная продолжительность в тиках для уровня 2
        levelToDuration.put(3, 30); // Примерная продолжительность в тиках для уровня 3

        levelToStrength.put(1, 1); // Прочность лиан для уровня 1
        levelToStrength.put(2, 2); // Прочность лиан для уровня 2
        levelToStrength.put(3, 3); // Прочность лиан для уровня 3

        // Регистрация зачарования
        NamespacedKey key = new NamespacedKey("myplugin", "nature_wrath");
        NATURE_WRATH = new NatureWrath(key);
    }

    // Конструктор должен быть приватным и принимать NamespacedKey
    private NatureWrath(NamespacedKey key) {
        super();
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return EnchantmentTarget.WEAPON.includes(item) || EnchantmentTarget.TOOL.includes(item);
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        // Укажите конфликты с другими зачарованиями, если есть
        return false;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON; // Возвращаем тип предметов, которые можно зачаровать
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public @NotNull String getName() {
        return "Nature's Wrath";
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Nature's Wrath " + level);
    }

    @Override
    public int getAnvilCost() {
        return 1 + getMaxLevel();
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityType entityType) {
        return 0; // Не увеличивает урон
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return EnumSet.of(EquipmentSlot.HAND, EquipmentSlot.OFF_HAND);
    }

    @Override
    public @NotNull Set<EquipmentSlotGroup> getActiveSlotGroups() {
        return Set.of();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey("myplugin", "nature_wrath");
    }

    @Override
    public @NotNull String translationKey() {
        return "enchantment.pugnewenchants.natures_wrath";
    }

    public int getDuration(int level) {
        return levelToDuration.getOrDefault(level, 0);
    }

    public int getStrength(int level) {
        return levelToStrength.getOrDefault(level, 0);
    }

    @Override
    public @NotNull String getTranslationKey() {
        return "enchantment.pugnewenchants.nature_wrath";
    }
}
