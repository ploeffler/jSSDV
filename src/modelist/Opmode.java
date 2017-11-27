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

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

/**
 *
 * @author User
 */
 

public class Opmode {
     
    private int ID;
    private String NAME;
    private int HEADERREPEAT;
    private String SHORTNAME;
    private String MODEMS; 
    private int CENTER;
    private Icon ICON;
    private String DESCRIPTION;
    private String DATASTRUCTURE;
    private int COMPRESSION;
    private String ENCODE;
    private String PAYLOAD;
    
    
    public void Opmode(){
      setID(1); 
      setName("New");
      setCenter(1700);
      
    }
    
    
    public DefaultComboBoxModel getModemsModel(){
       return new DefaultComboBoxModel(this.getModems().split(","));
    }
     public DefaultComboBoxModel getEncModel(){
       return new DefaultComboBoxModel(this.getEncode().split(","));
    }
    
    @Override
    public String toString(){
       return NAME; 
    }
    public void setPaiload(String imgproc){
        this.PAYLOAD = imgproc;
    }
    public String getPaiload(){
      return PAYLOAD;  
    }
    public String getEncode(){
        return ENCODE;
    }
    
    public void setEncode(String encoders){
        this.ENCODE = encoders;
    }
    
    public int getCompression(){
        return COMPRESSION;
    }
    
    public void setCompression(int val){
     this.COMPRESSION=val;
     
     
    }
    
    public String getDescription(){
        return DESCRIPTION;
    }
    
    public void setDescription(String text){
        this.DESCRIPTION = text;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }
    
    
    public int getCenter() {
        return CENTER;
    }

    public void setCenter(int center) {
        this.CENTER = center;
    }
    
    
    public String getName() {
        return NAME;
    }

    public void setModems(String modems) {
        this.MODEMS = modems;
    }
    
    public String getModems() {
        return MODEMS;
    }

    public void setName(String name) {
        this.NAME = name;
    }
    
    public String getShortName() {
        return SHORTNAME;
    }

    public void setShortName(String name) {
        this.SHORTNAME = name;
    }
    
    public int getHeaderrepeat() {
        return HEADERREPEAT;
    }

    public void setHeaderrepeat(int rep) {
        this.HEADERREPEAT = rep;
    }
}
