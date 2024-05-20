<%-- 
    Document   : index
    Created on : Jan 14, 2024, 2:06:08 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Arrays,java.util.Date" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:08:15 GMT -->
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
        <style>
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: chocolate;
            }
        </style>
    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <jsp:include page="headeradmin.jsp"></jsp:include>
        <jsp:include page="leftsidebaradmin.jsp"></jsp:include>       
            <!--Main container start -->
            <main class="ttr-wrapper">
                <div class="container-fluid">
                    <form id="filterForm" action="user-manage" method="get">
                        <div class="card">
                            <div class="card-header">
                                <div class="card-body">
                                    <h4>Users Management</h4>

                                    <!-- Functional button -->
                                    <a class="mx-1" href="" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="true" aria-controls="collapseFilter">
                                        <i class="fa fa-filter"></i> Filter
                                    </a>
                                    <a class="mx-1" href="${pageContext.request.contextPath}/add-user">
                                    <i class="fa fa-plus-circle"></i> Add
                                </a>
                                <!--Filter Board-->
                                <div class="collapse" id="collapseFilter">
                                    <div class="container border py-2 my-2 bg-white">
                                        <div class="row">
                                            <div class="form-group col">
                                                <label for="startDate" class='form-label'>Join from: </label>
                                                <input value="${searchStartDate}" type="date" id="startDate" class="form-control mt-2" name="searchStartDate">
                                            </div>
                                            <div class="form-group col">
                                                <label for="endDate" class='form-label'>To: </label>
                                                <input value="${searchEndDate}" type="date" id="endDate" class="form-control mt-2" name="searchEndDate">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col" >
                                                <label class="form-label">Status</label>
                                                <div class="row mx-2">
                                                    <div class="col form-check">
                                                        <input ${searchStatus == 'true' ? 'checked' : ''} class="form-check-input" type="radio" value="true" name="searchStatus" id="activeCheck">
                                                        <label class="form-check-label" for="activeCheck">
                                                            Active
                                                        </label>
                                                    </div>
                                                    <div class="col form-check">
                                                        <input ${searchStatus == 'false' ? 'checked' : ''} class="form-check-input" type="radio" value="false" name="searchStatus" id="inactiveCheck">
                                                        <label class="form-check-label" for="inactiveCheck">
                                                            Inactive
                                                        </label>
                                                    </div>
                                                    <div class="col form-check">
                                                        <input ${searchStatus == null ? 'checked' : ''} class="form-check-input" type="radio" value="" name="searchStatus" id="bothCheck">
                                                        <label class="form-check-label" for="bothCheck">
                                                            Both
                                                        </label>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-group col">
                                                <label for="selectRoleId" class="form-label">Role</label>
                                                <select class="form-select" name="searchRoleId" id="selectRoleId">
                                                    <option value="">All</option>
                                                    <c:forEach var="role" items="${roles}">
                                                        <option ${role.id == searchRoleId ? 'selected' : ''} value="${role.id}">${role.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row px-3 pb-2">
                                            <button type="submit" class="mx-2 btn-outline-primary">Submit</button>
                                            <button type="reset" class="mx-2 btn-outline-secondary">Reset</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Email</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Joined Date</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Role</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <td>${user.email}</td>
                                            <td>${user.name}</td>
                                            <td>
                                                <fmt:formatDate value="${user.createdAt}" pattern="dd/MM/yyyy HH:mm " />
                                            </td>
                                            <c:choose>
                                                <c:when test="${user.status}">
                                                    <td>Active</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>Inactive</td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>${user.roleSetting.value}</td>
                                            <td>
                                                <a href="user-update?id=${user.id}">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- Pagination ==== -->
                            <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                            <div class="pagination-bx rounded-sm gray clearfix">
                                <ul class="pagination">
                                    <c:if test="${currentPage > 1}">
                                        <li class="previous"><a href="#" onclick="choosePage(${currentPage-1})"><i class="ti-arrow-left"></i> Prev</a></li>
                                            <c:if test="${currentPage > 2}">
                                            <li><a href="#" onclick="choosePage(${1})">1</a></li>
                                            <li><a href="#">...</a></li>
                                            </c:if>
                                        <li><a href="#" onclick="choosePage(${currentPage-1})">${currentPage-1}</a></li>
                                        </c:if>
                                    <li class="active"><a href="#" onclick="choosePage(${currentPage})">${currentPage}</a></li>
                                        <c:if test="${currentPage < maxPage}">
                                        <li><a href="#" onclick="choosePage(${currentPage+1})">${currentPage+1}</a></li>
                                            <c:if test="${currentPage < (maxPage - 1)}">
                                            <li><a href="#">...</a></li>
                                            <li><a href="#" onclick="choosePage(${maxPage})">${maxPage}</a></li>
                                            </c:if>
                                        <li class="next"><a href="#" onclick="choosePage(${currentPage+1})">Next <i class="ti-arrow-right"></i></a></li>
                                            </c:if>
                                </ul>
                            </div>
                            <!-- Pagination END ==== -->
                        </div>
                    </div>
                </form>





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
        <script src='admin/assets/vendors/calendar/moment.min.js'></script>
        <script src='admin/assets/vendors/calendar/fullcalendar.js'></script>
        <script src='admin/assets/vendors/switcher/switcher.js'></script>
        <script>
                                            function choosePage(n) {
                                                document.getElementById("currentPage").value = n;
                                                document.getElementById("filterForm").submit();
                                            }
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
