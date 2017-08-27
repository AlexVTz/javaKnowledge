import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class levenshtein extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> valoresInstancias = new ArrayList<String>();
	private DefaultTableModel modeloInstancias = new DefaultTableModel();
	private JTable tablaInstancias = new JTable(modeloInstancias);
	private String elementoRemplazar, elementoAntiguo;
	private int indexColumna;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					levenshtein frame = new levenshtein();
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
	public levenshtein() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public levenshtein(ArrayList<String> list, DefaultTableModel dtm, JTable tabla) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		this.setTitle("Levenshtein");
		
		String[] elementos = list.toArray(new String[list.size()]);
		
		JButton btnDesplegar = new JButton("Desplegar");
		btnDesplegar.setBounds(5, 50, 138, 25);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(5, 85, 138, 25);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(280, 300, 138, 25);
		
		JComboBox<String> atributos = new JComboBox<String>(elementos);
		atributos.setBounds(5, 120, 138, 25);
		atributos.setBackground(Color.WHITE);
		
		//CREACION DE MODELO DE TABLA Y SCROLL PANE
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(160, 50, 180, 200);
		
		JLabel textoRemplazar = new JLabel();
		textoRemplazar.setBounds(50, 300, 220, 25);
		textoRemplazar.setBackground(Color.BLUE);
		
		btnDesplegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esEntero(atributos, tabla))
				{
					valoresInstancias.clear();
					textoRemplazar.setText("Aún no hay estimaciones");
					frecuenciaNombre(atributos, valoresInstancias, tabla);
					crearTablaInstancias(atributos, tablaInstancias, modeloInstancias, scroll);				
				}
				else
					JOptionPane.showMessageDialog(null,"El atributo es de tipo numerico");
				
			}
		});
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esEntero(atributos, tabla) && tablaInstancias.getSelectedRow() > -1)
				{
					
					calcularLevenshtain(atributos, modeloInstancias, tablaInstancias);
					textoRemplazar.setText("Quizas quizo decir " + elementoRemplazar);
				}
				else
					JOptionPane.showMessageDialog(null,"El atributo es de tipo numerico o selecciona una instancia");
				
			}
		});
		
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(elementoRemplazar.length() > 0)
				{
					tabla.setModel(nuevoModelo(tabla, elementos, dtm));
					dtm.fireTableStructureChanged();
					
				}
				else
					JOptionPane.showMessageDialog(null,"Aún no haz hecho una predicción");
				
			}
		});
		
		contentPane = new JPanel();
		contentPane.add(btnDesplegar);
		contentPane.add(btnCalcular);
		contentPane.add(atributos);
		contentPane.add(tablaInstancias);
		contentPane.add(scroll);
		contentPane.add(textoRemplazar);
		contentPane.add(btnCambiar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public boolean esEntero(JComboBox<String> list, JTable dtm){
		
		for(int i=0;i<dtm.getRowCount();i++){
					
					if(dtm.getValueAt(i, list.getSelectedIndex()).toString().matches("\\d*")){
						return false;
					}
				}
				return true;
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

	public void crearTablaInstancias(JComboBox<String> list, JTable table, 
			DefaultTableModel dtmInstancias, JScrollPane scroll)
	{
		
		String[] columnas =  {"" + list.getSelectedItem()};
		Object Matrix[][] = new Object[valoresInstancias.size()][1];
		
		
		for(int i = 0; i < valoresInstancias.size(); i++){
			Matrix[i][0] = new Object();
			Matrix[i][0] = valoresInstancias.get(i);
		}
		
		dtmInstancias = new DefaultTableModel(Matrix, columnas);
		
		table.setModel(dtmInstancias);
		getContentPane().setLayout(null);
		
		scroll.setViewportView(table);
		getContentPane().add(scroll);
	}
	
	public void calcularLevenshtain(JComboBox<String> index, DefaultTableModel modelo, JTable tabla)
	{
		int elementoEvaluado = tabla.getSelectedRow();
		indexColumna = index.getSelectedIndex();
		
		String valorElemento = valoresInstancias.get(elementoEvaluado).toString();
		elementoAntiguo = valorElemento;
		int menorSimilitud = 0;
		boolean primerComparacion = false;
		
		for(int k = 0; k < valoresInstancias.size(); k++)
		{
			if(k != elementoEvaluado)
			{
				String elementoAComparar = valoresInstancias.get(k).toString();
				
				int filas = valorElemento.length()+1;
				int columnas = elementoAComparar.length()+1;
				
				char primerElemento[] = new char[filas-1];
				char segundoElemento[] = new char[columnas-1];
				
				for(int i = 0; i < filas-1; i++)
				{
					primerElemento[i] = valorElemento.charAt(i);
				}
				
				for(int i = 0; i < columnas-1; i++)
				{
					segundoElemento[i] = elementoAComparar.charAt(i);
				}
				
				int Matrix[][] = new int[filas][columnas];
				
				for(int i = 0; i < filas; i++)
				{
					for(int j = 0; j < columnas; j++)
					{
						if(i == 0 && j == 0)
						{
							Matrix[i][j] = 0;
						}
						else if(i == 0)
						{
							Matrix[i][j] = j-1;
						}
						else if(j == 0)
						{
							Matrix[i][j] = i-1;
						}
						else
						{
							if(valorElemento.charAt(i-1) == elementoAComparar.charAt(j-1))
								Matrix[i][j] = Matrix[i-1][j-1] + 0;
							else
								Matrix[i][j] = Matrix[i-1][j-1] + 1;
						}
					}
				}
				if(!primerComparacion){
					menorSimilitud = Matrix[filas-1][columnas-1];
					elementoRemplazar = elementoAComparar;
				}
				else if(menorSimilitud > Matrix[filas-1][columnas-1]){
					menorSimilitud = Matrix[filas-1][columnas-1];
					elementoRemplazar = elementoAComparar;
				}
				
				primerComparacion = true;
			}
		}
		
	}
	
	public DefaultTableModel nuevoModelo(JTable dtm, String[] columnas, DefaultTableModel ok)
	{
		Object Matrix[][] = new Object[dtm.getRowCount()][dtm.getColumnCount()];
		
		for(int i = 0; i < dtm.getRowCount(); i++)
		{
			for(int j = 0; j < dtm.getColumnCount(); j++)
			{
				Matrix[i][j] = new Object();
				if(j != indexColumna)
				{
					Matrix[i][j] = dtm.getValueAt(i, j).toString();
				}
				else
				{
					if(dtm.getValueAt(i, j).toString().equals(elementoAntiguo))
					{
						Matrix[i][j] = elementoRemplazar;
					}
					else
						Matrix[i][j] = dtm.getValueAt(i, j).toString();
				}
			}
		}
		
		ok = new DefaultTableModel(Matrix, columnas);
		return ok;
	}
}
