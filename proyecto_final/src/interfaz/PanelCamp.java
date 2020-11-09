package interfaz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Formatter;

//@SuppressWarnings("serial")
import javax.swing.*;

import mundo.*;
import mundo.Armas.Arma;
import mundo.Armas.ArmaDeFuego;
import mundo.Personajes.Boss;
import mundo.Personajes.Personaje;
import mundo.Personajes.Zombie;

public class PanelCamp extends JPanel implements MouseListener, KeyListener {

	private JLabel labPuntaje;
	private JLabel labBajas;
	private JLabel labRonda;
	private JLabel labTirosALaCabeza;
	private JProgressBar labVidas;
	private JLabel labGranadas;
	private JLabel labBalas;
	private JPanel mostrador;
	private InterfazZombieKiller principal;
	private Zombie chombiMasLejano;
	private Personaje matador;
	private Point ultimoDisparo;
	private ImageIcon[] imagenesCargadas;
	private ArmaDeFuego armaEquipada;
	private Boss chief;

	public PanelCamp(InterfazZombieKiller inter) {
		setLayout(new BorderLayout());
		Font tipo = new Font("Chiller", Font.PLAIN, 34);

		ultimoDisparo = new Point();
		setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		mostrador = new JPanel();
		mostrador.setBackground(Color.BLACK);
		mostrador.setLayout(new GridLayout(3, 1));

		labBajas = new JLabel();
		labBajas.setFont(tipo);
		labBajas.setForeground(Color.white);

		labRonda = new JLabel();
		labRonda.setFont(tipo);
		labRonda.setForeground(Color.white);

		labTirosALaCabeza = new JLabel();
		labTirosALaCabeza.setFont(tipo);
		labTirosALaCabeza.setForeground(Color.white);

		labPuntaje = new JLabel();
		labPuntaje.setFont(tipo);
		labPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		labPuntaje.setForeground(Color.white);

		labVidas = new JProgressBar();
		labVidas.setFont(tipo);
		labVidas.setMaximum(Personaje.SALUD);
		labVidas.setForeground(Color.green);

		labGranadas = new JLabel();
		labGranadas.setIcon(new ImageIcon(getClass().getResource("/img/Fondo/Granada.png")));
		labGranadas.setFont(tipo);
		labGranadas.setVerticalAlignment(SwingConstants.TOP);
		labGranadas.setForeground(Color.white);

		labBalas = new JLabel();
		labBalas.setFont(tipo);
		labBalas.setVerticalAlignment(SwingConstants.TOP);
		labBalas.setForeground(Color.white);

		principal = inter;

		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);

		mostrador.add(labBajas);
		mostrador.add(labRonda);
		mostrador.add(labTirosALaCabeza);
		mostrador.setVisible(false);

