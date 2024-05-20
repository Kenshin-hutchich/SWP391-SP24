<%-- 
    Document   : blogs
    Created on : Jan 14, 2024, 5:42:08 PM
    Author     : KENSHIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
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
        <!-- Header Top ==== -->
        <jsp:include page="common-header.jsp"></jsp:include>
            <!-- header END -->
        </head>
        <body id="bg">
            <div class="page-wraper">
                <div id="loading-icon-bx"></div>
                <!-- Inner Content Box ==== -->
                <div class="page-content bg-white">
                    <!-- Page Heading Box ==== -->
                    <div class="breadcrumb-row">
                        <div class="container">
                            <ul class="list-inline">
                                <li><a href="${pageContext.request.contextPath}">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/blogs-list">Blogs</a></li>
                        </ul>
                    </div>
                </div>
                <!-- Page Heading Box END ==== -->
                <!-- Page Content Box ==== -->
                <div class="content-block">
                    <!-- Blog Grid ==== -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="ttr-blog-grid-3 row" id="masonry">
                                <c:forEach var="post" items="${data}" varStatus="iter">
                                    <div class="post action-card col-lg-4 col-md-6 col-sm-12 col-xs-12 m-b40">
                                        <div class="recent-news">
                                            <div class="action-box">
                                                <a href="${pageContext.request.contextPath}/blog-details?id=${post.getId()}">
                                                    <img src="${post.getThumbnail()}" alt="THUMBNAIL">
                                                </a>
                                            </div>
                                            <div class="info-bx">
                                                <ul class="media-post">
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/blog-details?id=${post.getId()}">
                                                            <i class="fa fa-calendar"></i>
                                                            <fmt:formatDate value="${post.getCreatedAt()}" pattern="dd/MM/yyyy" />
                                                        </a>
                                                    </li>
                                                    <li><a href="#"><i class="fa fa-user"></i>By ${post.getAuthor().getName()}</a></li>
                                                </ul>
                                                <h5 class="post-title"><a href="${pageContext.request.contextPath}/blog-details?id=${post.getId()}">${post.getTitle()}</a></h5>
                                                <p>${post.getBriefInfo()}</p>
                                                <div class="post-extra">
                                                    <a href="${pageContext.request.contextPath}/blog-details?id=${post.getId()}" class="btn-link">READ MORE</a>
                                                    <a class="comments-bx"><i class="fa fa-comments-o"></i>${post.getNumberOfComment()} Comment(s)</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Pagination ==== -->
                            <div class="pagination-bx rounded-sm gray clearfix">
                                <ul class="pagination">
                                    <c:if test="${page > 1}">
                                        <li class="previous"><a href="${pageContext.request.contextPath}/blogs-list?page=${page-1}"><i class="ti-arrow-left"></i> Prev</a></li>
                                        <li><a href="${pageContext.request.contextPath}/blogs-list?page=${page-1}">${page-1}</a></li>
                                        </c:if>
                                    <li class="active"><a href="${pageContext.request.contextPath}/blogs-list?page=${page}">${page}</a></li>
                                        <c:if test="${page < max}">
                                        <li><a href="${pageContext.request.contextPath}/blogs-list?page=${page+1}">${page+1}</a></li>
                                        <li class="next"><a href="${pageContext.request.contextPath}/blogs-list?page=${page+1}">Next <i class="ti-arrow-right"></i></a></li>
                                            </c:if>
                                </ul>
                            </div>
                            <!-- Pagination END ==== -->
                        </div>
                    </div>
                    <!-- Blog Grid END ==== -->
                </div>
                <!-- Page Content Box END ==== -->
            </div>
            <!-- Page Content Box END ==== -->
            <!-- Footer ==== -->
            <jsp:include page="common-footer.jsp"></jsp:include>
            <!-- Footer END ==== -->
            <button class="back-to-top fa fa-chevron-up"></button>
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