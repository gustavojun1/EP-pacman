package elements;

import utils.Consts;
import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Ghost extends ElementMove {
    
    
    public Ghost(String imageName) {
        super(imageName);
    }

	public Pacman pacman;
     
    
    abstract public void autoDraw(Graphics g);
    
    public void changeGhosttoBlue(String imageName) {
        this.isTransposable = true;
        this.isMortal = true;
        
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

    public void changeGhosttoNormal(String imageName) {
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

    protected void followPacman() {
    	Pacman pacman=Drawing.getGameScreen().getPacman();
        Position posPacman=pacman.getPos();
        int movDirectionPacman=pacman.getMoveDirection();
        
        if (movDirectionPacman==MOVE_LEFT ||movDirectionPacman==MOVE_RIGHT){
        	followPacmanHorizontal();
        }
        else if(movDirectionPacman==MOVE_DOWN ||movDirectionPacman==MOVE_UP) {
			followPacmanVertical();
		} else {
			moveRandom();
		}
	}



	static final int chanceMoverCorretamente = 80; // em porcentagem

	protected void followPacmanHorizontal() {
		Pacman pacman = Drawing.getGameScreen().getPacman();
		Random gerador = new Random();
		if(pacman.isMoving()) {
			// movimento errado (aleatório)
			if (gerador.nextInt(101) > chanceMoverCorretamente) {
				moveRandom();
			}
			// movimento correto
			else {
				if (pacman.getPos().getY() < this.getPos().getY()) {
					this.setMovDirection(Pacman.MOVE_LEFT);
				} else {
					this.setMovDirection(Pacman.MOVE_RIGHT);
				}
			}
		} else {
			moveRandom();
		}
	}
	protected void followPacmanVertical() {
		Pacman pacman = Drawing.getGameScreen().getPacman();
		Random gerador = new Random();
		if(pacman.isMoving()) {
			// movimento errado (aleatório)
			if (gerador.nextInt(101) > chanceMoverCorretamente) {
				moveRandom();
			}
			// movimento correto
			else {
				if (pacman.getPos().getY() < this.getPos().getY()) {
					this.setMovDirection(Pacman.MOVE_DOWN);
				} else {
					this.setMovDirection(Pacman.MOVE_UP);
				}
			}
		} else {
			moveRandom();
		}
	} 
	
    protected void escapePacman() {
    	Pacman pacman=Drawing.getGameScreen().getPacman();
        Position posPacman=pacman.getPos();
        int movDirectionPacman=pacman.getMoveDirection();
        
        if (movDirectionPacman==MOVE_LEFT ||movDirectionPacman==MOVE_RIGHT){
        	escapePacmanHorizontal(movDirectionPacman, posPacman);
        }
        else if(movDirectionPacman==MOVE_DOWN ||movDirectionPacman==MOVE_UP){
        	escapePacmanVertical(movDirectionPacman, posPacman);
        }		
	}
    
    

    
	protected void escapePacmanHorizontal(int movDirectionPacman,Position posPacman) {
       	Random gerador = new Random();
    	if(gerador.nextInt(11)>8){
    		this.setMovDirection(gerador.nextInt(5));
    	}
    	else{
    		if (posPacman.getY()<this.getPos().getY()){
    			this.setMovDirection(Pacman.MOVE_RIGHT);
    		}
    		else{
    			this.setMovDirection(Pacman.MOVE_LEFT);	
    		} 
    	}
	}
	protected void escapePacmanVertical(int movDirectionPacman, Position posPacman) {
    	Random gerador = new Random();
    	if(gerador.nextInt(11)>8){
    		this.setMovDirection(gerador.nextInt(5));
    	}
    	else{
    		if (posPacman.getX()<this.getPos().getX()){
    			this.setMovDirection(Pacman.MOVE_DOWN);
    		}
    		else{
    			this.setMovDirection(Pacman.MOVE_UP);	
    		} 
    	}		
	} 
	protected void moveRandom() {
    	Random gerador = new Random();
    	this.setMovDirection(gerador.nextInt(5));		
	}
   
}
