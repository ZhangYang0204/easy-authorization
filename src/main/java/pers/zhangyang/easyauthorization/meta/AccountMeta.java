package pers.zhangyang.easyauthorization.meta;

public class AccountMeta {
    private String playerUuid;
    private String accountPassword;

    public AccountMeta() {
    }

    public AccountMeta(String playerUuid, String accountPassword) {
        this.playerUuid = playerUuid;
        this.accountPassword = accountPassword;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
