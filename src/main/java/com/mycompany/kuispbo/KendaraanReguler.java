public class KendaraanReguler extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanReguler(String id, String nama, int stok) {
        super(id, nama, stok);
    }

    @Override
    public double getTarifPerUnit() {
        return 150000;
    }
}
