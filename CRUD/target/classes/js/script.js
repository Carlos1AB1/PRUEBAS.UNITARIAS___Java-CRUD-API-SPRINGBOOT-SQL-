document.addEventListener("DOMContentLoaded", function() {
    console.log("Script cargado correctamente.");

    document.querySelectorAll(".delete-btn").forEach(button => {
        button.addEventListener("click", function() {
            let studentId = this.getAttribute("data-id");

            if (confirm("¿Estás seguro de eliminar este estudiante?")) {
                fetch(`/student/delete/${studentId}`, {
                    method: "POST" // Si cambiaste @GetMapping a @PostMapping
                })
                    .then(response => {
                        if (response.redirected) {
                            window.location.href = response.url; // Recargar lista de estudiantes
                        } else {
                            alert("Error al eliminar el estudiante.");
                        }
                    })
                    .catch(error => console.error("Error:", error));
            }
        });
    });
});
