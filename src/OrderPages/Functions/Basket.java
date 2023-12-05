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

        // Create panel for product panels
        JPanel productPanelContainer = new JPanel();
        productPanelContainer.setLayout(new BoxLayout(productPanelContainer, BoxLayout.Y_AXIS));
        productPanelContainer.setBackground(Color.WHITE);

        // Load product information from the file
        loadProductsFromCartFile();

        // Create and add ProductPanel for each product to the productPanelContainer
        for (ProductPanel productPanel : productPanels) {
            productPanel.setBackground(Color.WHITE);
            productPanelContainer.add(productPanel);
        }

        // Wrap productPanelContainer in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(productPanelContainer);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);

        // Add close and checkout buttons to the buttonPanel
        JButton closeButton = new JButton("장바구니 닫기");
        JButton checkoutButton = new JButton("결제하기");

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

        // Add scrollPane and buttonPanel to the main Basket panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showPaymentWindow() {
        Payment paymentWindow = new Payment();
        paymentWindow.setVisible(true);
    }

    private void loadProductsFromCartFile() {
        productPanels = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using comma as a delimiter
                String[] parts = line.split(",");

                if (parts.length >= 4) {
                    // Create a ProductPanel for each product and add it to the list
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
    private void closeBasketWindow() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private class ProductPanel extends JPanel {

        public String getProductName() {
            return nameLabel.getText();
        }

        public String getTemp() {
            return tempLabel.getText();
        }

        public String getCup() {
            return cupLabel.getText();
        }

        public String getsSize() {
            return sizeLabel.getText();
        }

        public String getTime() {
            return timeLabel.getText();
        }

        public int getQuantity() {
            return Integer.parseInt(quantityLabel.getText());
        }

        public int getTotalPrice() {
            return Integer.parseInt(priceLabel.getText().split(":")[1].replace("원", "").trim());
        }

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
        private GridBagLayout gBag;

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

            // Use GridLayout for the ProductPanel components
            setLayout(new GridLayout(3, 1));

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            infoPanel.add(nameLabel);
            infoPanel.add(tempLabel);
            infoPanel.add(sizeLabel);
            infoPanel.add(cupLabel);
//            infoPanel.add(timeLabel);

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

