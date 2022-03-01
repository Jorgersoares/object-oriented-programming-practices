package console;


/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 * 
 * Teste do projeto de POO
 **********************************/


import java.util.ArrayList;

//import javax.swing.ImageIcon;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Mensagem;
import modelo.Pessoa;
import repositorio.Repositorio;

public class TesteConsole {

	public void teste1(){
		System.out.println("================= TESTE 1 ======================");
		Pessoa p;
		Mensagem m;

		try {
			p = Fachada.cadastrarPessoa("joao@ifpb", "123", "joao");
			p = Fachada.cadastrarPessoa("maria@ifpb", "123", "maria");
			p = Fachada.cadastrarPessoa("jose@ifpb", "123", "jose");
			p = (Pessoa) Fachada.cadastrarAdministrador("admin@ifpb", "123", "admin1", /* null, */ "DTI");
			System.out.println("\n-------- listar todas pessoas---------");
			System.out.println(Fachada.listarPessoas(""));
			System.out.println("\n-------- listar pessoas que cont�m 'jo' ---------");
			System.out.println(Fachada.listarPessoas("jo"));
		}catch (Exception e) {
			System.out.println("mensagem de erro==>"+ e.getMessage());
		}

		try {
			p = Fachada.login("joao@ifpb", "123");
			System.out.println("pessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("maria@ifpb", "ola maria quanto tempo! saudades");
			m = Fachada.enviarMensagem("jose@ifpb", "ola jose quanto tempo!");
			m = Fachada.enviarMensagem("joao@ifpb", "testando pra mim mesmo...");
			System.out.println("\n-------- caixa de entrada joao---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("\n-------- caixa de saida joao---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			Fachada.logoff();
		}catch (Exception e) {
			System.out.println("mensagem de erro==>"+ e.getMessage());
		}

		try {
			p = Fachada.login("maria@ifpb", "123");
			System.out.println("pessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("joao@ifpb", "oi joao, saudades tb! vamos nos encontar!");
			m = Fachada.enviarMensagem("joao@ifpb", "que tal um almoco?");
			m = Fachada.enviarMensagem("joao@ifpb", "vamos chamar jose?");
			m = Fachada.enviarMensagem("maria@ifpb", "testando pra mim mesmo...");
			System.out.println("\n-------- caixa de entrada maria---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("\n-------- caixa de saida maria---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			m = Fachada.apagarMensagem(6);
			System.out.println("mensagem excluida =>"+ m.getTexto());
			Fachada.logoff();
		}catch (Exception e) {
			System.out.println("mensagem de erro==>"+ e.getMessage());
		}

		try {
			Administrador administrador = new Administrador("admin@ifpb", "123", "admin1", /* null ,*/ "DTI");
			Repositorio.getPessoas().put(administrador.getEmail(), administrador);
			p = (Pessoa) Fachada.login("admin@ifpb", "123");
			System.out.println("pessoa logada =>" + Fachada.obterLogada().getNome());
			m = Fachada.enviarMensagem("joao@ifpb", "Benvindo ao sistema!");
			m = Fachada.enviarMensagem("maria@ifpb", "Benvindo ao sistema!");
			m = Fachada.enviarMensagem("jose@ifpb", "Benvindo ao sistema!");
			System.out.println("\n-------- caixa de entrada admin---------");
			imprimirMensagens(Fachada.listarCaixaEntrada());
			System.out.println("-------- caixa de saida admin---------");
			imprimirMensagens(Fachada.listarCaixaSaida());
			System.out.println("\n\n******* espionando as mensagens do sistema*********");
			imprimirMensagens(Fachada.espionarMensagens(""));
			System.out.println("-------- espionando as mensagens do sistema---------");
			imprimirMensagens(Fachada.espionarMensagens("saudade"));
			System.out.println("\n-------- RELATOIO 1 - pessoas q nao enviaram mensagens---------");
			System.out.println(Fachada.relatorio1());
			System.out.println("\n-------- RELATOIO 2 - mensagens com emitente igual destinatario---------");
			System.out.println(Fachada.relatorio2());
			Fachada.logoff();
		}catch (Exception e) {
			System.out.println("mensagem de erro==>"+ e.getMessage());
		}
		
		System.out.println("teste1 concluido");
	}
	public static void imprimirMensagens(ArrayList<Mensagem> lista) {
		for(Mensagem msg: lista) {
			System.out.println(msg);
		}
	}

	public void teste2() {
		System.out.println("================= TESTE 2 ======================");
		Pessoa p;
		Mensagem m ;
		try{
			p = Fachada.login("jose@ifpb", "123");
						m = Fachada.enviarMensagem("jose@ifpb", "");
						m = Fachada.enviarMensagem("jose@ifpb", 
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"+
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"+
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"+
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"+
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx"+
								"xxxxxxxx xxxxxxxxxxxxxxxxxxxxxxxxx xxxxxxxxxx xxxxxxxxx");
			System.out.println("Falha1 => nao valida texto da mensagem");
		}catch (Exception e) {
			System.out.println("==>"+ e.getMessage());
		}

		try{
			m = Fachada.apagarMensagem(1);
			System.out.println("Falha2 => apagar mensagem de outra pessoa");
		}catch (Exception e) {
			System.out.println("==>"+ e.getMessage());
		}

		try{
			p = Fachada.login("maria@ifpb", "123");
			System.out.println("Falha3 => login invalido");
		}catch (Exception e) {
			System.out.println("==>"+ e.getMessage());
		}

		System.out.println("teste2 concluido");
	}

	/*
	public void teste3(){
		System.out.println("================= TESTE 3 ======================");
		Pessoa p;
		Mensagem m;

		try {	
			ImageIcon icon=null;
//			icon = new ImageIcon(getClass.getResource("/imagens/pessoa.jpg"));
			icon = new ImageIcon(getClass().getResource("/pessoa.jpg"));
//			icon = new ImageIcon("c:/pessoa.jpg"));
			p = Fachada.cadastrarPessoa("ana@ifpb", "123", "ana");
		}catch (Exception e) {
			System.out.println("mensagem de erro==>"+ e.getMessage());
		}
		System.out.println("teste3 concluido");
	}
	*/
	
	

	
	
	//  ***********************************************
	public static void main (String[] args)   
	//  ***********************************************
	{
		TesteConsole testeconsole = new TesteConsole();
		testeconsole.teste1();
		testeconsole.teste2();
		//testeconsole.teste3();
	}

}