		add(labPuntaje, BorderLayout.NORTH);
		add(labVidas, BorderLayout.SOUTH);
		add(labBalas, BorderLayout.EAST);
		add(labGranadas, BorderLayout.WEST);
		add(mostrador, BorderLayout.CENTER);
	}

	public void actualizarChombis(Zombie chombi) {
		chombiMasLejano = chombi;
	}

	public void actualizarEquipada(ArmaDeFuego armaEquipada) {
		this.armaEquipada = armaEquipada;
		String armaActual = armaEquipada.getClass().getSimpleName();
		labBalas.setIcon(new ImageIcon(getClass().getResource("/img/Fondo/" + armaActual + ".png")));
		labBalas.setText("" + armaEquipada.getMunicion());
	}

	public void actualizarMatador(Personaje personaje) {
		matador = personaje;
		labVidas.setValue(matador.getSalud());
		labPuntaje.setText("Puntaje: " + matador.getScore());
		labBajas.setText("Bajas: " + matador.getMatanza());
		labGranadas.setText(matador.getGranadas().getMunicion() + "");
	}

	public void actualizarRonda() {
		labRonda.setText("Ronda: " + principal.darRondaActual());
	}

	public void incorporarJefe(Boss aMatar) {
		chief = aMatar;
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);

		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		Image fondo = Toolkit.getDefaultToolkit()
				.getImage(this.getClass().getResource("/img/Fondo/escenario-fondo-azul.png"));
		if (screenWidth>1024){
			fondo = fondo.getScaledInstance(screenWidth,720,Image.SCALE_SMOOTH);
		}
		// wait for image to be ready
		MediaTracker tracker = new MediaTracker(new java.awt.Container());
		tracker.addImage(fondo, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException ex) {
			throw new RuntimeException("Image loading interrupted", ex);
		}
		arg0.drawImage(fondo, 0, 0, null);

		// System.out.println(chombis.size());
		if (getDebugGraphicsOptions() == DebugGraphics.BUFFERED_OPTION) {
			requestFocusInWindow();
			cargarImagenes();
			setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		}
		Zombie aPintar = chombiMasLejano.getAlFrente();
		if (aPintar != null)
			while (!aPintar.getEstadoActual().equals(Zombie.NODO)) {
				try {
					int posX = aPintar.getPosX();
					int posY = aPintar.getPosY();

					Image imgZombie = Toolkit.getDefaultToolkit()
							.getImage(this.getClass().getResource(aPintar.getURL()));
					arg0.drawImage(imgZombie, posX, posY, null);
					aPintar = aPintar.getAlFrente();
				} catch (Exception e) {
					principal.pausarJuego();
					e.printStackTrace();
				}
				// System.out.println(chombis.size());
			}
		if (chief != null) {
			Image chiefAPintar = Toolkit.getDefaultToolkit().getImage(getClass().getResource(chief.getURL()));
			arg0.drawImage(chiefAPintar, chief.getPosX(), chief.getPosY(), null);
		}

		int ataqueX = (int) ultimoDisparo.getX();
		int ataqueY = (int) ultimoDisparo.getY();
		if (armaEquipada.isEnsangrentada()) {
			fondo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/img/Fondo/sangre.png"));
			arg0.drawImage(fondo, ataqueX - 33, ataqueY - 35, null);
		}
		if (matador.getCuchillo().getEstado().equals(Arma.CARGANDO)) {
			fondo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Fondo/pu�alada.png"));
			arg0.drawImage(fondo, ataqueX - 160, ataqueY - 30, null);
		} else if (armaEquipada.getEstado().equals(ArmaDeFuego.RECARGANDO)) {
			fondo = Toolkit.getDefaultToolkit().getImage(this.getClass()
					.getResource("/img/Fondo/disparo" + armaEquipada.getClass().getSimpleName() + ".png"));
			arg0.drawImage(fondo, ataqueX - 33, ataqueY - 35, null);
		} else if (matador.getGranadas().getEstado().equals(Arma.CARGANDO)) {
			fondo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Fondo/explosion.png"));
			arg0.drawImage(fondo, 250, 133, null);
		}
		if (matador.isEnsangrentado()) {
			fondo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/Fondo/boss_ataca.png"));
			arg0.drawImage(fondo, 0, 0, null);
		}
		if (principal.getEstadoPartida() == SurvivorCamp.INICIANDO_RONDA) {
			fondo = Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("/img/Palabras/ronda" + principal.darRondaActual() + ".png"));
			arg0.drawImage(fondo, ((screenWidth/2)-(fondo.getWidth(null)/2)), 300, null);
		}
	}

	private void cargarImagenes() {
		imagenesCargadas = new ImageIcon[205];
		ImageIcon actual;
		int contador = 0;
		// 31 es la mayor cantidad de imagenes de una animaci�n
		Formatter formato;
		for (int i = 0; i <= 31; i++) {
			// System.out.println("/img/Caminante/caminando/" +
			// formato.format("%02d", i) + ".png");
			formato = new Formatter();
			actual = new ImageIcon(
					getClass().getResource("/img/Rastrero/caminando/" + formato.format("%02d", i) + ".png"));
			imagenesCargadas[contador] = actual;
			contador++;
			if (i <= 24) {
				formato = new Formatter();
				actual = new ImageIcon(
						getClass().getResource("/img/Caminante/caminando/" + formato.format("%02d", i) + ".png"));
				imagenesCargadas[contador] = actual;
				contador++;
				if (i <= 21) {
					formato = new Formatter();
					// System.out.println(chief.getURL());
					actual = new ImageIcon(
							getClass().getResource("/img/Boss/atacando/" + formato.format("%02d", i) + ".png"));
					imagenesCargadas[contador] = actual;
					contador++;
					if (i <= 17) {
						formato = new Formatter();
						actual = new ImageIcon(getClass()
								.getResource("/img/Caminante/muriendo/" + formato.format("%02d", i) + ".png"));
						imagenesCargadas[contador] = actual;
						contador++;
						formato = new Formatter();
						actual = new ImageIcon(getClass().getResource(
								"/img/Caminante/muriendoIncendiado/" + formato.format("%02d", i) + ".png"));
						imagenesCargadas[contador] = actual;
						contador++;
						formato = new Formatter();
						actual = new ImageIcon(getClass()
								.getResource("/img/Caminante/gru�endo/" + formato.format("%02d", i) + ".png"));
						imagenesCargadas[contador] = actual;
						contador++;
						if (i <= 13) {
							formato = new Formatter();
							actual = new ImageIcon(
									getClass().getResource("/img/Boss/volando/" + formato.format("%02d", i) + ".png"));
							imagenesCargadas[contador] = actual;
							contador++;
							formato = new Formatter();
							actual = new ImageIcon(getClass()
									.getResource("/img/Caminante/atacando/" + formato.format("%02d", i) + ".png"));
							imagenesCargadas[contador] = actual;
							contador++;
							formato = new Formatter();
							actual = new ImageIcon(getClass()
									.getResource("/img/Rastrero/atacando/" + formato.format("%02d", i) + ".png"));
							imagenesCargadas[contador] = actual;
							contador++;
							if (i <= 11) {
								formato = new Formatter();
								actual = new ImageIcon(getClass()
										.getResource("/img/Rastrero/muriendo/" + formato.format("%02d", i) + ".png"));
								imagenesCargadas[contador] = actual;
								contador++;
								formato = new Formatter();
								actual = new ImageIcon(getClass().getResource(
										"/img/Rastrero/muriendoIncendiado/" + formato.format("%02d", i) + ".png"));
								imagenesCargadas[contador] = actual;
								contador++;
							}
						}
					}
				}
			}
		}
		actual = new ImageIcon(getClass().getResource("/img/Fondo/sangre.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		actual = new ImageIcon(getClass().getResource("/img/Fondo/boss_ataca.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		actual = new ImageIcon(getClass().getResource("/img/Fondo/disparoM1911.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		actual = new ImageIcon(getClass().getResource("/img/Fondo/disparoRemington.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		actual = new ImageIcon(getClass().getResource("/img/Fondo/fondoMenu.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		actual = new ImageIcon(getClass().getResource("/img/Fondo/escenario-fondo-azul.png"));
		imagenesCargadas[contador] = actual;
		contador++;
		// System.out.println(contador);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (principal.getEstadoPartida() == SurvivorCamp.EN_CURSO)
			principal.pausarJuego();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (principal.getEstadoPartida() == SurvivorCamp.EN_CURSO) {
			if (arg0.getButton() == MouseEvent.BUTTON1) {
				int x = arg0.getX();
				int y = arg0.getY();
				if (armaEquipada.getEstado().equals(Arma.LISTA) && armaEquipada.getMunicion() > 0) {
					ultimoDisparo = arg0.getPoint();
					principal.disparar(x, y);
				} else if (y > Zombie.POS_ATAQUE && matador.getCuchillo().getEstado().equals(Arma.LISTA)) {
					ultimoDisparo = arg0.getPoint();
					principal.acuchillar(x, y);
				} else if (armaEquipada.getMunicion() == 0)
					principal.reproducir("sin_balas");
				labPuntaje.setText("Puntaje: " + matador.getScore());
				labBajas.setText("Bajas: " + matador.getMatanza());
			} else if (arg0.getButton() == MouseEvent.BUTTON3
					&& armaEquipada.getMunicion() < armaEquipada.getLimBalas())
				principal.cargarArmaPersonaje();
			labBalas.setText("" + armaEquipada.getMunicion());
		}
	}

	public void zombieAtaco() {
		labVidas.setValue(matador.getSalud());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (principal.getEstadoPartida() == SurvivorCamp.EN_CURSO) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode() == KeyEvent.VK_P)
				principal.pausarJuego();
			else if (e.getKeyCode() == KeyEvent.VK_C) {
				principal.cambiarArma();
				actualizarEquipada(matador.getPrincipal());
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE && matador.getGranadas().getMunicion() > 0) {
				principal.granadaLanzada();
				labGranadas.setText("" + matador.getGranadas().getMunicion());
			} 
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			mostrador.setVisible(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			mostrador.setVisible(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void quitarSangreZombie() {
		ultimoDisparo.setLocation(-40, -40);
	}

}
