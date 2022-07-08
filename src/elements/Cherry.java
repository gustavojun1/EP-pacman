package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;
public class Cherry extends ElementGivePoint {
	private long startTime=0;

	// construtor da classe que, além de chamar o construtor de ElementGivePoint, determina o número de pontos que vale uma Cherry e inicia a contagem de tempo da Cherry no jogo
    public Cherry(String imageName) {
        super(imageName);
        this.numberPoints=100;
        this.startTime=System.currentTimeMillis();
    }

	// getter que retorna startTime
	public long getStartTime() {
		return this.startTime;
	}

	// setter que aloca valor para startTime
	public void setStartTime(long start){
		this.startTime=start;
	}

    
}
