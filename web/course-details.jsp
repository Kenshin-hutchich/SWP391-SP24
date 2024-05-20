<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row d-flex flex-row-reverse">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="course-detail-bx">
                                        <div class="course-buy-now text-center">
                                            <c:choose>
                                                <c:when test="${not empty userSessionDto}">
                                                    <c:choose>
                                                        <c:when test="${studentNumber < course.numberOfParticipants || hasAccess}">
                                                            <a href="${pageContext.request.contextPath}/lesson?courseId=${course.id}" class="btn radius-xl text-uppercase">LEARN</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a onclick="registerCourse('${course.id}')" class="btn radius-xl text-uppercase">Pre-register</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <strong>You must login to attempt the course</strong>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                        <div class="teacher-bx">
                                            <div class="teacher-info">
                                                <div class="teacher-thumb">
                                                    <c:if test="${not empty course.owner.avatar}">
                                                        <img src="${course.owner.avatar}" alt=""/>
                                                    </c:if>
                                                    <c:if test="${empty course.owner.avatar}">
                                                        <img src="assets/images/profile/default.jpg" alt=""/>
                                                    </c:if>
                                                </div>
                                                <div class="teacher-name">
                                                    <h5>${course.owner.name}</h5>
                                                    <span>Professor</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cours-more-info">
                                            <div class="review">
                                                <span>${totalReview} Review</span>
                                                <ul class="cours-star">
                                                    <c:forEach begin="1" end="5" var="i">
                                                        <c:choose>
                                                            <c:when test="${i <= averageRating}">
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <li><i class="fa fa-star-o"></i></li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="price categories">
                                                <span>Dimension</span>
                                                <h5 class="text-primary">
                                                    <a href="${pageContext.request.contextPath}/courses?dimensionId=${course.dimension.id}">${course.dimension.name}</a>
                                                </h5>
                                            </div>
                                        </div>
                                        <div class="course-info-list scroll-page">
                                            <ul class="navbar">
                                                <li><a class="nav-link" href="#overview"><i class="ti-zip"></i>Overview</a></li>
                                                <li><a class="nav-link" href="#curriculum"><i class="ti-bookmark-alt"></i>Curriculum</a></li>
                                                <li><a class="nav-link" href="#reviews"><i class="ti-comments"></i>Reviews</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-9 col-md-8 col-sm-12">
                                    <div class="courses-post">
                                        <div class="ttr-post-media media-effect">
                                            <a href="#"><img src="${course.thumbnail}" alt=""></a>
                                        </div>
                                        <div class="ttr-post-info">
                                            <div class="ttr-post-title ">
                                                <h2 class="post-title">${course.title}</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="courese-overview" id="overview">
                                        <h4>Overview</h4>
                                        <div class="row">
                                            <div class="col-md-12 col-lg-4">
                                                <ul class="course-features">
                                                    <li><i class="ti-book"></i> <span class="label">Lectures</span> <span class="value">${lessons.size()}</span></li>
                                                    <li><i class="ti-help-alt"></i> <span class="label">Quizzes</span> <span class="value">${quizzes.size()}</span></li>
                                                    <li><i class="ti-user"></i> <span class="label">Students</span> <span class="value">${studentNumber}</span></li>
                                                </ul>
                                            </div>
                                            <div class="col-md-12 col-lg-8">
                                                <h5 class="m-b5">Course Description</h5>
                                                <p>${course.description}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-b30" id="curriculum">
                                        <h4>Curriculum</h4>
                                        <ul class="curriculum-list">
                                            <li>
                                                <h5>Lessons: </h5>
                                                <ul>
                                                    <c:forEach var="lesson" items="${lessons}">
                                                        <li>
                                                            <div>
                                                                <span>${lesson.orderNumber}. </span> ${lesson.title}
                                                            </div>
                                                            <span></span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                            <li>
                                                <h5>Quizzes: </h5>
                                                <ul>
                                                    <c:forEach var="quiz" items="${quizzes}" varStatus="i">
                                                        <li>
                                                            <div>
                                                                <span>${i.index+1}. </span> ${quiz.title}
                                                            </div>
                                                            <span></span>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="" id="reviews">
                                        <h4>Reviews</h4>

                                        <div class="review-bx">
                                            <div class="all-review">
                                                <h2 class="rating-type">${averageRating}</h2>
                                                <ul class="cours-star">
                                                    <c:forEach begin="1" end="5" var="i">
                                                        <c:choose>
                                                            <c:when test="${i <= roundedRating}">
                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <li><i class="fa fa-star-o"></i></li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                </ul>
                                                <span>${totalReview} Rating</span>
                                            </div>
                                            <div class="review-bar">
                                                <c:forEach var="item" items="${numberOfReviews}" varStatus="status">
                                                    <div class="bar-bx">
                                                        <div class="side">
                                                            <div>${5 - status.index} star</div>
                                                        </div>
                                                        <div class="middle">
                                                            <div class="bar-container">
                                                                <div class="bar-5" style="width:${item/totalReview*100}%;"></div>
                                                            </div>
                                                        </div>
                                                        <div class="side right">
                                                            <div>${item}</div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
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
            <jsp:include page="common-footer.jsp"></jsp:include>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up" ></button>
            <div id="toast"></div>
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
        <script src="assets/js/jquery.scroller.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src="assets/vendors/switcher/switcher.js"></script>
        <script>

        </script>
        <script>
            function registerCourse(courseId) {
                var data = {courseId: courseId};
                $.ajax({
                    type: "POST",
                    url: "register-course",
                    data: data,
                    success: function (response) {
                        if (response.trim() === 'success') {
                            // SHOW SUCCESS TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                            toast.innerHTML = "Pre-registered successfully!";
                            toast.className = "show";

                            // After 3 seconds, remove the show class from DIV and reload
                            setTimeout(function () {
                                toast.className = toast.className.replace("show", "");
                            }, 3000);
                        } else {
                            // SHOW ERROR TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                            toast.innerHTML = "Failed to Pre-registere course. Please try again later.";
                            toast.className = "show";

                            // After 3 seconds, remove the show class from DIV
                            setTimeout(function () {
                                toast.className = toast.className.replace("show", "");
                            }, 3000);
                        }
                    },
                });
            }
        </script>
    </body>

</html>
