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
    
    // 상품정보를 설정할 메소드
    public void setProductInfo(String image, String name, int price) {
        imagePath = image;
        productName = name;
        productPrice = price;
        updateUI();
    }

    // 이미지, 상품명, 가격 라벨들을 갱신하는 메소드
    private void updateUI() {
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

    public OptionWindow() {
        setTitle("상세 옵션 선택");
        setLayout(new BorderLayout());
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 왼쪽의 상품 이미지, 상품명, 가격 라벨 패널
        JPanel productInfoPanel = createOptionPanel("");
        JPanel productInfoinsidePanel = new JPanel(new BorderLayout());
        productInfoinsidePanel.add(productImageLabel, BorderLayout.NORTH);
        productInfoinsidePanel.add(productNameLabel, BorderLayout.CENTER);
        productInfoinsidePanel.add(productPriceLabel, BorderLayout.SOUTH);
        productInfoPanel.add(productInfoinsidePanel);

        add(productInfoPanel, BorderLayout.WEST);


        // ICED, HOT 선택 패널
        JPanel temperaturePanel = createOptionPanel("Temperature");
        temperatureButtonGroup = new ButtonGroup();

        icedButton = new JToggleButton("ICED");
        hotButton = new JToggleButton("HOT");

        temperatureButtonGroup.add(icedButton);
        temperatureButtonGroup.add(hotButton);

        temperaturePanel.add(icedButton);
        temperaturePanel.add(hotButton);

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

        // 개인컵 사용 여부 체크박스 패널
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

        // 취소 및 장바구니 버튼 패널
        JPanel buttonPanel = createOptionPanel("");
        cancelButton = new JButton("취소");
        addToCartButton = new JButton("장바구니 담기");
        buttonPanel.add(cancelButton);
        buttonPanel.add(addToCartButton);

        // 상품 옵션들을 담을 오른쪽 패널 생성
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1));
        rightPanel.add(createBorderLayoutPanel(temperaturePanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(sizePanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(personalCupPanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(quantityPanel, BorderLayout.NORTH));
        rightPanel.add(createBorderLayoutPanel(buttonPanel, BorderLayout.NORTH));
        add(rightPanel, BorderLayout.CENTER);

        // 각종 옵션 버튼에 대한 이벤트를 수행하는 리스너들
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

        setVisible(true);
    }

    private String Temp;
    private String Size;
    private String Cup;
    private String Quantity;
    private int TotalPrice;

    // 음료 온도 선택에 따라 온도를 저장하는 메소드
    private void handleTemperatureButton(String temperature) {
        if (temperature == "ICED") {
            Temp = "아이스";
        } else if (temperature == "HOT") {
            Temp = "핫";
        }
    }

    // 컵 사이즈 선택에 따라 사이즈를 저장하는 메소드
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

    // 개인컵 사용 여부에 따라 컵 사용 여부를 저장하는 메소드
    private void handlePersonalCupCheckBox() {
        if (personalCupCheckBox.isSelected() || (personalCupCheckBox.getModel().isSelected())) {
            Cup = "개인컵O";
        } else {
            Cup = "개인컵X";
        }
    }

    // 수량 조절 버튼에 따라 수량을 저장 및 총 가격 계산하는 메소드
    private void handleQuantityButton(int a) {
        quantity += a;
        if (quantity < 1) {
            quantity = 1;
        }
        Quantity = String.valueOf(quantity);
        quantityLabel.setText(String.valueOf(quantity));
        priceLabel.setText("Price: " + calculateTotalPrice()+"원");
    }

    // 수량과 가격을 곱해서 총 가격 계산하는 메소드
    private int calculateTotalPrice() {
        return quantity * productPrice;
    }

    // 옵션 패널을 일일히 생성하기 위한 메소드
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

    // 장바구니에 상품을 추가하기 위한 메소드
    // cart.txt에 상품 정보를 저장 후, Basket 클래스에서 이 파일을 불러와 장바구니에 추가한다.
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

    // 현재 시간을 반환하는 메소드 - 장바구니에 상품을 추가할 때 사용
    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OptionWindow());
    }
}
