import java.io.IOException;
import java.util.Scanner;

public class AplicacaoConsole {
	public static void main(String[] args) throws IOException {

		JogoBatalhaTerrestre jogo = new JogoBatalhaTerrestre();
		System.out.println(jogo);

		Scanner teclado = new Scanner(System.in);
		int linha, coluna;

		do{
			System.out.print("Digite a linha do tiro:");
			linha = teclado.nextInt();
			System.out.print("Digite a coluna do tiro: ");
			coluna = teclado.nextInt();
			try {
				String resposta = jogo.atirar(linha,coluna);
				System.out.println(resposta);
			}
			catch(Exception erro) {
				System.out.println(erro.getMessage());
				continue;
			}
			System.out.println("total de acertos:"+jogo.getAcertos());
			System.out.println("total de tiros:"+jogo.getTiros());
			System.out.println(jogo);

		}while(!jogo.terminou());

		System.out.println("\n GAME OVER !!");
		System.out.println("Resultado " + jogo.checarResultadoFinal());
		System.out.println(jogo.getResultadoFinal());
		teclado.close();
	}

}
