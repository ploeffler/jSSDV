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

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public final class TXStockImage {

    private int ID;
    private String ICONFILE;
    private String PARENTIMG;
    private int STARTX;
    private int STARTY;
    private int XSIZE;
    private int YSIZE;
    private String NAME;
    private String DESCRIPTION;
    private String LASTTX;
    private String TAGS;

   

  
    
    public TXStockImage (){
        this.setDESCRIPTION("NewFile");
        this.setICONFILE("nofile");
        this.setID(1);
        this.setNAME("New");
        this.setPARENTIMG("Testbild.png");
        this.setSTARTX(0);
        this.setSTARTY(0);
        this.setXSIZE(320);
        this.setYSIZE(240);
        this.setLASTTX("never sent");
        this.setTAGS("");
        }
    
    
    
   
    
    
    
    
    @Override
    public String toString() {
    return NAME+DESCRIPTION;    
    }
    
    
     public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }
      public String getLASTTX() {
        return LASTTX;
    }

    public void setLASTTX(String LASTTX) {
        this.LASTTX = LASTTX;
    }
    
    
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getICONFILE() {
        return ICONFILE;
    }

    public void setICONFILE(String ICONFILE) {
        this.ICONFILE = ICONFILE;
    }

    public String getPARENTIMG() {
        return PARENTIMG;
    }

    public void setPARENTIMG(String PARENTIMG) {
        this.PARENTIMG = PARENTIMG;
    }

    public int getSTARTX() {
        return STARTX;
    }

    public void setSTARTX(int STARTX) {
        this.STARTX = STARTX;
    }

    public int getSTARTY() {
        return STARTY;
    }

    public void setSTARTY(int STARTY) {
        this.STARTY = STARTY;
    }

    public int getXSIZE() {
        return XSIZE;
    }

    public void setXSIZE(int XSIZE) {
        this.XSIZE = XSIZE;
    }

    public int getYSIZE() {
        return YSIZE;
    }

    public void setYSIZE(int YSIZE) {
        this.YSIZE = YSIZE;
    }
    

    

}
