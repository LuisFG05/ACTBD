/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conection;

/**
 *
 * @author luill
 */
public class Metodos {
    public static class ForeignKeyConstraintException extends Exception {
        public ForeignKeyConstraintException() {
            super("No se puede borrar el registro porque hay registros conectados.");
        }
    }

    // Otros métodos pueden ir aquí
}


