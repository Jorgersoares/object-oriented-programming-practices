package swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;

public class TelaApagar extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConsulta_1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaConsulta frame = new TelaConsulta();
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
	public TelaApagar() {
		setTitle("Apagar mensagem");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnConsulta_1 = new JButton("Exibir todas as mensagens");
		btnConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto;
				ArrayList<Mensagem> cx_saida = Fachada.obterLogada().getCaixaSaida();
				ArrayList<Mensagem> cx_entrada = Fachada.obterLogada().getCaixaEntrada();
				
				try{
					texto = "Caixa de entrada: \n";
					
					if (cx_entrada.isEmpty())
						texto += "n�o existe";
					else 	
						for(Mensagem p: cx_entrada) 
							texto +=  p + "\n"; 

					textArea.setText(texto + "\n");
					
					texto = "Caixa de saida: \n";
					
					if (cx_entrada.isEmpty())
						texto += "n�o existe";
					else 	
						for(Mensagem p: cx_saida) 
							texto +=  p + "\n"; 

					textArea.setText(textArea.getText() + texto + "\n");
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsulta_1.setBounds(414, 13, 271, 23);
		contentPane.add(btnConsulta_1);

		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 11, 348, 228);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(414, 48, 104, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("id da mensagem");
		label.setBounds(531, 48, 127, 15);
		contentPane.add(label);
		
		JButton button = new JButton("Apagar");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(label.getText());
				try {
					Fachada.apagarMensagem(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				
			}
		
		});
		
		button.setBounds(414, 77, 104, 25);
		contentPane.add(button);
		
	
  }
}
