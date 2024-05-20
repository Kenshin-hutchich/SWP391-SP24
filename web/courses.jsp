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
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <form action="courses" method="get" id="filterForm">
                    <div class="content-block">
                        <!-- About Us -->
                        <div class="section-area section-sp1">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                        <div class="widget courses-search-bx placeani">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Search Courses</label>
                                                    <input name="courseName" id="courseName" type="text" class="form-control" value="${courseName}">
                                                    <button type="submit" class="btn" style="width: 100%">Search</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="widget widget_archive">
                                            <h5 class="widget-title style-1">All Courses</h5>
                                            <div class="overflow-y-auto">
                                                <style>
                                                    /* Tùy chỉnh thanh cuộn */
                                                    .custom-scrollbar {
                                                        overflow: auto; /* Hiển thị thanh cuộn khi cần thiết */
                                                        scrollbar-width: thin; /* Kích thước thanh cuộn mảnh */
                                                        scrollbar-color: #555555 #dddddd; /* Màu sắc thanh cuộn */
                                                    }
                                                </style>
                                                <input type="hidden" id="dimensionId" name="dimensionId" value="0"> 
                                                <div class="custom-scrollbar" style="width: auto; height: 150px;">
                                                    <ul class="list-group list-group-flush">
                                                        <li><a  href="#" onclick="updateDimensionId('0')">General</a></li>
                                                            <c:forEach var="dimension" items="${dimensions}">
                                                            <li class="list-group-item"><a  href="#" onclick="updateDimensionId('${dimension.id}')">${dimension.name}</a></li>
                                                            </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-lg-9 col-md-8 col-sm-12">
                                        <div class="row">

                                            <c:forEach var="course" items="${courses}">
                                                <div class="col-md-6 col-lg-4 col-sm-6 m-b30">
                                                    <div class="cours-bx">
                                                        <div class="action-box">
                                                            <img src="${course.thumbnail}" alt="">
                                                        </div>
                                                        <div class="info-bx text-center">
                                                            <h5><a href="${pageContext.request.contextPath}/course-details?id=${course.id}">${course.title}</a></h5>
                                                            <span><a href="${pageContext.request.contextPath}/courses?dimensionId=${course.dimension.id}">${course.dimension.name}</a></span>
                                                        </div>
                                                        <div class="cours-more-info">
                                                            <div class="review">
                                                                <span>${course.numberOfRating} Review</span>
                                                                <ul class="cours-star">
                                                                    <c:forEach begin="1" end="5" var="i">
                                                                        <c:choose>
                                                                            <c:when test="${i <= course.rating}">
                                                                                <li class="active"><i class="fa fa-star"></i></li>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                <li><i class="fa fa-star-o"></i></li>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                </ul>
                                                            </div>
                                                            <div class="price">
                                                                <h5>${course.currentParticipants}/${course.numberOfParticipants}</h5>
                                                                <label>Students</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>

                                            <div class="col-lg-12 m-b20">
                                                <!-- Pagination ==== -->
                                                <div class="pagination-bx rounded-sm gray clearfix">
                                                    <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}">
                                                    <ul class="pagination">
                                                        <c:if test="${currentPage > 1}">
                                                            <li class="previous"><a href="#" onclick="paginateCourses(${currentPage-1})"><i class="ti-arrow-left"></i> Prev</a></li>
                                                            <li><a href="#" onclick="paginateCourses(${currentPage-1})">${currentPage-1}</a></li>
                                                            </c:if>
                                                        <li class="active"><a href="#" class="active" onclick="paginateCourses(${currentPage})">${currentPage}</a></li>
                                                            <c:if test="${currentPage < totalPage}">
                                                            <li><a href="#" onclick="paginateCourses(${currentPage+1})">${currentPage+1}</a></li>
                                                            <li class="next"><a href="#" onclick="paginateCourses(${currentPage+1})">Next <i class="ti-arrow-right"></i></a></li>
                                                                </c:if>
                                                    </ul>
                                                </div>
                                                <!-- Pagination END ==== -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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

        <script>

        </script>
        <script>
            function updateDimensionId(dimensionId) {
                document.getElementById('dimensionId').value = dimensionId; 
                submitForm();
            }
            function paginateCourses(pageNumber){
                document.getElementById('currentPage').value = pageNumber; 
                submitForm();
            }
            function submitForm(){
                document.getElementById("filterForm").submit();
            }
        </script>
    </body>

</html>
