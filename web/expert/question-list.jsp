<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mailbox-compose.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
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
        <link rel="shortcut icon" type="image/x-icon" href="admin/assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template </title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="admin/assets/js/html5shiv.min.js"></script>
        <script src="admin/assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/assets.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/vendors/calendar/fullcalendar.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/vendors/summernote/summernote.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/vendors/file-upload/imageuploadify.min.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/style.css">
        <link rel="stylesheet" type="text/css" href="admin/assets/css/dashboard.css">
        <link class="skin" rel="stylesheet" type="text/css" href="admin/assets/css/color/color-1.css">
        <link rel="stylesheet" type="text/css" href="assets/css/snackbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/successtoast.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <div id="sucesstoast">Imported successfully</div>
        <div id="alerttoast">Please import file</div>
        <jsp:include page="expert-header.jsp"></jsp:include>
        <jsp:include page="expert-sidebar.jsp"></jsp:include>   

            <!--Main container start -->
            <main class="ttr-wrapper">
                <div class="container-fluid">
                    <div class="db-breadcrumb">
                        <h4 class="breadcrumb-title">Question List</h4>
                        <ul class="db-breadcrumb-list">
                            <li><a href="#"><i class="fa fa-home"></i>Home</a></li>
                            <li>Compose</li>
                        </ul>
                    </div>	
                    <div class="row">
                        <!-- Your Profile Views Chart -->
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box">
                                <div class="email-wrapper">
                                    <div class="email-menu-bar">
                                        <div class="compose-mail">
                                            <a href="#" class="btn btn-block" data-toggle="modal" data-target="#modalImport">Import Question</a>
                                        </div>
                                        <!--MODAL START-->
                                        <div id="modalImport" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-lg modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLongTitle">Import Question</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="question-import" method="post">
                                                            <input name="action" value="download" type="hidden">
                                                            <p class="mb-1">You must use this sample file to import questions. Click <button type="submit" class="btn btn-sm pt-1 pb-1">here</button> to download.</p>
                                                        </form>
                                                        <form id="importfile" action="question-import" method="post" enctype="multipart/form-data">
                                                            <p class="m-0">Upload your file here to import questions</p>
                                                            <input name="action" value="import" type="hidden">
                                                            <div class="card">
                                                                <div class="card-body p-1">
                                                                    <input id="file" onchange="formsubmit()" name="file" type="file" accept=".xlsx" multiple>
                                                                </div>
                                                            </div>
                                                            <div class="alert-danger p-2 d-none mt-2" id="feedback">Please import file</div>
                                                        </form>
                                                        <div id="questionList"></div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" onclick="closeForm()">Close</button>
                                                        <button type="button" class="btn btn-secondary" onclick="submitImport()">Import</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--MODAL END-->


                                    </div>
                                    <div class="mail-list-container">
                                        <table
                                            id="myTable"
                                            class="table table-bordered display"
                                            style="width: 100%"
                                            >
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Content</th>
                                                    <th scope="col">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${ltQuestion}" var="q">
                                                <tr>
                                                    <td>${q.id}</td>
                                                    <td>${q.content}</td>
                                                    <td>${q.status eq 1 ? 'Active' : 'Inactive'}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <!-- Your Profile Views Chart END-->
                </div>
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
        <script src="admin/assets/vendors/summernote/summernote.js"></script>
        <script src="admin/assets/vendors/file-upload/imageuploadify.min.js"></script>
        <script src='admin/assets/vendors/switcher/switcher.js'></script>
        <!-- include plugin -->
        <!--        <script>
                    $(document).ready(function () {
                        $('.summernote').summernote({
                            height: 100,
                            tabsize: 2
                        });
        
                        $('input[type="file"]').imageuploadify();
                    });
                </script>-->
        <script>

        </script>
        <script>
            function formsubmit() {
                event.preventDefault();
                var formData = new FormData(document.getElementById("importfile"));
                var files = document.getElementById("file").files;
                if (files.length !== 0) {
                    $('#feedback').addClass('d-none');
                }
                $.ajax({
                    url: "question-import?action=preview",
                    type: 'POST',
                    data: formData,
                    success: function (data) {
                        document.getElementById('questionList').innerHTML = data;
                    },
                    cache: false,
                    contentType: false,
                    processData: false
                });
            }
        </script>
        <script>
            function submitImport() {
                var files = document.getElementById("file").files;
                if (files.length === 0) {
                    $('#feedback').removeClass('d-none');
                    return false;
                }
                $('#importfile').submit();
            }
        </script>
        <script>
            $(document).ready(function () {
            <c:if test="${sessionScope.isImportSuccess eq true}">
                var successToast = document.getElementById("sucesstoast");
                successToast.style.backgroundColor = "#00ff7f"; // Replace with the desired color code
                successToast.className = "show";

                // After 3 seconds, remove the show class from DIV and redirect
                setTimeout(function () {
                    successToast.className = successToast.className.replace("show", "");
                }, 2000);
            </c:if>
            });
        </script>
        <script>
            function closeForm() {
                document.getElementById("importfile").reset();
                document.getElementById('questionList').innerHTML = "";
                $('#modalImport').modal('toggle');
            }
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mailbox-compose.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:45 GMT -->
</html>

