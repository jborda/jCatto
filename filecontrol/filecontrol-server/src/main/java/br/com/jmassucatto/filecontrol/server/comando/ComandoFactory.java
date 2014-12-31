package br.com.jmassucatto.filecontrol.server.comando;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public class ComandoFactory {

	private ComandoTipo tipo;

	public ComandoFactory(ComandoTipo tipo) {
		System.out.println("ComandoFactory:new:" + tipo);
		if (tipo == null)
			throw new FileControlException(Excecao.COMANDO_INVALIDO);
		
		this.tipo = tipo;
	}

	public Comando getComando() {
		switch (tipo) {
		case GET_ARQUIVOS:
			return new ComandoGetArquivos();
		case GET_ARQUIVO:
			return new ComandoGetArquivo();
		default:
			throw new FileControlException(Excecao.COMANDO_INVALIDO);
		}
	}

}
