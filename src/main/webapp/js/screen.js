function update(){
    document.getElementById("screen").src="screen" + "?i=" + Math.random();
    setTimeout(update,5000);
}

$(document).ready(function(){
    update();
});
