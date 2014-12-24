package br.com.jmassucatto.filecontrol.server.comando;

public enum ComandoTipo {
	GET_ARQUIVOS,
	GET_ARQUIVO;
	
	public static ComandoTipo getOpcao(String nome) {
		return valueOf(nome);
	}
}
