<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
    <script type="text/javascript">
        function OnSelectionChange(select) {
            var selectedOption = select.options[select.selectedIndex];
            $.ajax({
                url: 'http://localhost:8080/cars_platform/main.do',
                type: "GET",
                cache: false,
                data: {
                    filterId: selectedOption.value,
                },
                success: function(response) {
                    $('#allPage').html(response);
                },
                error: function(info) {
                    console.log(info)
                }
            });
        }
    </script>
    <title>Объявления о продаже</title>
    <style type="text/css">
        .nowrap {
            white-space: nowrap;
            align: center;
        }
    </style>
</head>
<body>
<div id="allPage" style="font-size: 9pt;">
    <div class="form-group" align="left" style="border: 1px dotted gray; width: 200px; margin-left: 20px; margin-top: 20px">
        <label for="filters">Фильтры:</label>
        <select  onchange="OnSelectionChange(this)" class="form-control" size="1" id="filters" name="filters" title="filters">
            <option disabled selected>Выберите фильтр</option>
            <c:forEach items="${sessionScope.filterOperation.getAllFilters()}" var="entry">
                <option value='<c:out value="${entry.key}"/>'>${entry.value.description}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <h3 align="center">Предложения:</h3>
        <table class="table table-bordered" id="table">
            <thead>
            <tr>
                <th>№</th>
                <th>Марка авто</th>
                <th>Модель</th>
                <th>Цена</th>
                <th>Год выпуска</th>
                <th>Коробка передач</th>
                <th>Тип кузова</th>
                <th>Двигатель</th>
                <th>Пробег</th>
                <th>Привод</th>
                <th>Описание владельца</th>
                <th>Категория</th>
                <th>Владелец объявления</th>
                <th>Продано</th>
                <th>Фото</th>
                <th>Дата публикации</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.allItems}" var="item" varStatus="index">
                <c:set var="itemUserName" scope="request" value="${item.user.name}" />
                <c:set var="itemid" scope="request" value="${item.id}" />
                <tr>
                    <td class="nowrap"><c:out value="${index.count}"/></td>
                    <td class="nowrap"><c:out value="${item.car.brand.description}"/></td>
                    <td class="nowrap"><c:out value="${item.car.model}"/></td>
                    <td class="nowrap"><c:out value="${item.car.price}"/></td>
                    <td class="nowrap"><c:out value="${item.car.creationDate}"/></td>
                    <td class="nowrap"><c:out value="${item.car.transmission.description}"/></td>
                    <td class="nowrap"><c:out value="${item.car.bodyType.description}"/></td>
                    <td class="nowrap"><c:out value="${item.car.engineType.description}"/></td>
                    <td class="nowrap"><c:out value="${item.car.mileage}"/></td>
                    <td class="nowrap"><c:out value="${item.car.wheelDriveType.description}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td>
                        <c:forEach items="${item.category}" var="cat">
                            <div style="border: 1px dotted gray" class="nowrap">
                                <c:out value="${cat.description}"/>
                            </div>
                            <br>
                        </c:forEach>
                    </td>
                    <td class="nowrap"><c:out value="${item.user.name}"/></td>
                    <td class="nowrap">
                        <c:out value="${item.sold}"/>
                        <c:if test="${itemUserName == sessionScope.user.name}">
                            <form action="<%=request.getContextPath()%>/change_status.do?id=${item.id}" method="post">
                                <button type="submit" class="nowrap">Сменить статус</button>
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:forEach items="${item.images}" var="image">
                            <img src="<c:url value='/download.do?name=${image.fileName}'/>" width="100px" height="100px"/>
                        </c:forEach>
                    </td>
                    <td><c:out value="${item.dateCreateItem}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form action="<%=request.getContextPath()%>/add_item.do" method="get" align="center">
        <button type="submit" class="btn btn-primary">Добавить предложение</button>
    </form>
</div>
</body>
</html>