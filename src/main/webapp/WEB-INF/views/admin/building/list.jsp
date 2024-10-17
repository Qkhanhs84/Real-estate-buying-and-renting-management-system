<%--
  Created by IntelliJ IDEA.
  User: quock
  Date: 7/13/2024
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="buildingAPI" value="/api/buildings/"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">


            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

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
                                <form:form modelAttribute="modelSearch" action="/admin/building-list" id="listForm"
                                           method="get">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <div>
                                                        <label>Tên tòa nhà</label>
                                                        <form:input class="form-control" path="name" id="name"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-6">
                                                    <div>
                                                        <label>Diện tích sàn</label>
                                                        <form:input class="form-control" path="floorArea"
                                                                    id="floorArea"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <div>
                                                        <label>Chọn Quận</label>
                                                        <form:select class="form-control" path="district">
                                                            <form:option value="">--Chọn quận--</form:option>
                                                            <form:options items="${districtCode}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-xs-5">
                                                    <div>
                                                        <label>Phường</label>
                                                        <form:input class="form-control" path="ward" id="ward"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-5">
                                                    <div>
                                                        <label>Đường</label>
                                                        <form:input class="form-control" path="street" id="street"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Số tầng hầm</label>
                                                        <form:input class="form-control" path="numberOfBasement"
                                                                    id="numberOfBasement"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Hướng</label>
                                                        <form:input class="form-control" path="direction"
                                                                    id="direction"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label>Hạng</label>
                                                        <form:input class="form-control" path="level" id="level"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <div>
                                                        <label>Diện tích từ</label>
                                                        <form:input class="form-control" path="areaFrom" id="areaFrom"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">
                                                    <div>
                                                        <label>Diện tích đến</label>
                                                        <form:input class="form-control" path="areaTo" id="areaTo"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">
                                                    <div>
                                                        <label>Giá thuê từ</label>
                                                        <form:input class="form-control" path="rentPriceFrom"
                                                                    id="rentPriceFrom"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">
                                                    <div>
                                                        <label>Giá thuê đến</label>
                                                        <form:input class="form-control" path="rentPriceTo"
                                                                    id="rentPriceTo"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">

                                                <div class="col-xs-5">
                                                    <div>
                                                        <label>Tên quản lý</label>
                                                        <form:input class="form-control" path="managerName"
                                                                    id="managerName"/>
                                                    </div>
                                                </div>
                                                <div class="col-xs-5">
                                                    <div>
                                                        <label>SĐT quản lý</label>
                                                        <form:input class="form-control" path="managerPhone"
                                                                    id="managerPhone"/>
                                                    </div>
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <div class="col-xs-2">
                                                        <div>
                                                            <label>Chọn nhân viên</label>
                                                            <form:select class="form-control" path="staffId">
                                                                <form:option value="">--Chọn nhân viên--</form:option>
                                                                <form:options items="${staffs}"/>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </security:authorize>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <form:checkboxes path="typeCode" items="${typeCode}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <button class="btn btn-info" type="button" id="btnSearch">
                                                        <i class="fa fa-search"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="/admin/building-edit">
                                <button class="btn btn-app btn-purple btn-sm" title="Thêm tòa nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-building-add" viewBox="0 0 16 16">
                                        <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                    </svg>

                                </button>
                            </a>
                            <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-app btn-danger btn-sm" fdprocessedid="3et0vg" title="Xóa tòa nhà"
                                    id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-fill-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>

                            </button>
                            </security:authorize>

                        </div>
                    </div>
                </div>
            </div>
            <div class="hr hr-18 dotted hr-double"></div>
            <!-- Table -->
            <div class="row">
                <div class="col-xs-12 ">
                    <div class="table-responsive center">
                        <display:table name="buildingList" cellspacing="0" cellpadding="0"
                                       requestURI="/admin/building-list" partialList="true" sort="external"
                                       size="${modelSearch.totalItems}" defaultorder="ascending"
                                       id="buildingList" pagesize="${modelSearch.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer "
                                       style="margin: 3em 0 1.5em;">
                            <display:column>

                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${buildingList.id}">
                                    <span class="lbl"></span>
                                </label>


                            </display:column>

                            <display:column headerClass="center" property="name" title="Tên tòa nhà"/>
                            <display:column headerClass="center" property="address" title="Địa chỉ"/>
                            <display:column headerClass="center" property="numberOfBasement" title="Số tầng hầm"/>
                            <display:column headerClass="center" property="managerName" title="Tên quản lý"/>
                            <display:column headerClass="center" property="managerPhone" title="SĐT quản lý"/>
                            <display:column headerClass="center" property="floorArea" title="Diện tích sàn"/>
                            <display:column headerClass="center" property="rentArea" title="Diện tích thuê"/>
                            <display:column headerClass="center" property="emptyArea" title="Diện tích trống"/>
                            <display:column headerClass="center" property="rentPrice" title="Giá thuê"/>
                            <display:column headerClass="center" property="serviceFee" title="Phí dịch vụ"/>
                            <display:column headerClass="center" property="brokerageFee" title="Phí môi giới"/>
                            <display:column>
                                <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                            onclick="showAssignmentBuilding(${buildingList.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>
                                </security:authorize>


                                    <button class="btn btn-xs btn-info" title="Sửa tòa nhà">
                                        <a href="/admin/building-edit-${buildingList.id}">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>
                                    </button>

                                    <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"
                                            onclick="deleteBuilding(${buildingList.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>
                                    </security:authorize>


                                </div>
                            </display:column>


                        </display:table>
                    </div>

                </div>
            </div>

        </div><!-- /.page-content -->

    </div>
</div><!-- /.main-content -->
<div class="modal" id="assignmentBuildingModal" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>

        </div>
    </div>
</div>
<script>
    function showAssignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);
    }

    function loadStaffs(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}" + buildingId + "/staffs",
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
                alert("Load Staffs Failed")
                console.log(response);
            }


        });
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault(); // -> tranh truong hop load lại trang nhiều lần
        var data = {};
        data['Id'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;

        assignmentBuilding(data);
    })

    function assignmentBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}staffs",
            data: JSON.stringify(data),

            contentType: "application/json",
            success: function () {
                alert("Assign Successfully");
                location.reload();
            },
            error: function (response) {

                alert("Assign Failed");
            }

        });

    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};

        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['buildingIds'] = buildingIds;

        deleteBuildings(data);
    })

    function deleteBuilding(buildingId) {
        var data = {}
        data['buildingIds'] = buildingId;
        deleteBuildings(data);
    }

    function deleteBuildings(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}" + data['buildingIds'],
            data: JSON.stringify(data),

            contentType: "application/json",
            success: function () {
                alert("Deleted Successfully");
                location.reload();
            },
            error: function () {
                alert("Deleted Failed");
            }

        });

    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>
