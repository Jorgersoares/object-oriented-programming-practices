package swing;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Mensagem;

public class TelaEnviarMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNome;
	private JLabel lblPreco;
	private JButton btnCriar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
		
		/*
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaEnviarMensagem frame = new TelaEnviarMensagem();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		*/

	/**
	 * Create the frame.
	 */
	public TelaEnviarMensagem() {
		setTitle("Enviar mensagem");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(72, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Para");
		lblNome.setBounds(10, 14, 46, 14);
		contentPane.add(lblNome);

		lblPreco = new JLabel("Texto");
		lblPreco.setBounds(10, 52, 46, 14);
		contentPane.add(lblPreco);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		btnCriar = new JButton("Enviar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty())
						lblmsg.setText("campo vazio");
					else {
						
						
						String email = textField.getText();
						String content = textField_1.getText();
						Fachada.enviarMensagem(email, content);
						lblmsg.setText("Mensagem enviada com sucesso!");
						textField.setText("");
						textField_1.setText("");
						textField.requestFocus();
					}
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(168, 48, 115, 23);
		contentPane.add(btnCriar);

		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 94, 273, 14);
		contentPane.add(lblmsg);
	}
}
