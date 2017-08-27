import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class zeroR extends JFrame {

	private JPanel contentPane;
	private final ArrayList<String> nombres1 = new ArrayList<String>();
	private final ArrayList<String> nombres2 = new ArrayList<String>();
	private int[][] matrizCorrelacion;
	private int[] vectorValores;
	private String[] claseDeAtributo;
	private String[] porcentajeAtributo;
	private String margenDeErrorCadena;
	private float margenDeErrorNumerico = 0;
	private int indiceChido;
	private ArrayList<frecuencia> atributosFrecuencias = new ArrayList<frecuencia>();
	//private int tamanioIndices[];
	private String resultadoZeroR;
	private JTextField campoResultadoZeroR;
	//private ArrayList<String> tamaniosFrecuencias = new ArrayList<String>();
	private DefaultTableModel modeloInstancias = new DefaultTableModel();
	private JTable tablaInstancias = new JTable(modeloInstancias);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					zeroR frame = new zeroR();
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
	public zeroR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public zeroR(ArrayList<String> list, JTable tabla) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		
		String[] elementos = list.toArray(new String[list.size()]);
		
		JComboBox<String> seleccionClase = new JComboBox<String>(elementos);
		seleccionClase.setBounds(5, 50, 138, 25);
		seleccionClase.setBackground(Color.WHITE);
		
		JButton btnOneR = new JButton("One R");
		btnOneR.setBounds(150, 50, 138, 25);
		
		JButton btnZeroR = new JButton("Zero R");
		btnZeroR.setBounds(300, 50, 138, 25);
		
		campoResultadoZeroR = new JTextField();
		campoResultadoZeroR.setBounds(450, 50, 145, 25);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 100, 600, 250);
		
		btnZeroR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atributosFrecuencias.clear();
				
				frecuenciaZeroR(seleccionClase, tabla);
				mayorFrecuenciaClase();
			}
		});
		
		btnOneR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nombres1.clear();
				nombres2.clear();
				
				
				frecuenciaNombre(seleccionClase, nombres1, tabla);
				
			
				
				frecuenciaNombre2(4, nombres2, tabla);
				crearTablaDeFrecuencia(tabla, seleccionClase, 4,
						modeloInstancias, tablaInstancias, scrollPane);
				
				System.out.println(claseDeAtributo[0]);

			}
		});
		
		contentPane = new JPanel();
		contentPane.add(seleccionClase);
		contentPane.add(btnZeroR);
		contentPane.add(btnOneR);
		contentPane.add(campoResultadoZeroR);
		contentPane.add(scrollPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
	
	public void frecuenciaNombre2(int n, ArrayList<String> nombres, JTable dtm){
		
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			if(i == 0)
			{
				nombres.add(dtm.getValueAt(i, n).toString());
			}
			else
			{
				boolean repetido = false;
				for(int j = 0; j < nombres.size(); j++)
				{
					String valorEnTabla = dtm.getValueAt(i, n).toString();
					if(valorEnTabla.equals(nombres.get(j).toString()))
					{
						nombres.set(j, valorEnTabla);
						repetido = true;
					}
				}
				if(repetido == false)
				{
					nombres.add((String) dtm.getValueAt(i, n));
				}
				
			}
		}
		
	}

	public void mayorFrecuenciaClase()
	{
		int mayor = 0;
		for(int i = 0; i < atributosFrecuencias.size(); i++)
		{
			if(atributosFrecuencias.get(i).numero > mayor)
			{
				mayor = atributosFrecuencias.get(i).numero;
				this.resultadoZeroR = atributosFrecuencias.get(i).tipoDato.toString();
			}
		}
		campoResultadoZeroR.setText(resultadoZeroR);
	}
	
	public void crearTablaDeFrecuencia(JTable dtm, JComboBox<String> list1, int list2,
			DefaultTableModel dtmCorrelacion, JTable tablaCorrelacion, JScrollPane scroll){
			
			//AÑADIMOS LOS CAMPOS NECESARIOS PARA LA TABLA DE CORRELACIÓN
			nombres1.add(0, " atributo ");
			nombres1.add("error");
			nombres2.add("error");
			
			int mayor = 0;
			
			String[] columnasTabla = nombres1.toArray(new String[nombres1.size()]);
			String[] primerFila = nombres2.toArray(new String[nombres2.size()]);
			
			crearMatrizCorrelacion(dtm, primerFila, columnasTabla, list1, list2);
			calcularTotales(dtm, primerFila.length, columnasTabla.length, list2);
			
			
			int columnas = nombres1.size();
			int filas = nombres2.size();
			
			claseDeAtributo = new String[columnas-2];
			
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
						if(mayor < matrizCorrelacion[i][j] && j < columnas-1)
						{
							mayor = matrizCorrelacion[i][j];
							claseDeAtributo[j-1] = nombres1.get(j).toString();
						}
						Matrix[i][j] = matrizCorrelacion[i][j];
					}
				}
				mayor = 0;
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
	
	public void crearMatrizCorrelacion(JTable dtmMatriz, String[] fila, String[] columna,
			JComboBox<String> seleccion1, int seleccion2){
			
			matrizCorrelacion =  new int[fila.length][columna.length];
			
			for(int i = 0; i < fila.length-1; i++)
			{
				for(int j = 0; j < dtmMatriz.getRowCount() ; j++)
				{
					if(fila[i].equals(dtmMatriz.getValueAt(j, seleccion2).toString()))
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
		
	}
	
	public void calcularTotales(JTable dtmMatriz, int filas, int columnas, int indice)
	{
		vectorValores = new int[filas];
		claseDeAtributo = new String[filas];
		porcentajeAtributo = new String[filas];
		float vectorSumado = 0;
		float total = matrizCorrelacion[filas-1][columnas-1];
		float mayor = 0;
		
		for(int i = 0; i < filas-1; i++)
		{
			for(int j = 0; j < columnas-1; j++)
			{
				if(j == 0)
					vectorValores[i] = matrizCorrelacion[i][j];
				else if(vectorValores[i] < matrizCorrelacion[i][j])
				{
					vectorValores[i] = matrizCorrelacion[i][j];
				}
				
				if(mayor < matrizCorrelacion[i][j]){
					mayor = matrizCorrelacion[i][j];
					//System.out.println( nombres1.get(j).toString());
					claseDeAtributo[i] = nombres1.get(j).toString();
				}
			}
		}
		
		for(int i = 0; i < vectorValores.length; i++)
		{
			vectorSumado += vectorValores[i];
		}
		
		if(margenDeErrorNumerico < (vectorSumado / total))
		{
			margenDeErrorNumerico =  vectorSumado / total;
			margenDeErrorCadena = "" + vectorSumado + " / " + total;
			indiceChido = indice;
		}
		
	}
	
	public void frecuenciaZeroR(JComboBox<String> list, JTable dtm){
		
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			frecuencia nuevo = new frecuencia();
			if(i == 0)
			{
				nuevo.tipoDato = dtm.getValueAt(i, list.getSelectedIndex()).toString();
				nuevo.numero = 1;
				atributosFrecuencias.add(nuevo);
			}
			else
			{
				boolean repetido = false;
				for(int j = 0; j < atributosFrecuencias.size(); j++)
				{
					String valorEnTabla = dtm.getValueAt(i, list.getSelectedIndex()).toString();
					if(valorEnTabla.equals(atributosFrecuencias.get(j).tipoDato.toString()))
					{
						nuevo.tipoDato = valorEnTabla;
						nuevo.numero = atributosFrecuencias.get(j).numero + 1;
						atributosFrecuencias.set(j, nuevo);
						repetido = true;
					}
				}
				if(repetido == false)
				{
					nuevo.tipoDato = (String) dtm.getValueAt(i, list.getSelectedIndex());
					nuevo.numero = 1;
					atributosFrecuencias.add(nuevo);
				}
				
			}
		}
		
	}
}
