package org.lone64.sknbt;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkNBT extends JavaPlugin {

    private SkriptAddon addon;

    @Override
    public void onEnable() {
        try {
            this.addon = Skript.registerAddon(this);
            this.addon.loadClasses("org.lone64.sknbt", "skript");
        } catch (Exception ignored) { }
    }

    @Override
    public void onDisable() {

    }

}
