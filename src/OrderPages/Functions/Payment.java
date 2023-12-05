package OrderPages.Functions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Payment extends JFrame {

    private DefaultTableModel tableModel;
    private JLabel totalPriceLabel;

    public Payment() {
        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        // Create a table model with column names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("상품명");
        tableModel.addColumn("사이즈");
        tableModel.addColumn("개인컵");
        tableModel.addColumn("수량");
        tableModel.addColumn("가격");

        // Load cart information from cart.txt
        loadCartInfo();

        // Create a table with the table model
        JTable cartTable = new JTable(tableModel);

        // Create a label for total price
        totalPriceLabel = new JLabel("총 가격: " + calculateTotalPrice() + "원");

        // Create a panel for the payment button and total price
        JPanel buttonPanel = new JPanel();
        JButton paymentButton = new JButton("결제하기");

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle payment button action
                saveToCSV();  // Save to CSV when payment button is pressed
                JOptionPane.showMessageDialog(null, "영수증을 발행했습니다.");
                // Close the Payment window
                dispose();

            }
        });

        buttonPanel.add(totalPriceLabel);
        buttonPanel.add(paymentButton);

        // Set layout for the Payment frame
        setLayout(new BorderLayout());
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCartInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    // Extract information from the line
                    String productName = parts[0];
                    String size = parts[2];
                    String cup = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    int price = Integer.parseInt(parts[5]);

                    // Add information to the table model
                    tableModel.addRow(new Object[]{productName, size, cup, quantity, price});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateTotalPrice() {
        int total = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int price = (int) tableModel.getValueAt(row, 4);
            total += price;
        }
        return total;
    }

    private void saveToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.csv"))) {
            // Write column names
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Write table data
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    writer.write(String.valueOf(tableModel.getValueAt(row, col)));
                    if (col < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            // Write total price
            writer.write("총 가격," + calculateTotalPrice() + "원");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Payment paymentWindow = new Payment();
            paymentWindow.setVisible(true);
        });
    }
}
