package puj.patterns.mundo.Personajes;

public interface SeMueveEnZigzag {

	/**
	 * hace que var�e la velocidad en Y con respecto a la de X
	 */
	void moverEnDireccion();
	/**
	 * obtiene la direccion o velocidad en pixeles en el eje X
	 * @return direccionX
	 */
	int getDireccionX();
	/**
	 * obtiene la direcci�n o velocidad en el eje Y
	 * @return direccionY
	 */
	int getDireccionY();
}
