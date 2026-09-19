import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nim = sc.nextLine().trim();

        // Validasi panjang NIM
        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        // Data kode program studi
        Map<String, String> prodi = new HashMap<>();

        prodi.put("11S", "Sarjana Informatika");
        prodi.put("12S", "Sarjana Sistem Informasi");
        prodi.put("13S", "Sarjana Teknik Elektro");
        prodi.put("21S", "Sarjana Manajemen Rekayasa");
        prodi.put("22S", "Sarjana Teknik Metalurgi");
        prodi.put("31S", "Sarjana Teknik Bioproses");
        prodi.put("32S", "Sarjana Bioteknologi");

        prodi.put("114", "Diploma 4 Teknologi Rekayasa Perangkat Lunak");
        prodi.put("113", "Diploma 3 Teknologi Informasi");
        prodi.put("133", "Diploma 3 Teknologi Komputer");

        // Ambil 3 karakter pertama sebagai kode prodi
        String prefix = nim.substring(0, 3);

        // Cek apakah kode prodi tersedia
        if (!prodi.containsKey(prefix)) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        // Ambil kode angkatan dari karakter ke-4 dan ke-5
        String kodeAngkatan = nim.substring(3, 5);

        // Contoh:
        // 18 -> 2018
        // 24 -> 2024
        // 99 -> 2099
        int angkatan = Integer.parseInt("20" + kodeAngkatan);

        // Ambil nomor urut dari 3 karakter terakhir
        int urutan = Integer.parseInt(nim.substring(5, 8));

        // Tampilkan hasil
        System.out.println("Informasi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + prodi.get(prefix));
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }
}