<% Object contextRoot = request.getContextPath(); %><%--
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
<a href="/product/report">Generate Product Report</a><br><br>
<button id = "generatedPdf">(PDF)</button><br><br>
<a href="" onclick="openNewWindowForJasperWithCharts();">Generate Product Report(pdf)</a>


</body>
<script language="JavaScript">
    function openNewWindowForJasperWithCharts(){
        var url  = "/product/pdf";
        var strWindowFeatures = "menubar=no,location=no,width=800,height=500";
        window.open(url,"_blank", "location=0,height=500,width=800");
    }
</script>

<script language="javascript">
    $("#generatedPdf").click(function(){
        $.ajax({
            type    :'POST',
            url     :'<%=contextRoot%>/product/pdf',
            data    : $("#dbcni").serialize(),
            async   : false,
            success : function(data,textStatus,jqXHR){

                var sUrl = "<%=contextRoot%>/reportsTemp/"+ JSON.parse(jqXHR.responseText).fileName+ ".pdf";
                var strWinStyle   = "width=990 height=500 toolbar=no menubar=no location=no scrollbars=no resizable=no fullscreen=no ";
                var popup = window.open(sUrl, 'popup', strWinStyle);
            },
            error   : function(jqXHR, textStatus, errorThrown) {
                alert(JSON.parse(jqXHR.responseText).message);
            }
        })
    });
</script>
</html>
