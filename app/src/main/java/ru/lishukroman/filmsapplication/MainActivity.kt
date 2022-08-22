package ru.lishukroman.filmsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity(R.layout.activity_main), CallBackCommunicator {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MovieListFragment>(R.id.fragment_container_view_tag)
            }
        }
    }

    override fun passData(message: Movie) {
        val bundle = Bundle()
        bundle.putString("input_txt", message.title)

        Log.d(TAG, message.title.toString())
//        Toast.makeText(this,"MainActivity: $message",Toast.LENGTH_LONG).show()

        val transaction = this.supportFragmentManager.beginTransaction()
        val frag2 = DetailFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.fragment_container_view_tag, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}

interface CallBackCommunicator {
    fun passData(message: Movie)
}