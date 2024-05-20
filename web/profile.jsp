<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" type="text/css" href="assets/css/toast.css">

    </head>
    <body id="bg">
        <!-- Toast -->
        <div id="toast"></div>
        <!-- Toast END -->
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
                            <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->
                <!-- inner page banner END -->
                <div class="content-block">
                    <!-- About Us -->
                    <div class="section-area section-sp1">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-3 col-md-4 col-sm-12 m-b30">
                                    <div class="profile-bx text-center">
                                        <div class="user-profile-thumb">
                                            <c:if test="${not empty user.avatar}">
                                                <img id="userAvatar" src="${user.avatar}" alt=""/>
                                            </c:if>
                                            <c:if test="${empty user.avatar}">
                                                <img id="userAvatar" src="assets/images/profile/default.jpg" alt=""/>
                                            </c:if>
                                        </div>
                                        <div class="profile-info">
                                            <h4>${user.name}</h4>
                                            <span>${user.email}</span>
                                        </div>
                                        <div class="profile-tabnav">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#courses"><i class="ti-book"></i>My Courses</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#edit-profile"><i class="ti-pencil-alt"></i>Edit Profile</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="${pageContext.request.contextPath}/quiz-history"><i class="ti-timer"></i>Quiz History</a>
                                                </li>

                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#change-password"><i class="ti-lock"></i>Change Password</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-md-8 col-sm-12 m-b30">
                                    <div class="profile-content-bx">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="courses">
                                                <div class="profile-head">
                                                    <h3>My Courses</h3>
                                                    <div class="feature-filters style1 ml-auto">
                                                        <ul class="filters" data-toggle="buttons">
                                                            <li data-filter="" class="btn active">
                                                                <input type="radio">
                                                                <a href="#"><span>All</span></a> 
                                                            </li>
                                                            <li data-filter="ongoing" class="btn">
                                                                <input type="radio">
                                                                <a href="#"><span>Ongoing</span></a> 
                                                            </li>
                                                            <li data-filter="done" class="btn">
                                                                <input type="radio">
                                                                <a href="#"><span>Done</span></a> 
                                                            </li>
                                                            <li data-filter="pending" class="btn">
                                                                <input type="radio">
                                                                <a href="#"><span>Pending</span></a> 
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="courses-filter">
                                                    <div class="clearfix">
                                                        <ul id="masonry" class="ttr-gallery-listing magnific-image row">
                                                            <c:forEach var="course" items="${ongoingCourses}">
                                                                <li class="action-card col-xl-4 col-lg-6 col-md-12 col-sm-6 ongoing">
                                                                    <div class="cours-bx">
                                                                        <div class="action-box">
                                                                            <img src="${course.thumbnail}" alt="">
                                                                            <a href="${pageContext.request.contextPath}/course-details?id=${course.id}" class="btn">Learn</a>
                                                                        </div>
                                                                        <div class="info-bx text-center">
                                                                            <h5><a href="${pageContext.request.contextPath}/course-details?id=${course.id}">${course.title}</a></h5>
                                                                            <span>${course.dimension.name}</span>
                                                                        </div>
                                                                        <div class="cours-more-info">
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </c:forEach>

                                                            <c:forEach var="course" items="${doneCourses}">
                                                                <li class="action-card col-xl-4 col-lg-6 col-md-12 col-sm-6 done">
                                                                    <div class="cours-bx">
                                                                        <div class="action-box">
                                                                            <img src="${course.thumbnail}" alt="">
                                                                            <a href="${pageContext.request.contextPath}/course-details?id=${course.id}" class="btn">Learn</a>
                                                                        </div>
                                                                        <div class="info-bx text-center">
                                                                            <h5><a href="${pageContext.request.contextPath}/course-details?id=${course.id}">${course.title}</a></h5>
                                                                            <span>${course.dimension.name}</span>
                                                                        </div>
                                                                        <div class="cours-more-info">
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </c:forEach>

                                                            <c:forEach var="course" items="${pendingCourses}">
                                                                <li class="action-card col-xl-4 col-lg-6 col-md-12 col-sm-6 pending">
                                                                    <div class="cours-bx">
                                                                        <div class="action-box">
                                                                            <img src="${course.thumbnail}" alt="">
                                                                            <a href="${pageContext.request.contextPath}/course-details?id=${course.id}" class="btn">Learn</a>
                                                                        </div>
                                                                        <div class="info-bx text-center">
                                                                            <h5><a href="${pageContext.request.contextPath}/course-details?id=${course.id}">${course.title}</a></h5>
                                                                            <span>${course.dimension.name}</span>
                                                                        </div>
                                                                        <div class="cours-more-info">
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="edit-profile">
                                                <div class="profile-head">
                                                    <h3>Edit Profile</h3>
                                                </div>
                                                <form id="edit-profile-form" class="edit-profile" enctype="multipart/form-data">
                                                    <input type="hidden" name="id" value="${user.id}">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <h3>Personal Details</h3>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Full Name</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input class="form-control" name="name" type="text" value="${user.name}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Gender</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7 mt-2">
                                                                <div class="form-check-inline">
                                                                    <input class="form-check-input" type="radio" name="gender" id="genderMale" value="${true}" <c:if test="${user.gender == true}">checked</c:if>>
                                                                        <label class="form-check-label small" for="genderMale">
                                                                            Male
                                                                        </label>
                                                                    </div>
                                                                    <div class="form-check-inline">
                                                                        <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="${false}" <c:if test="${user.gender == false}">checked</c:if>>
                                                                        <label class="form-check-label small" for="genderFemale">
                                                                            Female
                                                                        </label>
                                                                    </div>
                                                                    <div class="form-check-inline">
                                                                        <input class="form-check-input" type="radio" name="gender" id="genderOther" value="${null}" <c:if test="${user.gender == null}">checked</c:if>>
                                                                        <label class="form-check-label small" for="genderOther">
                                                                            Other
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Phone No.</label>
                                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                    <input class="form-control" name="mobile" type="text" value="${user.mobile}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-3 col-md-3 col-lg-2 col-form-label">Avatar</label>
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                <input type="file" name="avatarInput" class="form-control-file" id="avatarInput" accept="image/*">
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-9 col-md-9 col-lg-10 ml-auto">
                                                                <img id="avatarPreview" src="#" alt="Preview" style="display: none; max-width: 60%;">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="">
                                                        <div class="">
                                                            <div class="row">
                                                                <div class="col-12 col-sm-3 col-md-3 col-lg-2">
                                                                </div>
                                                                <div class="col-12 col-sm-9 col-md-9 col-lg-7">
                                                                    <button type="submit" class="btn">Save changes</button>
                                                                    <button type="reset" id="resetButton" class="btn-secondry">Cancel</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="tab-pane" id="change-password">
                                                <div class="profile-head">
                                                    <h3>Change Password</h3>
                                                </div>
                                                <form id="change-password-form" class="edit-profile">
                                                    <input type="hidden" name="id" value="${user.id}">
                                                    <div class="">
                                                        <div class="form-group row">
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-9 ml-auto">
                                                                <h3>Password</h3>
                                                                <span class="help" style="color: red">
                                                                    *Password must be at least 8 characters long, including 
                                                                    <br>
                                                                    lowercase, uppercase, numbers, and special characters
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Current Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input name="old-password" id="current-password" class="form-control" type="password" value="">
                                                                <label class="help error-message text-red d-none" id="current-password-error">
                                                                    *Current password cannot be empty.
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input name="password" id="new-password" class="form-control" type="password" value="">
                                                                <label class="help error-message text-red d-none" id="new-password-error">
                                                                    *New password must meet the requirements.
                                                                </label>
                                                                <label class="help error-message text-red d-none" id="duplicated-password-error">
                                                                    *New password must be different from current password.
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label class="col-12 col-sm-4 col-md-4 col-lg-3 col-form-label">Re Type New Password</label>
                                                            <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                                <input id="retype-new-password" class="form-control" type="password" value="">
                                                                <label class="help error-message text-red d-none" id="retype-new-password-error">
                                                                    *Retype password must match new password.
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-4 col-md-4 col-lg-3">
                                                        </div>
                                                        <div class="col-12 col-sm-8 col-md-8 col-lg-7">
                                                            <button type="submit" class="btn">Save changes</button>
                                                            <button type="reset" class="btn-secondry">Cancel</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- contact area END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="common-footer.jsp"></jsp:include>
                <!-- Footer END ==== -->
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
            <script>

            </script>
            <script>
                // Chọn input file và khởi tạo sự kiện change
                const avatarInput = document.getElementById('avatarInput');
                avatarInput.addEventListener('change', function (event) {
                    const file = event.target.files[0];

                    //  Validate if the uploaded file is a picture
                    if (!file.type.startsWith('image/')) {
                        // SHOW NOTIFICATION TOAST BY JS
                        var toast = document.getElementById("toast");
                        toast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                        toast.innerHTML = "Please use an image file.";
                        toast.className = "show";

                        // After 3 seconds, remove the show class from DIV
                        setTimeout(function () {
                            toast.className = toast.className.replace("show", "");
                        }, 3000);
                        avatarInput.value = '';
                        return;
                    }

                    //  Display uploaded picture on user profile picture
                    const reader = new FileReader();
                    reader.onload = function (event) {
                        const imgPreview = document.getElementById('userAvatar');
                        imgPreview.src = event.target.result;
                    };
                    reader.readAsDataURL(file);
                });

                //  If user click reset button, display user's original profile picture
                const resetButton = document.getElementById('resetButton');
                resetButton.addEventListener('click', function () {
                    const userAvatar = document.getElementById('userAvatar');
                    //  Check if user has an avatar to display, otherwise use the default profile picture
                    if ("${not empty user.avatar}") {
                        userAvatar.src = "${user.avatar}";
                    } else {
                        userAvatar.src = "assets/images/profile/default.jpg";
                    }
                });
        </script>
        <script>
            $("#edit-profile-form").submit(function (event) {
                event.preventDefault(); // Prevent the default form submission
                var formData = new FormData(this);
                $.ajax({
                    type: "POST",
                    url: "profile", // Use the URL of your AddUserServlet
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (response.trim() === 'success') {
                            //  Set the uploaded profile picture to the header avatar
                            const file = document.getElementById('avatarInput').files[0];
                            if (file) {
                                const reader = new FileReader();
                                reader.onload = function (event) {
                                    const imgPreview = document.getElementById('headerUserAvatar');
                                    imgPreview.src = event.target.result;
                                };
                                reader.readAsDataURL(file);
                            }
                            // SHOW SUCCESS TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                            toast.innerHTML = "Profile updated successfully!";
                            toast.className = "show";

                            // After 3 seconds, remove the show class from DIV and reload
                            setTimeout(function () {
                                toast.className = toast.className.replace("show", "");
                            }, 3000);
                        } else {
                            // SHOW ERROR TOAST BY JS
                            var toast = document.getElementById("toast");
                            toast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                            toast.innerHTML = "Failed to update profile. Please try again later.";
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
        <script>
            $("#change-password-form").submit(function (event) {
                event.preventDefault(); // Prevent the default form submission
                var formData = $(this).serialize();
                if (validatePasswordForm()) {
                    $.ajax({
                        type: "POST",
                        url: "change-password", // Use the URL of your AddUserServlet
                        data: formData,
                        success: function (response) {
                            //  Clear password input sections
                            document.getElementById("current-password").value = '';
                            document.getElementById("new-password").value = '';
                            document.getElementById("retype-new-password").value = '';
                            if (response.trim() === 'success') {
                                // SHOW SUCCESS TOAST BY JS
                                var toast = document.getElementById("toast");
                                toast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                                toast.innerHTML = "Password updated successfully!";
                                toast.className = "show";

                                // After 3 seconds, remove the show class from DIV and reload
                                setTimeout(function () {
                                    toast.className = toast.className.replace("show", "");
                                    window.location.reload();
                                }, 3000);
                            } else {
                                // SHOW ERROR TOAST BY JS
                                var toast = document.getElementById("toast");
                                toast.style.backgroundColor = "#ff5733"; // Replace with the desired color code
                                toast.innerHTML = "Failed to change password. Please try again later.";
                                toast.className = "show";

                                // After 3 seconds, remove the show class from DIV
                                setTimeout(function () {
                                    toast.className = toast.className.replace("show", "");
                                }, 3000);
                            }
                        }
                    });
                }
            });
        </script>
        <script>
            function validatePasswordForm() {
                //  Get value of all password input sections
                var currentPassword = document.getElementById('current-password').value;
                var newPassword = document.getElementById('new-password').value;
                var retypeNewPassword = document.getElementById('retype-new-password').value;

                //  Get all error message elements
                var currentPasswordError = document.getElementById('current-password-error');
                var newPasswordError = document.getElementById('new-password-error');
                var duplicatedPasswordError = document.getElementById('duplicated-password-error');
                var retypeNewPasswordError = document.getElementById('retype-new-password-error');

                //  Hide all error message elements
                currentPasswordError.classList.add('d-none');
                newPasswordError.classList.add('d-none');
                duplicatedPasswordError.classList.add('d-none');
                retypeNewPasswordError.classList.add('d-none');

                //  Validate conditions and display corresponding error message
                //  Old password cannot leave blank
                if (currentPassword.trim() === '') {
                    currentPasswordError.classList.remove('d-none');
                    return false;
                }
                /*
                 * New password must meet requirements:
                 * 1. 8 characters long at least
                 * 2. Contain a lowercase character.
                 * 3. Contain an uppercase character.
                 * 4. Contain a special character.
                 * 5. Contain a number.
                 * 6. Is not duplicated with the old password.
                 */
                //  Validate requirements 1 to 5.
                var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+}{"':;?/>.<,])(?=.*[^\s]).{8,}$/;
                if (!passwordPattern.test(newPassword)) {
                    newPasswordError.classList.remove('d-none');
                    return false;
                }
                //  Validate requirement 6.
                if (newPassword === currentPassword) {
                    duplicatedPasswordError.classList.remove('d-none');
                }
                //  Retype password must match with new password
                if (newPassword !== retypeNewPassword) {
                    retypeNewPasswordError.classList.remove('d-none');
                    return false;
                }

                //  If all conditions are met, allow the form to submit
                return true;
            }
        </script>
    </body>
</html>
