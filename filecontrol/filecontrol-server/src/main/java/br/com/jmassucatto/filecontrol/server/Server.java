package br.com.jmassucatto.filecontrol.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public class Server {
	
	public List<String> getNomeArquivos() {
		List<String> nomeArquivos = new ArrayList<String>(); 
		for (File arquivo : getArquivos())
			nomeArquivos.add(arquivo.getName());
		
		return nomeArquivos;
	}

	private File[] getArquivos() {
		String caminho = "/tmp/filecontrol/server";
		File diretorio = new File(caminho);
		
		return diretorio.listFiles();
	}
	
	public File getArquivo(String nomeArquivo) {
		File[] arquivos = getArquivos();
		for (File arquivo : arquivos)
			if (arquivo.getName().equals(nomeArquivo))
				return arquivo;
		
		throw new FileControlException(Excecao.ARQUIVO_NAO_ENCONTRADO, nomeArquivo);
	}

}
