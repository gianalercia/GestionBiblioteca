/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Logica;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author gian_
 */
public class Main {

    public static void main(String[] args) {
        
        // Creo el gestor LoanManager mandando como parametro el repositorio ArrayList 
        // Se aplica el principio de Sustitucion de Liskov ya que uso un ArrayListBookRepository donde se espera un BookRepository
        // Es decir que las subclases pueden sustituir a la clase padre sin alterar el comportamiento
        // LoanManager depende de la interfaz BookRepository y no de sus implementaciones concretas, cumple con el Principio de Inversion de Dependencias
        BookRepository repo = new ArrayListBookRepository(); 
        LoanManager gestor = new LoanManager(repo); 

        // Agregar libros
        repo.agregar(new Book("123", "Clean Code", "Robert Martin", 2008));
        repo.agregar(new Book("789", "Effective Java", "Joshua Bloch", 2017));
        repo.agregar(new Book("399", "It", "Stephen King", 1986));
        repo.agregar(new Book("400", "De ratones y hombres", "John Steinbeck", 1937));

        // Mostrar libros disponibles
        System.out.println("=== Libros Disponibles ===");
        for (Book libro : repo.listar()) {
            System.out.println(libro.getTitulo() + " (" + libro.getAñoPublicacion() + ") por " + libro.getAutor()
                    + " [ISBN: " + libro.getIsbn() + "] - "
                    + (libro.isDisponible() ? "Disponible" : "Prestado"));
        }
        System.out.println("Total: " + repo.listar().size() + " libros\n");

        // Pruebas de excepción
        try {
            gestor.prestar("123");
            gestor.prestar("123"); // Excepción: ya prestado
        } catch (LibraryException e) {
            System.out.println("Error al prestar libro: " + e.getMessage());
        }

        try {
            gestor.devolver("123");
            gestor.devolver("123"); // Excepción: ya devuelto
        } catch (LibraryException e) {
            System.out.println("Error al devolver libro: " + e.getMessage());
        }

        try {
            repo.agregar(new Book("123", "Libro duplicado", "Autor", 2024)); // Excepción: ISBN duplicado
        } catch (LibraryException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }

        try {
            gestor.prestar("000"); // Excepción: ISBN inexistente
        } catch (LibraryException e) {
            System.out.println("Error al prestar libro: " + e.getMessage());
        }
        
        System.out.println("\nLibros de Stephen King:");
        for (Book libro : repo.getBooksByAuthor("Stephen King")) {
            System.out.println(libro.getTitulo() + " (" + libro.getAñoPublicacion() + ")");
        }

    }
}

//Este comentario es para el trabajo práctico de Git - Gian Alercia
