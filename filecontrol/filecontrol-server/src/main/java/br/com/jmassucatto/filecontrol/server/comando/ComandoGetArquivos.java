package br.com.jmassucatto.filecontrol.server.comando;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.server.Server;

public class ComandoGetArquivos implements Comando {

	public void executa(BufferedReader entrada, DataOutputStream saida) {
		System.out.println("ComandoGetArquivos:executa");
		List<String> arquivos = new Server().getNomeArquivos();
		try {
			saida.writeBytes(arquivos.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileControlException(Excecao.IO);
		}
	}

}
