package puj.patterns.mundo.Exceptions;

public class DatosErroneosException extends Exception {

	/**
	 * Constructor de la excepci�n lanzada por no tener los datos correctos
	 */
	public DatosErroneosException () {
		super("Hay valores no v�lidos en el juego");
	}
	/**
	 * Constructor de la excepti�n lanzada por tener m�s zombies en el archivo de texto plano de los estipulados por la ronda
	 * @param excedente
	 */
	public DatosErroneosException(int excedente) {
		super("El archivo ha excedido el n�mero de Zombies generados en " + excedente);
	}
}
