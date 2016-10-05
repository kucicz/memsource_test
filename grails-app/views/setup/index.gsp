<%--
  Created by IntelliJ IDEA.
  User: vaclav.kucera
  Date: 5.10.2016
  Time: 18:58
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Setup</title>
</head>

<body>
<g:form controller="setup" action="save">
    <label>Username:</label>
    <g:textField name="username" value="${configuration.username}"/><br/>
    <label>Password:</label>
    <g:textField name="password" value="${configuration.password}"/><br/>
    <g:actionSubmit value="Save"/>
</g:form>
</body>
</html>