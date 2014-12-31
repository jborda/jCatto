package br.com.jmassucatto.filecontrol.server.comando;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.common.FileUtils;
import br.com.jmassucatto.filecontrol.server.Server;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({FileUtils.class})
public class ComandoGetArquivoTest {

	@Spy private ComandoGetArquivo comando;
	@Mock private Server server;
	@Mock private File arquivo;
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Before
	public void setup() {
		when(server.getArquivo(anyString())).thenReturn(arquivo);
		comando.server = server;
	}
	
	@Test
	public void execucao() throws Exception {
		BufferedReader entrada = mock(BufferedReader.class);
		when(entrada.readLine()).thenReturn("um.txt");
		
		OutputStream out = mock(PrintStream.class);
		DataOutputStream saida = new DataOutputStream(out);
		
		doNothing().when(comando).getArquivo(arquivo, saida);
		
		comando.executa(entrada, saida);
		verify(comando).getArquivo(arquivo, saida);
	}
	
	@Test
	public void lancaExcecao() throws Exception {
		BufferedReader entrada = mock(BufferedReader.class);
		when(entrada.readLine()).thenReturn("um.txt");
		
		OutputStream out = mock(PrintStream.class);
		DataOutputStream saida = new DataOutputStream(out);
		
		doThrow(IOException.class).when(comando).getArquivo(arquivo, saida);

		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.IO.getChave());
		
		comando.executa(entrada, saida);
		verify(comando).getArquivo(arquivo, saida);
	}

}
