package club.archdev.archutilities;

import club.archdev.archutilities.utils.ClassRegistrationUtils;
import club.archdev.archutilities.utils.Utils;
import club.archdev.archutilities.utils.command.CommandFramework;
import club.archdev.archutilities.utils.menusystem.PlayerMenuUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

@Getter
public class ArchUtilities extends JavaPlugin {

    @Getter private static ArchUtilities instance;

    private CommandFramework commandFramework = new CommandFramework(this);

    private HashMap<Player, PlayerMenuUtil> playerMenuUtilMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(Utils.translate("&bArchUtilities &8- &av" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Utils.translate("&7Made by &eTrixkz"));
        Bukkit.getConsoleSender().sendMessage("------------------------------------------------");

        this.loadListeners();

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() != EntityType.PLAYER && entity.getType() != EntityType.ITEM_FRAME) {
                    entity.remove();
                }
            }

            world.setGameRuleValue("doDaylightCycle", "false");
            world.setTime(0L);
            world.setStorm(false);
        }
    }

    @Override
    public void onDisable() {
        instance = null;

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.DROPPED_ITEM) {
                    entity.remove();
                }
            }

            for (Chunk chunk : world.getLoadedChunks()) {
                chunk.unload(true);
            }
        }
    }

    private void loadListeners() {
        ClassRegistrationUtils.loadListeners("club.archdev.archutilities.listeners");
    }

    public PlayerMenuUtil getPlayerMenuUtil(Player player) {
        PlayerMenuUtil playerMenuUtil;

        if (playerMenuUtilMap.containsKey(player)) {
            return playerMenuUtilMap.get(player);
        } else {
            playerMenuUtil = new PlayerMenuUtil(player);

            playerMenuUtilMap.put(player, playerMenuUtil);

            return playerMenuUtil;
        }
    }
}
