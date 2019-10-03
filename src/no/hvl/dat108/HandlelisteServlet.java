package no.hvl.dat108;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HandlelisteServlet")
public class HandlelisteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null)
            response.sendRedirect("login.html");
        else {
            Handleliste handleliste = (Handleliste) session.getAttribute("handleliste");
            String item = request.getParameter("item");
            String delete = request.getParameter("delete");

            if (item != null && !item.isEmpty())
                handleliste.add(item);
            else if (delete != null && !delete.isEmpty())
                handleliste.remove(delete);

            response.setContentType("text/html");
            generatePage(response.getWriter(), handleliste);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null)
            response.sendRedirect("login.html");
        else {
            Handleliste handleliste = (Handleliste) session.getAttribute("handleliste");
            if (handleliste == null)
                handleliste = new Handleliste();
            generatePage(response.getWriter(), handleliste);
        }
    }

    private void generatePage(PrintWriter out, Handleliste handleliste) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"ISO-8859-1\">");
        out.println("    <title>Handleliste</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Min Handleliste</h1>");
        out.println("<form action=\"handleliste\" method=\"post\">");
        out.println("   <p><input type=\"submit\" value=\"Legg til\"><input type=\"text\" name=\"item\" autofocus></p>");
        out.println("</form>");
        out.println(handleliste.convertToHtml());
        out.println("</body>");
        out.println("</html>");
    }
}
