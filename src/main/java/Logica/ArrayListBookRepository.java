/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 *
 * @author gian_
 */

// Se implementa la interfaz BookRepository usando una coleccion dinamica para almacenar los libros
class ArrayListBookRepository implements BookRepository {
    private List<Book> libros = new ArrayList<>();

    // Para agregar libros se usa stream para convertirlos en una secuencia  de elementos sobre los cuales operar
    public void agregar(Book libro) { 
    // anyMatch verifica que el libro a agregar no sea duplicado
    if (libros.stream().anyMatch(l -> l.getIsbn().equals(libro.getIsbn()))) { 
        throw new LibraryException("ISBN duplicado"); // Lanza excepcion
    }
    // Si no hay duplicados se agrega el libro a la coleccion
    libros.add(libro);
}


    public void eliminar(String isbn) {
        // Usa el metodo buscar para dar con el libro y se almacena en la variable libro
        Book libro = buscar(isbn); 
        if (libro == null)
        // Si es null significa que el libro no fue encontrado, lanza excepcion    
            throw new LibraryException("Libro no encontrado");
        // Si el libro fue encontrado se elimina de la coleccion
        libros.remove(libro); 
    }

    public Book buscar(String isbn) {
        // Se busca el libro por numero y se valida que no sea null o vacio
        if (isbn == null || isbn.trim().isEmpty()) {
        throw new LibraryException("ISBN inválido: null o vacío");
    }   
        // Usa stream para realizarles un filtro por numero de identificacion y luego devuelve el primero que encuentra
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                //Si no encuentra ninguno lanza excepcion
                .orElseThrow(() -> new LibraryException("Libro con ISBN " + isbn + " no encontrado"));
    }
    
    // Crea una nueva array list para listar los libros sin comprometer la array list original
    public List<Book> listar() {
        return new ArrayList<>(libros);
    }
    
    // Filtra la lista para obtener los libros del autor que pasa por parametro
    
    @Override
    public List<Book> getBooksByAuthor(String author) {
    // Valida que el autor no sea null o vacio, si lo es lanza excepcion
        if (author == null || author.trim().isEmpty()) {
            throw new LibraryException("Autor inválido: null o vacío");
    }
    // Convierte la lista de libros en un stream para aplicarles el filtro que compara los nombres de autores con el nombre del autor que pasa por parametro
    return libros.stream()
            .filter(libro -> libro.getAutor().equalsIgnoreCase(author.trim()))
            //Recolecta los elementos filtrados en una nueva lista que sera la que se muestre
            .collect(Collectors.toList());
}
}

//Su responsabilidad es garantizar la persistencia de los libros