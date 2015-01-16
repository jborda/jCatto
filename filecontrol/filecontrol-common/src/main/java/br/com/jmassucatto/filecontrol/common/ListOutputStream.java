package br.com.jmassucatto.filecontrol.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOutputStream  extends FileOutputStream {
	
	private final List<String> conteudo = new ArrayList<String>();
	
	@Override
	public void write(byte[] b) throws IOException {
		getConteudo().add(new String(b));
	}
	
	public ListOutputStream() throws FileNotFoundException {
		this("arquivoFake");
	}

	public ListOutputStream(String name) throws FileNotFoundException {
		super(name);
	}

	public List<String> getConteudo() {
		return conteudo;
	}

}