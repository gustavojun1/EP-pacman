package control;

import elements.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import utils.Consts;
import utils.Position;

public class GameController {
	// método que desenha todos os elementos em suas determinadas posições em tela
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
    	Pacman pacman=(Pacman) elemArray.get(0);
    	int numberGhost=pacman.getNumberGhosttoEat();
    	for(int i=numberGhost+1; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
        
        for(int i=0;i<=numberGhost;i++){
        	elemArray.get(i).autoDraw(g);
        }
        
    }

	// método que processa todos os elementos, checando e aplicando todos os eventos a cada frame
    public void processAllElements(ArrayList<Element> elements, int [][]matrix, int cont){
        if(elements.isEmpty())
            return;
    	Pacman pacman = (Pacman)elements.get(0);
    	int numberGhost = pacman.getNumberGhosttoEat();
    	
        
    	checkElementColideWall(elements, numberGhost);
    	boolean overlapGhostPacman=checkOverlapGhostPacman(elements,pacman, numberGhost);

        if(overlapGhostPacman) { 
        	pacman.setNumberLifes(pacman.getLifes()-1);
        	if(pacman.getLifes()>0){
        		Main.gamePacMan.reStartLevel();
        	}
        	else{
				endGame();
        	}
        		
        }
        else if(pacman.getNumberDotstoEat() == 0){
        	Main.level += 1;
        	if(Main.level>=5){
				endGame();
        	}
        	else{
        		Main.gamePacMan.startNextLevel(pacman.getLifes(), pacman.getScore());
        	}
        }
        else{
	        checkPacmanEatSomeOneAndOrTimeFruittoDisappear(elements,pacman);
	        checkTimetoAppearFruit(elements,matrix);
	        checkTimeGhostBeNormal(elements,pacman);
	
	        pacman.move();
	        for (int i=1;i<=pacman.getNumberGhosttoEat();i++){
	            ElementMove elementMove = (ElementMove)elements.get(i);
	            if (!elementMove.isMortal()){
	            	elementMove.move();
	            }
	            else{
	            	if (elementMove.isMortal() && cont%2==0){
	            		elementMove.move();
	            	}
	            }
	        }
        }
    }

	// método que encerra o jogo, fechando a JFrame e exibindo uma mensagem de fim de jogo
	private void endGame() {
		Main.gamePacMan.dispose();
		JOptionPane.showMessageDialog(null, "Fim do jogo");
		System.exit(0);
	}

	// método que checa o evento de sobreposição entre fantasma e pacman e retorna o booleano correspondente
	private boolean checkOverlapGhostPacman(ArrayList<Element> elements, Pacman pacman,int numberGhost) {
        boolean overlapGhostPacman=false;
        for (int i=1;i<=numberGhost;i++){
        	if(elements.get(i).overlap(pacman) & !elements.get(i).isMortal()){
        		overlapGhostPacman=true;
        	}
        }
        return overlapGhostPacman;
	}

	// método que checa o evento de colisão entre elementos móveis (pacman e fantasmas) e muros
	private void checkElementColideWall(ArrayList<Element> elements, int numberGhost) {
    	for (int i=0;i<=numberGhost;i++){
        	ElementMove elementMove = (ElementMove)elements.get(i);
        	if (!isValidPosition(elements, elementMove)) {
        		elementMove.backToLastPosition();
        		elementMove.setMovDirection(ElementMove.STOP);
        		//return;
        	}
        }
		
	}

