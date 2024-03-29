package gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Student Name:	Marcin Rusiecki
// Student ID:		C00263263
// Date:			21/02/2022 

/**
 * This class creats the panel for the user dashboard that contains all product from database which user can buy.
 * @author Marcin Rusiecki
 * @version 1.0
 */
public class StorePanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JTable storeTable;
	private JTextField txtItemId;
	private JTextField txtItemName;
	private JTextField txtTotalPrice;
	private JTextField txtItemPrice;
	private JTextField txtItemStock;

	/**
	 * Builds the panel with all products information from database.
	 */
	public StorePanel(String email)
	{
		setBounds(0, 0, 625, 493);
		setBackground(new Color(102, 153, 204));
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("STORE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 0, 605, 40);
		add(lblNewLabel);
		
		JScrollPane scrollPaneStoreTable = new JScrollPane();
		scrollPaneStoreTable.setBounds(10, 40, 605, 177);
		add(scrollPaneStoreTable);
		
		storeTable = new JTable();
		storeTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model = (DefaultTableModel) storeTable.getModel();
				int rowSelectedIndex = storeTable.getSelectedRow();
				
				txtItemId.setText(model.getValueAt(rowSelectedIndex,0).toString());
				txtItemName.setText(model.getValueAt(rowSelectedIndex,1).toString());
				txtItemPrice.setText(model.getValueAt(rowSelectedIndex,2).toString());
				txtItemStock.setText(model.getValueAt(rowSelectedIndex,3).toString());
			}
		});
		storeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		storeTable.setRowHeight(20);
		storeTable.setSelectionBackground(SystemColor.activeCaption);
		storeTable.setName("StoreTable");
		storeTable.setFillsViewportHeight(true);
		storeTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		storeTable.setModel(new DefaultTableModel(
			new Object[][]
			{
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[]
			{
				"Item ID", "Item Name", "Price", "Stock"
			}
		){
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[]
			{
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});
		scrollPaneStoreTable.setViewportView(storeTable);
		DefaultTableModel table = (DefaultTableModel)storeTable.getModel();
		
		Panel panelDetails = new Panel();
		panelDetails.setName("");
		panelDetails.setBounds(10, 230, 605, 263);
		add(panelDetails);
		panelDetails.setLayout(null);
		
		JPanel selectedDetailsPanel = new JPanel();
		selectedDetailsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "SELECTED ITEM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		selectedDetailsPanel.setBounds(1, 10, 297, 199);
		selectedDetailsPanel.setBackground(new Color(102, 153, 204));
		panelDetails.add(selectedDetailsPanel);
		selectedDetailsPanel.setLayout(null);
		
		JLabel lblItemId = new JLabel("Item ID:");
		lblItemId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblItemId.setBounds(10, 28, 97, 30);
		selectedDetailsPanel.add(lblItemId);
		
		txtItemId = new JTextField();
		txtItemId.setEditable(false);
		txtItemId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtItemId.setBounds(113, 28, 89, 30);
		selectedDetailsPanel.add(txtItemId);
		txtItemId.setColumns(10);
		
		JLabel lblItemName = new JLabel("Item Name:");
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblItemName.setBounds(10, 68, 130, 30);
		selectedDetailsPanel.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setEditable(false);
		txtItemName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtItemName.setBounds(113, 68, 174, 30);
		selectedDetailsPanel.add(txtItemName);
		txtItemName.setColumns(10);
		
		JLabel lblItemPrice = new JLabel("Price:");
		lblItemPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblItemPrice.setBounds(10, 108, 130, 30);
		selectedDetailsPanel.add(lblItemPrice);
		
		txtItemPrice = new JTextField();
		txtItemPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtItemPrice.setEditable(false);
		txtItemPrice.setColumns(10);
		txtItemPrice.setBounds(113, 108, 174, 30);
		selectedDetailsPanel.add(txtItemPrice);
		
		JLabel lblItemStock = new JLabel("Stock:");
		lblItemStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblItemStock.setBounds(10, 148, 97, 30);
		selectedDetailsPanel.add(lblItemStock);
		
		txtItemStock = new JTextField();
		txtItemStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtItemStock.setEditable(false);
		txtItemStock.setColumns(10);
		txtItemStock.setBounds(113, 148, 89, 30);
		selectedDetailsPanel.add(txtItemStock);
		
		JPanel itemDetailsPanel = new JPanel();
		itemDetailsPanel.setLayout(null);
		itemDetailsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "DETAILS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		itemDetailsPanel.setBackground(new Color(102, 153, 204));
		itemDetailsPanel.setBounds(308, 10, 297, 199);
		panelDetails.add(itemDetailsPanel);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(10, 26, 89, 30);
		itemDetailsPanel.add(lblQuantity);
		
		JComboBox<Object> lstQuantity = new JComboBox<Object>();
		lstQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lstQuantity.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5"}));
		lstQuantity.setBounds(96, 26, 191, 30);
		itemDetailsPanel.add(lstQuantity);
		lstQuantity.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int quantity = (int)lstQuantity.getSelectedIndex() + 1;
				String priceField = txtItemPrice.getText();
				double price = Double.parseDouble(priceField);
				double totalprice = price * quantity;
				totalprice = Math.round(totalprice * 100.00) / 100.00;
				txtTotalPrice.setText("" + totalprice);
			}
		});
		
		JLabel lblPaymentType = new JLabel("Payment type:");
		lblPaymentType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaymentType.setBounds(10, 66, 130, 30);
		itemDetailsPanel.add(lblPaymentType);
		
		JComboBox<Object> lstPaymentType = new JComboBox<Object>();
		lstPaymentType.setModel(new DefaultComboBoxModel<Object>(new String[] {"Card", "PayPal", "Google Pay"}));
		lstPaymentType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lstPaymentType.setBounds(137, 66, 150, 30);
		itemDetailsPanel.add(lstPaymentType);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalPrice.setBounds(10, 106, 105, 30);
		itemDetailsPanel.add(lblTotalPrice);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTotalPrice.setEditable(false);
		txtTotalPrice.setBounds(137, 106, 150, 30);
		itemDetailsPanel.add(txtTotalPrice);
		txtTotalPrice.setColumns(10);
		
		JButton btnBuy = new JButton("BUY");
		btnBuy.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try
				{

					final String DATABASE_URL = "jdbc:mysql://localhost/oosd_ca3";
					Connection connection = null ;
					ResultSet resultset = null;
					PreparedStatement prepstat = null ;
					PreparedStatement prepstat1 = null ;
					String payment =(String) lstPaymentType.getSelectedItem();
					int quantity = (int) lstQuantity.getSelectedIndex() + 1;
					double totalprice = Double.parseDouble(txtTotalPrice.getText());
					int id = 0;
					String productID = txtItemId.getText();
					int PID = Integer.parseInt(productID);
					int index = 0;
					
					connection = DriverManager.getConnection(DATABASE_URL, "root", "");
					
					prepstat = connection.prepareStatement("SELECT CustomerId FROM customer WHERE Email=?");
					prepstat.setString(1, email);
					
					resultset = prepstat.executeQuery();
					while(resultset.next())
					{
						id = resultset.getInt(1);
					}

					prepstat1 = connection.prepareStatement("INSERT INTO invoice (PaymentType, Quantity, TotalPrice, CustomerID, ProductID) VALUES (?,?,?,?,?)");
					prepstat1.setString(1, payment);
					prepstat1.setInt(2, quantity);
					prepstat1.setDouble(3, totalprice);
					prepstat1.setInt(4, id);
					prepstat1.setInt(5, PID);

					index = prepstat1.executeUpdate();
			
					if (index == 1)
					{
						JOptionPane.showMessageDialog(null, "Order successfully created");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Order has not been created");
					}
				}
				catch(SQLException sqlexception)
				{
				
				}
			}
		});
		btnBuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuy.setBounds(1, 224, 605, 39);
		panelDetails.add(btnBuy);
		table.setRowCount(0);
		setVisible(true);
		
		try
		{
			final String DATABASE_URL = "jdbc:mysql://localhost/oosd_ca3";
			Connection connection = null ;
			ResultSet resultset = null;
			PreparedStatement prepstat = null ;
			
			connection = DriverManager.getConnection(DATABASE_URL, "root", "");

			prepstat = connection.prepareStatement("SELECT * FROM product");
			
			resultset = prepstat.executeQuery();
			while(resultset.next())
			{
				Object row [] = {resultset.getInt("ProductID"), resultset.getString("ProductName"), resultset.getDouble("ProductPrice"), resultset.getInt("ProductStock")};
				table.addRow(row);
			}

		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}