import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.dneelyep.utils.GridBagUtils;

/** Simple program to automatically roll dice in a D and D game. */
public class DiceRoller extends JFrame {

    private final JButton rollButton = new JButton(new ImageIcon("res/img/roll.png"));

    public static void main(String[] args) {
        new DiceRoller();
    }

    private DiceRoller() {
        super("Dice Roller.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 228, 209));
        add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 40;
        constraints.ipady = 40;

        for (dieTypes type : dieTypes.values()) {
            GridBagUtils.addAtCoords(new JLabel(type.toImage()),
                    new Point((Integer) type.getController().getClientProperty("x") - 1,
                            (Integer) type.getController().getClientProperty("y")), panel, constraints);

            GridBagUtils.addAtCoords(type.getController(), new Point((Integer) type.getController().getClientProperty("x"),
                    (Integer) type.getController().getClientProperty("y")), panel, constraints);
        }

        rollButton.setContentAreaFilled(false);
        rollButton.setBorderPainted(false);
        rollButton.setRolloverIcon(new ImageIcon(("res/img/rollRollover.png")));
                rollButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        roll();
                        rollButton.setEnabled(false);
                    }
                });
        rollButton.setEnabled(false);
        GridBagUtils.addAtCoords(rollButton, new Point(0, 8), panel, constraints);

        JButton resetButton = new JButton(new ImageIcon("res/img/reset.png"));
        resetButton.setContentAreaFilled(false);
        resetButton.setBorderPainted(false);
        resetButton.setRolloverIcon(new ImageIcon(("res/img/resetRollover.png")));
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset();
                        rollButton.setEnabled(false);
                    }
                });
        GridBagUtils.addAtCoords(resetButton, new Point(2, 8), panel, constraints);

        for (dieTypes type : dieTypes.values()) {
            type.getController().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    rollButton.setEnabled(false);
                    for (dieTypes dieType : dieTypes.values()) {
                        if (dieType.getController().getValue() != 0)
                            rollButton.setEnabled(true);
                    }
                }
            });
        }

        setVisible(true);
        pack();

        //UIDefaults spinnerDefaults = new UIDefaults();

        //System.out.println("Properties: " + new JSpinner().getUI().toString());
        //spinnerDefaults.put("")

/*        sliderDefaults.put("Slider.thumbWidth", 20);
        sliderDefaults.put("Slider.thumbHeight", 20);
        sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new Painter() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.RED);
                g.fillOval(1, 1, w-3, h-3);
                g.setColor(Color.WHITE);
                g.drawOval(1, 1, w-3, h-3);
            }
        });*/

        //System.out.println(UIManager.getDefaults());
    }

    /** Use the input in the GUI to make a random roll. */
    private void roll() {
        final JFrame resultsFrame = new JFrame("Roll results");
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultsFrame.add(resultsPanel);

        // TODO Replace the Roll/etc buttons with nice-looking images.
        for (dieTypes type : dieTypes.values()) {
            if (!type.getController().getValue().equals(0)) {
                type.getPanel().add(new JLabel(type.toImage()));
                resultsPanel.add(type.getPanel());

                int maxNumber = 0;
                for (dieTypes dieType : dieTypes.values()) {
                    if ((Integer) dieType.getController().getValue() > maxNumber)
                        maxNumber = (Integer) dieType.getController().getValue();
                }

                // Always add the largest amount of pictures, to keep spacing in the results panel consistent.
                for (int numPictures = 0; numPictures < maxNumber; numPictures++) {
                    if (numPictures >= (Integer) type.getController().getValue()) { // Use empty pictures when
                        type.getPanel().add(new JLabel(new ImageIcon("res/img/empty.png")));
                    }
                    else {
                        int result = Math.abs(new Random().nextInt() % type.getValue()) + 1;

                        if (result >= 1 && result <= 20)
                            type.getPanel().add(new JLabel(new ImageIcon("res/img/" + result + ".png")));

                            // TODO Is this if else check even needed?
                        else
                            type.getPanel().add(new JLabel(Integer.toString(result)));
                    }
                }

//                for (int i = 0; i < (Integer) type.getController().getValue(); i++) {
//                    int result = Math.abs(new Random().nextInt() % type.getValue()) + 1;
//
//                    if (result >= 1 && result <= 20)
//                        type.getPanel().add(new JLabel(new ImageIcon("res/img/" + result + ".png")));
//
//                    // TODO Is this if else check even needed?
//                    else
//                        type.getPanel().add(new JLabel(Integer.toString(result)));
//                }
            }
        }

        JButton closeButton = new JButton(new ImageIcon("res/img/close.png"));
        closeButton.setRolloverIcon(new ImageIcon("res/img/closeRollover.png"));
                closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (dieTypes type : dieTypes.values())
                    type.getPanel().removeAll();

                resultsFrame.setVisible(false);
                rollButton.setEnabled(true);
            }
        });

        resultsPanel.add(closeButton);

        resultsFrame.pack();
        resultsFrame.setVisible(true);
    }

    /** Reset the all dice values in the provided panel. */
    private void reset() {
        for (dieTypes dieType : dieTypes.values())
            dieType.getController().setValue(0);
    }
}
