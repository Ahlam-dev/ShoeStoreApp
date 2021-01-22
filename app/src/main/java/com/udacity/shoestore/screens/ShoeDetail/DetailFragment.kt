package com.udacity.shoestore.screens.ShoeDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.Listing.ShoesListViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ShoesListViewModel by activityViewModels()
    private var myShoe: Shoe = Shoe("", 0.0, "", "")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.shoe = myShoe

        binding.SaveButton.setOnClickListener {
            binding.apply {
                shoe?.name = editTextShoeName.text.toString()
                shoe?.company = editTextShoeCompany.text.toString()
                shoe?.size = editTextShoeSize.text.toString().toDouble()
                shoe?.description = editTextShoeDescription.text.toString()

                viewModel.addShoe(shoe)
            }
            findNavController().navigate(R.id.action_detailFragment_to_listingFragment2)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_listingFragment2)
        }

        return binding.root
    }
}