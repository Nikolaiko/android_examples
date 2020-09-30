package com.ml.core.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import com.ml.core.R

class MLCoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ml_core, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageStream = activity?.assets?.open("download.png")

        val l = activity?.assets?.list("") ?: emptyArray()
        for (i in l) {
            println("I : $i")
        }
        val bitmap = BitmapFactory.decodeStream(imageStream)
        val image = InputImage.fromBitmap(bitmap, 0)

        val localModel = LocalModel
                .Builder()
                .setAssetFilePath("assets/object_detection_mobile.tflite")
                .build()

        println(localModel)

        val customObjectDetectorOptions =
            CustomObjectDetectorOptions.Builder(localModel)
                .setDetectorMode(CustomObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableMultipleObjects()
                .enableClassification()
                .setClassificationConfidenceThreshold(0.5f)
                .setMaxPerObjectLabelCount(3)
                .build()

        val objectDetector =
            ObjectDetection.getClient(customObjectDetectorOptions)

        objectDetector
            .process(image).addOnFailureListener {
                activity?.runOnUiThread {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }.addOnSuccessListener {
                activity?.runOnUiThread {
                    Toast.makeText(context, it.count(), Toast.LENGTH_SHORT).show()
                }
            }
    }
}
