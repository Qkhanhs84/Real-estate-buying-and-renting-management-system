<%--
  Created by IntelliJ IDEA.
  User: quock
  Date: 10/15/2024
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thông tin khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home Admin</a>
                </li>

                <li class="active">Thông tin khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>
        <div class="page-content" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
            <div class="page-header">
                <c:if test="${empty customer.id}">
                    <h1>
                        Thêm thông tin khách hàng

                    </h1>
                </c:if>
                <c:if test="${not empty customer.id}">
                    <h1>
                        Sửa thông tin khách hàng

                    </h1>
                </c:if>
            </div>
            <div class="row">
                <form:form modelAttribute="customer" method="get" id="customerForm" action="/admin/customer-edit">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-xs-3">Tên khách hàng</label>
                                <div class="col-xs-9">
                                    <form:input path="fullName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại</label>
                                <div class="col-xs-9">
                                    <form:input path="phone" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <form:input path="email" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên công ty</label>
                                <div class="col-xs-9">
                                    <form:input path="companyName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input path="demand" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tình trạng</label>
                                <div class="col-xs-9">
                                    <form:select path="status" class="form-control">
                                        <form:options items="${status}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${empty customer.id}">
                                        <button class="btn btn-primary" id="btnAddBuilding" type="button"
                                                onclick="addOrUpdateCustomer()">Thêm thông tin
                                        </button>
                                    </c:if>
                                    <c:if test="${!empty customer.id}">
                                        <button class="btn btn-warning" id="btnUpdateBuilding" type="button"
                                                onclick="addOrUpdateCustomer(${customer.id})">Sửa thông tin
                                        </button>
                                    </c:if>
                                    <a href="/admin/customer-list">
                                        <button class="btn btn-primary" type="button">Hủy thao tác</button>
                                    </a>

                                </div>
                            </div>
                        </form>
                    </div>
                </form:form>
            </div>
            <c:forEach var="item" items="${transactions}">
                <div class="row form-group">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue">${item.value}</h3>
                        <button class="btn btn-primary" onclick="openModelFadeAdd('${item.key}',${customer.id})">
                            <div>
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="16" fill="currentColor"
                                     class="bi bi-plus-square-fill" viewBox="0 0 16 16">
                                    <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0"/>
                                </svg>
                                <span>Thêm giao dịch</span>
                            </div>

                        </button>
                    </div>
                </div>
                <c:if test="${item.key == 'DDX' }">

                    <div class="row">
                        <div class="col-xs-12">
                            <table class="table table-responsive table-bordered">
                                <thead>
                                <tr>
                                    <th class="center">Ngày tạo</th>
                                    <th class="center">Người tạo</th>
                                    <th class="center">Ngày sửa</th>
                                    <th class="center">Người sửa</th>
                                    <th class="center">Chi tiết giao dịch</th>
                                    <th class="center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="it" items="${DDX}">
                                    <tr>
                                        <td class="center">${it.createdDate}</td>
                                        <td class="center">${it.createdBy}</td>
                                        <td class="center">${it.modifiedDate}</td>
                                        <td class="center">${it.modifiedBy}</td>
                                        <td class="center">${it.note}</td>
                                        <td class="center">
                                            <button onclick="openModelFadeEdit(${it.id},'${it.note}','${item.key}',${customer.id})"
                                                    class="btn btn-xs btn-info">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-pencil-square"
                                                     viewBox="0 0 16 16">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                    <path fill-rule="evenodd"
                                                          d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                                </svg>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button onclick="deleteTransaction(${it.id})"
                                                        class="btn btn-xs btn-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                         fill="currentColor" class="bi bi-calendar-x"
                                                         viewBox="0 0 16 16">
                                                        <path d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708"/>
                                                        <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                                                    </svg>
                                                </button>
                                            </security:authorize>
                                        </td>


                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>
                        </div>
                    </div>

                </c:if>
                <c:if test="${item.key == 'CSKH' }">

                    <div class="row">
                        <div class="col-xs-12">
                            <table class="table table-responsive table-bordered">
                                <thead>
                                <tr>
                                    <th class="center">Ngày tạo</th>
                                    <th class="center">Người tạo</th>
                                    <th class="center">Ngày sửa</th>
                                    <th class="center">Người sửa</th>
                                    <th class="center">Chi tiết giao dịch</th>
                                    <th class="center">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="it" items="${CSKH}">
                                    <tr>
                                        <td class="center">${it.createdDate}</td>
                                        <td class="center">${it.createdBy}</td>
                                        <td class="center">${it.modifiedDate}</td>
                                        <td class="center">${it.modifiedBy}</td>
                                        <td class="center">${it.note}</td>
                                        <td class="center">
                                            <button onclick="openModelFadeEdit(${it.id},'${it.note}','${item.key}',${customer.id})"
                                                    class="btn btn-xs btn-info">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-pencil-square"
                                                     viewBox="0 0 16 16">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                    <path fill-rule="evenodd"
                                                          d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                                                </svg>
                                            </button>
                                            <security:authorize access="hasRole('MANAGER')">
                                                <button onclick="deleteTransaction(${it.id})"
                                                        class="btn btn-xs btn-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                         fill="currentColor" class="bi bi-calendar-x"
                                                         viewBox="0 0 16 16">
                                                        <path d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708"/>
                                                        <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                                                    </svg>
                                                </button>
                                            </security:authorize>
                                        </td>

                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>
                        </div>
                    </div>

                </c:if>
            </c:forEach>
        </div>

    </div>
