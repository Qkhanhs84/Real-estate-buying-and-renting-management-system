<%--
  Created by IntelliJ IDEA.
  User: quock
  Date: 10/5/2024
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="customerAPI" value="/api/customers/"/>


<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
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
                <li class="active">Danh sách khách hàng</li>
            </ul>
        </div>
        <div class="page-content" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
            <div class="page-header">
                <h1>Danh sách khách hàng</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm khách hàng</h4>
                            <span class="widget-toolbar">
										<a href="#" data-action="reload">
											<i class="ace-icon fa fa-refresh"></i>
										</a>

										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>

										<a href="#" data-action="close">
											<i class="ace-icon fa fa-times"></i>
										</a>
									</span>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">

                                <div class="row">
                                    <form:form method="GET" action="/admin/customer-list"
                                               id="listForm" modelAttribute="modelSearch">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Tên khách hàng</label>
                                                        <form:input path="fullName" class="form-control" id="name"/>
                                                    </div>

                                                </div>
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Số điện thoại</label>
                                                        <form:input path="phone" class="form-control"
                                                                    id="customerPhone"/>
                                                    </div>

                                                </div>
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Email</label>
                                                        <form:input path="email" class="form-control" id="email"/>
                                                    </div>

                                                </div>


                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Tình trạng</label>
                                                        <form:select path="status" class="form-control" id="status">
                                                            <form:option value="">-- Chọn tình trạng --</form:option>
                                                            <form:options items="${status}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <div class="col-xs-4">
                                                        <div>
                                                            <label>Nhân viên phụ trách</label>
                                                            <form:select path="staffId" class="form-control"
                                                                         id="staffId">
                                                                <form:option
                                                                        value="">-- Chọn nhân viên phụ trách --</form:option>
                                                                <form:options items="${staffs}"/>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </security:authorize>

                                            </div>


                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <button class="btn btn-info" type="submit" id="btnSearch">
                                                        <i class="fa fa-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/customer-edit">
                            <button class="btn btn-app btn-purple btn-sm" title="Thêm khách hàng">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-person-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                    <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
                                </svg>

                            </button>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                            <a href="#">
                                <button class="btn btn-app btn-danger btn-sm" title="Xóa khách hàng" id = "btnDelete">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-person-dash" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                        <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
                                    </svg>

                                </button>
                            </a>
                        </security:authorize>

                    </div>

                </div>
            </div>
            <div class="hr hr-18 dotted hr-double"></div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive center">
                        <display:table name="customers" cellspacing="0" cellpadding="0"
                                       requestURI="/admin/customer-list" partialList="true" sort="external"
                                       size="${modelSearch.totalItems}" defaultorder="ascending"
                                       id="customerList" pagesize="${modelSearch.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer "
                                       style="margin: 3em 0 1.5em;">
                            <display:column>

                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${customerList.id}">
                                    <span class="lbl"></span>
                                </label>


                            </display:column>
                            <display:column headerClass="center" property="fullName" title="Tên khách hàng"/>
                            <display:column headerClass="center" property="phone" title="Số điện thoại"/>
                            <display:column headerClass="center" property="email" title="Email"/>
                            <display:column headerClass="center" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="center" property="createdBy" title="Người thêm"/>
                            <display:column headerClass="center" property="createdDate" title="Ngày thêm"/>
                            <display:column headerClass="center" property="status" title="Tình trạng"/>
                            <display:column title="Thao tác" headerClass="center">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                                onclick="showAssignmentCustomer(${customerList.id})">
                                            <i class="ace-icon fa fa-check bigger-120"></i>
                                        </button>
                                    </security:authorize>


                                    <button class="btn btn-xs btn-info" title="Sửa thông tin khách hàng">
                                        <a href="/admin/customer-edit-${customerList.id}">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>
                                    </button>

                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" title="Xóa khách hàng"
                                        onclick="deleteCustomer(${customerList.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>


                                </div>
                            </display:column>
                        </display:table>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="modal" id="assignmentCustomerModal" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Danh sách nhân viên</h4>

            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th class="center">Tên nhân viên</th>

                    </tr>
                    </thead>

                    <tbody>


                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>

        </div>
    </div>
</div>
<script>
    function showAssignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaffs(customerId);
    }

    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}" + customerId + "/staffs",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += ' <td class="center">';
                    row += ' <input type="checkbox" class="check-box-element" value="' + item.staffId + '" ' + item.checked + ' /> ';
                    row += ' </td>';
                    row += ' <td class="center">';
                    row += item.fullName + ' </td>';
                    row += ' </tr>';
                });
                $('#staffList tbody').html(row);

            },
            error: function (response) {
                alert(response.responseJSON.message);

            }


        });
    }

    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault(); // -> tranh truong hop load lại trang nhiều lần
        var data = {};
        data['id'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;

        assignmentCustomer(data);
    })

    function assignmentCustomer(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}staffs",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                alert(response.message);
                location.reload();
            },
            error: function (response) {

                alert(response.responseJSON.message);
            }

        });

    }

    $('#btnDelete').click(function (e) {
        e.preventDefault();
        var data = {};

        var customerIds = $('#customerList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = customerIds;

        deleteCustomers(data);
    })

    function deleteCustomer(customerId) {
        var data = {}
        data['ids'] = customerId;
        deleteCustomers(data);
    }

    function deleteCustomers(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}" + data['ids'],
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                alert(response.message);
                location.reload();
            },
            error: function (response) {
                alert(response.responseJSON.message);
            }

        });
    }
</script>
</body>

</html>
