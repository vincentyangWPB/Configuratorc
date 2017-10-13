package ExcelGUI;
import ExcelReader.ExcelDriver;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class ExcelGUI {

	private JFrame frame;
	private JTextField textField;
	private JList alterationList;
	private JList tableList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcelGUI window = new ExcelGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcelGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 522, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnInsert = new JButton("Enter");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String originalString = textField.getText();
				String afterDirectory= "";
				for(int i = 0; i < originalString.length(); i++) {
					if(originalString.charAt(i) == '\\' && originalString.charAt(i+1) == '\\') {
						afterDirectory+=originalString.charAt(i);
					} else if(originalString.charAt(i) == '\\') {
						afterDirectory += originalString.charAt(i) + "\\";
					} else {
						afterDirectory += originalString.charAt(i);
					}
				}
				ExcelDriver myExcel = new ExcelDriver(afterDirectory);
				String myAlteration = alterationList.getSelectedValues().toString();
				String myTable = tableList.getSelectedValue().toString();
				
			}
			public void dataAlterationSwitch(String myAlteration, String myTable) {
				switch(myTable) {
				case "Designs": 
					break;
				case "Colors":
					break;
				case "Finish Methods":
					break;
				case "Special Features":
					break;
				case "Top Colors" :
					break;
				case "All" :
					break;
				}
			}
		});
		btnInsert.setBounds(189, 391, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		textField = new JTextField();
		textField.setBounds(39, 59, 422, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblExcelFileDirectory = new JLabel("Excel File Directory");
		lblExcelFileDirectory.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblExcelFileDirectory.setBounds(189, 7, 181, 41);
		frame.getContentPane().add(lblExcelFileDirectory);
		
		alterationList = new JList();
		alterationList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		alterationList.setToolTipText("Alteration");
		alterationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		alterationList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Insert", "Delete", "Update"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		alterationList.setBounds(39, 157, 94, 68);
		frame.getContentPane().add(alterationList);
		
		JLabel alterationLabel = new JLabel("Data Alteration");
		alterationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		alterationLabel.setBounds(39, 108, 144, 23);
		frame.getContentPane().add(alterationLabel);
		
		JLabel tableLabel = new JLabel("Select Tables");
		tableLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tableLabel.setBounds(325, 107, 115, 23);
		frame.getContentPane().add(tableLabel);
		
		tableList = new JList();
		tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Designs", "Colors", "Finish Method", "Special Features", "Top Colors", "All"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		tableList.setBounds(325, 161, 106, 132);
		frame.getContentPane().add(tableList);
	}
}
