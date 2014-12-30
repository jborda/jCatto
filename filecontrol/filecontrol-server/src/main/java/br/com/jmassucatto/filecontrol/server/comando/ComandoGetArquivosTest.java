package br.com.jmassucatto.filecontrol.server.comando;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;

@RunWith(MockitoJUnitRunner.class)
public class ComandoGetArquivosTest {

	@Spy private ComandoGetArquivos comando;
	@Rule public ExpectedException excecao = ExpectedException.none();

	@Test
	public void execucao() throws Exception {
		BufferedReader entrada = mock(BufferedReader.class);
		
		OutputStream out = mock(PrintStream.class);
		doNothing().when(out).write(anyInt());
		DataOutputStream saida = new DataOutputStream(out);
		
		List<String> arquivos = Arrays.asList("um.txt");
		when(comando.getNomesArquivos()).thenReturn(arquivos);
		
		comando.executa(entrada, saida);
		verify(comando).escreveNomesArquivos(saida, arquivos);
	}
	
	@Test
	public void lancaExcecao() throws Exception {
		BufferedReader entrada = mock(BufferedReader.class);
		
		OutputStream out = mock(PrintStream.class);
		doThrow(IOException.class).when(out).write(anyInt());
		DataOutputStream saida = new DataOutputStream(out);
		
		List<String> arquivos = Arrays.asList("um.txt");
		when(comando.getNomesArquivos()).thenReturn(arquivos);
		
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.IO.getChave());
		
		comando.executa(entrada, saida);
	}
}
