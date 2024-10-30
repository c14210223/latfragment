package lat.c14210223.latfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class fSatu : Fragment() {

    private lateinit var scoreTextView: TextView
    private lateinit var buttons: List<Button>
    private var angkaTebakan: Int = 0
    private var score: Int = 50 // Modal awal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_f_satu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi TextView dan Button
        scoreTextView = view.findViewById(R.id.scoreTextView)
        buttons = listOf(
            view.findViewById(R.id.button1),
            view.findViewById(R.id.button2),
            view.findViewById(R.id.button3),
            view.findViewById(R.id.button4),
            view.findViewById(R.id.button5),
            view.findViewById(R.id.button6),
            view.findViewById(R.id.button7),
            view.findViewById(R.id.button8),
            view.findViewById(R.id.button9)
        )

        updateScore()

        // Mulai permainan
        startGame()
        // Menambahkan listener pada setiap tombol
        buttons.forEach { button ->
            button.setOnClickListener {
                val selectedNumber = button.text.toString().toInt()
                checkAnswer(selectedNumber)
            }
        }
    }

    // Fungsi angka Random
    private fun startGame() {
        angkaTebakan = Random.nextInt(1, 5) // Batas awal default 1-5
        val angkaList = mutableListOf(angkaTebakan, angkaTebakan) // Angka muncul 2 kali

        // Isi sisa list dengan angka acak lainnya
        while (angkaList.size < 9) {
            angkaList.add(Random.nextInt(1, 5))
        }

        angkaList.shuffle() // Mengacak urutan angka

        // Menampilkan angka pada tombol
        buttons.forEachIndexed { index, button ->
            button.text = angkaList[index].toString()
        }
    }

    // Fungsi untuk memeriksa jawaban
    private fun checkAnswer(selectedNumber: Int) {
        if (selectedNumber == angkaTebakan) {
            score += 10
        } else {
            score -= 5
        }
        updateScore()
        startGame() // Mengacak angka untuk ronde berikutnya
    }

    // Fungsi untuk memperbarui tampilan skor
    private fun updateScore() {
        scoreTextView.text = "Score: $score"
    }
}
