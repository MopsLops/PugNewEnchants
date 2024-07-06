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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CoreOfDestruction extends EnchantmentWrapper {
    public static final Enchantment CORE_OF_DESTRUCTION;
    private static final Map<Integer, Integer> levelToRadius = new HashMap<>();
    private static final Map<Integer, Integer> levelToPower = new HashMap<>();

    static {
        // Инициализация радиуса и силы взрыва для каждого уровня зачарования
        levelToRadius.put(1, 3);  // Примерные значения
        levelToRadius.put(2, 5);

        levelToPower.put(1, 5);   // Примерные значения
        levelToPower.put(2, 8);

        // Регистрация зачарования
        NamespacedKey key = new NamespacedKey("myplugin", "core_of_destruction");
        CORE_OF_DESTRUCTION = new CoreOfDestruction(key);
    }

    // Приватный конструктор с параметром NamespacedKey
    private CoreOfDestruction(NamespacedKey key) {
        super();
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return EnchantmentTarget.WEAPON.includes(item);
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        // Укажите конфликты с другими зачарованиями, если есть
        return false;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON; // Возвращаем нужный тип EnchantmentTarget
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public @NotNull String getName() {
        return "Core of Destruction";
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

    @Deprecated
    public @NotNull Component displayName(int level, @NotNull ItemStack item) {
        return Component.text("Core of Destruction " + level);
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
        return EnumSet.of(EquipmentSlot.HAND);
    }

    @Override
    public @NotNull Set<EquipmentSlotGroup> getActiveSlotGroups() {
        return Set.of();
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return new NamespacedKey("myplugin", "core_of_destruction");
    }

    @Override
    public @NotNull String translationKey() {
        return "enchantment.pugnewenchants.core_of_destruction";
    }

    public int getRadius(int level) {
        return levelToRadius.getOrDefault(level, 0);
    }

    public int getPower(int level) {
        return levelToPower.getOrDefault(level, 0);
    }

    @Override
    public @NotNull String getTranslationKey() {
        return "enchantment.pugnewenchants.core_of_destruction";
    }
}
