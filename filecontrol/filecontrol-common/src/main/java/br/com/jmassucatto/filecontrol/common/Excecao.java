package br.com.jmassucatto.filecontrol.common;

public enum Excecao {
	
	ARQUIVO_NAO_ENCONTRADO("filecontrol.server.arquivoNaoEncontrado"), 
	COMANDO_INVALIDO("filecontrol.server.comandoInvalido"), 
	ERRO_AO_PARAR_SERVIDOR("filecontrol.server.erroAoPararServidor"),
	ERRO_AO_SUBIR_SERVIDOR("filecontrol.server.erroAoSubirServidor"),
	IO("filecontrol.server.io"), 
	NAO_DEFINIDO("filecontrol.server.naoDefinido"), 
	ERRO_AO_EXECUTAR_REQUISICAO("filecontrol.server.erroAoExucutarRequisicao"), 
	ERRO_AO_INICIAR_CLIENTE("filecontrol.server.erroAoIniciarCliente"), 
	;
	
	private String chave;

	private Excecao(String chave) {
		this.chave = chave;
	}
	
	public String getChave() {
		return chave;
	}
}
