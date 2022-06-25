package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;


public class Wall extends BackgroundElement{
    
    public Wall(String imageName) {
        super(imageName);
        this.isTransposable = false;
    }

    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

    }    
}
