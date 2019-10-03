package no.hvl.dat108;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int MAX_INACTIVE_INTERVAL = Integer.parseInt(getInitParameter("inactiveInterval"));

        String userPass = request.getParameter("password");
        String password = getInitParameter("password");
        PrintWriter out = response.getWriter();

        if (userPass.equals(password)) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
            if(session.getAttribute("handleliste") == null)
                session.setAttribute("handleliste", new Handleliste());
            response.sendRedirect("handleliste");
        }
        else {
            showLoginPage(out);
        }
    }

    private void showLoginPage(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Logg inn</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <form action=\"login\">");
        out.println("        <fieldset title=\"Logg inn\">");
        out.println("            <p>Passord: <input type=\"password\" formaction=\"login\" name=\"password\" autofocus></p>");
        out.println("            <p><font color=\"red\">Feil passord</font></p>");
        out.println("            <p><input type=\"submit\" value=\"Logg inn\"></p>");
        out.println("        </fieldset>");
        out.println("    </form>");
        out.println("</body>");
        out.println("</html>");
    }
}
