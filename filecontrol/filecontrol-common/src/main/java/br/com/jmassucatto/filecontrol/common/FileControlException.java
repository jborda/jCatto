package br.com.jmassucatto.filecontrol.common;

public class FileControlException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FileControlException(Excecao excecao) {
		super(excecao.getChave());
	}
	
	public FileControlException(Excecao excecao, Throwable causa) {
		super(excecao.getChave(), causa);
		
		causa.printStackTrace();
	}

}
