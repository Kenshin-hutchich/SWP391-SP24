<%-- 
    Document   : login
    Created on : Jan 13, 2024, 12:05:38 AM
    Author     : tudo7
--%>

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
        <link rel="stylesheet" type="text/css" href="assets/css/snackbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/successtoast.css">

    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="failtoast">Fail to reset! check your password</div>
            <div id="sucesstoast">Reset successful! Redirecting...</div>
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(assets/images/background/bg2.jpg);">
                    <a href="index.html"><img src="assets/images/logo-white-2.png" alt=""></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Reset your <span>Account</span></h2>
                            <p>Don't have an account? <a href="register.jsp">Create one here</a></p>
                        </div>	
                        <form id="resetForm" class="contact-bx" action="usercontroller" method="post">
                            <input type="hidden"  name="action" value="changepassword">
                            <div><b>${mess}</b></div>

                            <div class="row placeani">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Password:</label>
                                             <input name="password" type="password" required="" class="form-control" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$">
                                             <div class="invalid-feedback">
                                                Please enter a valid password.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <div class="input-group"> 
                                            <label>Repeat password:</label>
                                            <input  type="password" class="form-control" required="" name="repassword">
                                        </div>
                                    </div>
                                </div>
<!--                                <div class="col-lg-12">
                                    <div class="form-group form-forget">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="custom-control-input" id="customControlAutosizing">
                                            <label class="custom-control-label" for="customControlAutosizing">Remember me</label>
                                        </div>

                                    </div>
                                </div>-->
                                <div class="col-lg-12 m-b30">
                                    <button  type="submit" class="btn button-md">save</button>
                                </div>
<!--                                <div class="col-lg-12">
                                    <h6>Login with Social media</h6>
                                    <div class="d-flex">
                                        <a class="btn flex-fill m-r5 facebook" href="#"><i class="fa fa-facebook"></i>Facebook</a>
                                        <a class="btn flex-fill m-l5 google-plus" href="#"><i class="fa fa-google-plus"></i>Google Plus</a>
                                    </div>
                                </div>-->
                            </div>

                        </form>
                    </div>
                </div>
            </div>
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
        <script>
             $("#resetForm").submit(function (event) {
        event.preventDefault(); // Prevent the default form submission
        var formData = $(this).serialize();
        $.ajax({
            type: "GET",
            url: "usercontroller?action=changepassword", // Use the URL of your AddUserServlet
            data: formData,
            success: function (response) {
                if (response.trim() === 'fail') {
                    // SHOW SUCCESS TOAST BY JS
                     var errorToast = document.getElementById("failtoast");
                    errorToast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                    errorToast.className = "show";

                    // After 3 seconds, remove the show class from DIV
                    setTimeout(function () {
                        errorToast.className = errorToast.className.replace("show", "");
                    }, 1000);
                } else {
                 var successToast = document.getElementById("sucesstoast");
                successToast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                successToast.className = "show";

                // After 3 seconds, remove the show class from DIV and redirect
                setTimeout(function () {
                    successToast.className = successToast.className.replace("show", "");
                    window.location.href = "login.jsp";
                }, 2000);
                   
                }
            }
        });
    });
        </script>
    </body>

</html>

