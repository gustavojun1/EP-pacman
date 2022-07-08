package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;
public class PowerPellet extends ElementGivePoint {

    // construtor da classe que chamaa o construtor de ElementGivePoint e determina o número de pontos que vale uma PowerPellet
    public PowerPellet(String imageName) {
        super(imageName);
        this.numberPoints=50;
    }

    
}
