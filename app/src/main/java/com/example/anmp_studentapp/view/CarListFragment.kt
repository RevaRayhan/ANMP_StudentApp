package com.example.anmp_studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp_studentapp.R
import com.example.anmp_studentapp.databinding.CarListItemBinding
import com.example.anmp_studentapp.databinding.FragmentCarListBinding
import com.example.anmp_studentapp.viewmodel.CarViewModel
import com.example.anmp_studentapp.viewmodel.ListViewModel

class CarListFragment : Fragment() {

    private lateinit var viewModel:CarViewModel
    private lateinit var binding:FragmentCarListBinding
    private val carListAdapter = CarListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = carListAdapter

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

        swipe.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            swipe.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.carsLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            }
            else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError?.visibility = View.VISIBLE
            }
            else {
                binding.txtError?.visibility = View.GONE
            }
        })
    }
}