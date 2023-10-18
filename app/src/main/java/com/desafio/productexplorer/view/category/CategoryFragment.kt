package com.desafio.productexplorer.view.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio.productexplorer.Constant
import com.desafio.productexplorer.R
import com.desafio.productexplorer.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(){
    private lateinit var binding: FragmentCategoryBinding
    val viewModel:CategoryViewModel by viewModels()
    val adapter:CategoryAdapter= CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)
        binding.rvCategory.layoutManager= LinearLayoutManager(context)
        binding.rvCategory.adapter=adapter
        adapter.onClickItem = {
            val bundle= Bundle()
            bundle.putInt(Constant.BUNDLE_CATEGORY_ID, it.id)
            bundle.putString(Constant.BUNDLE_CATEGORY_NAME, it.name)
            navController.navigate(R.id.action_categoryFragment_to_productsFragment, bundle)
        }
        viewModel.categoryLiveData.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

    }
}