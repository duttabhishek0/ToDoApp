package com.abhishek.todoapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abhishek.todoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 30) {

            // Root ViewGroup of my activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decor: View = window.decorView
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->

                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

                // Apply the insets as a margin to the view. Here the system is setting
                // only the bottom, left, and right dimensions, but apply whichever insets are
                // appropriate to your layout. You can also update the view padding
                // if that's more appropriate.

                view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
                    leftMargin = insets.left
                    bottomMargin = insets.bottom
                    rightMargin = insets.right
                }

                // Return CONSUMED if you don't want want the window insets to keep being
                // passed down to descendant views.
                WindowInsetsCompat.CONSUMED
            }
        }
    }
}
