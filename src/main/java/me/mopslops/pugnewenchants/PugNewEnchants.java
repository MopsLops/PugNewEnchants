package me.mopslops.pugnewenchants;

import me.mopslops.pugnewenchants.commands.EnchantmentCommand;
import me.mopslops.pugnewenchants.handlers.EnchantmentEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PugNewEnchants extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EnchantmentEventListener(this), this);
        this.getCommand("giveenchantmentbook").setExecutor(new EnchantmentCommand());

    }

}
