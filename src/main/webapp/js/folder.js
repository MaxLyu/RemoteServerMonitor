$(document).ready(function(){
    fun();
});

var file;

String.prototype.endWith=function(s){
    if(s==null||s===""||this.length===0||s.length>this.length)
        return false;
    if(this.substring(this.length-s.length)===s)
        return true;
    else
        return false;
    return true;
};

function fun(){
    $.ajax({
        type:"POST",
        url:"folder",
        success:function(result){
            init(result);
        }
    });
}

function init(json){
    var obj = eval(json);
    var count = obj.length;
    var div;
    var img;
    var name;
    var filename;
    var uploadDiv,uploadImg, uploadText;
    for(var i=0;i<count;i++){
        div = document.createElement("DIV");
        img = document.createElement("DIV");
        name = document.createElement("DIV");
        div.className = "filebox";
        img.className = "iconBox";
        name.className = "nameBox";
        filename = obj[i].text;
        name.innerHTML = filename;
        div.appendChild(img);
        div.appendChild(name);
        if(obj[i].state === "closed"){
            if(obj[i].empty === "true")
                img.style.background="url(./image/empty_folder.png) no-repeat center center";
            else
                img.style.background="url(./image/folder.png) no-repeat center center";
            div.ondblclick = function(){
                var filename = $(this).text();
                $.ajax({
                    type:"post",
                    data:'filename='+filename,
                    url:"folder"
                });
                location.reload();
            };
        }else if(filename.endWith(".png") || filename.endWith(".jpg")){
            img.style.background="url(./image/img.png) no-repeat center center";
            div.ondblclick = function(){
                var filename = $(this).text();
                var img = document.getElementById("image");
                img.src="img"+"?filename="+filename;
                $("#image_back").fadeIn("slow");
                $("#image_area").click(function() {
                    $("#image_back").fadeOut("slow");
                })
            };
        }else if(filename.endWith(".mp3") || filename.endWith(".m4a")|| filename.endWith(".wav")){
            img.style.background="url(./image/music.png) no-repeat center center";
            div.ondblclick = function(){
                var filename = $(this).text();
                $("#music_back").fadeIn("slow");
                $("#music_area").click(function() {
                    $("#music_back").fadeOut("slow");
                    var music = document.getElementById("audio");
                    music.pause();
                });
                var music = document.getElementById("audio");
                music.src="music"+"?filename="+filename;
            };
        }else if(filename.endWith(".mp4")){
            img.style.background="url(./image/video.png) no-repeat center center";
            div.ondblclick = function(){
                var filename = $(this).text();
                $("#video_back").fadeIn("slow");
                $("#video_area").click(function() {
                    $("#video_back").fadeOut("slow");
                    var video = document.getElementById("video_player");
                    video.pause();
                });
                var video = document.getElementById("video_player");
                video.src="music"+"?filename="+filename;
            };
        }else{
            img.style.background="url(./image/file.png) no-repeat center center";
            div.ondblclick = function(){
                alert("This file type is not supported");
            };
        }

        div.onmousedown = function(e) {
            if(e.button === 2)
            {
                file = $(this).text();
                $(document).bind('contextmenu',function(e){
                    e.preventDefault();
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                });
            }
        };

        div.onmousemove = function(){
            this.style.backgroundColor="#F0F8FF";
        };

        div.onmouseout = function(x){
            this.style.backgroundColor="#ffffff";
        };
        $('#canvas').append(div);
    }
    div = document.createElement("DIV");
    img = document.createElement("DIV");
    name = document.createElement("DIV");
    div.className = "filebox";
    img.className = "iconBox";
    name.className = "nameBox";
    img.style.background="url('./image/upload.png') no-repeat center center";
    name.innerHTML = "UPLOAD";
    div.appendChild(img);
    div.appendChild(name);
    div.onclick = function(){
        $('#dialog').dialog('open');
        $('#name').filebox({
            buttonText: 'Choose File',
            buttonAlign: 'right'
        })
    };
    div.onmousemove = function(){
        //this.style.backgroundColor="#F0F8FF";
        $(this).find('.filebox').addClass('hotshadow');

    };

    div.onmouseout = function(x){
        //this.style.backgroundColor="#ffffff";
    };
    $('#canvas').append(div);
}

function menuHandler(item){
    if(item.name === "Download"){

        var $eleForm = $("<form method='get'></form>");
        $eleForm.attr("action","download");
        $(document.body).append($eleForm);
        if ($eleForm.submit()){
            $.ajax({
                type:"post",
                data:'name='+ file,
                url:"download"
            });
        }
    }
    else {
        if (confirm("确认删除文件： " + file + "?")) {
            $.ajax({
                type:"post",
                data:'name='+ file,
                url:"delete"
            });
            location.reload();
            return true;
        }
        else return false;
    }
}
