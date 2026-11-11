public class StokTidakCukupException extends RentalException {
    private static final long serialVersionUID = 1L;
    public StokTidakCukupException(String kode, int sisa, int diminta) {
        super("Stok " + kode + " tidak cukup (sisa " + sisa + ", diminta " + diminta + ")");
    }
}
