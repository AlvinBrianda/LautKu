package com.uti.lautku

import Fragment.UploadFragment
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uti.lautku.adapter.JournalAdapter
import com.uti.lautku.model.JournalPaper


class MainActivity : AppCompatActivity(), UploadFragment.UploadFragmentListener {

    private lateinit var journalList: ListView
    private lateinit var journalAdapter: JournalAdapter
    private val papers = mutableListOf<JournalPaper>() // Contoh data jurnal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buat contoh data jurnal
        papers.add(JournalPaper("Judul 1", "Penulis 1", "Abstrak 1"))
        papers.add(JournalPaper("Judul 2", "Penulis 2", "Abstrak 2"))
        // Tambahkan data jurnal lainnya sesuai kebutuhan

        // Inisialisasi daftar jurnal dan adapter
        journalList = findViewById(R.id.journal_list)
        journalAdapter = JournalAdapter(this, papers)
        journalList.adapter = journalAdapter

        // Atur aksi ketika item di daftar jurnal diklik
        journalList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedJournalPaper = papers[position]
            intent.putExtra("paper_title", selectedJournalPaper.title)
            startActivity(intent)
        }
    }

    // Fungsi untuk menangani klik tombol unggah
    fun onUploadButtonClick() {
        // Buka UploadFragment saat tombol unggah diklik
        val uploadFragment = UploadFragment()
        supportFragmentManager.beginTransaction()
            .replace(androidx.fragment.R.id.fragment_container_view_tag,uploadFragment)
            .addToBackStack(null)
            .commit()
    }

    // Implementasi fungsi dari UploadFragmentListener

        override fun onUploadFragmentInteraction() {
            // Tambahkan logika untuk menangani interaksi dengan UploadFragment jika diperlukan
            // Misalnya, tampilkan pesan sukses atau lakukan aksi lain setelah unggah selesai
            // Contoh:
            Toast.makeText(this, "Unggah berhasil", Toast.LENGTH_SHORT).show()

            // Jika Anda ingin melakukan sesuatu setelah unggah berhasil, tambahkan kode di sini
            // Misalnya, muat ulang daftar jurnal atau tampilkan tampilan lain
            // Contoh:
            supportFragmentManager.popBackStack() // Kembali ke tampilan sebelumnya setelah unggah selesai
        }


}
