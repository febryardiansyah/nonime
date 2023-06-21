package id.nonime.app.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.nonime.app.R
import id.nonime.app.adapter.HomeGenresAdapter
import id.nonime.app.ui.home.dummyGenres

class GenresBottomSheetFragment(val windowManager: WindowManager) : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.genre_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genresRV: RecyclerView = view.findViewById(R.id.genreBottomSheetRV)
        genresRV.layoutManager = GridLayoutManager(view.context, 4)
        genresRV.adapter = HomeGenresAdapter(dummyGenres)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val bottomSheet =
            dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height = height / 2
    }

    override fun getTheme(): Int {
        return R.style.rounded_bottom_sheet_theme
    }
}