<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <title>Ajax First Example</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'project.css')}" type="text/css">
    <g:javascript plugin="jquery" library="jquery"/>
    <script>
        $(document).ready(function () {
            $('#projectsButton').click(function () {
                clearMessage();
                $('#table').find('tr').remove();
                $.ajax({
                    url: 'getprojects',
                    type: 'POST',
                    dataType: 'json',
                    data: {userName: $("#userName").val(), password: $("#password").val()},
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
<label for="userName">username:</label>
<input id="userName" type="text" size="20"/>
<label for="password">password:</label>
<input id="password" type="password" size="20"/>
<button id="projectsButton">Get Projects</button>

<div id="result">
    Result:
    <div id="message"></div>
    <table id="table"></table>
</div>

</body>
</html>