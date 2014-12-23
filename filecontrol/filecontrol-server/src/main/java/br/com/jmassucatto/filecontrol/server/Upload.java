package br.com.jmassucatto.filecontrol.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

public class Upload {
	
	public static void main(String[] args) throws Exception {
		JFileChooser chooser = new JFileChooser();
		int retorno = chooser.showSaveDialog(null);
		if(retorno == JFileChooser.APPROVE_OPTION) {
			File arquivoOrigem = chooser.getSelectedFile();
			FileInputStream origem = new FileInputStream(arquivoOrigem);
			String arquivoDestino = "/tmp/filecontrol/client/" + arquivoOrigem.getName();
			FileOutputStream destino = new FileOutputStream(arquivoDestino );
			
			byte[] buffer = new byte[1024];
			int tamanho;
			while((tamanho = origem.read(buffer)) > 0)
				destino.write(buffer, 0, tamanho);
			
			origem.close();
			destino.close();
		}
			
	}
}

