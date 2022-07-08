package control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import utils.Consts;

public class InitialScreen extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private final String nomeImagemInicial = "inicialimagem.png";

	JMenuBar menuBar;
	JMenu jogar;
	JMenuItem novoJogo;
	JMenuItem jogoSalvo;
	JMenu selecionarNivel;
	JMenuItem nivel1;
	JMenuItem nivel2;
	JMenuItem nivel3;
	JMenuItem nivel4;
	private static ButtonGroup niveis;

	// construtor da classe que inicia a tela inicial (barra de menu e plano de fundo)
	public InitialScreen() {
		setIconImage((new ImageIcon("imgs/pacman_logo.png")).getImage());

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		jogar = new JMenu("Jogar");
		menuBar.add(jogar);

		novoJogo = new JMenuItem("Novo Jogo");
		novoJogo.addActionListener(new HandlerStartButton());
		jogar.add(novoJogo);

		jogoSalvo = new JMenuItem("Jogo Salvo");
		jogoSalvo.addActionListener(new HandlerOpenButton());
		jogar.add(jogoSalvo);

		selecionarNivel = new JMenu("Selecionar Nível");
		menuBar.add(selecionarNivel);

		nivel1 = new JRadioButtonMenuItem("Nível 1");
		nivel1.addItemListener(new HandlerLevel1());
		selecionarNivel.add(nivel1);

		nivel2 = new JRadioButtonMenuItem("Nível 2");
		nivel2.addItemListener(new HandlerLevel2());
		selecionarNivel.add(nivel2);

		nivel3 = new JRadioButtonMenuItem("Nível 3");
		nivel3.addItemListener(new HandlerLevel3());
		selecionarNivel.add(nivel3);

		nivel4 = new JRadioButtonMenuItem("Nível 4");
		nivel4.addItemListener(new HandlerLevel4());
		selecionarNivel.add(nivel4);

		niveis = new ButtonGroup();
		niveis.add(nivel1);
		niveis.add(nivel2);
		niveis.add(nivel3);
		niveis.add(nivel4);
		niveis.setSelected(nivel1.getModel(), true);

		configureInitialScreen();
	}

	// método que configura imagem, dimensões, nome, etc da tela inicial
	private void configureInitialScreen(){
		int sizeWidth = Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right;
		int sizeHeight = Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom;
		
		setSize(sizeWidth, sizeHeight);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman"); 
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);		
	    

        try{
        	setContentPane(new JLabel(new ImageIcon(new File(".").getCanonicalPath() + Consts.PATH + nomeImagemInicial)));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }	
        //pack();
	}


	// handler da ação de iniciar um novo jogo
	public class HandlerStartButton implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			Main.initialScreen.setVisible(false);
	    	Main.initialScreen.dispose();
//			Main.level = InitialScreen.level;
			Main.startGame();
		}
	}

	// handler da ação de carregar um jogo salvo
	public class HandlerOpenButton implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			Main.initialScreen.setVisible(false);  
	    	Main.initialScreen.dispose();
	    	Main.openSavedGame = true;
	    	Main.startGame();
		}
	}

	// handler da ação de selecionar o nível 1
	public class HandlerLevel1 implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ev) {
			if(ev.getStateChange() == ItemEvent.SELECTED) {
				Main.level = 1;
			}
		}
	}

	// handler da ação de selecionar o nível 2
	public class HandlerLevel2 implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ev) {
			if(ev.getStateChange() == ItemEvent.SELECTED) {
				Main.level = 2;
			}
		}
	}

	// handler da ação de selecionar o nível 3
	public class HandlerLevel3 implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ev) {
			if(ev.getStateChange() == ItemEvent.SELECTED) {
				Main.level = 3;
			}
		}
	}

	// handler da ação de selecionar o nível 4
	public class HandlerLevel4 implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ev) {
			if(ev.getStateChange() == ItemEvent.SELECTED) {
				Main.level = 4;
			}
		}
	}
}
