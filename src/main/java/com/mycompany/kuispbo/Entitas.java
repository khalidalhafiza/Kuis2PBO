import java.io.Serializable;

// Superclass dasar: Penyewa dan Kendaraan sama-sama punya ID & nama
public abstract class Entitas implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String nama;

    public Entitas(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
}
