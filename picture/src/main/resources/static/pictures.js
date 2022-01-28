$(document).ready(function() {
    showImg();
    $('#input').on('submit', function(e){
        e.preventDefault();
        var file = $('#file').get(0).files[0];
            //  console.log("FIle",file);
         let formData = new FormData();
         formData.append("img",file);
         $.ajax({
            type: 'POST',
            url: `/save`,
            data:formData,
            contentType: false,
            processData: false,
            success:function(data){ 
                console.log("Завершилось успешно"); 
                console.log("data",data);
                 addRow(data);
            },
            error: function(data){ 
                console.log("Завершилось с ошибкой"); 
                console.log(data); 
            }
        });
       
     })
 
     
function showImg(){
     $.get( "/all", function( data ) {
        $.each(data, function(i, item) {
           addRow(item);
        });
    });
}

function addRow(item){
    var $tr = $('<tr>').attr("data-id",item.id).append(
        $('<td>').text(item.id),
        $('<td>').text(item.name),
        $('<td>').append($("<img style='height:30px;width: 30px;' src='data:image/jpeg;base64,"+item.img+"'>")),
        $('<td>').append($("<button class='btnRemove' data-id='"+item.id+"'>X</button>"))
    );
    $tr.appendTo('#output tbody');
    subscribeOnRemove();
}
function ImgId(id){
    console.log("idf",id);
let img = document.getElementById('img');
fetch(`/img/${id}`).then(res => res.blob()).then(blob => img.src = URL.createObjectURL(blob));
}

$('#id').on('change', function(e){
    e.preventDefault();
    let id=$(this).val();
    console.log("id",id);
ImgId(id);
})

function subscribeOnRemove(){
    var btn$ = $('button.btnRemove');
      btn$.unbind();
       btn$.on('click', function(e) {
           e.preventDefault();
           var rowId = $(this).attr("data-id");

           $.ajax({
               type: 'DELETE',
               url: `/del/${rowId}`,
               contentType: 'application/x-www-form-urlencoded',//"application/json",
               // dataType: "json",
               // data: JSON.stringify(payload),
                success: function(data) {
                   console.log('Received data after delete: ', data);
                  $(`table#output tr[data-id="${rowId}"]`).remove();
                  // $.remove(`table#shoes tr[data-id="${rowId}"]`);
               },
               error: function(err){
                   console.log('Error during creation of shoes: ', err);
               }
           });
           
       });
       
   }
})

