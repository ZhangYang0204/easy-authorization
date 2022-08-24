package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pers.zhangyang.easyauthorization.exception.DuplicateAccountException;
import pers.zhangyang.easyauthorization.meta.AccountMeta;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.base.FiniteInputListenerBase;
import pers.zhangyang.easylibrary.base.GuiPage;
import pers.zhangyang.easylibrary.util.Md5Util;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

import java.util.List;

public class PlayerInputAfterClickMainOptionPageRegisterAccount extends FiniteInputListenerBase {
    public PlayerInputAfterClickMainOptionPageRegisterAccount(Player player, OfflinePlayer owner, GuiPage previousPage) {
        super(player, owner, previousPage, 1);
        List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.howToRegisterAccount");
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

        GuiService guiService = (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();
        AccountMeta accountMeta = new AccountMeta(owner.getUniqueId().toString(), Md5Util.getMd5Value(messages[0]));
        try {
            guiService.registerAccount(accountMeta);
        } catch (DuplicateAccountException e) {
            List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.duplicateAccount");
            MessageUtil.sendMessageTo(player, list);
            return;
        }
        List<String> list = MessageYaml.INSTANCE.getStringList("message.chat.registerAccount");
        MessageUtil.sendMessageTo(player, list);

    }
}
