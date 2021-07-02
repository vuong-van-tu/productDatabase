<%--
  Created by IntelliJ IDEA.
  User: ACER PC
  Date: 7/1/2021
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Products List</title>

</head>
<body>
<h1>Products</h1>
<p>
    <a href="/product?action=create">Create Product</a>
</p>
<p>
<form method="get">
    <input type="hidden" value="search" name="action">
    <input type="text" name="nameSearch">
<%--    <input type="text" name="maxPrice">--%>
    <button>Search</button>
</form>

</p>
<table border="1px">
    <tr>
<%--        <td>ID</td>--%>
        <td>Name</td>
        <td>Price</td>
        <td>Description</td>
        <td>NSX</td>
        <td colspan="2" style="text-align: center"><form method="get">
            <input type="hidden" value="sort" name="action">
            <button style="width: 50px;margin-top: 10px ">Sort</button>
        </form></td>
    </tr>
    <c:forEach items="${requestScope['products']}" var="product">
        <tr>
<%--            <td>${product.id}</td>--%>
            <td><a href="/product?action=view&id=${product.id}">${product.name}</a></td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.nsx}</td>
            <td><a href="/product?action=edit&id=${product.id}">edit</a></td>
            <td><a href="/product?action=delete&id=${product.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
