var data = [];
function fun(){
    $.ajax({
        type:"GET",
        url:"disk",
        success:function(result){
            init(result);
        }
    });
}

function init(_data){
    var obj = eval(_data);
    var count = obj.length;
    var div,name,pie;
    var dataset;
    var id;
    for(var i=0;i<count;i++){
        div = document.createElement("DIV");
        pie = document.createElement("DIV");
        pie.style.width= "250px";
        pie.style.height= "200px";
        var myChart = echarts.init(pie);
        name = document.createElement("DIV");
        div.className = "FileBox";
        div.style = "width:300px; height:250px; float:left; padding:6px";
        //pie.className = "iconBox";
        name.className = "nameBox"
        name.style = "width:250px; height:30px; text-align:center; font-size:14px";
        name.innerHTML = obj[i].name;
        pie.id="pie"+i;
        id = "#pie"+i;
        dataset=[{name:"used",value:obj[i].used},{name:"free",value:obj[i].free}];
        myChart.setOption({
            legend: {
                orient: 'vertical',
                x: 'left',
                data:['used','free']
            },
            series : [
                {
                    type: 'pie',
                    radius: '70%',
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        }
                    },
                    //roseType: 'angle',
                    data:[{name:"free",value:obj[i].free,itemStyle: {
                            color: '#b0c4de'}},
                        {name:"used",value:obj[i].used,itemStyle: {
                            color: '#1993ff'
                        }}]


                }
            ]
        })
        div.appendChild(pie);
        div.appendChild(name);

        $('#canvas').append(div);
    }

}
$(document).ready(function(){
    fun();
});