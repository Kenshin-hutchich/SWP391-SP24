<%-- 
    Document   : index
    Created on : Jan 14, 2024, 2:06:08 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays,java.util.Date" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/expert/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:08:15 GMT -->
    <head>

        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../error-404.html" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/vendors/calendar/fullcalendar.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/expert/assets/css/color/color-1.css">
    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <jsp:include page="expert-header.jsp"></jsp:include>
        <jsp:include page="expert-sidebar.jsp"></jsp:include>       
            <!--Main container start -->
            <main class="ttr-wrapper">
                <div class="container-fluid">
                    <h4>Course Content: ${course.title} (${course.subjectCode})</h4>
                    <a href="my-courses" class="btn green outline radius-xl">
                        <i class="fa fa-arrow-left"></i>
                        Return
                    </a>
                    <div class="row mt-3">
                        <div class="col-lg-6">
                            <div class="widget-box">
                                <div class="wc-title">
                                    <h4>Course Lesson</h4>
                                </div>
                                <div class="widget-inner pt-2">
                                    <a href="add-lesson" class="py-1">
                                        <i class="fa fa-plus-circle"></i>
                                        Add lesson
                                    </a>
                                    <table class="table" id="lessonTable">
                                        <thead>
                                            <tr>
                                                <th scope="col">Title</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="lesson" items="${lessons}">
                                            <tr>
                                                <td>${lesson.title}</td>
                                                <td class="float-right">
                                                    <a href="lesson-detail?quizId=${quiz.id}">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                    <a href="lesson-remove?quizId=${quiz.id}">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Course Quiz</h4>
                            </div>
                            <div class="widget-inner pt-2">
                                <a href="add-quiz" class="py-1">
                                    <i class="fa fa-plus-circle"></i>
                                    Add quiz
                                </a>
                                <table class="table" id="quizTable">
                                    <thead>
                                        <tr>
                                            <th scope="col">Title</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="quiz" items="${quizzes}">
                                            <tr>
                                                <td>${quiz.title}</td>
                                                <td class="float-right">
                                                    <a href="quiz-detail?quizId=${quiz.id}">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                    <a href="quiz-remove?quizId=${quiz.id}">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>

        <!-- External JavaScripts -->
        <script src="expert/assets/js/jquery.min.js"></script>
        <script src="expert/assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="expert/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="expert/assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="expert/assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="expert/assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="expert/assets/vendors/counter/waypoints-min.js"></script>
        <script src="expert/assets/vendors/counter/counterup.min.js"></script>
        <script src="expert/assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="expert/assets/vendors/masonry/masonry.js"></script>
        <script src="expert/assets/vendors/masonry/filter.js"></script>
        <script src="expert/assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src='expert/assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="expert/assets/js/functions.js"></script>
        <script src="expert/assets/vendors/chart/chart.min.js"></script>
        <script src="expert/assets/js/admin.js"></script>
        <script src='expert/assets/vendors/calendar/moment.min.js'></script>
        <script src='expert/assets/vendors/calendar/fullcalendar.js'></script>
        <script src='expert/assets/vendors/switcher/switcher.js'></script>
        <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#lessonTable').DataTable({
                    "pagingType": "full_numbers",
                    "lengthChange": false,
                    "searching": false,
                    "pageLength": 5
                });
                $('#quizTable').DataTable({
                    "pagingType": "full_numbers",
                    "lengthChange": false,
                    "searching": false,
                    "pageLength": 5
                });
            });
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/expert/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
