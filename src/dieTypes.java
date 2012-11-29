/*
 * This file is part of <project name here>.
 *
 * <project name here> is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * <project name here> is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with <project name here>.  If not, see <http://www.gnu.org/licenses/>.
 */

import javax.swing.*;
import java.util.Arrays;

/** An enumeration of all the types of die available to roll. */
public enum dieTypes {
    d3(3,   (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 0)),
    d4(4,   (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 1)),
    d6(6,   (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 2)),
    d8(8,   (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 3)),
    d10(10, (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 4)),
    d12(12, (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 5)),
    d16(16, (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 6)),
    d20(20, (JSpinner) DiceRoller.initAtCoords(new JSpinner(new SpinnerListModel(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))), 1, 7));

    private final int value;

    /** Spinner to change the number of this die type that the user is rolling. */
    private final JSpinner controller;

    dieTypes(int value, JSpinner controller) {
        this.value = value;
        this.controller = controller;
    }

    public int getValue() {
        return value;
    }

    public JSpinner getController() {
        return controller;
    }

}
