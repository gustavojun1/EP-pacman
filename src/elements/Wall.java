package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;


public class Wall extends BackgroundElement{

    // construtor da classe que chama o construtor de BackgroundElement e determina a Wall como intransponível
    public Wall(String imageName) {
        super(imageName);
        this.isTransposable = false;
    }

    // implementação do método abstrato autoDraw que desenha o elemento
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
