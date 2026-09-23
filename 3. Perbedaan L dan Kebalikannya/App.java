import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        final int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Data tidak valid");
            return;
        }
        if (n <= 0) {
            System.out.println("Data tidak valid");
            return;
        }

        long[][] matriks = new long[n][n];
        for (int i = 0; i < n; i++) {
            if (!sc.hasNextLine()) {
                System.out.println("Data tidak valid");
                return;
            }
            String baris = sc.nextLine().trim();
            String[] angka = baris.isEmpty() ? new String[0] : baris.split("\\s+");
            if (angka.length != n) {
                System.out.println("Data tidak valid");
                return;
            }
            for (int j = 0; j < n; j++) {
                try {
                    matriks[i][j] = Long.parseLong(angka[j]);
                } catch (NumberFormatException e) {
                    System.out.println("Data tidak valid");
                    return;
                }
            }
        }

        if (n < 3) {
            long tengah = 0;
            if (n == 1) {
                tengah = matriks[0][0];
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) tengah += matriks[i][j];
                }
            }
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + tengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + tengah);
            return;
        }

        long nilaiL = 0;
        long nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += matriks[i][0];
            nilaiKebalikanL += matriks[i][n - 1];
        }
        for (int j = 1; j < n - 1; j++) {
            nilaiL += matriks[n - 1][j];
            nilaiKebalikanL += matriks[0][j];
        }

        long nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matriks[n / 2][n / 2];
        } else {
            int a = n / 2 - 1;
            int b = n / 2;
            nilaiTengah = matriks[a][a] + matriks[a][b]
                    + matriks[b][a] + matriks[b][b];
        }

        long perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        long dominan = perbedaan == 0 ? nilaiTengah : Math.max(nilaiL, nilaiKebalikanL);
        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}
