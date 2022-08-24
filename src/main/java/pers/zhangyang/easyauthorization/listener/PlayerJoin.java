package pers.zhangyang.easyauthorization.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.SettingYaml;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

@EventListener
public class PlayerJoin implements Listener {
    @EventHandler
    public void on(PlayerJoinEvent event) {


        Player player = event.getPlayer();

        if (!SettingYaml.INSTANCE.getBooleanDefault("setting.enableJoinOnBlock")) {
            return;
        }

        GuiService guiService = (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();
        Location registerLocation = SettingYaml.INSTANCE.getLocation("setting.registerLocation");

        if (registerLocation != null && guiService.getAccount(player.getUniqueId().toString()) == null) {

            player.teleport(registerLocation);

        }
        new MainOptionPage(player, null, player).send();
    }
}
