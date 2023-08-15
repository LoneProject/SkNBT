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

public class NBTSkriptCheck extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(NBTSkriptCheck.class, Boolean.class, ExpressionType.SIMPLE,
                "nbt named %string% of %itemstack%");
    }

    private Expression<String> name;
    private Expression<ItemStack> item;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] data, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.name = (Expression<String>) data[0];
        this.item = (Expression<ItemStack>) data[1];
        return true;
    }


    @Override
    protected @Nullable Boolean[] get(Event e) {
        ItemNmsUtil itemNms = new ItemNmsUtil(this.item.getSingle(e));
        return new Boolean[]{itemNms.asTag(this.name.getSingle(e)) != null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "nbt named " + this.name.getSingle(e) + " of " + this.item.getSingle(e);
    }

}