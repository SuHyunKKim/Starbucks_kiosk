package OrderPages.MenuPanel;


import OrderPages.Functions.OptionWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frappuccino extends JPanel {

    // Variables to store selected product information
    private String selectedProductImage;
    private String selectedProductName;
    private int selectedProductPrice;

    // Method to store selected product information
    private void setSelectedProduct(String image, String name, int price) {
        selectedProductImage = image;
        selectedProductName = name;
        selectedProductPrice = price;
    }

    private void showOptionWindow() {
        // OptionWindow 인스턴스 생성 및 선택된 상품의 정보 전달
        OptionWindow optionWindow = new OptionWindow();
        optionWindow.setProductInfo(selectedProductImage, selectedProductName, selectedProductPrice);
    }

     private String[][] productInfo = {
            {"images/menu/frappuccino/caramel.jpg", "카라멜 프라푸치노", "3000"},
            {"images/menu/frappuccino/chococreamchip.jpg", "초콜렛 크림 칩 프라푸치노", "3500"},
            {"images/menu/frappuccino/espresso.jpg", "에스프레소 프라푸치노", "4000"},
            {"images/menu/frappuccino/javachip.jpg", "자바칩 프라푸치노", "4500"},
            {"images/menu/frappuccino/jejucamangcream.jpg", "제주 까망 크림 프라푸치노", "5000"},
            {"images/menu/frappuccino/jejumalcha.jpg", "제주 말차 프라푸치노", "5500"},
            {"images/menu/frappuccino/jejussukddokcream.jpg", "제주쑥떡 크림 프라푸치노", "6000"},
            {"images/menu/frappuccino/whitechomocca.jpg", "화이트 모카 프라푸치노", "6500"},
            {"images/menu/frappuccino/whitetiger.jpg", "화이트 타이커 프라푸치노", "7000"}
    };

    public Frappuccino() {
        // 레이아웃 설정
        setLayout(new GridLayout(3, 3)); // 3x3 그리드 레이아웃
        setSize(400, 400);

        // 버튼 생성 및 이미지 설정
        for (String[] info : productInfo) {
            JButton button = new JButton();
            ImageIcon icon = new ImageIcon(new ImageIcon(info[0]).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
//            ImageIcon icon = new ImageIcon(info[0]);
            button.setIcon(icon);

            // 버튼의 이미지 위에 라벨 추가
            JLabel nameLabel = new JLabel(info[1]);
            JLabel priceLabel = new JLabel(info[2]);
            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.add(nameLabel, BorderLayout.NORTH);
            labelPanel.add(priceLabel, BorderLayout.CENTER);

            // 버튼과 라벨 패널을 담을 상위 패널 생성
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.add(button, BorderLayout.CENTER);
            productPanel.add(labelPanel, BorderLayout.SOUTH);

            // 패널에 상품 패널 추가
            add(productPanel);

            // 버튼에 액션 리스너 추가
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // wantwindow 창 띄우기
                    setSelectedProduct(info[0], info[1], Integer.parseInt(info[2]));
                    showOptionWindow();
                }
            });
        }


    }

    // 테스트용 메인 메소드
    public static void main(String[] args) {
        JFrame frame = new JFrame("Beverage Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Beverage 패널 추가
        Frappuccino beveragePanel = new Frappuccino();
        frame.add(beveragePanel);

        frame.setVisible(true);
    }
}