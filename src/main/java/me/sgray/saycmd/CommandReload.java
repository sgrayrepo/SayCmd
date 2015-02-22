package me.sgray.saycmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor {
    SayCmdMain plugin;

    public CommandReload(SayCmdMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("sayreload")) {
            return false;
        }

        plugin.reloadConfig();
        sender.sendMessage("Config has been reloaded for " + plugin.getDescription().getName());

        return true;
    }

}
