package com.plusmobileapps.bottomnavtransition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.plusmobileapps.bottomnavtransition.R

const val ANDROID_JETPACK_IMAGE_URL = "https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png"

class DashboardFragment : Fragment() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        imageView = root.findViewById(R.id.dashboard_imageview)
        root.findViewById<Button>(R.id.button).setOnClickListener {
            onDetailButtonClicked()
        }
        root.findViewById<ImageView>(R.id.dashboard_imageview)?.let { imageView ->
            Glide.with(requireContext())
                .load(ANDROID_JETPACK_IMAGE_URL)
                .placeholder(R.drawable.ic_android_robot)
                .into(imageView)
        }
        return root
    }

    private fun onDetailButtonClicked() {
        val bottomNavFragment = fragmentManager?.findFragmentByTag(BottomNavFragment.TAG)
        fragmentManager?.beginTransaction()?.let { fragmentTransaction ->
            fragmentTransaction.apply {
                setReorderingAllowed(true)
                addSharedElement(imageView, imageView.transitionName)
                replace(R.id.container_content, DashboardDetailFragment())
                bottomNavFragment?.let {
                    setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                    remove(it)
                }
                addToBackStack(null)
                commit()
            }
        }
    }

}