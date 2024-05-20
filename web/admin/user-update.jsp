<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
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
        <link rel="stylesheet" type="text/css" href="admin/assets/css/toast.css">

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
                            <div class="widget-box">
                                <div class="wc-title">
                                    <h4>Update User</h4>
                                </div>
                                <div class="widget-inner">
                                    <form class="edit-profile m-b30" id="updateUserForm">
                                        <input type="hidden" name="id" value="${user.id}">
                                    <div class="">
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Full Name</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" name="name" type="text" value="${user.name}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Phone No.</label>
                                            <div class="col-sm-7">
                                                <input class="form-control" type="text" name="mobile" value="${user.mobile}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Role</label>
                                            <div class="col-sm-7">
                                                <select class="form-select" name="roleId" id="roleId">
                                                    <c:forEach var="role" items="${roles}">
                                                        <option ${role.id == user.roleSetting.id ? 'selected' : ''} value="${role.id}">${role.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Status</label>
                                            <div class="col-sm-7">
                                                <div class="row ml-4">
                                                    <div class="col form-check">
                                                        <input ${user.status == 'true' ? 'checked' : ''} class="form-check-input" type="radio" value="true" name="status" id="activeCheck">
                                                        <label class="form-check-label" for="activeCheck">
                                                            Active
                                                        </label>
                                                    </div>
                                                    <div class="col form-check">
                                                        <input ${user.status == 'false' ? 'checked' : ''} class="form-check-input" type="radio" value="false" name="status" id="inactiveCheck">
                                                        <label class="form-check-label" for="inactiveCheck">
                                                            Inactive
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">Gender</label>
                                            <div class="col-sm-7">
                                                <div class="row ml-4">
                                                    <div class="col form-check">
                                                        <input ${user.gender == 'true' ? 'checked' : ''} class="form-check-input" type="radio" value="true" name="gender" id="activeCheck">
                                                        <label class="form-check-label" for="activeCheck">
                                                            Male
                                                        </label>
                                                    </div>
                                                    <div class="col form-check">
                                                        <input ${user.gender == 'false' ? 'checked' : ''} class="form-check-input" type="radio" value="false" name="gender" id="inactiveCheck">
                                                        <label class="form-check-label" for="inactiveCheck">
                                                            Female
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                            </div>
                                            <div class="col-sm-7">
                                                <button type="submit" class="btn">Save changes</button>
                                                <a href="user-manage" class="btn-secondry">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
            </div>
            <div id="toast"></div>
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
            $("#updateUserForm").submit(function (event) {
                event.preventDefault(); // Prevent the default form submission
                var formData = $(this).serialize();
                $.ajax({
                    type: "POST",
                    url: "user-update", // Use the URL of your AddUserServlet
                    data: formData,
                    success: function (response) {
                        if (response.trim() === 'success') {
                            // SHOW SUCCESS TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                            toast.innerHTML = "User updated successfully!";
                            toast.className = "show";

                            // After 3 seconds, remove the show class from DIV and reload
                            setTimeout(function () {
                                toast.className = toast.className.replace("show", "");
                            }, 3000);
                        } else {
                            // SHOW ERROR TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                            toast.innerHTML = "Failed to update user. Please try again later.";
                            toast.className = "show";

                            // After 3 seconds, remove the show class from DIV
                            setTimeout(function () {
                                errorToast.className = toast.className.replace("show", "");
                            }, 3000);
                        }
                    }
                });
            });
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/user-profile.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
</html>