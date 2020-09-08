package com.example.fagments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.R
import kotlinx.android.synthetic.main.fragment_basic_operations.*
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

class ReadOperationsFragment : Fragment() {
    private var imageMat:Mat? = null

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
            readImageWithFlag(Imgcodecs.IMREAD_UNCHANGED)
            updateDisplayData()
        }

        readColorButton.setOnClickListener {
            readImageWithFlag(Imgcodecs.IMREAD_COLOR)
            updateDisplayData()
        }

        readGrayScale.setOnClickListener {
            readImageWithFlag(Imgcodecs.IMREAD_GRAYSCALE)
            updateDisplayData()
        }
    }

    private fun readImageWithFlag(flag:Int) {
        val sourceStream = activity?.assets?.open("example_image.png")
        if (sourceStream != null) {
            val bytes = sourceStream.readBytes()
            val matWithBytes = MatOfByte(*bytes)

            imageMat = Imgcodecs.imdecode(matWithBytes, flag)
            val convertedBitmap =
                Bitmap.createBitmap(
                    imageMat?.cols() ?: 0,
                    imageMat?.rows() ?: 0,
                    Bitmap.Config.ARGB_8888
                )
            Utils.matToBitmap(imageMat, convertedBitmap)
            processedImageView.setImageBitmap(convertedBitmap)
        }
    }

    private fun updateDisplayData() {
        activity?.runOnUiThread {
            typeValue.text = imageMat?.type().toString()
            rowValue.text = imageMat?.rows().toString()
            colsValue.text = imageMat?.cols().toString()
            channelsValue.text = imageMat?.channels().toString()
        }
    }
}