</div>
<div class="modal" id="editTransaction" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
    <div class="modal-dialog">
        <div class="row">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Chi tiết giao dịch</h4>

                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <div class="form-group">
                        <input type="text" class="form-control" id="note" name="note">
                    </div>
                </div>
                <input type="hidden" id="customerId" name="customerId" value="">
                <input type="hidden" id="code" name="code" value="">
                <input type="hidden" id="id" name="id" value="">
                <!-- Modal footer -->
                <div class="modal-footer">
                    <div class="form-group pull-right">


                        <button type="button" class="btn btn-danger" id="btnTransaction"
                                onclick="addOrEditTransaction()">Sửa giao dịch
                        </button>


                        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

<script>
    function checkAddOrUpdate(str = "") {
        var id = document.getElementById("id").value;
        console.log(id);
        if (id == "") {
            document.getElementById("btnTransaction").textContent = "Thêm giao dịch";
        } else {
            document.getElementById("btnTransaction").textContent = "Sửa giao dịch";
        }
        document.getElementById("note").value = str;
    }

    function openModelFadeAdd(code, customerId) {
        $('#id').val("");
        checkAddOrUpdate();

        $('#code').val(code);
        $('#customerId').val(customerId);
        $('#editTransaction').modal();
    }


    function openModelFadeEdit(id, str,code,customerId) {
        $('#id').val(id);
        $('#code').val(code);
        $('#customerId').val(customerId);
        checkAddOrUpdate(str);

        $('#editTransaction').modal();

    }

    function addOrEditTransaction() {
        var json = {};
        json['id'] = $('#id').val();
        json['note'] = $('#note').val();
        json['customerId'] = $('#customerId').val();
        json['code'] = $('#code').val();
        addOrUpdateTransactionAjax(json);
    }

    function addOrUpdateTransactionAjax(json) {
        $.ajax({
            type: "POST",
            url: "/api/transactions",
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function () {
                alert("Create or Update Transaction Successfully");
                window.location.replace("/admin/customer-edit-${customer.id}");
            },
            error: function () {
                alert("Create or Update Transaction Failed");
            }
        })
    }

    function deleteTransaction(id) {
        $.ajax({
            type: "DELETE",
            url: "/api/transactions/" + id,
            success: function () {
                alert("Delete Transaction Successfully");
                window.location.replace("/admin/customer-edit-${customer.id}");
            },
            error: function () {
                alert("Delete Transaction Failed");
            }
        })
    }

    function addOrUpdateCustomer(customerId = null) {

        var json = {};
        var formData = $('#customerForm').serializeArray();
        $.each(formData, function (i, item) {
            json["" + item.name + ""] = item.value;

        });
        if (customerId != null) {
            json["id"] = customerId;
        }
        if (json.fullName == "") {
            alert("Tên khách hàng không được để trống");
            return;
        }
        if (json.phone == "") {
            alert("Số điện thoại không được để trống");
            return;
        }
        addOrUpdate(json);
    }

    function addOrUpdate(data) {
        $.ajax({
            type: "POST",
            url: "/api/customers",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function () {
                alert("Create or Update Customer Successfully");
                window.location.replace("/admin/customer-list");
            },
            error: function () {

                alert("Create or Update Building Failed");
            }
        })
    }
</script>
</body>

</html>
