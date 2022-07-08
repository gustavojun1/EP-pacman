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

    // getter que retorna a direção de movimento do elemento
    public int getMoveDirection(){
    	return movDirection;
    }

    // construtor da classe que apenas chama o construtor de Element
    public ElementMove(String imageName) {
        super(imageName);
    }


    // método abstrato de desenho do elemento em questão na tela
    abstract public void autoDraw(Graphics g);
    
    // método que move o elemento para a sua última posição salva
    public void backToLastPosition(){
        this.pos.comeBack();
    }

    // setter que determina a posição de movimento do elemento
    public void setMovDirection(int direction) {
        movDirection = direction;
    }

    // método que movimenta o elemento em tela de acordo com a direção determinada
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

    // método que move o elemento para cima
    public boolean moveUp() {
        return this.pos.moveUp();
    }

    // método que move o elemento para baixo
    public boolean moveDown() {
        return this.pos.moveDown();
    }

    // método que move o elemento para a direita
    public boolean moveRight() {
        return this.pos.moveRight();
    }

    // método que move o elemento para a esquerda
    public boolean moveLeft() {
        return this.pos.moveLeft();
    }


}
