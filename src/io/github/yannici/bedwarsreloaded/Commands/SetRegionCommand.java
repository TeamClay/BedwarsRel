package io.github.yannici.bedwarsreloaded.Commands;

import io.github.yannici.bedwarsreloaded.ChatWriter;
import io.github.yannici.bedwarsreloaded.Main;
import io.github.yannici.bedwarsreloaded.Game.Game;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRegionCommand extends BaseCommand implements ICommand {

    public SetRegionCommand(Main plugin) {
        super(plugin);
    }

    @Override
    public String getCommand() {
        return "setregion";
    }

    @Override
    public String getName() {
        return "Set Region Points";
    }

    @Override
    public String getDescription() {
        return "Sets the playing region points";
    }

    @Override
    public String[] getArguments() {
        return new String[]{"game", "location"};
    }

    @Override
    public boolean execute(CommandSender sender, ArrayList<String> args) {
        if(!super.hasPermission(sender)) {
            return false;
        }

        Player player = (Player) sender;

        Game game = this.getPlugin().getGameManager().getGame(args.get(0));
        if(game == null) {
            player.sendMessage(ChatWriter.pluginMessage(ChatColor.RED + "The given game wasn't found!"));
            return false;
        }

        String loc = args.get(1);
        if(!loc.equalsIgnoreCase("loc1") && !loc.equalsIgnoreCase("loc2")) {
            player.sendMessage(ChatWriter.pluginMessage(ChatColor.RED + "Your location argument has to be \"loc1\" or \"loc2\"!"));
            return false;
        }

        game.setLoc(player.getLocation(), loc);
        player.sendMessage(ChatWriter.pluginMessage(ChatColor.GREEN + "Game Location " + loc + " for Game " + game.getName() + " was set successfully!"));
        return true;
    }

    @Override
    public String getPermission() {
        return "setup";
    }

}