import java.io.Serializable;

public class Transaksi implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Penyewa penyewa;       // Association ke Penyewa
    private final Kendaraan kendaraan;   // Association ke Kendaraan
    private final int jumlah;
    private final double totalBiaya;

    public Transaksi(Penyewa penyewa, Kendaraan kendaraan, int jumlah) {
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.jumlah = jumlah;
        this.totalBiaya = kendaraan.hitungBiaya(jumlah);
    }

    public Penyewa getPenyewa() { return penyewa; }
    public Kendaraan getKendaraan() { return kendaraan; }
    public int getJumlah() { return jumlah; }
    public double getTotalBiaya() { return totalBiaya; }
}
