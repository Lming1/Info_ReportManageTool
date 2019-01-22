<%--
  Created by IntelliJ IDEA.
  User: iminhyeog
  Date: 2019-01-15
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<a href="/report/viewer">Generate Product Report</a><br><br>
<a href="" onclick="openNewWindowForJasperWithCharts();">Generate Product Report(pdf)</a>


</body>
<script language="JavaScript">
    function openNewWindowForJasperWithCharts(){
        var url  = "/report/pdf";
        var strWindowFeatures = "menubar=no,location=no,width=800,height=500";
        window.open(url,"_blank", "location=0,height=500,width=800");
    }
</script>

</html>
