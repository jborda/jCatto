package br.com.jmassucatto.filecontrol.common;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {
	
	@Mock private BufferedReader bufferedReader;
	@Spy private ListOutputStream fileOutputStream;

	@Test
	public void copy() throws Exception {
		List<String> linhas = Arrays.asList("linha1\n", "linha2\n", "linha3\n");
		when(bufferedReader.readLine()).thenReturn("linha1\n", "linha2\n", "linha3\n", null);
	
		FileUtils.copy(bufferedReader, fileOutputStream);
		
		assertEquals(linhas, fileOutputStream.getConteudo());
	}
	
}
