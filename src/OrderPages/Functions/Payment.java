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
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        // 행렬로 선택한 상품 정보를 출력하기 위한 tableModel 생성
        tableModel = new DefaultTableModel();
        tableModel.addColumn("상품명");
        tableModel.addColumn("사이즈");
        tableModel.addColumn("개인컵");
        tableModel.addColumn("수량");
        tableModel.addColumn("가격");

        // 장바구니 정보를 불러올 메소드
        loadCartInfo();

        // 장바구니 정보를 출력할 테이블 생성
        JTable cartTable = new JTable(tableModel);
        
        totalPriceLabel = new JLabel("총 가격: " + calculateTotalPrice() + "원");

        // 결제 버튼 패널 생성 및 버튼 클릭시 수행 동작
        // 결제 버튼 클릭시 영수증을 발행하고 결제 창을 닫음
        JPanel buttonPanel = new JPanel();
        JButton paymentButton = new JButton("결제하기");
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToCSV(); 
                JOptionPane.showMessageDialog(null, "영수증을 발행했습니다.");
                dispose(); // 결제 창을 닫음

            }
        });

        buttonPanel.add(totalPriceLabel);
        buttonPanel.add(paymentButton);
        
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // 장바구니 정보를 불러오는 메소드
    private void loadCartInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    String productName = parts[0];
                    String size = parts[2];
                    String cup = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    int price = Integer.parseInt(parts[5]);
                    // 테이블에 장바구니 정보를 추가
                    tableModel.addRow(new Object[]{productName, size, cup, quantity, price});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 총 가격을 계산하는 메소드
    private int calculateTotalPrice() {
        int total = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int price = (int) tableModel.getValueAt(row, 4);
            total += price;
        }
        return total;
    }
    
    // 영수증 발행을 위한 메소드
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
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    writer.write(String.valueOf(tableModel.getValueAt(row, col)));
                    if (col < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
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
