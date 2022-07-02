package control;

import elements.Blinky;

import elements.Cherry;
import elements.Clyde;
import elements.Inky;
import elements.PacDots;
import elements.Pinky;
import elements.PowerPellet;
import elements.Pacman;
import elements.Element;
import elements.Wall;
import utils.Consts;
import utils.Drawing;
import utils.Stage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GameScreen extends javax.swing.JFrame implements KeyListener {
    
    private Pacman pacman;
    private ArrayList<Element> elemArray;
    private final GameController controller = new GameController();
    private Stage stage;
    int cont = 0; 
    String fileName="jogo.ser";
    
    public GameScreen() {
        this.setVisible(true);
    	Main.time = System.currentTimeMillis();
        Drawing.setGameScreen(this);
        initComponents();
        
        this.addKeyListener(this);   
        
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);
        elemArray = new ArrayList<Element>();
        pacman = null;
        if(Main.openSavedGame){
        	try{
        		openSavedGame(fileName);
        	}
        	catch(FileNotFoundException e1){
        	 		System.err.println("Arquivo jogo.ser não existente. Iniciando novo jogo ...");
                	this.stage = new Stage(Main.level);
                	fillInitialElemArrayFromMatrix(stage.getMatrix());
        	}
        	catch(IOException e1){
             		e1.printStackTrace();
            }
            catch(ClassNotFoundException e1){
             		e1.printStackTrace();
            }
        	 		
        }
        else {
        	this.stage = new Stage(Main.level);
        	fillInitialElemArrayFromMatrix(stage.getMatrix());
        }
    }
    
    public Pacman getPacman(){
    	return pacman;
    }    
    
    private void fillInitialElemArrayFromMatrix(int [][]matrix) {
	 	pacman = new Pacman("pacman.png");
        pacman.setPosition(1,1);
        this.addElement(pacman);

        Blinky blinky=new Blinky("blinky.png");
        blinky.setPosition (10,8);
        this.addElement(blinky);

        Pinky pinky=new Pinky("pinky.png");
        pinky.setPosition (10,9);
        this.addElement(pinky);
        
        Inky inky=new Inky("inky.png");
        inky.setPosition (10,10);
        this.addElement(inky);
        
        Clyde clyde=new Clyde("clyde.png");
        clyde.setPosition (8,9);
        this.addElement(clyde);
        
        
        for (int i=0;i<Consts.NUM_CELLS; i=i+1){
        	for(int j=0; j<Consts.NUM_CELLS; j=j+1){
        		switch (matrix[i][j]) {
        		case 1:
        			Wall wall1=new Wall("bricks6.png");
        			wall1.setPosition (i,j);
        			this.addElement(wall1);
        			break;
                case 0:    
                    PacDots pacDot=new PacDots("pac-dot.png");
                    pacDot.setPosition (i,j);
                    this.addElement(pacDot);
                    pacman.addNumberDotstoEat();
                    break;
                case 6:    
                    PowerPellet power=new PowerPellet("power_Pellet.png");
                    power.setPosition (i,j);
                    this.addElement(power);
                    break;    
                default:
                    break;
        		}
            }
        }

		
	}

	private void openSavedGame(String fileName) throws FileNotFoundException,IOException, ClassNotFoundException {
        try(FileInputStream savefile = new FileInputStream("savefile1.ser")) {
            ObjectInputStream entrada = new ObjectInputStream(savefile);
            this.stage = (Stage) entrada.readObject();
//            this.stage = new Stage(Main.level);
//            fillInitialElemArrayFromMatrix(stage.getMatrix());
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public final void addElement(Element elem) {
        elemArray.add(elem);
    }
    
    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }
    
    public void reStartGame(int numberLifes){
    	elemArray.clear();
    	elemArray = new ArrayList<Element>();
        pacman = null;
        
        this.stage = new Stage(Main.level);
    	fillInitialElemArrayFromMatrix(stage.getMatrix());
    	((Pacman)elemArray.get(0)).setNumberLifes(numberLifes);
    }
    
    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
     
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);
      
           
        for (int i = 0; i < Consts.NUM_CELLS; i=i+1) {
            for (int j = 0; j < Consts.NUM_CELLS; j=j+1) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + stage.getBackground());
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                    
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
               
        cont++;
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray,stage.getMatrix(),cont);
        this.setTitle(pacman.getStringPosition()+" Score:"+pacman.getScore()+" Lifes:"+pacman.getLifes()+" Level:" + Main.level+" NumberDots:"+pacman.getNumberDotstoEat() + " NumberGhosts:"+pacman.getNumberGhosttoEat());
      
        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }
    
    public void go() {
        TimerTask task = new TimerTask() {
            
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.setMovDirection(Pacman.MOVE_UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.setMovDirection(Pacman.MOVE_DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.setMovDirection(Pacman.MOVE_LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.setMovDirection(Pacman.MOVE_RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pacman.setMovDirection(Pacman.STOP);
        } else if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            saveElemArrayandStage();
        }
        pacman.setMoving(true);
    }

    private void saveElemArrayandStage() {
        try (FileOutputStream savefile = new FileOutputStream("savefile1.ser");) {
            ObjectOutputStream saida = new ObjectOutputStream(savefile);
            saida.writeObject(this.stage);
//            saida.writeObject(this.elemArray);
            saida.close();
        } catch(FileNotFoundException e) {
            System.out.println("arquivo de save não encontrado");
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("jogo salvo");
        }
    }

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);
        setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        
         pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void dispose(){
		super.dispose();
	}
}
