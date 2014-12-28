package br.com.jmassucatto.filecontrol.common;

public enum Excecao {
	
	COMANDO_INVALIDO("filecontrol.server.comandoInvalido"), 
	IO("filecontrol.server.io"), 
	ARQUIVO_NAO_ENCONTRADO("filecontrol.server.arquivoNaoEncontrado"), 
	NAO_DEFINIDO("filecontrol.server.naoDefinido");
	
	private String chave;

	private Excecao(String chave) {
		this.chave = chave;
	}
	
	public String getChave() {
		return chave;
	}
}
