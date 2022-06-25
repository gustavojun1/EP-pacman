package elements;

import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
public class Blinky extends Ghost  {
     
	public Blinky(String imageName) {
	      super(imageName);
	}
    @Override
    public void autoDraw(Graphics g){
    	if(!this.isMortal){
    		followPacman();
    	}
    	else{
    		escapePacman();
    	}
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    


}
