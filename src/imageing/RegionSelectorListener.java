/*
 * Copyright (C) 2017 User
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package imageing;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.scene.Parent;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class RegionSelectorListener extends MouseAdapter {
    final JLabel label;

    public RegionSelectorListener(JLabel theLabel) {
        this.label = theLabel;
        theLabel.addMouseListener(this);
    }

    Point start = null;
    Point end = null;
    public void mouseClicked(MouseEvent event) {
        if (start == null) { //If the first corner is not set...

            start = event.getPoint(); //set it.

        } else { //if the first corner is already set...

            //calculate width/height substracting from origin
            int width = event.getX() - start.x;
            int height = event.getY() - start.y;
            end = event.getPoint();
            
            //output the results (replace this)
            System.out.println("Selected X is: "+ start.x+"/"+end.x);
            System.out.println("Selected Y is: "+ start.y+"/"+end.y);
            System.out.println("Selected dimensions: "+ width+"x"+height);
            
        }
    }
}