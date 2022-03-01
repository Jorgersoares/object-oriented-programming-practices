package repositorio;

import java.util.ArrayList;
import java.util.TreeMap;

import modelo.Mensagem;
import modelo.Pessoa;

public class Repositorio {
	private static TreeMap<String, Pessoa> pessoas = new TreeMap<>();
	private ArrayList<Mensagem> mensagens = new ArrayList<>();
	
	public void adicionar(Pessoa p) {
		pessoas.put(p.getEmail()+p.getSenha(),p);
	}
	
	public void adicionar(Mensagem m) {
		mensagens.add(m);
	}
	
	public void remover(Pessoa p) {
		pessoas.remove(p.getEmail()+p.getSenha());
	}
	
	public void remover(Mensagem m) {
		mensagens.remove(m);
	}
	
	public static TreeMap<String, Pessoa> getPessoas() {
		return pessoas;
	}
	public Pessoa localizarPessoa(String email, String senha) {
		return pessoas.get(email+senha);
	}
	
	public ArrayList<Pessoa> localizarPessoa(String padrao) {
		ArrayList<Pessoa> pessoas_list = new ArrayList<>();
		
		if(!padrao.equals("")){
			for(Pessoa p : pessoas.values()){
				if(p.getNome().contains(padrao))
					pessoas_list.add(p);
			}
		
			return pessoas_list;
		}
		
		for(Pessoa p : pessoas.values()) {
			pessoas_list.add(p);
		}
		
		return pessoas_list;
	}
	
	
	
	public Mensagem localizarMensagem(int id) {
		for(Mensagem msg : mensagens) {
			if(id == msg.getId()) {
				return msg;
			}
		}
		return null;
	}
	
	public ArrayList<Mensagem> localizarMensagem(String termo) {
		ArrayList<Mensagem> msg_list = new ArrayList<>();
		
		if(!termo.equals("")) {
			for(Mensagem msg : mensagens) {
				if(msg.getTexto().contains(termo)) 
					msg_list.add(msg);
			}
			return msg_list;
		}
		return mensagens;
	}
	
}
