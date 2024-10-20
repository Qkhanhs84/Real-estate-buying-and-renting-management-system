<%--
  Created by IntelliJ IDEA.
  User: quock
  Date: 10/18/2024
  Time: 8:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng kí</title>
</head>
<body>
<section class="vh-100 gradient-custom form-register">

    <div class="container">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                    <div class="card-body p-2 px-5 text-center">
                        <div class="md-5 md-4 mt-4 pb-2">
                            <h2 class="fw-bold mb-2 text-uppercase">Create an account</h2>
                            <p class="text-white-50 mb-2">Please enter your Information</p>
                            <form action="j_spring_security_check" id="formRegister" method="post">
                            <div class="row">
                                <div class="col-md-12 mb-2">
                                    <div class="form-outline">
                                        <label class="form-label" for="fullName">Full name</label>
                                        <input type="text" id="fullName" name = "fullName"
                                               class="form-control form-control-lg"/>
                                    </div>
                                </div>

                            </div>



                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="userName">Username</label>
                                <input type="text" id="userName" name="userName" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="password">Password</label>
                                <input type="password" id="password" name="password" class="form-control form-control-lg"/>
                            </div>

                            <div class="form-outline form-white mb-2">
                                <label class="form-label" for="retypePassword">Repeat your password</label>
                                <input type="password" id="retypePassword" name = "retypePassword" class="form-control form-control-lg"/>
                            </div>
                            </form>
                            <div class="form-check d-flex justify-content-sm-center mb-2">
                                <div>
                                <input class="form-check-input me-2" type="checkbox" value=""
                                       id="agree" name="agree"/>
                                </div>
                                <div>
                                <label class="form-check-label">
                                    I agree all statements in <a href="#" class="text-body"><u
                                        style="color: white ;">Terms of service</u></a>
                                </label>
                                </div>
                            </div>

                            <button class="btn btn-outline-light btn-lg px-5" type="button" onclick="validateAndSend()">Register</button>

                            <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-facebook-f fa-lg"></i></a>
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                <a href="#!" class="login-extension text-white"><i
                                        class="fab fa-google fa-lg"></i></a>
                            </div>

                            <p class="text-center text-muted mt-2 mb-0">Have already an account? <a href="/login"
                                                                                                    class="fw-bold text-body"><u
                                    style="color: white ;">Login here</u></a></p>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function validateAndSend(){

        var data = $('#formRegister').serializeArray();
        var json = {}

        $.each(data,function (index, value){
            json[value.name] = value.value;
        });
        if(json['fullName'] == '' || json['userName'] == '' || json['password'] == '' || json['retypePassword'] == ''){
            alert('Please fill all the fields');
            return ;
        }
        var agree = document.getElementById('agree').checked;
        if(!agree){
            alert('Please agree to the terms of service');
            return ;
        }
        if(json['password'] != json['retypePassword']){
            alert('Password and Repeat Password must be the same');
            return ;
        }
        $.ajax({
            url: "/api/user/register",
            type: "POST",
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType : "text",
            success: function (response){
                alert(response);
                window.location.href = '/login';
            },
            error: function (response){

                alert("Username already exists");
            }

        });

    }
</script>
</body>
</html>

