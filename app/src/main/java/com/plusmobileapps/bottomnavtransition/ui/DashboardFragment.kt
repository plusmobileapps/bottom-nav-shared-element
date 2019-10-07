package com.plusmobileapps.bottomnavtransition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.plusmobileapps.bottomnavtransition.R

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
        textView.text = "This is dashboard"
        root.findViewById<Button>(R.id.button).setOnClickListener {
            onDetailButtonClicked()
        }
        return root
    }

    private fun onDetailButtonClicked() {
        val bottomNavFragment = fragmentManager?.findFragmentByTag(BottomNavFragment.TAG)
        fragmentManager?.beginTransaction()?.let { fragmentTransaction ->
            fragmentTransaction.apply {
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                replace(R.id.container_content, DashboardDetailFragment())
                bottomNavFragment?.let { remove(it) }
                addToBackStack(null)
                commit()
            }
        }
    }

}