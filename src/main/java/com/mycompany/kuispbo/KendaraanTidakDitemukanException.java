public class KendaraanTidakDitemukanException extends RentalException {
    private static final long serialVersionUID = 1L;
    public KendaraanTidakDitemukanException(String kode) {
        super("Kendaraan dengan kode " + kode + " tidak ditemukan");
    }
}
