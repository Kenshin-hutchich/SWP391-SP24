package controllers.expert;

import dal.AnswerDAO;
import dal.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Answer;
import model.Question;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author win
 */
@WebServlet(name = "QuestionImportController", urlPatterns = {"/question-import"})
@MultipartConfig
public class QuestionImportController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        if (request.getSession().getAttribute("userSessionDto") == null) {
            response.sendRedirect("login");
        } else {
            request.setAttribute("ltQuestion", new QuestionDAO().getListQuestion());
            request.getRequestDispatcher("expert/question-list.jsp").forward(request, response);
        }
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
        QuestionDAO qtdao = new QuestionDAO();
        AnswerDAO adao = new AnswerDAO();
        String action = request.getParameter("action");
        switch (action) {
            case "download":
                OutputStream out = response.getOutputStream();
                String my_file = request.getServletContext().getRealPath("resources") + File.separator + "question-import-sample.xlsx";
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition",
                        "attachment; filename=question-import-sample.xlsx");
                FileInputStream in = new FileInputStream(my_file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
//                System.out.println(my_file);
                break;
            case "preview":
                InputStream inputStream = null;
                List<Question> ltQuestion = new ArrayList<>();
                List<Answer> ltAnswer = new ArrayList<>();
                Part filePart = request.getPart("file");
                if (filePart != null) {
                    inputStream = filePart.getInputStream();
                }

                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter dataFormatter = new DataFormatter();
                for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                    org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                    Question q = new Question();
                    q.setId(i);
                    q.setContent(dataFormatter.formatCellValue(row.getCell(0)));
                    ltQuestion.add(q);
//                    int questionId = qtdao.importQuestion(dataFormatter.formatCellValue(row.getCell(0)));
                    String correctAnswers = dataFormatter.formatCellValue(row.getCell(5));
                    String[] correctAnswersArray = correctAnswers.split(",");
                    List<Integer> correctAnswerIndexes = new ArrayList<>();
                    for (String answer : correctAnswersArray) {
                        int index = Integer.parseInt(answer.trim());
                        correctAnswerIndexes.add(index);
                    }
                    for (int j = 1; j < 5; j++) {
                        Answer a = new Answer();
                        a.setContent(dataFormatter.formatCellValue(row.getCell(j)));
                        a.setIsCorrect(correctAnswerIndexes.contains(j) ? 1 : 0);
                        a.setQuestionId(i);
                        ltAnswer.add(a);
                    }

                }
                try ( PrintWriter pw = response.getWriter()) {
                    for (Question question : ltQuestion) {
                        pw.print("<div class=\"card mt-2\"><div class=\"card-body\"><p class=\"m-0\">" + question.getContent() + "</p><div class=\"ml-4\">");
                        for (Answer answer : ltAnswer) {
                            if (answer.getQuestionId() == question.getId()) {
                                pw.print("<div class=\"form-check\"><input name=\"answers\" class=\"form-check-input\" type=\"checkbox\" disabled " + (answer.getIsCorrect() == 1 ? "checked" : "") + "><label class=\"form-check-label\" style=\"font-weight: 400;\">" + answer.getContent() + "</label></div>");
                            }
                        }
                        pw.print("</div></div></div>");
                    }
                }
                break;
            case "import": {
                try {
                    inputStream = null;
                    filePart = request.getPart("file");
                    if (filePart != null) {
                        inputStream = filePart.getInputStream();
                    }
                    workbook = new XSSFWorkbook(inputStream);
                    sheet = workbook.getSheetAt(0);
                    dataFormatter = new DataFormatter();
                    for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                        org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                        int questionId = qtdao.importQuestion(dataFormatter.formatCellValue(row.getCell(0)));
                        String correctAnswers = dataFormatter.formatCellValue(row.getCell(5));
                        String[] correctAnswersArray = correctAnswers.split(",");
                        List<Integer> correctAnswerIndexes = new ArrayList<>();
                        for (String answer : correctAnswersArray) {
                            int index = Integer.parseInt(answer.trim());
                            correctAnswerIndexes.add(index);
                        }
                        for (int j = 1; j < 5; j++) {
                            int answerId = adao.importAnswer(questionId, dataFormatter.formatCellValue(row.getCell(j)), (correctAnswerIndexes.contains(j) ? 1 : 0));
                        }
                    }
                    request.getSession().setAttribute("isImportSuccess", true);
                    request.getRequestDispatcher("question-list.jsp").forward(request, response);
                } catch (Exception e) {
                    response.sendRedirect("error-404.html");
                }
                break;
            }
            default:
                throw new AssertionError();
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
