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
        <link rel="stylesheet" type="text/css" href="assets/css/snackbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/successtoast.css">
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
                <div class="card">
                    <div class="card-header">
                        <div class="card-body">
                            <h4>User Authorization</h4>
                            <!-- Functional button -->
                            <form id="roleSelectForm" method="get" action="user-authorization">
                                <div class="form-group">
                                    <label for="role">Select a role:</label>
                                    <div class="form-group">
                                        <select name="role" id="role" onchange="updateTable()">
                                            <c:forEach var="role" items="${roles}">
                                                <option <c:if test="${role.id == selectedRole}">selected</c:if> value="${role.id}">${role.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <a class="small">
                                    *Select a role to display its corresponding access permissions.
                                    <br>
                                    *Check/uncheck to promote/revoke access permission to the selected page.
                                </a>
                            </form>
                        </div>
                    </div>
                    <div class="card-body table-responsive">
                        <table class="table table-bordered align-content-center">
                            <thead>
                                <tr>
                                    <c:forEach var="objective" items="${objectives}">
                                        <th>${objective.value}</th> <!-- Objective column headers -->
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="objective" items="${objectives}" varStatus="colStatus">
                                <td>
                                    <c:forEach var="page" items="${pages}">
                                        <c:if test="${page.objectSetting.id == objective.id}">
                                            <c:set var="hasAccess" value="false" />
                                            <c:forEach var="id" items="${permissions}">
                                                <c:if test="${page.id == id}">
                                                    <c:set var="hasAccess" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <form action="user-authorization" method="post" id="pageUpdateForm_${page.id}}">
                                                <input type="hidden" name="pageId" value="${page.id}">
                                                <input type="hidden" name="roleId" value="${selectedRole}">
                                                    <div class="form-group">
                                                        <div class="form-check ml-2">
                                                            <c:choose>
                                                                <c:when test="${hasAccess}">
                                                                    <input class="form-check-input" type="checkbox" name="hasAccess" id="hasAccess_${page.id}" checked>
                                                                    <label class="form-check-label mx-1 text-success" for="hasAccess" id="page_${page.id}">
                                                                        ${page.name}
                                                                    </label>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input class="form-check-input" type="checkbox" name="hasAccess" id="hasAccess_${page.id}">
                                                                    <label class="form-check-label mx-1 text-danger" for="hasAccess" id="page_${page.id}">
                                                                        ${page.name}
                                                                    </label>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                               </form>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <br>
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
            function updateTable() {
                document.getElementById("roleSelectForm").submit();
            }
            $(document).ready(function() {
                $('input[name^="hasAccess"]').change(function() {
                    var form = $(this).closest('form');
                    var formData = form.serialize();
                    var checkbox = $(this);
                    var pageId = checkbox.attr('id').split('_')[1]; 
                    var pageName = $('#page_' + pageId);
                    $.ajax({
                        type: "POST",
                        url: "user-authorization", // Use the URL of your Servlet
                        data: formData,
                        success: function(response) {
                            
                            var successToast = document.getElementById("sucesstoast");
                            var errorToast = document.getElementById("failtoast");
        
                            if (response.trim() === 'success') {
                                // SHOW SUCCESS TOAST BY JS
                                var successToast = document.getElementById("sucesstoast");
                                successToast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                                successToast.className = "show";
                                
                                // After 2 seconds, remove the show class from DIV
                                setTimeout(function() {
                                    successToast.className = successToast.className.replace("show", "");
                                }, 2000);
                                
                                if (checkbox.is(':checked')) {
                                    pageName.removeClass('text-danger').addClass('text-success');
                                } else {
                                    pageName.removeClass('text-success').addClass('text-danger');
                                }
                            } else {
                                // SHOW ERROR TOAST BY JS
                                var errorToast = document.getElementById("failtoast");
                                errorToast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                                errorToast.className = "show";
                                
                                // After 2 seconds, remove the show class from DIV
                                setTimeout(function() {
                                    errorToast.className = errorToast.className.replace("show", "");
                                }, 2000);
                            }
                        }
                    });
                });
            });
        </script>
        <div id="failtoast">Update failed, try again later</div>
        <div id="sucesstoast">Update successful!</div>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
