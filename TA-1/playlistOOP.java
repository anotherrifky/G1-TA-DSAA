// Group-1 
// ANANDA EVRIANSYAH - 2902825321 
// DAMANHURI - 2902809160 
// M. MUHLIS IRAWAN - 2902814936 
// NASYWA ANDIRA KHOIRUNNISA - 2902818240 
// RIFKY ARDIANSYAH -  2902830012 
 
// Tugas Kelompok ke-1 

// COSC6025036

import java.util.*;

class Lagu {
    // Enkapsulasi: semua atribut dibuat private
    private String judul;
    private String artis;
    private double durasi; // dalam menit

    // Constructor untuk menginisialisasi data lagu
    public Lagu (String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter: mengambil nilai atribut
    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public double getDurasi() {
        return durasi;
    }

    // Setter: mengubah nilai atribut
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method untuk menampilkan informasi lengkap lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + durasi + " menit");
    }
}

// ================= Class User (Soal 2 - Parent Class) =================
// Class abstrak yang menjadi induk dari Admin dan Member (inheritance).
// Menyimpan atribut umum yang dimiliki setiap jenis pengguna.
abstract class User {
    protected String nama;

    // Constructor: menyimpan nama user saat objek dibuat
    public User(String nama) {
        this.nama = nama;
    }

    // Getter: mengambil nama user
    public String getNama() {
        return nama;
    }

    // Method abstract: setiap child class WAJIB mengimplementasikan versinya sendiri.
    // Inilah yang menjadi contoh polymorphism, karena isi/output method ini
    // akan berbeda-beda tergantung objeknya Admin atau Member.
    public abstract void tampilkanAkses();
}


// ================= Class Admin (Soal 2 - Child Class) =================
// Mewarisi User. Admin memiliki kemampuan khusus: menambahkan lagu ke playlist.
class Admin extends User {

    // Constructor: memanggil constructor parent (User) untuk set nama
    public Admin(String nama) {
        super(nama);
    }

    // Fungsi utama Admin: menambahkan objek Lagu baru ke dalam array playlist.
    // jumlahLagu dibungkus dalam array int[] agar nilainya bisa berubah
    // di luar method ini (karena Java bersifat pass-by-value untuk primitif).
    public void tambahLagu(Lagu[] playlist, int[] jumlahLagu, Lagu laguBaru) {
        // Cek dulu apakah kapasitas array masih cukup
        if (jumlahLagu[0] < playlist.length) {
            playlist[jumlahLagu[0]] = laguBaru; // simpan lagu baru di posisi kosong berikutnya
            jumlahLagu[0]++;                    // tambah counter jumlah lagu
            System.out.println("Lagu '" + laguBaru.getJudul() + "' berhasil ditambahkan oleh Admin " + nama);
        } else {
            System.out.println("Playlist sudah penuh, tidak bisa menambahkan lagu lagi.");
        }
    }

    // Implementasi polymorphism: versi tampilkanAkses() khusus untuk Admin
    @Override
    public void tampilkanAkses() {
        System.out.println(nama + " login sebagai Admin -> dapat menambahkan lagu ke playlist.");
    }
}


// ================= Class Member (Soal 2 - Child Class) =================
// Mewarisi User. Member memiliki kemampuan khusus: melihat & mencari lagu.
class Member extends User {

    // Constructor: memanggil constructor parent (User) untuk set nama
    public Member(String nama) {
        super(nama);
    }

    // Fungsi utama Member: menampilkan seluruh lagu yang ada di playlist
    // dengan cara melakukan looping sepanjang jumlahLagu yang aktif.
    public void lihatDaftarLagu(Lagu[] playlist, int jumlahLagu) {
        System.out.println("=== Daftar Lagu ===");
        for (int i = 0; i < jumlahLagu; i++) {
            playlist[i].tampilkanInfo();
            System.out.println("-----------------------");
        }
    }

    // Fungsi utama Member: mencari lagu berdasarkan judul secara linear search.
    // Pencarian tidak case-sensitive (huruf besar/kecil dianggap sama).
    public void cariLaguByJudul(Lagu[] playlist, int jumlahLagu, String judulDicari) {
        boolean ditemukan = false;
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judulDicari)) {
                System.out.println("Lagu ditemukan:");
                playlist[i].tampilkanInfo();
                ditemukan = true;
                break; // hentikan pencarian begitu ditemukan
            }
        }
        if (!ditemukan) {
            System.out.println("Lagu dengan judul '" + judulDicari + "' tidak ditemukan.");
        }
    }

    // Implementasi polymorphism: versi tampilkanAkses() khusus untuk Member
    @Override
    public void tampilkanAkses() {
        System.out.println(nama + " login sebagai Member -> dapat melihat & mencari lagu.");
    }
}


// ================= Class Utama =================
// WAJIB bernama PlaylistOOP karena disimpan dalam file PlaylistOOP.java
public class PlaylistOOP {
    public static void main(String[] args) {
        // Menyiapkan array untuk menyimpan kumpulan objek Lagu (kapasitas 10)
        Lagu[] playlist = new Lagu[10];
        int[] jumlahLagu = {0}; // counter jumlah lagu yang sudah tersimpan

        // Membuat objek Admin dan Member sebagai contoh penggunaan sistem
        Admin admin = new Admin("Kevin");
        Member member = new Member("Sinta");

        // Menampilkan hak akses masing-masing user (polymorphism beraksi di sini)
        admin.tampilkanAkses();
        member.tampilkanAkses();
        System.out.println();

        // Admin menambahkan beberapa lagu ke playlist
        admin.tambahLagu(playlist, jumlahLagu, new Lagu("Judul A", "Artis A", 3.5));
        admin.tambahLagu(playlist, jumlahLagu, new Lagu("Judul B", "Artis B", 4.2));
        admin.tambahLagu(playlist, jumlahLagu, new Lagu("Judul C", "Artis C", 5.0));
        System.out.println();

        // Member melihat seluruh daftar lagu yang sudah ditambahkan Admin
        member.lihatDaftarLagu(playlist, jumlahLagu[0]);
        System.out.println();

        // Member mencari lagu berdasarkan judul (kasus ditemukan dan tidak ditemukan)
        member.cariLaguByJudul(playlist, jumlahLagu[0], "Judul A");
        member.cariLaguByJudul(playlist, jumlahLagu[0], "Judul Z");
        System.out.println();

        // Menghitung rata-rata durasi lagu dalam playlist
        double totalDurasi = 0;
        for (int i = 0; i < jumlahLagu[0]; i++) {
            totalDurasi += playlist[i].getDurasi();
        }
        double rataRata = (jumlahLagu[0] > 0) ? totalDurasi / jumlahLagu[0] : 0;
        System.out.println("Rata-rata durasi lagu dalam playlist: " + rataRata + " menit");
    }
}
