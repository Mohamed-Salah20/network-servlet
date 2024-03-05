/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//https://docs.oracle.com/javaee/7/api/javax/servlet/annotation/MultipartConfig.html
@MultipartConfig
public class FilterUploadServlet extends HttpServlet {

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

        /*
        //https://stackoverflow.com/questions/1748259/get-form-parameters-from-multipart-request-without-getting-the-files

         */
//https://docs.oracle.com/javaee/7/api/javax/servlet/http/Part.html        
        Part optionPart = request.getPart("option");

        String option = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(optionPart.getInputStream()));
        option = reader.readLine();
        if (!request.getPart("file").getSubmittedFileName().isEmpty()) {
            File file = saveSubmittedFileToPath(request);
            request.setAttribute("SavedFile", file);
        }

        if (option != null && option.equals("udp")) {
            request.getRequestDispatcher("/UdpServlet").forward(request, response);
        } else if (option != null && option.equals("tcp")) {
            request.getRequestDispatcher("/TcpServlet").forward(request, response);
        } else {
            // Handle invalid option
            // You may want to display an error message or redirect to a default servlet
        }
    }

    private File saveSubmittedFileToPath(HttpServletRequest request) throws ServletException {
        try {
            Part filePart = request.getPart("file");
            // get current app path
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String appPath = request.getServletContext().getRealPath("");
            //create Directory
            String pathToSave = appPath + "UploadsFile" + File.separator;
            File dir = new File(pathToSave);
            if (!dir.exists()) {
                dir.mkdir();
            }
            pathToSave += fileName;
            File file = new File(pathToSave);
            filePart.write(pathToSave);         //save file to get absolute path to get file content
            return file;
        } catch (IOException ex) {
            Logger.getLogger(FilterUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
