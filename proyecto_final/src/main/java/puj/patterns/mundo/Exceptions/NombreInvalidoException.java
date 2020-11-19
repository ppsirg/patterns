package puj.patterns.mundo.Exceptions;

public class NombreInvalidoException extends Exception{

	/**
	 * Constructor de la excepci�n lanzada por escribir caracteres no alfab�ticos
	 * @param caracter
	 */
	public NombreInvalidoException (char caracter) {
		super("El nombre no puede contener n�meros ni s�mbolos \n" + "Caracter inv�lido: " + caracter);
	}
}
