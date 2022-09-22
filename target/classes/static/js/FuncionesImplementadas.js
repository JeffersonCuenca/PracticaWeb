function eliminar(id){
    Swal.fire({
        title: '¿Estás seguro?',
        text: "¡No podrás revertir esto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'OK'
      }).then((OK) => {
        if (OK.isConfirmed) {
            $.ajax({
                url: "/tortas/delete/" + id,
                succes: function(res) {
                    console.log(res);
                }
            });
          Swal.fire(
            '¡Eliminado!',
            'Su archivo ha sido eliminado.',
            'success'
          ).then((OK)=>{
            if(OK){
                location.href="/tortas/list"
            }
          });
        }
      })
}

function change(diametroTorta, porcionesTorta){
  diametroTorta = document.getElementById(diametroTorta);
  porcionesTorta = document.getElementById(porcionesTorta);
  porcionesTorta.value ="";
  porcionesTorta.innerHTML = "";
  if(diametroTorta.value == "19"){
  var optionArray = ["|","12 - 15|12 - 15",];

  } else if(diametroTorta.value == "22"){
  var optionArray = ["|","15 - 18|15 - 18",];

  } else if(diametroTorta.value == "28"){
  var optionArray = ["|","22 - 25|22 - 25",];
  };

  for(option = 0;option < optionArray.length; option++){
    var pair = optionArray[option].split("|");
    var newOption = document.createElement("option");
    newOption.value = pair[0];
    newOption.innerHTML = pair[1];
    porcionesTorta.options.add(newOption);
  };    
}  
