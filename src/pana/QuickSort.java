package pana;

import java.util.Random;

public class QuickSort {

	static class Crianca {
		public String nome;
		public int idade;
		public char sexo;
		public String range;
		
		@Override
		public String toString() {
			return "Nome: " + nome + "Idade: " + idade + " Sexo: " + sexo + " Range:" + range;
		}

		public Crianca() {
			Random randomGenerator = new Random();
			nome = "";
			nome += (char) ('A' + randomGenerator.nextInt(26));
			for (int i = 2; i <= 10; i++) {
				nome += (char) ('a' + randomGenerator.nextInt(26));
			}
			idade = 6 + randomGenerator.nextInt(11);
			sexo = (randomGenerator.nextInt(2) == 0) ? 'M' : 'F';
			range = isRange6a10(idade) ? "de 6 a 10" : isRange11a14(idade) ? "de 11 a 14" : isRange15a16(idade) ? "de 15 a 16" : "";
		}
	}
	
	static void organizaSexo (Crianca[] A) {
		sortPorSexoVetorCriancas(A, 0, A.length - 1);
		printArray(A);
	}

	static void organizaIdadeLocal (Crianca[] A) {
		sort(A, 0, A.length - 1);
		printArray(A);
	}

	static void organizaIdadeNaoLocal (Crianca[] A) {
		//to do
		Crianca[] B = A.clone();
	}

	
	static int partirIdades(Crianca criancas[], int menor, int maior) {
		int pivor = criancas[maior].idade;
		int i = (menor - 1);
		for (int j = menor; j < maior; j++) {
			if (criancas[j].idade < pivor) {
				i++;

				Crianca temp = criancas[i];
				criancas[i] = criancas[j];
				criancas[j] = temp;
			}
		}
		Crianca crianca = criancas[i + 1];
		criancas[i + 1] = criancas[maior];
		criancas[maior] = crianca;

		return i + 1;
	}

	static void sort(Crianca crianca[], int menor, int maior) {
		if (menor < maior) {
			int pivor = partirIdades(crianca, menor, maior);
			sort(crianca, menor, pivor - 1);
			sort(crianca, pivor + 1, maior);
		}
	}

	static void printArray(Crianca arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " \n ");
		System.out.println();
	}

	// Driver program
	public static void main(String args[]) {
		int n = 10;
		Crianca[] criancas = new Crianca[n];
		for (int i = 0; i < criancas.length; i++) {
			criancas[i] = new Crianca();
		}
		

		organizaIdadeLocal(criancas);
		System.out.println("sorted array");
		printArray(criancas);
	}
	
	private static Crianca[] sortPorSexoVetorCriancas(Crianca[] crianca, int p, int r) {
		if (p < r) {
			int q = organizarPorSexo(crianca, p, r);
			sortPorSexoVetorCriancas(crianca, p, q);
			sortPorSexoVetorCriancas(crianca, q + 1, r);
		}

		return crianca;
	}

	private static int organizarPorSexo(Crianca[] crianca, int p, int r) {
		Crianca x = crianca[p];
		int i = p - 1;
		int j = r + 1;

		while (true) {
			i++;
			while (i < r && x.sexo == 'M' & crianca[i].sexo == 'F')
				i++;
			j--;
			while (j > p && crianca[j].sexo == 'M' & x.sexo == 'F')
				j--;

			if (i < j)
				swap(crianca, i, j);
			else
				return j;
		}
	}
	
	private static boolean isRange6a10(int a) {
		return a <= 10 & a >= 6;
	}

	private static boolean isRange11a14(int a) {
		return a <= 14 & a >= 11;
	}
	
	private static boolean isRange15a16(int a) {
		return a <= 16 & a >= 15;
	}
	
	private static void swap(Crianca[] crianca, int i, int j) {
		Crianca temp = crianca[i];
		crianca[i] = crianca[j];
		crianca[j] = temp;
	}
}

