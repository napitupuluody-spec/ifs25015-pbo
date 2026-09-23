# Praktikum Java Dasar

Project berisi lima studi kasus. Setiap folder memiliki `App.java`, `input.txt`, dan `test-cases/` masing-masing.

## Cara menjalankan

Buka terminal pada folder studi kasus yang ingin diuji, lalu jalankan:

```bash
javac App.java
java App < input.txt
```

Setiap studi kasus dikompilasi secara terpisah karena semuanya menggunakan nama kelas `App`.

## Catatan

- File `.class` tidak disertakan karena merupakan hasil kompilasi dan sudah di-ignore oleh `.gitignore`.
- Folder `test-cases/` dipertahankan sebagai tempat file TC-01.tc sampai TC-20.tc.
- Pastikan output dan aturan validasi dibandingkan kembali dengan spesifikasi resmi/TC Delcom sebelum submit. File TC resmi tidak tersedia di arsip yang diperiksa, jadi hasil nilai Delcom tidak dapat dijamin.
