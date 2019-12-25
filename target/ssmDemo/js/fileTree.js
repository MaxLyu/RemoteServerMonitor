$(document).ready(function(){
    $('#tt').tree({
        onDblClick: function(node){
            $.ajax({
                type:"post",
                data:'path='+node.id,
                url:"folder"
            });

            window.parent.addFolderTab(00);
        }
    });
});