package puj.patterns.mundo.Personajes;

public interface SerViviente {
	/**
	 * todo ser viviente en el juego realiza un ataque caracter�stico
	 * @return
	 */
	String ataco();
	/**
	 * cambia la salud del ser viviente
	 * @param nuevaSalud
	 */
	void setSalud(byte nuevaSalud);
	/**
	 * todo ser viviente contiene salud, representado por un valor num�rico
	 * @return
	 */
	byte getSalud();
}
