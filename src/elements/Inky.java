package elements;

import utils.Consts;
import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public class Inky extends Ghost  {
     
	public Inky(String imageName) {
	      super(imageName);
	}
    @Override
    public void autoDraw(Graphics g){
    	Pacman pacman=Drawing.getGameScreen().getPacman();
        Position posPacman=pacman.getPos();
        double distancia=posPacman.distance(this.pos);
        
        if(distancia>Consts.DISTANCEGHOST){
        	moveRandom();
        }
        else{
        	if(!this.isMortal){
        		followPacman();
        	}
        	else{
        		escapePacman();
        	}
        }
        	
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

    }


}
