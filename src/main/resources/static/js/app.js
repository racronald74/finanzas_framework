/*
 * Funciones JavaScript reutilizables
 * del sistema.
 */

/*
 * Confirmar eliminación.
 */
function confirmarEliminacion(boton) {

    const url = boton.dataset.url;

    Swal.fire({
        title: "¿Eliminar registro?",
        text: "Esta acción no se puede deshacer.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
        confirmButtonColor: "#d33",
        cancelButtonColor: "#6c757d"
    }).then((result) => {

        if (result.isConfirmed) {

            window.location.href = url;

        }

    });

}

/*
 * Mostrar mensaje de éxito.
 */
function mostrarMensajeExito(mensaje) {

    if (!mensaje) {
        return;
    }

    Swal.fire({

        icon: "success",

        title: mensaje,

        timer: 1800,

        showConfirmButton: false

    });
    

}