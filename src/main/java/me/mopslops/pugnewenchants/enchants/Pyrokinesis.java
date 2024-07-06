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

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Pyrokinesis {

    public static final Enchantment PYROKINESIS;
    private static final Map<Integer, Integer> levelToChance = new HashMap<>();
    private static final NamespacedKey KEY = new NamespacedKey("myplugin", "pyrokinesis");

    static {
        levelToChance.put(1, 20);
        levelToChance.put(2, 40);
        levelToChance.put(3, 60);

        // Создание и регистрация зачарования
        PYROKINESIS = new EnchantmentWrapper(KEY.getKey()) {
            @Override
            public boolean canEnchantItem(@NotNull ItemStack item) {
                return EnchantmentTarget.WEAPON.includes(item) || EnchantmentTarget.BOW.includes(item);
            }

            @Override
            public boolean conflictsWith(@NotNull Enchantment other) {
                return false;
            }

            @Override
            public @NotNull EnchantmentTarget getItemTarget() {
                return EnchantmentTarget.WEAPON;
            }

            @Override
            public int getMaxLevel() {
                return 3;
            }

            @Override
            public @NotNull String getName() {
                return "Pyrokinesis";
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
                return Component.text("Pyrokinesis " + level);
            }

            @Override
            public int getAnvilCost() {
                return 1 + getMaxLevel();
            }

            @Override
            public float getDamageIncrease(int level, @NotNull EntityType entityType) {
                return 0;
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
                return KEY;
            }

            @Override
            public @NotNull String translationKey() {
                return "enchantment.pugnewenchants.pyrokinesis";
            }

            @Override
            public @NotNull String getTranslationKey() {
                return "enchantment.pugnewenchants.pyrokinesis";
            }

            public int getChance(int level) {
                return levelToChance.getOrDefault(level, 0);
            }
        };

        // Регистрация зачарования
        registerEnchantment(PYROKINESIS);
    }

    // Метод для регистрации зачарования через рефлексию
    private static void registerEnchantment(@NotNull Enchantment enchantment) {
        try {
            Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingNew.setAccessible(true);
            acceptingNew.set(null, true);

            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            byKeyField.setAccessible(true);
            Map<NamespacedKey, Enchantment> byKey = (Map<NamespacedKey, Enchantment>) byKeyField.get(null);
            byKey.put(enchantment.getKey(), enchantment);

            Field byNameField = Enchantment.class.getDeclaredField("byName");
            byNameField.setAccessible(true);
            Map<String, Enchantment> byName = (Map<String, Enchantment>) byNameField.get(null);
            byName.put(enchantment.getName(), enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
