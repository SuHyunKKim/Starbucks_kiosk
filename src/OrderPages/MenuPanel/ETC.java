package OrderPages.MenuPanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ETC extends JPanel {
    // 색을 표시할 라벨과 색상을 조절할 슬라이더 배열 생성
    private static JLabel colorLabel;
    private JSlider[] sl = new JSlider[3];

    public ETC() {
        setLayout(new GridLayout(4, 1));

        colorLabel = new JLabel(" STARBUCKS ");
        colorLabel.setFont(new Font("Arial", Font.BOLD, 70));
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // RGB 각각의 슬라이더 생성 및 이벤트 리스너 추가
        for (int i = 0; i < sl.length; i++) {
            sl[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
            sl[i].setPaintLabels(true);
            sl[i].setPaintTicks(true);
            sl[i].setPaintTrack(true);
            sl[i].setMajorTickSpacing(50);
            sl[i].setMinorTickSpacing(10);
            sl[i].addChangeListener(new MyChangeListener());
            add(sl[i]);
        }

        sl[0].setForeground(Color.RED);
        sl[1].setForeground(Color.GREEN);
        sl[2].setForeground(Color.BLUE);

        colorLabel.setOpaque(true);

        add(colorLabel);
    }

    // RGB 각 슬라이더를 움직이면 색이 변하는 기능을 구현
    class MyChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int r = sl[0].getValue();
            int g = sl[1].getValue();
            int b = sl[2].getValue();

            Color textColor = new Color(r, g, b);
            colorLabel.setForeground(textColor);
        }
    }
}
