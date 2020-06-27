import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;



/**
 * 
 *  @author: Wirkus Tomasz
 *	@dateUpdate: 28.06.2020 
 * 
 */

public class GUI implements DateListener {
	
	private JFrame frame;
	private JTable table;
	private TableModel tableModel;
	private JTextField numberField;
	private JLabel dialogLabel, dialogLabel2, enterNumberLabel, positionLabel, rowLabel, columnLabel, resultLabel;

	private JTextArea textArea, dateArea;
	private JList<String> list;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuHelp;
	private JMenuItem menuItemAdd, menuItemNew, menuItemOpen, menuItemPrint, menuItemZeros, menuItemSave, menuItemExit, menuItemHelp, menuItemAuthor;
	private JToolBar toolBar;
	private JPanel panel;
	private JButton addButton, zerosButton, fileButton;
	private JCalendarCombo calendar;


	//main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					
					DefaultTipModel tips = new DefaultTipModel();
					tips.add(new DefaultTip("tip1", "  You can save data in the table at any time to a txt file"));
					tips.add(new DefaultTip("tip2", "  I recommend you to test out diagrams funcionality to analyze data"));
					JTipOfTheDay tipOfTheDay = new JTipOfTheDay(tips);
					tipOfTheDay.showDialog(window.frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public GUI() {
		init();
		
		tableModel = new TableModel(5, 5);
	}
	private void init() {
		frame = new JFrame("GUI APP");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(20, 20, 57));
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		//dialog author
		JDialog d = new JDialog(frame , "Java project author", true); 
		d.setLayout( new FlowLayout() );  
		d.setSize(300,100); 
		d.setLocationRelativeTo(null);
		dialogLabel = new JLabel("Application made by Wirkus Tomasz");
        d.getContentPane().add(dialogLabel);
        
        //dialog help
  		JDialog d2 = new JDialog(frame , "Java project help", true); 
  		d2.setLayout( new FlowLayout() );  
  		d2.setSize(300,100); 
  		d2.setLocationRelativeTo(null);
  		dialogLabel2 = new JLabel("Its Java, you should get some help");
        d2.getContentPane().add(dialogLabel2);
		
        //menuBar
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuHelp = new JMenu("Help");
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);
		
