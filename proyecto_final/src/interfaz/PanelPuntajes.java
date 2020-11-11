package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import mundo.Puntaje;

public class PanelPuntajes extends JPanel implements ActionListener {

	private static final String ORDEN_HEADSHOT = "Filtrar por tiros a la cabeza";
	private static final String ORDEN_BAJAS = "Filtrar por bajas";
	private static final String ORDEN_SCORE = "Filtrar por puntaje";
	private static final String BUSCAR_NOMBRE = "Buscar por nombre";
	private static final String SALIR = "salir";

	private JLabel titulo;
	private JLabel[] labScores;
	private JLabel[] labNombres;
	private JLabel[] labHeadShots;
	private JLabel[] labBajas;
	private JButton butFiltroHeadShot;
	private JButton butBuscarNombre;
	private JButton butFiltroBajas;
	private JButton butFiltroScore;
	private JButton butSalir;
	private InterfazZombieKiller principal;

	public PanelPuntajes(InterfazZombieKiller inter) {
		principal = inter;
		setBackground(Color.black);
		Font f = new Font("Chiller", Font.BOLD, 26);
		titulo = new JLabel("Puntajes");
		titulo.setForeground(Color.WHITE);
		butFiltroHeadShot = new JButton(ORDEN_HEADSHOT);
		butFiltroHeadShot.addActionListener(this);
		butFiltroHeadShot.setActionCommand(ORDEN_HEADSHOT);
		butBuscarNombre = new JButton(BUSCAR_NOMBRE);
		butBuscarNombre.addActionListener(this);
		butBuscarNombre.setActionCommand(BUSCAR_NOMBRE);
		butFiltroBajas = new JButton(ORDEN_BAJAS);
		butFiltroBajas.addActionListener(this);
		butFiltroBajas.setActionCommand(ORDEN_BAJAS);
		butFiltroScore = new JButton(ORDEN_SCORE);
		butFiltroScore.addActionListener(this);
		butFiltroScore.setActionCommand(ORDEN_SCORE);
		butSalir = new JButton();
		configurarBoton(butSalir, getClass().getResource("/img/Palabras/volver.png"), SALIR);
		butSalir.setActionCommand(SALIR);
		JLabel texto = new JLabel("No se encontraron puntajes");
		texto.setForeground(Color.WHITE);
		titulo.setFont(f);
		setLayout(new BorderLayout());
		add(titulo, BorderLayout.NORTH);
		add(texto, BorderLayout.CENTER);
		add(butSalir, BorderLayout.SOUTH);
	}

	public void actualizarPuntajes(ArrayList<Puntaje> scores) {
		if (scores.size() != 0) {
			removeAll();
			add(titulo, BorderLayout.NORTH);
			//generaryAgregarLabels(scores);
			generarTablaPuntajes(scores);
			generarYAgregarBotones();
			updateUI();
		}
	}

	private void generarTablaPuntajes(ArrayList<Puntaje> scores){
		String [] columnNames = {"Nombre","Score", "Bajas", "Headshot"};
		Object[][] data = new Object[scores.size()][columnNames.length];
		for (int i=0; i<scores.size();i++){
			data[i][0]= scores.get(i).getNombreKiller();
			data[i][1]= scores.get(i).getPuntaje();
			data[i][2]= scores.get(i).getBajas();
			data[i][3]= scores.get(i).getTirosALaCabeza();
		}


		TableModel tableModel = new AbstractTableModel() {
			public String getColumnName(int col) {
				return columnNames[col].toString();
			}
			public int getRowCount() { return data.length; }
			public int getColumnCount() { return columnNames.length; }
			public Object getValueAt(int row, int col) {
				return data[row][col];
			}
			public boolean isCellEditable(int row, int col)
			{ return false; }
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

		};

		JTable table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		JPanel auxTabla = new JPanel();
		auxTabla.setBackground(Color.BLACK);
		auxTabla.add(scrollPane);
		add(auxTabla, BorderLayout.CENTER);
	}

