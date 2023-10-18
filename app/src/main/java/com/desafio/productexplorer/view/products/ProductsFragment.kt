package com.desafio.productexplorer.view.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio.productexplorer.Constant
import com.desafio.productexplorer.R
import com.desafio.productexplorer.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: Fragment() {
    private lateinit var binding: FragmentProductsBinding
    val viewModel: ProductsViewModel by viewModels()
    val adapter: ProductsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProductsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.rvProducts.layoutManager= LinearLayoutManager(context)
        binding.rvProducts.adapter=adapter

        adapter.onClickItem = {
            val bundle=Bundle()
            bundle.putParcelable(Constant.BUNDLE_PRODUCT, it)
            navController.navigate(R.id.action_productsFragment_to_productDetailFragment, bundle)
        }

        val categoryName= arguments?.getString(Constant.BUNDLE_CATEGORY_NAME)
        binding.tvCategoryTitle.text=categoryName.toString()
        val categoryId = arguments?.getInt(Constant.BUNDLE_CATEGORY_ID)
        if (categoryId != null) {
            viewModel.productsLiveData(categoryId).observe(viewLifecycleOwner){
                adapter.updateList(it)
            }
        }

    }
}