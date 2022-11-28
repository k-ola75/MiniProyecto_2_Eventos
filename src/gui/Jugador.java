package gui;

import java.awt.*;

public class Jugador {
    private Color login;
    private String nickname;
    private int x;
    private int y;

    public Jugador(String nick, Color log, int x, int y) {
        nickname = nick;
        login = log;
        this.x = x;
        this.y = y;
    }

    public Color getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
