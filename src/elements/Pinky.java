package elements;

import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
public class Pinky extends Ghost  {

	// construtor da classe
	public Pinky(String imageName) {
	      super(imageName);
	}

	// implementação do método abstrato autoDraw que determina o comportamento do fantasma em relação ao pacman e desenha o elemento
    @Override
    public void autoDraw(Graphics g){
    	Pacman pacman=Drawing.getGameScreen().getPacman();
        Position posPacman=pacman.getPos();
        int movDirectionPacman=pacman.getMoveDirection();
        if (movDirectionPacman==MOVE_LEFT || movDirectionPacman==MOVE_RIGHT){
        	if(!this.isMortal){
        		followPacmanHorizontal();
        	}
        	else{
        		escapePacmanHorizontal(movDirectionPacman, posPacman);
        	}
        }
        else{
        	moveRandom();
		}
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

    }



}
