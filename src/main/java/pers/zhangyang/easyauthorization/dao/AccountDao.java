package pers.zhangyang.easyauthorization.dao;

import org.jetbrains.annotations.Nullable;
import pers.zhangyang.easyauthorization.meta.AccountMeta;
import pers.zhangyang.easylibrary.base.DaoBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends DaoBase {
    @Override
    public int init() {
        try {
            PreparedStatement ps = getConnection().prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS account(" +
                    "player_uuid TEXT  ," +
                    "account_password TEXT " +
                    ")");
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(AccountMeta accountMeta) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("" +
                    "INSERT INTO account (player_uuid,account_password)" +
                    "VALUES(?,?)");
            ps.setString(1, accountMeta.getPlayerUuid());
            ps.setString(2, accountMeta.getAccountPassword());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public AccountMeta getByPlayerUuid(String playerUuid) {
        try {

            PreparedStatement ps = getConnection().prepareStatement("" +
                    "SELECT * FROM account WHERE player_uuid = ?" +
                    "");
            ps.setString(1, playerUuid);
            ResultSet rs = ps.executeQuery();

            return singleTransform(rs, AccountMeta.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPlayerUuid(String playerUuid) {
        try {

            PreparedStatement ps = getConnection().prepareStatement("" +
                    "DELETE FROM account WHERE player_uuid = ?" +
                    "");
            ps.setString(1, playerUuid);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
