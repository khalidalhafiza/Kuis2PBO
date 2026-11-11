import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SistemRental implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String namaRental;
    private final Map<String, Penyewa> daftarPenyewa = new HashMap<>();
    private final Map<String, Kendaraan> daftarKendaraan = new HashMap<>();
    private final ArrayList<Transaksi> logTransaksi = new ArrayList<>();

    public SistemRental(String namaRental) {
        this.namaRental = namaRental;
    }

    public void tambahPenyewa(String id, String nama) {
        daftarPenyewa.put(id, new Penyewa(id, nama));
    }

    public void tambahKendaraan(String kode, String nama, int stok) {
        Kendaraan k;
        if (nama.contains("Premium")) {
            k = new KendaraanPremium(kode, nama, stok);
        } else if (nama.contains("Reguler")) {
            k = new KendaraanReguler(kode, nama, stok);
        } else {
            k = new KendaraanEkonomis(kode, nama, stok);
        }
        daftarKendaraan.put(kode, k);
    }

    public void prosesTransaksi(String idPenyewa, String kodeKendaraan, int jumlah)
            throws RentalException {
        Penyewa p = daftarPenyewa.get(idPenyewa);
        if (p == null) throw new PenyewaTidakDitemukanException(idPenyewa);

        Kendaraan k = daftarKendaraan.get(kodeKendaraan);
        if (k == null) throw new KendaraanTidakDitemukanException(kodeKendaraan);

        k.kurangiStok(jumlah);
        logTransaksi.add(new Transaksi(p, k, jumlah));
    }

    public void jalankanTransaksiDariFile(String namaFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            int noBaris = 0;
            while ((baris = br.readLine()) != null) {
                noBaris++;
                baris = baris.trim();
                if (baris.isEmpty()) continue;

                try {
                    String[] bagian = baris.split("\\.");
                    if (bagian.length != 3) throw new FormatTransaksiException(baris);

                    String id = ambilNilai(bagian[0], "ID", baris);
                    String kd = ambilNilai(bagian[1], "KD", baris);
                    int jml = Integer.parseInt(ambilNilai(bagian[2], "JML", baris));

                    prosesTransaksi(id, kd, jml);
                    System.out.println("[OK] Baris " + noBaris + ": " + id + " sewa "
                            + jml + " x " + kd);
                } catch (NumberFormatException e) {
                    System.out.println("[GAGAL] Baris " + noBaris + ": JML bukan angka");
                } catch (RentalException e) {
                    System.out.println("[GAGAL] Baris " + noBaris + ": " + e.getMessage());
                }
            }
        }
    }

    private String ambilNilai(String pasangan, String keyHarapan, String barisAsli)
            throws FormatTransaksiException {
        String[] kv = pasangan.split(">");
        if (kv.length != 2 || !kv[0].equals(keyHarapan)) {
            throw new FormatTransaksiException(barisAsli);
        }
        return kv[1];
    }

    public void simpanLaporanAkhir() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("data_master.bak"))) {
            oos.writeObject(this);
            System.out.println("[INFO] Serialisasi berhasil -> data_master.bak");
        } catch (IOException e) {
            System.out.println("[ERROR] Serialisasi gagal: " + e.getMessage());
        }

        try (FileWriter fw = new FileWriter("laporan.txt")) {
            for (int i = 0; i < logTransaksi.size(); i++) {
                Transaksi t = logTransaksi.get(i);
                fw.write(t.getPenyewa().getId() + "," + t.getKendaraan().getId()
                        + "," + t.getJumlah() + "\n");
            }
            System.out.println("[INFO] Laporan CSV berhasil -> laporan.txt");
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis laporan.txt: " + e.getMessage());
        }
    }
}
