import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartPage extends JFrame {
    private JLabel imageLabel;
    private int currentImageIndex = 0;
    private String[] imagePaths = {"images/logo/StartPage1.jpg", "images/logo/StartPage2.jpg",
            "images/logo/StartPage3.jpg", "images/logo/StartPage4.jpg"};
    private Timer timer;

    public StartPage() {
        // 기본 창 설정
        setTitle("키오스크 시작 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 960);
        setLocationRelativeTo(null); // 창을 화면 가운데에 위치

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 이미지 레이블
        imageLabel = new JLabel();
        updateImage();
        add(imageLabel, BorderLayout.CENTER);

        // 이미지 전환을 위한 타이머 설정
        int interval = 3000; // 3초마다 이미지 변경
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextImage();
            }
        });
        timer.start();

        // 화면 터치 이벤트 처리
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 이미지를 클릭하면 다음 화면으로 전환
                timer.stop(); // 타이머 중지
                goToNextScreen();
            }
        });
    }

    private void updateImage() {
        // 현재 이미지 인덱스에 해당하는 이미지로 업데이트
        String imagePath = imagePaths[currentImageIndex];
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        // 이미지 크기 조절
        int newWidth = getWidth(); // 프레임의 너비에 맞춤
        int newHeight = getHeight(); // 프레임의 높이에 맞춤
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // 조절된 이미지로 ImageIcon 생성
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedIcon);
    }

    private void nextImage() {
        // 다음 이미지로 전환
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        updateImage();
    }

    private void goToNextScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // ForhereTogo 클래스의 인스턴스를 생성하여 화면을 전환
                new ForhereTogo().setVisible(true);
                // 현재 StartPage 창을 닫음
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartPage().setVisible(true);
            }
        });
    }
}

