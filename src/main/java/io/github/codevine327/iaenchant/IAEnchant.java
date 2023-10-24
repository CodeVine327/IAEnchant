package io.github.codevine327.iaenchant;

import com.github.jikoo.planarenchanting.table.Enchantability;
import io.github.codevine327.iaenchant.listener.IAAnvilHandler;
import io.github.codevine327.iaenchant.listener.IAEnchantHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class IAEnchant extends JavaPlugin {
    private Enchantability tableEnchantability;
    private Set<Enchantment> tableEnchantments = new HashSet<>();
    private Set<Enchantment> anvilEnchantments = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            tableEnchantability = (Enchantability) Enchantability.class.getDeclaredField(getConfig().getString("enchanting-table.enchantability")).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        tableEnchantments = this.getEnchantmentSet("enchanting-table.possible-enchantments");
        anvilEnchantments = this.getEnchantmentSet("anvil.possible-enchantments");


        getServer().getPluginManager().registerEvents(new IAEnchantHandler(this), this);
        getServer().getPluginManager().registerEvents(new IAAnvilHandler(this), this);
    }

    @Override
    public void onDisable() {

    }

    public Enchantability getTableEnchantability() {
        return tableEnchantability;
    }

    public Set<Enchantment> getTableEnchantments() {
        return tableEnchantments;
    }

    public Set<Enchantment> getAnvilEnchantments() {
        return anvilEnchantments;
    }

    private Set<Enchantment> getEnchantmentSet(String path) {
        return getConfig().getStringList(path).stream()
                .map(Enchantment::getByName).collect(Collectors.toSet());
    }
}