		menuItemNew = new JMenuItem("New");
		menuItemOpen = new JMenuItem("Open");
		menuItemPrint = new JMenuItem("Print");
		menuItemAdd = new JMenuItem("Add");		//EVENTS ARE DESCRIBED ON THE END OF THE CLASS
		menuItemZeros = new JMenuItem("Zeros");
		menuItemSave = new JMenuItem("Save");
		menuItemHelp = new JMenuItem("Help");	
		menuItemAuthor = new JMenuItem("Author");	
		menuItemExit = new JMenuItem("Exit");
		
		
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemPrint);
		menuFile.add(menuItemExit);
		menuEdit.add(menuItemAdd);
		menuEdit.add(menuItemZeros);
		menuEdit.add(menuItemSave);
		menuHelp.add(menuItemHelp);
		menuHelp.add(menuItemAuthor);
		
		frame.setJMenuBar(menuBar);
		
		//toolbar
		toolBar = new JToolBar();
	    toolBar.setRollover(false);
	    toolBar.setFloatable(false);
	    toolBar.setBounds(0, 0, 800, 50);
	    JButton toolBarAdd = new JButton(new ImageIcon("img/add.png"));
	    JButton toolBarZeros = new JButton(new ImageIcon("img/zeros.png"));
	    JButton toolBarSave= new JButton(new ImageIcon("img/save.png"));
	    JButton toolBarHelp = new JButton(new ImageIcon("img/help.png"));
	    JButton toolBarAuthor = new JButton(new ImageIcon("img/author.png"));
	    JButton toolBarExit = new JButton(new ImageIcon("img/exit.png"));
 	    
	    toolBar.add(toolBarAdd);
	    toolBar.add(toolBarZeros);
	    toolBar.add(toolBarSave);
	    toolBar.add(toolBarHelp);
	    toolBar.add(toolBarAuthor);
	    toolBar.add(toolBarExit); 
	    	
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setForeground(new Color(151, 151, 151));
		panel.setBounds(0, 0, 800, 55); 
		
		panel.add(toolBar);
		frame.getContentPane().add(panel);
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		calendar = new JCalendarCombo();
		calendar.setBackground(Color.white);;	
		calendar.setDateFormat(df);
		calendar.addDateListener(this);		
		calendar.setBounds(555, 80, 100, 20);
		
		dateArea = new JTextArea();
		dateArea.setBounds(665, 80, 100, 20);
		dateArea.setEditable(false);
		dateArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateArea.setColumns(10);
		dateArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(null), BorderFactory.createEmptyBorder(1, 3, 1, 3)));
		
		frame.getContentPane().add(calendar);
		frame.getContentPane().add(dateArea);
		
		enterNumberLabel = new JLabel("Enter number value:");
		enterNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		enterNumberLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		enterNumberLabel.setForeground(new Color(221, 221, 221));
		enterNumberLabel.setBounds(265, 80, 140, 20);
		frame.getContentPane().add(enterNumberLabel);
		
		numberField = new JTextField("0");
		numberField.setHorizontalAlignment(SwingConstants.CENTER);
		numberField.setBackground(Color.LIGHT_GRAY);
		numberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		numberField.setColumns(10);
		numberField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		numberField.setBounds(410, 80, 80, 22);
		frame.getContentPane().add(numberField);
		
		rowLabel = new JLabel("Row:");
		rowLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rowLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rowLabel.setForeground(new Color(221, 221, 221));
		rowLabel.setBounds(265, 120, 40, 20);
		frame.getContentPane().add(rowLabel);
		
		columnLabel = new JLabel("Column:");
		columnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		columnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		columnLabel.setForeground(new Color(221, 221, 221));
		columnLabel.setBounds(377, 120, 60, 20);
		frame.getContentPane().add(columnLabel);
		
		//spinners	
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 5, 1); //default value,lower bound,upper bound,increment by
		SpinnerModel sm2 = new SpinnerNumberModel(1, 1, 5, 1); //default value,lower bound,upper bound,increment by
		
		JSpinner column = new JSpinner(sm);
		frame.getContentPane().add(column);
		column.setBackground(Color.LIGHT_GRAY);
		column.setBounds(305, 120, 50, 25);	
		
		JSpinner row = new JSpinner(sm2);
		frame.getContentPane().add(row);
		row.setBackground(Color.LIGHT_GRAY);
		row.setBounds(442, 120, 50, 25);
		
		
		//table		
		table = new JTable(5, 5); 
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setBounds(265, 180, 500, 200);
		table.setRowMargin(1);
		table.setRowHeight(40);
		frame.getContentPane().add(table);
		zeros(); //user friendly 
		
		//centalizing array items
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      
    	for(int x=0;x<5;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
           }
        	
		String calculations[] = {
				"1. Sum of the elements",
				"2. Average of the elements",
				"3. Min and MAX values"
			};
		list = new JList<String>(calculations);
		list.setBounds(20, 460, 180, 60);
		list.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(list);
		
		textArea = new JTextArea();
		textArea.setBounds(265, 440, 500, 80);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(textArea);
	
		//JTaskPane
		JTaskPane taskPane = new JTaskPane();
		taskPane.setBackground(new Color(20, 20, 57));
		taskPane.setBounds(10,60,200,400);
		JTaskPaneGroup fileGroup = new JTaskPaneGroup();
		fileGroup.setTitle("File");
		JButton zerosButton = new JButton("New table", new ImageIcon("img\\save_small.gif"));
		zerosButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JButton btnChart = new JButton("Draw chart", new ImageIcon("img\\save_small.gif"));
		btnChart.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		fileGroup.add(zerosButton);
		fileGroup.add(btnChart);
	
		JTaskPaneGroup fileGroup2 = new JTaskPaneGroup();
		fileGroup2.setTitle("Edit");
		JButton addButton = new JButton("Add", new ImageIcon("img\\save_small.gif"));
		addButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JButton fileButton = new JButton("Save table", new ImageIcon("img\\open_small.gif"));
		fileButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JButton zerosButton2 = new JButton("Zeros table", new ImageIcon("img\\door_small.gif"));
		zerosButton2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		fileGroup2.add(addButton);
		fileGroup2.add(fileButton);
		fileGroup2.add(zerosButton2);
		
		JTaskPaneGroup fileGroup3 = new JTaskPaneGroup();
		fileGroup3.setTitle("Help");
		JButton tableItemHelp = new JButton("Help", new ImageIcon("img\\save_small.gif"));
		tableItemHelp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		JButton tableItemAuthor = new JButton("About author", new ImageIcon("img\\open_small.gif"));
		tableItemAuthor.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		fileGroup3.add(tableItemHelp);
		fileGroup3.add(tableItemAuthor);
		
		taskPane.add(fileGroup);
		taskPane.add(fileGroup2);
		taskPane.add(fileGroup3);
		
		frame.getContentPane().add(taskPane);
		
		
		//EVENTS
		btnChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawChart();
			}
		});
		menuItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add(row, column);             
			}
		});
		menuItemZeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeros();             
			}
		});
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();             
			}
		});
		menuItemHelp.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  d2.setVisible(true); 
		      }
		    });
		tableItemHelp.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  d2.setVisible(true); 
		      }
		    });
		menuItemAuthor.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  d.setVisible(true); 
		      }
		    });
		
		tableItemAuthor.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		    	  d.setVisible(true); 
		      }
		    });
		menuItemExit.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ev) {
		        System.exit(0);
		      }
		    });
		
		toolBarAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add(row, column);             
			}
		});
		toolBarZeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeros();             
			}
		});
		toolBarSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();            
			}
		});
		toolBarHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				d2.setVisible(true);            
			}
		});
		toolBarAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				d.setVisible(true);           
			}
		});
		toolBarExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);            
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add(row, column); 
				tableModel.updateValueAt((int) row.getValue() -1,(int) column.getValue() -1, Integer.parseInt(numberField.getText()));
				
	            
			}
		});
		zerosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				zeros();
			
			}
		});		
		zerosButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				zeros();
			
			}
		});
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				save();
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent evt) {
		    	textArea.setText("");    	
		    	try {	
		    		if(list.getSelectedIndex() == 0) {
			    		
			    		int result = 0;
			    		for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) {
								result += (int) table.getValueAt(i, j);				
							}
						}
			    		textArea.setForeground(Color.BLACK);
			    		textArea.setText("\n   Sum of the elements in array equals " + result );
			    	}
			    	if(list.getSelectedIndex() == 1) {
			    		float result = 0;
			    		for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) {
								result = result +  (int) table.getValueAt(i, j);
								
							}
						}
			    		textArea.setForeground(Color.BLACK);
			    		DecimalFormat df = new DecimalFormat("#.###");
			    		textArea.setText("\n   Average of the elements in array equals " + df.format(result/25) );    		  
			    	}
			    	if(list.getSelectedIndex() == 2) {
			    		int resultMAX = (int) table.getValueAt(0, 0);
			    		int resultMIN = (int) table.getValueAt(0, 0);
			    		for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) {							
								if((int) table.getValueAt(i, j) > resultMAX) {
									resultMAX = (int) table.getValueAt(i, j);
								}
								if((int) table.getValueAt(i, j) < resultMIN) {
									resultMIN = (int) table.getValueAt(i, j);
								}								
							}
						}
			    		textArea.setForeground(Color.BLACK);
			    		textArea.setText("\n   MAX value: " + resultMAX + " MIN Value: " + resultMIN );  
			    	}
		    		
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		JOptionPane.showMessageDialog(null, "Array cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		    		
		    	}    	
		    }
		  });

	}

	private void drawChart() {
		if (tableModel.IsTableFilledOnlyWithZeros()) {
			UIManager UI = new UIManager();
			UI.put("OptionPane.background", new ColorUIResource(244, 247, 252));
			UI.put("Panel.background", new ColorUIResource(244, 247, 252));
	        int value = JOptionPane.showOptionDialog(null,
	                "Narysowanie tabeli jest mozliwe tylko  gdy posiada ona co najmniej jedna wartosc rozna od 0",
	                "Tabela jest pusta",
	                JOptionPane.YES_OPTION,
	                JOptionPane.WARNING_MESSAGE,
	                null,
	                new String[] { "Ok" },
	                "Ok");
	        return;
		}
		int number = tableModel.getSum();
		System.out.println(number);
		if (number < 1) number = 1;
		int amount = tableModel.getRowCount() * tableModel.getColumnCount();
		double[] values = new double[amount];
		int count = 0;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				values[count++] = tableModel.getValue(i, j);
			}
		}
		HistogramDataset dataset = new HistogramDataset();
		dataset.addSeries("Histogram", values, number);
		JFreeChart chart = ChartFactory.createHistogram("Histogram pionowy", "Liczba elementów", "Wartości elementów", 
				dataset, PlotOrientation.VERTICAL, false, false, false);
		ChartFrame frame2 = new ChartFrame("Histogram pionowy", chart);
		//frame2.setLocationRelativeTo(null);
		frame2.pack();
		frame2.setSize(new Dimension(600, 400));
		frame2.setVisible(true);
	}
	private void add(JSpinner row, JSpinner column) {

		try {
			int number = Integer.parseInt(numberField.getText());
			int sRow = (int) row.getValue() -1;
            int sCol = (int) column.getValue() -1;
            
            table.setValueAt(number, sCol, sRow);
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " Please enter an integer", "Error", JOptionPane.ERROR_MESSAGE);
		}                
	}
	private void zeros() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				table.setValueAt(0, i, j);
			}
		}
	}
	private void save() {
		try {
	        FileWriter fw = new FileWriter("data/array.txt");
	        for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 5; j++) {
	             
	                    fw.write((int) table.getValueAt(i, j) + ",");
	                      
	            }
	            fw.write("\n");
	        }
	        JOptionPane.showMessageDialog(null, "\n Saved correctly.", "Error", JOptionPane.INFORMATION_MESSAGE);
	        fw.write("\n");
	        fw.flush();
	    } catch (IOException e) {
	    	JOptionPane.showMessageDialog(null, "\n There was an error with saving process", "Error", JOptionPane.ERROR_MESSAGE);	    	  
	    }
	}
	/**
	 * Nadpisana metoda zmiany daty kalendarza programu
	 */
	@Override
	public void dateChanged(DateEvent arg0) {
		String dateAsString = "";
		String yearAsString = "";
		String monthAsString = "";
		String dayAsString = "";
		
		java.util.Date date = calendar.getDate();
		int year = date.getYear()+1900;
		int month = date.getMonth()+1;
		int day = date.getDate();
		
		if(month<10)
			monthAsString = "0" + String.valueOf(month);
		else
			monthAsString = String.valueOf(month);
		
		if(day<10)
			dayAsString = "0" + String.valueOf(day);
		else
			dayAsString = String.valueOf(day);
		
		dateAsString = String.valueOf(year) + "-" + monthAsString + "-" + dayAsString;
		
		dateArea.setText(dateAsString);
	}
}
