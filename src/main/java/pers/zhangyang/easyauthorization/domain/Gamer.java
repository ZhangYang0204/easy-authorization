package pers.zhangyang.easyauthorization.domain;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Gamer {
    private Player player;


    private boolean login;

    public Gamer(Player player) {
        this.player = player;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
