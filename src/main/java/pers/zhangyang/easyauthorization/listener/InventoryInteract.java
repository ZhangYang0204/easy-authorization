package pers.zhangyang.easyauthorization.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.util.MessageUtil;

import java.util.List;

@EventListener
public class InventoryInteract implements Listener {
    @EventHandler
    public void on(InventoryInteractEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        if (event.getInventory().getHolder() instanceof MainOptionPage){
            return;
        }
        Player player= (Player) event.getWhoClicked();
        Gamer gamer= GamerManager.INSTANCE.getGamer(player);
        if (gamer.isLogin()){
            return;
        }

        event.setCancelled(true);

    }
}