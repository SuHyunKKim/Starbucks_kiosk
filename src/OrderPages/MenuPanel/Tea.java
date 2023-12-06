package OrderPages.MenuPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import OrderPages.Functions.OptionWindow;

public class Tea extends JPanel {

    private String selectedProductImage;
    private String selectedProductName;
    private int selectedProductPrice;

    // 선택된 상품의 정보를 저장하는 메소드
    private void setSelectedProduct(String image, String name, int price) {
        selectedProductImage = image;
        selectedProductName = name;
        selectedProductPrice = price;
    }

    // 상품 이미지, 이름, 가격
     private String[][] productInfo = {
            {"images/menu/tea/camomileblend.jpg", "카모마일 블렌드 티", "3000"},
            {"images/menu/tea/englishbreak.jpg", "잉글리시 브렉퍼스트 티", "3500"},
            {"images/menu/tea/hibiblend.jpg", "히비스커스 블렌드 티", "4000"},
            {"images/menu/tea/jamonghoney.jpg", "자몽 허니 티", "4500"},
            {"images/menu/tea/jejuyuginoccha.jpg", "제주 유기농 녹차", "5000"},
            {"images/menu/tea/mintblend.jpg", "민트 블렌드 티", "5500"},
            {"images/menu/tea/ulgray.jpg", "얼그레이 티", "6000"},
            {"images/menu/tea/youthberry.jpg", "유스 베리 티", "6500"},
            {"images/menu/tea/yujamint.jpg", "유자 민트 티", "7000"}
    };

    public Tea() {
        setLayout(new GridLayout(3, 3)); // 3x3 그리드 레이아웃
        setSize(400, 400);

        // 상품 버튼 생성
        for (String[] info : productInfo) {
            JButton button = new JButton();
            // 버튼을 이미지로 설정
            ImageIcon icon = new ImageIcon(new ImageIcon(info[0]).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            button.setIcon(icon);

            // 상품 정보를 담을 패널 생성
            JLabel nameLabel = new JLabel(info[1]);
            JLabel priceLabel = new JLabel(info[2]+"원");
            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.add(nameLabel, BorderLayout.NORTH);
            labelPanel.add(priceLabel, BorderLayout.CENTER);

            // 버튼과 상품 정보 패널을 담을 상위 패널 생성
            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.add(button, BorderLayout.CENTER);
            productPanel.add(labelPanel, BorderLayout.SOUTH);

            add(productPanel);

            // 버튼 클릭시 상세 옵션창을 띄우는 기능 추가
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

    // 버튼 클릭시, 상세 옵션창을 띄우고, 선택된 상품의 정보를 전달하는 메소드
    private void showOptionWindow() {
        OptionWindow optionWindow = new OptionWindow();
        optionWindow.setProductInfo(selectedProductImage, selectedProductName, selectedProductPrice);
    }
}