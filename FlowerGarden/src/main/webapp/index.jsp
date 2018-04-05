<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <head>
        <title>Bouquet Info</title>
        <link href="css/main.css" type="text/css" rel="stylesheet">
    </head>

    <body>
        <table id="main_table">
            <tr>
                <td width="10%">
                    <a href="/FlowerGarden?command=bouquets" class="myButton">Show Bouquets</a>
                </td>
                <td>
                <table id="bouquets_table">
                    <c:if test="${not empty bouquets}">
                        <th>BouquetId</th>
                        <th>Name</th>
                        <th>Assemble Price</th>
                        <th>Action</th>
                        <c:forEach items="${bouquets}" var="bouquet">
                            <tr>
                                <td width="5%">${bouquet.id}</td>
                                <td width="25%">${bouquet.name}</td>
                                <td width="15%">${bouquet.assemblePrice}</td>
                                <td width="25%">
                                    <form action="/FlowerGarden?command=bouquet_info&id=${bouquet.id}" method="post">
                                        <button type="submit"> Flowers </button>
                                    <form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty flowers}">


                    </c:if>
                 </table>
                </td>
            </tr>
        </table>
    </body>
</html>