package br.com.jmassucatto.filecontrol.server.comando;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.Server;

public class ComandoGetArquivo implements Comando {
	
	Server server;

	ComandoGetArquivo() {
		server = new Server();
	}

	public void executa(BufferedReader entrada, DataOutputStream saida) {
		System.out.println("ComandoGetArquivo:executa");
		try {
			String nomeArquivo = entrada.readLine();
			File arquivo = server.getArquivo(nomeArquivo);
			getArquivo(arquivo, saida);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileControlException(Excecao.IO, e);
		}
	}

	void getArquivo(File arquivo, DataOutputStream saida) throws IOException {
		FileUtils.copy(arquivo, saida);
	}

}
