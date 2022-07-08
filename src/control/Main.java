package control;

import javax.swing.JFrame;

public class  Main {

	public static InitialScreen initialScreen; 
	public static int level = 1; 
	public static boolean openSavedGame = false; 
	public static long time;
	
	public static GameScreen gamePacMan;

    // método principal que executa a tela inicial
    public static void main(String[] args) {
    	
    	initialScreen = new InitialScreen();
    	
    	initialScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	initialScreen.setVisible(true);
        
    }

    // método que inicia a tela do jogo propriamente dito
    public static void startGame(){
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main.gamePacMan = new GameScreen();
                gamePacMan.setVisible(true);
                gamePacMan.createBufferStrategy(2);
                gamePacMan.go();
            }
        });
    }
    
}

