package modelo;

import java.time.LocalDateTime;

public class Mensagem {
	

	private static int class_id = 0;
	private int id;
	private Pessoa emitente;
	private Pessoa destinatario;
	private String texto;
	private LocalDateTime data;
	
	public Mensagem(Pessoa emitente, Pessoa dest, String texto) {
		Mensagem.class_id++;
		this.id = Mensagem.class_id;
		this.emitente = emitente;
		this.destinatario = dest;
		this.texto = texto;
		this.data = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public Pessoa getEmitente() {
		return emitente;
	}

	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Mensagem: " + this.texto + "\nEmitente: " + this.emitente +
				"\nDestinatario: " + this.destinatario + "\nData da mensagem: " +
				this.data + "\n";
	}
}
