/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author gian_
 */

// Se definen los atributos de la clase Book
public class Book {
    private String isbn;
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private boolean disponible;
    // Santiago Civalero
    // Agregar atributo editorial

    // Constructor de la clase Book usando los setters
    public Book(String isbn, String titulo, String autor, int añoPublicacion) {
        setIsbn(isbn); // Se reutilizan los setters con validación
        setTitulo(titulo);
        setAutor(autor);
        setAñoPublicacion(añoPublicacion);
        this.disponible = true;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Setters con validaciones
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN no puede ser nulo o vacío.");
        }
        this.isbn = isbn.trim();
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título no puede ser nulo o vacío.");
        }
        this.titulo = titulo.trim();
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor no puede ser nulo o vacío.");
        }
        this.autor = autor.trim();
    }

    public void setAñoPublicacion(int añoPublicacion) {
        if (añoPublicacion < 0) {
            throw new IllegalArgumentException("El año de publicación no puede ser negativo.");
        }
        this.añoPublicacion = añoPublicacion;
    }

    // Métodos para cambiar el estado de disponibilidad
    public void prestar() {
        this.disponible = false;
    }

    public void devolver() {
        this.disponible = true;
    }
}
