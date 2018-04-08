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
            </tr>
            <tr>
                <table id="bouquets_table">
                    <c:if test="${not empty bouquets}">
                        <tr>
                            <th>BouquetId</th>
                            <th>Name</th>
                            <th>Assemble Price</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${bouquets}" var="bouquet">
                            <tr>
                                <td width="5%">${bouquet.id}</td>
                                <td width="25%">${bouquet.name}</td>
                                <td width="15%">${bouquet.assemblePrice}</td>
                                <td width="15%">
                                    <form action="/FlowerGarden?command=bouquet_info&id=${bouquet.id}" method="post">
                                        <button type="submit"> Flowers </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty flowers}">
                        <th>Name</th>
                        <th>Length</th>
                        <th>Freshness</th>
                        <th>Price</th>
                        <th>Petal</th>
                        <th>Spike</th>
                        <c:forEach items="${flowers}" var="flower">
                            <tr>
                                <td width="25%">${flower.name}</td>
                                <td width="15%">${flower.length}</td>
                                <td width="10%">${flower.freshness.freshness}</td>
                                <td width="10%">${flower.price}</td>
                                <c:choose>
                                    <c:when test="${flower.getClass().name == 'com.flowergarden.flowers.Chamomile'}">
                                        <td width="10%">${flower.petals}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>null</td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${flower.getClass().name == 'com.flowergarden.flowers.Rose'}">
                                        <td width="15%">${flower.spike}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>null</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:if>
                 </table>
            </tr>
        </table>
    </body>
</html>