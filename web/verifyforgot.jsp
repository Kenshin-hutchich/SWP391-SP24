<%-- 
    Document   : login
    Created on : Jan 13, 2024, 12:05:38 AM
    Author     : tudo7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">


    <head>
        <title>Verify Code</title>
    
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
        <link rel="stylesheet" type="text/css" href="assets/css/verifyform.css"/>
        <link rel="stylesheet" type="text/css" href="assets/css/snackbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/successtoast.css">

    </head>
    <body id="bg">
        <div id="failtoast">Code is incorrect or expired please resend</div>
        <div id="sucesstoast">Confirm successful! Redirecting...</div>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(assets/images/background/bg2.jpg);">
                    <a href="index.html"><img src="assets/images/logo-white-2.png" alt=""></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Verifying your <span>account</span></h2>
                            <p>Haved an account? <a href="login.jsp">Login</a></p>
                        </div>	
                        <div class="container">
        <h2 class="head">Enter Verification Code</h2>
        <h4 class="inform">${mess}</h4>
        <form id="verifyFormfg" action="usercontroller" method="post">
            <input type="hidden"  name="action" value="fverify">
            <input type="text" id="code" name="code" placeholder="Enter your 6-digit code sent in email">
            <input type="submit" value="verify">
            
        </form>
        <a href="usercontroller?action=verifyforgot&email=${email}">resend code</a>
    </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- External JavaScripts -->
        
    <script>
        // JavaScript code
        document.getElementById('verifyForm').addEventListener('submit', function (e) {
            const code = document.getElementById('code').value;
            if (code.length !== 6) {
                alert("Please enter a valid 6-digit code.");
                e.preventDefault();
            }
        });
    </script>
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
            $("#verifyFormfg").submit(function (event) {
                event.preventDefault(); // Prevent the default form submission
                var formData = $(this).serialize();
                $.ajax({
                    type: "GET",
                    url: "usercontroller?action=fverify", // Use the URL of your AddUserServlet
                    data: formData,
                    success: function (response) {
                        if (response.trim() === 'fail') {
                            // SHOW SUCCESS TOAST BY JS
                            var failToast = document.getElementById("failtoast");
                            failToast.style.backgroundColor = "red"; // Replace with the desired color code
                            failToast.className = "show";

                            // After 3 seconds, remove the show class from DIV and redirect
                            setTimeout(function () {
                                failToast.className = failToast.className.replace("show", "");
                            }, 2000);



                        } else {
                            var successToast = document.getElementById("sucesstoast");
                            successToast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                            successToast.className = "show";

                            // After 3 seconds, remove the show class from DIV and redirect
                            setTimeout(function () {
                                successToast.className = successToast.className.replace("show", "");
                                window.location.href = "resetpass.jsp";
                            }, 2000);

                        }
                    }
                });
            });
        </script>
    </body>

</html>

