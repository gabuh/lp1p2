package br.ifsp.edu.lp1p2.util;

import br.ifsp.edu.lp1p2.model.UsuarioEntity;

public class Usuarioinfo {
    public static Long id;
    public static String name;
    public static String email;

    public static void setUser(UsuarioEntity usuario) {
        id = usuario.getId();
        name = usuario.getNomeUsuario();
        email = usuario.getEmailUsuario();
    }

    public static void clearInfo() {
        id = null;
        name = null;
        email = null;
    }

}
