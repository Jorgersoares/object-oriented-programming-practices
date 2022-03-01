import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class JogoBatalhaTerrestre {
	private String[][] tabuleiro_secreto = new String[10][10];
	private String[][] tabuleiro_publico = new String[10][10];
	private int contadorTiros; 
	private int contadorAcertos;
	private int aux = 0;
	private int TAM = 10;
	private int ELEMENTS_QTD = 5;
	private int MAX_SHOTS = 19;
	private String history = "";
	
	//Construtor
	public JogoBatalhaTerrestre() {
		contadorTiros = 0;
		contadorAcertos = 0;
		
		//Matriz interna do jogo
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				tabuleiro_secreto[x][y] = " . ";
				tabuleiro_publico[x][y] = " . ";
			}
		}
		
		while(aux < 5) {
			int px, py;	
			Random random = new Random();
			px = random.nextInt(10);
			py = random.nextInt(10);
			if(tabuleiro_secreto[px][py] == " O " || tabuleiro_secreto[px][py] == " 1 ") 
				continue;
			tabuleiro_secreto[px][py] = " O ";
			AddBorda(px,py);
			aux++;
			
		}
	}
	
	public void AddBorda(int linha, int coluna){
		for(int x = -1; x < 2; x++) {
			for(int y = -1; y < 2; y++) {
				try {
					if(this.tabuleiro_secreto[linha+x][coluna+y] == " . ") {
						this.tabuleiro_secreto[linha+x][coluna+y] = " 1 ";
					}	
				} catch(Exception err) {
					
				}
			}
		}
	}
	
	private boolean checarProximo(int linha, int coluna) {		
		if(this.tabuleiro_secreto[linha][coluna] == " 1 ")
			return true;
		return false;	
	}
	
	public boolean terminou() {
		if(this.getAcertos() == ELEMENTS_QTD || this.getTiros() == MAX_SHOTS)
			return true;
		else
			return false;
	}
	
	public String checarResultadoFinal() {
		if(this.getTiros() == MAX_SHOTS && this.getAcertos() < ELEMENTS_QTD)
			return "Perdeu";
		else if(this.getAcertos() == ELEMENTS_QTD && this.getTiros() < MAX_SHOTS)
			return "Ganhou";
		throw new IllegalArgumentException("Jogo não terminou ainda!");	
	}
	
	public String atirar(int linha, int coluna) {
		linha--;
		coluna--;
		
		if((linha > TAM || coluna > TAM) || (linha < 0 || coluna < 0))
			throw new RuntimeException("Posicao invalida");
		
		else {
			if(this.tabuleiro_secreto[linha][coluna] == " O ") {
				this.contadorAcertos++;
				this.contadorTiros++;
				this.tabuleiro_secreto[linha][coluna] = " C ";
				this.tabuleiro_publico[linha][coluna] = " C ";
				history += "\nTiro na pos X:" + linha + " Y:" + coluna + " status: " + "alvo";
				return "alvo";
			}
			
			else if(this.checarProximo(linha, coluna) == true) {
				this.contadorTiros++;
				this.tabuleiro_secreto[linha][coluna] = " P ";
				this.tabuleiro_publico[linha][coluna] = " P ";
				history += "\nTiro na pos X:" + linha + " Y:" + coluna + "status: " + "proximo";
				return "proximo";
			}
			
			//Verifica se um algum elemento já foi acertado por um tiro
			else if(this.tabuleiro_secreto[linha][coluna] != " . ") {
				history += "\nTiro na pos X:" + linha + " Y:" + coluna + "status: " + "repetido";
				throw new RuntimeException("repetido");
			}
			
			else {
				this.contadorTiros++;
				this.tabuleiro_publico[linha][coluna] = " X ";
				this.tabuleiro_secreto[linha][coluna] = " X ";
				history += "\nTiro na pos X:" + linha + " Y:" + coluna + "status: " + "distante";
				return "distante";
			}
		}	
	}
	
	private void salvarResultado(String path, String content) throws IOException{
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.append(content);
		buffWrite.close();   
    	}
	
	public String getResultadoFinal() throws IOException {
		salvarResultado("arq.txt", history);
		String res = "";
		res = res + "Acertos: " + this.contadorAcertos + "\n";
		res = res + "Nº de tiros: " + this.contadorTiros + "\n";
		
		return res;
	}
	
	public int getAcertos() {
		return this.contadorAcertos;
	}
	
	public int getTiros() {
		return this.contadorTiros;
	}
	
	public String toString() {
		String stringTabuleiro = "\nLegenda:\nX - Tiro longe\nC - Tiro no alvo\nP - Tiro perto do alvo\n\n";
		stringTabuleiro+="       1  2  3  4  5  6  7  8  9  10\n";
		
		for(int x = 0; x < 10; x++) {
			if(x == 9) stringTabuleiro+=String.format(" %d : ", x+1);
			else stringTabuleiro+=String.format(" %d  : ", x+1);
			
			for(int y = 0; y < 10; y++) {
				
				stringTabuleiro+=tabuleiro_publico[x][y];
				
			}
			stringTabuleiro+="\n";
		}
		
		/*Matriz interna com os alvos visiveis
		*Comentar em produção!
		*/
		stringTabuleiro+="\n       1  2  3  4  5  6  7  8  9  10\n";
		for(int x = 0; x < 10; x++) {
			if(x == 9) stringTabuleiro+=String.format(" %d : ", x+1);
			else stringTabuleiro+=String.format(" %d  : ", x+1);
			
			for(int y = 0; y < 10; y++) {
				stringTabuleiro+=tabuleiro_secreto[x][y];
			}
			stringTabuleiro+="\n";
		}	
		//Retorno do ToString
		return stringTabuleiro;
	}
	
}
