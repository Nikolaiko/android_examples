package com.example.fagments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.R
import kotlinx.android.synthetic.main.fragment_basic_operations.*
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

class BasicOperationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basic_operations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageStream = activity?.assets?.open("example_image.png")
        val bitmap = BitmapFactory.decodeStream(imageStream)

        sourceImageView.setImageBitmap(bitmap)
        justReadButton.setOnClickListener {
            val sourceStream = activity?.assets?.open("example_image.png")
            if (sourceStream != null) {
                val bytes = sourceStream.readBytes()
                val matWithBytes = MatOfByte(*bytes)
                val sourceImage = Imgcodecs.imdecode(matWithBytes, Imgcodecs.IMREAD_COLOR)

                println("Rad!")
            }
        }
    }
}