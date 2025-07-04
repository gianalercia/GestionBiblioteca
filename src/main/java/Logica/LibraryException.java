/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author gian_
 */


// Creo la excepcion personalizada LibraryException que hereda de RuntimeException
// Es de tipo no check y la utilicé para no realizar tantos bloques try catch

class LibraryException extends RuntimeException {
    public LibraryException(String mensaje) {
        super(mensaje);
    }
}

// Su responsabilidad es manejar errores del dominio