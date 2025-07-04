package exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(int id) {
        System.out.println("elemento "+id+" non è stato trovato");
    }
}
