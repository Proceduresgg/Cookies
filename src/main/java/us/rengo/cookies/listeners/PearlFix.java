package us.rengo.cookies.listeners;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PearlFix implements Listener {

    private final ImmutableSet<Material> blockedTypes;

    public PearlFix() {
        this.blockedTypes = Sets.immutableEnumSet(Material.THIN_GLASS,
                Material.IRON_FENCE, Material.FENCE, Material.NETHER_FENCE,
                Material.FENCE_GATE, Material.ACACIA_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.BRICK_STAIRS,
                Material.COBBLESTONE_STAIRS, Material.DARK_OAK_STAIRS, Material.JUNGLE_WOOD_STAIRS,
                Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS, Material.SANDSTONE_STAIRS,
                Material.SMOOTH_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.WOOD_STAIRS);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            Location to = event.getTo();

            if (this.blockedTypes.contains(to.getBlock().getType())) {
                event.setCancelled(true);
                return;
            }

            to.setX(to.getBlockX() + 0.5D);
            to.setZ(to.getBlockZ() + 0.5D);
            event.setTo(to);
        }
    }
}
