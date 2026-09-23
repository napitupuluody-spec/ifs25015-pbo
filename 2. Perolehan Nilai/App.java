import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class App {
    private static final String FORMAT_ERROR =
            "Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai";
    private static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};
    private static final Map<String, String> NAMA = new LinkedHashMap<>();

    static {
        NAMA.put("PA", "Partisipatif");
        NAMA.put("T", "Tugas");
        NAMA.put("K", "Kuis");
        NAMA.put("P", "Proyek");
        NAMA.put("UTS", "UTS");
        NAMA.put("UAS", "UAS");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> bobotAkhir = new LinkedHashMap<>();
        long totalBobot = 0L;

        for (String simbol : SIMBOL) {
            if (!sc.hasNextLine()) {
                System.out.println(FORMAT_ERROR);
                return;
            }
            try {
                int bobot = Integer.parseInt(sc.nextLine().trim());
                if (bobot < 0) {
                    System.out.println(FORMAT_ERROR);
                    return;
                }
                bobotAkhir.put(simbol, bobot);
                totalBobot += bobot;
            } catch (NumberFormatException e) {
                System.out.println(FORMAT_ERROR);
                return;
            }
        }

        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        Map<String, Long> totalBobotRecord = new LinkedHashMap<>();
        Map<String, Long> totalNilaiRecord = new LinkedHashMap<>();
        for (String simbol : SIMBOL) {
            totalBobotRecord.put(simbol, 0L);
            totalNilaiRecord.put(simbol, 0L);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;

            String[] data = line.split("\\|", -1);
            if (data.length != 3) {
                System.out.println(FORMAT_ERROR);
                continue;
            }

            String simbol = data[0].trim();
            if (!NAMA.containsKey(simbol)) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            try {
                int bobot = Integer.parseInt(data[1].trim());
                int nilai = Integer.parseInt(data[2].trim());
                if (bobot <= 0 || nilai < 0 || nilai > bobot) {
                    System.out.println(FORMAT_ERROR);
                    continue;
                }
                totalBobotRecord.put(simbol, totalBobotRecord.get(simbol) + bobot);
                totalNilaiRecord.put(simbol, totalNilaiRecord.get(simbol) + nilai);
            } catch (NumberFormatException e) {
                System.out.println(FORMAT_ERROR);
            }
        }

        double nilaiAkhir = 0.0;
        double[] kontribusi = new double[SIMBOL.length];
        long[] persentase = new long[SIMBOL.length];

        for (int i = 0; i < SIMBOL.length; i++) {
            String simbol = SIMBOL[i];
            long totalB = totalBobotRecord.get(simbol);
            long totalN = totalNilaiRecord.get(simbol);
            persentase[i] = totalB == 0 ? 0 : Math.round(totalN * 100.0 / totalB);
            kontribusi[i] = totalB == 0 ? 0.0 : (totalN * 1.0 / totalB) * bobotAkhir.get(simbol);
            nilaiAkhir += kontribusi[i];
        }

        System.out.println("Perolehan Nilai:");
        for (int i = 0; i < SIMBOL.length; i++) {
            String simbol = SIMBOL[i];
            System.out.printf(Locale.US, ">> %s: %d/100 (%.2f/%d)%n",
                    NAMA.get(simbol), persentase[i], kontribusi[i], bobotAkhir.get(simbol));
        }

        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;
        System.out.println();
        System.out.printf(Locale.US, ">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + grade(nilaiAkhir));
    }

    private static String grade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}
