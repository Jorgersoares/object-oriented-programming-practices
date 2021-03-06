package swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JFrame;

public class TelaCx extends JFrame {
	private JPanel contentPane;
	private JTextArea textArea;
	private JButton button1;
	private JButton button;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaListagem frame = new TelaListagem();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public TelaCx() {

		setTitle("Listagem de caixas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 242);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		button1 = new JButton("Caixa de entrada");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Mensagem> msgs;
				String text = "";
				try {
					msgs = Fachada.listarCaixaEntrada();
					for(Mensagem m : msgs) {
						text += m + "\n";
					}
					if(text.equals("")) {
						JOptionPane.showMessageDialog(null, "N??o h?? mensagens!");
					}
					else {
						textArea.setText(text);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button1.setBounds(34, 177, 184, 23);
		contentPane.add(button1);
		
		button = new JButton("Caixa de saida");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Mensagem> msgs;
				String text = "";
				try {
					msgs = Fachada.listarCaixaSaida();
					for(Mensagem m : msgs) {
						text += m + "\n";
					}
					if(text.equals("")) {
						JOptionPane.showMessageDialog(null, "N??o h?? mensagens!");
					}
					else {
						textArea.setText(text);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(292, 176, 184, 23);
		contentPane.add(button);


	}
}

