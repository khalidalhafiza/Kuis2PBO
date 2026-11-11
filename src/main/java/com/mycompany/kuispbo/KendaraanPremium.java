public class KendaraanPremium extends Kendaraan {
    private static final long serialVersionUID = 1L;

    public KendaraanPremium(String id, String nama, int stok) {
        super(id, nama, stok);
    }

    @Override
    public double getTarifPerUnit() {
        return 500000;
    }
}
