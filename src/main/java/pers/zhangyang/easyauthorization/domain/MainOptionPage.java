package pers.zhangyang.easyauthorization.domain;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import pers.zhangyang.easyauthorization.yaml.GuiYaml;
import pers.zhangyang.easylibrary.base.BackAble;
import pers.zhangyang.easylibrary.base.GuiPage;
import pers.zhangyang.easylibrary.base.SingleGuiPageBase;
import pers.zhangyang.easylibrary.util.CommandUtil;

import java.util.List;

public class MainOptionPage extends SingleGuiPageBase implements BackAble {
    public MainOptionPage(Player viewer, GuiPage backPage, OfflinePlayer owner) {
        super(GuiYaml.INSTANCE.getString("gui.title.mainOptionPage"), viewer, backPage, owner,54);
    }



    @Override
    public void refresh() {

        this.inventory.clear();

        ItemStack back=GuiYaml.INSTANCE.getButton("gui.button.mainOptionPage.back");
        this.inventory.setItem(49,back);

        ItemStack registerAccount=GuiYaml.INSTANCE.getButton("gui.button.mainOptionPage.registerAccount");
        this.inventory.setItem(21,registerAccount);

        ItemStack loginAccount=GuiYaml.INSTANCE.getButton("gui.button.mainOptionPage.loginAccount");
        this.inventory.setItem(23,loginAccount);

        ItemStack cancelAccount=GuiYaml.INSTANCE.getButton("gui.button.mainOptionPage.cancelAccount");
        this.inventory.setItem(13,cancelAccount);

        ItemStack changeAccountPassword=GuiYaml.INSTANCE.getButton("gui.button.mainOptionPage.changeAccountPassword");
        this.inventory.setItem(22,changeAccountPassword);

        this.viewer.openInventory(this.inventory);

    }


    @Override
    public void back() {
        List<String> cmdList= GuiYaml.INSTANCE.getStringList("gui.firstPageBackCommand");
        if (cmdList==null){
            return;
        }
        CommandUtil.dispatchCommandList(viewer,cmdList);
    }

    @Override
    public int getBackSlot() {
        return 49;
    }
}
