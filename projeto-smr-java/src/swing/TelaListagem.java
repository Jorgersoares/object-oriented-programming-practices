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
import modelo.Pessoa;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class TelaListagem extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton button1;
	private JTextField textField;
	private JLabel label;

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
	public TelaListagem() {

		setTitle("Listagem de pessoas");
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

		textField = new JTextField();
		textField.setBounds(167, 179, 210, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		label = new JLabel("Termo da pesquisa");
		label.setBounds(24, 181, 136, 15);
		contentPane.add(label);

		button1 = new JButton("Listar ");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String termo = textField.getText();
				ArrayList<Pessoa> pessoas;
				String text = "";
				try {
					pessoas = Fachada.listarPessoas(termo);
					for(Pessoa p : pessoas) {
						text += p + "\n";
					}
					if(text.equals("")) {
						JOptionPane.showMessageDialog(null, "Não há ninguem com este termo!");
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
		button1.setBounds(389, 177, 145, 23);
		contentPane.add(button1);


	}
}
