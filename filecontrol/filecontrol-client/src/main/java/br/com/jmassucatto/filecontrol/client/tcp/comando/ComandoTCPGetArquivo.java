package br.com.jmassucatto.filecontrol.client.tcp.comando;

import java.io.FileOutputStream;
import java.io.IOException;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.comando.ComandoTipo;

public class ComandoTCPGetArquivo extends ComandoTCP {

	String nomeArquivoOrigem;
	FileOutputStream arquivoDestino;
	
	public ComandoTCPGetArquivo() {}

	public ComandoTCPGetArquivo(String nomeArquivoOrigem, FileOutputStream arquivoDestino) {
		this.nomeArquivoOrigem = nomeArquivoOrigem;
		this.arquivoDestino = arquivoDestino;
	}

	//TODO levar método para classe pai e deixar as coisas específicas nas classes filhas
	public void executa() {
		inicializa();
		
		try {
			fazRequisicao();
			obtemArquivo();
			finaliza();
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVO, e);
		}
	}

	@Override
	protected String getRequisicao() {
		return ComandoTipo.GET_ARQUIVO.name() + '\n' + nomeArquivoOrigem + '\n';
	}
	
	private void obtemArquivo() throws IOException {
		FileUtils.copy(getEntrada(), arquivoDestino);
	}

}
