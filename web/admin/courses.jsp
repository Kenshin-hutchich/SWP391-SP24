<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/courses.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:10:19 GMT -->
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
        <link rel="shortcut icon" type="image/x-icon" href="admin/assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/vendors/calendar/fullcalendar.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="admin/assets/css/color/color-1.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">

        <!-- header start -->
        <jsp:include page="headeradmin.jsp"></jsp:include>
            <!-- header end -->
            <!-- Left sidebar menu start -->
        <jsp:include page="leftsidebaradmin.jsp"></jsp:include> 
            <!-- Left sidebar menu end -->

            <!--Main container start -->
            <main class="ttr-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <!-- Your Profile Views Chart -->
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box border">
                                <ul class="nav nav-tabs">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="tab" href="#tab1">
                                            All Courses
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#tab2">
                                            Top 3 Courses
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#tab3">
                                            Pending Courses
                                        </a>
                                    </li>
                                </ul>
                                <div class="widget-inner">
                                    <div class="tab-content">
                                        <div id="tab1" class="container tab-pane active"><br>
                                            <form action="admin-course-manage" method="get" id="filterForm">
                                                <div class="content-block">
                                                    <!-- About Us -->
                                                    <div class="section-area section-sp1" style="padding-top: 0px">
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
                                                                                                    <c:when test="${i <= course.rating || (i - course.rating) <= 0.5}">
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
                                    </div>
                                    <div id="tab2" class="container tab-pane fade"><br>
                                        <c:forEach var="course" items="${topCourses}">
                                            <div class="card-courses-list admin-courses">
                                                <div class="card-courses-media">
                                                    <img src="${course.thumbnail}" alt=""/>
                                                </div>
                                                <div class="card-courses-full-dec">
                                                    <div class="card-courses-title">
                                                        <h4>${course.title}</h4>
                                                    </div>
                                                    <div class="card-courses-list-bx">
                                                        <ul class="card-courses-view">
                                                            <li class="card-courses-user">
                                                                <div class="card-courses-user-pic">
                                                                    <c:choose>
                                                                        <c:when test="${not empty course.owner.avatar}">
                                                                            <img src="${course.owner.avatar}" alt=""/>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <img src="assets/images/profile/default.jpg" alt=""/>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </div>
                                                                <div class="card-courses-user-info">
                                                                    <h5>Teacher</h5>
                                                                    <h4>${course.owner.name}</h4>
                                                                </div>
                                                            </li>
                                                            <li class="card-courses-categories">
                                                                <h5>Dimension</h5>
                                                                <h4>${course.dimension.name}</h4>
                                                            </li>
                                                            <li class="card-courses-review" data-toggle="tooltip" title="${course.rating} Review">
                                                                <h5>${course.numberOfRating} Review</h5>
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
                                                            </li>
                                                            <li class="card-courses-stats">
                                                                <c:choose>
                                                                    <c:when test="${course.status}">
                                                                        <a href="#" class="btn button-sm green radius-xl">Active</a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a href="#" class="btn button-sm red radius-xl">Inactive</a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="row card-courses-dec">
                                                        <div class="col-md-12">
                                                            <h6 class="m-b10">Course Description</h6>
                                                            <p>
                                                                ${course.description}
                                                            </p>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div id="tab3" class="container tab-pane fade"><br>
                                        <c:forEach var="course" items="${pendingCourses}">
                                            <form action="admin-course-manage" id="approveForm_${course.id}" method="post">
                                                <input type="hidden" name="courseId" value="${course.id}">
                                                <input type="hidden" name="isApproved" value="false">
                                                <div class="card-courses-list admin-courses">
                                                    <div class="card-courses-media">
                                                        <img src="${course.thumbnail}" alt=""/>
                                                    </div>
                                                    <div class="card-courses-full-dec">
                                                        <div class="card-courses-title">
                                                            <h4>${course.title}</h4>
                                                        </div>
                                                        <div class="card-courses-list-bx">
                                                            <ul class="card-courses-view">
                                                                <li class="card-courses-user">
                                                                    <div class="card-courses-user-pic">
                                                                        <c:choose>
                                                                            <c:when test="${not empty course.owner.avatar}">
                                                                                <img src="${course.owner.avatar}" alt=""/>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <img src="assets/images/profile/default.jpg" alt=""/>
                                                                            </c:otherwise>
                                                                        </c:choose>

                                                                    </div>
                                                                    <div class="card-courses-user-info">
                                                                        <h5>Teacher</h5>
                                                                        <h4>${course.owner.name}</h4>
                                                                    </div>
                                                                </li>
                                                                <li class="card-courses-categories">
                                                                    <h5>Dimension</h5>
                                                                    <h4>${course.dimension.name}</h4>
                                                                </li>
                                                                <li class="card-courses-stats">
                                                                    <a href="#" class="btn button-sm gray radius-xl">Pending</a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <div class="row card-courses-dec">
                                                            <div class="col-md-12">
                                                                <h6 class="m-b10">Course Description</h6>
                                                                <p>
                                                                    ${course.description}
                                                                </p>	
                                                            </div>
                                                            <div class="col-md-12">
                                                                <button type="button" class="btn green radius-xl outline" onclick="setApproval(true, '${course.id}')">Approve</button>
                                                                <button type="button" class="btn red outline radius-xl" onclick="setApproval(false, '${course.id}')">Deny</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>

        <!-- External JavaScripts -->
        <script src="admin/assets/js/jquery.min.js"></script>
        <script src="admin/assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="admin/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="admin/assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="admin/assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="admin/assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="admin/assets/vendors/counter/waypoints-min.js"></script>
        <script src="admin/assets/vendors/counter/counterup.min.js"></script>
        <script src="admin/assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="admin/assets/vendors/masonry/masonry.js"></script>
        <script src="admin/assets/vendors/masonry/filter.js"></script>
        <script src="admin/assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src='admin/assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="admin/assets/js/functions.js"></script>
        <script src="admin/assets/vendors/chart/chart.min.js"></script>
        <script src="admin/assets/js/admin.js"></script>
        <script src='admin/assets/vendors/switcher/switcher.js'></script>
        <script>

        </script>
        <script>
            function setApproval(approval, courseId) {
                var formId = "approveForm_" + courseId;
                var form = document.getElementById(formId);
                var input = form.querySelector('input[name="isApproved"]');
                input.value = approval.toString(); // Convert boolean to string and set as value
                form.submit(); // Submit the form
            }
        </script>
        <script>
            function updateDimensionId(dimensionId) {
                document.getElementById('dimensionId').value = dimensionId;
                submitForm();
            }
            function paginateCourses(pageNumber) {
                document.getElementById('currentPage').value = pageNumber;
                submitForm();
            }
            function submitForm() {
                document.getElementById("filterForm").submit();
            }
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/courses.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
</html>