package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

public class Strawberry extends ElementGivePoint {
	private long startTime=0;

	// construtor da classe que, além de chamar o construtor de ElementGivePoint, determina o número de pontos que vale uma Strawberry e inicia a contagem de tempo da Strawberry no jogo
	public Strawberry(String imageName) {
        super(imageName);
        this.numberPoints=300;
        this.startTime=System.currentTimeMillis();
    }

	// getter que retorna o tempo que a Strawberry apareceu no mapa
	public long getStartTime() {
		return this.startTime;
	}

	// setter que determina o tempo que a Strawberry apareceu no mapa
	public void setStartTime(long start){
		this.startTime=start;
	}

}