package pana;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AnaliseComplexidade {

	private static int c = 0;

	public static void main(String[] args) {
		metodo1(3);
		System.out.println("COMPELXIDADE " + c);
	}

	// n² + 1
	static void metodo1(int n) {
		System.out.println("inicio");
		for (int i = 1; i <= n * n; i++) {
			c++; // n²
			for (int j = 1; j <= i; j++) {
				System.out.println(j); c++; // (n³ + 2) + n²
			} 
		} c++; // -------- 1
		System.out.println("fim");
	}

	static int[] criaVetorCrescente(int n) {
		Random randomGenerator = new Random();
		int[] A = new int[n];
		for (int i = 1; i < n; i++) {
			A[i] = A[i - 1] + randomGenerator.nextInt(10);
		}
		return A;
	}

	static int[] criaVetorDecrescente(int n) {
		Random randomGenerator = new Random();
		int[] A = new int[n];
		A[0] = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			A[i] = A[i - 1] - randomGenerator.nextInt(10);
		}
		return A;
	}

	static int[] criaVetorAleatorio(int n) {
		Random randomGenerator = new Random();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = randomGenerator.nextInt(100 * n);
		}
		return A;
	}

	static void imprimeVetor(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}

	static int[] insertionSort(int[] A) {
		for (int j = 1; j < A.length; j++) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int chave = A[j];
			int i = j - 1;
			while (i >= 0 && A[i] > chave) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				A[i + 1] = A[i];
				i--;
			}
			A[i + 1] = chave;
		}
		return A;
	}

	static int[] mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
		return A;
	}

	static void merge(int[] A, int p, int q, int r) {
		int i, j;
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}
		for (j = 0; j < n2; j++) {
			R[j] = A[q + j + 1];
		}
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		i = 0;
		j = 0;
		for (int k = p; k <= r; k++) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (L[i] <= R[j]) {
				A[k] = L[i++];
			} else {
				A[k] = R[j++];
			}
		}
	}

}
