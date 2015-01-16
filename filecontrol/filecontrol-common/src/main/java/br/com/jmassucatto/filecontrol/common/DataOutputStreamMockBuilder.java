package br.com.jmassucatto.filecontrol.common;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class DataOutputStreamMockBuilder {
	
	private DataOutputStream mock;
	private OutputStream out;

	public DataOutputStreamMockBuilder() throws IOException {
		inicializaPadrao();
	}

	private void inicializaPadrao() throws IOException {
		out = mock(PrintStream.class);
		mock = new DataOutputStream(out);
		doNothing().when(out).write(anyInt());
	}

	public DataOutputStream getMock() {
		return mock;
	}

}
