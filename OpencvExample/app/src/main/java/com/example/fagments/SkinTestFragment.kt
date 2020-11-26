package com.example.fagments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.R
import com.example.utils.SkinDetektor
import kotlinx.android.synthetic.main.fragment_skin_test.*
import org.opencv.android.Utils

class SkinTestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skin_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageStream = activity?.assets?.open("finger1.jpeg")
        val bitmap = BitmapFactory.decodeStream(imageStream)

        val skinMat = SkinDetektor().detectSkinPixels(bitmap)
        val convertedBitmap =
            Bitmap.createBitmap(
                skinMat.cols() ?: 0,
                skinMat.rows() ?: 0,
                Bitmap.Config.ARGB_8888
            )
        Utils.matToBitmap(skinMat, convertedBitmap)
        initialImage.setImageBitmap(convertedBitmap)





    }
}
