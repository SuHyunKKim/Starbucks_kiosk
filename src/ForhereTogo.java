import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import OrderPages.MainMenuPanel;

public class ForhereTogo extends JFrame {
    public ForhereTogo() {
        // ForHere or ToGo 창 설정
        setTitle("For Here or To Go?");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // 창을 화면 가운데에 위치

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        // For Here 버튼 및 버튼 클릭시 수행 동작
        JButton forHereButton = new JButton("매장");
        forHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "매장을 선택했습니다.");
                goToNextScreen();
            }
        });
        buttonPanel.add(forHereButton);

        // To Go 버튼 및 버튼 클릭시 수행 동작
        JButton toGoButton = new JButton("포장");
        toGoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "포장 선택했습니다.");
                goToNextScreen();
            }
        });

        buttonPanel.add(toGoButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // 버튼 클릭시 다음 화면으로 이동하는 기능
    private void goToNextScreen() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainMenuFrame = new JFrame("상품 선택");
            mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainMenuFrame.getContentPane().add(new MainMenuPanel());
            mainMenuFrame.setSize(540, 960);
            mainMenuFrame.setLocationRelativeTo(null);
            mainMenuFrame.setVisible(true);

            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForhereTogo().setVisible(true);
            }
        });
    }
}

