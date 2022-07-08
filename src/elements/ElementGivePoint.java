package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

public class ElementGivePoint extends Element {
    protected  int numberPoints=0;

    // getter que retorna o número de pontos que o elemento vale
	public int getNumberPoints(){
		return numberPoints;
	}

    // construtor da classe que chama o construtor de Element e determina o objeto criado como mortal
    public ElementGivePoint(String imageName) {
        super(imageName);
        this.isMortal = true;        
    }

    // implementação do método autoDraw que desenha o elemento na tela
    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
    
}
