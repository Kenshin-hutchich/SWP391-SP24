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
                    <h4 class="breadcrumb-title">UserList</h4>
                    <ul class="db-breadcrumb-list">
                        <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                        <li>UserList</li>
                    </ul>
                    

                </div>	
                
                    <div style="margin-left:5px" class="row mt-3">
                        <form  id="myForm"  action="${pageContext.request.contextPath}/ausersearchcontrol">
            Name: <input style="margin-left:3px" type="text" name="name" value=""/> 
            Email: <input style="margin-left:5px" type="text" name="email" value=""/> 
            Mobile: <input type="text" name="mobile" value=""/> 
            

            <input style="margin-left:20px" type="submit" value="Search"/>
             <br/>
           
            
            
         </form> 
            
            
            
                        
                    </div>
           
            <div style="margin-left:5px" class="row mt-3">
                 <c:set var="ci" value="${cid}"/>
               <c:set var="ci2" value="${cid2}"/>
                        <form  id="myForm"  action="${pageContext.request.contextPath}/checkuserbox">
                            <input type="checkbox" ${ci[0]?"checked":""} name="gender" value="0" onclick="this.form.submit()"/>FeMale
                            
           <input type="checkbox" name="gender" ${ci[1]?"checked":""} value="1" onclick="this.form.submit()"/>Male</br>
           
                   <input type="checkbox" ${ci2[2]?"checked":""} name="status" value="0" onclick="this.form.submit()"/>DeActive
                            
           <input type="checkbox" name="status" ${ci2[3]?"checked":""} value="1" onclick="this.form.submit()"/>active      
         </form>
           
           
   
                    </div>
                <div class="row mt-3">
                    <div class="col-lg-12">
                        <button class="add-catalog"><a href="${pageContext.request.contextPath}/auseraddcontrol">Add User</a></button>
                    </div>
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">User list</h5>
                                <div class="table-responsive">


                                    <table class="table table-striped">
                                        <thead>
                                            
        
   
    
    
  
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">avatar</th>
                                                <th scope="col">email</th>
                                                
                                                <th scope="col">hash</th>
                                                <th scope="col">name</th>
                                                <th scope="col">mobile</th>
                                                <th scope="col">gender</th>
                                                <th scope="col">status</th>
                                                
                                                <th scope="col">role</th>
                                                <th scope="col">Action</th>
                                            </tr>


                                        </thead>


                                        <tbody>





                                            <c:forEach items="${sls}" var="u">
                                                <c:set var="id" value="${u.id}">  </c:set>

                                                    <tr>
                                                        <th scope="row">${u.id}</th>
                                                     <td><img style="    width: 110px;height: 67px; object-fit: cover;border: 1px solid #fff;" src="${pageContext.request.contextPath}/admin/assets/images/testimonials/${u.avatar}" ></td>
                                                    <td>${u.email}</td>
                                                    <td>${u.hash}</td>
                                                    <td>${u.name }</td>
                                                    <td>${u.mobile }</td>
                                                    
                                                    <td>

                                                        <c:choose>
                                                            <c:when test="${u.gender == true}"> 
                                                                <c:out value="Male"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="Female"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>

                                                        <c:choose>
                                                            <c:when test="${u.status == true}"> 
                                                                <c:out value="Active"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="Deactive"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td> ${u.roleSetting.value}  </td>
                                                    
                                                    <td>
                                                        <button onclick="doDelete(${id})" ><a >Delete</a></button>

                                                        <button class="btn btn-success" data-toggle="tooltip" data-placement="top"  ><a href="${pageContext.request.contextPath}/auserupdatecontrol?id=${id}">Edit</a></button>



                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>

                                    </table>
                                    <c:set var="page" value="${requestScope.page}"/>
                                    <div class="pagination">
                                        <c:forEach begin="1" end="${requestScope.num}" var="i">
                                            <a class="${i==page?"active":""}" href="${pageContext.request.contextPath}/postcontrol?page=${i}">${i}</a>


                                        </c:forEach>

                                    </div>




                                </div>
                            </div>
                        </div>
                    </div>
                </div>







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
        <script type="text/javascript">
                                                            function doDelete(id) {
                                                                if (confirm("are you want to delete user id= '" + id + "'?")) {
                                                                    window.location = "${pageContext.request.contextPath}/auserdeletecontrol?id=" + id;
                                                                }
                                                            }


        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mindex.jsp by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:09:05 GMT -->
</html>
