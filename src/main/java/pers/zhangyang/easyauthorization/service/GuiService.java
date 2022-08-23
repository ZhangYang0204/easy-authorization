package pers.zhangyang.easyauthorization.service;

import org.jetbrains.annotations.Nullable;
import pers.zhangyang.easyauthorization.exception.DuplicateAccountException;
import pers.zhangyang.easyauthorization.exception.NotExistAccountException;
import pers.zhangyang.easyauthorization.meta.AccountMeta;

public interface GuiService {

    void registerAccount(AccountMeta accountMeta) throws DuplicateAccountException;

    AccountMeta getAccount(String playerUuid);
    @Nullable
    void cancelAccount(String playerUuid) throws NotExistAccountException;
    void changeAccountPassword(String playerUuid,String newPass) throws NotExistAccountException;
}
