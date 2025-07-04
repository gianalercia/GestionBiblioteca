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

//Interfaz que definira las operaciones que realizara cualquier repositorio de libros
//Respeta el principio Open/Close : El código debe estar abierto a la extensión, pero cerrado a la modificación.
//Se pueden crear nuevas clases a partir de esta interfaz, extender el codigo sin modificarlo

interface BookRepository {
    void agregar(Book libro);
    void eliminar(String isbn);
    Book buscar(String isbn);
    List<Book> listar();
    List<Book> getBooksByAuthor(String author);
}
