package ru.lishukroman.filmsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(R.layout.activity_main), CallBackCommunicator {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<MovieListFragment>(R.id.fragment_container_view_tag)
//            }
            val movieListFragment = MovieListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view_tag, movieListFragment)
                .commit()
        }
    }

    override fun passData(message: Movie) {
        val bundle = Bundle()
        bundle.putString("input_txt", message.title)

        Log.d(TAG, message.title)
//        Toast.makeText(this,"MainActivity: $message",Toast.LENGTH_LONG).show()

        val transaction = this.supportFragmentManager.beginTransaction()
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        transaction.replace(R.id.fragment_container_view_tag, detailFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}

interface CallBackCommunicator {
    fun passData(message: Movie)
}