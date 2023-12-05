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
        // 시작 페이지 창 설정
        setTitle("키오스크 시작 페이지");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 960);
        setLocationRelativeTo(null); // 창을 화면 가운데에 위치하는 코드


        // 이미지 라벨 생성
        imageLabel = new JLabel();
        updateImage();
        add(imageLabel, BorderLayout.CENTER);

        // 이미지 전환을 위한 타이머 기능 추가
        int interval = 3000; // 3초마다 이미지 변경
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextImage();
            }
        });
        timer.start();

        // 화면 클릭시 다음 화면으로 전환하는 기능
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                timer.stop(); // 타이머 중지
                goToNextScreen();
            }
        });
    }
    
    // 이미지를 화면 크기에 맞게 조절하는 메소드
    private void updateImage() {
        String imagePath = imagePaths[currentImageIndex];
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        
        int newWidth = getWidth();
        int newHeight = getHeight(); 
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        imageLabel.setIcon(resizedIcon);
    }

    // 다음 이미지로 전환하는 메소드
    private void nextImage() {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        updateImage();
    }

    // 다음 화면인 ForhereTogo 화면으로 전환하는 기능
    private void goToNextScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ForhereTogo().setVisible(true);
                dispose(); // 시작 페이지 닫음
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

