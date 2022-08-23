package pers.zhangyang.easyauthorization.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easylibrary.annotation.EventListener;

@EventListener
public class PlayerQuit implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent event){
        Player player=event.getPlayer();
        GamerManager.INSTANCE.remove(player);
    }


}
