package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mundo.Armas.Cuchillo;
import mundo.Armas.Granada;
import mundo.Armas.M1911;
import mundo.Armas.Remington;

public class PanelArmas extends JPanel implements ActionListener{

	private static final String ANTERIOR = "a";
	private static final String POSTERIOR = "p";

	private JLabel titulo;
	private JLabel [] labArmas;
	private PanelAtributos [] panelAtributos;
	private JButton butAnterior;
	private JButton butPosterior;
	private InterfazZombieKiller principal;
//	http://es.halo.wikia.com/wiki/Granada_Incendiaria_Antipersonal_Tipo-3
	
	public PanelArmas (InterfazZombieKiller inter) {
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		principal = inter;
//		JPanel auxNorte = new JPanel();
//		auxNorte.setLayout(new BorderLayout());
//		auxNorte.setBackground(Color.BLACK);
//		JPanel auxCentro = new JPanel();
//		auxCentro.setLayout(new BorderLayout());
//		auxCentro.setBackground(Color.BLACK);
//		JPanel auxSur= new JPanel();
//		auxSur.setLayout(new BorderLayout());
//		auxSur.setBackground(Color.BLACK);

		titulo = new JLabel("Armas");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Chiller", Font.ITALIC, 30));
		titulo.setForeground(Color.WHITE);

		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panel1, panel2, panel3, panel4, panel5;
		panel1 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel2 = new JPanel();
		panel2.setBackground(Color.BLACK);
		panel3 = new JPanel();
		panel3.setBackground(Color.BLACK);
		panel4 = new JPanel();
		panel4.setBackground(Color.BLACK);

		tabbedPane.addTab("Cuchillo", panel1);
		tabbedPane.addTab("Granada ", panel2);
		tabbedPane.addTab("M1911", panel3);
		tabbedPane.addTab("Remington ", panel4);

		labArmas = new JLabel[4];
		panelAtributos = new PanelAtributos[4];
		
		butAnterior = new JButton("<");
		butAnterior.setActionCommand(ANTERIOR);
		butAnterior.addActionListener(this);
		
		butPosterior = new JButton(">");
		butPosterior.setActionCommand(POSTERIOR);
		butPosterior.addActionListener(this);
		
		ImageIcon perfil;
		perfil = new ImageIcon(getClass().getResource("/img/Fondo/perfilRemington.png"));
		labArmas[0] = new JLabel (perfil);
		perfil = new ImageIcon(getClass().getResource("/img/Fondo/perfilM1911.png"));
		labArmas[1] = new JLabel (perfil);
//		labArmas[1].setVisible(false);
		perfil = new ImageIcon(getClass().getResource("/img/Fondo/perfilGranada.png"));
		labArmas[2] = new JLabel (perfil);
//		labArmas[2].setVisible(false);
		perfil = new ImageIcon(getClass().getResource("/img/Fondo/perfilCuchillo.png"));
		labArmas[3] = new JLabel (perfil);
//		labArmas[3].setVisible(false);
		
		inicializarAtributos();
		
		panel3.add(labArmas[1], BorderLayout.CENTER);
		panel3.add(panelAtributos[1], BorderLayout.SOUTH);
		panel2.add(labArmas[2], BorderLayout.CENTER);
		panel2.add(panelAtributos[2], BorderLayout.SOUTH);
		panel1.add(labArmas[3], BorderLayout.CENTER);
		panel1.add(panelAtributos[3], BorderLayout.SOUTH);
		panel4.add(labArmas[0], BorderLayout.CENTER);
		panel4.add(panelAtributos[0], BorderLayout.SOUTH);
		//add(butAnterior, BorderLayout.WEST);
		//add(butPosterior, BorderLayout.EAST);

		add(titulo,BorderLayout.NORTH);
		add(tabbedPane,BorderLayout.CENTER);
	}
	
	public void inicializarAtributos () {
		String[] atributos = new String[3];
		int[] valores = new int [3];
		atributos[0] = "Da�o";
		atributos[1] = "Retroceso";
		atributos[2] = "Tiempo de Carga";
		
		valores[0] = Remington.DANIO;
		valores[1] = Remington.RETROCESO;
		valores[2] = Remington.TIEMPO_CARGA;
		
		panelAtributos[0] = new PanelAtributos(atributos, valores);
		
		atributos = new String[3];
		valores = new int [3];
		atributos[0] = "Da�o";
		atributos[1] = "Retroceso";
		atributos[2] = "Tiempo de Carga";
		
		valores[0] = M1911.DANIO;
		valores[1] = M1911.RETROCESO;
		valores[2] = M1911.TIEMPO_CARGA;
		
		panelAtributos[1] = new PanelAtributos(atributos, valores);
//		panelAtributos[1].setVisible(false);
		
		atributos = new String[1];
		valores = new int [1];
		atributos[0] = "Da�o";
		
		valores[0] = Granada.DANIO;
		
		panelAtributos[2] = new PanelAtributos(atributos, valores);
//		panelAtributos[2].setVisible(false);
		
		atributos = new String[1];
		valores = new int [1];
		atributos[0] = "Da�o";
		
		valores[0] = Cuchillo.DANIO;
		
		panelAtributos[3] = new PanelAtributos(atributos, valores);
//		panelAtributos[3].setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(ANTERIOR))
			verIzquierda();
		if(arg0.getActionCommand().equals(POSTERIOR))
			verDerecha();
	}

	private void verDerecha() {
		labArmas[principal.darArmaMostrada()].setVisible(false);
		panelAtributos[principal.darArmaMostrada()].setVisible(false);
		int aMostrar = principal.cambiarArmaVisibleDerecha();
		labArmas[principal.darArmaMostrada()].setVisible(true);
		panelAtributos[principal.darArmaMostrada()].setVisible(true);
		add(labArmas[aMostrar], BorderLayout.CENTER);
		add(panelAtributos[aMostrar], BorderLayout.SOUTH);
	}

	private void verIzquierda() {
		labArmas[principal.darArmaMostrada()].setVisible(false);
		panelAtributos[principal.darArmaMostrada()].setVisible(false);
		int aMostrar = principal.cambiarArmaVisibleIzquierda();
		labArmas[principal.darArmaMostrada()].setVisible(true);
		panelAtributos[principal.darArmaMostrada()].setVisible(true);
		add(labArmas[aMostrar], BorderLayout.CENTER);
		add(panelAtributos[aMostrar], BorderLayout.SOUTH);
	}
}
