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

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/**
 *
 * @author User
 */
public class rxListrenderer extends DefaultListCellRenderer {
 
    
    
    
    
    
    @Override
 public Component getListCellRendererComponent(JList list,Object value, int index, boolean isSelected, boolean cellHasFocus ){
     rxItem  item = new rxItem(); 
     // File file = (File)value;
        try {
            item.setValues(value.toString());
        } catch (IOException ex) {
            Logger.getLogger(rxListrenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 return item;    
 }   
}
