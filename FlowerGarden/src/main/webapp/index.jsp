<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
    <head>
        <title>Bouquet Info</title>
        <link href="css/main.css" type="text/css" rel="stylesheet">
    </head>

    <body>
        <form id="logout" class="function-class" action="/FlowerGarden?command=bouquets" method="post">
            <button class="myButton" type="submit">Show Bouquets</button>
        </form>
        <a href="/FlowerGarden?command=bouquets" class="myButton">Show Bouquets</a>
    </body>
</html>