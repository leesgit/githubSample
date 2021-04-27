package com.lee.githubsample.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.githubsample.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.lee.githubsample.databinding.ActivityMainBinding


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    private lateinit var githubAdapter: GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        init()
        observe()
    }

    private fun init() {
        githubAdapter = GithubAdapter()

        binding.rvRepository.let {
            it.adapter = githubAdapter
            it.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observe() {
        viewModel.loadRepositories.observe(this, Observer {
            githubAdapter.submitList(viewModel.repositories)
            githubAdapter.notifyDataSetChanged()
        })
    }
}