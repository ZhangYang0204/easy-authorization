package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.domain.MainOptionPage;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.meta.AccountMeta;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.annotation.EventListener;
import pers.zhangyang.easylibrary.annotation.GuiDiscreteButtonHandler;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

import java.util.List;

@EventListener
public class PlayerClickMainOptionPageLoginAccount implements Listener {

    @GuiDiscreteButtonHandler(guiPage = MainOptionPage.class, slot = {33},closeGui = true,refreshGui = true)
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        MainOptionPage mainOptionPage = (MainOptionPage) event.getInventory().getHolder();
        GuiService guiService = (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();
        assert mainOptionPage != null;
        AccountMeta accountMeta = guiService.getAccount(mainOptionPage.getOwner().getUniqueId().toString());
        Player owner = mainOptionPage.getOwner().getPlayer();
        if (owner == null) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notOnline");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        if (accountMeta == null) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notExistAccountWhenLoginAccount");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        Gamer gamer = GamerManager.INSTANCE.getGamer(owner);
        if (gamer.isLogin()) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.duplicateLoginAccount");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        new PlayerInputAfterClickMainOptionPageLoginAccount(player, mainOptionPage.getOwner(), mainOptionPage);
    }

}
