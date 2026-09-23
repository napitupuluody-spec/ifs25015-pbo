import java.util.Scanner;

public class App {
    private static final long MENIT_PER_HARI = 24L * 60L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String jamAwal = sc.nextLine().trim();

        if (!jamAwal.matches("\\d{2}:\\d{2}")) {
            System.out.println("Jam tidak valid");
            return;
        }

        String[] bagian = jamAwal.split(":", -1);
        int jam = Integer.parseInt(bagian[0]);
        int menit = Integer.parseInt(bagian[1]);
        if (jam > 23 || menit > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        long menitSaatIni = jam * 60L + menit;
        long totalGeser = 0L;
        long pergantianHari = 0L;

        while (sc.hasNextLine()) {
            String perintah = sc.nextLine().trim();
            if (perintah.equals("---")) break;
            if (perintah.isEmpty()) continue;

            if (!perintah.matches("[+-]\\d+")) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            try {
                long besarGeser = Long.parseLong(perintah.substring(1));
                long geser = perintah.charAt(0) == '+' ? besarGeser : -besarGeser;
                long menitSebelum = menitSaatIni;
                long menitAbsolut = Math.addExact(menitSebelum, geser);

                // Hitung jumlah batas tengah malam yang dilewati dalam perintah ini.
                pergantianHari += Math.abs(Math.floorDiv(menitAbsolut, MENIT_PER_HARI));
                menitSaatIni = Math.floorMod(menitAbsolut, MENIT_PER_HARI);
                totalGeser = Math.addExact(totalGeser, geser);
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
            } catch (ArithmeticException e) {
                System.out.println("Perintah tidak valid");
            }
        }

        int jamAkhir = (int) (menitSaatIni / 60);
        int menitAkhir = (int) (menitSaatIni % 60);
        String totalMenitStr = totalGeser > 0 ? "+" + totalGeser : String.valueOf(totalGeser);

        System.out.printf("Jam Awal: %02d:%02d%n", jam, menit);
        System.out.printf("Jam Akhir: %02d:%02d%n", jamAkhir, menitAkhir);
        System.out.println("Total Menit: " + totalMenitStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}
