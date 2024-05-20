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
                    <div class="db-breadcrumb">
                        <ul class="db-breadcrumb-list">
                            <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/functions-list">Functions List</a></li>   
                    </ul>
                </div>
                <form id="filterForm" action="functions-list" method="get">
                    <div class="card">
                        <div class="card-header">
                            <div class="card-body">
                                <h4>Functions List</h4>

                                <!-- Functional button -->
                                <a class="mx-1" href="" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="true" aria-controls="collapseFilter">
                                    <i class="fa fa-filter"></i> Filter
                                </a>
                                <a class="mx-1" href="${pageContext.request.contextPath}/add-function">
                                    <i class="fa fa-plus-circle"></i> Add
                                </a>
                                <div class="collapse" id="collapseFilter">
                                    <input type="hidden" name="page" id="currentPage" value="${page}">
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="roleSelect">Can be accessed by: </label>
                                                <div id="roleSelect">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="selectAllRoles"
                                                               <c:if test="${selectedRoles == null}">checked</c:if>
                                                                   >
                                                               <label class="form-check-label" for="selectAllRoles">All</label>
                                                        </div>
                                                    <c:forEach var="role" items="${roles}">
                                                        <div class="form-check">
                                                            <c:set var="isChecked"/>
                                                            <c:if test="${selectedRoles != null}">
                                                                <c:forEach var="selectedRole" items="${selectedRoles}">
                                                                    <c:if test="${selectedRole == String.valueOf(role.id)}">
                                                                        <c:set var="isChecked" value="${true}" />
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                            <input type="checkbox" class="form-check-input role-checkbox" id="role_${role.id}" name="selectedRoles" value="${role.id}"
                                                                   <c:if test="${isChecked}">checked</c:if>                                                                  
                                                                       >
                                                                   <label class="form-check-label" for="role_${role.id}">${role.value}</label>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="actionSelect">Action: </label>
                                                <div id="actionSelect">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="selectAllActions"
                                                               <c:if test="${selectedActions == null}">checked</c:if>>
                                                               <label class="form-check-label" for="selectAllActions">All</label>
                                                        </div>
                                                    <c:forEach var="action" items="${actions}">
                                                        <div class="form-check">
                                                            <c:set var="isChecked"/>
                                                            <c:if test="${selectedActions != null}">
                                                                <c:forEach var="selectedAction" items="${selectedActions}">
                                                                    <c:if test="${selectedAction == String.valueOf(action.id)}">
                                                                        <c:set var="isChecked" value="${true}" />
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                            <input type="checkbox" class="form-check-input action-checkbox" id="role_${action.id}" name="selectedActions" value="${action.id}"
                                                                   <c:if test="${isChecked}">checked</c:if>        
                                                                       >
                                                                   <label class="form-check-label" for="role_${action.id}">${action.value}</label>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>     
                                        <div class="col">
                                            <div class="form-group">
                                                <label for="objSelect">Objectives: </label>
                                                <div id="objSelect">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="selectAllObjs"
                                                               <c:if test="${selectedObjs == null}">checked</c:if>
                                                                   >
                                                               <label class="form-check-label" for="selectAllObjs">All</label>
                                                        </div>
                                                    <c:forEach var="obj" items="${objects}">
                                                        <div class="form-check">
                                                            <c:set var="isChecked"/>
                                                            <c:if test="${selectedObjs != null}">
                                                                <c:forEach var="selectedObj" items="${selectedObjs}">
                                                                    <c:if test="${selectedObj == String.valueOf(obj.id)}">
                                                                        <c:set var="isChecked" value="${true}" />
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                            <input type="checkbox" class="form-check-input obj-checkbox" id="obj_${obj.id}" name="selectedObjs" value="${obj.id}"
                                                                   <c:if test="${isChecked}">checked</c:if>
                                                                       >
                                                                   <label class="form-check-label" for="obj_${obj.id}">${obj.value}</label>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>    
                                        <div class="col">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="statusForm">Status: </label>
                                                    <div class="form-group" id="statusForm">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="status" id="statusNull" value="-1"
                                                                   <c:if test="${status == -1}">checked</c:if>
                                                                       >
                                                                   <label class="form-check-label" for="statusNull">
                                                                       All
                                                                   </label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="status" id="statusTrue" value="1"
                                                                <c:if test="${status == 1}">checked</c:if>
                                                                    >
                                                                <label class="form-check-label" for="statusTrue">
                                                                    Active
                                                                </label>
                                                            </div>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="radio" name="status" id="statusFalse" value="0"
                                                                <c:if test="${status == 0}">checked</c:if>
                                                                    >
                                                                <label class="form-check-label" for="statusFalse">
                                                                    Inactive
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label for="numOfRecords">
                                                            Number of records
                                                        </label>
                                                        <input class="form-control" id="numOfRecords" name="numOfRecords" type="number" value="${numOfRecords}">
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn-outline-success">Apply</button>
                                        <button type="reset" class="btn-outline-info">Reset</button>
                                        <button type="button" class="btn-outline-dark" onclick="setDefaultFilters()">Default</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Screen</th>
                                        <th scope="col">Function</th>
                                        <th scope="col">Authorized For</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${data}" varStatus="i">
                                        <tr>
                                            <td>${item.screen}</td>
                                            <td>${item.name}</td>
                                            <td>
                                                <c:forEach var="roleList" items="${authorizedFor[i.index]}">
                                                    ${roles[roleList-1].value}
                                                </c:forEach>
                                            </td>
                                            <c:if test="${item.status}">
                                                <td>Active</td>
                                            </c:if>
                                            <c:if test="!${item.status}">
                                                <td>Inactive</td>
                                            </c:if>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/function-details?id=${item.id}">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <!-- Pagination ==== -->
                            <div class="pagination-bx rounded-sm gray clearfix">
                                <ul class="pagination">
                                    <c:if test="${page > 1}">
                                        <li class="previous"><a onclick="choosePage(${page-1})"><i class="ti-arrow-left"></i> Prev</a></li>
                                            <c:if test="${page > 2}">
                                            <li><a onclick="choosePage(${1})">1</a></li>
                                            <li><a>...</a></li>
                                            </c:if>
                                        <li><a onclick="choosePage(${page-1})">${page-1}</a></li>
                                        </c:if>
                                    <li class="active"><a onclick="choosePage(${page})">${page}</a></li>
                                        <c:if test="${page < max}">
                                        <li><a onclick="choosePage(${page+1})">${page+1}</a></li>
                                            <c:if test="${page < (max - 1)}">
                                            <li><a>...</a></li>
                                            <li><a onclick="choosePage(${max})">${max}</a></li>
                                            </c:if>
                                        <li class="next"><a onclick="choosePage(${page+1})">Next <i class="ti-arrow-right"></i></a></li>
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
                                            document.getElementById("selectAllRoles").addEventListener("change", function () {
                                                var checkboxes = document.querySelectorAll('.role-checkbox');
                                                checkboxes.forEach(function (checkbox) {
                                                    checkbox.checked = document.getElementById("selectAllRoles").unchecked;
                                                });
                                            });
                                            document.getElementById("selectAllActions").addEventListener("change", function () {
                                                var checkboxes = document.querySelectorAll('.action-checkbox');
                                                checkboxes.forEach(function (checkbox) {
                                                    checkbox.checked = document.getElementById("selectAllActions").unchecked;
                                                });
                                            });
                                            document.getElementById("selectAllObjs").addEventListener("change", function () {
                                                var checkboxes = document.querySelectorAll('.obj-checkbox');
                                                checkboxes.forEach(function (checkbox) {
                                                    checkbox.checked = document.getElementById("selectAllObjs").unchecked;
                                                });
                                            });
                                            function choosePage(n) {
                                                document.getElementById("currentPage").value = n;
                                                document.getElementById("filterForm").submit();
                                            }
                                            function handleCheckboxChange(checkboxes, selectAllCheckbox) {
                                                checkboxes.forEach(function (checkbox) {
                                                    checkbox.addEventListener('change', function () {
                                                        // Kiểm tra xem có checkbox nào được chọn không
                                                        const anyChecked = Array.from(checkboxes).some(function (checkbox) {
                                                            return checkbox.checked;
                                                        });

                                                        // Nếu có checkbox được chọn, hủy chọn nút All
                                                        if (anyChecked) {
                                                            selectAllCheckbox.checked = false;
                                                        }
                                                    });
                                                });
                                            }
                                            document.addEventListener("DOMContentLoaded", function () {
                                                handleCheckboxChange(document.querySelectorAll('.role-checkbox'), document.getElementById('selectAllRoles'));
                                                handleCheckboxChange(document.querySelectorAll('.action-checkbox'), document.getElementById('selectAllActions'));
                                                handleCheckboxChange(document.querySelectorAll('.obj-checkbox'), document.getElementById('selectAllObjs'));
                                            });
                                            function setDefaultFilters() {
                                                document.querySelectorAll('input[type="checkbox"]').forEach(function (checkbox) {
                                                    checkbox.checked = false;
                                                });

                                                document.getElementById('selectAllRoles').checked = true;
                                                document.getElementById('selectAllActions').checked = true;
                                                document.getElementById('selectAllObjs').checked = true;

                                                document.getElementById('statusTrue').checked = true;
                                            }

        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
