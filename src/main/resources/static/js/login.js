/*
 * Mostrar u ocultar la contraseña.
 */
function togglePassword() {

    const password = document.getElementById("contrasena");
    const icon = document.getElementById("passwordIcon");

    if (password.type === "password") {

        password.type = "text";

        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");

    } else {

        password.type = "password";

        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");

    }

}

/*
 * Mostrar indicador de carga al enviar el formulario.
 */
document.addEventListener("DOMContentLoaded", function () {

    const form = document.querySelector("form");

    const submitButton = form.querySelector("button[type='submit']");

    form.addEventListener("submit", function () {

        submitButton.disabled = true;

        submitButton.innerHTML =
            '<span class="spinner-border spinner-border-sm me-2" role="status"></span>Iniciando sesión...';

    });

});