package br.ifsp.edu.lp1p2.util;


public class Validator {


    public static String passwordValidator(String password){
        String passwordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
        if (!password.matches(passwordRegex)){
            return "A senha deve conter 1 número (0-9), 1 letra maiúscula, 1 letra minúscula, 1 número não alfanumérico, 8-16 caracteres sem espaço";
        }
        return null;
    }

    public static String nameValidator(String name){
        String nameRegex = "^[a-zA-Z ]{6,18}+$";
        if (!name.matches(nameRegex)){
            return "Nome Inválido deve ter entre 6 a 18 digitos não numéricos";
        }
        return null;
    }

    public static String emailValidator(String email){
        String emailRegex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
        if (!email.matches(emailRegex)) {
            return email +" é inválido";
        }
        return null;
    }

    
    
    
}
