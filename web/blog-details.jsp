<%-- 
    Document   : blog-details
    Created on : Jan 16, 2024, 7:36:38 PM
    Author     : KENSHIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
                                <li><a href="${pageContext.request.contextPath}">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/blogs-list">Blogs List</a></li>
                            <li><a href="${pageContext.request.contextPath}/blog-details?id=${blog.id}">${blog.title}</a></li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <div class="content-block">
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <!-- Left part start -->
                                <div class="col-lg-8 col-xl-8">
                                    <!-- blog start -->
                                    <div class="recent-news blog-lg">
                                        <div class="action-box blog-lg">
                                            <h5 class="post-title">${blog.title}</h5>
                                            <ul class="media-post">
                                                <li>
                                                    <a>
                                                        <i class="fa fa-calendar"></i>
                                                        <fmt:formatDate value="${blog.createdAt}" pattern="dd/MM/yyyy" />
                                                    </a>
                                                </li>
                                                <li><a><i class="fa fa-comments-o"></i>${comments.size()} Comment(s)</a></li>
                                            </ul>
                                            <img src="${blog.thumbnail}" alt="">
                                        </div>
                                        <div class="info-bx">
                                            <c:forEach var="line" items="${paragraphs}">
                                                <p>${line}</p>
                                            </c:forEach>
                                            <div class="ttr-divider bg-gray"><i class="icon-dot c-square"></i></div>
                                        </div>
                                    </div>
                                    <div class="clear" id="comment-list">
                                        <div class="comments-area" id="comments">
                                            <h2 class="comments-title">${comments.size()} COMMENT(S)</h2>
                                            <div class="clearfix m-b20">
                                                <!-- comment list END -->
                                                <ol class="comment-list">
                                                    <c:forEach var="comment" items="${comments}" varStatus="count">
                                                        <li class="comment">
                                                            <div class="comment-body">
                                                                <div class="comment-author vcard"> 
                                                                    <c:if test="${not empty comment.author.avatar}">
                                                                        <img  class="avatar photo" src="${comment.author.avatar}" alt=""> 
                                                                    </c:if>
                                                                    <c:if test="${empty comment.author.avatar}">
                                                                        <img  class="avatar photo" src="assets/images/profile/default.jpg" alt=""> 
                                                                    </c:if>
                                                                    <cite class="fn">${comment.author.name}</cite> 
                                                                    <span class="says">says:</span> 
                                                                </div>
                                                                <div class="comment-meta">
                                                                    <a href="#">
                                                                        <fmt:formatDate value="${comment.createdAt}" pattern="dd/MM/yyyy 'at' HH:mm" />
                                                                    </a> 
                                                                </div>
                                                                <p>${comment.content}</p>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </ol>
                                                <!-- comment list END -->
                                                <!-- Form -->
                                                <div class="comment-respond" id="respond">
                                                    <h4 class="comment-reply-title" id="reply-title">Leave a Reply <small> <a style="display:none;" href="#" id="cancel-comment-reply-link" rel="nofollow">Cancel reply</a> </small> </h4>
                                                    <form action="post-comment" class="comment-form" id="commentform" method="post">
                                                        <input type="hidden" name="postId" value="${blog.id}">
                                                        <input type="hidden" name="UID" value="${userSessionDto.id}">
                                                        <p class="comment-form-comment">
                                                            <label for="comment">Comment</label>
                                                            <textarea class="px-3" required rows="8" name="comment" id="comment" 
                                                                      <c:if test="${not empty userSessionDto}">
                                                                          placeholder="Comment"
                                                                      </c:if>
                                                                      <c:if test="${empty userSessionDto}">
                                                                          disabled
                                                                          placeholder="You must log in to comment"
                                                                      </c:if>
                                                                      ></textarea>
                                                        </p>
                                                        <p class="form-submit">
                                                            <input type="submit" value="Submit Comment" class="submit" id="submit" name="submit">
                                                        </p>
                                                    </form>
                                                </div>
                                                <!-- Form -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- blog END -->
                                </div>
                                <!-- Left part END -->
                                <!-- Side bar start -->
                                <div class="col-lg-4 col-xl-4">
                                    <aside  class="side-bar sticky-top">
                                        <div class="widget recent-posts-entry">
                                            <h6 class="widget-title">Recent Posts</h6>
                                            <div class="widget-post-bx">
                                                <c:forEach var="recentPost" items="${recentPosts}" varStatus="count">
                                                    <div class="widget-post clearfix">
                                                        <div class="ttr-post-media"> <img src="${recentPost.thumbnail}" width="200" height="143" alt=""> </div>
                                                        <div class="ttr-post-info">
                                                            <div class="ttr-post-header">
                                                                <h6 class="post-title"><a href="blog-details.html">${recentPost.title}</a></h6>
                                                            </div>
                                                            <ul class="media-post">
                                                                <li>
                                                                    <a href="#"><i class="fa fa-calendar"></i>
                                                                        <fmt:formatDate value="${recentPost.createdAt}" pattern="dd/MM/yyyy" />
                                                                    </a>
                                                                </li>
                                                                <li><a href="#"><i class="fa fa-comments-o"></i>${recentCmts[count.index]} Comment(s)</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </aside>
                                </div>
                                <!-- Side bar END -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="common-footer.jsp"></jsp:include>
            <!-- Footer END ==== -->
            <!-- scroll top button -->
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