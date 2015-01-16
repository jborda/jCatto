package br.com.jmassucatto.filecontrol.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {
	
	public static void copy(FileInputStream origem, OutputStream destino) throws IOException {
		byte[] buffer = new byte[1024];
		int tamanho;
		
		while((tamanho = origem.read(buffer)) > 0)
			destino.write(buffer, 0, tamanho);
		
		origem.close();
		destino.close();
	}

	public static void copy(BufferedReader origem, FileOutputStream destino) throws IOException {
		String linha;
		
		while ((linha = origem.readLine()) != null)
			destino.write(linha.getBytes());
		
		destino.flush();
		destino.close();
	}
}
