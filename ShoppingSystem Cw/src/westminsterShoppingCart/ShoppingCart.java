package westminsterShoppingCart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShoppingCart {

    static JTable table;
    static DefaultTableModel model;
    static Object[] columns = {"Product", "Quantity", "Price"};


    public void createAndShow() {

        // JTable ADDED Items
        table = new JTable(model);
        model = new DefaultTableModel();

        // JPanel For Items
        JPanel store = new JPanel();
        store.setPreferredSize(new Dimension(100, 250));
        store.setLayout(new BorderLayout());
        store.setBackground(Color.WHITE);
        model.setColumnIdentifiers(columns);
        store.add(new JScrollPane(table), BorderLayout.CENTER);
        table.setBounds(100, 100, 757, 610);
        table.setModel(model);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);


        // Add a label with the custom message to the new frame
        JLabel label = new JLabel();

        //  new frame for the new window
        JFrame newFrame = new JFrame("Shopping cart");
        newFrame.setSize(200, 100);
        newFrame.getContentPane().add(label);
        newFrame.setResizable(false);
        newFrame.setSize(500, 500);
        newFrame.add(store, BorderLayout.NORTH);


        newFrame.setVisible(true);
    }





}