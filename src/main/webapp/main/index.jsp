<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${pageContext.request.contextPath}/banner/findByPage",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            datatype: "json",
            colNames: ["编号", "标题", "状态", "描述", "创建时间", "图片"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "status", editable: true,
                    edittype: "select",
                    editoptions: {
                        value: "展示:展示;不展示:不展示;",
                    },
                },
                {name: "desc", editable: true},
                {name: "create_date",},
                {
                    name: "img_path", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "' />"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#bannerPager",
            page: 1,
            rowNum: 2,
            rowList: [1, 2, 4, 6],
            viewrecords: true,
            multiselect: true
        }).jqGrid("navGrid", "#bannerPager",
            {
                search: false
            },
            {
                beforeShowForm: function (obj) {
                    obj.find("#title").attr("readonly", true);
                    obj.find("#img_path").attr("disabled", true);
                    obj.find("#desc").attr("readonly", true);
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "img_path",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return response
                }

            },
            {}
        )
    })

    function showModal() {
        $.ajax({
            url: "${pageContext.request.contextPath}/banner/select",
            datatype: "json",
            type: "post",
        })
    }
</script>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图列表</a></li>
        <%--
                <li role="presentation"><a href="#profile" onclick="showModal()" aria-controls="profile" role="tab" data-toggle="tab">导出轮播图</a></li>
        --%>
        <li role="presentation"><a href="${pageContext.request.contextPath}/banner/select">导出轮播图</a></li>

    </ul>

</div>
<table id="bannerList"></table>
<div id="bannerPager"></div>