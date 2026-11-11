// Induk semua Custom Exception di sistem rental
public class RentalException extends Exception {
    private static final long serialVersionUID = 1L;
    public RentalException(String message) { super(message); }
}
