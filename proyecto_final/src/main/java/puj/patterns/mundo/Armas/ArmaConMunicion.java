package puj.patterns.mundo.Armas;

public interface ArmaConMunicion {

	/**
	 * obtiene la munici�n del arma en cuesti�n
	 * @return munici�n
	 */
	byte getMunicion();
	/**
	 * cambia la munici�n del arma presente
	 * @param municion
	 */
	void setMunicion(byte municion);
}
