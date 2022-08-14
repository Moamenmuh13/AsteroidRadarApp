package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val TAG = "MainFragment"
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider((this),
            MainViewModel.Factory(activity.application))[MainViewModel::class.java]
    }


    private var mainAdapter: MainAdapter = MainAdapter(MainAdapter.AsteroidListener {
        findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.asteroidRecycler.adapter = mainAdapter

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.asteroidList.observe(viewLifecycleOwner) { list ->
            list?.apply {
                mainAdapter.submitList(list)
                Log.d(TAG, "onViewCreated: ${list[1]}")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
