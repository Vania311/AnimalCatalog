package com.animalcatalog.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.animalcatalog.data.retrofit.RetrofitCreator
import com.animalcatalog.databinding.CustomviewRequestFragmentBinding
import com.animalcatalog.presentation.basefragment.BaseFragment
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.presenters.CustomViewRequestFragmentPresenter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomViewRequestFragment: BaseFragment<CustomviewRequestFragmentBinding>() {

    private var presenter: MainContract.CustomViewRequestPresenterInterface? = null

    private val retrofitCreator by lazy { RetrofitCreator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CustomViewRequestFragmentPresenter()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CustomviewRequestFragmentBinding = CustomviewRequestFragmentBinding.inflate(inflater, container,false)

    @OptIn(DelicateCoroutinesApi::class)
    override fun CustomviewRequestFragmentBinding.onBindView(savedInstanceState: Bundle?) {
        presenter?.attachView(this@CustomViewRequestFragment)

        requestBt.setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                requestTextview.text = retrofitCreator.getCurrentData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }
}