package vcmsa.ci.ryansmusicstore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val btnReturn = findViewById<Button>(R.id.btnHome)
        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val txtDisplay = findViewById<TextView>(R.id.txtResults)
        val btnAvgRating = findViewById<Button>(R.id.btnAverageRating)

        val title = intent.getStringArrayListExtra("title")
        val name = intent.getStringArrayListExtra("name")
        val rating = intent.getIntegerArrayListExtra("rating")
        val comments = intent.getStringArrayListExtra("comments")
        var count = intent.getIntExtra("count", 0)
        //Accessing the inputs from the previous page

        btnReturn.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity ::class.java)
            startActivity(intent)
        }
        btnDisplay.setOnClickListener {
            var result = "Songs in your playlist: $count\n\n"
            var i = 0
            while (i < count) {
                result += "• ${title?.get(i)} - ${name?.get(i)} - rating: ${rating?.get(i)} - ${comments?.get(i)}\n\n"
                //display songs in the playlist line by line
                i++
            }
            txtDisplay.text = result
        }
        btnAvgRating.setOnClickListener {
            btnAvgRating.setOnClickListener {
                if (rating != null) {
                    val total = rating.sum() //Adding all the ratings together
                    val average = total / rating.size //Total ratings divided by the number of ratings
                    txtDisplay.text = "The average song rating in your playlist is: $average"
                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}