// Group-1 
// ANANDA EVRIANSYAH - 2902825321 
// DAMANHURI - 2902809160 
// M. MUHLIS IRAWAN - 2902814936 
// NASYWA ANDIRA KHOIRUNNISA - 2902818240 
// RIFKY ARDIANSYAH -  2902830012 
 
// Tugas Kelompok ke-2 
// Week 4 

// Kelas: COSC6025036
 
import java.util.Scanner; 
  
/** 
 * Class Lagu (dipakai ulang dari Tugas Kelompok 1). 
 * Menyimpan data satu buah lagu: judul, artis, dan durasi (menit). 
 * Menerapkan enkapsulasi: atribut private, diakses lewat getter/setter. 
 */ 
class Lagu { 
    private String judul; 
    private String artis; 
    private double durasi; // dalam menit, contoh 4.23 
  
    public Lagu(String judul, String artis, double durasi) { 
        this.judul = judul; 
        this.artis = artis; 
        this.durasi = durasi; 
    } 
  
    // Getter - dipakai untuk searching (judul) dan sorting (durasi) 
    public String getJudul()  { return judul; } 
    public String getArtis()  { return artis; } 
    public double getDurasi() { return durasi; } 
  
    // Setter - untuk merubah data lagu yang diperlukan 
    public void setJudul(String judul) { 
        this.judul = judul;  
    } 
    
    public void setArtis(String artis) {  
        this.artis = artis; 
    } 
    
    public void setDurasi(double durasi) { 
        this.durasi = durasi;  
    } 
  
    /** Menampilkan judul, artis, dan durasi lagu. */ 
    public void tampilkanInfo() { 
        System.out.println(judul + " - " + artis + " (" + durasi + " menit)"); 
    } 
} 

/** 
 * Class utama: mengelola playlist dengan ARRAY STATIS berkapasitas 10 lagu. 
 */ 
public class PlaylistArray { 
  
    private static final int MAKS = 10;          // kapasitas maksimum array 
    private static Lagu[] playlist = new Lagu[MAKS]; 
    private static int jumlahLagu = 0;           // jumlah elemen yang terisi saat ini 
    private static Scanner input = new Scanner(System.in); 
  
    /* -------------------------------------------------------- 
     * 1. TRAVERSAL - menelusuri array dari indeks 0 sampai jumlahLagu-1 
     *    Kompleksitas: O(n), karena setiap elemen dikunjungi tepat 1 kali. 
     * -------------------------------------------------------- */ 
    public static void tampilkanSemuaLagu() { 
        System.out.println("\n--- DAFTAR LAGU ---"); 
        
        if (jumlahLagu == 0) { 
            System.out.println("Playlist masih kosong."); 
            return; 
        } 
        
        for (int i = 0; i < jumlahLagu; i++) {   // ini proses traversal 
            System.out.print((i + 1) + ". "); 
            playlist[i].tampilkanInfo(); 
        } 
        
        System.out.println("Total lagu: " + jumlahLagu + "/" + MAKS); 
    } 
  
    /* -------------------------------------------------------- 
     * 2. INSERTION - menyisipkan lagu baru di posisi paling belakang 
     *    Wajib mengecek apakah array sudah penuh (overflow). 
     *    Kompleksitas: O(1) untuk penyisipan di akhir. 
     * -------------------------------------------------------- */ 
    public static void tambahLagu() { 
        System.out.println("\n--- TAMBAH LAGU ---"); 
  
        // Pengecekan kapasitas: array statis tidak bisa membesar sendiri 
        if (jumlahLagu >= MAKS) { 
            System.out.println("Playlist PENUH! Maksimal " + MAKS + " lagu."); 
            return; 
        } 
  
        System.out.print("Masukkan judul lagu     : "); 
        String judul = input.nextLine(); 
        
        System.out.print("Masukkan artis          : "); 
        String artis = input.nextLine(); 
        
        System.out.print("Masukkan durasi (menit) : "); 
        double durasi; 
        
        try { 
            durasi = Double.parseDouble(input.nextLine()); 
        } catch (NumberFormatException e) { 
            System.out.println("Durasi tidak valid. Lagu batal ditambahkan."); 
            return; 
        } 
  
        // Simpan objek pada slot kosong pertama, lalu naikkan counter 
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi); 
        jumlahLagu++; 
  
