package pers.zhangyang.easyauthorization.service.impl;

import org.jetbrains.annotations.Nullable;
import pers.zhangyang.easyauthorization.dao.AccountDao;
import pers.zhangyang.easyauthorization.exception.DuplicateAccountException;
import pers.zhangyang.easyauthorization.exception.NotExistAccountException;
import pers.zhangyang.easyauthorization.meta.AccountMeta;
import pers.zhangyang.easyauthorization.service.GuiService;

public class GuiServiceImpl implements GuiService {
    @Override
    public void registerAccount(AccountMeta accountMeta) throws DuplicateAccountException {
        AccountMeta a=new AccountDao().getByPlayerUuid(accountMeta.getPlayerUuid());
        if (a!=null){
            throw  new DuplicateAccountException();
        }
        new AccountDao().insert(accountMeta);
    }

    @Override
    @Nullable
    public AccountMeta getAccount(String playerUuid) {
        return new AccountDao().getByPlayerUuid(playerUuid);
    }

    @Override
    public  void cancelAccount(String playerUuid) throws NotExistAccountException {
        AccountMeta a=new AccountDao().getByPlayerUuid(playerUuid);
        if (a==null){
            throw  new NotExistAccountException();
        }
        new AccountDao().deleteByPlayerUuid(playerUuid);
    }

    @Override
    public void changeAccountPassword(String playerUuid, String newPass) throws NotExistAccountException {
        AccountMeta a=new AccountDao().getByPlayerUuid(playerUuid);
        if (a==null){
            throw  new NotExistAccountException();
        }
        a.setAccountPassword(newPass);
        new AccountDao().deleteByPlayerUuid(playerUuid);
        new AccountDao().insert(a);
    }
}
