package me.luca.deathlogger;

import java.util.logging.Logger;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        Logger logger = DeathLogger.Instance.getServer().getLogger();

        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        World.Environment dim = player.getWorld().getEnvironment();

        NamedTextColor dimCol;
        String dimName;

        switch (dim) {
            case NORMAL:
                dimName = "Overworld";
                dimCol = NamedTextColor.GREEN;
                break;
            case NETHER:
                dimName = "Nether";
                dimCol = NamedTextColor.DARK_RED;
                break;
            case THE_END:
                dimName = "End";
                dimCol = NamedTextColor.DARK_PURPLE;
                break;
            case CUSTOM:
                dimName = player.getWorld().getName();
                dimCol = NamedTextColor.GOLD;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dim);
        }

        Component message = Component.text("Died @ ", NamedTextColor.RED)
                .append(Component.text(String.format("[%s] ", dimName), dimCol))
                .append(Component.text(String.format("%d %d %d", (int)x, (int)y, (int)z), NamedTextColor.AQUA));

        player.sendMessage(message);

        logger.info(String.format("%s died at location: [%s] %d, %d, %d", player.getName(), dimName, (int)x, (int)y, (int)z));
    }
}
