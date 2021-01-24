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
import com.udacity.shoestore.databinding.NewitemBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_listing.view.*


class ListingFragment : Fragment() {
    private lateinit var ListingBinding: FragmentListingBinding
    private lateinit var NewItemBinding:NewitemBinding
    private val viewModel: ShoesListViewModel by activityViewModels()
    private lateinit var mylayout: LinearLayout

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        ListingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        ListingBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listingFragment_to_detailFragment)
        }
        mylayout = ListingBinding.shoeItem
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newList ->
            if (newList.size == 0) {
                Toast.makeText(context, "No Shoe data exist", Toast.LENGTH_SHORT).show()
            } else {
                newList.forEach {
                    NewItemBinding=NewitemBinding.inflate(inflater, container, false)

                    NewItemBinding.apply {
                        shoesizeData.text=it.size.toString()
                        companynameData.text=it.company
                        shoenameData.text=it.name
                        descriptionData.text = it.description
                        mylayout.addView(shoeDataCardview)

                    }
                }
            }
        })
        setHasOptionsMenu(true)

        return ListingBinding.root

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