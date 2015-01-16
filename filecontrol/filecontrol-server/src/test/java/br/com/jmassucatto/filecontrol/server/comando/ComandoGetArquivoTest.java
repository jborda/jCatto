package br.com.jmassucatto.filecontrol.server.comando;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.server.Server;

@RunWith(MockitoJUnitRunner.class)
public class ComandoGetArquivoTest {

	@Spy private ComandoGetArquivo comando;
	
	@Mock private Server server;
	@Mock private File arquivo;
	@Mock private BufferedReader entrada;
	@Mock private OutputStream out;

	@Rule public ExpectedException excecao = ExpectedException.none();

	private DataOutputStream saida;
	
	@Before
	public void setup() throws Exception {
		when(server.getArquivo(anyString())).thenReturn(arquivo);
		when(entrada.readLine()).thenReturn("um.txt");
		saida = new DataOutputStream(out);
		comando.server = server;
	}
	
	@Test
	public void execucao() throws Exception {
		doNothing().when(comando).getArquivo(arquivo, saida);
		
		comando.executa(entrada, saida);
		verify(comando).getArquivo(arquivo, saida);
	}
	
	@Test
	public void lancaExcecao() throws Exception {
		doThrow(IOException.class).when(entrada).readLine();

		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.IO.getChave());
		
		comando.executa(entrada, saida);
	}

}
