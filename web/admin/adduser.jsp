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
        
       
	
</head>


<body class="ttr-opened-sidebar ttr-pinned-sidebar">
   
	   <jsp:include page="headeradmin.jsp"></jsp:include>
	 <jsp:include page="leftsidebaradmin.jsp"></jsp:include>  
        
        
        

	<!--Main container start -->
	<main class="ttr-wrapper">
		<div class="container-fluid">
        <div class="row mt-3">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Add User</div>
                        <hr>
                      
                        <form action="${pageContext.request.contextPath}/auseraddcontrol" method="POST">
                            
                                    <%--   ${pageContext.request.contextPath}/admin/product/add --%>


                            
                            <div class="form-group">
                                <label for="input-1">Avatar</label>
                                <input type="text" class="form-control"   placeholder="avatar " id="input-1" name="user-avatar">
                            </div>

                            <div class="form-group">
                               <label for="input-1">Email</label>
                                <input type="text" class="form-control" placeholder="email" id="input-1" name="user-email">
                            </div>
                            <div class="form-group">
                               <label for="input-1">Hash</label>
                                <input type="text" class="form-control" placeholder="hash"  id="input-1" name="user-hash">
                            </div>
                            <div class="form-group">
                               <label for="input-1">Name</label>
                                <input type="text" class="form-control"  placeholder="name"  id="input-1" name="user-name">
                            </div>
                            <div class="form-group">
                               <label for="input-1">Mobile</label>
                                <input type="text" class="form-control"  placeholder="$mobile"  id="input-1" name="user-mobile">
                            </div>
                            <div class="form-group">
                               <label for="input-1">Role</label>
                                <input type="text" class="form-control" placeholder="role" id="input-1" name="user-role">
                            </div>
                  
                            
                           <div class="form-group">
                                <label for="input-2">Gender</label>
                                <div>
                                    <select class="form-control valid" id="input-6" name="user-gender"  aria-invalid="false">
                                        
                                   
                                            <option value="true">Male</option>
                                            <option value="false">Female</option>
                                            
                                        
                                        
                                            
                                    </select>
                                </div>
                            </div>
                          <div class="form-group">
                                <label for="input-2">Status</label>
                                <div>
                                    <select class="form-control valid" id="input-6" name="user-status" aria-invalid="false">
                                        
                                          
                                            <option value="true">active</option>
                                             <option value="false">deactive</option>

                                    </select>
                                </div>
                            </div>

                            
                           
                            <div class="form-footer">
                                
                                
                                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Save</button>
                            </div>
                        </form>
                                    <button class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/ausercontrol">Cancel</a></button>
                                
                                
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
	</main>
	<div class="ttr-overlay"></div>

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
<script src='assets/vendors/scroll/scrollbar.min.js'></script>
<script src="assets/js/functions.js"></script>
<script src="assets/vendors/chart/chart.min.js"></script>
<script src="assets/js/admin.js"></script>
<script src='assets/vendors/calendar/moment.min.js'></script>
<script src='assets/vendors/calendar/fullcalendar.js'></script>
<script src='assets/vendors/switcher/switcher.js'></script>

  
</body>

<!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
