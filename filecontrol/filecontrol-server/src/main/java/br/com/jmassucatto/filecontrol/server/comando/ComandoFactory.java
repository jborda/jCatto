package br.com.jmassucatto.filecontrol.server.comando;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;


public class ComandoFactory {

	private ComandoTipo opcao;

	public ComandoFactory(ComandoTipo opcao) {
		if (opcao == null)
			throw new FileControlException(Excecao.COMANDO_INVALIDO);
		
		this.opcao = opcao;
	}

	public Comando getComando() {
		switch (opcao) {
		case GET_ARQUIVOS:
			return new ComandoGetArquivos();
		case GET_ARQUIVO:
			return new ComandoGetArquivo();
		default:
			return null;
		}
	}

}
