<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" type="text/css" href="assets/css/toast.css">

    </head>
    <body id="bg">
        <!-- Toast -->
        <div id="toast"></div>
        <!-- Toast END -->
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <!-- Header Top ==== -->
            <jsp:include page="common-header.jsp"></jsp:include>
                <!-- header END ==== -->
                <!-- Content -->
                <div class="page-content bg-white">
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
                                <a href="${pageContext.request.contextPath}/lesson?courseId=${course.id}&lessonId=${lesson.id}">${lesson.title}</a>
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
                            <div class="row">
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="embed-responsive embed-responsive-16by9">
                                        <iframe src="${lesson.videoSrc}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="profile-bx">
                                        <div class="overflow-y-auto">
                                            <style>
                                                /* Tùy chỉnh thanh cuộn */
                                                .custom-scrollbar {
                                                    overflow: auto; /* Hiển thị thanh cuộn khi cần thiết */
                                                    scrollbar-width: thin; /* Kích thước thanh cuộn mảnh */
                                                    scrollbar-color: #555555 #dddddd; /* Màu sắc thanh cuộn */
                                                }
                                            </style>
                                            <div class="profile-head">
                                                <h3>Lessons list</h3>
                                            </div>
                                            <div class="custom-scrollbar" style="width: auto; height: 182px;">
                                                <ul class="list-group list-group-flush">
                                                    <c:forEach var="les" items="${lessons}">
                                                        <li class="list-group-item">
                                                            <a href="${pageContext.request.contextPath}/lesson?courseId=${course.id}&lessonId=${les.id}">
                                                                ${les.orderNumber}. ${les.title}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="overflow-y-auto">
                                            <style>
                                                /* Tùy chỉnh thanh cuộn */
                                                .custom-scrollbar {
                                                    overflow: auto; /* Hiển thị thanh cuộn khi cần thiết */
                                                    scrollbar-width: thin; /* Kích thước thanh cuộn mảnh */
                                                    scrollbar-color: #555555 #dddddd; /* Màu sắc thanh cuộn */
                                                }
                                            </style>
                                            <div class="profile-head">
                                                <h3>Quizzes list</h3>
                                            </div>
                                            <div class="custom-scrollbar" style="width: auto; height: 182px;">
                                                <ul class="list-group list-group-flush">
                                                    <c:forEach var="quiz" items="${quizzes}" varStatus="i">
                                                        <li class="list-group-item">
                                                            <a href="${pageContext.request.contextPath}/quiz-handle?quizId=${quiz.id}">
                                                                ${i.index + 1}. ${quiz.title}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div>
                                    ${lesson.content}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="common-footer.jsp"></jsp:include>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
        </div>

        <!-- External JavaScripts -->
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
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src='assets/vendors/switcher/switcher.js'></script>
    </body>
</html>
