package elements;

import utils.Drawing;
import utils.Position;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

public abstract class ElementMove extends Element  {
    
    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    private int movDirection = STOP;
    
    public int getMoveDirection(){
    	return movDirection;
    }
    
    public ElementMove(String imageName) {
        super(imageName);
    }
    
     
    
    abstract public void autoDraw(Graphics g);
    
    

    public void backToLastPosition(){
        this.pos.comeBack();
    }
    
    public void setMovDirection(int direction) {
        movDirection = direction;
    }
    
    public void move() {
        switch (movDirection) {
            case MOVE_LEFT:
                this.moveLeft();
                break;
            case MOVE_RIGHT:
                this.moveRight();
                break;
            case MOVE_UP:
                this.moveUp();
                break;
            case MOVE_DOWN:
                this.moveDown();
                break;
            default:
                break;
        }
    }
    public boolean moveUp() {
        return this.pos.moveUp();
    }

    public boolean moveDown() {
        return this.pos.moveDown();
    }

    public boolean moveRight() {
        return this.pos.moveRight();
    }

    public boolean moveLeft() {
        return this.pos.moveLeft();
    }


}
