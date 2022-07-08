package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

public abstract class BackgroundElement extends Element{

    // construtor da classe que apenas chama o construtor de Element
    public BackgroundElement(String imageName) {
        super(imageName);
    }

    // método abstrato de desenho do elemento em questão na tela
    public abstract void autoDraw(Graphics g);
}
