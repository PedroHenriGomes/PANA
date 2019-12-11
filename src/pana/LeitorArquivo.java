package pana;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

	private static String NOMEARQUIVO = "src/entrada.txt";

	private static List<Integer[][]> matrizes = new ArrayList<>();

	protected static int[][] m;

	protected static int[][] s;

	public static void main(String[] args) {

		try {
			FileReader arquivo = new FileReader(NOMEARQUIVO);
			BufferedReader breader = new BufferedReader(arquivo);
			String linha = breader.readLine();
			while (linha != null) {
				int[] linhaInt = new int[7];
				int el = 0;
				for (String elemento : linha.split(",")) {
					if (!elemento.isEmpty()) {
						linhaInt[el] = Integer.parseInt(elemento.trim());
						el++;
					}
				}
				matrixChainOrder(linhaInt);
				exibirParentizacao();
				linha = breader.readLine();
			}

			arquivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static List<Integer[][]> obterMatriz(int[] linha) {
		for (int i = 0; i <= linha.length - 2; i++) {
			Integer[][] mat = new Integer[linha[i]][linha[i + 1]];
			System.out.println(mat);
			matrizes.add(mat);
		}
		return matrizes;
	}


	public static void matrixChainOrder(int[] dims) {
		int n = dims.length - 1;
		m = new int[n][n];
		s = new int[n][n];

		for (int lenMinusOne = 1; lenMinusOne < n; lenMinusOne++) {
			for (int i = 0; i < n - lenMinusOne; i++) {
				int j = i + lenMinusOne;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int cost = m[i][k] + m[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
					if (cost < m[i][j]) {
						m[i][j] = cost;
						s[i][j] = k;
					}
				}
			}
		}
	}

	public static void exibirParentizacao() {
		boolean[] inAResult = new boolean[s.length];
		printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
	}

	static void printOptimalParenthesizations(int[][] s, int i, int j, /* for pretty printing: */ boolean[] inAResult) {
		if (i != j) {
			printOptimalParenthesizations(s, i, s[i][j], inAResult);
			printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
			String istr = inAResult[i] ? " resultado " : " ";
			String jstr = inAResult[j] ? " resultado" : " ";
			System.out.println(" A_" + i + istr + "* A_" + j + jstr);
			inAResult[i] = true;
			inAResult[j] = true;
		}
	}
}
