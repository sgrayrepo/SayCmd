package me.sgray.saycmd;

import org.bukkit.plugin.java.JavaPlugin;

public class SayCmdMain extends JavaPlugin {
    @Override
    public void onDisable() {}

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("say").setExecutor(new CommandSay(this));
        getCommand("sayreload").setExecutor(new CommandReload(this));
    }
}
