package com.plusmobileapps.bottomnavtransition.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.plusmobileapps.bottomnavtransition.R
import com.plusmobileapps.bottomnavtransition.ui.dashboarddetail.DashboardDetailFragment
import com.plusmobileapps.bottomnavtransition.ui.setupBottomNav

class DashboardFragment : Fragment() {

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
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        setupBottomNav(root, R.id.navigation_dashboard)
        textView.text = "This is dashboard"
        root.findViewById<Button>(R.id.button).setOnClickListener {
            fragmentManager?.beginTransaction()?.let { fragmentTransaction ->
                fragmentTransaction
                    .setReorderingAllowed(true)
                    .addSharedElement(root.findViewById(R.id.imageView), getString(R.string.android_guy_transition_name))

                        //multi animation - https://stackoverflow.com/a/48703708
//                    .setCustomAnimations( R.anim.slide_in_top, R.anim.slide_out_top ) // Top Fragment Animation

//                    .setCustomAnimations(R.animator.slide_up, R.animator.slide_down, R.animator.slide_up, R.animator.slide_up)
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                    .replace(R.id.container, DashboardDetailFragment())
                        //multi animation
//                    .setCustomAnimations( R.anim.slide_in_bottom, R.anim.abc_slide_out_bottom ) // Bottom Fragment Animation
                    .addToBackStack(null)
                    .commit()
            }
        }
        return root
    }
}