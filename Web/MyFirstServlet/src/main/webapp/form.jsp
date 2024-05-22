<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
  <head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/form.css"
  </head>
  <body>
    <p>Register</p>
    <div style="display:flex;justify-content:center">
    <form class=form action="formServlet" method="post">
            <label>Name</label>
            <input type="text" name="name" id="name" />
            <label>Lastname</label>
            <input type="text" name="lastname" id="lastname" />
            <label>Age</label>
            <input type="text" name="age" id="age" />
            <label>Address</label>
            <input type="text" name="address" id="address" />
            <input type="submit" name="submit" id="submit" />
    </form>
    </div>
  </body>
</html>
