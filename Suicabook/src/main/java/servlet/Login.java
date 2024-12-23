package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	// リクエスト処理
	request.setAttribute("message", "");
	// 転送処理
	ServletContext context = getServletContext();
	RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
	rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト処理
		request.setCharacterEncoding("UTF-8");
		String searchBtn = request.getParameter("btn");
		// 押されたボタン情報
		String jsp;
		String id=request.getAttribute("id");
		
	    
		// 転送処理
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
		}
	}

