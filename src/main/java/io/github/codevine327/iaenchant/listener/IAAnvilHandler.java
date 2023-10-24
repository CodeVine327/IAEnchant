package io.github.codevine327.iaenchant.listener;

import com.github.jikoo.planarenchanting.anvil.AnvilOperation;
import com.github.jikoo.planarenchanting.anvil.AnvilResult;
import io.github.codevine327.iaenchant.IAEnchant;
import io.github.codevine327.iaenchant.IAUtil;
import jdk.jshell.execution.Util;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

public class IAAnvilHandler implements Listener {
    private IAEnchant plugin;

    public IAAnvilHandler(IAEnchant plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPrepareAnvil(PrepareAnvilEvent event) {
        if (!IAUtil.isIAHat(event.getInventory().getItem(0))) {
            return;
        }

        AnvilInventory inventory = event.getInventory();
        AnvilOperation operation = new AnvilOperation();
        operation.setEnchantApplies((e, item) -> plugin.getAnvilEnchantments().contains(e));

        AnvilResult result = operation.apply(inventory);
        event.setResult(result.item());

        Bukkit.getScheduler().runTask(plugin, () -> {
            inventory.setRepairCostAmount(result.materialCost());
            inventory.setRepairCost(result.levelCost());
        });
    }
}
