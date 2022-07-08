package elements;

import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.Random;
public class Blinky extends Ghost  {

	// construtor da classe
	public Blinky(String imageName) {
	      super(imageName);
	}

	// implementação do método abstrato autoDraw que determina o comportamento do fantasma em relação ao pacman e desenha o elemento
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
