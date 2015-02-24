package me.sgray.saycmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandSay implements CommandExecutor {
    SayCmdMain plugin;

    public CommandSay(SayCmdMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        // Don't waste time on non-matching commands.
        // Return false if command lacks a message, resulting in sender
        // getting usage information from plugin.yml
        if (!cmdName.equals("say") || args.length == 0) {
            return false;
        }

        // Determine name of sender to use for broadcasting the message
        String senderName;
        if (sender instanceof ConsoleCommandSender) {
            senderName = plugin.getConfig().getString("console-name");
        } else {
            senderName = sender.getName();
        }

        // Complete sending the message.
        plugin.getServer().broadcastMessage(this.parsePlaceholders(senderName, message(args)));

        return true;
    }

    /**
     * Convert the string array representing the message to a single string.
     *
     * @param   parts   array representing the message input with the command
     * @return          raw message sent with the command
     */
    private String message(String[] parts) {
        int size = parts.length;

        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < size; i++) {
            msg.append(parts[i]);
            if (i < size - 1) {
                msg.append(" ");
            }
        }

        return msg.toString();
    }

    /**
     * Format the full message to broadcast
     *
     * @param   senderName  name of sender
     * @param   message     message sent with command
     * @return              formatted string to broadcast
     */
    private String parsePlaceholders(String senderName, String message) {
        String result = plugin.getConfig().getString("format");

        result = result.replaceFirst("\\{name\\}", senderName);
        result = result.replaceFirst("\\{message\\}", message);

        return ChatColor.translateAlternateColorCodes('&', result);
    }

}
