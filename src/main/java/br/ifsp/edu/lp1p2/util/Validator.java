package br.ifsp.edu.lp1p2.util;


public class Validator {


    public static String passwordValidator(String password){
        if (!password.matches("^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$")){
            return "A senha deve conter 1 número (0-9), 1 letra maiúscula, 1 letra minúscula, 1 número não alfanumérico, 8-16 caracteres sem espaço";
        }
        return null;
    }

    public static String moneyValidator(String money){
        if (!money.matches("^[0-9]\\d*([\\,\\.]\\d{2})?$")) return "Seguinte formato: 00.00";
        return null;
    }

    public static String nameValidator(String name){
        if (!name.matches("^[a-zA-Z ]{6,18}+$")){
            return "Nome Inválido deve ter entre 6 a 18 digitos não numéricos";
        }
        return null;
    }

    public static String emailValidator(String email){
        if (!email.matches("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")) {
            return email +" é inválido";
        }
        return null;
    }


    public static String telefoneValidator(String telefone){
        if (telefone.length() != 11) {
            return "Lembre-se; Telefone com onze numeros ex: 11987654321";
        }
        return null;
    }

    
}
