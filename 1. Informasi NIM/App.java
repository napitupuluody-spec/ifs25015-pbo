import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static final Map<String, String> PROGRAM_STUDI = new LinkedHashMap<>();

    static {
        PROGRAM_STUDI.put("11S", "Sarjana Informatika");
        PROGRAM_STUDI.put("12S", "Sarjana Sistem Informasi");
        PROGRAM_STUDI.put("13S", "Sarjana Teknik Elektro");
        PROGRAM_STUDI.put("21S", "Sarjana Manajemen Rekayasa");
        PROGRAM_STUDI.put("22S", "Sarjana Teknik Metalurgi");
        PROGRAM_STUDI.put("31S", "Sarjana Teknik Bioproses");
        PROGRAM_STUDI.put("32S", "Sarjana Bioteknologi");
        PROGRAM_STUDI.put("114", "Diploma 4 Teknologi Rekayasa Perangkat Lunak");
        PROGRAM_STUDI.put("113", "Diploma 3 Teknologi Informasi");
        PROGRAM_STUDI.put("133", "Diploma 3 Teknologi Komputer");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String nim = sc.nextLine().trim();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefix = nim.substring(0, 3);
        if (!PROGRAM_STUDI.containsKey(prefix)) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        // Dua digit tahun dan tiga digit nomor urut harus berupa angka.
        if (!nim.substring(3).matches("\\d{5}")) {
            System.out.println("NIM tidak valid");
            return;
        }

        int angkatan = 2000 + Integer.parseInt(nim.substring(3, 5));
        int urutan = Integer.parseInt(nim.substring(5, 8));

        System.out.println("Informasi NIM " + nim + ":");
        System.out.println(">> Program Studi: " + PROGRAM_STUDI.get(prefix));
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }
}
