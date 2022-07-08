package elements;

import utils.Consts;
import utils.Position;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
public abstract class Element implements Serializable {

    protected ImageIcon imageIcon;
    protected Position pos;
    protected boolean isTransposable; 
    protected boolean isMortal;       

    // construtor da classe que determina imagem, posição, transponibilidade e mortabilidade do objeto
    protected Element(String imageName) {
        this.pos = new Position(1, 1);
        this.isTransposable = true;
        this.isMortal = false;
        
        try {
            imageIcon = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imageName);
            Image img = imageIcon.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
            imageIcon = new ImageIcon(bi);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // método que checa se o elemento em questão e o passado como parâmetro se sobrepõem
    public boolean overlap(Element elem) {
        double xDist = Math.abs(elem.pos.getX() - this.pos.getX());
        double yDist = Math.abs(elem.pos.getY() - this.pos.getY());
        
        if (xDist < 1.0 && yDist < 1.0)
            return true;
        else
            return false;
    }

    // getter que retorna a posição do elemento em formato de String
    public String getStringPosition() {
        return ("(" + pos.getX() + ", " + pos.getY() + ")");
    }

    // getter que retorna a posição do elemento como um objeto Position
    public Position getPos(){
    	return pos;
    }

    // setter que determina a posição do elemento
    public boolean setPosition(double x, double y) {
        return pos.setPosition(x, y);
    }

    // getter que retorna se o elemento é transponível ou não
    public boolean isTransposable() {
        return isTransposable;
    }

    // getter que retorna se o elemento é mortal ou não
    public boolean isMortal() {
        return isMortal;
    }

    // setter que determina se o elemento é transponível ou não
    public void setTransposable(boolean isTransposable) {
        this.isTransposable = isTransposable;
    }

    // método abstrato de desenho do elemento em questão na tela
    abstract public void autoDraw(Graphics g);

 
}
