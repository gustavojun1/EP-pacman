package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;


public class PacDots extends ElementGivePoint {
    
	// construtor da classe que chama o construtor de ElementGivePoint e determina o número de pontos que cada PacDot vale
    public PacDots(String imageName) {
        super(imageName);
        this.numberPoints = 10;
    }

    
    
}
