package pana;

public class TabelaHash {

	public static void main(String[] args) {
		Tabela tab = new Tabela(3);

		tab.insere(1);
		tab.insere(2);
		tab.insere(3);
		tab.insere(4);
		tab.insere(5);
		tab.insere(6);
		
		tab.imprimir();

	}
}


