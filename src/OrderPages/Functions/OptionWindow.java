package OrderPages.Functions;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OptionWindow extends JFrame {

    private String imagePath;
    private String productName;
    private int productPrice;

    public void setProductInfo(String image, String name, int price) {
        imagePath = image;
        productName = name;
        productPrice = price;

        updateUI();
    }

    private void updateUI() {
        // 이미지, 상품명, 가격 라벨들을 갱신
        productImageLabel.setIcon(new ImageIcon(imagePath));
        productNameLabel.setText(productName);
        productPriceLabel.setText(productPrice+"원");
    }

    private JLabel productImageLabel = new JLabel();
    private JLabel productNameLabel = new JLabel();
    private JLabel productPriceLabel = new JLabel();

    private JToggleButton icedButton, hotButton;
    private ButtonGroup temperatureButtonGroup;
    private JCheckBox personalCupCheckBox;
    private JButton decreaseButton, increaseButton;
    private JLabel quantityLabel, priceLabel;
    private JButton cancelButton, addToCartButton;

    private int quantity = 1;
//    private double basePrice; // 기본 상품 가격

    public OptionWindow() {
        setTitle("Option Window");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // 상품 정보 패널
        JPanel productInfoPanel = createOptionPanel("");
//        JLabel productImageLabel = new JLabel(new ImageIcon(imagePath));
//        JLabel productNameLabel = new JLabel(productName);
//        JLabel productPriceLabel = new JLabel("$" + productPrice);
        JPanel productInfoinsidePanel = new JPanel(new BorderLayout());
        productInfoinsidePanel.add(productImageLabel, BorderLayout.NORTH);
        productInfoinsidePanel.add(productNameLabel, BorderLayout.CENTER);
        productInfoinsidePanel.add(productPriceLabel, BorderLayout.SOUTH);
        productInfoPanel.add(productInfoinsidePanel);
        /*
        productInfoPanel.add(productImageLabel);
        productInfoPanel.add(productNameLabel);
        productInfoPanel.add(productPriceLabel);
        */

        add(productInfoPanel, BorderLayout.WEST);


        // ICED, HOT 선택 패널
        JPanel temperaturePanel = createOptionPanel("Temperature");

        temperatureButtonGroup = new ButtonGroup(); // Initialize ButtonGroup

        icedButton = new JToggleButton("ICED");
        hotButton = new JToggleButton("HOT");

        // Add buttons to ButtonGroup
        temperatureButtonGroup.add(icedButton);
        temperatureButtonGroup.add(hotButton);

        temperaturePanel.add(icedButton);
        temperaturePanel.add(hotButton);
/*
        // ICED, HOT 선택 패널
        JPanel temperaturePanel = createOptionPanel("Temperature");
        icedButton = new JButton("ICED");
        hotButton = new JButton("HOT");
        temperaturePanel.add(icedButton);
        temperaturePanel.add(hotButton);
*/
        // 컵 사이즈 선택 패널
        JPanel sizePanel = createOptionPanel("Size");
        JToggleButton sizeButton1 = new JToggleButton("Tall");
        JToggleButton sizeButton2 = new JToggleButton("Grande");
        JToggleButton sizeButton3 = new JToggleButton("Venti");
        JToggleButton sizeButton4 = new JToggleButton("Trenta");
        ButtonGroup sizeButtonGroup = new ButtonGroup();

        sizeButtonGroup.add(sizeButton1);
        sizeButtonGroup.add(sizeButton2);
        sizeButtonGroup.add(sizeButton3);
        sizeButtonGroup.add(sizeButton4);

        sizePanel.add(sizeButton1);
        sizePanel.add(sizeButton2);
        sizePanel.add(sizeButton3);
        sizePanel.add(sizeButton4);

        // 개인컵 사용 체크박스 패널
        JPanel personalCupPanel = createOptionPanel("");
        personalCupCheckBox = new JCheckBox("Personal Cup");
        personalCupPanel.add(personalCupCheckBox);

        // 수량 및 가격 조절 패널
        JPanel quantityPanel = createOptionPanel("");
        decreaseButton = new JButton("-");
        increaseButton = new JButton("+");
        quantityLabel = new JLabel(String.valueOf(quantity));
        priceLabel = new JLabel("Price: " + calculateTotalPrice()+"원");
        quantityPanel.add(new JLabel("Quantity: "));
        quantityPanel.add(decreaseButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(increaseButton);
        quantityPanel.add(priceLabel);

        // 취소 및 장바구니 담기 버튼 패널
        JPanel buttonPanel = createOptionPanel("");
        cancelButton = new JButton("Cancel");
        addToCartButton = new JButton("Add to Cart");
        buttonPanel.add(cancelButton);
        buttonPanel.add(addToCartButton);


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1));
        rightPanel.add(createBorderLayoutPanel(temperaturePanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(sizePanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(personalCupPanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(quantityPanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(buttonPanel, BorderLayout.NORTH));
        add(rightPanel, BorderLayout.CENTER);
        
        // 이벤트 핸들러 등록
        icedButton.addActionListener(e -> handleTemperatureButton("ICED"));
        hotButton.addActionListener(e -> handleTemperatureButton("HOT"));

        sizeButton1.addActionListener(e -> handleSizeButton(1));
        sizeButton2.addActionListener(e -> handleSizeButton(2));
        sizeButton3.addActionListener(e -> handleSizeButton(3));
        sizeButton4.addActionListener(e -> handleSizeButton(4));

        personalCupCheckBox.addActionListener(e -> handlePersonalCupCheckBox());

        decreaseButton.addActionListener(e -> handleQuantityButton(-1));
        increaseButton.addActionListener(e -> handleQuantityButton(1));

        cancelButton.addActionListener(e -> dispose());
        addToCartButton.addActionListener(e -> addToCart());

        // 창을 보이게 설정
        setVisible(true);
    }


    private String Temp;
    private String Size;
    private String Cup;
    private String Quantity;
    private int TotalPrice;

    private void handleTemperatureButton(String temperature) {
        // ICED 또는 HOT 버튼을 눌렀을 때의 동작

//        JToggleButton selectedButton = (JToggleButton) temperatureButtonGroup.getSelection();

        if (temperature == "ICED") {
            Temp = "아이스";
        } else if (temperature == "HOT") {
            Temp = "핫";
        }
    }

    private void handleSizeButton(int size) {
        if (size == 1) {
            Size = "Tall";
        } else if (size == 2) {
            Size = "Grande";
        } else if (size == 3) {
            Size = "Venti";
        } else if (size == 4) {
            Size = "Trenta";
        }
    }

    private void handlePersonalCupCheckBox() {
        if (personalCupCheckBox.isSelected() || (personalCupCheckBox.getModel().isSelected())) {
            Cup = "개인컵O";
        } else {
            Cup = "개인컵X";
        }
    }


    private void handleQuantityButton(int a) {
        quantity += a;
        if (quantity < 1) {
            quantity = 1;
        }
        Quantity = String.valueOf(quantity);
        quantityLabel.setText(String.valueOf(quantity));
        priceLabel.setText("Price: " + calculateTotalPrice()+"원");
    }

    private int calculateTotalPrice() {
        // 수량과 기본 가격을 곱하여 총 가격 계산
        return quantity * productPrice;
    }



    private JPanel createOptionPanel(String optionName) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(optionName);
        panel.add(label);
        return panel;
    }

    private JPanel createBorderLayoutPanel(JPanel panel, String position) {
        JPanel borderLayoutPanel = new JPanel(new BorderLayout());
        borderLayoutPanel.add(panel, position);
        return borderLayoutPanel;
    }


    public int getQuantity() {return quantity;}
    public String getProductName() {return productName;}

    private void addToCart() {
        String productInfo = String.format(
                "%s,%s,%s,%s,%s,%s,%s\n",
                productName, Temp, Size, Cup, Quantity, calculateTotalPrice(), getCurrentTime());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt", true))) {
            writer.write(productInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OptionWindow());
    }
}
