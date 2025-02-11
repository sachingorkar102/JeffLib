package de.jeff_media.jefflib.data;

import de.jeff_media.jefflib.ItemStackUtils;
import de.jeff_media.jefflib.NumberUtils;
import de.jeff_media.jefflib.RandomUtils;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Data
public class VariableItemStack {

    @NotNull private final ItemStack itemStack;
    private final int minAmount;
    private final int maxAmount;
    private final double chance;

    public static VariableItemStack fromConfigurationSection(final ConfigurationSection section) {
        final ItemStack itemStack = ItemStackUtils.fromConfigurationSection(section);
        Integer minAmount = null;
        Integer maxAmount = null;
        if(section.isSet("amount") && section.getString("amount").contains("-")) {
            minAmount = NumberUtils.parseInteger(section.getString("amount").split("-")[0]);
            maxAmount = NumberUtils.parseInteger(section.getString("amount").split("-")[1]);
        }
        if(minAmount == null || maxAmount == null) {
            minAmount = itemStack.getAmount();
            maxAmount = itemStack.getAmount();
        }
        if(minAmount > maxAmount) {
            minAmount = maxAmount;
        }
        double chance = 100;
        if(section.isSet("chance")) {
            if(section.isDouble("chance")) {
                chance = section.getDouble("chance");
            } else {
                chance = Double.parseDouble(section.getString("chance").replace("%",""));
            }
        }
        return new VariableItemStack(itemStack,minAmount,maxAmount,chance);
    }

    public ItemStack getItemStack() {
        final ItemStack clone = itemStack.clone();
        clone.setAmount(RandomUtils.getInt(minAmount, maxAmount));
        return clone;
    }

}
