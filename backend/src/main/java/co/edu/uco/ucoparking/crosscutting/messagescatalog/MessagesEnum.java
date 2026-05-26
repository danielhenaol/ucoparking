package co.edu.uco.ucoparking.crosscutting.messagescatalog;

public enum MessagesEnum {

    STUDENT_IDENTIFICATION_REQUIRED("La identificación del estudiante es obligatoria."),
    STUDENT_INSTITUTIONAL_CODE_REQUIRED("El código institucional del estudiante es obligatorio."),
    STUDENT_NAME_REQUIRED("El nombre del estudiante es obligatorio."),
    STUDENT_LAST_NAME_REQUIRED("El apellido del estudiante es obligatorio."),
    STUDENT_EMAIL_REQUIRED("El correo del estudiante es obligatorio."),
    STUDENT_EMAIL_INVALID("El correo del estudiante no tiene un formato válido."),
    STUDENT_MOBILE_NUMBER_REQUIRED("El número celular del estudiante es obligatorio."),
    STUDENT_ALREADY_EXISTS("Ya existe un estudiante con esa identificación."),
    STUDENT_NOT_FOUND("No se encontró el estudiante solicitado.");

    private final String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}