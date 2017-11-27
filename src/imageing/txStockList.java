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

import XMLhandling.getTXStockitems;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.AbstractListModel;
import imageing.TXStockImage;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JList;
import javax.xml.stream.XMLStreamException;
import org.apache.commons.io.input.XmlStreamReader;

/**
 *
 * @author User
 */
public class txStockList extends AbstractListModel {
    //    private enum Element {IMAGE, NAME, ORIGFILE, DESCRIPTION,STARTX,STARTY,XSIZE,YSIZE,ICONFILE}

    private List<TXStockImage> daten = new LinkedList<>();

    public txStockList(String directory) throws FileNotFoundException, XMLStreamException {
        String dirPath = directory;
        File dir = new File(dirPath);
        String[] myFiles = dir.list(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(".xml");
            }
        });
        
        for(String file: myFiles) {
        String configfile = directory + "\\"+file;

        InputStream xmlStream = new FileInputStream(configfile);
       // TXStockImage newstock = new TXStockImage();

        getTXStockitems reader = new getTXStockitems();
       // newstock = reader.readXml(xmlStream);
        daten.add(reader.readXml(xmlStream));
        }
       
    }

    @Override
    public int getSize() {
        return daten.size();

    }

    @Override
    public Object getElementAt(int index) {
        return daten.get(index);
    }
   
}
