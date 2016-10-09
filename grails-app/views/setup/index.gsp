<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'setup.css')}" type="text/css">
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
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
</body>
</html>