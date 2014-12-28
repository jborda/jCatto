package br.com.jmassucatto.filecontrol.server.comando;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public interface Comando {

	void executa(BufferedReader entrada, DataOutputStream saida);

}
