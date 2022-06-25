package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

public abstract class BackgroundElement extends Element{
    
    public BackgroundElement(String imageName) {
        super(imageName);
    }

    public abstract void autoDraw(Graphics g);
}
