package pana;

import java.util.Random;

public class AV3 {

	public static void main(String[] args) {
		int n = 10;
		Crianca[] criancas = new Crianca[n];
		for (int i = 0; i < criancas.length; i++) {
			criancas[i] = new Crianca();
		}
		
		Integer[] a = organizarPorIdadeNLocal(criancas);
		System.out.println("sorted array");
		print(a);
	}
	
	public static void print(Integer A[]) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + ",");
		}
	}
	
	static class Crianca {
		public String nome;
		public int idade;
		public char sexo;

		@Override
		public String toString() {
			return "Nome: " + nome + "Idade: " + idade + " Sexo: " + sexo;
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
		}
	}

	public static Integer[] organizarPorIdadeNLocal(Crianca[] v) {
		int maior = v[0].idade;
		for (int i = 1; i < v.length; i++) {
			if (v[i].idade > maior) {
				maior = v[i].idade;
			}
		}

		int[] c = new int[maior + 1];

		for (int i = 0; i < v.length; i++) {
			c[v[i].idade] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i - 1];
		}

		Integer[] b = new Integer[v.length];
		for (int i = b.length - 1; i >= 0; i--) {
			b[c[v[i].idade] - 1] = v[i].idade;
			c[v[i].idade]--;
		}

		return b;
	}


}
