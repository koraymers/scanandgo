package com.example.k_app.fragmets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.k_app.MainActivity
import com.example.k_app.R
import com.fevziomurtekin.payview.Payview
import com.fevziomurtekin.payview.data.PayModel


class CardFragment: Fragment(){

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val contentView = inflater.inflate(R.layout.fragment_creditcard, container, false)
    val payview = contentView.findViewById<Payview>(R.id.pView)

    payview.setPayOnclickListener {
      (activity as MainActivity).openGenerateFragment()
    }

    return contentView
  }
}