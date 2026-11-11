public class PenyewaTidakDitemukanException extends RentalException {
    private static final long serialVersionUID = 1L;
    public PenyewaTidakDitemukanException(String id) {
        super("Penyewa dengan ID " + id + " tidak ditemukan");
    }
}
