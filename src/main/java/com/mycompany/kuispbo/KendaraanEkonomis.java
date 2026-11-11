public class KendaraanEkonomis extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanEkonomis(String id, String nama, int stok) {
        super(id, nama, stok);
    }

    @Override
    public double getTarifPerUnit() {
        return 75000;
    }
}
