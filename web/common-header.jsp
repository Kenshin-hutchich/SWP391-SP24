<%-- 
    Document   : common-header
    Created on : Feb 29, 2024, 3:46:58 AM
    Author     : KENSHIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header class="header rs-nav">
            <!-- Header Start ==== -->
            <div class="top-bar">
                <div class="container">
                    <div class="row d-flex justify-content-between">
                        <div class="topbar-left">
                            <ul>
                                <li><a href="faq-1"><i class="fa fa-question-circle"></i>Ask a Question</a></li>
                                <li><a href="javascript:;"><i class="fa fa-envelope-o"></i>Support@website.com</a></li>
                            </ul>
                        </div>
                        <div class="topbar-right">
                            <ul>
                                <c:if test="${not empty userSessionDto}">
                                    <li>
                                        <div class="dropdown profile-dropdown">
                                            <div type="button" id="dropdownProfileButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <c:if test="${not empty userSessionDto.avatar}">
                                                    <img id="headerUserAvatar" src="${userSessionDto.avatar}" class="rounded-circle shadow-4"
                                                         style="width: 25px;" alt="AVATAR" />
                                                </c:if>
                                                <c:if test="${empty userSessionDto.avatar}">
                                                    <img id="headerUserAvatar" src="assets/images/profile/default.jpg" class="rounded-circle shadow-4"
                                                         style="width: 25px; 
                                                         border: 1px solid #fff;
                                                         box-shadow:0 0 15px 0 rgba(0,0,0,0.2);" alt="AVATAR" />
                                                </c:if>
                                                <a href="">Hello, ${userSessionDto.name}</a>
                                                <i class="dropdown-toggle"></i>
                                            </div>
                                            <div class="dropdown-menu" aria-labelledby="dropdownProfileButton">
                                                <a style="color: #000" class="dropdown-item" href="profile">Profile</a>
                                                <a style="color: #000" class="dropdown-item" href="home">Menu</a>
                                                <a style="color: #000" class="dropdown-item" href="logout">Logout</a>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>
                                <c:if test="${empty userSessionDto}">
                                    <li>
                                        <a href="login">Login</a>
                                    </li>
                                    <li>
                                        <a href="register">Register</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sticky-header navbar-expand-lg">
                <div class="menu-bar clearfix">
                    <div class="container clearfix">
                        <!-- Header Logo ==== -->
                        <div class="menu-logo">
                            <a href="${pageContext.request.contextPath}"><img src="assets/images/logo.png" alt=""></a>
                        </div>
                        <!-- Mobile Nav Button ==== -->
                        <button class="navbar-toggler collapsed menuicon justify-content-end" type="button" data-toggle="collapse" data-target="#menuDropdown" aria-controls="menuDropdown" aria-expanded="false" aria-label="Toggle navigation">
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>
                        <!-- Navigation Menu ==== -->
                        <div class="menu-links navbar-collapse collapse justify-content-start" id="menuDropdown">
                            <div class="menu-logo">
                                <a href="${pageContext.request.contextPath}"><img src="assets/images/logo.png" alt=""></a>
                            </div>
                            <ul class="nav navbar-nav">	
                                <li><a href="${pageContext.request.contextPath}/courses">Courses</a>
                                <li><a href="${pageContext.request.contextPath}/blogs-list">Blogs</a>
                                </li>
                            </ul>
                            <div class="nav-social-link">
                                <a href="javascript:;"><i class="fa fa-facebook"></i></a>
                                <a href="javascript:;"><i class="fa fa-google-plus"></i></a>
                                <a href="javascript:;"><i class="fa fa-linkedin"></i></a>
                            </div>
                        </div>
                        <!-- Navigation Menu END ==== -->
                    </div>
                </div>
            </div>
            <!-- Header END ==== -->
        </header>
        <script src="assets/js/jquery.min.js"></script>
    </body>
</html>
