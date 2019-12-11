package pana;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OptmalParentization {

	
	// 30x35, 35x15, 15x5, 5x10, 10x20, 20x25

	// public static int[] p = {10, 100, 5, 50}; //this example should return 7500,
	// the optimal amount of multiplication operations for these matrices
	public static int[] p = new int[0]; // should return 15125
	protected static int m[][];
	protected static int s[][];
	protected static int n;
	private static String NOMEARQUIVO = "src/entrada.txt";
	private static String NOMEARQUIVOSAIDA = "src/saida.txt";

	public static void main(String[] args) {

		try {
			FileReader arquivo = new FileReader(NOMEARQUIVO);
			BufferedReader breader = new BufferedReader(arquivo);
			FileWriter arq = new FileWriter(NOMEARQUIVOSAIDA);
			PrintWriter gravarArq = new PrintWriter(arq);
			String linha = breader.readLine();
			while (linha != null) {
				int[] linhaInt = new int[9];
				int el = 0;
				for (String elemento : linha.split(",")) {
					if (!elemento.isEmpty()) {
						linhaInt[el] = Integer.parseInt(elemento.trim());
						el++;	
					}
				}
				p = linhaInt;
				if (p[0] != 0) {
					exibirParentizacaoParaMatriz(p, gravarArq);
				}
				linha = breader.readLine();
			}

			arquivo.close();
			arq.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void exibirParentizacaoParaMatriz(int[] p2, PrintWriter gravarArq) throws IOException {
		// TODO Auto-generated method stub
		
		gravarArq.print("\n\n");
		System.out.println("\n\n");
		System.out.print("{");
		gravarArq.print("{");
		for (int i = 0; i < p2.length; i++) {
			gravarArq.print(p2[i] + " ");
			System.out.print(p2[i] + " ");
		}
		gravarArq.print("}");
		System.out.print("}");
		System.out.println("");
		System.out.println("");
		gravarArq.print("\n");
		gravarArq.print("\n");
		
		gravarArq.print("A PARENTIZACAO COM MENOR CUSTO EH: " + ordemCadeiaGuloso(p2, 1, p2.length - 1) + "\n");
		System.out.println();
//		printarParentizacao(s, 0, n - 1, gravarArq);
	}
	
	public static int ordemCadeiaGuloso(int[] p,int i,int j) {
		if(i==j) {
			System.out.print("A"+i);
			return 0;
		}	
		int qMin = p[i-1]*p[i]*p[j];
		int kMin =	i;
		for(int k = i + 1; k<j-1;k++) {
			if	(qMin >= p[i-1]*p[k]*p[j]) {
				kMin=k;
				qMin = p[i-1]*p[k]*p[j];
			} 
		}
		System.out.print("(");
		int	qEsq = ordemCadeiaGuloso(p, i, kMin);
		int qDir = ordemCadeiaGuloso(p, kMin+1, j);
		System.out.print(")");
		return qEsq + qDir + p[i-1]*p[kMin]*p[j];
		}
	
	public static int ordemCadeiaDeMatriz(int[] p) {
		n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];
		for (int i = 0; i < n; i++) {
			m[i][i] = 0;
		}

		for (int row = 1; row < n; row++) {
			for (int i = 0; i < n - row; i++) { // valor por linha
				int j = i + row;
				m[i][j] = Integer.MAX_VALUE; // defini valor absurdamenter grande
				for (int k = i; k < j; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (k == i) {
						m[i][j] = q;
						s[i][j] = k;
					} else if (k == i + 1) {
						if (m[i][j] > q) {
							m[i][j] = q;
							s[i][j] = k;
						}
					} else {
						if (q < m[i][j]) {
							m[i][j] = q;
							s[i][j] = k;
						}
					}
				}

				if (row == p.length - 2) { // verificar se esta no lugar certo
					return m[i][j]; // custo de retorno
				}
			}
		}
		return -1; // error
	}

	static void printarParentizacao(int[][] s, int i, int j, PrintWriter gravarArq) throws IOException {
		if (i == j) {
			gravarArq.print("A"+(i + 1));
			System.out.print((getMatriz(i + 1)));
		} else {
			System.out.print("(");
			gravarArq.print("(");
			printarParentizacao(s, i, s[i][j],gravarArq);
			printarParentizacao(s, s[i][j] + 1, j, gravarArq);
			gravarArq.print(")");
			System.out.print(")");
		}
	}
	
	static String getMatriz(int n) {
		String letra = "";
		switch (n) {
		case 1:
			letra = "A";
			break;
		case 2:
			letra = "B";
			break;
		case 3:
			letra = "C";
			break;
		case 4:
			letra = "D";
			break;
		case 5:
			letra = "E";
			break;
		case 6:
			letra = "F";
			break;
		}
		return letra;
	}
}
