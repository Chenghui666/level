<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>省市级联查询</title>
  <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
  <script type="text/javascript">
    function loadDataAjax() {
      $.ajax({
        url:"queryProvince",
        type:"get",
        dataType:"json",
        success:function (resp) {
          //删除旧的数据，把已经存在的数据清空
          $("#province").empty();
          $.each(resp,function (i, n) {
            $("#province").append("<option value='"+n.id+"'>"+n.name+"</option>");
          })
        }
      })
    }
    $(function () {
      //$(function () {})在页面dom对象成功加载之后执行的函数，我们再次执行ajax请求
      loadDataAjax();
      //绑定事件
      $("#btnLoad").click(function () {
        loadDataAjax();
      })

      //给省份的下拉列表框绑定一个changes事件，当select发生变化是触发事件
      $("#province").change(function () {
        //获取选中的列表框的值
        var obj = $("#province>option:selected");
        var provinceId = obj.val();   //保存选中的省份编号
        $.post("queryCity",{pioid:provinceId},callback,"json")
      })
    })

    //定义一个处理返回数据的函数
    function callback (rest){
      $("#city").empty();
      $.each(rest,function (i,n) {
        $("#city").append("<option value='"+n.id+"'>"+n.name+"</option>");
      })
    }
  </script>
</head>
<body>
  <p>省市级联查询，使用ajax</p>
  <div>
    <table border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td>省份名称</td>
        <td>
          <select id="province">
            <option value="0">请选择.....</option>
          </select>
          <input type="button" value="load数据" id="btnLoad"/>
        </td>
      </tr>
      <tr>
        <td>城市：</td>
        <td>
          <select id="city">
            <option value="0">请选择......</option>
          </select>
        </td>
      </tr>
    </table>
  </div>
</body>
</html>