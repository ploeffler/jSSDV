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
package modelist;

import static com.sun.jmx.mbeanserver.Util.cast;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author User
 */
public class rxModeList implements ListCellRenderer {
 

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
      
        
        System.err.println(value.toString());
         System.err.println(value.getClass().toString());
     //   opmode mymode = value;
     modeItem  myitem = new modeItem(); 
     Opmode myopmode = cast(value);
     myitem.setValues(myopmode);
   //  myitem.setValues(value);
     
     if (isSelected) {
         try {
             myitem.setModeActive(Color.green);
         } catch (IOException ex) {
             Logger.getLogger(rxModeList.class.getName()).log(Level.SEVERE, null, ex);
         }
        
     }
 return myitem;
    }
}
