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

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author User
 */



public class rxItemList extends AbstractListModel {
  //  private String rxdir = "C:\\Users\\User\\Documents\\jSSDV\\rx\\";
    private File f = null;
  private File[] paths;
    // Static String imgdir = 
    private List<String> daten = new LinkedList<>();
    //     File("C:\\Users\\User\\Documents\\jSSDV\\").listFiles().toString();
    
    public rxItemList(String rxdir) {
    f = new File(rxdir);
    paths = f.listFiles();
      
    for(File file : paths) {
    daten.add(file.toString());
  //  System.err.println(file.toString());
    }
  //  daten.addAll(paths.getClass().);
    } 
    
    
    
    @Override
    public int getSize() {
     return  daten.size();
    }

    @Override
    public Object getElementAt(int index) {
      return daten.get(index);
    }
   



}
