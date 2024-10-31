package Testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.*;
import java.util.Iterator;

public class Balls extends JFrame {
    public JPanel panel;
    public JButton btnStart;
    private JButton btnStop;
    private JCheckBox cbFlyweight;

    ArrayList<JPanel> balls;
    int colorInd = 0;
    Color colors[] = {Color.gray, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.CYAN};
    Timer timer = null;

    Balls() {
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balls = new ArrayList<>();
                timer = new Timer();
                if (cbFlyweight.isSelected()) {
                    timer.scheduleAtFixedRate(FWTask(), 0, 100);
                } else {
                    timer.scheduleAtFixedRate(nonFWTask(), 0, 100);
                }
                btnStart.setVisible(false);
                btnStop.setVisible(true);
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                for (JPanel ball : balls) {
                    if (ball instanceof Ball) {
                        ((Ball) ball).disposeImage();
                    } else {
                        ((MovingBall) ball).ballFW.disposeImage();
                    }
                    remove(ball);
                }
                balls.clear();
                colorInd = 0;
                revalidate();
                repaint();
                btnStart.setVisible(true);
                btnStop.setVisible(false);
            }
        });
    }

    TimerTask nonFWTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (balls.size() < 100) {
                    Ball newBall = (new Ball(colors[colorInd]));
                    colorInd = ((++colorInd) % 6);
                    balls.add(newBall);
                    add(newBall);
                }
//                System.out.println("This is a new run");
                for (JPanel ballPanel : balls) {
                    Ball ball = (Ball) ballPanel;
                    ball.x += ball.xVel * ball.xDir;
                    ball.y += ball.yVel * ball.yDir;
                    if (ball.x <= 0 || ball.x >= 600) {
                        ball.xDir *= -1;
                        ball.xVel = (ball.xVel + 1) % 31;
                        ball.x = Math.max(0, Math.min(ball.x, 600));
                    }
                    if (ball.y <= 0 || ball.y >= 600) {
                        ball.yDir *= -1;
                        ball.yVel = (ball.yVel + 1) % 31;
                        if (ball.xVel >= 31) ball.xVel = 20;
                        ball.y = Math.max(0, Math.min(ball.y, 600));
                    }
//                    System.out.println("this ball is in (" + ball.x + ", " + ball.y + ")");
                    ball.setLocation(ball.x, ball.y);
                }
                System.out.println("BALL COUNT: " + balls.size());
            }
        };
    }

    TimerTask FWTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (balls.size() < 100) {
                    BallFW ballFW = BallFWFactory.getBallFW(colors[colorInd]);
                    colorInd = ((++colorInd) % 6);
                    MovingBall newBall = new MovingBall(ballFW);
                    balls.add(newBall);
                    add(newBall);
                }
//                System.out.println("This is a new run");
                for (JPanel ballPanel : balls) {
                    MovingBall ball = (MovingBall) ballPanel;
                    ball.x += ball.xVel * ball.xDir;
                    ball.y += ball.yVel * ball.yDir;
                    if (ball.x <= 0 || ball.x >= 600) {
                        ball.xDir *= -1;
                        ball.xVel = (ball.xVel + 1) % 31;
                        ball.x = Math.max(0, Math.min(ball.x, 600));
                    }
                    if (ball.y <= 0 || ball.y >= 600) {
                        ball.yDir *= -1;
                        ball.yVel = (ball.yVel + 1) % 31;
                        if (ball.xVel >= 31) ball.xVel = 20;
                        ball.y = Math.max(0, Math.min(ball.y, 600));
                    }
//                    System.out.println("this ball is in (" + ball.x + ", " + ball.y + ")");
                    ball.setLocation(ball.x, ball.y);
                }
                System.out.println("BALL COUNT: " + balls.size());
            }
        };
    }

    public static void main(String[] args) {
        Balls frame = new Balls();

        frame.panel.setLayout(null);

        frame.panel.setSize(600, 600);

        frame.panel.add(frame.btnStart);
        frame.panel.add(frame.btnStop);
        frame.panel.add(frame.cbFlyweight);
        frame.panel.setBackground(Color.WHITE);
        frame.btnStart.setBounds(300, 500, 100, 30);
        frame.btnStop.setBounds(300, 500, 100, 30);
        frame.cbFlyweight.setBounds(310, 550, 80, 30);
        frame.btnStop.setVisible(false);
        frame.setLayout(null);
        frame.setContentPane(frame.panel);
        frame.setSize(640, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("Balls");
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        btnStart = new JButton();
        btnStart.setText("Start");
        panel.add(btnStart, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnStop = new JButton();
        btnStop.setText("Stop");
        panel.add(btnStop, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cbFlyweight = new JCheckBox();
        cbFlyweight.setText("Flyweight");
        panel.add(cbFlyweight, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}