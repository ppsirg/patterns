package puj.patterns.mundo.Personajes;

public abstract class Zombie extends Enemigo {
	/**
	 *
	 * caracteres que representan el estado Caminando del zombie
	 */
	public static final String CAMINANDO = "caminando";
	/**
	 * caracteres que representan el estado Muriendo del zombie
	 */
	public static final String MURIENDO = "muriendo";
	/**
	 * caracteres que representan el estado Muriendo de tiro a la cabeza del zombie
	 */
	public static final String MURIENDO_HEADSHOT = "muriendo";
	/**
	 * caracteres que representan el estado Muriendo incendiado o por granada del zombie
	 */
	public static final String MURIENDO_INCENDIADO = "muriendoIncendiado";
	/**
	 * caracteres que representan el estado Nodo del zombie, �ste no se cambia nunca
	 */
	public static final String NODO = "nodo";

	/**
	 * valor incambiable que representa el ancho de toda imagen zombie
	 */
	public static final int ANCHO_IMAGEN = 150;
	
	/**
	 * zombie que se encuentra al frente o fue generado antes que �ste
	 */
	private Zombie alFrente;
	/**
	 * zombie que se encuentra atr�s o fue generado despu�s que �ste
	 */
	private Zombie atras;

	/**
	 * Constructor de un zombie nodo
	 */
	public Zombie () {
		setEstadoActual(NODO);
	}
	/**
	 * Constructor de un zombie cargado por una partida
	 * @param posY 
	 * @param estadoActual
	 * @param frameActual
	 * @param salud
	 * @param ronda
	 */
	public Zombie (int posY, String estadoActual, byte frameActual, byte salud, int ronda) {
		super(posY, estadoActual, frameActual);
		determinarDificultadZombie(ronda);
		setSalud(salud);
	}

	public static EnemigoContexto getEnemigo(){
		return EnemigoFactoria.getEnemigo("zombie");
	}
	/**
	 * Constructor de un zombie reci�n generado con respecto a la ronda actual
	 * @param nivel
	 * @param atras
	 */
	public Zombie(short nivel, Zombie atras) {
		determinarDificultadZombie(nivel);
		setEstadoActual(CAMINANDO);
		this.atras = atras;
	}

	/**
	 * m�todo auxiliar que determina la dificultad de un zombie cuando se crea o se carga
	 * @param ronda
	 */
	public void determinarDificultadZombie (int ronda) {
		EnemigoContexto enemy = EnemigoFactoria.getEnemigo("zombie");
		switch (ronda) {
			case 9:
				setLentitud(enemy.getLentitud(2));//LENTITUD3
				setSalud(enemy.getSalud(3));//SALUD4
				break;
			case 8:
				setLentitud(enemy.getLentitud(2));//LENTITUD3
				setSalud(enemy.getSalud(3));//SALUD4);
				break;
			case 7:
				setLentitud(enemy.getLentitud(3));//LENTITUD4
				setSalud(enemy.getSalud(1));//(SALUD2);
				break;
			case 6:
				setLentitud(enemy.getLentitud(2));//LENTITUD3
				setSalud(enemy.getSalud(2));//(SALUD3);
				break;
			case 5:
				setLentitud(enemy.getLentitud(2));//LENTITUD3
				setSalud(enemy.getSalud(1));//SALUD2);
				break;
			case 4:
				setLentitud(enemy.getLentitud(2));//LENTITUD3
				setSalud(enemy.getSalud(1));//(SALUD2);
				break;
			case 3:
				setLentitud(enemy.getLentitud(1)); //LENTITUD2
				setSalud(enemy.getSalud(1));//(SALUD2);
				break;
			case 2:
				setLentitud(enemy.getLentitud(0));//LENTITUD1
				setSalud(enemy.getSalud(1));//(SALUD2);
				break;
			case 1:
				setLentitud(enemy.getLentitud(0));//LENTITUD1
				setSalud(enemy.getSalud(0));//(SALUD1);
				break;
			default:
				setLentitud(enemy.getLentitud(0));//LENTITUD1
				setSalud(enemy.getSalud(0));//(SALUD1);
				break;
		}
	}
	/**
	 * obtiene el zombie que fue creado antes que el correspondiente
	 * @return
	 */
	public Zombie getAlFrente() {
		return alFrente;
	}
	/**
	 * Cambia el zombie que se encuentra al frente del correspondiente
	 * @param alFrente
	 */
	public void setAlFrente(Zombie alFrente) {
		this.alFrente = alFrente;
	}

	/**
	 * obtiene el zombie que fue creado despu�s del correspondiente
	 * @return zombie de atr�s
	 */
	public Zombie getAtras() {
		return atras;
	}
	/**
	 * Cambia el zombie que se encuentra al atr�s del correspondiente
	 * @param atras
	 */
	public void setAtras(Zombie atras) {
		this.atras = atras;
	}

	/**
	 * se elimina a s� mismo cambiando las asociaciones de los zombies laterales
	 */
	public void eliminarse () {
		atras.alFrente = alFrente;
		alFrente.atras = atras;
	}
	
	@Override
	public abstract void terminaDeAtacar();
	@Override
	public abstract boolean comprobarDisparo(int x, int y, byte danio);

	@Override
	public abstract String ataco();
	@Override
	public boolean recibeGranada() {
		boolean afectaGranada = false;
		if(!getEstadoActual().equals(MURIENDO) && !getEstadoActual().equals(MURIENDO_INCENDIADO)){
			setEstadoActual(MURIENDO_INCENDIADO);
			afectaGranada = true;
		}
		return afectaGranada;
	}

	/**
	 * entra en la lista enlazada relacionando los par�metros zombie atr�s y al frente
	 * @param zombAlFrente
	 * @param zombAtras
	 */
	public void introducirse(Zombie zombAlFrente, Zombie zombAtras) {
		atras = zombAtras;
		alFrente = zombAlFrente;
		zombAlFrente.atras = this;
		zombAtras.alFrente = this;
	}

}
