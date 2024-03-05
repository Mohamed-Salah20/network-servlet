/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package newpackage;

import java.io.IOException;
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
    
    //TCP_File
    int TCP_FILE_PORT = 3457;
    Socket tcp_file_socket;
    
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
        } catch (IOException ex) {
            System.out.println("tcp msg server is down");
        }
        try {
            tcp_file_socket = new Socket(TCP_SERVER_IP, TCP_FILE_PORT);
        } catch (IOException ex) {
           System.out.println("tcp file server is down");
        }
            
            System.out.println("Connected to server");
            
     ///////Mo Salah note : added the BufferedWriter tcp_out_socket in the TcpServlet
//            BufferedWriter tcp_out_socket = new BufferedWriter(
//				new OutputStreamWriter(tcp_socket.getOutputStream())
//				);
//            
            servletContext.setAttribute("tcp_socket", tcp_socket);
            servletContext.setAttribute("file_socket", tcp_file_socket);
//            servletContext.setAttribute("tcp_out_socket", tcp_out_socket);
         /////////////////////
         
         ////////////////////
//         //udp:
//            udp_socket = new DatagramSocket();
//            UDP_IP_ADDRESS = InetAddress.getByName("127.0.0.1");
//            servletContext.setAttribute("udp_socket", udp_socket);
//            servletContext.setAttribute("UDP_IP_ADDRESS", UDP_IP_ADDRESS);
//            servletContext.setAttribute("UDP_PORT_NUM", UDP_PORT_NUM);
         ////////////////////   
         
         
        
    
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
