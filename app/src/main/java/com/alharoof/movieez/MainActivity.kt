package com.alharoof.movieez

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.pbLoading
import kotlinx.android.synthetic.main.activity_main.rvMovies

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    private val moviesAdapter = MoviesAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies.adapter = moviesAdapter
        rvMovies.itemAnimator = DefaultItemAnimator()

        loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_action_refresh) {
            loadMovies()
        }

        return true
    }

    private fun loadMovies() {
        Injector.YTS_API.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoadingProgress() }
            .subscribe(object : SingleObserver<GetMoviesResponse> {
                override fun onSuccess(response: GetMoviesResponse) {
                    var movies = emptyList<Movie>()

                    if (response.status.equals("ok", true)) {
                        movies = response.data?.movies ?: emptyList()
                    }

                    moviesAdapter.updateData(movies)
                    showMoviesList()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    moviesAdapter.updateData(emptyList())
                    showMoviesList()
                }
            })
    }

    private fun showMoviesList() {
        pbLoading.visibility = View.GONE
        rvMovies.visibility = View.VISIBLE
    }

    private fun showLoadingProgress() {
        pbLoading.visibility = View.VISIBLE
        rvMovies.visibility = View.GONE
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
