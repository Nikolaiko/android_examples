package com.example.utils

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class SkinDetektor {
// S.Kolkur1, D. Kalbande, P.Shimpi, C.Bapat,and J. Jatakia
// Human Skin Detection Using RGB, HSV and YCbCr Color Models

    private val c00 = arr2mat(
        arrayOf(
            floatArrayOf(1f, 0f, 0f, -95f), // R > 95
            floatArrayOf(0f, 1f, 0f, -40f), // G > 40
            floatArrayOf(0f, 0f, 1f, -20f), // B > 20
            floatArrayOf(1f, -1f, 0f, 0f), // R > G
            floatArrayOf(1f, 0f, -1f, 0f)  // R > B
        )
    )

    private val c01 = arr2mat(
        arrayOf(
            floatArrayOf(1f, -1f, 0f, -15f), // R - G >  15
            floatArrayOf(-1f, 1f, 0f, -15f)  // R - G < -15
        )
    )

    // H = 60*(G-B)/(R-B) when H < 60
// S = (R-B)/R
    private val c1 = arr2mat(
        arrayOf(
            floatArrayOf(50f, -60f, 10f, 0f),  // H < 50
            floatArrayOf(0.77f, 0f, -1f, 0f),// S > 0.23
            floatArrayOf(-0.32f, 0f, 1f, 0f) // S < 0.68
        )
    )

    // Y = 0.299*R + 0.587*G + 0.114*B
// Cr = (R-Y)*0.713+128
// Cb = (B-Y)*0.564+128
    private val c2 = arr2mat(
        arrayOf(
            floatArrayOf(4.99813000e-01f, -4.18531000e-01f, -8.12820000e-02f, -7.00000000e+00f),// Cr >= 135
            floatArrayOf(-1.68636000e-01f, -3.31068000e-01f, 4.99704000e-01f, 4.30000000e+01f),// Cb >= 85
            floatArrayOf(2.99000000e-01f, 5.87000000e-01f, 1.14000000e-01f, -8.00000000e+01f),// Y >= 80
            floatArrayOf(-7.67303423e-01f, -1.06609062e-01f, 8.73912485e-01f, 9.50336000e+01f),// Cr <=  1.5862*Cb+20
            floatArrayOf(
                5.57958693e-01f,
                -3.04378754e-01f,
                -2.53579939e-01f,
                7.65870000e+00f
            ),// Cr >=  0.3448*Cb+76.2069
            floatArrayOf(
                -2.70044067e-01f,
                -1.92992263e+00f,
                2.19996670e+00f,
                4.77780400e+02f
            ),// Cr >= -4.5652*Cb+234.5652
            floatArrayOf(-3.05881600e-01f, 7.99259200e-01f, -4.93377600e-01f, 2.65500000e+01f),// Cr <= -1.15*Cb+301.75
            floatArrayOf(
                -1.14361695e-01f,
                1.17525313e+00f,
                -1.06089143e+00f,
                1.22804000e+01f
            ) // Cr <= -2.2857*Cb+432.85
        )
    )

    private val trfResult = Mat()
    private val gtzResult = Mat()
    private val argb8 = Mat()
    private val rgb8 = Mat()
    private val img = Mat()
    private val m00 = Mat()
    private val m01 = Mat()
    private val m1 = Mat()
    private val m2 = Mat()



    private fun transformBitwise(src: Mat, m: Mat, op: (Mat, Mat, Mat) -> Unit, dst: Mat) {
        for (i in 0 until m.rows()) {
            Core.transform(src, trfResult, m.row(i))
            Core.compare(trfResult, Scalar(0.0), gtzResult, Core.CMP_GE)
            if (i == 0) {
                Core.copyTo(gtzResult, dst, gtzResult)
            } else {
                op(dst, gtzResult, dst)
            }
        }
    }

    fun detectSkinPixels(bmp: Bitmap): Mat {
        Utils.bitmapToMat(bmp, argb8)
        Imgproc.cvtColor(argb8, rgb8, Imgproc.COLOR_RGBA2RGB)

        rgb8.convertTo(img, CvType.CV_32F)

        m00.setTo(Scalar(0.0))
        m01.setTo(Scalar(0.0))
        m1.setTo(Scalar(0.0))
        m2.setTo(Scalar(0.0))

        transformBitwise(img, c00, Core::bitwise_and, m00)

        transformBitwise(img, c01, Core::bitwise_or, m01)

        transformBitwise(img, c1, Core::bitwise_and, m1)

        transformBitwise(img, c2, Core::bitwise_and, m2)

        // m00 & m01 & (m1 | m2)
        Core.bitwise_or(m1, m2, m1) // m1 = m1 | m2
        Core.bitwise_and(m00, m01, m00) // m00 = m00 & m01
        Core.bitwise_and(m00, m1, m00) // m00 = m00 & m1
//        Utils.matToBitmap(m00, out)

        Imgproc.GaussianBlur(m00, m00, Size(51.0,51.0), 8.0)
        Core.compare(m00, Scalar(192.0), m00, Core.CMP_GE)
        return m00
    }
}