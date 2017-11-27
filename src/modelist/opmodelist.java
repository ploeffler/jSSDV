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

import XMLhandling.getOpModes;
import XMLhandling.getTXStockitems;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author User
 */
public class opmodelist extends AbstractListModel {

    private List<Opmode> daten = new LinkedList<>();
//static File modelsfile =  

    public opmodelist(String dirname) throws XMLStreamException, FileNotFoundException {
String dirPath = dirname;
        String file = "modes.xml";
        File dir = new File(dirPath);
        
        
        String configfile = dirname + "\\"+file;

        InputStream xmlStream = new FileInputStream(configfile);
       // TXStockImage newstock = new TXStockImage();

        getOpModes reader = new getOpModes();
       // newstock = reader.readXml(xmlStream);
        daten = reader.readXml(xmlStream);
        }
       
    public DefaultComboBoxModel getModemModel (int index){
    Opmode mymode = daten.get(index);
    String[] modemarray = mymode.getModems().split(",");
    return new DefaultComboBoxModel(modemarray);
    
    }

    @Override
    public int getSize() {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return daten.size();
    }

    @Override
    public Object getElementAt(int index) {
        return daten.get(index);
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
