import java.awt.EventQueue;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import java.awt.SystemColor;

 public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//ELEMENTOS DE PANEL, TABLAS Y MENU SUPERIOR
	private JTable table, tablaFrecuencias, tablaEstadisticas, tableRegular;
	private JPanel contentPane;
	private JMenuBar barraMenu;
	private DefaultTableModel dtm;

	//ELEMENTOS DE MENU SUPERIOR
	private JMenu menuVer,menuPreproceso,menuDataM, menuClasificacion;
	 
	//ELEMENTOS DE JMENU ITEM
	private JMenuItem itmCargarArchivo,itmGuardarArchivo, menuLimpieza, correlacion, transformacion, menuLevenshtain,
						zeroR, oneR, naiveBayes;
	private JScrollPane scrollPane_1 = new JScrollPane();
	private JTextField textR, textRemplazar;
	ArrayList<String> cabezeras = new ArrayList<String>();
	
	//ARCHIVO CARGADO
	private boolean cargado;
			
	//MAIN DE LA CLASE
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CONSTRUCTOR DE LA CLASE
	public Inicio() {
	    /* Inicializo todos los componentes de la ventana */
	    this.barraMenu          = new JMenuBar();
	    this.setTitle("Minería de Datos");
	    this.menuVer            = new JMenu("Archivo");
	    this.menuPreproceso        = new JMenu("Pre-procesamiento");
	    this.menuDataM          = new JMenu("Data M");
	    this.menuClasificacion = new JMenu("Clasificacion");
	     
	    this.itmCargarArchivo        = new JMenuItem("Cargar Archivo");
	    this.itmGuardarArchivo      = new JMenuItem("Guardar Archivo");
	    this.menuLimpieza      = new JMenuItem("Limpieza de Datos");
	    this.correlacion = new JMenuItem("Correlación");
	    this.transformacion = new JMenuItem("Transformación");
	    this.menuLevenshtain = new JMenuItem("Levenshtain");
	    this.zeroR = new JMenuItem("Zero R & One R");
	    this.oneR = new JMenuItem("One R");
	    this.naiveBayes = new JMenuItem("Naive Bayes");
	    this.cargado = false;
	    final JList<String> list = new JList<String>();
	  
	    //EL METODO INIT AGREGA LOS COMPONENTES A LA VENTANA
	    this.init();

		final JScrollPane scroll=new JScrollPane();
		scroll.setBounds(10, 11, 685, 285);
		contentPane.add(scroll);
		
		
		scrollPane_1.setBounds(392, 332, 303, 158);
		contentPane.add(scrollPane_1);


	itmCargarArchivo.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			
			JButton btnNewButton = new JButton("Frecuencia");
			JButton eliminarColumna = new JButton("Eliminar Columna");
			JButton eliminarFila = new JButton("Eliminar Fila");
			JButton estadistica = new JButton("Estadistica");
			JButton expresionR = new JButton("Expresion Regular");
			JButton valorMasComun = new JButton("Más Común");
			JButton remplazar = new JButton("Remplazar");
			
			textR = new JTextField();
			textR.setBounds(170, 320, 86, 25);
			contentPane.add(textR);
			textR.setColumns(10);
			
			textRemplazar = new JTextField();
			textRemplazar.setBounds(170, 360, 86, 25);
			contentPane.add(textRemplazar);
			textRemplazar.setColumns(10);

			btnNewButton.setBounds(720, 320, 119, 25);
			eliminarColumna.setBounds(720,360,145,25);
			eliminarFila.setBounds(720,400,130,25);
			estadistica.setBounds(720,440,120,25);
			expresionR.setBounds(10, 320, 150, 25);
			remplazar.setBounds(10, 360, 150, 25);
			valorMasComun.setBounds(10, 400, 150, 25);
			
			contentPane.add(eliminarColumna);
			contentPane.add(btnNewButton);
			contentPane.add(eliminarFila);
			contentPane.add(estadistica);
			contentPane.add(expresionR);
			contentPane.add(remplazar);
			contentPane.add(valorMasComun);
			
			eliminarColumna.setVisible(true);
			btnNewButton.setVisible(true);
			eliminarFila.setVisible(true);
			estadistica.setVisible(true);
			expresionR.setVisible(true);
			remplazar.setVisible(true);
			valorMasComun.setVisible(true);
			textR.setVisible(true);
			textRemplazar.setVisible(true);
			
			JFileChooser fc=new JFileChooser("");
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");

			fc.setFileFilter(filtro);

			//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
			int seleccion = fc.showOpenDialog(contentPane);

			//Si el usuario, pincha en aceptar
			if(seleccion==JFileChooser.APPROVE_OPTION)
			{
				File fichero=fc.getSelectedFile();
				try {
					
					//VARIABLES PARA LEER EL CSV E INSERCIÓN EN TABLAS
					dtm = new DefaultTableModel();
					table = new JTable(dtm);
					
					final DefaultTableModel dtmRegular = new DefaultTableModel();
					tableRegular = new JTable(dtmRegular);
					
					@SuppressWarnings("resource")
					CSVReader csvReader = new CSVReader(new FileReader(fichero));
					List<String[]> allData = csvReader.readAll();
					
					//ArrayList<String> atributos = new ArrayList<String>();

					
					//LISTA DE TIPOS A TOMAR
					
					
					list.setBackground(SystemColor.WHITE);
					list.setBounds(720, 11, 119, 229);
					
					DefaultListModel<String> modelo = new DefaultListModel<String>();
					list.setName("Atributos");
					contentPane.add(list);

					Object[] datos = new Object[allData.size()-1];
					
					int cont = 0,cont2=0;
					
					for(String[] data : allData)
					 {
						cont2=0;
						for(String s : data)
						{
							if(cont==0)
							{
								dtm.addColumn(s);//AÑADE UNA COLUMNA A LA TABLA
								cabezeras.add(s);
								modelo.addElement(s); //AÑADE UN ELEMENTO A LA LISTA
							}
							else
							{
								//atributos.add(s);
								datos[cont2]=s;
								cont2++;
							}
						
						 }
						 if(cont!=0)
							 dtm.addRow(datos);
						 
						 cont++;
					 }
					
					scroll.setViewportView(table);
					list.setModel(modelo);
					
					cargado = true;
					contentPane.repaint();
					//contentPane.validate();
					
				expresionR.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						DefaultTableModel dtm2 = new DefaultTableModel();
						expresionRegular(dtm2,list);
						
				}});
				
				estadistica.addActionListener(new ActionListener(){
	
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						if(list.getSelectedIndex()>-1){
								if(esEntero(list)){
									if(!estaVacio(list))
									{
										DefaultTableModel dtm2 = new DefaultTableModel();
										dtm2.addColumn("Estadistica");
										dtm2.addColumn("Resultado");
										
										media(dtm2,list);
										moda(dtm2,list);
										numeroMayor(dtm2,list);
										numeroMenor(dtm2,list);
										desviacionEstandar(dtm2,list);
										mediana(dtm2,list);
										
										tablaEstadisticas = new JTable(dtm2);
										scrollPane_1.setViewportView(tablaEstadisticas);
									}
									else
									{
										JOptionPane.showMessageDialog(null,"Hay celdas vacias, realiza limpieza de Datos!");
									}
								
								}
								else{
									JOptionPane.showMessageDialog(null,"No es numerico");
								}
						}
					
				}});
				
				remplazar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0)
					{
						String textoRemplazar = textRemplazar.getText();
						Object[] datos2 = new Object[allData.size()-1];

						int cont = 0, cont2=0;

						for (int i = dtm.getRowCount() - 1; i > -1; i--) {
	                        dtm.removeRow(i);
	                    }
						
						//allData.addAll(textoRemplazar.);
						
						allData.add(new String[5]);
						
						for(String[] dato: allData)
						{
							cont2=0;
							for(String s : dato)
							{	
								if(s.isEmpty() && cont2 == list.getSelectedIndex())
								{
									datos2[cont2] = textoRemplazar;
									dato[cont2] = textoRemplazar;
									allData.set(cont, dato);
								}
								else
								{
									datos2[cont2]=s;
								}
								cont2++;
							 }
							
							 
							if(cont!=0)
							{
								dtm.addRow(datos2);
							}	 
							 
							 cont++;
						}
						
						table.setModel(dtm);
						
						scroll.setViewportView(table);
						
					}
				});
				
				valorMasComun.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0)
					{
						ArrayList<String> moda = new ArrayList<String>();
						frecuencia(list);
						
						boolean hayMayoria = false;
						int numeroMayor= Integer.parseInt(tablaFrecuencias.getValueAt(0, 1).toString());
						for(int i=0;i<tablaFrecuencias.getRowCount();i++){
							if(Integer.parseInt(tablaFrecuencias.getValueAt(i, 1).toString()) > numeroMayor){
								numeroMayor = Integer.parseInt(tablaFrecuencias.getValueAt(i, 1).toString());	
								moda.add(tablaFrecuencias.getValueAt(i, 0).toString());
								hayMayoria = true;
							}
							
						}
						
						String textoRemplazar; 
						if(hayMayoria)
							textoRemplazar = moda.get(moda.size()-1);
						else
							textoRemplazar = tablaFrecuencias.getValueAt(0, 0).toString();
						
						Object[] datos2 = new Object[allData.size()-1];
						
						int cont = 0, cont2=0;

						for (int i = dtm.getRowCount() - 1; i > -1; i--) {
	                        dtm.removeRow(i);
	                    }
						
						limpiarFrecuencia();
						
						for(String[] dato: allData)
						{
							cont2=0;
							for(String s : dato)
							{	
								if(s.isEmpty() && cont2 == list.getSelectedIndex())
								{
									datos2[cont2] = textoRemplazar;
									dato[cont2] = textoRemplazar;
									allData.set(cont, dato);
								}
								else
								{
									datos2[cont2]=s;
								}
								cont2++;
							
							 }
							
							 
							if(cont!=0)
							{
								dtm.addRow(datos2);
							}	 
							 
							 cont++;
						}
						
						
						table.setModel(dtm);
						
						scroll.setViewportView(table);
					}
				});
					
				eliminarFila.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(table.getSelectedRow()>-1){
							int filaABorrar = table.getSelectedRow();
							
							DefaultTableModel modelo = (DefaultTableModel)table.getModel(); 
							modelo.removeRow(filaABorrar); 
							table.updateUI();
						}
					}
				});
					
				eliminarColumna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						if(list.getSelectedIndex()>-1){
							cabezeras.remove(list.getSelectedIndex());
							TableColumn columnaABorrar = table.getColumnModel().getColumn(list.getSelectedIndex());
							
							table.removeColumn(columnaABorrar);
							table.updateUI();
							actualizarList(list);
							
						}
					}
				 });
					
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
							
						//Object[] datos = new Object[table.getRowCount()-1];

						frecuencia(list);			
					}
				});
					
					
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	
	  correlacion.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  correlacion nuevaCorrelacion = new correlacion(cabezeras, table);
				  nuevaCorrelacion.setVisible(true);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
	  
	  transformacion.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  transformacion nuevaTransformacion = new transformacion(cabezeras, table);
				  nuevaTransformacion.setVisible(true);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
	  
	  menuLevenshtain.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  levenshtein nuevoLevenshtein = new levenshtein(cabezeras, dtm, table);
				  nuevoLevenshtein.setVisible(true);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
	  
	  zeroR.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  zeroR nuevo = new zeroR(cabezeras, table);
				  nuevo.setVisible(true);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
	  
	  oneR.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
	  
	  naiveBayes.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent arg0){
			  if(cargado){
				  
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"No hay un archivo cargado!");
			  }
			  
		  }
	  });
		
	  itmGuardarArchivo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(cargado)
	    		{
	    			JFileChooser fg = new JFileChooser();
		 			 
		 			int seleccion = fg.showSaveDialog(contentPane);
		 			 
		 			 if(seleccion == JFileChooser.APPROVE_OPTION)
		 			 {
		 				try {
		 					File fichero = fg.getSelectedFile();
		 					
		 					
		 					
	    				    FileWriter fw = new FileWriter(fichero);
	    				    
	    				    System.out.println(table.getColumnCount());
	  					
		  					for(int i=0;i<table.getColumnCount();i++){
		  						
		  						if( i == table.getColumnCount()-1)
		  						{
		  							fw.append(table.getColumnName(i));
		  						}
		  						else
		  						{
		  							fw.append(table.getColumnName(i)).append(",");
		  						}
		  					
		  					}
		  					fw.append("\n");
		  					
		  					int cantidadColumnas = table.getColumnCount();
		  					int cantidadFilas= table.getRowCount();
		  					for(int i = 0; i < cantidadFilas; i++)
		  					{
		  						
		  						for(int j = 0; j < cantidadColumnas; j++)
		  						{
		  							fw.append((CharSequence) table.getValueAt(i,j)).append(",");
		  						}
		  						
		  						fw.append("\n");
		  							
		  					}
		  					fw.flush();
		  					fw.close();
	    			  
					
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 		}
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null,"No hay un archivo para guardar!");
	    		}
	 			 
	    		  
	    	}
	    });
 }
	
	public void expresionRegular(DefaultTableModel dtm2, JList<String> list){
		String Exp=	textR.getText();
		final ArrayList<String> frecuency = new ArrayList<String>();
		frecuency.clear();
		String []nombreColumnas = {(String) list.getSelectedValue()};
		System.out.print(Exp);
		
		for(int i = 0; i < table.getRowCount(); i++)
		{
			frecuency.add(table.getValueAt(i, list.getSelectedIndex()).toString());
		}
		
		int columnas = 1;
		int filas = frecuency.size();
		
		Object Matrix[][] = new Object[filas][columnas];
		
		int i = 0;
		
		for(String data : frecuency)
		{
			if(Pattern.matches(Exp, data) == false)
			{
				Matrix[i][0] = data;
				i++;
			}
			
		}
		
		DefaultTableModel dtmRegular = new DefaultTableModel(Matrix, nombreColumnas);

		tableRegular = new JTable(dtmRegular);
	
		scrollPane_1.setViewportView(tableRegular);
		
		}

	public void frecuencia(JList<String> list)
	{
		final ArrayList<frecuencia> frecuency = new ArrayList<frecuencia>();
		frecuency.clear();
		String []nombreColumnas = {(String) list.getSelectedValue(), "Cantidad"};
	
		
		for(int i = 0; i < table.getRowCount(); i++)
		{
			frecuencia nuevo = new frecuencia();
			if( i == 0)
			{
				nuevo.tipoDato = table.getValueAt(i, list.getSelectedIndex()).toString();
				nuevo.numero = 1;
				frecuency.add(nuevo);
			}
			else
			{
				boolean repetido = false;
				for(int j = 0; j < frecuency.size(); j++)
				{
					String valorEnTabla = table.getValueAt(i, list.getSelectedIndex()).toString();
					if( valorEnTabla.equals(frecuency.get(j).tipoDato.toString()))
					{
						nuevo.tipoDato = valorEnTabla;
						nuevo.numero = frecuency.get(j).numero + 1;
						frecuency.set(j, nuevo);
						
						repetido = true;
					}
				}
				if(repetido == false)
				{
					nuevo.tipoDato = (String) table.getValueAt(i, list.getSelectedIndex());
					nuevo.numero = 1;
					frecuency.add(nuevo);
				}
			}
			
		}
		
		int columnas = 2;
		int filas = frecuency.size();
		
		Object Matrix[][] = new Object[filas][columnas];
		
		for(int i = 0; i < columnas; i++)
		{
			for(int j = 0; j < filas; j++)
			{
				Matrix[j][i] = new Object();
				if(i == 0)
				{
					Matrix[j][i] = frecuency.get(j).tipoDato.toString();
				}
				else
				{
					Matrix[j][i] = frecuency.get(j).numero;
				}
			}
		}
		
		DefaultTableModel dtm2 = new DefaultTableModel(Matrix, nombreColumnas);

		tablaFrecuencias = new JTable(dtm2);
		
		scrollPane_1.setViewportView(tablaFrecuencias);
	}	
	
	public void limpiarFrecuencia()
	{
		DefaultTableModel dtm2 = new DefaultTableModel();
		
		tablaFrecuencias = new JTable(dtm2);
		
		scrollPane_1.setViewportView(tablaFrecuencias);
	}
	
	public void media(DefaultTableModel dtm2, JList<String> list){
		
		int media=0;
		for(int i=0;i<table.getRowCount();i++){
			media+= Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString());
		}
		
		media/=table.getRowCount();
		dtm2.addRow(new Object []{"Media",media});
	}
	
	public void moda(DefaultTableModel dtm2, JList<String> list){
		
		ArrayList<String> moda = new ArrayList<String>();
		frecuencia(list);
		int numeroMayor= Integer.parseInt(tablaFrecuencias.getValueAt(0, 1).toString());
		for(int i=0;i<tablaFrecuencias.getRowCount();i++){
			if(Integer.parseInt(tablaFrecuencias.getValueAt(i, 1).toString()) > numeroMayor){
				numeroMayor = Integer.parseInt(tablaFrecuencias.getValueAt(i, 1).toString());	
				moda.add(tablaFrecuencias.getValueAt(i, 0).toString());
			}
			
		}
		dtm2.addRow(new Object[]{"Moda",moda.get(moda.size()-1)});

		}
	
	public void desviacionEstandar(DefaultTableModel dtm2, JList<String> list)
	{
		int media=0;
		for(int i=0;i<table.getRowCount();i++){
			media+= Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString());
		}
		
		media/=table.getRowCount();
		
		double sumatoria;
		double desviacion;
		double redondeo;
		double varianza = 0;
		for(int i = 0; i < dtm2.getRowCount(); i++)
		{
			sumatoria = 
			Math.pow(Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString()) - media, 2);
			varianza = varianza + sumatoria;
		}
		varianza = varianza / (table.getRowCount()-1);
		
		desviacion = Math.sqrt(varianza);
		redondeo = Math.rint(desviacion*100)/100;
		dtm2.addRow(new Object []{"Desviación",redondeo});
	}
	
	public boolean esEntero(JList<String> list){
		
			for(int i=0;i<table.getRowCount();i++){
						
						if(!table.getValueAt(i, list.getSelectedIndex()).toString().matches("\\d*")){
							return false;
						}
					}
					return true;
	}
	
	public boolean estaVacio(JList<String> list)
	{
		for(int i=0;i<table.getRowCount();i++){
			
			if(table.getValueAt(i, list.getSelectedIndex()).toString().matches("\\s*")){
				System.out.println(table.getValueAt(i, list.getSelectedIndex()));
				return true;
			}
		}

		return false;
	}
	
	public void numeroMayor(DefaultTableModel dtm2,JList<String> list){
		int mayor=0;
		mayor = Integer.parseInt(table.getValueAt(0, list.getSelectedIndex()).toString());
		
		
		for(int i=0;i<table.getRowCount();i++){
			if(Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString())>mayor){
				mayor= Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString());
				
				
			}
		}
		dtm2.addRow(new Object[]{"Numero Mayor",mayor} );
		
	}
	
	public void numeroMenor(DefaultTableModel dtm2,JList<String> list){
		int menor=0;
		menor = Integer.parseInt(table.getValueAt(0, list.getSelectedIndex()).toString());
		for(int i=0;i<table.getRowCount();i++){
		if(menor>Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString())){
			menor = Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString());
		}
		}
		dtm2.addRow(new Object[]{"Numero Menor",menor} );
	}
	
	public void mediana(DefaultTableModel dtm2, JList<String> list)
	{
		double mediana;
		int MAX = table.getRowCount();
		double[] numeros = new double[MAX];
		for(int i = 0; i < MAX; i++)
		{
			numeros[i] = Integer.parseInt(table.getValueAt(i, list.getSelectedIndex()).toString());
		}
	    
	    for(int i = 0; i < MAX - 1; i++)
        {
            for(int j = 0; j < numeros.length - 1; j++)
            {
                if (numeros[j] < numeros[j + 1])
                {
                    double tmp = numeros[j+1];
                    numeros[j+1] = numeros[j];
                    numeros[j] = tmp;
                }
            }
        }
	    
	    mediana = numeros[MAX/2];
	    dtm2.addRow(new Object[]{"Mediana",mediana} );
	    
	}
	
	public void actualizarList(JList<String> list){
		
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		for(int i=0;i<table.getColumnCount();i++){
			modelo.addElement(table.getColumnName(i));
		}
		list.setModel(modelo);
	}
	
	public void remplazarValor(DefaultTableModel dtm2, JList<String> list)
	{
		/*Object Matrix[][] = new Object[table.getRowCount()][table.getColumnCount()];
		
		if( columnaSeleccionada > -1)
		{
			if(textoRemplazar.length() != 0)
			{
				for(int i = 0; i < maxColumnas; i++)
				{
					for(int j = 0; j < maxFilas; j++)
					{
						if(j == columnaSeleccionada)
						{
							if(table.getValueAt(i, j).equals(""))
							{
								Matrix[i][j] = textoRemplazar;
							}
							else
							{
								Matrix[i][j] = table.getValueAt(j, i);;
							}
						}
						else
						{
							Matrix[i][j] = table.getValueAt(j, i);
						}
					}
				}
				campoVacio = false;
			}
		}
		
		if(!campoVacio)
			System.out.println("No hay Campos Vacios");*/
	
			
	}
	
	public void init() {
	    /* le digo que al presionar el boton "X" el programa se detenga */
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     
	    //MENU PARA CARGAR Y GUARDAR ARCHIVOS
	    this.barraMenu.add(this.menuVer);
	        this.menuVer.add(this.itmCargarArchivo);
	        this.menuVer.add(this.itmGuardarArchivo);
	    //MENU PARA PRE-PROCESAMIENTO
	    this.barraMenu.add(this.menuPreproceso);
	        this.menuPreproceso.add(this.menuLimpieza);
	            
	    this.barraMenu.add(this.menuDataM);
	    	this.menuDataM.add(this.correlacion);
	    	this.menuDataM.add(this.transformacion);
	    	this.menuDataM.add(this.menuLevenshtain);
	    
	    this.barraMenu.add(this.menuClasificacion);
	    	this.menuClasificacion.add(this.zeroR);
	    	this.menuClasificacion.add(this.oneR);
	    	this.menuClasificacion.add(this.naiveBayes);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	       
	    this.setJMenuBar(this.barraMenu);

	    this.setLocationRelativeTo(null);

	    this.setVisible(true);

	}
}