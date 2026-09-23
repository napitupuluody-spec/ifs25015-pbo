import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Long> frekuensi = new LinkedHashMap<>();
        boolean adaData = false;
        int tertinggi = 0;
        int terendah = 0;

        while (sc.hasNextLine()) {
            String baris = sc.nextLine().trim();
            if (baris.equals("---")) break;
            if (baris.isEmpty()) continue;

            try {
                int nilai = Integer.parseInt(baris);
                if (!adaData) {
                    tertinggi = nilai;
                    terendah = nilai;
                    adaData = true;
                } else {
                    if (nilai > tertinggi) tertinggi = nilai;
                    if (nilai < terendah) terendah = nilai;
                }
                frekuensi.put(nilai, frekuensi.getOrDefault(nilai, 0L) + 1L);
            } catch (NumberFormatException ignored) {
                // Abaikan baris yang bukan bilangan bulat.
            }
        }

        if (!adaData) return;

        int terbanyakNilai = 0;
        long terbanyakFrekuensi = Long.MIN_VALUE;
        int tersedikitNilai = 0;
        long tersedikitFrekuensi = Long.MAX_VALUE;
        int jumlahTertinggiNilai = 0;
        long jumlahTertinggi = Long.MIN_VALUE;
        int jumlahTerendahNilai = 0;
        long jumlahTerendah = Long.MAX_VALUE;

        for (Map.Entry<Integer, Long> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            long frek = entry.getValue();
            long hasil = (long) nilai * frek;

            if (frek > terbanyakFrekuensi || (frek == terbanyakFrekuensi && nilai > terbanyakNilai)) {
                terbanyakFrekuensi = frek;
                terbanyakNilai = nilai;
            }
            if (frek < tersedikitFrekuensi || (frek == tersedikitFrekuensi && nilai < tersedikitNilai)) {
                tersedikitFrekuensi = frek;
                tersedikitNilai = nilai;
            }
            if (hasil > jumlahTertinggi || (hasil == jumlahTertinggi && nilai > jumlahTertinggiNilai)) {
                jumlahTertinggi = hasil;
                jumlahTertinggiNilai = nilai;
            }
            if (hasil < jumlahTerendah || (hasil == jumlahTerendah && nilai < jumlahTerendahNilai)) {
                jumlahTerendah = hasil;
                jumlahTerendahNilai = nilai;
            }
        }

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakNilai + " (" + terbanyakFrekuensi + "x)");
        System.out.println("Tersedikit: " + tersedikitNilai + " (" + tersedikitFrekuensi + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiNilai + " * "
                + frekuensi.get(jumlahTertinggiNilai) + " = " + jumlahTertinggi);
        System.out.println("Jumlah Terendah: " + jumlahTerendahNilai + " * "
                + frekuensi.get(jumlahTerendahNilai) + " = " + jumlahTerendah);
    }
}
