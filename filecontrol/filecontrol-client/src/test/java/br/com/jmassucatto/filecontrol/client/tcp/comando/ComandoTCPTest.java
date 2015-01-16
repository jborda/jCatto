package br.com.jmassucatto.filecontrol.client.tcp.comando;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComandoTCPTest {
	
	@Mock private ComandoTCP comando;
	@Mock private Socket conexao;
	
	@Before
	public void setup() throws Exception {
		doNothing().when(comando).inicializa();
		when(comando.getConexao()).thenReturn(conexao);
	}
	
	@Test
	public void naoInicializa_casoConexaoNaoEstejaNull() throws Exception {
		assertEquals(conexao, comando.getConexao());
		verify(comando, never()).inicializa();
	}
	
	@Test
	public void naoInicializa_casoConexaoNaoEstejaNullENaoClosed() throws Exception {
		when(conexao.isClosed()).thenReturn(false);
		
		assertEquals(conexao, comando.getConexao());
		verify(comando, never()).inicializa();
	}
	
	@Test
	public void inicializa_casoConexaoNaoEstejaClosed() throws Exception {
		when(conexao.isClosed()).thenReturn(false);
		
		assertEquals(conexao, comando.getConexao());
		verify(comando, never()).inicializa();
	}

}
