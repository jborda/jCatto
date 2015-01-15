package br.com.jmassucatto.filecontrol.client.tcp.comando;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComandoTCPTeste {
	
	@Mock private ComandoTCP comando;
	
	@Test
	public void naoInicializa_casoConexaoNaoEstejaNull() throws Exception {
		doNothing().when(comando).inicializa();
		Socket conexao = mock(Socket.class);
		when(comando.getConexao()).thenReturn(conexao);
		
		assertEquals(conexao, comando.getConexao());
		verify(comando, never()).inicializa();
	}

}
