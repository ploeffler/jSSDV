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


import static com.sun.jmx.mbeanserver.Util.cast;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author User
 */
public class txStockListRenderer extends DefaultListCellRenderer  {

    /**
     *
     * @param list
     * @param data
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object data, int index, boolean isSelected, boolean cellHasFocus) {
        txItem item = new txItem();
        // File file = (File)value;
        TXStockImage tempobj;
        tempobj = cast(data);
        item.setName(tempobj.getNAME());
        item.setValues(tempobj);

        return item;
    }
    
    
}
