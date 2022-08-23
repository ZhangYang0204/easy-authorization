package pers.zhangyang.easyauthorization.domain;

import org.bukkit.entity.Player;

public class Gamer {
    private Player player;


    private boolean login;

    public Gamer(Player player, boolean login) {
        this.player = player;
        this.login = login;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public Gamer(Player player){
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

}
