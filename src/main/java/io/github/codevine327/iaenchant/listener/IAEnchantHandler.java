package io.github.codevine327.iaenchant.listener;

import com.github.jikoo.planarenchanting.table.Enchantability;
import com.github.jikoo.planarenchanting.table.EnchantingTable;
import com.github.jikoo.planarenchanting.table.TableEnchantListener;
import dev.lone.itemsadder.api.CustomStack;
import io.github.codevine327.iaenchant.IAEnchant;
import io.github.codevine327.iaenchant.IAUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IAEnchantHandler extends TableEnchantListener {
    private final EnchantingTable table;

    public IAEnchantHandler(@NotNull Plugin plugin) {
        super(plugin);
        table = new EnchantingTable(
                ((IAEnchant) plugin).getTableEnchantments().stream().toList(),
                ((IAEnchant) plugin).getTableEnchantability());
    }

    @Override // 是否不符合资格
    protected boolean isIneligible(@NotNull Player player, @NotNull ItemStack enchanted) {
        return !IAUtil.isIAHat(enchanted);
    }

    @Override
    protected @Nullable EnchantingTable getTable(@NotNull Player player, @NotNull ItemStack enchanted) {
        return table;
    }
}
