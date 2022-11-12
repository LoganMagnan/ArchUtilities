package club.archdev.archutilities.api;

import club.archdev.archutilities.ArchUtilities;
import club.archdev.archutilities.utils.menusystem.PlayerMenuUtil;
import org.bukkit.entity.Player;

public class ArchUtilitiesAPI {

    private ArchUtilities main = ArchUtilities.getInstance();

    public PlayerMenuUtil getPlayerMenuUtil(Player player) {
        PlayerMenuUtil playerMenuUtil;

        if (this.main.getPlayerMenuUtilMap().containsKey(player)) {
            return this.main.getPlayerMenuUtilMap().get(player);
        } else {
            playerMenuUtil = new PlayerMenuUtil(player);

            this.main.getPlayerMenuUtilMap().put(player, playerMenuUtil);

            return playerMenuUtil;
        }
    }
}
