package me.mopslops.pugnewenchants.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantmentCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public EnchantmentCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length < 2) {
            sender.sendMessage("Usage: /giveenchantmentbook <enchantment> <level>");
            return true;
        }

        String enchantmentName = args[0];
        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid level. Please enter a number.");
            return true;
        }

        Enchantment enchantment = Enchantment.getByKey(me.mopslops.pugnewenchants.enchants.Pyrokinesis.PYROKINESIS.getKey());
        if (enchantment == null) {
            sender.sendMessage("Enchantment " + enchantmentName + " not found.");
            return true;
        }

        ItemStack enchantedBook = new ItemStack(Enchantment.PYROKINESIS); // Пример использования PROTECTION_ENVIRONMENTAL
        ItemMeta meta = enchantedBook.getItemMeta();
        if (meta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) meta;
            bookMeta.addStoredEnchant(enchantment, level, true);
            enchantedBook.setItemMeta(bookMeta);
            player.getInventory().addItem(enchantedBook);
            sender.sendMessage("You have been given an enchanted book with " + enchantmentName + " " + level + ".");
        } else {
            sender.sendMessage("Error: Invalid item meta type.");
        }

        return true;
    }
}
