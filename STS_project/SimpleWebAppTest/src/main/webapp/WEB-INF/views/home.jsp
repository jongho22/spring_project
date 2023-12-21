<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8" />
    <title>Title</title>
  </head>
  <body>
    <h1>File Upload Example</h1>
    <form method="post" action="<c:url value='/test/upload' />" enctype="multipart/form-data">
      <input type="file" name="file" />
      <input type="submit" value="submit" />
    </form>
  </body>
</html>
