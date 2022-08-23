package pers.zhangyang.easyauthorization.listener.mainoptionpage;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pers.zhangyang.easyauthorization.domain.Gamer;
import pers.zhangyang.easyauthorization.exception.DuplicateAccountException;
import pers.zhangyang.easyauthorization.manager.GamerManager;
import pers.zhangyang.easyauthorization.meta.AccountMeta;
import pers.zhangyang.easyauthorization.service.GuiService;
import pers.zhangyang.easyauthorization.service.impl.GuiServiceImpl;
import pers.zhangyang.easyauthorization.yaml.MessageYaml;
import pers.zhangyang.easylibrary.base.FiniteInputListenerBase;
import pers.zhangyang.easylibrary.base.GuiPage;
import pers.zhangyang.easylibrary.util.MessageUtil;
import pers.zhangyang.easylibrary.util.TransactionInvocationHandler;

import java.util.List;

public class PlayerInputAfterClickMainOptionPageLoginAccount extends FiniteInputListenerBase {
    public PlayerInputAfterClickMainOptionPageLoginAccount(Player player, OfflinePlayer owner, GuiPage previousPage) {
        super(player, owner, previousPage, 1);
        List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.howToLoginAccount");
        MessageUtil.sendMessageTo(player,list);
    }

    @Override
    public void run() {

        Player owner=this.owner.getPlayer();
        if (owner==null){
            List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.notOnline");
            MessageUtil.sendMessageTo(player,list);
            return;
        }
        GuiService guiService= (GuiService) new TransactionInvocationHandler(new GuiServiceImpl()).getProxy();
        AccountMeta accountMeta=guiService.getAccount(owner.getUniqueId().toString());
        if (accountMeta==null){
            List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.notExistAccountWhenLoginAccount");
            MessageUtil.sendMessageTo(player,list);
            return;
        }
        if (!accountMeta.getAccountPassword().equals(messages[0])){
            List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.wrongPassword");
            MessageUtil.sendMessageTo(player,list);
            return;
        }

        Gamer gamer= GamerManager.INSTANCE.getGamer(owner);
        if (gamer.isLogin()){
            List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.duplicateLoginAccount");
            MessageUtil.sendMessageTo(player,list);
            return;
        }
        gamer.setLogin(true);

        List<String> list= MessageYaml.INSTANCE.getStringList("message.chat.loginAccount");
        MessageUtil.sendMessageTo(player,list);

    }
}
