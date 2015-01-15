package br.com.jmassucatto.filecontrol.client;

import static org.mockito.Mockito.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.jmassucatto.filecontrol.client.tcp.TCPClient;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
	
	private static final String ARQUIVO_UM = "um.txt";
	
	@Spy private Client client;
	@Mock private TCPClient tcpClient;
	
	@Before
	public void setup() {
		client.tcpClient = tcpClient;
	}
	
	@Test
	public void sincroniza() throws Exception {
		when(tcpClient.getArquivos()).thenReturn(Arrays.asList(ARQUIVO_UM));
		FileOutputStream destino = mock(FileOutputStream.class);
		doReturn(destino).when(client).getDestino(ARQUIVO_UM);
		
		doNothing().when(tcpClient).buscaArquivo(ARQUIVO_UM, destino);
		
		client.sincroniza();
		verify(tcpClient).buscaArquivo(ARQUIVO_UM, destino);
	}
	
	@Test
	public void naoSincronizaSemArquivos() throws Exception {
		when(tcpClient.getArquivos()).thenReturn(new ArrayList<String>());
		FileOutputStream destino = mock(FileOutputStream.class);
		
		client.sincroniza();
		verify(tcpClient, never()).buscaArquivo(ARQUIVO_UM, destino);
	}

}
