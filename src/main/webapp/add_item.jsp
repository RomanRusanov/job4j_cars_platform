<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>function addPhoto() {

    }

    function validate() {
            var brand = checkUserInput($('#brand'));
            var model = checkUserInput($('#model'));
            var creation_date = checkUserInput($('#creation_date'));
            var transmission = checkUserInput($('#transmission'));
            var body_type = checkUserInput($('#body_type'));
            var engine_type = checkUserInput($('#engine_type'));
            var mileage = checkUserInput($('#mileage'));
            var wheelDriveType = checkUserInput($('#wheelDriveType'));
            var description = checkUserInput($('#description'));
            var category = checkUserInput($('#category'));
            if (brand !== ""
                && model !== ""
                && creation_date !== ""
                && transmission !== ""
                && body_type !== ""
                && engine_type !== ""
                && mileage !== ""
                && wheelDriveType !== ""
                && description !== ""
                && category !== ""
            ) {
                return true;
            }
            return false;
        }
        function checkUserInput(input) {
            console.log($(input).val())
            var currentInput = $(input).val();
            if (currentInput === ''
                || currentInput === undefined
                || currentInput === null
            ) {
                alert("Поле: "+ $(input).attr('title') + " не заполнено")
                return "";
            }
            return currentInput;
        }

    </script>
    <style type="text/css">
        #col_1, #col_2, #col_3 {
            padding-right: 20px;
            vertical-align: top;
            width: 200px;
        }
    </style>
    <title>Добавление объявления</title>
</head>
<body>
<h3 align="center">Добавление объявления</h3>
<div class="card-body">
    <form action="<%=request.getContextPath()%>/add_item.do" method="post">
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td id="col_1">
                    <div class="form-group">
                        <label for="brand">Марка автомобиля:</label>
                        <select class="form-control" size="1" id="brand" name="brand" title="Марка автомобиля">
                            <option disabled selected value="">Выберите марку</option>
                            <c:forEach items="${requestScope.all_brands}" var="brand">
                                <option value='<c:out value="${brand.id}"/>'>${brand.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="model">Модель автомобиля:</label>
                        <input type="text" id="model" title="Модель автомобиля" class="form-control" name="model" placeholder="Введите вашу модель">
                    </div>
                    <div class="form-group">
                        <label for="price">Стоимость:</label>
                        <input type="text" id="price" title="Стоимость" class="form-control" name="price" placeholder="Введите стоимость">
                    </div>
                    <label for="category">Выберите категорию:</label>
                    <div class="form-group">
                        <select class="form-group" name="category" id="category" title="Категория" multiple>
                            <c:forEach items="${requestScope.all_categories}" var="category">
                                <option value="${category.id}">${category.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
                <td id="col_2">
                    <div class="form-group">
                        <label for="creation_date">Дата выпуска авто:</label>
                        <input type="date" id="creation_date" name="creation_date"
                               title="Дата выпуска авто" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="transmission">Тип КПП:</label>
                        <select class="form-control" size="1" id="transmission" name="transmission" title="Тип КПП">
                            <option disabled selected>Выберите тип КПП</option>
                            <c:forEach items="${requestScope.all_transmission_types}" var="transmission">
                                <option value='<c:out value="${transmission.id}"/>'>${transmission.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="body_type">Тип кузова:</label>
                        <select class="form-control" size="1" id="body_type" name="body_type" title="Тип кузова">
                            <option disabled selected>Выберите тип кузова</option>
                            <c:forEach items="${requestScope.all_body_types}" var="body_type">
                                <option value='<c:out value="${body_type.id}"/>'>${body_type.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for="description">Заполните описание:</label>
                        <textarea class="form-control" id="description" rows="3" cols="45" name="description" title="описание"></textarea>
                    </div>
                </td>
                <td id="col_3">
                    <div class="form-group">
                        <label for="engine_type">Тип двигателя:</label>
                        <select class="form-control" size="1" id="engine_type" name="engine_type" title="Тип двигателя">
                            <option disabled selected>Выберите тип двигателя</option>
                            <c:forEach items="${requestScope.all_engine_types}" var="engine_type">
                                <option value='<c:out value="${engine_type.id}"/>'>${engine_type.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="mileage">Пробег:</label>
                        <input type="text" id="mileage" title="Пробег" class="form-control" name="mileage" placeholder="Введите пробег">
                    </div>
                    <div class="form-group">
                        <label for="wheelDriveType">Привод:</label>
                        <select class="form-control" size="1" id="wheelDriveType" name="wheelDriveType" title="Привод">
                            <option disabled selected>Выберите тип привода</option>
                            <c:forEach items="${requestScope.all_wheel_drive_types}" var="wheel_drive_type">
                                <option value='<c:out value="${wheel_drive_type.id}"/>'>${wheel_drive_type.description}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div style="border: 1px dotted gray">
                        <c:if test="${requestScope.list_size != 0}">
                            <c:forEach items="${sessionScope.imagesAdd}" var="image">
                                <img src="<c:url value='/download.do?name=${image.fileName}'/>" width="100px" height="100px"/>
                            </c:forEach>
                        </c:if>
                        <a href="upload.jsp">Добавить фото</a>
                    </div>
                </td>
            </tr>

        </table>
        <div STYLE="text-align: right">
            <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
        </div>
    </form>
</div>
</body>
</html>