	private void generaryAgregarLabels(ArrayList<Puntaje> scores) {
		labScores = new JLabel[scores.size()];
		labNombres = new JLabel[scores.size()];
		labHeadShots = new JLabel[scores.size()];
		labBajas = new JLabel[scores.size()];
		JPanel auxPuntajes = new JPanel();
		auxPuntajes.setBackground(Color.black);
		if (scores.size() > 10) {
			auxPuntajes.setLayout(new GridLayout(11, 4));
			titulo.setText("Top 10 Mejores Puntajes");
		} else
			auxPuntajes.setLayout(new GridLayout(scores.size() + 1, 4));
		JLabel labScore = new JLabel("Score");
		labScore.setForeground(Color.WHITE);
		JLabel labNombre = new JLabel("Nombre");
		labNombre.setForeground(Color.WHITE);
		JLabel labTC = new JLabel("Headshots");
		labTC.setForeground(Color.WHITE);
		JLabel labKills = new JLabel("Bajas");
		labKills.setForeground(Color.WHITE);
		auxPuntajes.add(labNombre);
		auxPuntajes.add(labScore);
		auxPuntajes.add(labKills);
		auxPuntajes.add(labTC);
		for (int i = 0; i < scores.size() && i < 10; i++) {
			labScores[i] = new JLabel(scores.get(i).getPuntaje() + "");
			labScores[i].setForeground(Color.WHITE);
			labNombres[i] = new JLabel(scores.get(i).getNombreKiller());
			labNombres[i].setForeground(Color.WHITE);
			labHeadShots[i] = new JLabel(scores.get(i).getTirosALaCabeza() + "");
			labHeadShots[i].setForeground(Color.WHITE);
			labBajas[i] = new JLabel(scores.get(i).getBajas() + "");
			labBajas[i].setForeground(Color.WHITE);
			auxPuntajes.add(labNombres[i]);
			auxPuntajes.add(labScores[i]);
			auxPuntajes.add(labBajas[i]);
			auxPuntajes.add(labHeadShots[i]);
		}
		add(auxPuntajes, BorderLayout.CENTER);
	}

	private void generarYAgregarBotones () {
		JPanel auxBotones = new JPanel();
		auxBotones.setBackground(Color.black);
		//auxBotones.setLayout(new GridLayout(5, 1));
		//auxBotones.add(butFiltroHeadShot);
		//auxBotones.add(butFiltroBajas);
		//auxBotones.add(butFiltroScore);
		//auxBotones.add(butBuscarNombre);
		//auxBotones.add(butSalir);
		add(butSalir, BorderLayout.SOUTH);
	}

	public void mostrarPuntajeDe(Puntaje buscado) {
		if (buscado != null) {
			JLabel encontrado = new JLabel("Mejor puntaje del nombre buscado");
			Font f = new Font("Chiller", Font.BOLD, 26);
			encontrado.setFont(f);
			encontrado.setForeground(Color.WHITE);
			removeAll();
			add(encontrado, BorderLayout.NORTH);
			generarYAgregarBotones();
			ArrayList<Puntaje> aMostrar = new ArrayList<>();
			aMostrar.add(buscado);
			generaryAgregarLabels(aMostrar);
			//generarTablaPuntajes(aMostrar);
			updateUI();
		} else
			JOptionPane.showMessageDialog(this, "No se encontr� el nombre buscado en los puntajes");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String c = arg0.getActionCommand();
		if (c.equals(ORDEN_BAJAS))
			principal.ordenarPorBajas();
		else if (c.equals(ORDEN_HEADSHOT))
			principal.ordenarPorHeadshot();
		else if (c.equals(BUSCAR_NOMBRE))
			principal.buscarPorNombre();
		else if (c.equals(ORDEN_SCORE))
			principal.ordenarPorScore();
	}

	public void configurarBoton(JButton aEditar, URL rutaImagen, String comando) {
		aEditar.setBorder(null);
		aEditar.setFocusable(false);
		aEditar.setContentAreaFilled(false);
		aEditar.setActionCommand(comando);
		ImageIcon letras = new ImageIcon(rutaImagen);
		aEditar.setIcon(letras);
		aEditar.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon iI = new ImageIcon(getClass().getResource("/img/Palabras/volver.png"));
				butSalir.setIcon(iI);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon iI = new ImageIcon(getClass().getResource("/img/Palabras/volver_p.png"));
				butSalir.setIcon(iI);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				principal.mostrarPuntajes();
			}
		});
	}
}
