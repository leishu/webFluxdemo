<!DOCTYPE html>
<html >
<head>
    <title >${title}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <p class="navbar-text">Thymeleaf plain</p>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Home</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <h3 >${title}</h3>
    <div >${message}</div>
    <br/>
    <ul class="list-group">
        <li class="list-group-item list-group-item-success">当前时间(1秒刷新)：
            <span id="now-1" >${date?string("yyyy-MM-dd HH:mm:ss")}</span>
        </li>
        <li class="list-group-item list-group-item-info">当前时间(5秒刷新)：
            <span id="now-5" >${date?string("yyyy-MM-dd HH:mm:ss")}</span>
        </li>
    </ul>

</div>
</body>
<script type="text/javascript">

    function interval1() {
        this.source = null;

        this.start = function () {
            var now = document.getElementById("now-1");

            this.source = new EventSource('/now');

            this.source.addEventListener("message", function (event) {
                now.innerHTML = event.data;
            });
            this.source.onerror = function () {
                this.close();
            };
        };

        this.stop = function () {
            this.source.close();
        };
    }

    function interval5() {
        this.source = null;

        this.start = function () {
            var now = document.getElementById("now-5");

            this.source = new EventSource('/now5');

            this.source.addEventListener("message", function (event) {
                now.innerHTML = event.data;
            });
            this.source.onerror = function () {
                this.close();
            };
        };

        this.stop = function () {
            this.source.close();
        };
    }

    now1 = new interval1();
    now5 = new interval5();

    window.onload = function() {
        now1.start();
        now5.start();
    };

    window.onbeforeunload = function() {
        now1.stop();
        now5.stop();
    }
</script>
</html>