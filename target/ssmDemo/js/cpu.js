var updateInterval=50;
var data={};
var c=0;
var points=100;
var options = {
    series: {
        lines: {
            lineWidth: 1
        },
        bars: {
            align: "center",
            fillColor: { colors: [{ opacity: 1 }, { opacity: 1}] },
            barWidth: 500,
            lineWidth: 1
        }
    },
    xaxis: {
        show: false
    },
    yaxis: {
        min: 0,
        max: 1
    },
    legend: {
        noColumns: 0,
        position:"nw"
    },
    grid: {
        backgroundColor: { colors: ["#ffffff", "#EDF5FF"] }
    }
};

function iniate(_data){
    // 将传回的json数据转换成对象
    var obj = eval(_data);
    // 获得cpu个数
    var count = obj.length;
    var canvas;
    // 用for循环动态添加cpu折线图
    for(var i=0;i<count;i++){
        canvas = document.createElement("DIV");
        canvas.style="width:300px; height:200px; float:left; margin:10px";
        canvas.id="canvas"+i;
        $('#canvas').append(canvas);
    }
    for(var i = 0; i < count; i++){
        data['cpu'+i]=[];
    }
}

function toPercent(point){
    var str=Number(point*100).toFixed(1);
    str+="%";
    return str;
}

function update(_data){
    var jsonObj = eval(_data);
    var combined;
    var temp;
    var comPercent;
    var dataset;
    var id;
    // 若外围没有子对象，则代表第一次发出请求，先执行iniate函数初始化界面
    if(document.getElementById("canvas").children.length === 0){
        iniate(_data);
    }else{
        for(var i=0;i<jsonObj.length;i++){
            // 若data数组超过100个数据则删除最前面的数据
            if(data['cpu'+i].length>=points){
                data['cpu'+i].shift();
            }
        }
    }
    for(var i = 0; i < jsonObj.length; i++){
        combined=jsonObj[i];
        temp=[c, combined];
        data['cpu'+i].push(temp);
        comPercent=toPercent(combined);
        dataset=[{label:"CPU" + i + ": "+comPercent,data:data['cpu'+i],lines: { fill: true, lineWidth: 1 }, color: "#1b96ff"}];
        id = "#canvas" + i;
        $.plot($(id), dataset, options);
    }
    c++;
    setTimeout(fun,updateInterval);  // 50毫秒执行一次
}

function fun(){
    $.ajax({
        type:"GET",
        url:"cpu",
        success:function(result){
            update(result);
        }
    });
}

$(document).ready(function(){
    fun();
});
