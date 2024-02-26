/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package newpackage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author mohamed
 */
public class ConnServletListener implements ServletContextListener {
    
    //tcp:
    String TCP_SERVER_IP = "localhost";
    int TCP_SERVER_PORT = 3456;
    Socket tcp_socket;
    //
    
    //udp:
//    InetAddress UDP_IP_ADDRESS;
//    int UDP_PORT_NUM = 9999;
//    DatagramSocket udp_socket;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    
        ServletContext servletContext = sce.getServletContext();

        try {
          ////////////////////
            //tcp:
            tcp_socket = new Socket(TCP_SERVER_IP,TCP_SERVER_PORT);
            System.out.println("Connected to server");
            
            //
            PrintWriter tcp_out_socket = new PrintWriter(
				new OutputStreamWriter(tcp_socket.getOutputStream())
				);
            
            servletContext.setAttribute("tcp_socket", tcp_socket);
            servletContext.setAttribute("tcp_out_socket", tcp_out_socket);
         /////////////////////
         
         ////////////////////
//         //udp:
//            udp_socket = new DatagramSocket();
//            UDP_IP_ADDRESS = InetAddress.getByName("127.0.0.1");
//            servletContext.setAttribute("udp_socket", udp_socket);
//            servletContext.setAttribute("UDP_IP_ADDRESS", UDP_IP_ADDRESS);
//            servletContext.setAttribute("UDP_PORT_NUM", UDP_PORT_NUM);
         ////////////////////   
         
         
        } catch (IOException ex) {
            Logger.getLogger(ConnServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
