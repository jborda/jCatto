package br.com.jmassucatto.filecontrol.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

public class Server {
	
	public List<String> getNomeArquivos() {
		File[] arquivos = getArquivos();
		
		if (arquivos == null)
			return new ArrayList<String>();;
		
		return getNomesArquivos(arquivos);
	}

	public File getArquivo(String nomeArquivo) {
		File[] arquivos = getArquivos();
		
		for (File arquivo : arquivos)
			if (arquivo.getName().equals(nomeArquivo))
				return arquivo;
		
		throw new FileControlException(Excecao.ARQUIVO_NAO_ENCONTRADO, nomeArquivo);
	}

	private File[] getArquivos() {
		return getDiretorio().listFiles();
	}

	private List<String> getNomesArquivos(File[] arquivos) {
		List<String> nomeArquivos = new ArrayList<String>();
	
		for (File arquivo : arquivos)
			nomeArquivos.add(arquivo.getName());
		
		return nomeArquivos;
	}

	File getDiretorio() {
		String caminho = "/tmp/filecontrol/server";
		return new File(caminho);
	}

}
