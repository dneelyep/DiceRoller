import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.dneelyep.utils.GridBagUtils;

/** Simple program to automatically roll dice in a D and D game. */
public class DiceRoller extends JFrame {

    public static void main(String[] args) {
        new DiceRoller();
    }

    public DiceRoller() {
        super("Dice Roller.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel(new GridBagLayout());
        add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 40;
        constraints.ipady = 40;

        for (dieTypes type : dieTypes.values()) {
            GridBagUtils.addAtCoords(new JLabel(type.toString() + ": "),
                    new Point(0, (Integer) type.getController().getClientProperty("y")), panel, constraints);

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
                reset();
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

        for (dieTypes type : dieTypes.values()) {
            type.getPanel().add(new JLabel(type.toString() + ": "));
            resultsPanel.add(type.getPanel());

            if (!type.getController().getValue().equals(0)) {
                for (int i = 0; i < (Integer) type.getController().getValue(); i++)
                    type.getPanel().add(new JLabel(Integer.toString((Math.abs(new Random().nextInt() % type.getValue()) + 1))));
            }
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (dieTypes type : dieTypes.values())
                    type.getPanel().removeAll();

                resultsFrame.setVisible(false);
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
