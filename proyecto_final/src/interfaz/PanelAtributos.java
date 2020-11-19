package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import mundo.Personajes.Boss;
import mundo.Armas.Granada;
import mundo.Armas.Remington;
import mundo.Personajes.Zombie;

public class PanelAtributos extends JPanel {

	private JProgressBar[] barValores;
	private JLabel[] labAtributos;

	// La cantidad de atributos y valores debe ser la misma
	public PanelAtributos(String[] atributos, int[] valores) {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(valores.length, 2));
		
		labAtributos = new JLabel[atributos.length];
		barValores = new JProgressBar[valores.length];
		
		Font letra = new Font("Chiller", Font.ITALIC, 34);
		for (int i = 0; i < valores.length; i++) {

			labAtributos[i] = new JLabel(atributos[i]);
			labAtributos[i].setFont(letra);
			labAtributos[i].setForeground(Color.white);
			add(labAtributos[i]);

			barValores[i] = new JProgressBar();
			barValores[i].setForeground(Color.RED);
			barValores[i].setBackground(Color.WHITE);
			add(barValores[i]);
			
			if (atributos[i].equals("Da�o"))
				barValores[i].setMaximum(Granada.DANIO);
			else if (atributos[i].equals("Salud"))
				barValores[i].setMaximum(Boss.getEnemigo().getSalud(0));
			else if (atributos[i].equals("Lentitud"))
				//barValores[i].setMaximum(Zombie.LENTITUD1);
				barValores[i].setMaximum(Zombie.getEnemigo().getLentitud(0));//LENTITUD1
			else if (atributos[i].equals("Retroceso"))
				barValores[i].setMaximum(Remington.RETROCESO + 100);
			else if (atributos[i].equals("Tiempo de Carga"))
				barValores[i].setMaximum(Remington.TIEMPO_CARGA + 200);
			
			barValores[i].setValue(valores[i]);
		}
	}
}
