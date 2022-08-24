package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.exception.NotExistAccountException;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.base.FiniteInputListenerBase;
import pers.zhangyang.easylibrary.base.GuiPage;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

import java.util.List;

public class PlayerInputAfterClickMainOptionPageChangeAccountPassword extends FiniteInputListenerBase {
    public PlayerInputAfterClickMainOptionPageChangeAccountPassword(Player player, OfflinePlayer owner, GuiPage previousPage) {
        super(player, owner, previousPage, 1);
        List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.howToChangeAccountPassword");
        MessageUtil.sendMessageTo(player, list);
    }

    @Override
    public void run() {

        Player owner = this.owner.getPlayer();
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
        GuiService guiService = (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();


        try {
            guiService.changeAccountPassword(owner.getUniqueId().toString(), messages[0]);
        } catch (NotExistAccountException e) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.notExistAccountWhenChangeAccountPassword");
            MessageUtil.sendMessageTo(player, list);
            return;
        }


        List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.changeAccountPassword");
        MessageUtil.sendMessageTo(player, list);

    }
}
