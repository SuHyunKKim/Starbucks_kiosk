package Test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TextColorSliderExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Text Color Slider Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JPanel panel = new JPanel();
            JLabel label = new JLabel("Colorful Text");
            JSlider redSlider = new JSlider(0, 255);
            JSlider greenSlider = new JSlider(0, 255);
            JSlider blueSlider = new JSlider(0, 255);

            redSlider.addChangeListener(new ColorSliderChangeListener(label, redSlider, greenSlider, blueSlider));
            greenSlider.addChangeListener(new ColorSliderChangeListener(label, redSlider, greenSlider, blueSlider));
            blueSlider.addChangeListener(new ColorSliderChangeListener(label, redSlider, greenSlider, blueSlider));

            panel.add(redSlider);
            panel.add(greenSlider);
            panel.add(blueSlider);

            frame.setLayout(new BorderLayout());
            frame.add(label, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    static class ColorSliderChangeListener implements ChangeListener {
        private JLabel label;
        private JSlider redSlider;
        private JSlider greenSlider;
        private JSlider blueSlider;

        ColorSliderChangeListener(JLabel label, JSlider redSlider, JSlider greenSlider, JSlider blueSlider) {
            this.label = label;
            this.redSlider = redSlider;
            this.greenSlider = greenSlider;
            this.blueSlider = blueSlider;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            int redValue = redSlider.getValue();
            int greenValue = greenSlider.getValue();
            int blueValue = blueSlider.getValue();

            Color textColor = new Color(redValue, greenValue, blueValue);
            label.setForeground(textColor);
        }
    }
}
