package br.com.jmassucatto.filecontrol.client.tcp.comando;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.server.comando.ComandoTipo;

public class ComandoTCPGetArquivos extends ComandoTCP {

	private List<String> arquivos = new ArrayList<String>();

	//TODO levar método para classe pai e deixar as coisas específicas nas classes filhas
	public void executa() {
		inicializa();

		try {
			fazRequisicao();
			obtemListaArquivos();
			finaliza();
		} catch (IOException e) {
			throw new FileControlException(Excecao.ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVOS, e);
		}
	}

	public List<String> getArquivos() {
		return arquivos;
	}
	
	@Override
	protected String getRequisicao() {
		return ComandoTipo.GET_ARQUIVOS.name();
	}

	private void obtemListaArquivos() throws IOException {
		String retorno = getEntrada().readLine();
		convertParaList(retorno);
	}

	private void convertParaList(String retorno) {
		retorno = retorno.substring(1, retorno.length() - 1);
		String[] arrayNomes = retorno.split(", ");
		arquivos = Arrays.asList(arrayNomes);
	}

}
