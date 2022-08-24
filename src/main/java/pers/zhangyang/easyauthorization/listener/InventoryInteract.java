package pers.zhangyang.easyauthorization.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easylibrary.annotation.EventListener;

@EventListener
public class InventoryInteract implements Listener {
    @EventHandler
    public void on(InventoryInteractEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        if (event.getInventory().getHolder() instanceof MainOptionPage) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        Gamer gamer = GamerManager.INSTANCE.getGamer(player);
        if (gamer.isLogin()) {
            return;
        }

        event.setCancelled(true);

    }
}