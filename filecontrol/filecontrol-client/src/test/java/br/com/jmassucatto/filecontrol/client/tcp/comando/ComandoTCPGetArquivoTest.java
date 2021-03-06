package br.com.jmassucatto.filecontrol.client.tcp.comando;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jmassucatto.filecontrol.common.DataOutputStreamMockBuilder;
import br.com.jmassucatto.filecontrol.common.Excecao;
import br.com.jmassucatto.filecontrol.common.FileControlException;
import br.com.jmassucatto.filecontrol.common.ListOutputStream;

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
		
		DataOutputStreamMockBuilder mockBuilder = new DataOutputStreamMockBuilder();
		doReturn(mockBuilder.getMock()).when(comando).getSaida();
		
		comando.executa();
		
		assertEquals(linhas, arquivoDestino.getConteudo());
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
		DataOutputStreamMockBuilder mockBuilder = new DataOutputStreamMockBuilder();
		doReturn(mockBuilder.getMock()).when(comando).getSaida();
		
		comando.getSaida();
		
		verify(comando, never()).inicializa();
	}

}
