package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.exception.NotExistAccountException;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.annotation.GuiDiscreteButtonHandler;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

import java.util.List;

@EventListener
public class PlayerClickMainOptionPageCancelAccount implements Listener {

    @GuiDiscreteButtonHandler(guiPage = MainOptionPage.class, slot = {13},closeGui = false)
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
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notLoginAccountWhenCancelAccount");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        GuiService guiService = (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();
        try {
            guiService.cancelAccount(owner.getUniqueId().toString());
        } catch (NotExistAccountException e) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notExistAccountWhenCancelAccount");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        gamer.setLogin(false);
        List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.cancelAccount");
        MessageUtil.sendMessageTo(player, list);
    }
}
