package com.example.anmp_studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.anmp_studentapp.R
import com.example.anmp_studentapp.databinding.FragmentStudentDetailBinding
import com.example.anmp_studentapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.refresh(StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId)

            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it
//            var student = it
//
//            binding.btnUpdate.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "Five seconds")
//                        MainActivity.showNotification(
//                            student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_24
//                        )
//                    }
//            }
//
//            if (it == null) {
//            }
//            else {
//                binding.txtID.setText(it.id)
//                binding.txtName.setText(it.name)
//                binding.txtBOD.setText(it.dob)
//                binding.txtPhone.setText(it.phone)
//                val picasso = Picasso.Builder(binding.root.context)
//                picasso.listener { picasso, uri, exception -> exception.printStackTrace()}
//                picasso.build().load(it.photoUrl).into(binding.imgStudent)
//            }
        })
    }

}