import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;


public class correlacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField resultadoCorrelacion;

	private final ArrayList<String> nombres1 = new ArrayList<String>();
	private final ArrayList<String> nombres2 = new ArrayList<String>();
	private int[][] matrizCorrelacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					correlacion frame = new correlacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public correlacion(ArrayList<String> list, JTable tabla) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		
		//CREACION DE COMBOBOX
		String[] elementos = list.toArray(new String[list.size()]);
		
		JComboBox<String> chiCuadrada1 = new JComboBox<String>(elementos);
		chiCuadrada1.setBounds(5, 50, 138, 25);
		chiCuadrada1.setBackground(Color.WHITE);
		
		JComboBox<String> chiCuadrada2 = new JComboBox<String>(elementos);
		chiCuadrada2.setBounds(150, 50, 138, 25);
		chiCuadrada2.setBackground(Color.WHITE);
		
		//CREACIÓN DE BOTON PARA UTILIZAR CORRELACIÓN
		JButton btnCorrelacion = new JButton("Correlación");
		btnCorrelacion.setBounds(300, 50, 138, 25);
		
		//CREACIÓN DEL  TEXTBOX DE EL RESULTADO
		resultadoCorrelacion = new JTextField();
		resultadoCorrelacion.setBounds(450, 50, 145, 25);
		
		//DECLARACION DE ELEMENTOS PARA LA TABLA CORRELACION
		JTable tablaCorrelacion = new JTable();
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 100, 600, 250);

		DefaultTableModel modeloTablaDeCorrelacion = new DefaultTableModel();
		
		//CREACION DEL MODELO DE LA TABLA
		btnCorrelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nombres1.clear();
				nombres2.clear();
				
				frecuenciaNombre(chiCuadrada1, nombres1, tabla);
				frecuenciaNombre(chiCuadrada2, nombres2, tabla);
				crearTablaDeFrecuencia(tabla, chiCuadrada1, chiCuadrada2, modeloTablaDeCorrelacion, 
										tablaCorrelacion, scrollPane);
			}
		});
		
		contentPane = new JPanel();
		contentPane.add(chiCuadrada1);
		contentPane.add(chiCuadrada2);
		contentPane.add(btnCorrelacion);
		contentPane.add(resultadoCorrelacion);
		contentPane.add(scrollPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	public correlacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}
	
	
	public void frecuenciaNombre(JComboBox<String> list, ArrayList<String> nombres, JTable dtm){
		
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			if(i == 0)
			{
				nombres.add(dtm.getValueAt(i, list.getSelectedIndex()).toString());
			}
			else
			{
				boolean repetido = false;
				for(int j = 0; j < nombres.size(); j++)
				{
					String valorEnTabla = dtm.getValueAt(i, list.getSelectedIndex()).toString();
					if(valorEnTabla.equals(nombres.get(j).toString()))
					{
						nombres.set(j, valorEnTabla);
						repetido = true;
					}
				}
				if(repetido == false)
				{
					nombres.add((String) dtm.getValueAt(i, list.getSelectedIndex()));
				}
				
			}
		}
		
	}
	
	public void crearTablaDeFrecuencia(JTable dtm, JComboBox<String> list1, JComboBox<String> list2,DefaultTableModel dtmCorrelacion, JTable tablaCorrelacion, JScrollPane scroll){
		
		//AÑADIMOS LOS CAMPOS NECESARIOS PARA LA TABLA DE CORRELACIÓN
		nombres1.add(0, " ");
		nombres1.add("Total");
		nombres2.add("Total");
		
		String[] columnasTabla = nombres1.toArray(new String[nombres1.size()]);
		String[] primerFila = nombres2.toArray(new String[nombres2.size()]);
		
		crearMatrizCorrelacion(dtm, primerFila, columnasTabla, list1, list2);
		
		int columnas = nombres1.size();
		int filas = nombres2.size();
		
		calcularCorrelacion(filas, columnas);
		
		Object Matrix[][] = new Object[filas][columnas];
		
		for(int i = 0; i < filas; i++)
		{
			for(int j = 0; j < columnas; j++)
			{
				Matrix[i][j] = new Object();
				if(j == 0)
				{
					Matrix[i][j] = primerFila[i].toString();
				}
				else
				{
					Matrix[i][j] = matrizCorrelacion[i][j];
				}
			}
		}
		
		DefaultTableModel modelo = (DefaultTableModel)tablaCorrelacion.getModel(); 
		
		if(dtmCorrelacion.getRowCount() > 0)
		{
			for(int i = 0; i < dtmCorrelacion.getColumnCount(); i++)
			{
				modelo.removeRow(i);
			}
		}
		
		tablaCorrelacion.updateUI();
		
		dtmCorrelacion = new DefaultTableModel(Matrix, columnasTabla);
		
		tablaCorrelacion = new JTable(dtmCorrelacion);
		tablaCorrelacion.updateUI();
		getContentPane().setLayout(null);
		
		scroll.setViewportView(tablaCorrelacion);
		getContentPane().add(scroll);
		getContentPane().repaint();
		
	}
	
	public void crearMatrizCorrelacion(JTable dtmMatriz, String[] fila, String[] columna,JComboBox<String> seleccion1, JComboBox<String> seleccion2){
		
		matrizCorrelacion =  new int[fila.length][columna.length];
		
		for(int i = 0; i < fila.length-1; i++)
		{
			for(int j = 0; j < dtmMatriz.getRowCount() ; j++)
			{
				if(fila[i].equals(dtmMatriz.getValueAt(j, seleccion2.getSelectedIndex()).toString()))
				{
					for(int k = 1; k < columna.length-1; k++)
					{
						if(columna[k].equals(dtmMatriz.getValueAt(j, seleccion1.getSelectedIndex()).toString()))
						{
							if(matrizCorrelacion[i][k] >= 1)
								matrizCorrelacion[i][k]++;
							else
								matrizCorrelacion[i][k] = 1;
						}
					}
				}
			}
		}
		
		for(int i = 1; i < columna.length; i++)
		{
			int suma = 0;
			for(int k = 0; k < fila.length; k++)
			{
				suma += matrizCorrelacion[k][i];
				if(k == fila.length-1)
					matrizCorrelacion[k][i] = suma;
			}
		}
		
		for(int i = 0; i < fila.length; i++)
		{
			int suma = 0;
			for(int k = 1; k < columna.length; k++)
			{
				suma += matrizCorrelacion[i][k];
				if(k == columna.length-1)
					matrizCorrelacion[i][k] = suma;
			}
		}
		
	}
	
	public void calcularCorrelacion(int filas, int columnas)
	{
		double totalCalculo = 0;
		double totalMatriz = matrizCorrelacion[filas-1][columnas-1];
		double matrizCalculo[][] = new double[filas][columnas];
		
		for(int i = 1; i < columnas-1; i++)
		{
			for(int j = 0; j < filas-1; j++){
				matrizCalculo[j][i] = (matrizCorrelacion[j][columnas-1] * matrizCorrelacion[filas-1][i])/ totalMatriz;
				matrizCalculo[j][i] = (double) Math.pow((matrizCorrelacion[j][i]  - matrizCalculo[j][i]), 2) / totalMatriz;
				totalCalculo += matrizCalculo[j][i];
			}
		}
		
		String t = ""+totalCalculo;
		
		resultadoCorrelacion.setText(t);
		
		System.out.println(totalCalculo);
		
	}
}
