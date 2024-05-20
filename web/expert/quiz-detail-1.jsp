<%-- 
    Document   : quiz-detail-1
    Created on : Feb 28, 2024, 9:39:31 PM
    Author     : win
--%>
<jsp:useBean id="adao" class="dal.AnswerDAO" scope="request"></jsp:useBean>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mailbox.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
    <head>
        <!-- META ============================================= -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta
            property="og:description"
            content="EduChamp : Education HTML Template"
            />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no" />

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="../error-404.html" type="image/x-icon" />
        <link
            rel="shortcut icon"
            type="image/x-icon"
            href="admin/assets/images/favicon.png"
            />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>EduChamp : Education HTML Template</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!--[if lt IE 9]>
          <script src="admin/assets/js/html5shiv.min.js"></script>
          <script src="admin/assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/assets.css" />
        <link
            rel="stylesheet"
            type="text/css"
            href="admin/assets/vendors/calendar/fullcalendar.css"
            />

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/typography.css" />

        <!-- SHORTCODES ============================================= -->
        <link
            rel="stylesheet"
            type="text/css"
            href="admin/assets/css/shortcodes/shortcodes.css"
            />

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="admin/assets/css/style.css" />
        <link rel="stylesheet" type="text/css" href="admin/assets/css/dashboard.css" />
        <link
            class="skin"
            rel="stylesheet"
            type="text/css"
            href="admin/assets/css/color/color-1.css"
            />
        <link rel="stylesheet" type="text/css" href="assets/css/snackbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/successtoast.css">

    </head>
    <body class="ttr-opened-sidebar ttr-pinned-sidebar">
        <div id="sucesstoast">Updated successfully</div>

        <jsp:include page="expert-header.jsp"></jsp:include>
        <jsp:include page="expert-sidebar.jsp"></jsp:include>     

            <!--Main container start -->
            <main class="ttr-wrapper">
                <div class="container-fluid">
                    <div class="db-breadcrumb">
                        <h4 class="breadcrumb-title">Quiz Detail</h4>
                        <ul class="db-breadcrumb-list">
                            <li>
                                <a href="#"><i class="fa fa-home"></i>Home</a>
                            </li>
                            <li>Quiz Detail</li>
                        </ul>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 m-b30">
                            <div class="widget-box">
                                <div class="wc-title">
                                    <h4>Information</h4>
                                </div>
                                <div class="widget-inner">
                                    <form class="edit-profile m-b30" action="quiz-detail" method="post">
                                        <input name="quizId" value="${quizId}" type="hidden">
                                    <input name="action" value="updateInformation" type="hidden">
                                    <div class="row">
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Title</label>
                                            <div>
                                                <input class="form-control" name="title" type="text" value="${quiz.title}">
                                            </div>
                                        </div>
                                        <div class="form-group col-6">
                                            <label class="col-form-label">Description</label>
                                            <div>
                                                <input class="form-control" name="description" type="text" value="${quiz.content}">
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Course</label>
                                            <div>
                                                <select name="courseId" class="form-control">
                                                    <c:forEach items="${ltCourse}" var="c">
                                                        <option value="${c.id}" ${c.id eq quiz.courseId ? 'checked' : ''}>${c.subjectCode}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Time</label>
                                            <div>
                                                <input type="text" id="time" name="time" class="form-control" placeholder="HH:MM:SS" data-mask="00:00:00" value="${time}">
                                            </div>
                                        </div>
                                        <div class="form-group col-4">
                                            <label class="col-form-label">Status</label>
                                            <div class="mt-2">
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" value="1" name="status" ${quiz.status eq 1 ? 'checked' : ''}>Active
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label">
                                                        <input type="radio" class="form-check-input" value="0" name="status" ${quiz.status eq 0 ? 'checked' : ''}>Inactive
                                                    </label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="seperator"></div>
                                        <div class="col-12">
                                            <button type="submit" class="btn">Save changes</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 m-b30">
                        <div class="widget-box">
                            <div class="wc-title">
                                <h4>Questions & Answers</h4>
                            </div>
                            <div class="widget-inner">
                                <div class="row">
                                    <c:forEach items="${ltQuestion}" var="q">
                                        <div class="col-lg-12 m-b30">
                                            <div class="widget-box">
                                                <div class="email-wrapper">
                                                    <div class="mail-list-container" id="question_${q.id}">
                                                        <input id="questionId_${q.id}" type="hidden" value="${q.id}">
                                                        <div class="mail-toolbar">
                                                            <div class="mail-search-bar w-100">
                                                                <input
                                                                    type="text"
                                                                    class="form-control question-content"
                                                                    value="${q.content}"
                                                                    style="font-weight: bold"
                                                                    />
                                                            </div>
                                                            <div class="dropdown all-msg-toolbar">
                                                                <span onclick="deleteQuestion(${q.id})" class="btn btn-info-icon red" data-toggle="dropdown" title="Delete"
                                                                      >-</span>
                                                            </div>
                                                        </div>
                                                        <div class="mail-box-list">
                                                            <c:forEach items="${adao.getAnswerByQuestion(q.id)}" var="a">
                                                                <div class="mail-list-info" id="answer_${a.id}">
                                                                    <input type="hidden" id="answerId_${a.id}" value="${a.id}">
                                                                    <div class="checkbox-list">
                                                                        <div
                                                                            class="custom-control custom-checkbox checkbox-st1"
                                                                            >
                                                                            <input
                                                                                type="checkbox"
                                                                                class="custom-control-input checkbox"
                                                                                id="check${a.id}"
                                                                                onclick="isCorrect(${a.id})"
                                                                                ${a.isCorrect eq 1 ? 'checked' : ''}
                                                                                />

                                                                            <label
                                                                                class="custom-control-label"
                                                                                for="check${a.id}"
                                                                                ></label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="mail-list-title-info h-100 pt-0 pb-0">
                                                                        <input
                                                                            type="text"
                                                                            class="form-control form-control-sm"
                                                                            value="${a.content}"
                                                                            />
                                                                    </div>
                                                                    <ul class="mailbox-toolbar">
                                                                        <li data-toggle="tooltip" title="Delete" onclick="deleteAnswer(${a.id})">
                                                                            <i class="fa fa-trash-o"></i>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                            </c:forEach>
                                                            <a onclick="insertAnswer(${q.id})" class="btn m-2">+</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <a onclick="insertQuestion(${quizId})" class="btn m-3 pl-3 pr-3"> Add Question</a>
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
        <script src="admin/assets/vendors/scroll/scrollbar.min.js"></script>
        <script src="admin/assets/js/functions.js"></script>
        <script src="admin/assets/vendors/chart/chart.min.js"></script>
        <script src="admin/assets/js/admin.js"></script>
        <script src="admin/assets/vendors/switcher/switcher.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script>
                                        $(document).ready(function () {
                                            $('#time').mask('00:00:00');
                                            $('[data-toggle="tooltip"]').tooltip();
            <c:if test="${sessionScope.isUpdateSuccessfully eq true}">
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
            $(document).ready(function () {
                $('.mail-list-container .question-content').on('blur keydown', function (e) {
                    if (e.type === 'keydown' && e.which !== 13)
                        return; // Ignore keys except 'enter'

                    var questionId = $('#questionId_' + $(this).closest('.mail-list-container').attr('id').split('_')[1]).val();
                    var newContent = $(this).val();

                    // Send AJAX request to update question content
                    $.ajax({
                        url: 'quiz-detail?action=updateQuestion',
                        method: 'post',
                        data: {
                            id: questionId,
                            content: newContent
                        },
                        success: function (response) {
                            console.log('Question content updated successfully');
                        },
                        error: function (xhr, textStatus, errorThrown) {
                            console.log('Error updating question content:', textStatus, errorThrown);
                        }
                    });
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $('.mail-list-title-info input').on('blur keydown', function (e) {
                    if (e.type === 'keydown' && e.which !== 13)
                        return; // Ignore keys except 'enter'

                    var answerId = $('#answerId_' + $(this).closest('.mail-list-info').attr('id').split('_')[1]).val();
                    var answerContent = $(this).val();

                    // Send AJAX request to update answer
                    $.ajax({
                        url: 'quiz-detail?action=answerContent',
                        method: 'post',
                        data: {
                            id: answerId,
                            content: answerContent
                        },
                        success: function (response) {
                            console.log('Answer updated successfully');
                        },
                        error: function (xhr, textStatus, errorThrown) {
                            console.log('Error updating answer:', textStatus, errorThrown);
                        }
                    });
                });
            });
            function isCorrect(answerId) {
                var isChecked = $('#check' + answerId).is(':checked');

                // Send AJAX request to update isCorrect property
                $.ajax({
                    url: 'quiz-detail?action=isCorrect',
                    method: 'post',
                    data: {
                        id: answerId,
                        isCorrect: isChecked ? 1 : 0
                    },
                    success: function (response) {
                        console.log('isCorrect updated successfully');
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error updating isCorrect:', textStatus, errorThrown);
                    }
                });
            }
            function deleteAnswer(answerId) {
                $.ajax({
                    url: 'quiz-detail?action=deleteAnswer',
                    method: 'post',
                    data: {
                        id: answerId
                    },
                    success: function (response) {
                        location.reload();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error', textStatus, errorThrown);
                    }
                });
            }
            function insertAnswer(questionId) {
                $.ajax({
                    url: 'quiz-detail?action=insertAnswer',
                    method: 'post',
                    data: {
                        id: questionId
                    },
                    success: function (response) {
                        location.reload();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error', textStatus, errorThrown);
                    }
                });
            }
            function insertQuestion(quizId) {
                $.ajax({
                    url: 'quiz-detail?action=insertQuestion',
                    method: 'post',
                    data: {
                        id: quizId
                    },
                    success: function (response) {
                        location.reload();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error', textStatus, errorThrown);
                    }
                });
            }
            function deleteQuestion(questionId) {
                $.ajax({
                    url: 'quiz-detail?action=deleteQuestion',
                    method: 'post',
                    data: {
                        id: questionId
                    },
                    success: function (response) {
                        location.reload();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log('Error', textStatus, errorThrown);
                    }
                });
            }
        </script>
    </body>

    <!-- Mirrored from educhamp.themetrades.com/demo/admin/mailbox.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 22 Feb 2019 13:11:35 GMT -->
</html>
<% 
    session.removeAttribute("isUpdateSuccessfully"); 
%>

