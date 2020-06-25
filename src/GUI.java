import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 *  @author: Wirkus Tomasz
 *	@dateUpdate: 24.06.2020 
 * 
 */

public class GUI {
	
	private JFrame frame;
	private JTextField numberField;
	private JLabel dialogLabel, dialogLabel2, enterNumberLabel, positionLabel, rowLabel, columnLabel, resultLabel;
	private JTable table;
	private JTextArea textArea;
	private JList<String> list;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemAdd, menuItemZeros, menuItemSave, menuItemExit, menuItemHelp, menuItemAuthor;
	private JToolBar toolBar;
	private JPanel panel;
	private JButton addButton, zerosButton, fileButton;

	//main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	public GUI() {
		init();
	}
	private void init() {
		frame = new JFrame("GUI APP");
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(20, 20, 57));
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		menuItemAdd = new JMenuItem("Add");		//EVENTS ARE DESCRIBED ON THE END OF THE CLASS
		menuItemZeros = new JMenuItem("Zeros");
		menuItemSave = new JMenuItem("Save");
		menuItemHelp = new JMenuItem("Help");	
		menuItemAuthor = new JMenuItem("Author");	
		menuItemExit = new JMenuItem("Exit");
		
		menu.add(menuItemAdd);
		menu.add(menuItemZeros);
		menu.add(menuItemSave);
		menu.add(menuItemHelp);
		menu.add(menuItemAuthor);
		menu.add(menuItemExit);
		frame.setJMenuBar(menuBar);
		
		//toolbar
		toolBar = new JToolBar();
	    toolBar.setRollover(true);
	    JButton toolBarAdd = new JButton(new ImageIcon("img/add.png"));
	    JButton toolBarZeros = new JButton(new ImageIcon("img/zeros.png"));
	    JButton toolBarSave= new JButton(new ImageIcon("img/save.png"));
	    JButton toolBarHelp = new JButton(new ImageIcon("img/help.png"));
	    JButton toolBarAuthor = new JButton(new ImageIcon("img/author.png"));
	    JButton toolBarExit = new JButton(new ImageIcon("img/exit.png"));
	    
	    //seperators to align right which i couldn't achieve otherwise
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator(); toolBar.addSeparator();
	    	    
	    toolBar.add(toolBarAdd);
	    toolBar.add(toolBarZeros);
	    toolBar.add(toolBarSave);
	    toolBar.add(toolBarHelp);
	    toolBar.add(toolBarAuthor);
	    toolBar.add(toolBarExit); 
	    menuBar.add(toolBar);
		
		panel = new JPanel();
		panel.setForeground(new Color(151, 151, 151));
		panel.setBounds(0, 0, 800, 20); 
		frame.getContentPane().add(panel);
		
		enterNumberLabel = new JLabel("Enter number value:");
		enterNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		enterNumberLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		enterNumberLabel.setForeground(new Color(221, 221, 221));
		enterNumberLabel.setBounds(35, 50, 140, 20);
		frame.getContentPane().add(enterNumberLabel);
		
		numberField = new JTextField("0");
		numberField.setHorizontalAlignment(SwingConstants.CENTER);
		numberField.setBackground(Color.LIGHT_GRAY);
		numberField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		numberField.setColumns(10);
		numberField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		numberField.setBounds(200, 50, 35, 25);
		frame.getContentPane().add(numberField);
		
		positionLabel = new JLabel("Set position in the table:");
		positionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		positionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		positionLabel.setForeground(new Color(221, 221, 221));
		positionLabel.setBounds(35, 100, 140, 20);
		frame.getContentPane().add(positionLabel);
		
		rowLabel = new JLabel("Row:");
		rowLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rowLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rowLabel.setForeground(new Color(221, 221, 221));
		rowLabel.setBounds(35, 130, 40, 20);
		frame.getContentPane().add(rowLabel);
		
		columnLabel = new JLabel("Column:");
		columnLabel.setHorizontalAlignment(SwingConstants.LEFT);
		columnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		columnLabel.setForeground(new Color(221, 221, 221));
		columnLabel.setBounds(135, 130, 60, 20);
		frame.getContentPane().add(columnLabel);
		
		//spinners	
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 5, 1); //default value,lower bound,upper bound,increment by
		SpinnerModel sm2 = new SpinnerNumberModel(1, 1, 5, 1); //default value,lower bound,upper bound,increment by
		
		JSpinner column = new JSpinner(sm);
		frame.getContentPane().add(column);
		column.setBackground(Color.LIGHT_GRAY);
		column.setBounds(75, 130, 35, 25);	
		
		JSpinner row = new JSpinner(sm2);
		frame.getContentPane().add(row);
		row.setBackground(Color.LIGHT_GRAY);
		row.setBounds(200, 130, 35, 25);
		
		
		//table		
		table = new JTable(5, 5); 
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table.setBounds(35, 180, 200, 150);
		table.setRowMargin(1);
		table.setRowHeight(30);
		frame.getContentPane().add(table);
		zeros(); //user friendly 
		
		//centalizing array items
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      
    	for(int x=0;x<5;x++){
            table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
           }
        
		//buttons
		addButton = new JButton("Add");
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		addButton.setMargin(new Insets(1, 1, 1, 1));
		addButton.setBounds(35, 360, 60, 40);
		frame.getContentPane().add(addButton);
		
		zerosButton = new JButton("Zeros");
		zerosButton.setMargin(new Insets(1, 1, 1, 1));
		zerosButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		zerosButton.setForeground(Color.BLACK);
		zerosButton.setBackground(Color.LIGHT_GRAY);
		zerosButton.setBounds(105, 360, 60, 40);
		frame.getContentPane().add(zerosButton);
		
		fileButton = new JButton("Save");
		fileButton.setMargin(new Insets(1, 1, 1, 1));
		fileButton.setForeground(Color.BLACK);
		fileButton.setBackground(Color.LIGHT_GRAY);
		fileButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		fileButton.setBounds(175, 360, 60, 40);
		frame.getContentPane().add(fileButton);
				
		//RIGHT SIDE OF APP
		resultLabel = new JLabel("Select an option:");
		resultLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		resultLabel.setForeground(new Color(221, 221, 221));
		resultLabel.setBounds(550, 40, 200, 42);
		frame.getContentPane().add(resultLabel);
		
		String calculations[] = {
				"1. Sum of the elements",
				"2. Average of the elements",
				"3. Min and MAX values"
			};
		list = new JList<String>(calculations);
		list.setBounds(550, 100, 200, 60);
		list.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(list);
		
	

		textArea = new JTextArea();
		textArea.setBounds(550, 175, 200, 155);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frame.getContentPane().add(textArea);
	

	ImageIcon icon = new ImageIcon("img/java.png");
    JLabel centerImg = new JLabel(icon);
		centerImg.setBounds(235, 60, 310, 308);
	

		frame.getContentPane().add(centerImg);
		
		//EVENTS
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
		menuItemAuthor.addActionListener(new ActionListener() {
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
			}
		});
		zerosButton.addActionListener(new ActionListener() {
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
			    		textArea.setText("\n Sum of the elements in \n array equals " + result );
			    	}
			    	if(list.getSelectedIndex() == 1) {
			    		float result = 0;
			    		for(int i = 0; i < 5; i++) {
							for(int j = 0; j < 5; j++) {
								result = result +  (int) table.getValueAt(i, j);
								
							}
						}
			    		textArea.setForeground(Color.BLACK);
			    		textArea.setText("\n Average of the elements in \n array equals " + result/25 );    		  
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
			    		textArea.setText("\n MAX value: " + resultMAX + "\n MIN Value: " + resultMIN );  
			    	}
		    		
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		JOptionPane.showMessageDialog(null, "Array cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
		    		
		    	}    	
		    }
		  });	
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
}
