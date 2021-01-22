package com.udacity.shoestore.screens.Listing

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_listing.view.*


class ListingFragment : Fragment() {
    private lateinit var binding: FragmentListingBinding
    private val viewModel: ShoesListViewModel by activityViewModels()
    private lateinit var mylayout: LinearLayout
    private lateinit var mynewView: View
    private lateinit var name: TextView
    private lateinit var company: TextView
    private lateinit var size: TextView
    private lateinit var description: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listingFragment_to_detailFragment)
        }
        mylayout = binding.shoeItem
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            if (newList.size == 0) {
                Toast.makeText(context, "No Shoe data exist", Toast.LENGTH_SHORT).show()
            } else {
                newList.forEach {
                    mynewView = getLayoutInflater().inflate(R.layout.newitem, null, false)
                    name = mynewView.findViewById(R.id.shoename_data)
                    company = mynewView.findViewById(R.id.companyname_data)
                    size = mynewView.findViewById(R.id.shoesize_data)
                    description = mynewView.findViewById(R.id.description_data)
                    company.text = it.company
                    name.text = it.name
                    size.text = it.size.toString()
                    description.text = it.description
                    mylayout.addView(mynewView)
                }
            }
        })
        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }

}