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
	ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVO("filecontrol.server.erroAoExecutarComandoTCPGetArquivo"), 
	ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVOS("filecontrol.server.erroAoExecutarComandoTCPGetArquivos"), 
	;
	
	private String chave;

	private Excecao(String chave) {
		this.chave = chave;
	}
	
	public String getChave() {
		return chave;
	}
}
