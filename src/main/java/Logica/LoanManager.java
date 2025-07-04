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

// Esta clase se encarga del prestamo y devolución de los libros
// Su atributo es el repositorio donde estan almacenados los libros, al tratarse de la interfaz BookRepository pueden ser ambos
// tipos (Array o ArrayList). Por lo tanto al depender de una abstracción y no de una implementación concreta se respeta el principio de Inversion
// de dependencias y cumple con el principio Single Responsability.

class LoanManager {
    private BookRepository repositorio;

    public LoanManager(BookRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void prestar(String isbn) {
        Book libro = repositorio.buscar(isbn); // Se busca el libro por su número y se almacena en la variable local libro
        if (libro == null) // Si el libro es null entonces no se encontró, arroja una excepción
            throw new LibraryException("Libro no encontrado");
        if (!libro.isDisponible()) // Si el libro no esta disponible es porque ya esta prestado, lanza una excepción
            throw new LibraryException("El libro ya está prestado");
        libro.prestar(); // Caso contrario se presta y se cambia su disponibilidad a False
    }

    public void devolver(String isbn) {
        Book libro = repositorio.buscar(isbn); // Se busca el libro por su número y se almacena en la variable local libro
        if (libro == null) // Si el libro es null entonces no se encontró, arroja una excepción
            throw new LibraryException("Libro no encontrado");
            
        if (libro.isDisponible()) { // Si el libro se encontró y esta disponible entonces no es necesaria devolverlo, lanza una excepción
        throw new LibraryException("El libro ya está disponible, no es necesario devolverlo");
    }
        libro.devolver(); // Si no se cumplen las condiciones el libro se devuelve y cambia su disponibilidad a True
    } 
}

// Su responsabilidad es encapsular la logica de prestamos y devoluciones