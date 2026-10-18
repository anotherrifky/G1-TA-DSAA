// Group-1 
// ANANDA EVRIANSYAH - 2902825321 
// DAMANHURI - 2902809160 
// M. MUHLIS IRAWAN - 2902814936 
// NASYWA ANDIRA KHOIRUNNISA - 2902818240 
// RIFKY ARDIANSYAH -  2902830012 
 
// Tugas Kelompok ke-3 
// COSC6025036

class Lagu {
    // Enkapsulasi: semua atribut dibuat private
    private String judul;
    private String artis;
    private double durasi; // dalam menit

    // Constructor untuk menginisialisasi data lagu
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter: mengambil nilai atribut
    public String getJudul()  { return judul; }
    public String getArtis()  { return artis; }
    public double getDurasi() { return durasi; }

    // Setter: mengubah nilai atribut
    public void setJudul(String judul)    { this.judul = judul; }
    public void setArtis(String artis)    { this.artis = artis; }
    public void setDurasi(double durasi)  { this.durasi = durasi; }

    // Menampilkan informasi lengkap lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + durasi + " menit");
    }
}

public class PlaylistRekursif {
    Lagu[] playlist;

    // ---------------------------------------------------------
    // Tujuan: menghitung total durasi seluruh lagu.
    // Base case: n <= 0, mengembalikan 0.
    // Recursive case: durasi lagu terakhir + total lagu sebelumnya.
    // Kompleksitas waktu: O(n).
    // ---------------------------------------------------------
    static double totalDurasi(Lagu[] list, int n) {
        if (n <= 0) {
            return 0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    // ---------------------------------------------------------
    // Tujuan: menampilkan lagu dari akhir ke awal.
    // Dipanggil dengan index = list.length - 1.
    // Base case: index < 0, berhenti.
    // Recursive case: cetak lagu[index], lalu panggil index - 1.
    // Kompleksitas waktu: O(n).
    // ---------------------------------------------------------
    static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0) {
            return;
        }
        System.out.println((index + 1) + ". " + list[index].getJudul());
        tampilkanMundur(list, index - 1);
    }

    // ---------------------------------------------------------
    // Tujuan: mencari durasi lagu terpanjang.
    // Dipanggil dengan index = 0.
    // Base case: index == list.length - 1, kembalikan durasi lagu terakhir.
    // Recursive case: max(durasi lagu[index], hasil index + 1).
    // Kompleksitas waktu: O(n).
    // ---------------------------------------------------------
    static double cariDurasiTerpanjang(Lagu[] list, int index) {
        if (index == list.length - 1) {
            return list[index].getDurasi();
        }
        return Math.max(list[index].getDurasi(), cariDurasiTerpanjang(list, index + 1));
    }

    // Fungsi bantu: sama seperti di atas, tetapi mengembalikan objek Lagu
    // agar judul dan artis lagu terpanjang bisa ditampilkan.
    static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (index == list.length - 1) {
            return list[index];
        }
        Lagu sisa = cariLaguTerpanjang(list, index + 1);
        return (list[index].getDurasi() >= sisa.getDurasi()) ? list[index] : sisa;
    }

    // Tiap fungsi dijalankan berulang lalu dirata-rata (hasil dalam ms),
    // karena satu kali eksekusi terlalu cepat untuk diukur dengan akurat.
    static double waktuTotalDurasi(Lagu[] list) {
        int ulang = 10000;
        long mulai = System.nanoTime();
        for (int i = 0; i < ulang; i++) {
            totalDurasi(list, list.length);
        }
        return (System.nanoTime() - mulai) / 1_000_000.0 / ulang;
    }

    static double waktuTampilkanMundur(Lagu[] list) {
        java.io.PrintStream asli = System.out;
        // Output disembunyikan saat pengukuran agar waktu cetak tidak ikut terhitung
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            public void write(int b) { }
        }));
        int ulang = 1000;
        long mulai = System.nanoTime();
        for (int i = 0; i < ulang; i++) {
            tampilkanMundur(list, list.length - 1);
        }
        long selesai = System.nanoTime();
        System.setOut(asli);
        return (selesai - mulai) / 1_000_000.0 / ulang;
    }

    static double waktuCariTerpanjang(Lagu[] list) {
        int ulang = 10000;
        long mulai = System.nanoTime();
        for (int i = 0; i < ulang; i++) {
            cariDurasiTerpanjang(list, 0);
        }
        return (System.nanoTime() - mulai) / 1_000_000.0 / ulang;
    }

    // ===================== Integrasi & Demo =====================
    public static void main(String[] args) {
        Lagu[] semua = {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 4.52),
            new Lagu("Yellow", "Coldplay", 4.29),
            new Lagu("Viva La Vida", "Coldplay", 4.02),
            new Lagu("Bohemian Rhapsody", "Queen", 5.55),
            new Lagu("Hello", "Adele", 4.55),
            new Lagu("Someone Like You", "Adele", 4.45),
            new Lagu("Photograph", "Ed Sheeran", 4.18),
            new Lagu("Paradise", "Coldplay", 4.38)
        };

        // ---------- Demo dengan 5 lagu --------------
        Lagu[] lagu = java.util.Arrays.copyOf(semua, 5);
        Lagu terpanjang = cariLaguTerpanjang(lagu, 0);

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu     : " + lagu.length);
        System.out.printf("Total durasi    : %.2f menit%n", totalDurasi(lagu, lagu.length));
        System.out.printf("Lagu terpanjang : \"%s\" - %s (%.2f menit)%n",
                terpanjang.getJudul(), terpanjang.getArtis(), cariDurasiTerpanjang(lagu, 0));

        System.out.println();
        System.out.println("Daftar lagu (ditampilkan terbalik):");
        tampilkanMundur(lagu, lagu.length - 1);

        System.out.println();
        System.out.printf("Execution Time (totalDurasi)         : %.6f ms%n", waktuTotalDurasi(lagu));
        System.out.printf("Execution Time (tampilkanMundur)     : %.6f ms%n", waktuTampilkanMundur(lagu));
        System.out.printf("Execution Time (cariDurasiTerpanjang): %.6f ms%n", waktuCariTerpanjang(lagu));

        // ---------- Eksperimen n = 3, 5, 10  ----------
        System.out.println();
        System.out.println("=== TABEL WAKTU EKSEKUSI (ms) ===");
        System.out.printf("%-6s | %-14s | %-18s | %-22s%n",
                "n", "totalDurasi()", "tampilkanMundur()", "cariDurasiTerpanjang()");
        System.out.println("-------+----------------+--------------------+-----------------------");
        int[] jumlahUji = {3, 5, 10};
        for (int n : jumlahUji) {
            Lagu[] list = java.util.Arrays.copyOf(semua, n);
            System.out.printf("%-6d | %-14.6f | %-18.6f | %-22.6f%n", n,
                    waktuTotalDurasi(list), waktuTampilkanMundur(list), waktuCariTerpanjang(list));
        }
    }
}
