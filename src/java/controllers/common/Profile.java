/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.common;

import dal.CourseDAO;
import dal.UserDAO;
import dtos.UserProfileDto;
import dtos.UserSessionDto;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.util.List;
import model.Course;
import model.User;
import services.DataService;
import services.IDataService;

/**
 *
 * @author KENSHIN
 */
@MultipartConfig
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

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
        //  Get user information from current session
        UserSessionDto userSessionDto = new UserSessionDto();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        try {
            //  This line will throw NullPointerException if cookies is null
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    String sessionID = cookie.getValue();
                    //  This line will throw NullPointerException if current session is null
                    if (sessionID.equals(session.getId())) {
                        //  Throw a NullPointerException if session doesn't store user information
                        userSessionDto = (UserSessionDto) session.getAttribute("userSessionDto");
                        if (userSessionDto == null) {
                            throw new NullPointerException();
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            //  If any of those cases happen, it means user haven't login yet. Return 403 Forbidden Error
            System.out.println(ex.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        //  At this point, userSessionDto should have legal value
        //  Get user data and transfer to userProfileDto
        UserDAO userDAO = new UserDAO();
        UserProfileDto userProfile = new UserProfileDto(userDAO.getById(userSessionDto.getId()));
        //  userDAO won't be used anymore, close it's connection
        userDAO.closeConnection();
        
        //  Get list of course that user registered
        CourseDAO courseDAO = new CourseDAO();
        List<Course> doneCourses = courseDAO.registeredCourses(userSessionDto.getId(), true);
        List<Course> ongoingCourses = courseDAO.registeredCourses(userSessionDto.getId(), false);
        List<Course> pendingCourses = courseDAO.pendingCourses(userSessionDto.getId());
        //  courseDAO won't be used anymore, close it's connection
        courseDAO.closeConnection();
        //  Set data to the request
        request.setAttribute("user", userProfile);
        request.setAttribute("pendingCourses", pendingCourses);
        request.setAttribute("ongoingCourses", ongoingCourses);
        request.setAttribute("doneCourses", doneCourses);
        
        //  Finish
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        IDataService dataService = new DataService();
        UserDAO userDAO = new UserDAO();
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String mobile = request.getParameter("mobile");
        String imagePath = userDAO.getById(id).getAvatar();
        Part imageFile = request.getPart("avatarInput");
        if (imageFile != null && imageFile.getSize() > 0) {
            String appPath = request.getServletContext().getRealPath("");
            String directoryPath = "assets/images/profile/";
            imagePath = dataService.saveImage(imageFile, appPath, directoryPath);
        }

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setGender(gender);
        user.setMobile(mobile);
        user.setAvatar(imagePath);
        if (userDAO.updateUserProfile(user)) {
            userDAO.closeConnection();
            HttpSession session = request.getSession();
            UserSessionDto userSessionDto = new UserSessionDto(user);
            session.setAttribute("userSessionDto", userSessionDto);
            System.out.println("Success");
            try ( PrintWriter out = response.getWriter()) {
                out.print("success");
            }
        } else {
            userDAO.closeConnection();
            System.out.println("Failed");
            try ( PrintWriter out = response.getWriter()) {
                out.print("failed");
            }
        }
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
