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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author User
 */
public class iAPRSclient {
    Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
     public void iAprsclient(String servername, int port) throws IOException {
         try {
            pingSocket = new Socket(servername, port);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        out.println("ping");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
        
    
     }
    
    
    
    
}
