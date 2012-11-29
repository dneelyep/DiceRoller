import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.dneelyep.utils.GridBagUtils;

/** Simple program to automatically roll dice in a D and D game. */
public class DiceRoller extends JFrame {

    /** The number of dice allowed in the spinners. */
    private Integer[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    /** A Map linking together the die controllers and the panels that display their randomized output. */
    private Map<JSpinner, JPanel> controllerPanelMap = new HashMap() {{
        for (dieTypes type : dieTypes.values())
            put(type.getController(), new JPanel());
    }};

    public static void main(String[] args) {
        new DiceRoller();
    }

    public DiceRoller() {
        super("Dice Roller.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel(new GridBagLayout());
        add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 20;
        constraints.ipady = 10;

        GridBagUtils.addAtCoords(new JLabel("d3:"), new Point(0, 0), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d4:"), new Point(0, 1), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d6:"), new Point(0, 2), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d8:"), new Point(0, 3), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d10:"), new Point(0, 4), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d12:"), new Point(0, 5), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d16:"), new Point(0, 6), panel, constraints);
        GridBagUtils.addAtCoords(new JLabel("d20:"), new Point(0, 7), panel, constraints);

        for (dieTypes type : dieTypes.values()) {
            GridBagUtils.addAtCoords(type.getController(), new Point((Integer) type.getController().getClientProperty("x"),
                    (Integer) type.getController().getClientProperty("y")), panel, constraints);
        }

        JButton rollButton = new JButton("Roll!");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roll();
            }
        });
        GridBagUtils.addAtCoords(rollButton, new Point(0, 8), panel, constraints);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset(panel);
            }
        });
        GridBagUtils.addAtCoords(resetButton, new Point(1, 8), panel, constraints);

        setVisible(true);
        pack();
    }

    /** Use the input in the GUI to make a random roll. */
    public void roll() {
        final JFrame resultsFrame = new JFrame("Roll results");
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultsFrame.add(resultsPanel);

        controllerPanelMap.get(dieTypes.d3.getController()).add(new JLabel("d3: "));
        controllerPanelMap.get(dieTypes.d4.getController()).add(new JLabel("d4: "));
        controllerPanelMap.get(dieTypes.d6.getController()).add(new JLabel("d6: "));
        controllerPanelMap.get(dieTypes.d8.getController()).add(new JLabel("d8: "));
        controllerPanelMap.get(dieTypes.d10.getController()).add(new JLabel("d10: "));
        controllerPanelMap.get(dieTypes.d12.getController()).add(new JLabel("d12: "));
        controllerPanelMap.get(dieTypes.d16.getController()).add(new JLabel("d16: "));
        controllerPanelMap.get(dieTypes.d20.getController()).add(new JLabel("d20: "));

        for (JPanel panel : controllerPanelMap.values()) {
            resultsPanel.add(panel);
        }

        for (JSpinner spinner : controllerPanelMap.keySet()) {

            if (! spinner.getValue().equals(0)) {

                for (int i = 0; i < (Integer) spinner.getValue(); i++) {
                    // TODO Simplify this much
                    if (spinner == dieTypes.d3.getController())
                        controllerPanelMap.get(dieTypes.d3.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 3) + 1))));
                    else if (spinner == dieTypes.d4.getController())
                        controllerPanelMap.get(dieTypes.d4.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 4) + 1))));
                    else if (spinner == dieTypes.d6.getController())
                        controllerPanelMap.get(dieTypes.d6.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 6) + 1))));
                    else if (spinner == dieTypes.d8.getController())
                        controllerPanelMap.get(dieTypes.d8.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 8) + 1))));
                    else if (spinner == dieTypes.d10.getController())
                        controllerPanelMap.get(dieTypes.d10.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 10) + 1))));
                    else if (spinner == dieTypes.d12.getController())
                        controllerPanelMap.get(dieTypes.d12.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 12) + 1))));
                    else if (spinner == dieTypes.d16.getController())
                        controllerPanelMap.get(dieTypes.d16.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 16) + 1))));
                    else if (spinner == dieTypes.d20.getController())
                        controllerPanelMap.get(dieTypes.d20.getController()).add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % 20) + 1))));
                }
            }
        }


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JPanel panel : controllerPanelMap.values())
                    panel.removeAll();

                resultsFrame.setVisible(false);
            }
        });

        resultsPanel.add(closeButton);

        resultsFrame.pack();
        resultsFrame.setVisible(true);
    }

    /** Reset the all dice values in the provided panel. */
    private void reset(JPanel panel) {
        for (dieTypes dieType : dieTypes.values())
            dieType.getController().setValue(0);
    }

    // TODO Move this to the enum?
    /** Initialize a given component at the point (x, y). */
    protected static JComponent initAtCoords(JComponent component, int x, int y) {
        component.putClientProperty("x", x);
        component.putClientProperty("y", y);
        return component;
    }
}
