package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;


public class Pacman extends ElementMove {
    
    private int score=0;
    private int remainingScore=0;
    private int numberLifes=1;
    private int numberDotstoEat=0;
    private int numberGhosttoEat=4;
    private long startTimePower=0;

	// construtor da classe que chama o construtor de ElementMove e determina o pacman como mortal
    public Pacman(String imageName) {
        super(imageName);
        this.isMortal = true;
        
    }

	// getter que retorna a pontuação do pacman
    public int getScore(){
    	return this.score;
    }

	// getter que retorna a pontuação restante a ser obtida no nível
    public int getRemainingScore(){
    	return this.remainingScore;
    }

	// getter quer retorna o número de vidas restantes do pacman
	public int getLifes() {
		return this.numberLifes;
	}

	// getter que retorna o número de pacdots restantes no nível
	public int getNumberDotstoEat() {
		return this.numberDotstoEat;
	}

	// getter que retorna o tempo de início do evento da powerpellet
	public long getStartTimePower() {
		return this.startTimePower;
	}

	// setter que determina o tempo de início do evento da powerpellet
	public void setStartTimePower(long start){
		this.startTimePower=start;
	}

	// setter que determina a pontuação restante
	public void setRemainingScore(int remainingScore){
		this.remainingScore=remainingScore;
	}

	// setter que determina o número de vidas restantes
	public void setNumberLifes(int numberLifes){
		this.numberLifes=numberLifes;
	}

	// método que adiciona uma vida ao pacman
	public void addLife(){
		this.numberLifes++;
	}

	// método que adiciona uma pacdot ao contador de pacdots restantes
	public void addNumberDotstoEat() {
		this.numberDotstoEat++;
	}

	// método que subtrai uma pacdot do contador de pacdots restantes
	public void minusNumberDotstoEat() {
		this.numberDotstoEat--;
	}

	// método que subtrai um fantasma do contador de fantasmas restantes
	public void minusNumberGhotstoEat() {
		this.numberGhosttoEat--;
	}

	// método que adiciona determinada quantidade de pontos à pontuação do pacman
	public void addScore(int i) {
		score=score+i;
	}

	// método que adiciona determinada quantidade de pontos à pontuação restante do pacman
	public void addRemainingScore(int i) {
		this.remainingScore=this.remainingScore+i;
	}


	// implementação do método abstrato autoDraw que desenha o elemento
	@Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

	// getter que retorna o número de fantasmas restantes no nível
	public int getNumberGhosttoEat() {
		return numberGhosttoEat;
	}

	private boolean moving = false;

	// getter que devolve se o pacman está se movendo ou não
	public boolean isMoving() {
		return moving;
	}

	// setter que determina se o pacman está se movendo ou não
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	// implementação do método setMovDirection que determina a direção de movimento do pacman (contando o estado do pacman parado)
	@Override
	public void setMovDirection(int direction) {
		super.setMovDirection(direction);
		if(direction == ElementMove.STOP) {
			setMoving(false);
		}
		else {
			setMoving(true);
		}
	}
}
