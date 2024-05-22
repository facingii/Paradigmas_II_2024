<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Viewer</title>

    <script type="text/javascript" language="JavaScript">

        document.addEventListener ("DOMContentLoaded",
            function (e) {
                var x1, y1, x2, y2;
                var clicked = false;

                var element  = document.getElementById("launch");
                element.style.cursor = "crosshair";

                element.addEventListener("mousedown", function (e) {
                    console.log("X: " + e.clientX + ", Y:" + e.clientY);

                    clicked = true;
                    x1 = e.clientX;
                    y1 = e.clientY;

                    e.preventDefault();
                }, false);

                element.addEventListener("mouseup", function (e) {
                    e.preventDefault();

                    console.log("X: " + e.clientX + ", Y:" + e.clientY);
                    x2 = e.clientX;
                    y2 = e.clientY;
                    clicked = false;

                    cut (x1, y1, x2, y2, element);
                }, false);

                element.addEventListener("mousemove", function (e) {
                    e.preventDefault();
                    if (clicked)
                        console.log(e.clientX);
                }, false);
            });

        function cut (x1, y1, x2, y2, img) {
            var ajax = new XMLHttpRequest();
            ajax.responseType = "blob";

            ajax.onreadystatechange = function (data) {
                if (ajax.readyState === 4) {
                    if (ajax.status === 200) {
                        var urlCreator = window.URL || window.webkitURL;
                        var base64Image = urlCreator.createObjectURL(ajax.response);
                        img.src = base64Image;
                    }
                }
            };

            ajax.open("GET", "Cutter?x1=" + x1 + "&y1=" + y1 + "&x2=" + x2 + "&y2=" + y2, true);
            ajax.send();
        }

    </script>

</head>
<body>
    <img src="images/launch.jpg" alt="launch.jpg" id="launch" />
</body>
</html>