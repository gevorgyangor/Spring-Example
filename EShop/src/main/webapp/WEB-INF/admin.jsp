<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Gor_Gevorgyan
  Date: 05.03.2018
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<spring:form action="/addCategory" method="post" modelAttribute="category">
    <spring:label path="name">Name: </spring:label><br>
    <spring:input path="name"/><br>
    <input type="submit" value="Add Category"/>
</spring:form>
<spring:form action="/addBrand" method="post" modelAttribute="brand">
    <spring:label path="name">Name: </spring:label><br>
    <spring:input path="name"/><br>
    <input type="submit" value="Add Brand"/>
</spring:form>

<spring:form action="/addProduct" method="post" modelAttribute="product" enctype="multipart/form-data">
    <spring:label path="name">Name: </spring:label><br>
    <spring:input path="name"/><br>
    <spring:label path="description">Description: </spring:label><br>
    <spring:input path="description"/><br>
    <spring:label path="price">Price: </spring:label><br>
    <spring:input path="price"/><br>
    <spring:label path="category">Category: </spring:label><br>
    <spring:select path="category" items="${categories}" itemLabel="name"/><br>
    <spring:label path="brand">Brand: </spring:label><br>
    <spring:select path="brand" items="${brands}" itemLabel="name"/><br>
    <input type="file" name="picture"/><br>
    <input type="submit" value="Add Brands"/>

</spring:form>
</body>
</html>
