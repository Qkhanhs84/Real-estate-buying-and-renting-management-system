<%--
  Created by IntelliJ IDEA.
  User: quock
  Date: 7/13/2024
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home Admin</a>
                </li>

                <li class="active">Thông tin tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content" style = "font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">


            <div class="page-header" >
                <h1>
                    Thông tin tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
                <form:form modelAttribute="building" id = "form-edit">
                 <div class="col-xs-12">
                    <form class="form-horizontal" >
                        <div class="form-group">
                            <label class="col-xs-3">Tên tòa nhà</label>
                            <div class="col-xs-9">
                                <form:input path="name" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-3">
                               <form:select path="district" class = "form-control">
                               <form:option value="">--Chọn quận--</form:option>
                               <form:options items="${districtCode}" />
                               </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">

                                <form:input path="ward" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">

                                <form:input path="street" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input path="structure" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input path="numberOfBasement" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <form:input path="floorArea" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
                                <form:input path="direction" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
                                <form:input path="level" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentArea" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentPrice" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <div class="col-xs-9">
                               <form:input path="rentDescription" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input path="serviceFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input path="carFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input path="motoFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input path="overtimeFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input path="electricityFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tiền nước</label>
                            <div class="col-xs-9">
                                <form:input path="waterFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input path="deposit" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input path="payment" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input path="decorationTime" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="col-xs-3">Loại tòa nhà</label>
                            <div class="col-xs-9">
                                <form:checkboxes items="${typeCode}" path="typeCode" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
                               <form:input path="brokerageFee" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input path="note" class = "form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right">Hình đại diện</label>
                            <div class="col-xs-3">
                                <form:input path="image" type = "file" class = "form-control" id = "uploadImage"/>
                            </div>
                            </div>
                            <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-6">
                                <c:if test="${not empty building.image}">
                                    <c:set var="imagePath" value="/repository${building.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" >
                                </c:if>
                                <c:if test="${empty building.image}">
                                    <img src="/repository/building/default.jpg" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <c:if test="${empty building.id}">
                                    <button class="btn btn-primary" id="btnAddBuilding" type="button" onclick="addOrUpdateBuilding()" >Thêm tòa nhà</button>
                                </c:if>
                                <c:if test="${!empty building.id}">
                                    <button class="btn btn-warning" id="btnUpdateBuilding" type="button" onclick="addOrUpdateBuilding(${building.id})">Sửa tòa nhà</button>
                                </c:if>
                                <a href="/admin/building-list" >
                                 <button class="btn btn-primary" type="button">Hủy thao tác</button>    
                                </a>

                            </div>
                        </div>


                    </form>
                </div>
                </form:form>

            </div>
        </div><!-- /.page-content -->
    </div>

</div><!-- /.main-content -->

<script>
    var imageBase64 = '';
    var imageName = '';
    function addOrUpdateBuilding(buildingId = null){
        var json = {};
        var formData = $('#form-edit').serializeArray();
        var typeCode = [];
        $.each(formData,function(i,it){
            if(it.name != 'typeCode') json["" + it.name +""] = it.value;
            else typeCode.push(it.value) ;
        });
        if ('' !== imageBase64) {
            json['imageBase64'] = imageBase64;
            json['imageName'] = imageName;
        }
        json['typeCode'] = typeCode ;
        if(buildingId != null){
            json["id"] = buildingId ;
        }
        if(json["name"] == ""){
            alert("Tên tòa nhà không được để trống") ;
            return ;
        }
        else if(json["district"] == ""){
            alert("Quận không được để trống") ;
            return ;
        }
        else if (json["typeCode"] == ""){
            alert("Loại tòa nhà không được để trống") ;
            return ;
        }

        addOrUpdate(json) ;
    }

    function addOrUpdate(data){
        $.ajax({
            type:"POST" ,
            url : "/api/buildings" ,
            data : JSON.stringify(data) ,
            contentType : "application/json" ,
            dataType : "json" ,
            success : function(response){
                alert(response.message) ;
                window.location.replace("/admin/building-list") ;
            },
            error : function(response){
                if(response.responseJSON.detail){
                    alert(response.responseJSON.detail) ;
                }
                if(response.responseJSON.error){
                    alert(response.responseJSON.error) ;
                }

            }

        }) ;

    }
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });
    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
