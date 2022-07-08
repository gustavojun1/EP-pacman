package utils;

import control.GameScreen;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Drawing implements Serializable {
    static GameScreen screen;

    // getter que retorna a tela do jogo
    public static GameScreen getGameScreen() {
        return screen;
    }

    // setter que determina a tela do jogo
    public static void setGameScreen(GameScreen newScreen) {
        screen = newScreen;
    }

    // método que desenha uma imagem em uma determinada posição
    public static void draw(Graphics g, ImageIcon imageIcon, double y, double x) {
        //System.out.println("y="+(y * Consts.CELL_SIZE)+", x="+(x * Consts.CELL_SIZE));
        imageIcon.paintIcon(screen, g, (int)Math.round(y * Consts.CELL_SIZE),(int)Math.round(x * Consts.CELL_SIZE));
    }
}
