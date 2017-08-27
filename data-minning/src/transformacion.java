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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class transformacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6130875630827007664L;
	private JPanel contentPane;
	private ArrayList<frecuencia> listaDeValores = new ArrayList<frecuencia>();
	private final int rangoMaximo = 1;
	private final int rangoMinimo = 0;
	
	void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transformacion frame = new transformacion();
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
	public transformacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public transformacion(ArrayList<String> list, JTable tabla) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		
		//CREACION DE COMBOBOX
		String[] elementos = list.toArray(new String[list.size()]);
		
		JComboBox<String> lista = new JComboBox<String>(elementos);
		lista.setBounds(10, 50, 138, 20);
		lista.setBackground(Color.WHITE);
		
		//CREACIÓN DE BOTONES
		JButton btnMinMax = new JButton("Min-Max");
		btnMinMax.setBounds(10, 100, 138, 25);
		
		JButton btnZscore = new JButton("Z Score");
		btnZscore.setBounds(10, 150, 138, 25);
		
		JButton btnEscalamiento = new JButton("Escalamiento");
		btnEscalamiento.setBounds(10, 200, 138, 25);
		
		//CREACION DE MODELO DE TABLA Y SCROLL PANE
		DefaultTableModel modeloTransformaciones = new DefaultTableModel();
		JTable tablaTransformaciones = new JTable();
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(160, 50, 400, 200);
		
		btnMinMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esEntero(lista, tabla))
				{
					listaDeValores.clear();
					
					frecuenciaNombre(lista, tabla);
					minMax(lista, tabla, tablaTransformaciones, modeloTransformaciones, scroll);
				}
				else
					JOptionPane.showMessageDialog(null,"El atributo no es de tipo numerico");
				
			}
		});
		
		btnZscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esEntero(lista, tabla))
				{
					listaDeValores.clear();
					
					frecuenciaNombre(lista, tabla);
					zScore(lista, tabla, tablaTransformaciones, modeloTransformaciones, scroll);
				}
				else
					JOptionPane.showMessageDialog(null,"El atributo no es de tipo numerico");
				
			}
		});
		
		btnEscalamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esEntero(lista, tabla))
				{
					listaDeValores.clear();
					
					frecuenciaNombre(lista, tabla);
					escalamientoDecimal(lista, tabla, tablaTransformaciones, modeloTransformaciones, scroll);
				}
				else
					JOptionPane.showMessageDialog(null,"El atributo no es de tipo numerico");
				
			}
		});
		
		contentPane = new JPanel();
		contentPane.add(lista);
		contentPane.add(btnMinMax);
		contentPane.add(btnZscore);
		contentPane.add(btnEscalamiento);
		contentPane.add(scroll);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void frecuenciaNombre(JComboBox<String> list, JTable dtm){
			
			for(int i = 0; i < dtm.getRowCount(); i++)
			{
				frecuencia nuevo = new frecuencia();
				if(i == 0)
				{
					nuevo.tipoDato = dtm.getValueAt(i, list.getSelectedIndex()).toString();
					nuevo.numero = 1;
					listaDeValores.add(nuevo);
				}
				else
				{
					boolean repetido = false;
					for(int j = 0; j < listaDeValores.size(); j++)
					{
						String valorEnTabla = dtm.getValueAt(i, list.getSelectedIndex()).toString();
						if(valorEnTabla.equals(listaDeValores.get(j).tipoDato.toString()))
						{
							nuevo.tipoDato = valorEnTabla;
							nuevo.numero = listaDeValores.get(j).numero + 1;
							listaDeValores.set(j, nuevo);
							repetido = true;
						}
					}
					if(repetido == false)
					{
						nuevo.tipoDato = (String) dtm.getValueAt(i, list.getSelectedIndex());
						nuevo.numero = 1;
						listaDeValores.add(nuevo);
					}
					
				}
			}
			
		}
	
	public boolean esEntero(JComboBox<String> list, JTable dtm){
			
		for(int i=0;i<dtm.getRowCount();i++){
					
					if(!dtm.getValueAt(i, list.getSelectedIndex()).toString().matches("\\d*")){
						return false;
					}
				}
				return true;
	}
	
	public void minMax(JComboBox<String> list, JTable dtm, JTable table, 
			DefaultTableModel dtmMinMax, JScrollPane scroll)
	{
		float mayor, menor;
		int indiceColumna = list.getSelectedIndex();
		String[] columnas =  {"Vn", "Valor", "Resultado " + list.getSelectedItem()};
		
		menor = Float.parseFloat(dtm.getValueAt(0, indiceColumna).toString());
		mayor = Float.parseFloat(dtm.getValueAt(0, indiceColumna).toString());
		
		for(int i = 1; i < dtm.getRowCount(); i++)
		{
			if(menor > Float.parseFloat(dtm.getValueAt(i, indiceColumna).toString()))
				menor = Float.parseFloat(dtm.getValueAt(i, indiceColumna).toString());
			
			if(mayor < Float.parseFloat(dtm.getValueAt(i, indiceColumna).toString()))
				mayor = Float.parseFloat(dtm.getValueAt(i, indiceColumna).toString());
		}

		Object Matrix[][] = new Object[listaDeValores.size()][3];
		float valor = 0;
		
		for(int i = 0; i < listaDeValores.size(); i++){
			
			for(int j = 0; j < 3; j++){
				Matrix[i][j] = new Object();
				if(j == 0)
					Matrix[i][j] = "V " + (i +1);
				else if( j == 1){
					Matrix[i][j] = listaDeValores.get(i).tipoDato.toString();
				}
				else if(j == 2){
					valor = Float.parseFloat(listaDeValores.get(i).tipoDato.toString());
					Matrix[i][j] = ((valor - menor) / (mayor - menor))
									* (( rangoMaximo - rangoMinimo) + rangoMinimo);
				}
			}
		}
		
		dtmMinMax = new DefaultTableModel(Matrix, columnas);
		
		table = new JTable(dtmMinMax);
		getContentPane().setLayout(null);
		
		scroll.setViewportView(table);
		getContentPane().add(scroll);
	}

	public void zScore(JComboBox<String> list, JTable dtm, JTable table, 
			DefaultTableModel dtmZscore, JScrollPane scroll)
	{
		double desviacionEstandar = desviacionEstandar(list, dtm);
		double media = media(list, dtm);
		String[] columnas =  {"Vn", "Valor", "Resultado " + list.getSelectedItem()};
		
		Object Matrix[][] = new Object[listaDeValores.size()][3];
		double valor = 0;
		
		for(int i = 0; i < listaDeValores.size(); i++){
			
			for(int j = 0; j < 3; j++){
				Matrix[i][j] = new Object();
				if(j == 0)
					Matrix[i][j] = "V " + (i +1);
				else if( j == 1){
					Matrix[i][j] = listaDeValores.get(i).tipoDato.toString();
				}
				else if(j == 2){
					valor = Double.parseDouble(listaDeValores.get(i).tipoDato.toString()) - media / desviacionEstandar;
					Matrix[i][j] = valor;
				}
			}
		}
		
		dtmZscore = new DefaultTableModel(Matrix, columnas);
		
		table = new JTable(dtmZscore);
		getContentPane().setLayout(null);

		scroll.setViewportView(table);
		getContentPane().add(scroll);	
	}
	
	public void escalamientoDecimal(JComboBox<String> list, JTable dtm, JTable table, 
			DefaultTableModel dtmEscalamiento, JScrollPane scroll)
	{
		String[] columnas =  {"Vn", "Valor", "Resultado " + list.getSelectedItem()};
		int tamanio = 0;
		float sum = 0;
		int aux, cadena;
		
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			aux =  Integer.parseInt(dtm.getValueAt(i, list.getSelectedIndex()).toString());
			cadena = ("" + aux).length();
			if(tamanio < cadena)
				tamanio = cadena;		
		}
		
		System.out.println(tamanio);
		
		Object Matrix[][] = new Object[listaDeValores.size()][3];
		
		for(int i = 0; i < listaDeValores.size(); i++)
		{	
			for(int j = 0; j < 3; j++){
				Matrix[i][j] = new Object();
				if(j == 0)
					Matrix[i][j] = "V " + (i +1);
				else if( j == 1){
					Matrix[i][j] = listaDeValores.get(i).tipoDato.toString();
				}
				else if(j == 2){
					sum = (float) (Float.parseFloat(dtm.getValueAt(i, list.getSelectedIndex()).toString())/ (Math.pow(10, tamanio)));	
					Matrix[i][j] = sum;
				}
			}
		}
		

		
		dtmEscalamiento = new DefaultTableModel(Matrix, columnas);
		
		table = new JTable(dtmEscalamiento);

		getContentPane().setLayout(null);
		
		scroll.setViewportView(table);
		
		getContentPane().add(scroll);	
		getContentPane().revalidate();
	}
	
	public double desviacionEstandar(JComboBox<String> list, JTable dtm)
	{
		int media=0;
		for(int i=0;i<dtm.getRowCount();i++){
			media+= Integer.parseInt(dtm.getValueAt(i, list.getSelectedIndex()).toString());
		}
		
		media/=dtm.getRowCount();
		
		double sumatoria;
		double desviacion;
		double redondeo;
		double varianza = 0;
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			sumatoria = 
			Math.pow(Integer.parseInt(dtm.getValueAt(i, list.getSelectedIndex()).toString()) - media, 2);
			varianza = varianza + sumatoria;
		}
		varianza = varianza / (dtm.getRowCount()-1);
		
		desviacion = Math.sqrt(varianza);
		redondeo = Math.rint(desviacion*100)/100;
		return redondeo;
	}

	public double media(JComboBox<String> list, JTable dtm)
	{
		int media=0;
		for(int i=0;i < dtm.getRowCount();i++){
			media+= Integer.parseInt(dtm.getValueAt(i, list.getSelectedIndex()).toString());
		}
		
		media/=dtm.getRowCount();
		return media;
	}
}