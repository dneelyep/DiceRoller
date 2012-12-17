import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Arrays;

/** An enumeration of all the types of die available to roll. */
public enum dieTypes {
    d3(3,   new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 0)),
    d4(4,   new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 1)),
    d6(6,   new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 2)),
    d8(8,   new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 3)),
    d10(10, new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 4)),
    d12(12, new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 5)),
    d16(16, new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 6)),
    d20(20, new JPanel(), (JSpinner) giveCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 7));

    private final int value;

    /** Spinner to change the number of this die type that the user is rolling. */
    private final JSpinner controller;

    /** Panel that displays the random result of a given dieType being rolled. */
    private final JPanel resultPanel;

    dieTypes(int value, JPanel resultPanel, JSpinner controller) {
        this.value = value;
        this.resultPanel = resultPanel;
        this.controller = controller;
    }

    public int getValue() {
        return value;
    }

    public JSpinner getController() {
        return controller;
    }

    public JPanel getPanel() {
        return resultPanel;
    }

    @Override
    /** Get a string representation of this dieType. */
    public String toString() {
        return "d" + value;
    }

    /** Initialize a given component at the point (x, y). */
    private static JComponent giveCoords(JComponent component, int x, int y) {
        component.putClientProperty("x", x);
        component.putClientProperty("y", y);
        return component;
    }
}
