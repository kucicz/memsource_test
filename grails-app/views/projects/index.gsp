<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Ajax First Example</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'projects.css')}" type="text/css">
    <g:javascript plugin="jquery" library="jquery"/>
    <script>
        $(document).ready(function () {
            clearMessage();
            $('#table').find('tr').remove();
            $.ajax({
                url: 'getProjectList',
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    // process response
                    var tableContent = "";
                    $.each(data, function (index) {
                        tableContent += "<tr>";
                        tableContent += "<td>" + data[index].name + "</td>";
                        tableContent += "<td>" + data[index].status + "</td>";
                        tableContent += "<td>" + data[index].sourceLanguage + "</td>";
                        tableContent += "<td>" + data[index].targetLanguages + "</td>";
                        tableContent += "</tr>";
                    });
                    if (tableContent != "") {
                        // fill new data to table
                        $('#table').append(createTableHeader()).append(tableContent);
                    } else {
                        alert("No projects available");
                    }
                },
                error: function (data) {
                    document.getElementById("message").innerHTML = data.responseJSON.error;
                }
            });
            function createTableHeader() {
                return "<tr><th>Name</th><th>Status</th><th>Source language</th><th>Target languages</th></tr>";
            }

            function clearMessage() {
                document.getElementById("message").innerHTML = "";
            }
        });

    </script>
</head>

<body>
<div class="buttonDiv">
    <g:link controller="setup" action="index" name="goToSetup">
        Setup
    </g:link>
</div>

<div id="result">
    <table id="table"></table>
    <div id="message"></div>
</div>

</body>
</html>