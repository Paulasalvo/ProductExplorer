package com.desafio.productexplorer.view.productdetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.desafio.productexplorer.Constant
import com.desafio.productexplorer.R
import com.desafio.productexplorer.databinding.FragmentProductDetailBinding
import com.desafio.productexplorer.model.data.Product
import com.desafio.productexplorer.model.data.ProductDetail
import com.desafio.productexplorer.util.UtilFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProductDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(Constant.BUNDLE_PRODUCT, Product::class.java)
        } else {
            arguments?.getParcelable(Constant.BUNDLE_PRODUCT)
        }
        if (product!=null){
            setupView(product)
            viewModel.productDetail(product.id)
            viewModel.productDetailLiveData.observe(viewLifecycleOwner){
                binding.ratingBar.rating = it.rating.toFloat()
                binding.editTextComentario.setText(it.comment)
            }
        }

        binding.btnGuardar.setOnClickListener {
            if (product!= null){
                val productDetail = ProductDetail(
                    id = product.id,
                    title = product.title,
                    description = product.description,
                    price = product.price,
                    creationAt = product.creationAt,
                    rating = binding.ratingBar.rating.toInt(),
                    comment = binding.editTextComentario.text.toString()
                )
                viewModel.saveProductDetail(productDetail)
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.save_data),
                    Toast.LENGTH_SHORT
                ).show()
                navController.popBackStack()
            }

        }
    }

    private fun setupView(product: Product){

        binding.tvCreationAt.text = UtilFormat.getDateFormatted(product?.creationAt.orEmpty())
        binding.tvDescripcionProducto.text = product?.description
        binding.tvPrecioProducto.text = "$ " + product?.price.toString()
        binding.titleProduct.text = product?.title

        Glide
            .with(binding.root.context)
            .load(product.images.getOrNull(0))
            .error(R.drawable.baseline_cancel_24)
            .into(binding.ivImagenProducto1)
        Glide
            .with(binding.root.context)
            .load(product.images.getOrNull(1))
            .into(binding.ivImagenProducto2)
        Glide
            .with(binding.root.context)
            .load(product.images.getOrNull(2))
            .into(binding.ivImagenProducto3)

        binding.ratingBar.stepSize = 1.0f

    }

}