/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admin;

import com.google.gson.Gson;
import dal.AccessLogDAO;
import dal.CourseDAO;
import dal.CourseReviewDAO;
import dal.DimensionDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Course;
import model.Dimension;

/**
 *
 * @author KENSHIN
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/dashboard"})
public class Dashboard extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Dashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Dashboard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        AccessLogDAO accessLogDAO = new AccessLogDAO();
        CourseDAO courseDAO = new CourseDAO();
        CourseReviewDAO courseReviewDAO = new CourseReviewDAO();
        UserDAO userDAO = new UserDAO();
        DimensionDAO dimensionDAO = new DimensionDAO();

        //  ---------------------------------- Traffic Chart -----------------------------------------------
        String timeUnit = request.getParameter("timeUnit");
        String rawFromDate = request.getParameter("fromDate");
        String rawToDate = request.getParameter("toDate");
        LocalDateTime fromDate;
        LocalDateTime toDate;
        if (timeUnit == null || timeUnit.isEmpty()) {
            timeUnit = "day";
        }
        if (rawToDate == null || rawToDate.isEmpty()) {
            // Default toDate is today
            toDate = LocalDateTime.now()
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        } else {
            toDate = LocalDateTime.parse(rawToDate + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        if (rawFromDate == null || rawFromDate.isEmpty()) {
            switch (timeUnit) {
                case "day":
                    fromDate = toDate.minusDays(10);
                    break;
                case "week":
                    fromDate = toDate.minusWeeks(10);
                    break;
                case "month":
                    fromDate = toDate.minusMonths(10);
                    break;
                case "year":
                    fromDate = toDate.minusYears(10);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
            }
        } else {
            fromDate = LocalDateTime.parse(rawFromDate + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        List<Integer> chartData = accessLogDAO.getByTimePeriod(fromDate, toDate, timeUnit);
        List<String> chartLabels = generateLabels(fromDate, toDate, timeUnit);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDateStr = fromDate.format(formatter);
        String toDateStr = toDate.format(formatter);
        //  ---------------------------------Statistical-------------------------------------------
        //  Get student success rate
        int studentFailedNumber = courseDAO.totalnumberOfFailedStudent();
        int studentSuccessNumber = courseDAO.totalnumberOfPassedStudent();
        int total = studentFailedNumber + studentSuccessNumber;
        double studentSuccessRate = (double) studentSuccessNumber / (double) total * 10000;
        studentSuccessRate = (double) Math.round(studentSuccessRate) / 100;
        //  Get course registrations of the current month
        int courseRegistrations = courseDAO.numberOfRegistrationsThisMonth();
        int assessmentMilestone = courseDAO.totalNumberOfParticipants();
        double courseRegisterPercentage = (double) courseRegistrations / (double) assessmentMilestone * 10000;
        courseRegisterPercentage = (double) Math.round(courseRegisterPercentage) / 100;
        //  Get rate of user registration
        int totalUser = userDAO.getTotal();
        int totalDistinctIps = accessLogDAO.countDistinctIPs();
        double userRegisterPercentage = (double) totalUser / (double) totalDistinctIps * 10000;
        userRegisterPercentage = (double) Math.round(userRegisterPercentage) / 100;
        //  Get average review of all course
        double totalCourseRating = courseReviewDAO.calculateAverageRating() / 5.0 * 10000;
        totalCourseRating = (double) Math.round(totalCourseRating) / 100;
        int totalFeedbacks = courseReviewDAO.total();

        //  --------------------------------Notification board -------------------------------------------------------
        //  Get number of pending courses
        int waitingCourseNumber = courseDAO.getWaitingCourseNumber();
        //  Get number of new user today
        int newUserToday = userDAO.getRegisteredUsersCountToday();

        //  --------------------------------Course registration chart------------------------------
        //  Total courses by dimensions.
        List<List<Object>> totalCourseChart = new ArrayList<>();
        //  Total registration by dimensions.
        List<List<Object>> totalRegistrationChart = new ArrayList<>();
        //  Get data
        for (Dimension d : dimensionDAO.getAll()) {
            //  Get raw data
            String dName = d.getName();
            List<Course> courses = courseDAO.getCourseByDimensionId(d.getId());
            double dNumber = (double) courses.size();
            double regisNumber = 0;
            for (Course c : courses) {
                regisNumber += courseDAO.totalNumberOfRegistrationOfCourse(c.getId());
            }
            //  Convert to by percent data
            dNumber = dNumber / (double) courseDAO.getTotal() * 10000;
            regisNumber = regisNumber / (double) courseDAO.totalNumberOfRegistration() * 10000;

            dNumber = (double) Math.round(dNumber) / 100.0;
            regisNumber = (double) Math.round(regisNumber) / 100.0;

            List<Object> tempList1 = new ArrayList<>();
            tempList1.add(dName);
            tempList1.add(dNumber);

            totalCourseChart.add(tempList1);

            List<Object> tempList2 = new ArrayList<>();
            tempList2.add(dName);
            tempList2.add(regisNumber);

            totalRegistrationChart.add(tempList2);
        }
        totalCourseChart.add(0, Arrays.asList("Dimension", "Total Courses"));
        totalRegistrationChart.add(0, Arrays.asList("Dimension", "Total Registrations"));

        //  Close connections
        courseDAO.closeConnection();
        accessLogDAO.closeConnection();
        courseReviewDAO.closeConnection();
        userDAO.closeConnection();
        dimensionDAO.closeConnection();

        request.setAttribute("totalCourseChart", new Gson().toJson(totalCourseChart));
        request.setAttribute("totalRegistrationChart", new Gson().toJson(totalRegistrationChart));

        request.setAttribute("timeUnit", timeUnit);
        request.setAttribute("fromDate", fromDateStr);
        request.setAttribute("toDate", toDateStr);
        request.setAttribute("totalUser", totalUser);

        request.setAttribute("waitingCourseNumber", waitingCourseNumber);
        request.setAttribute("newUserToday", newUserToday);

        request.setAttribute("userRegisterPercentage", userRegisterPercentage);
        request.setAttribute("registerPercentage", courseRegisterPercentage);
        request.setAttribute("courseRegistrations", courseRegistrations);
        request.setAttribute("studentSuccessRate", studentSuccessRate);
        request.setAttribute("studentSuccessNumber", studentSuccessNumber);
        request.setAttribute("totalCourseRating", totalCourseRating);
        request.setAttribute("totalFeedbacks", totalFeedbacks);
        request.setAttribute("chartData", chartData);
        request.setAttribute("chartLabels", chartLabels);

        request.getRequestDispatcher("admin/index.jsp").forward(request, response);
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

    private List<String> generateLabels(LocalDateTime fromDate, LocalDateTime toDate, String timeUnit) {
        List<String> chartLabels = new ArrayList<>();

        switch (timeUnit) {
            case "day":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
                    chartLabels.add(date.format(DateTimeFormatter.ofPattern("dd/MM")));
                }
                break;
            case "week":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusWeeks(1)) {
                    LocalDateTime endOfWeek = date.plusDays(6);
                    chartLabels.add(date.format(DateTimeFormatter.ofPattern("dd/MM")) + " - " + endOfWeek.format(DateTimeFormatter.ofPattern("dd/MM")));
                }
                break;
            case "month":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusMonths(1)) {
                    chartLabels.add(date.getMonth().toString());
                }
                break;
            case "year":
                for (LocalDateTime date = fromDate; !date.isAfter(toDate); date = date.plusYears(1)) {
                    chartLabels.add(String.valueOf(date.getYear()));
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
        }

        return chartLabels;
    }

}
