package br.com.jmassucatto.filecontrol.client.tcp;

import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class TCPClientTest {
	
	@Mock private Socket conexao;
	@Spy private TCPClient tcpClient;
	@Mock private OutputStream saida;
	@Mock private InputStream entrada;
	
	@Before
	public void before() throws Exception {
		when(entrada.read()).thenReturn(0);
		when(conexao.getOutputStream()).thenReturn(saida);
		when(conexao.getInputStream()).thenReturn(entrada);
	}

	@Test
	public void getArquivos() throws Exception {
		tcpClient.getArquivos();
	}

}
