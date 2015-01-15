package br.com.jmassucatto.filecontrol.client.tcp.comando;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class ComandoTCPGetArquivoTest {
	
	@Spy private ComandoTCPGetArquivo comando;
	@Mock private Socket conexao;
	@Mock private BufferedReader entrada;
	@Rule public ExpectedException excecao = ExpectedException.none();
	
	@Test
	public void	executaComando() throws Exception {
		ListOutputStream arquivoDestino = new ListOutputStream();
		ComandoTCPGetArquivo comando = spy(new ComandoTCPGetArquivo("um.txt", arquivoDestino));
		doReturn(conexao).when(comando).getConexao();
		
		List<String> linhas = Arrays.asList("linha1\n", "linha2\n", "linha3\n");
		when(entrada.readLine()).thenReturn("linha1\n", "linha2\n", "linha3\n", null);
		doReturn(entrada).when(comando).getEntrada();
		
		doNothing().when(comando).inicializa();
		
		mockSaida(comando);
		
		comando.executa();
		
		assertEquals(linhas, arquivoDestino.conteudo);
	}
	
	@Test
	public void erroAoExecutarLancaExcecaoInterna() throws Exception {
		doNothing().when(comando).inicializa();
		doThrow(IOException.class).when(comando).fazRequisicao();
		
		excecao.expect(FileControlException.class);
		excecao.expectMessage(Excecao.ERRO_AO_EXECUTAR_COMANDO_TCP_GET_ARQUIVO.getChave());
		
		comando.executa();
	}

	@Test
	public void inicializa_casoConexaoEstejaNull() throws Exception {
		doNothing().when(comando).inicializa();
		
		comando.getConexao();
		
		verify(comando).inicializa();
	}

	@Test
	public void naoInicializa_casoSaidaNaoEstejaNull() throws Exception {
		mockSaida(comando);
		
		comando.getSaida();
		
		verify(comando, never()).inicializa();
	}

	private void mockSaida(ComandoTCPGetArquivo comando) throws IOException {
		OutputStream out = mock(PrintStream.class);
		doNothing().when(out).write(anyInt());
		DataOutputStream saida = new DataOutputStream(out);
		doReturn(saida).when(comando).getSaida();
	}

	//TODO mover para classe externa
	class ListOutputStream extends FileOutputStream {
		
		private final List<String> conteudo = new ArrayList<String>();
		
		@Override
		public void write(byte[] b) throws IOException {
			conteudo.add(new String(b));
		}
		
		public ListOutputStream() throws FileNotFoundException {
			this("arquivoFake");
		}

		public ListOutputStream(String name) throws FileNotFoundException {
			super(name);
		}

	}
}
