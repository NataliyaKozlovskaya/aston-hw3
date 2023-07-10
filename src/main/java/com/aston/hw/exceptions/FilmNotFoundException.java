package com.aston.hw.exceptions;

public class FilmNotFoundException extends RuntimeException{
        public FilmNotFoundException(String message) {
            super(message);
        }
}
