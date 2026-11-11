public abstract class Kendaraan extends Entitas {
    private static final long serialVersionUID = 1L;
    private int stok;

    public Kendaraan(String id, String nama, int stok) {
        super(id, nama);
        this.stok = stok;
    }

    public int getStok() { return stok; }

    public void kurangiStok(int jumlah) throws StokTidakCukupException {
        if (jumlah > stok) {
            throw new StokTidakCukupException(getId(), stok, jumlah);
        }
        stok -= jumlah;
    }

    public abstract double getTarifPerUnit();

    public double hitungBiaya(int jumlah) {
        return getTarifPerUnit() * jumlah;
    }
}
