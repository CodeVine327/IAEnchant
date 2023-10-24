package io.github.codevine327.iaenchant;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public class IAUtil {
    public static boolean isIAHat(ItemStack item) {
        CustomStack IAItem = CustomStack.byItemStack(item);
        if (IAItem == null) {
            return false;
        }

        return IAItem.getConfig().getBoolean("items." + IAItem.getId() + ".behaviours.hat", false);
    }
}
