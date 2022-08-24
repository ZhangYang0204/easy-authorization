package pers.zhangyang.easyauthorization.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easylibrary.annotation.EventListener;

@EventListener
public class PlayerMove implements Listener {
    @EventHandler
    public void on(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Gamer gamer = GamerManager.INSTANCE.getGamer(player);

        if (gamer.isLogin()) {
            return;
        }
        if (event.getTo()!=null&&event.getFrom().getX() == event.getTo().getX() && event.getFrom().getZ() == event.getTo().getZ()) {
            return;
        }
        if (!(player.getOpenInventory().getTopInventory() instanceof MainOptionPage)) {
            new MainOptionPage(player, null, player).send();
        }
        event.setCancelled(true);

    }
}
