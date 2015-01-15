package br.com.jmassucatto.filecontrol.client.tcp.comando;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

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

@RunWith(MockitoJUnitRunner.class)
public class ComandoTCPGetArquivosTest {
	
	@Spy private ComandoTCPGetArquivos comando;
	@Mock private BufferedReader entrada;
	@Mock private Socket conexao;
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Before
	public void setup() throws Exception {
		doReturn(entrada).when(comando).getEntrada();
		
		doNothing().when(conexao).close();
	}
	
	@Test
	public void	executaComando() throws Exception {
		doReturn(conexao).when(comando).getConexao();
		
		List<String> arquivos = Arrays.asList("um.txt");
		when(entrada.readLine()).thenReturn(arquivos.toString());
		
		OutputStream out = mock(PrintStream.class);
		doNothing().when(out).write(anyInt());
		DataOutputStream saida = new DataOutputStream(out);
		doReturn(saida).when(comando).getSaida();
		
		doNothing().when(comando).inicializa();
		
		comando.executa();
		
		assertEquals(arquivos, comando.getArquivos());
	}
	
	@Test
	public void inicializa_casoConexaoEstejaNull() throws Exception {
		doNothing().when(comando).inicializa();
		
		comando.getConexao();
		
		verify(comando).inicializa();
	}
	
	@Test
	public void naoInicializa_casoEntradaNaoEstejaNull() throws Exception {
		doNothing().when(comando).inicializa();
		
		assertEquals(entrada, comando.getEntrada());
		verify(comando, never()).inicializa();
	}
	
	@Test
	public void erroAoExecutarLancaExcecaoInterna() throws Exception {
		doNothing().when(comando).inicializa();
		doThrow(IOException.class).when(comando).fazRequisicao();
		
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVOS.getChave());
		
		comando.executa();
	}
	
}
