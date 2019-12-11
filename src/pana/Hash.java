package pana;

class No {
	public int item;
	public No prox; // proximo no da lista

	public No(int valor) {
		item = valor;
	} // construtor

	public void mostra() {
		System.out.print(item + " -> ");
	}
}

class Lista {
	private No primeiro; 

	public Lista() {
		primeiro = null;
	} // Construtor

	public boolean empty() {
		return (primeiro == null);
	}

	public void insere_lista(int v) {
		No lista = new No(v); // Alocando memoria para o No
		if (empty()) { // Se a lista estiver vazia
			lista.prox = null;
			primeiro = lista;
			return;
		}
		No atual = primeiro;
		while (atual.prox != null) // caminhando para o ultimo Nó da lista
			atual = atual.prox;
		atual.prox = lista;
		lista.prox = null;
	}

	public void imprime_lista() {
		No atual = primeiro;
		while (atual != null) { // caminhando para o fim da lista
			atual.mostra();
			atual = atual.prox;
		}
	}

	public boolean buscaNaLista(double chave) {
		No atual = primeiro;
		while (atual != null) { // caminhando para o fim da lista
			if (atual.item == chave)
				return true;
			atual = atual.prox;
		}
		return false;
	}

	public void apaga_lista(double valor) { 
		if (!buscaNaLista(valor)) {
			System.out.println(" item nao foi encontrado ***");
			return;
		}
		No atual, anterior;
		atual = anterior = primeiro;
		while (atual != null) { // caminhando para o fim da lista
			if (atual.item == valor)
				break;
			anterior = atual;
			atual = atual.prox;
		}
		if (atual == primeiro)
			primeiro = primeiro.prox;
		else
			anterior.prox = atual.prox;
	}

}

class Tabela {

	private Lista[] tab;
	private int TAM_MAX;

	public Tabela(int tam) {
		tab = new Lista[tam];
		TAM_MAX = tam;
		for (int i = 0; i < tam; i++) // inicializando
			tab[i] = null;
	}

	private int funcaohash(int chave) {
		int v = (int) chave;
		return (Math.abs(v) % TAM_MAX);
	}

	public void insere(int item) {
		int pos = funcaohash(item);
		if (tab[pos] != null) { // se esta ocupado
			if (tab[pos].buscaNaLista(item)) { // verificando se a chave ja existe
				System.out.println(" *** ATENCAO O item " + item + " ja foi cadastrado ***");
				return;
			}
		} else // se estiver livre
			tab[pos] = new Lista();

		tab[pos].insere_lista(item);
	}

	public void apaga(int chave) {
		int pos = buscarNaTabela(chave);
		if (pos != -1) {
			tab[pos].apaga_lista(chave);
		} else
			System.out.println("\n Item nao encontrado");
	}

	public void imprimir() {
		for (int i = 0; i < TAM_MAX; i++) {
			System.out.print("\n Tabela[" + i + "] -> ");
			if (tab[i] != null)
				tab[i].imprime_lista();
			System.out.print("null");
		}
	}

	public int buscarNaTabela(int chave) {
		for (int i = 0; i < TAM_MAX; i++)
			if (tab[i] != null)
				if (tab[i].buscaNaLista(chave))
					return i;
		return -1;
	}

}
