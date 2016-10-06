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
                $.ajax({
                    url: 'getprojects',
                    type: 'POST',
                    dataType: 'json',
                    data: {username: $("#username").val(), password: $("#password").val()},
                    success: function (data) {
                        // process response
                        var txt = "";
                        $.each(data, function (index) {
                            txt += "<tr>";
                            txt += "<td>" + data[index].name + "</td>";
                            txt += "<td>" + data[index].status + "</td>";
                            txt += "<td>" + data[index].sourceLanguage + "</td>";
                            txt += "<td>" + data[index].targetLanguages + "</td>";
                            txt += "</tr>";
                        });
                        if (txt != "") {
                            // clear table
                            $('#table tr').remove();
                            // fill new data to table
                            $('#table').append(createTableHeader()).append(txt);
                        } else {
                            alert("No projects available");
                        }
                    }
                });
            });
            function createTableHeader() {
                return "<tr><th>Name</th><th>Status</th><th>Source language</th><th>Target languages</th></tr>";
            }
        });

    </script>
</head>

<body>
<label for="username">username:</label>
<input id="username" type="text" size="20"/>
<label for="password">password:</label>
<input id="password" type="password" size="20"/>
<button id="projectsButton">Get Projects</button>
<table id="table">
</table>
</body>
</html>