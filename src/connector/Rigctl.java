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
package connector;

/**
 *
 * @author Rein
 */


import static com.sun.jmx.mbeanserver.Util.cast;
  import java.io.*;
    import java.net.Socket;
    import java.net.MalformedURLException;
    import org.apache.xmlrpc.XmlRpcException;
    import org.apache.xmlrpc.client.XmlRpcClient;
    import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
    import java.net.URL;

/**
 *
 * @author User
 */
public class Rigctl {
    
    static Socket sock;

    /**
     *
     */
    public PrintWriter pout;

    /**
     *
     */
    public BufferedReader in;
    static boolean opened = false;
    static String host = "localhost";
    static int port = 7362;
    static String Offset = "1000";
    static int OFF = 1000;
    static String[] freqs = {"0","0","0","0","0",};
    static String freq = "0";
    static XmlRpcClient client = null;
    static boolean function_ok = true;
    static int txmacro = 12;
    /**
     *
     * @param defhost
     * @param defport
     */
    public static void Rigctl() {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL("http://"+ host +":"+ port +"/"));
            opened = true;
        } catch (MalformedURLException ex) {
             System.err.println("Problem with xmlrpc: " + ex);
         
                     
                     
                     
             function_ok = false;
        }
        try {
            client = new XmlRpcClient();
        } 
        catch (Exception ex) {
            System.out.println("SQL problem");
        }
        client.setConfig(config);
    }

    
    
    public static Boolean config(String sethost,int setport) {
    host = sethost;
    port = setport;    
    return true;
    }
    
    /**
     *
     */
    public static void initfreqs() {
       OFF = Integer.parseInt(Offset);
//       freqs = Main.configuration.getQRGs();
//       freqs[0] = Main.configuration.getPreference(Main.configuration.qrg0);
//       freqs[1] = Main.configuration.getPreference(Main.configuration.qrg1);
//       freqs[2] = Main.configuration.getPreference(Main.configuration.qrg2);
//       freqs[3] = Main.configuration.getPreference(Main.configuration.qrg3);
//       freqs[4] = Main.configuration.getPreference(Main.configuration.qrg4);
//      freqs[0] = Main.configuration.getPreference("QRG0");
//      freqs[1] = Main.configuration.getPreference("QRG1");
//      freqs[2] = Main.configuration.getPreference("QRG2");
//      freqs[3] = Main.configuration.getPreference("QRG3");
//      freqs[4] = Main.configuration.getPreference("QRG4");
//      Main.Freq_offset = Integer.parseInt(Main.configuration.getPreference("RIGOFFSET","0"));
//      OFF = Main.Freq_offset;

    }

    /**
     *
     * @param Frequency
     * @return
     */
    public static String Setfreq (String Frequency) {

       if (function_ok & Frequency != null) {
         Double Outfreq = Double.parseDouble(Frequency);
         Outfreq -= OFF;

         Object[] params = new Object[]{new Double(Outfreq)};

        try {
            Object result =  client.execute("main.set_frequency", params);
            String myobject = result.toString();
            Double myfrequency = Double.parseDouble(myobject );
            Integer oldfrequency =  myfrequency.intValue();
            return oldfrequency.toString();

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set frequency: " + ex);
            function_ok = false;
        }
      }
         return "0";
     }

    /**
     *
     * @param newmodem
     * @return
     */
    public static String SetModem (String newmodem) {
         String myoutval = "";
       if (function_ok) {
        

         Object[] params = new Object[]{new String(newmodem)};

        try {
            Object result =  client.execute("modem.set_by_name", params);
            myoutval = result.toString();
            

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set modem: " + ex);
            function_ok = false;
        }
      }
         return myoutval;
     }
     
    
    public static int SetCenter (int center) {
         int myoutval = 0;
       if (function_ok) {
        

         Object[] params = new Object[]{center};

        try {
            Object result =  client.execute("modem.set_carrier", params);
            myoutval = cast(result);
             return myoutval;

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set modem: " + ex);
            function_ok = false;
        }
      }
         return myoutval;
     }
    
    
    
     public static int GetCenter () {
         int myoutval = 0;
       if (function_ok) {
        

         Object[] params = new Object[]{};

        try {
            Object result =  client.execute("modem.get_carrier", params);
            myoutval = cast(result);
             return myoutval;

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set modem: " + ex);
            function_ok = false;
        }
      }
         return myoutval;
     }
    
    
    
     /**
     *
     * @param beaconstring
     * @param modem
     * @return null
     */
    public static Boolean TXBeacon (String beaconstring, String modem) {

       
         

         Object[] txstring = new Object[]{beaconstring};
         Object[] beaconmodem = new Object[]{modem};
         Object[] nullobject = null;
        
         
         Object[] txmacroobject = new Object[txmacro];
        try {
           // rig.take_control
           Object result =  client.execute("rig.take_control", nullobject);
           result = client.execute("rig.take_control", nullobject);
            Object Ogetoldmode =  client.execute("modem.set_by_name", beaconmodem);
            String oldmode = Ogetoldmode.toString();
            Object[] Ooldmode = new Object[]{new String(oldmode)};
           // result =  client.execute("main.tx", nullobject);
            result =  client.execute("text.add_tx", txstring);
            result =  client.execute("main.run_macro", txmacroobject);
            System.out.println(result.toString());
            
            Object setmode = client.execute("modem.set_by_name",Ooldmode);
            result = client.execute("rig.release_control", nullobject);
            String myobject = setmode.toString();
            return true;
          

        } catch (XmlRpcException ex) {
            System.err.println("Problem with sendbeacon: " + ex);
            function_ok = false;
            return false;
        }
      
       
     }
     
    
    public static Boolean TXString (String txstring, String modem) {

       
         

         Object[] Otxstring = new Object[]{txstring};
         Object[] beaconmodem = new Object[]{modem};
         Object[] nullobject = null;
        
         
         Object[] txmacroobject = new Object[txmacro];
        try {
           // rig.take_control
           Object result =  null;
         //  result = client.execute("rig.take_control", nullobject);
            Object Ogetoldmode =  client.execute("modem.set_by_name", beaconmodem);
            String oldmode = Ogetoldmode.toString();
            Object[] Ooldmode = new Object[]{new String(oldmode)};
           
            result =  client.execute("text.add_tx", Otxstring);
             result =  client.execute("main.tx", nullobject);
            result =  client.execute("main.run_macro", txmacroobject);
            System.out.println(result.toString());
            
            Object setmode = client.execute("modem.set_by_name",Ooldmode);
          //  result = client.execute("rig.release_control", nullobject);
            String myobject = setmode.toString();
            return true;
          

        } catch (XmlRpcException ex) {
            System.err.println("Problem with sendstring: " + ex);
            function_ok = false;
            return false;
        }
      
       
     }
     
    
    
    
    public static String SetMaster () {

       
         

         Object[] params = null;
         

        try {
            Object result =  client.execute("rig.take_control", params);
            String myobject = result.toString();
            
          

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set frequency: " + ex);
            function_ok = false;
        }
      
         return "0";
     }
    /**
     *
     * @param spo
     * @return
     */
    public static String SetSpotting (Boolean spo) {

       
         
        Object[] params = new Object[]{spo};
        Object result = null;
        try {
           
            result =  client.execute("spot.set_auto", params);   
            String myobject = result.toString();
            
        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc set spotting: " + ex);
            function_ok = false;
        }
      
         return "0";
     }
     
    /**
     *
     * @param rxid
     * @return
     */
    public static String SetRxID (Boolean rxid) {

       
         
        Object[] params = new Object[]{rxid};
         
        String myretval = "";
        try {
           
           Object result =  client.execute("main.set_rsid", params);    
            
           
            myretval = result.toString();
            
          

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc main.set_rsid: " + ex);
            function_ok = false;
        }   catch (NullPointerException np) {
            System.err.println("Problem with xmlrpc set_rsid: " + np);
            return "";
        }
      
         return myretval;
     }

    /**
     *
     * @param sql
     * @return
     */
    public static String SetSQL (Boolean sql) {

       
         
        Object[] params = new Object[]{new Boolean(sql)};
         Object result = null;
        String myretval = "";
        try {
           
             result =  client.execute("main.set_squelch", params);    
            
           
            myretval = result.toString();
            
          

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc main.set_squelch: " + ex);
            function_ok = false;
        }
      
         return myretval;
     }      

    /**
     *
     * @param Sql
     * @return
     */
    public static String SetSql (int Sql) {
         
         

             double d;
        
            if (function_ok & Sql != 0) {
              
                d = Double.parseDouble(Integer.toString(Sql));
                Object[] params = new Object[]{new Double(d)};

            try {
                Object result =  client.execute("main.set_squelch_level", params);
                String myobject = result.toString();
                Double mySqlLevel = Double.parseDouble(myobject );
                Integer oldlevel =  mySqlLevel.intValue();
                return oldlevel.toString();

            } catch (XmlRpcException ex) {
                System.out.println("Problem with xmlrpc set squelch_level: " + ex);
                function_ok = false;
            }
          }
             return "0";      
     }

    /**
     *
     * @return
     */
    public static String Getfreq () {


        Object[] params = null;

      if (function_ok) {
        try {
            Object result =  client.execute("main.get_frequency", params);
            String myobject = result.toString();
            Double myfrequency = Double.parseDouble(myobject );
            Integer frequency =  myfrequency.intValue();
            Integer curfreq = myfrequency.intValue();
 //           Main.CurrentFreq = Integer.toString(curfreq);
            
            String newfreq = Integer.toString(frequency);
 //         System.out.println(newfreq);
            return newfreq;
            
        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get frequency: " + ex);
            function_ok = false;
        }
      }
        return "0";
     }

    /**
     *
     * @return
     */
    public static String GetRxData () {


        Object[] params = null;

      if (function_ok) {
        try {
            Object result =  client.execute("rx.get_data", params);
   //         String myobject = new String();
           
          
          //  System.out.println(SerializationUtils.deserialize(result));
         
            String retstring = cast(result);
            return retstring;

        } catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get frequency: " + ex);
            function_ok = false;
        }
      }
        return "";
     }
     
    /**
     *
     * @return
     */
    public static String GetSNR () {

        Object[] params = null;

       if (function_ok) {

        try {
            Object result =  client.execute("main.get_status1", params);
            return result.toString();
         }
        catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get snr: " + ex);
            function_ok = false;
        }
        catch (NullPointerException np) {
  //          Main.log.writelog("Problem with xmlrpc get snr: " + np, true)
            return "";
        }
       }

        return "";

     }

    /**
     *
     * @return
     */
    public static int GetQual () {

        Object[] params = null;
       int myretval = 0;     
       if (function_ok) {

        try {
            Object result =  client.execute("modem.get_quality", params);
            Double str = Double.parseDouble(result.toString());
            return str.intValue();
            
         }
        catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get quality: " + ex);
            function_ok = false;
        }
        catch (NullPointerException np) {
            System.err.println("Problem with xmlrpc get quality: " + np);
            return myretval;
        }
       }

        return myretval;

     }

    /**
     *
     * @return
     */
    public static String GetModem () {

        Object[] params = null;

       if (function_ok) {

        try {
            Object result =  client.execute("modem.get_name",params);
            String str = result.toString();
            
            return str;
         }
        catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get quality: " + ex);
            function_ok = false;
        }
        catch (NullPointerException np) {
  //          Main.log.writelog("Problem with xmlrpc get quality: " + np, true)
            return "";
        }
       }

        return "";

     }
      
    /**
     *
     * @return
     */
    public static boolean GetSQL () {

        Object[] params = null;

       if (function_ok) {

        try {
            Object result =  client.execute("main.get_squelch", params);

            String str = result.toString();
//          System.out.println("STR:" + str);          
            if (str.equals("true") | str.equals("1")){
                return true;
            } else {
                return false;
            }
         }
        catch (XmlRpcException ex) {
            System.err.println("Problem with xmlrpc get quality: " + ex);
            function_ok = false;
        }
        catch (NullPointerException np) {
//            Main.log.writelog("Problem with xmlrpc get quality: " + np, true);
//            System.out.println("SQL null"); 
            return false;
        }
       }

        return false;

     }     
       
    /**
     *
     * @param msg
     * @param time
     */
    public void Message(String msg, int time) {
        System.err.println(msg+" "+time);
        
    }

    /**
     *
     * @param server
     */
    public static void Loadfreqs (String server) {
   /*
        File f = new File(Main.HomePath + Main.Dirprefix + server + ".chn");
        if (f.exists()) {
            try{
    // Open the file 
                FileInputStream fstream = new FileInputStream(Main.HomePath + Main.Dirprefix + server + ".chn");
    // Get the object of DataInputStream
                DataInputStream lfin = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(lfin));
                String strLine;
    //Read File Line By Line
                strLine = br.readLine();
                freqs = strLine.split(",");


    //Close the input stream
                lfin.close();
            }catch (Exception e){//Catch exception if any
                 System.err.println("Error: " + e.getMessage());
            }

        }
*/
    }


}