        System.out.println("Lagu berhasil ditambahkan!"); 
        tampilkanSemuaLagu(); 
    } 
  
    /* -------------------------------------------------------- 
     * 3. SEARCHING - LINEAR SEARCH berdasarkan judul 
     *    Membandingkan judul yang dicari dengan setiap elemen array 
     *    sampai ketemu, atau sampai array habis. 
     *    Kompleksitas: O(n) kasus terburuk, O(1) kasus terbaik. 
     *    Mengembalikan indeks lagu, atau -1 jika tidak ditemukan. 
     * -------------------------------------------------------- */ 
    public static int cariIndeks(String judul) { 
        for (int i = 0; i < jumlahLagu; i++) { 
            if (playlist[i].getJudul().equalsIgnoreCase(judul)) { 
                return i;      // ketemu, pencarian langsung dihentikan 
            } 
        } 
        
        return -1;             // tidak ditemukan 
    } 
  
    public static void cariLagu() { 
        System.out.println("\n--- CARI LAGU ---"); 
        
        if (jumlahLagu == 0) { 
            System.out.println("Playlist masih kosong."); 
            return; 
        } 
        
        System.out.print("Masukkan judul yang dicari: "); 
        String judul = input.nextLine(); 
  
        int posisi = cariIndeks(judul); 
        
        if (posisi == -1) { 
            System.out.println("Lagu \"" + judul + "\" tidak ditemukan."); 
        } else { 
            System.out.println("Lagu ditemukan pada indeks " + posisi 
                             + " (urutan ke-" + (posisi + 1) + "):"); 
            playlist[posisi].tampilkanInfo(); 
        } 
    } 
  
    /* -------------------------------------------------------- 
     * 4. DELETION - menghapus lagu berdasarkan judul 
     *    Langkah: cari posisinya (linear search), lalu GESER semua 
     *    elemen setelahnya satu langkah ke kiri agar data tetap rapat 
     *    (tidak ada lubang/null di tengah array). 
     *    Kompleksitas: O(n) karena pencarian + pergeseran elemen. 
     * -------------------------------------------------------- */ 
    public static void hapusLagu() { 
        System.out.println("\n--- HAPUS LAGU ---"); 
        
        if (jumlahLagu == 0) { 
            System.out.println("Playlist masih kosong, tidak ada yang dihapus."); 
            return; 
        } 
        
        System.out.print("Masukkan judul yang akan dihapus: "); 
        String judul = input.nextLine(); 
  
        int posisi = cariIndeks(judul); 
        
        if (posisi == -1) { 
            System.out.println("Lagu \"" + judul + "\" tidak ditemukan."); 
            return; 
        } 
  
        // Proses shifting: elemen i+1 dipindah ke posisi i 
        for (int i = posisi; i < jumlahLagu - 1; i++) { 
            playlist[i] = playlist[i + 1]; 
        } 
        
        playlist[jumlahLagu - 1] = null; // kosongkan slot terakhir 
        jumlahLagu--; 
  
        System.out.println("Lagu \"" + judul + "\" berhasil dihapus!"); 
        tampilkanSemuaLagu(); 
    } 
  
    /* -------------------------------------------------------- 
     * 5. SORTING - SELECTION SORT berdasarkan durasi 
     *    Mengurutkan lagu dari durasi terkecil ke terbesar (ascending). 
     *    Algoritma mencari durasi terkecil pada bagian array yang 
     *    belum terurut, kemudian menukarnya dengan posisi saat ini. 
     *    Kompleksitas: O(n²), karena menggunakan dua perulangan. 
     * -------------------------------------------------------- */ 
    public static void urutkanLaguBerdasarkanDurasi() { 
        System.out.println("\n--- SORTING BERDASARKAN DURASI ---"); 
        
        if (jumlahLagu == 0) { 
            System.out.println("Playlist masih kosong."); 
            return; 
        } 
        
        // Menampilkan data sebelum proses sorting 
        System.out.println("\n=== SEBELUM SORTING ==="); 
        tampilkanSemuaLagu(); 
  
        // Selection Sort 
      
        for (int i = 0; i < jumlahLagu - 1; i++) { 
            
            // Menentukan indeks lagu dengan durasi terkecil 
            int indeksMinimum = i; 
            
            for (int j = i + 1; j < jumlahLagu; j++) { 
                if (playlist[j].getDurasi() < playlist[indeksMinimum].getDurasi()) { 
                    indeksMinimum = j; 
                } 
            } 
  
            // Menukar lagu jika ditemukan durasi yang lebih kecil 
            if (indeksMinimum != i) { 
                Lagu temp = playlist[i]; 
                playlist[i] = playlist[indeksMinimum]; 
                playlist[indeksMinimum] = temp; 
            } 
        } 
  
        // Menampilkan data setelah proses sorting 
        System.out.println("\n=== SESUDAH SORTING (ASCENDING) ==="); 
        tampilkanSemuaLagu(); 
    } 
  
    /** Data awal agar program langsung bisa diuji saat dijalankan. */ 
    public static void isiDataAwal() { 
        playlist[jumlahLagu++] = new Lagu("Perfect", "Ed Sheeran", 4.23); 
        playlist[jumlahLagu++] = new Lagu("Shivers", "Ed Sheeran", 3.50); 
        playlist[jumlahLagu++] = new Lagu("kembang perawan", "gita gutawa", 3.00); 
        playlist[jumlahLagu++] = new Lagu("rapsodi", "jkt", 2.90); 
        playlist[jumlahLagu++] = new Lagu("pesawat kertas", "jkt", 2.50); 
        playlist[jumlahLagu++] = new Lagu("catalist", "werd genius", 2.80); 
    } 
  
    /* -------------------------------------------------------- 
     * MENU INTERAKTIF BERBASIS KONSOL 
     * -------------------------------------------------------- */ 
    public static void main(String[] args) { 
        isiDataAwal(); 
        int pilihan = 0; 
  
        do { 
            System.out.println("\n=== MENU PLAYLIST MUSIK ==="); 
            System.out.println("1. Tampilkan semua lagu"); 
            System.out.println("2. Tambah lagu baru"); 
            System.out.println("3. Hapus lagu berdasarkan judul"); 
            System.out.println("4. Cari lagu berdasarkan judul"); 
            System.out.println("5. Urutkan berdasarkan durasi"); 
            System.out.println("6. Keluar"); 
            System.out.print("Pilih menu: "); 
  
            try { 
                pilihan = Integer.parseInt(input.nextLine()); 
            } catch (NumberFormatException e) { 
                pilihan = 0; // input bukan angka -> dianggap tidak valid 
            } 
  
            switch (pilihan) { 
                case 1: 
                    tampilkanSemuaLagu(); 
                    break; 
                    
                case 2: 
                    tambahLagu(); 
                    break; 
                    
                case 3: 
                    hapusLagu(); 
                    break; 
                    
                case 4: 
                    cariLagu(); 
                    break; 
                    
                case 5: 
                    urutkanLaguBerdasarkanDurasi(); 
                    break; 
                    
                case 6: 
                    System.out.println("Terima kasih, program selesai."); 
                    break; 
                    
                default: 
                    System.out.println("Pilihan tidak valid! Masukkan angka 1-6."); 
            } 
        } while (pilihan != 6); 
  
        input.close(); 
    } 
}
