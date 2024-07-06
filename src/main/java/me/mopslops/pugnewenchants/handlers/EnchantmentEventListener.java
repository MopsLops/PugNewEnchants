package me.mopslops.pugnewenchants.handlers;

import me.mopslops.pugnewenchants.enchants.CoreOfDestruction;
import me.mopslops.pugnewenchants.enchants.NatureWrath;
import me.mopslops.pugnewenchants.enchants.Pyrokinesis;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType; // Добавлен правильный импорт

public class EnchantmentEventListener implements Listener {

    private final JavaPlugin plugin;

    public EnchantmentEventListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack weapon = player.getInventory().getItemInMainHand();
            if (weapon.containsEnchantment(Pyrokinesis.PYROKINESIS)) {
                int level = weapon.getEnchantmentLevel(Pyrokinesis.PYROKINESIS);
                // Заменяем метод getChance на соответствующий метод из класса Pyrokinesis
                int chance = ((Pyrokinesis) Pyrokinesis.PYROKINESIS).getChance(level);
                if (Math.random() * 100 < chance) {
                    Entity target = event.getEntity();
                    if (target instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) target;
                        livingEntity.setFireTicks(60 * level);
                    }
                }
            }
            if (weapon.containsEnchantment(CoreOfDestruction.CORE_OF_DESTRUCTION)) {
                int level = weapon.getEnchantmentLevel(CoreOfDestruction.CORE_OF_DESTRUCTION);
                if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK && player.getAttackCooldown() == 1.0) {
                    Entity target = event.getEntity();
                    target.getWorld().createExplosion(target.getLocation(), level, false, false, player);
                }
            }
            if (weapon.containsEnchantment(NatureWrath.NATURE_WRATH)) {
                int level = weapon.getEnchantmentLevel(NatureWrath.NATURE_WRATH);
                Entity target = event.getEntity();
                if (target instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) target;
                    // Исправленный импорт для PotionEffectType
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60 * level, level - 1, false, true));

                }
            }
        }
    }
}
