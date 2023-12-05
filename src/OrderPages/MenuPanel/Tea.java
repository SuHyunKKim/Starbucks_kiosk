package OrderPages.MenuPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import OrderPages.Functions.OptionWindow;

public class Tea extends JPanel {

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
        Tea teaPanel = new Tea();
        frame.add(teaPanel);

        frame.setVisible(true);
    }
}