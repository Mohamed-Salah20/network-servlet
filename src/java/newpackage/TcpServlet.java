/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author mohamed
 */
@MultipartConfig
public class TcpServlet extends HttpServlet {

    Socket tcp_socket;
    Socket tcp_file_socket;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        tcp_socket = (Socket) servletContext.getAttribute("tcp_socket");
        tcp_file_socket = (Socket) servletContext.getAttribute("file_socket");

        //------------------------Kirollos----------------------------
        File file = (File) request.getAttribute("SavedFile");
        if (file != null) {

            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            long fileSize = file.length();

            ObjectOutputStream out = new ObjectOutputStream(tcp_file_socket.getOutputStream());
            out.writeUTF(file.getName()); //sending file name
            out.writeLong(fileSize); //sending file size to check connection
            out.flush();

            System.out.println("File sent successfully");

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);    //sending file data
                out.flush();
            }
        }
        //----------------------------------------------------
        ////////Mo Salah note : removed BufferedWriter tcp_out_socket intialization from the ConnServletListener context/////////// 
        String dataToSend = request.getParameter("msg");

        if (tcp_socket != null && !dataToSend.isEmpty()) {

            
            DataOutputStream data = new DataOutputStream(tcp_socket.getOutputStream());
            data.writeBytes(dataToSend);
            data.flush();
            System.out.println("Sent : " + dataToSend);
            /*
            BufferedWriter tcp_out_socket = new BufferedWriter(
                    new OutputStreamWriter(tcp_socket.getOutputStream())
            );

            tcp_out_socket.write(dataToSend);
            tcp_out_socket.newLine();
            tcp_out_socket.flush();*/
            // 
//            if (tcp_out_socket != null) {
//                // Send data to the server
//                
//
//            }
        }

        response.sendRedirect("index.html");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
