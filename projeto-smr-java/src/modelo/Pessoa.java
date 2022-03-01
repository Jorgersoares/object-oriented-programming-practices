package modelo;

//import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pessoa {
	
	private String email;
	private String nome;
	private String senha;
	// private BufferedImage image;
	private ArrayList<Mensagem> caixaEntrada = new ArrayList<>();
	private ArrayList<Mensagem> caixaSaida = new ArrayList<>();
	
	public Pessoa(String email, String senha, String nome) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public String getNome() {
		return this.nome;
	}

	public ArrayList<Mensagem> getCaixaEntrada() {
		return caixaEntrada;
	}
	
	public ArrayList<Mensagem> getCaixaSaida() {
		return caixaSaida;
	}

	public void adicionarEntrada(Mensagem m) {
		this.caixaEntrada.add(m);
	}


	public void adicionarSaida(Mensagem m) {
		this.caixaSaida.add(m);
	}
	
	@Override
	public String toString() {
		return String.format("%s", this.nome);
	}
	
}
