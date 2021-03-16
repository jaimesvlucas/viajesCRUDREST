/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Destinos;
import modelo.ViajesCRUD;

/**
 *
 * @author User
 */
public class ServletDestinos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int id;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
            /* TODO output your page here. You may use following sample code. */
            if(op.equals("listar")){
                List<Destinos> misDestinos = ViajesCRUD.getDestinos();
                request.setAttribute("destinos", misDestinos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
            if(op.equals("insert1")){
                request.getRequestDispatcher("insert.jsp").forward(request, response);
            }
            if(op.equals("insert2")){
                Destinos miDestino = new Destinos();
                miDestino.setCiudad(request.getParameter("ciudad"));
                miDestino.setImagen(request.getParameter("imagen"));
                String precio=request.getParameter("precio");
                miDestino.setPrecio(Float.parseFloat(precio));
                ViajesCRUD.insertaDestino(miDestino);
                out.println("<h1>Se ha insertado el destino</h1>");
                out.println("<a href='index.html'>Volver a index</a>");
            }
            if(op.equals("borrar")){
                if(ViajesCRUD.destroyDestino(Integer.parseInt(request.getParameter("id")))>0){
                    out.println("<h1>Se ha borrado el destino</h1>");
                    out.println("<a href='index.html'>Volver a index</a>");
                }else{
                    out.println("<h1>No se ha borrado el destino</h1>");
                    out.println("<a href='index.html'>Volver a index</a>");
                }
            }
            if(op.equals("update1")){
                int id = Integer.parseInt(request.getParameter("id"));
                Destinos miDestino = ViajesCRUD.getDestino(id);
                request.setAttribute("destino", miDestino);
                this.id = miDestino.getId();
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
            if(op.equals("update2")){
                Destinos miDestino = new Destinos();
                miDestino.setId(this.id);
                miDestino.setCiudad(request.getParameter("ciudad"));
                miDestino.setImagen(request.getParameter("imagen"));
                String precio=request.getParameter("precio");
                miDestino.setPrecio(Float.parseFloat(precio));
                ViajesCRUD.actualizaDestino(miDestino);
                //Con el metodo merge que utilizamos en insertar tambien actualiza
                //ProductosCRUD.insertaProducto(miProducto);
                request.setAttribute("mensaje", "El destino se ha actualizado");
                request.setAttribute("destino", miDestino);
                request.getRequestDispatcher("update.jsp").forward(request, response);
                //out.println("<h1>Se ha editado el producto</h1>");
                //out.println("<a href='index.html'>Volver a index</a>");
            }
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
