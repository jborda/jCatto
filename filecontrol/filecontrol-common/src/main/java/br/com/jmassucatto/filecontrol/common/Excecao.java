package br.com.jmassucatto.filecontrol.common;

public enum Excecao {
	COMANDO_INVALIDO("filecontrol.server.comandoInvalido");
	
	private String chave;

	private Excecao(String chave) {
		this.chave = chave;
	}
	
	public String getChave() {
		return chave;
	}
}
