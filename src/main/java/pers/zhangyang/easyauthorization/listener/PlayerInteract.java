package pers.zhangyang.easyauthorization.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easylibrary.annotation.EventListener;

@EventListener
public class PlayerInteract implements Listener {

    @EventHandler
    public void on(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Gamer gamer = GamerManager.INSTANCE.getGamer(player);
        if (gamer.isLogin()) {
            return;
        }

        event.setCancelled(true);
    }
}