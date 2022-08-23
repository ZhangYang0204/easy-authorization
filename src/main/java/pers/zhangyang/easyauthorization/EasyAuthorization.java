package pers.zhangyang.easyauthorization;

import org.bstats.bukkit.Metrics;
import pers.zhangyang.easylibrary.EasyPlugin;

public class EasyAuthorization extends EasyPlugin {
    @Override
    public void onOpen() {

        // bStats统计信息
        new Metrics(EasyAuthorization.instance,16232);
    }

    @Override
    public void onClose() {

    }
}
