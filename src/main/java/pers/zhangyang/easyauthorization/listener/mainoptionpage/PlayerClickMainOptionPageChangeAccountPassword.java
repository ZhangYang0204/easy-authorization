package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.annotation.GuiDiscreteButtonHandler;
import pers.zhangyang.easylibrary.util.MessageUtil;

import java.util.List;

@EventListener
public class PlayerClickMainOptionPageChangeAccountPassword implements Listener {

    @GuiDiscreteButtonHandler(guiPage = MainOptionPage.class, slot = {31},closeGui = true,refreshGui = false)
    public void on(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        MainOptionPage mainOptionPage = (MainOptionPage) event.getInventory().getHolder();
        assert mainOptionPage != null;
        Player owner = mainOptionPage.getOwner().getPlayer();
        if (owner == null) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notOnline");
            MessageUtil.sendMessageTo(player, list);
            return;
        }


        Gamer gamer = GamerManager.INSTANCE.getGamer(owner);
        if (!gamer.isLogin()) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notLoginAccountWhenChangeAccountPassword");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        new PlayerInputAfterClickMainOptionPageChangeAccountPassword(player, mainOptionPage.getOwner(), mainOptionPage);
    }
}
