# 📖 Vocab Note

Aplikasi pencatat dan pengingat kosakata yang dirancang untuk membantu pengguna belajar bahasa asing dengan lebih efisien. Vocab Note mempermudah proses pencatatan kosakata baru dan menyediakan fitur kuis interaktif untuk me-review secara berkala.

# ✨ Fitur Utama

Vocab Note saat ini menyediakan fungsionalitas sebagai berikut:

1. Daftar Kosakata: Menampilkan daftar semua kosakata yang telah ditambahkan.

    - Review Cepat: Menampilkan kata dan terjemahan secara langsung

    - Detail Kosakata: melihat informasi lebih lanjut tentang kosakata tersebut.

2. Tambah Kosakata: Form untuk menambahkan kata, terjemahan, dan informasi lainnya.

3. Edit Kosakata: Memungkinkan pengguna untuk memodifikasi detail kosakata yang sudah ada.

4. Kuis Review: Latihan sederhana untuk menguji ingatan.

    - Menampilkan pertanyaan (kata) dan pilihan jawaban.

    - Pengguna cukup memilih jawaban, dan aplikasi akan menampilkan jawaban yang benar.

# 🛠️ Teknologi

Aplikasi Vocab Note dikembangkan secara native untuk Android menggunakan:

    Bahasa Pemrograman: Kotlin

    UI Toolkit: Jetpack Compose (Pengembangan UI modern dan deklaratif).

    Penyimpanan Data Lokal: Room Persistent Library.

        Digunakan sebagai local database untuk menyimpan data kosakata, mendukung operasi CRUD (Create, Read, Update, Delete) yang efisien.

    Navigasi: Jetpack Compose Navigation.

        Mengimplementasikan NavController dan NavHost sesuai best practice dari dokumentasi resmi untuk pengelolaan state dan transisi antar layar.

    State Management: ViewModel, Observable State, Remember, Recomposition.

        Digunakan untuk memisahkan logika bisnis dari UI dan mengakses database. ( Catatan: Implementasi ViewModel ini adalah hasil referensi, dan saya masih terus mendalami prinsip arsitektur MVVM/MVI yang optimal dalam Jetpack Compose.)

# 🚧 Kekurangan Aplikasi dan Rencana Pengembangan

Aplikasi ini masih dalam tahap pengembangan awal dan memiliki keterbatasan seperti  berikut:

Kekurangan Saat Ini:

1. Aplikasi belum sepenuhnya stabil dan rentan terhadap bug. Terutama pada tahap Kuis, terkadang dapat terjadi force close.

2. Feedback Pengguna: Belum ada pop-up messages atau Snackbar setelah operasi penting (seperti Tambah atau Edit).