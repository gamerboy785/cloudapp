package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.DBHandler;
import models.Room;

import java.util.*;
/**
 * Servlet implementation class EditRoomServlet
 */
@WebServlet("/EditRoomServlet")
public class EditRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomID = Integer.parseInt(request.getParameter("roomID"));
		String roomImage = request.getParameter("roomImage");
		String roomType = request.getParameter("roomType");
		double roomPrice = Double.parseDouble(request.getParameter("roomPrice"));
		String balcony = request.getParameter("balcony");
		String coolingSystem = request.getParameter("coolingSystem");
		String setBox = request.getParameter("setBox");
		//boolean update check
		Room room = new Room(roomID,roomImage,roomType,roomPrice,balcony,setBox,coolingSystem);
		boolean isEdited = DBHandler.shared.updateRoom(room);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isEdited", isEdited);
		toJSON(response,map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void toJSON(HttpServletResponse response,Map<String,Object> map)
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(new Gson().toJson(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
