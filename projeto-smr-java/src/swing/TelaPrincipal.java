package swing;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {
	private JFrame frame;
	private JLabel label;
	
	private JMenu mnUsuario;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	
	private JMenu mnMensagem;
	private JMenuItem mnEnviar;
	private JMenuItem mnApagar;
	
	private JMenu mnCaixa;
	
	private JMenu mnAdmin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("SMR");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Até breve !");
			}
		});

		frame.setBounds(100, 100, 384, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		//-------------BARRA DE MENU-----------------------------------
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		//-------------MENU-----------------------------------
		
		mnMensagem = new JMenu("Opções Mensagem");
		
		menuBar.add(mnMensagem);

		mnEnviar = new JMenuItem("Enviar nova mensagem");
		mnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Fachada.obterLogada()==null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				
				else {
					TelaEnviarMensagem j = new TelaEnviarMensagem();
					j.setVisible(true);
				}
			}
		});
		mnMensagem.add(mnEnviar);

		
		mnApagar = new JMenuItem("Apagar mensagem");
		mnApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(Fachada.obterLogada()==null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaApagar j = new TelaApagar();
					j.setVisible(true);
				}
				
			}
		});
		
		mnMensagem.add(mnApagar);
		
		//-------------MENU-----------------------------------
		
		mnCaixa = new JMenu("Consultar caixas");
		menuBar.add(mnCaixa);
		mnCaixa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Fachada.obterLogada()==null)
					JOptionPane.showMessageDialog(frame, "voce precisa se logar");
				else {
					TelaCx j = new TelaCx();
					j.setVisible(true);
				}
			}
		});
		
		//-------------MENU-----------------------------------
		mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);

		menuItem = new JMenuItem("Login");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin j = new TelaLogin();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem);

		menuItem_1 = new JMenuItem("Logoff");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin j = new TelaLogin();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem_1);

		menuItem_2 = new JMenuItem("Cadastrar");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroUsuario j = new TelaCadastroUsuario();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem_2);

		menuItem_3 = new JMenuItem("Listar");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagem j = new TelaListagem();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem_3);
		
		menuItem_4 = new JMenuItem("Cadastrar Admin");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroAdmin j = new TelaCadastroAdmin();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem_4);
		
		menuItem_5 = new JMenuItem("Relatorios");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRelatorio j = new TelaRelatorio();
				j.setVisible(true);
			}
		});
		mnUsuario.add(menuItem_5);


	}
}
