package OrderPages.Functions;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Basket extends JPanel {

    private ArrayList<ProductPanel> productPanels;
    private String line;
    public Basket() {
        setLayout(new BorderLayout());

        // 상품을 추가한 패널들을 담을 컨테이너 패널
        JPanel productPanelContainer = new JPanel();
        productPanelContainer.setLayout(new BoxLayout(productPanelContainer, BoxLayout.Y_AXIS));
        productPanelContainer.setBackground(Color.WHITE);

        // cart.txt에서 상품 정보를 읽어와서 productPanels에 추가
        loadProductsFromCartFile();

        // 각 상품 패널을 productPanelContainer에 추가
        for (ProductPanel productPanel : productPanels) {
            productPanel.setBackground(Color.WHITE);
            productPanelContainer.add(productPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(productPanelContainer);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        // 패널에 장바구니 닫기 버튼과 결제하기 버튼 추가
        JButton closeButton = new JButton("장바구니 닫기");
        JButton checkoutButton = new JButton("결제하기");

        // 각 버튼 클릭시 수행하는 동작
        closeButton.addActionListener(e -> {
            updateCart();
            closeBasketWindow();
        });

        checkoutButton.addActionListener(e -> {
            updateCart();
            closeBasketWindow();
            showPaymentWindow();
        });

        buttonPanel.add(closeButton);
        buttonPanel.add(checkoutButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 결제창을 띄우는 기능
    private void showPaymentWindow() {
        Payment paymentWindow = new Payment();
        paymentWindow.setVisible(true);
    }

    // cart.txt에서 상품 정보를 읽어와서 productPanels에 추가하는 기능
    private void loadProductsFromCartFile() {
        productPanels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    String productName = parts[0];
                    String temp = parts[1];
                    String size = parts[2];
                    String cup = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    int totalprice = Integer.parseInt(parts[5]);
                    String time = parts[6];

                    ProductPanel productPanel = new ProductPanel(productName, temp, size, cup, quantity, totalprice, time);
                    productPanels.add(productPanel);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 장바구니에서 상품 수량 변경시, cart.txt에 변경된 수량을 저장하는 기능
    private void updateCart() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"));

            for (ProductPanel productPanel : productPanels) {
                if (productPanel.getQuantity() != 0) {
                    String updatedLine = productPanel.getProductName() + "," + productPanel.getTemp()+ "," +
                            productPanel.getsSize() + "," + productPanel.getCup()+ "," + productPanel.getQuantity() + ","
                            + productPanel.getTotalPrice() + "," + productPanel.getTime();
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 장바구니 창을 닫는 기능
    private void closeBasketWindow() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private class ProductPanel extends JPanel {
        // 상품 관련 정보들을 담을 라벨들과 getter 메소드
        public String getProductName() {return nameLabel.getText();}
        public String getTemp() {return tempLabel.getText();}
        public String getCup() {return cupLabel.getText();}
        public String getsSize() {return sizeLabel.getText();}
        public String getTime() {return timeLabel.getText();}
        public int getQuantity() {return Integer.parseInt(quantityLabel.getText());}

        public int getTotalPrice() {return Integer.parseInt(priceLabel.getText().split(":")[1].replace("원", "").trim());}

        private JLabel sizeLabel;
        private JLabel tempLabel;
        private JLabel cupLabel;
        private JLabel timeLabel;
        private JLabel nameLabel;
        private JLabel quantityLabel;
        private JLabel priceLabel;
        private int price;
        private JButton increaseButton;
        private JButton decreaseButton;
        
        // 장바구니에 상품 수량을 변경 가능한 패널을 추가하는 기능
        public ProductPanel(String productName, String temp, String size, String cup, int initialQuantity, int totalprice, String time) {
            sizeLabel = new JLabel(size);
            tempLabel = new JLabel(temp);
            cupLabel = new JLabel(cup);
            timeLabel = new JLabel(time);

            nameLabel = new JLabel(productName, SwingConstants.LEFT);
            increaseButton = new JButton("+");
            decreaseButton = new JButton("-");
            quantityLabel = new JLabel(String.valueOf(initialQuantity));

            price = totalprice / initialQuantity;
            int Totprice = initialQuantity * price;
            priceLabel = new JLabel("Price: " + String.valueOf(Totprice) + "원");

            setLayout(new GridLayout(3, 1));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            infoPanel.add(nameLabel);
            infoPanel.add(tempLabel);
            infoPanel.add(sizeLabel);
            infoPanel.add(cupLabel);

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            quantityPanel.add(decreaseButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(increaseButton);

            JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pricePanel.add(priceLabel);

            add(infoPanel);
            add(quantityPanel);
            add(pricePanel);

            decreaseButton.addActionListener(e -> handleQuantityButton(-1));
            increaseButton.addActionListener(e -> handleQuantityButton(1));
        }

        // 장바구니 창에서 상품 수량을 변경하는 기능
        private void handleQuantityButton(int a) {
            int currentQuantity = Integer.parseInt(quantityLabel.getText());
            int newQuantity = currentQuantity + a;

            if (newQuantity >= 0) {
                quantityLabel.setText(String.valueOf(newQuantity));
                priceLabel.setText("Price: " + newQuantity * price + "원");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Basket basket = new Basket();
            basket.setVisible(true);
        });
    }
}

