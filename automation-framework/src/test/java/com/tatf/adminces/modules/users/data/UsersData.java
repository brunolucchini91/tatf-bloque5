package com.tatf.adminces.modules.users.data;

public class UsersData {
    public static final String TITLE =
            "USUARIOS REGISTRADOS EN EL SISTEMA";
    public static final String DELETED_MESSAGE = "Usuario eliminado.";

    public static String deleteQuestion(String email) {
        return "¿Eliminar usuario: " + email + "?";
    }
}
