package com.example.fagments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ImageReader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.R
import kotlinx.android.synthetic.main.fragment_basic_operations.*
import kotlinx.android.synthetic.main.fragment_modifications.*
import kotlinx.android.synthetic.main.fragment_modifications.sourceImageView
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs

class ModificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        removeColorButton.setOnClickListener {
            val imageStream = activity?.assets?.open("dmc5.jpg")
            val bitmap = BitmapFactory.decodeStream(imageStream)

            activity?.runOnUiThread {
                sourceImageView.setImageBitmap(bitmap)
                val imageMat = readImageWithName("dmc5.jpg")
                val mask = Mat(imageMat.size(), imageMat.type(), Scalar(255.0))
                val alphaChannel = Mat()



            }
        }

        writeOnImageButton.setOnClickListener {
            val imageStream = activity?.assets?.open("dante.jpeg")
            val bitmap = BitmapFactory.decodeStream(imageStream)

            activity?.runOnUiThread {
                sourceImageView.setImageBitmap(bitmap)
                val imageMat = readImageWithName("dante.jpeg")

                



            }
        }
    }


    private fun readImageWithName(name: String): Mat {
        var result = Mat()
        val sourceStream = activity?.assets?.open(name)
        if (sourceStream != null) {
            val bytes = sourceStream.readBytes()
            val matWithBytes = MatOfByte(*bytes)

            result = Imgcodecs.imdecode(matWithBytes, Imgcodecs.IMREAD_UNCHANGED)
        }
        return result
    }
}