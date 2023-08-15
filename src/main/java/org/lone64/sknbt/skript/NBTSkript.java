package org.lone64.sknbt.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;
import org.lone64.sknbt.util.item.ItemUtil;
import org.lone64.sknbt.util.nms.ItemNmsUtil;

public class NBTSkript extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(NBTSkript.class, ItemStack.class, ExpressionType.SIMPLE,
                "nbt named %string% in %string% of %itemstack%");
    }

    private Expression<String> name;
    private Expression<String> value;
    private Expression<ItemStack> item;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] data, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.name = (Expression<String>) data[0];
        this.value = (Expression<String>) data[1];
        this.item = (Expression<ItemStack>) data[2];
        return true;
    }


    @Override
    protected @Nullable ItemStack[] get(Event e) {
        ItemUtil item = new ItemUtil(this.item.getSingle(e));
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bItems with NBT added"));

        ItemNmsUtil itemNms = new ItemNmsUtil(item.getItemStack());
        if (itemNms.asTag(this.name.getSingle(e)) == null)
            itemNms.asTag(this.name.getSingle(e), this.value.getSingle(e));
        return new ItemStack[]{itemNms.asItemStack()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "nbt named " + this.name.getSingle(e) + " in " + this.value.getSingle(e) + " of " + this.item.getSingle(e);
    }

}