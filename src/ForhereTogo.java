import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import OrderPages.MainMenuPanel;

public class ForhereTogo extends JFrame {
    public ForhereTogo() {
        // 기본 창 설정
        setTitle("For Here or To Go?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // 창을 화면 가운데에 위치

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 가운데에 두 개의 버튼이 있는 패널 추가
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        // For Here 버튼
        JButton forHereButton = new JButton("For Here");
        forHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For Here 버튼을 클릭했을 때 수행할 동작 추가
                JOptionPane.showMessageDialog(null, "For Here를 선택했습니다.");
                // 여기에 For Here를 선택한 경우의 동작 추가
                goToNextScreen();
            }
        });
        buttonPanel.add(forHereButton);

        // To Go 버튼
        JButton toGoButton = new JButton("To Go");
        toGoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // To Go 버튼을 클릭했을 때 수행할 동작 추가
                JOptionPane.showMessageDialog(null, "To Go를 선택했습니다.");
                // 여기에 To Go를 선택한 경우의 동작 추가
                goToNextScreen();
            }
        });
        buttonPanel.add(toGoButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
/*
    private void goToNextScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // ForhereTogo 클래스의 인스턴스를 생성하여 화면을 전환
                new Beverage().setVisible(true);
                // 현재 StartPage 창을 닫음
                dispose();
            }
        });
    }
*/
    private void goToNextScreen() {
        SwingUtilities.invokeLater(() -> {
            JFrame mainMenuFrame = new JFrame("Main Menu Panel");
            mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainMenuFrame.getContentPane().add(new MainMenuPanel());
            mainMenuFrame.setSize(540, 960);
            mainMenuFrame.setLocationRelativeTo(null);
            mainMenuFrame.setVisible(true);

            // Close the current ForhereTogo frame
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

