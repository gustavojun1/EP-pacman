package utils;

import java.io.Serializable;


public class Position implements Serializable {
    /* Elements are positioned in a grid layout (integers).
       However, walking is implemented with float steps (continuous).
       This is why x and y are double types.
       x and y ranges from 0 to CELL_SIZE*NUM_CELLS.
       The real pixel positioning is converted by the Drawing class.
       As consequence, any element has size 1x1 (x and y). */
    private double x;
    private double y;
    
    private double previousX;
    private double previousY;

    // construtor da classe
    public Position(double x, double y){
        this.setPosition(x,y);
    }

    // setter que determina as coordenadas de posição x e y que serão armazenadas caso estejam no plano de jogo
    public final boolean setPosition(double x, double y){
        int factor = (int)Math.pow(10, Consts.WALK_STEP_DEC_PLACES+1);
        x = (double)Math.round(x * factor) / factor;
        y = (double)Math.round(y * factor) / factor;
        
        if(x < 0 || x > Consts.NUM_CELLS-1)
            return false;
        previousX = this.x;
        this.x = x;
        
        if(y < 0 || y > Consts.NUM_CELLS-1)
            return false;
        previousY = this.y;
        this.y = y;
        return true;
    }

    // getter que retorna a coordenada x da posição armazenada
    public double getX(){
        return x;
    }

    // getter que retorna a coordenada y da posição armazenada
    public double getY(){
        return y;
    }

    // método que retorna para a última posição armazenada
    public boolean comeBack(){
        return this.setPosition(previousX,previousY);
    }

    // método que move uma posição para cima
    public boolean moveUp(){
        return this.setPosition(this.getX()-Consts.WALK_STEP, this.getY());
    }

    // método que move uma posição para baixo
    public boolean moveDown(){
        return this.setPosition(this.getX()+Consts.WALK_STEP, this.getY());
    }

    // método que move uma posição para a direita
    public boolean moveRight(){
        return this.setPosition(this.getX(), this.getY()+Consts.WALK_STEP);
    }

    // método que move uma posição para a esquerda
    public boolean moveLeft(){
        return this.setPosition(this.getX(), this.getY()-Consts.WALK_STEP);        
    }

    // método que devolve a distância entre a posição em questão e a passada como parâmetro
	public double distance(Position pos) {
		return Math.sqrt(Math.pow(x-pos.getX(),2)+Math.pow(y-pos.getY(),2));
	}
    
}
