package pers.zhangyang.easyauthorization.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easylibrary.annotation.EventListener;

@EventListener
public class PlayerCommandPreprocess implements Listener {

    @EventHandler
    public void on(PlayerCommandPreprocessEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        Gamer gamer = GamerManager.INSTANCE.getGamer(player);
        if (gamer.isLogin()) {
            return;
        }
        event.setCancelled(true);
    }
}
