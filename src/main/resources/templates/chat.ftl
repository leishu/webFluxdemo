<!DOCTYPE html>
<html  >
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

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar nav nav-sidebar">
                <div class="form-group">
                    <label for="roomName">聊天室</label>
                    <select class="form-control" id="roomName">
                        <#list rooms as room>
                            <option >${room.room}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="chatUser">用户名</label>
                    <input type="text" class="form-control" id="chatUser" placeholder="user">
                </div>
                <button type="button" id="enterRoom" class="btn btn-primary">进入</button>
            </div>

            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">Dashboard</h1>

                <div class="row placeholders" id="chatRecords">
                </div>
                <div class="row placeholders" >
                    <textarea class="form-control" rows="3"></textarea>
                    <button type="button" id="send" class="btn btn-primary">发送</button>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script src="/js/jquery-3.2.1.min.js" />
<script data-th-inline="javascript">
    $('enterRoom').click(function() {
        var user = $('#chatUser').val();
        var room = $('#roomName').sel();
        $.post("/enterRoom", {user: user, room: room}, function(result) {

        });
    });
</script>
</html>