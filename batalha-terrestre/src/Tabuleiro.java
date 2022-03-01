import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Tabuleiro  {

	private JFrame frame;
	private JLabel[][] grid;
	private JogoBatalhaTerrestre backend_game = new JogoBatalhaTerrestre();
	private JLabel label_tirosT = new JLabel("Tiros");
	private JLabel label_acertosT = new JLabel("Acertos");
	private JLabel label_acertos = new JLabel("0");
	private JLabel label_tiros = new JLabel("0");
	private final JLabel label_status = new JLabel("Status");
	private final JLabel label_statusT = new JLabel("");
	private int tam = 60;
	private int n=10; //Matriz 10x10

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabuleiro window = new Tabuleiro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tabuleiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		System.out.println(backend_game.toString());
		frame = new JFrame();
		frame.setTitle("Matriz de Labels");
		frame.setBounds(100, 100, 815, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Labels de info do jogo
		label_acertosT.setBounds(693, 40, 70, 15);
		frame.getContentPane().add(label_acertosT);
		
		label_tirosT.setBounds(693, 93, 70, 15);
		frame.getContentPane().add(label_tirosT);
		
		label_acertos.setBounds(713, 66, 70, 15);
		frame.getContentPane().add(label_acertos);
		
		label_tiros.setBounds(713, 110, 70, 15);
		frame.getContentPane().add(label_tiros);
		
		label_status.setBounds(693, 137, 70, 15);
		frame.getContentPane().add(label_status);
		
		label_statusT.setBounds(713, 152, 70, 15);
		frame.getContentPane().add(label_statusT);

		//inicializar a matriz de labels
		grid = new JLabel[n][n];
		for(int y=0; y < n; y++){
			for(int x=0; x < n; x++){
				grid[x][y]=new JLabel( );
				frame.getContentPane().add(grid[x][y]);
				grid[x][y].setBounds(x*tam, y*tam, tam, tam);
				grid[x][y].setBackground(Color.WHITE);
				grid[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				grid[x][y].setVerticalAlignment(SwingConstants.CENTER);
				grid[x][y].setBorder(new LineBorder(Color.BLACK, 1, true));
				grid[x][y].setOpaque(true);
				
				grid[x][y].addMouseListener(new  MouseAdapter(){
					public void mouseClicked(MouseEvent e){	//click
						int indicex = e.getComponent().getX()/tam;
						int indicey = e.getComponent().getY()/tam;
						String result_tiro = "";
						
						try{
							result_tiro = backend_game.atirar(indicey+1, indicex+1);
							label_statusT.setText(result_tiro);
						}
						catch(Exception err){
							label_statusT.setText(err.getMessage());
						}
						
						if(result_tiro == "alvo") {
							grid[indicex][indicey].setBackground(Color.RED);
							label_acertos.setText(Integer.toString(backend_game.getAcertos()));
						}
						else if(result_tiro == "proximo") {
							grid[indicex][indicey].setBackground(Color.BLUE);
						}
						else if(result_tiro == "distante") {
							grid[indicex][indicey].setBackground(Color.YELLOW);
						}
						
						label_tiros.setText(Integer.toString(backend_game.getTiros()));
							
						//Verifica se jogo acabou
						if(backend_game.terminou()) {
							String resultado = backend_game.checarResultadoFinal();
							
							try {
								backend_game.getResultadoFinal();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							int resposta = JOptionPane.showConfirmDialog(
									frame, resultado + ", deseja jogar novamente");
							
							if(resposta == JOptionPane.YES_OPTION)
								ResetGame();
							
							else if(resposta == JOptionPane.NO_OPTION)
								System.exit(0);
							
							else
								System.exit(0);
								
						}
						
					}
				});
			}
		}

	}
	private void ResetGame() {
		//Resetando o tabuleiro
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				grid[x][y].setBackground(Color.WHITE);
			}
		}
		//Novo objeto do jogo
		backend_game = new JogoBatalhaTerrestre();
		
		//Reseta o placar
		label_acertos.setText(Integer.toString(backend_game.getAcertos()));
		label_tiros.setText(Integer.toString(backend_game.getTiros()));
		label_statusT.setText("");
	}
}

