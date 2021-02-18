package controler;

import model.Human;
import sevice.HumanSevice;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HumanServlet", urlPatterns = "/human")
public class HumanServlet extends HttpServlet {
    HumanSevice humanSevice = new HumanSevice();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";

        }
        switch (action){
            case "":
                showFindAll(request,response);
                break;
        }
    }

    private void showFindAll(HttpServletRequest request, HttpServletResponse response) {
        List<Human> humanList = humanSevice.findAll();
        request.setAttribute("humanList", humanList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");

        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
