package mundo.Armas;

import java.io.Serializable;

public abstract class Arma implements Serializable {

	/**
	 * cadena de caracteres que representa el estado del arma cargandose
	 */
	public static final String CARGANDO = "carga";
	/**
	 * cadena de caracteres que representa el estado del arma lista
	 */
	public static final String LISTA = "ready";

	/**
	 * valor num�rico entero que muestra el tiempo de carga en milisegundos
	 */
	private short tiempoCarga;
	/**
	 * valor num�rico entero que representa el da�o causado por un arma
	 */
	private byte danio;
	/**
	 * cadena de caracteres que representa el estado del arma
	 */
	private String estado;

	/**
	 * Constructor abstracto de un arma que pone el estado del arma Lista
	 */
	public Arma() {
		estado = LISTA;
	}

	/**
	 * obtiene el tiempo que tarda en cargar el arma
	 * @return tiempoCarga
	 */
	public short getTiempoCarga() {
		return tiempoCarga;
	}
	/**
	 * cambia el tiempo que tarda en cargar el arma
	 * @param tiempoCarga
	 */
	protected void setTiempoCarga(short tiempoCarga) {
		this.tiempoCarga = tiempoCarga;
	}

	/**
	 * cambia el da�o que causa el arma
	 * @param danio
	 */
	protected void setDanio(byte danio) {
		this.danio = danio;
	}

	/**
	 * obtiene el da�o que causa el arma
	 * @return danio
	 */
	public byte getDanio() {
		return danio;
	}

	/**
	 * m�todo que calcula el tiempo de espera en el hilo del arma con respecto al estado
	 * @return tiempo de sleep en milisegundos
	 */
	public abstract long calcularDescanso();

	/**
	 * obtiene el estado del arma presente
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * cambia el estado del arma en cuesti�n
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
