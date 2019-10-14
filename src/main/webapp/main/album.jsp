<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/findAll",
            editurl: "${pageContext.request.contextPath}/album/edit",
            datatype: "json",
            colNames: ["id", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发行时间", "上传时间", "插图",],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "beam", editable: true},
                {name: "count", editable: true},
                {name: "content", editable: true},
                {
                    name: "status", editable: true,
                    edittype: "select",
                    editoptions: {
                        value: "展示:展示;不展示:不展示;",
                    },
                },
                {name: "publish_date", formatter: "date"},
                {name: "create_date", formatter: "date"},
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + a + "' />"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#albumPager",
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [1, 2, 4, 6],
            viewrecords: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {
                search: false
            },
            {
                beforeShowForm: function (obj) {
                    obj.find("#title").attr("readonly", true);
                    obj.find("#cover").attr("disabled", true);
                    obj.find("#author").attr("readonly", true);
                    obj.find("#content").attr("readonly", true);
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {albumId: albumId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid");
                        }
                    });
                    return response
                }
            },
            {}
        )
    });

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "' ></table>" + "<div id='" + divId + "' ></div>"
        );
        $("#" + tableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/findAll?album_id=" + albumId,
            editurl: "${pageContext.request.contextPath}/chapter/edit?album_id=" + albumId,
            datatype: "json",
            colNames: ["标题", "大小", "时长", "上传时间", "音频", "操作"],
            colModel: [

                {name: "title", editable: true},
                {name: "size"},
                {name: "longs",},
                {name: "publish_date", formatter: "date"},
                {
                    name: "filepath", editable: true,
                    edittype: "file",

                },
                {
                    name: "",
                    formatter: function (cellValue, options, rowObject) {
                        /*        return "<a href='
                        ${pageContext.request.contextPath}/mp3/"+rowObject.filepath+"'><span class='glyphicon glyphicon-play-circle'></span></a>"+"                       "+
                        "<a href='
                        ${pageContext.request.contextPath}/chapter/download?filepath="+rowObject.filepath+"'><span class='glyphicon glyphicon-download'></span></a>"
                    //   return "<audio controls src=\"
                        ${pageContext.request.contextPath}/mp3/萧敬腾 - 口是心非.mp3\"></audio>"*/
                        return "<audio style=\"height:35px\" controls src=\"${pageContext.request.contextPath}/mp3/萧敬腾 - 口是心非.mp3\"></audio>" +
                            "<br/><center><a href='${pageContext.request.contextPath}/chapter/download?filepath=" + rowObject.filepath + "'><span class='glyphicon glyphicon-download'></span></a></center>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#" + divId,
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [1, 2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#" + divId,
            {
                search: false
            },
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload",
                        fileElementId: "filepath",
                        data: {chapterId: chapterId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid");
                        }
                    });
                    return response
                }
            },
            {}
        )

    }

</script>


<table id="albumList"></table>
<div id="albumPager"></div>