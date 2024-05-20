<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="java.util.Map" %>
<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:08:15 GMT -->
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
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/admin/assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/vendors/calendar/fullcalendar.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/color/color-1.css">

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
                    <h4>Dashboard</h4>
                    <!-- Card -->
                    <div class="row">
                        <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                            <div class="widget-card widget-bg1">					 
                                <div class="wc-item">
                                    <h4 class="wc-title">
                                        Student Success
                                    </h4>
                                    <span class="wc-des">
                                        Passed student number
                                    </span>
                                    <span class="wc-stats">
                                        <span class="counter">${studentSuccessNumber}</span>
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${studentSuccessRate}%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Based on all registration
                                    </span>
                                    <span class="wc-number ml-auto">
                                        ${studentSuccessRate}%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg2">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Course Feedback
                                </h4>
                                <span class="wc-des">
                                    Student review amount
                                </span>
                                <span class="wc-stats counter">
                                    ${totalFeedbacks}
                                </span>
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${totalCourseRating}%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Satisfaction
                                    </span>
                                    <span class="wc-number ml-auto">
                                        ${totalCourseRating}%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg3">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    New Registration
                                </h4>
                                <span class="wc-des">
                                    Course registration
                                </span>
                                <span class="wc-stats counter">
                                    ${courseRegistrations}
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${registerPercentage}%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Registration rate this month
                                    </span>
                                    <span class="wc-number ml-auto">
                                        ${registerPercentage}%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 col-xl-3 col-sm-6 col-12">
                        <div class="widget-card widget-bg4">					 
                            <div class="wc-item">
                                <h4 class="wc-title">
                                    Total Users 
                                </h4>
                                <span class="wc-des">
                                    All user
                                </span>
                                <span class="wc-stats counter">
                                    ${totalUser}
                                </span>		
                                <div class="progress wc-progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${userRegisterPercentage}%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                                <span class="wc-progress-bx">
                                    <span class="wc-change">
                                        Registration rate on traffic
                                    </span>
                                    <span class="wc-number ml-auto">
                                        ${userRegisterPercentage}%
                                    </span>
                                </span>
                            </div>				      
                        </div>
                    </div>
                </div>
                <!-- Card END -->
                <div class="row">
                    <!-- Website Traffic Chart -->
                    <div class="col-lg-8 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>
                                    <a data-toggle="collapse" href="#trafficCollapse" role="button" aria-expanded="true" aria-controls="trafficCollapse" class="collapsed">
                                        Website Traffic
                                    </a>
                                    <i class="dropdown-toggle"></i>
                                </h4>
                                <div class="collapse show" id="trafficCollapse">
                                    <form action="dashboard" method="get">
                                        <div class="filter-container p-3 rounded border border-dark mt-2">
                                            <div class="select-box">
                                                <label for="timeUnit" class="form-label">Time Unit:</label>
                                                <select name="timeUnit" id="timeUnit" class="form-select">
                                                    <option value="day" ${timeUnit == 'day' ? 'selected' : ''}>Day</option>
                                                    <option value="week" ${timeUnit == 'week' ? 'selected' : ''}>Week</option>
                                                    <option value="month" ${timeUnit == 'month' ? 'selected' : ''}>Month</option>
                                                    <option value="year" ${timeUnit == 'year' ? 'selected' : ''}>Year</option>
                                                </select>
                                            </div>
                                            <div class="row mt-2">
                                                <div class="col">
                                                    <label for="fromDate" class="form-label">From:</label>
                                                    <input type="date" id="fromDate" name="fromDate" class="form-control" value="${fromDate}">
                                                </div>
                                                <div class="col">
                                                    <label for="toDate" class="form-label">To:</label>
                                                    <input type="date" id="toDate" name="toDate" class="form-control" value="${toDate}">
                                                </div>
                                            </div>
                                            <button class="btn mt-2" type="submit">Update</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="widget-inner">
                                <canvas id="chart" width="100" height="45"></canvas>
                            </div>
                        </div>
                    </div>
                    <!-- Website Traffic Chart END-->
                    <div class="col-lg-4 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Notifications</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="noti-box-list">
                                    <ul>
                                        <li>
                                            <span class="notification-icon dashbg-gray">
                                                <i class="fa fa-book"></i>
                                            </span>
                                            <span class="notification-text">
                                                You have <a href="course-manage">${waitingCourseNumber} courses</a> awaiting approval.
                                            </span>
                                        </li>
                                        <li>
                                            <span class="notification-icon dashbg-yellow">
                                                <i class="fa fa-user"></i>
                                            </span>
                                            <span class="notification-text">
                                                <a href="user-manage">${newUserToday} users</a> registered today.
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Course Distribution</h4>
                            </div>
                            <div class="widget-inner">
                                <div id="coursesChart"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Course Enrollment Distribution</h4>
                            </div>
                            <div class="widget-inner">
                                <div id="regisChart"></div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </main>
        <div class="ttr-overlay"></div>

        <!-- External JavaScripts -->
        <script src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/counter/waypoints-min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/counter/counterup.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/masonry/masonry.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/masonry/filter.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src='${pageContext.request.contextPath}/admin/assets/vendors/scroll/scrollbar.min.js'></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/functions.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/vendors/chart/chart.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/assets/js/admin.js"></script>
        <script src='${pageContext.request.contextPath}/admin/assets/vendors/calendar/moment.min.js'></script>
        <script src='${pageContext.request.contextPath}/admin/assets/vendors/calendar/fullcalendar.js'></script>
        <script src='${pageContext.request.contextPath}/admin/assets/vendors/switcher/switcher.js'></script>
        <script>
            google.charts.load('current', {packages:['corechart']});
            google.charts.setOnLoadCallback(drawCourseChart);
            google.charts.setOnLoadCallback(drawRegistrationChart);
            function drawCourseChart() {
            var totalCourseData = ${totalCourseChart};
            var courseData = google.visualization.arrayToDataTable(totalCourseData);
            var courseOptions = {
            width: 480,
                    height: 480,
                    titlePosition: 'none',
                    legend: { position: 'bottom' }
            };
            var courseChart = new google.visualization.PieChart(document.getElementById('coursesChart'));
            courseChart.draw(courseData, courseOptions);
            }

            function drawRegistrationChart() {
            var totalRegistrationData = ${totalRegistrationChart};
            var regisData = google.visualization.arrayToDataTable(totalRegistrationData);
            var regisOptions = {
            width: 480,
                    height: 480,
                    titlePosition: 'none',
                    legend: { position: 'bottom' }
            };
            var regisChart = new google.visualization.PieChart(document.getElementById('regisChart'));
            regisChart.draw(regisData, regisOptions);
            }
        </script>

        <script>
            var checkSelectorExistence = function (selectorName) {
            if (jQuery(selectorName).length > 0) {
            return true;
            } else {
            return false;
            }
            };
        </script>
        <script>
            var displayGraph = function () {
            if (!checkSelectorExistence('#chart')) {
            return;
            }
            Chart.defaults.global.defaultFontFamily = "rubik";
            Chart.defaults.global.defaultFontColor = '#999';
            Chart.defaults.global.defaultFontSize = '12';
            var ctx = document.getElementById('chart').getContext('2d');
            var chart = new Chart(ctx, {
            type: 'line',
                    // The data for our dataset
                    data: {
                    labels: [
            <c:forEach var="label" items="${chartLabels}" varStatus="loop">
                    "${label}"<c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                    ],
                            // Information about the dataset
                            datasets: [{
                            label: "Website traffic: ",
                                    backgroundColor: 'rgba(0,0,0,0.05)',
                                    borderColor: '#4c1864',
                                    borderWidth: "3",
                                    data: ${chartData},
                                    pointRadius: 4,
                                    pointHoverRadius: 4,
                                    pointHitRadius: 10,
                                    pointBackgroundColor: "#fff",
                                    pointHoverBackgroundColor: "#fff",
                                    pointBorderWidth: "3",
                            }]
                    },
                    // Configuration options
                    options: {

                    layout: {
                    padding: 0,
                    },
                            legend: {display: false},
                            title: {display: false},
                            scales: {
                            yAxes: [{
                            scaleLabel: {
                            display: false
                            },
                                    gridLines: {
                                    borderDash: [6, 6],
                                            color: "#ebebeb",
                                            lineWidth: 1,
                                    },
                            }],
                                    xAxes: [{
                                    scaleLabel: {display: false},
                                            gridLines: {display: false},
                                    }],
                            },
                            tooltips: {
                            backgroundColor: '#333',
                                    titleFontSize: 12,
                                    titleFontColor: '#fff',
                                    bodyFontColor: '#fff',
                                    bodyFontSize: 12,
                                    displayColors: false,
                                    xPadding: 10,
                                    yPadding: 10,
                                    intersect: false
                            }
                    },
            });
            }

            $(document).ready(function () {
            displayGraph();
            });
        </script>

    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>