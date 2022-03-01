package fachada;
import java.util.ArrayList;


import repositorio.Repositorio;
import modelo.Pessoa;
import modelo.Mensagem;
import modelo.Administrador;

public class Fachada {
	private static Pessoa logado;
	private static Repositorio repo = new Repositorio();
	
	public static Pessoa login(String email,  String senha) throws Exception {
		if(Fachada.obterLogada() != null) {
			throw new Exception("Já existe um usuário logado!");
		}
		logado = repo.localizarPessoa(email, senha);
		return logado;
	}
	
	public static void logoff() {
		logado = null;
	}
	
	public static Pessoa obterLogada() {
		return logado;
	}
	
	public static Pessoa cadastrarPessoa(String email, String senha, String nome) throws Exception {
		if(email.matches("[A-Za-z0-9]+@+[A-Za-z0-9]*")) {
			Pessoa p = new Pessoa(email, senha, nome);
			repo.adicionar(p);
			return p;
		}
		throw new Exception("Email inválido!");
	}
	
	public static Administrador cadastrarAdministrador(String email, String senha, String nome, String setor) {
		Administrador adm = new Administrador(email,senha,nome,setor);
		repo.adicionar(adm);
		return adm;
	}
	
	
	public static ArrayList<Pessoa> listarPessoas(String termo) throws Exception {
		return repo.localizarPessoa(termo);
	}
	
	
	public static Mensagem enviarMensagem(String emaildest, String texto) throws Exception {
		Pessoa logada = Fachada.obterLogada();
		
		if(logada == null)
			throw new Exception("É necessário fazer login!");
		else {
			
			if(texto.length() > 200)
				throw new Exception("Ultrapassa 200 caracteres");
			
			for(Pessoa p: repo.localizarPessoa("")) {
				if(p.getEmail().equals(emaildest)) {
					Mensagem new_message = new Mensagem(
							logada,
							p,
							texto
					);
					
					repo.adicionar(new_message);
					p.adicionarEntrada(new_message);
					logada.adicionarSaida(new_message);
					return new_message;
				}
			}
			
			throw new Exception("Esse email não existe!");
		}
	}
		
	
	public static ArrayList<Mensagem> listarCaixaEntrada() throws Exception {
		if(Fachada.obterLogada() == null)
			throw new Exception("É necessário fazer login!");
	
		return Fachada.obterLogada().getCaixaEntrada();
	}
	
	public static ArrayList<Mensagem> listarCaixaSaida() throws Exception {
		if(Fachada.obterLogada() == null)
			throw new Exception("É necessário fazer login!");
		
		return Fachada.obterLogada().getCaixaSaida();
	}
	
	public static Mensagem apagarMensagem(int id) throws Exception {
		Pessoa logada = Fachada.obterLogada();
		
		if(logada == null)
			throw new Exception("É necessário fazer login!");
		
		Mensagem msg = repo.localizarMensagem(id);
		
		if(msg != null) {
			
			int status_cx_entrada = Fachada.listarCaixaEntrada().indexOf(msg);
			int status_cx_saida = Fachada.listarCaixaSaida().indexOf(msg);
			
			if(status_cx_entrada != -1) {
				logada.getCaixaEntrada().remove(msg);
				return msg;
			}
			
			if(status_cx_saida != -1) {
				logada.getCaixaSaida().remove(msg);
				return msg;
			}
		}
		
		throw new Exception("Esta mensagem não existe!");
	}
	
	public static ArrayList<Mensagem> espionarMensagens(String termo) {
		if(Fachada.obterLogada().getClass().getSimpleName().equals("Administrador")) {
			return repo.localizarMensagem(termo);
		}
		return null;
	}
	
	public static ArrayList<Pessoa> relatorio1(){
		
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		
		for(Pessoa p : repo.localizarPessoa("")) {
			if(p.getCaixaSaida().isEmpty()) {
				pessoas.add(p);
			}
		}
		return pessoas;
	}
	
	public static ArrayList<Mensagem> relatorio2() {
		if(Fachada.obterLogada().getClass().getSimpleName().equals("Administrador")) {
			ArrayList<Mensagem> msgs = new ArrayList<>();
			
			for(Mensagem m : repo.localizarMensagem("")) {
				if(m.getEmitente().equals(m.getDestinatario())) {
					msgs.add(m);
				}
			}
			
			return msgs;
		}
		else {
			return null;
		}
	}
}
