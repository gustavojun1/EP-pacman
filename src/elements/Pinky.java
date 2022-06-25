package elements;

import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
public class Pinky extends Ghost  {
     
	public Pinky(String imageName) {
	      super(imageName);
	}
    @Override
    public void autoDraw(Graphics g){
    	Pacman pacman=Drawing.getGameScreen().getPacman();
        Position posPacman=pacman.getPos();
        int movDirectionPacman=pacman.getMoveDirection();
        if (movDirectionPacman==MOVE_LEFT ||movDirectionPacman==MOVE_RIGHT){
        	if(!this.isMortal){
        		followPacmanHorizontal(movDirectionPacman, posPacman);
        	}
        	else{
        		escapePacmanHorizontal(movDirectionPacman, posPacman);
        	}
        }
        else if(movDirectionPacman==MOVE_DOWN ||movDirectionPacman==MOVE_UP){
        	moveRandom();

        	}
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

    }



}
