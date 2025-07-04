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

// Gestiona el almacenamiento de libros implementando la interfaz BookRepository y usando un arreglo book en lugar de una lista dinamica

class ArrayBookRepository implements BookRepository {
    private static final int CAPACIDAD_MAXIMA = 10;
    private Book[] libros = new Book[CAPACIDAD_MAXIMA];
    private int cantidad = 0; //Contador de libros en el arreglo

    @Override
    public void agregar(Book libro) {
        if (cantidad >= CAPACIDAD_MAXIMA) { // Si la cantidad de libros supera o iguala la capacidad maxima se lanza excepcion
            throw new LibraryException("Capacidad máxima alcanzada. No se pueden agregar más libros."); 
        }

        // Validar ISBN duplicado
        for (int i = 0; i < cantidad; i++) { // Si ya hay un libro con el numero de identificacion ingresado se lanza excepcion
            if (libros[i].getIsbn().equals(libro.getIsbn())) {
                throw new LibraryException("ISBN duplicado: " + libro.getIsbn());
            }
        }

        libros[cantidad++] = libro; // Si pasa ambas verificaciones se agrega el libro y se suma uno en la cantidad de libros, el cual es el indice
    }

    @Override
    public void eliminar(String isbn) {
        int index = buscarIndice(isbn); // Busca el indice del libro usando un metodo auxiliar definido mas abajo
        if (index == -1) {
            throw new LibraryException("Libro con ISBN " + isbn + " no encontrado"); // Si no existe lanza excepción
        }

        // Mediante este ciclo se desplazan a la izquierda todos los elementos que siguen al indice que es eliminado, ordenan el arreglo para dejar un hueco justo en el último espacio del arreglo
        for (int i = index; i < cantidad - 1; i++) {
            libros[i] = libros[i + 1];
        }
        libros[--cantidad] = null; // Se decrementa la cantidad y ademas se establece null en el ultimo hueco del arreglo ahora disponible
    }

    @Override
    public Book buscar(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) { // Valida que el numero buscado no sea null o vacio, si lo es lanza excepcion
            throw new LibraryException("ISBN inválido");
        }

        for (int i = 0; i < cantidad; i++) { // Recorre el arreglo hasta encontrar el libro correspondiente al numero buscado y lo retorna
            if (libros[i].getIsbn().equals(isbn)) {
                return libros[i];
            }
        }
        // Si no encuentra el libro lanza una excepcion
        throw new LibraryException("Libro con ISBN " + isbn + " no encontrado");
    }

     
    @Override
    public List<Book> listar() {
        List<Book> lista = new ArrayList<>(); // Crea nueva array list
        for (int i = 0; i < cantidad; i++) { // Recorre el arreglo y añade los libros a la array list
            lista.add(libros[i]);
        }
        return lista; // Retorna la array list la cual es una copia para proteger la integridad de la original
    }
    
    
    @Override
    public List<Book> getBooksByAuthor(String author) { // Se busca una lista de libros filtrados por el autor 
    if (author == null || author.trim().isEmpty()) { // Si el autor es null o vacio se arroja una excepcion
        throw new LibraryException("El autor no puede ser nulo o vacío.");
    }

    List<Book> resultado = new ArrayList<>(); // Se crea la lista de los libros por autor
    for (int i = 0; i < cantidad; i++) { // Se recorre el arreglo 
        if (libros[i].getAutor().equalsIgnoreCase(author.trim())) { // Se buscan los libros que coincidan con el autor
            resultado.add(libros[i]); // Se añaden a la lista
        }
    }
    return resultado; // Retorna la lista
    
}


    // Método auxiliar para buscar indice
    // Recorre el arreglo hasta devolver la posicion del libro en el arreglo y en caso de no encontrarlo retorna -1
    private int buscarIndice(String isbn) {
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getIsbn().equals(isbn)) { 
                return i;
            }
        }
        return -1;
    }
}

//Su responsabilidad es garantizar la persistencia de los libros