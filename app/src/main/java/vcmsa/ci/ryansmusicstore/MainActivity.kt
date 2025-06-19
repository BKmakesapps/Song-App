package vcmsa.ci.ryansmusicstore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnNextScreen  = findViewById<Button>(R.id.btnNext)
        val Title  = findViewById<EditText>(R.id.edtTitle)
        val ArtistName = findViewById<EditText>(R.id.edtArtistName)
        val Rating = findViewById<EditText>(R.id.edtRating)
        val Comments  = findViewById<EditText>(R.id.edtComments)
        val Feedback  = findViewById<TextView>(R.id.txtFeedback)


        val title = ArrayList<String>()     //declaring and initialising parallel arrays
        val name = ArrayList<String>()
        val comments = ArrayList<String>()
        val rating = ArrayList<Int>()
        var count = 0
        btnAdd.setOnClickListener {
            btnAdd.setOnClickListener {
                val txtRating = Rating.text.toString()
                if (Title != null && ArtistName != null && Rating != null && Comments != null)
                //checking that each field has a value thats been entered
                     {
                    title.add(count, Title.text.toString())
                    name.add(count, ArtistName.text.toString())
                    comments.add(count, Comments.text.toString())
                    rating.add(count, txtRating.toIntOrNull() ?: 0)
                    count++
                    Feedback.text = "Song added to playlist!"

                    Title.text.clear()      //Clearing all inputs
                    Rating.text.clear()
                    ArtistName.text.clear()
                    Comments.text.clear()
                } else {
                    Feedback.text = "Please fill in all the fields!"
                }
            }
        }
        btnExit.setOnClickListener {
            finishAffinity()
        }
        btnNextScreen.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2 ::class.java)
            intent.putStringArrayListExtra("title", title)
            intent.putStringArrayListExtra("name", name)
            intent.putIntegerArrayListExtra("rating", rating)
            intent.putStringArrayListExtra("comments", comments)
            intent.putExtra("count",count )
            startActivity(intent)
        } //Moving the inputs to the next page





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}