	// método que checa se o pacman comeu (se sobrepôs a) um fantasma (apenas se sobre efeito de power pellet) ou uma fruta
	// caso positivo, realiza as atualizações necessárias (diminuir o contador de fantasmas, adicionar os respectivos pontos, etc)
	private void checkPacmanEatSomeOneAndOrTimeFruittoDisappear(ArrayList<Element> elements, Pacman pacman) {

        Element eTemp;
        for(int i =1; i < elements.size(); i++){
            eTemp = elements.get(i);
            if(pacman.overlap(eTemp)){
                if(eTemp.isTransposable() && eTemp.isMortal()){
                    elements.remove(eTemp);
                    if (eTemp instanceof Ghost){
						int score = 200*(4-pacman.getNumberGhosttoEat());
						pacman.minusNumberGhotstoEat();
						pacman.addScore(score);
						pacman.addRemainingScore(score);
						changeBackground(score, eTemp.getPos());
                    }
                    
                    if (eTemp instanceof ElementGivePoint){

	                    pacman.addScore(((ElementGivePoint) eTemp).getNumberPoints());
    	                pacman.addRemainingScore(((ElementGivePoint) eTemp).getNumberPoints());
                      
	                    if (eTemp instanceof PacDots){
    	                	pacman.minusNumberDotstoEat();
						}
            	        if (eTemp instanceof PowerPellet){
                	    	for(int k=1;k<=pacman.getNumberGhosttoEat(); k++){
                    			((Ghost)elements.get(k)).changeGhosttoBlue("ghostBlue.png");
							}
						pacman.setStartTimePower(System.currentTimeMillis());
                    }
                      
                    }
                }
                int remainingScore=pacman.getRemainingScore();
                if(remainingScore>=10000){
                	pacman.addLife();
                	pacman.setRemainingScore(remainingScore-10000);
                }
                
            }   
            else{
            	if (eTemp instanceof Cherry){
            		long elapsed = System.currentTimeMillis()-((Cherry)eTemp).getStartTime();
            		if (elapsed>=15000){
            			elements.remove(eTemp);
            		}
            		
            		
            	}
            	if (eTemp instanceof Strawberry){
            		long elapsed = System.currentTimeMillis()-((Strawberry)eTemp).getStartTime();
            		if (elapsed>=15000){
            			elements.remove(eTemp);
            		}
            	}
                 	
            }
        }
        
	}

	// método que muda momentaneamente o plano de fundo para indicar a pontuação obtida após comer um fantasma
	private void changeBackground(int numPoints, Position pos) {
		Wall pointImage = new Wall(numPoints + ".jpg");
		pointImage.setTransposable(true);
		pointImage.setPosition(pos.getX(), pos.getY());
		Main.gamePacMan.addElement(pointImage);
	}

	private void checkTimeChangeBackground() {

	}

	// método que checa se já deu o tempo de aparecer uma nova fruta (Strawberry)
	private void checkTimetoAppearFruit(ArrayList<Element> elements,  int [][]matrix) {
        
        long elapsedTime = System.currentTimeMillis()-Main.time;
        if (elapsedTime%75000<=10){
        	Strawberry straw=new Strawberry("strawberry.png");
        	straw.setStartTime(System.currentTimeMillis());
        	Position pos=getValidRandomPositionMatrix(matrix);
            straw.setPosition (pos.getX(),pos.getY());
            elements.add(straw);
        }
        if (elapsedTime%50000<=10){
        	Cherry cherry=new Cherry("cherry.png");
        	cherry.setStartTime(System.currentTimeMillis());
        	Position pos=getValidRandomPositionMatrix(matrix);
            cherry.setPosition (pos.getX(),pos.getY());
            elements.add(cherry);
        }
		
	}

	// método auxiliar ao anterior que pega uma posição aleatória da matriz do plano do jogo que seja válida (não tenha elementos ou tenha apenas uma pacdot, o que na matriz é indicado por espaços preenchidos com 0)
	private Position getValidRandomPositionMatrix(int[][] matrix) {
		Random gerador = new Random();
		int x;
		int y;
		Position pos=new Position(0,0);
		do{
			x=gerador.nextInt(Consts.NUM_CELLS);		
			y=gerador.nextInt(Consts.NUM_CELLS);
		}while(matrix[x][y]==1);
		pos.setPosition(x, y);
		return pos;
	}

	// método que checa se já deu o tempo dos fantasmas voltarem do estado azul para o normal (ou seja, se o efeito da powerpellet vigente expirou)
	private void checkTimeGhostBeNormal(ArrayList<Element> elements,
			Pacman pacman) {
        long start=pacman.getStartTimePower();
        if (start!=0){
        	long elapsedTimePower = System.currentTimeMillis()-start;
        	if(elapsedTimePower>=7000){
        		
        		pacman.setStartTimePower(0);
        		Element e;
        		for (int i=1;i<=pacman.getNumberGhosttoEat();i++){
        			e = elements.get(i);
        			if(e instanceof Blinky){
        				((Blinky) e).changeGhosttoNormal("blinky.png");
        			}
        			if(e instanceof Pinky){
        				((Pinky) e).changeGhosttoNormal("pinky.png");
        			}
        			if(e instanceof Inky){
        				((Inky) e).changeGhosttoNormal("inky.png");
        			}
        			if(e instanceof Clyde){
        				((Clyde) e).changeGhosttoNormal("clyde.png");
        			}
        			
                }		
        			
        	}
        	
        }
        

		
	}

	// método que checa se o elemento passado como parâmetro está em uma posição válida (em relação aos elementos de elemArray)
	public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);            
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }        
        return true;
    }
}
