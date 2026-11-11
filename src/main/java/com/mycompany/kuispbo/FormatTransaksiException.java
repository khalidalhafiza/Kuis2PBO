public class FormatTransaksiException extends RentalException {
    private static final long serialVersionUID = 1L;
    public FormatTransaksiException(String baris) {
        super("Format baris tidak valid: \"" + baris + "\"");
    }
}
