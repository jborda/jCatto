package br.com.jmassucatto.filecontrol.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
		throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo);
	}
	
	public static void main(String[] args) {
		System.out.println(new Server().getNomeArquivos());
		System.out.println(new Server().getArquivo("aquivo1"));
	}

}
