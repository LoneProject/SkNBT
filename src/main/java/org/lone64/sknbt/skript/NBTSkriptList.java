package org.lone64.sknbt.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;
import org.lone64.sknbt.util.nms.ItemNmsUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NBTSkriptList extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(NBTSkriptList.class, ItemStack.class, ExpressionType.SIMPLE,
                "nbt list named %string% in %string% of %itemstack%");
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
        String value = this.value.getSingle(e);
        if (value == null) return new ItemStack[]{};

        ItemNmsUtil itemNms = new ItemNmsUtil(this.item.getSingle(e));
        if (itemNms.asTag(this.name.getSingle(e)) == null)
            itemNms.asTag(this.name.getSingle(e), value.replace(" and ", ", "));
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
        return "nbt list named " + this.name.getSingle(e) + " in " + this.value.getSingle(e) + " of " + this.item.getSingle(e);
    }

}