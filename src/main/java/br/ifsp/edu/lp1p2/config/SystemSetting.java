package br.ifsp.edu.lp1p2.config;

import javafx.stage.Screen;

public class SystemSetting {
    private static final Screen screen = Screen.getPrimary();
        public static double getScreenWidth(){
            return screen.getBounds().getWidth();
        }
        public static double getScreenHeight(){
            return screen.getBounds().getHeight();
        }
}
