package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TeamDaoImpl;

/**
 * Servlet implementation class AddPlayerForm
 */
@WebServlet("/addPlayerForm")
public class AddPlayerForm extends HttpServlet {
	private TeamDaoImpl teamDaoImpl;

	public void init(ServletConfig config) throws ServletException {

		System.out.println("IN init()" + Thread.currentThread().getName());
		try {
			teamDaoImpl = new TeamDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException("exception in init()", e);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		try {
			teamDaoImpl.cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("hello vaishnav!");
			HttpSession session = request.getSession();
			session.setAttribute("teamDao", teamDaoImpl);
			List<String> teams = teamDaoImpl.getTeamsAbbreviations();
			pw.print("<h2>Add a player</h2>");
			pw.print("<form action='addToTeam' method='post'>");
			pw.print("<h3>Choose Team</h3>");
			pw.print("<select name='teamAbbr'>");
			for (String abb : teams) {
				pw.print("<option value="+abb+">"+abb+"</option>");
			}
			pw.print("</select>");
			pw.print("<br>");
			pw.print("<lable>Enter name: </label>");
			pw.print("<h3><input type='text' name='name'></h3>");
			pw.print("<lable>Enter DOB: </label>");
			pw.print("<h3><input type='date' name='date'></h3>");
			pw.print("<lable>Enter Average: </label>");
			pw.print("<h3><input type='number' name='avg'></h3>");
			pw.print("<lable>Enter wkts taken: </label>");
			pw.print("<h3><input type='number' name='wkts'></h3>");
			pw.print("</form>");
			pw.print("<button type='submit'>Add player</button>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
