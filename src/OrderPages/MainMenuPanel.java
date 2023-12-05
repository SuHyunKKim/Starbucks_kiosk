package OrderPages;

import OrderPages.Functions.Basket;
import OrderPages.Functions.OptionWindow;
import OrderPages.Functions.Payment;
import OrderPages.MenuPanel.Tea;
import OrderPages.MenuPanel.Espresso;
import OrderPages.MenuPanel.Frappuccino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel() {
        setLayout(new BorderLayout());

        // 위쪽 이미지 추가
        ImageIcon headerImage = new ImageIcon("images/logo/StarbucksMain.png"); // 이미지 경로에 맞게 수정
        JLabel headerLabel = new JLabel(headerImage);
        headerLabel.setPreferredSize(new Dimension(540, 200));
        add(headerLabel, BorderLayout.NORTH);

        // 중간 탭패널 추가
        JTabbedPane tabbedPane = new JTabbedPane();
        Tea tea = new Tea();
        Espresso espresso = new Espresso();
        Frappuccino frappuccino = new Frappuccino();
        JPanel tab4Panel = new JPanel();
        tabbedPane.addTab("Tea", tea);
        tabbedPane.addTab("Espresso", espresso);
        tabbedPane.addTab("Frappuccino", frappuccino);
        tabbedPane.addTab("Tab 4", tab4Panel);

        // 폭 540, 높이 500으로 설정
        Dimension tabPanelSize = new Dimension(540, 500);
        tea.setPreferredSize(tabPanelSize);
        espresso.setPreferredSize(tabPanelSize);
        frappuccino.setPreferredSize(tabPanelSize);
        tab4Panel.setPreferredSize(tabPanelSize);

        add(tabbedPane, BorderLayout.CENTER);

        // 아래 상품 담기 창 추가
        JPanel kioskPanel = new JPanel();
        JButton cartButton = new JButton("장바구니");
        JButton checkoutButton = new JButton("결제하기");

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 장바구니 버튼 클릭 시 수행할 동작
                showBasketWindow();
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 결제하기 버튼 클릭 시 수행할 동작
                showPaymentWindow();
            }
        });

        kioskPanel.add(cartButton);
        kioskPanel.add(checkoutButton);

        add(kioskPanel, BorderLayout.SOUTH);
    }

    private void showBasketWindow() {
        // Create and show the Basket window
        JFrame basketFrame = new JFrame("Basket");
        basketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        basketFrame.getContentPane().add(new Basket());
        basketFrame.setSize(400, 200);
        basketFrame.setLocationRelativeTo(null);
        basketFrame.setVisible(true);
    }

    private void showPaymentWindow() {
        Payment paymentWindow = new Payment();
        paymentWindow.setVisible(true);
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Main Menu Panel");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.getContentPane().add(new MainMenuPanel());
//            frame.setSize(540, 960);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }
}
