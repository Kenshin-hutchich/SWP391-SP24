<%-- 
    Document   : quiz-review
    Created on : Feb 18, 2024, 4:00:27 PM
    Author     : win
--%>

<jsp:useBean id="adao" class="dal.AnswerDAO" scope="request"></jsp:useBean>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ultility.ConvertTime" %>
<!DOCTYPE html>
<html lang="en">

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
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>Quiz Review</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">
    </head>

    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <jsp:include page="common-header.jsp"></jsp:include>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner2.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">${quiz.title}</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="${pageContext.request.contextPath}">Home</a></li>
                            <li><a href="quiz-handle">Quiz Handle</a></li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row d-flex flex-row-reverse">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="course-detail-bx">
                                        <div class="course-price">
                                            <h4 id="timer" class="price">${score}</h4>
                                        </div>
                                        <% int i = 0; %>
                                        <div class="teacher-bx">
                                            <c:forEach items="${ltQuestion}" var="q1">
                                                <c:if test="${adao.isQuestionCorrect(selectedAnswers, q1.id) eq true}">
                                                    <% i++; %>
                                                </c:if>
                                            </c:forEach>
                                            <div>Correct questions: <%= i %>/${requestScope.ltQuestion.size()}</div>
                                            <div>Time: ${ConvertTime.secondsToTime(time)}</div>
                                        </div>
                                        <div class="course-info-list">
                                            <ul class="navbar">
                                                <li>
                                                    <a class="nav-link" href="quiz-history"
                                                       ><i class="ti-zip"></i>History</a
                                                    >
                                                </li>
                                            </ul>
                                        </div>
                                        <div style="margin-top: 10px" class="scroll-page">
                                            <% i = 1; %>
                                            <c:forEach items="${ltQuestion}" var="q">
                                                <a 
                                                    href="#question${q.id}" 
                                                    class="${adao.isQuestionCorrect(selectedAnswers, q.id) eq true ? 'bg-green' : 'bg-red'} nav-link" 
                                                    style="margin: 4px; padding: 6px 12px; width: 35px; height: 35px; display: inline-block" 
                                                    id="navBtn${q.id}">
                                                    <%= i %>
                                                </a>
                                                <% i++; %>
                                            </c:forEach>
                                        </div>
                                    </div> 
                                </div>

                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <div class="row">
                                        <form id="quizForm" action="quiz-handle" method="post" class="w-100">
                                            <input name="quizId" type="hidden" value="${quizId}">
                                            <% i = 1; %>
                                            <c:forEach items="${ltQuestion}" var="q">
                                                <div class="col-lg-12 mb-3" id="question${q.id}">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <%= i %>. ${q.content}
                                                        </div>
                                                        <div class="card-body">
                                                            <h6>Answer</h6>
                                                            <c:forEach items="${adao.getAnswerByQuestion(q.id)}" var="a">
                                                                <div class="form-check">
                                                                    <c:set var="checked" value=""/>
                                                                    <c:forEach items="${selectedAnswers}" var="selectedAnswerId">
                                                                        <c:if test="${selectedAnswerId == a.id}">
                                                                            <c:set var="checked" value="checked"/>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    <input 
                                                                        name="answers" 
                                                                        class="form-check-input" 
                                                                        type="checkbox" 
                                                                        value="${a.id}" 
                                                                        id="defaultCheck${a.id}"
                                                                        disabled
                                                                        ${checked}
                                                                        >
                                                                    <label class="form-check-label" style="font-weight: 400;" for="defaultCheck${a.id}">
                                                                        ${a.content}
                                                                    </label>
                                                                </div>
                                                            </c:forEach>

                                                        </div>
                                                    </div>
                                                    <div class="alert alert-warning mt-2" role="alert">
                                                        <p class="p-0 m-0">
                                                            Correct Answers: 
                                                            <c:forEach items="${adao.getAnswerByQuestion(q.id)}" var="as">
                                                                <c:if test="${as.isCorrect eq 1}">
                                                                    ${as.content} 
                                                                </c:if>
                                                            </c:forEach>
                                                        </p>
                                                        <p class="p-0 m-0">${q.explaination}</p>
                                                    </div>
                                                </div>
                                                <% i++; %>
                                            </c:forEach>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->

            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <footer>
                <div class="footer-top">
                    <div class="pt-exebar">
                        <div class="container">
                            <div class="d-flex align-items-stretch">
                                <div class="pt-logo mr-auto">
                                    <a href="${pageContext.request.contextPath}"><img src="assets/images/logo-white.png" alt="" /></a>
                                </div>
                                <div class="pt-social-link">
                                    <ul class="list-inline m-a0">
                                        <li><a href="#" class="btn-link"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#" class="btn-link"><i class="fa fa-google-plus"></i></a></li>
                                    </ul>
                                </div>
                                <div class="pt-btn-join">
                                    <a href="#" class="btn ">Join Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4 col-md-12 col-sm-12 footer-col-4">
                                <div class="widget">
                                    <h5 class="footer-title">Sign Up For A Newsletter</h5>
                                    <p class="text-capitalize m-b20">Weekly Breaking news analysis and cutting edge advices
                                        on job searching.</p>
                                    <div class="subscribe-form m-b20">
                                        <form class="subscription-form"
                                              action="http://educhamp.themetrades.com/demo/assets/script/mailchamp.php"
                                              method="post">
                                            <div class="ajax-message"></div>
                                            <div class="input-group">
                                                <input name="email" required="required" class="form-control"
                                                       placeholder="Your Email Address" type="email">
                                                <span class="input-group-btn">
                                                    <button name="submit" value="Submit" type="submit" class="btn"><i
                                                            class="fa fa-arrow-right"></i></button>
                                                </span>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-5 col-md-7 col-sm-12">
                                <div class="row">
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Company</h5>
                                            <ul>
                                                <li><a href="${pageContext.request.contextPath}">Home</a></li>
                                                <li><a href="about-1.html">About</a></li>
                                                <li><a href="faq-1.html">FAQs</a></li>
                                                <li><a href="contact-1.html">Contact</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Get In Touch</h5>
                                            <ul>
                                                <li><a href="http://educhamp.themetrades.com/admin/${pageContext.request.contextPath}">Dashboard</a>
                                                </li>
                                                <li><a href="blog-classic-grid.html">Blog</a></li>
                                                <li><a href="portfolio.html">Portfolio</a></li>
                                                <li><a href="event.html">Event</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-4 col-lg-4 col-md-4 col-sm-4">
                                        <div class="widget footer_widget">
                                            <h5 class="footer-title">Courses</h5>
                                            <ul>
                                                <li><a href="courses.html">Courses</a></li>
                                                <li><a href="courses-details.html">Details</a></li>
                                                <li><a href="membership.html">Membership</a></li>
                                                <li><a href="profile.html">Profile</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-3 col-md-5 col-sm-12 footer-col-4">
                                <div class="widget widget_gallery gallery-grid-4">
                                    <h5 class="footer-title">Our Gallery</h5>
                                    <ul class="magnific-image">
                                        <li><a href="assets/images/gallery/pic1.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic1.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic2.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic2.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic3.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic3.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic4.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic4.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic5.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic5.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic6.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic6.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic7.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic7.jpg" alt=""></a></li>
                                        <li><a href="assets/images/gallery/pic8.jpg" class="magnific-anchor"><img
                                                    src="assets/images/gallery/pic8.jpg" alt=""></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 text-center"><a target="_blank"
                                                                                      href="https://www.templateshub.net">Templates Hub</a></div>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up"></button>
        </div>
        <!-- External JavaScripts -->
        <!--        <script>
                    // Function to check if all questions have at least one answer selected
                    function validateAnswers() {
                        var allQuestionsAnswered = true;
                        var questionCount = ${ltQuestion.size()};
        
                        for (var i = 1; i <= questionCount; i++) {
                            var checkboxes = document.querySelectorAll('#question' + i + ' input[type="checkbox"]');
                            var isChecked = Array.prototype.slice.call(checkboxes).some(function (checkbox) {
                                return checkbox.checked;
                            });
        
                            if (!isChecked) {
                                allQuestionsAnswered = false;
                            }
        
                            // Remove outline class from navigation button if any checkbox is checked
                            if (isChecked) {
                                document.getElementById('navBtn' + i).classList.remove('outline');
                            } else {
                                document.getElementById('navBtn' + i).classList.add('outline');
                            }
                        }
        
                        return allQuestionsAnswered;
                    }
        
                    // Function to submit the quiz
                    function submitQuiz() {
                        if (!validateAnswers()) {
                            $('#confirmModal').modal('show');
                            return;
                        } else {
                            $('#quizForm').submit();
                        }
        
                        // Additional submission logic here if needed
                    }
        
                    // Add event listeners to checkboxes to trigger validation function
                    document.addEventListener('DOMContentLoaded', function () {
                        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
                        checkboxes.forEach(function (checkbox) {
                            checkbox.addEventListener('change', function () {
                                validateAnswers();
                            });
                        });
                    });
                </script>-->

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="assets/js/jquery.scroller.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src="assets/vendors/switcher/switcher.js"></script>
    </body>

</html>