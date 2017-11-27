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
package jssdv;

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import modelist.Opmode;

/**
 *
 * @author User
 */
public class sysopmodelist extends AbstractListModel {

    private List<Opmode> daten = new LinkedList<Opmode>();

    public sysopmodelist(String dir) {

        
        
        
        
    }

    public int getModesNumber() {
        return daten.size();
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getElementAt(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String toString(int index){
       Opmode m = daten.get(index);
       return m.getName();
    }
}
