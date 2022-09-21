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
