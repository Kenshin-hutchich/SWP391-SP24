<jsp:useBean id="adao" class="dal.AnswerDAO" scope="request"></jsp:useBean>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>EduChamp : Education HTML Template </title>

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
        <script>
            var timer; // variable to store the timer
            var timeRemaining = ${quiz.time};

            function startTimer() {
                timer = setInterval(updateTimer, 1000);
            }

            function updateTimer() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    var minutes = Math.floor(timeRemaining / 60);
                    var seconds = timeRemaining % 60;
                    document.getElementById("timer").innerHTML = minutes + ":" + seconds;
                    document.getElementById("timeSpentInput").value = timeRemaining;
                } else {
                    clearInterval(timer);
                    document.getElementById("timer").innerHTML = "Time's up!";
                    // Optionally, submit the quiz automatically when time is up
                    document.getElementById("quizForm").submit();
                }
            }

            window.onload = startTimer;
        </script>


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
                    <h1 class="text-center">${quiz.title}</h1>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li>
                                <a href="${pageContext.request.contextPath}">Home</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/courses">Courses</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/course-details?id=${course.id}">${course.title}</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/quiz-handle?quizId=${quiz.id}">${quiz.title}</a>
                            </li>
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
                                            <h4 id="timer" class="price"></h4>
                                        </div>
                                        <div class="course-buy-now text-center">
                                            <a onclick="submitQuiz()" class="btn radius-xl text-uppercase">SUBMIT</a>
                                        </div>
                                        <div style="margin-top: 10px" class="scroll-page">
                                            <c:forEach begin="1" end="${ltQuestion.size()}" var="i">
                                                <a href="#question${i}" class="btn outline nav-link" style="margin: 4px; padding: 6px 12px;" id="navBtn${i}">
                                                    ${i}
                                                </a>
                                            </c:forEach>
                                        </div>
                                    </div> 
                                </div>

                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <div class="row">
                                        <form id="quizForm" action="quiz-handle" method="post" class="w-100">
                                            <input name="quizId" type="hidden" value="${quizId}">    
                                            <input type="hidden" id="timeSpentInput" name="time">
                                            <% int i = 1; %>
                                            <c:forEach items="${ltQuestion}" var="q">
                                                <div class="col-lg-12 mb-3" id="question<%= i %>">
                                                    <div class="card">
                                                        <div class="card-header">
                                                            <%= i %>. ${q.content}
                                                        </div>
                                                        <div class="card-body">
                                                            <h6>Answer</h6>
                                                            <c:forEach items="${adao.getAnswerByQuestion(q.id)}" var="a">
                                                                <div class="form-check">
                                                                    <input name="answers" class="form-check-input" type="checkbox" value="${a.id}" id="defaultCheck_${a.id}">
                                                                    <label class="form-check-label" style="font-weight: 400;" for="defaultCheck_${a.id}">
                                                                        ${a.content}
                                                                    </label>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
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

            <!--CONFIRM MODAL-->
            <!-- Modal -->
            <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Are you sure want to submit?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            You have not answered all questions yet.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button onclick="$('#quizForm').submit()" type="button" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- External JavaScripts -->
        <script>
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
        </script>

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