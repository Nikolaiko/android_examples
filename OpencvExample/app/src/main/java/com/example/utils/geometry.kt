package com.example.utils

import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

fun deg2rad(x: Float): Float {
    return (x / 180.0 * PI).toFloat()
}

fun hypot(x: Double, y: Double): Double {
    return sqrt(x*x + y*y)
}

fun dist(p1: Point, p2: Point): Double {
    return hypot(p1.x - p2.x, p1.y - p2.y)
}

fun resizeImageWithPad(src: Mat, size: Size): Mat {
    val rows = size.height.toInt()
    val cols = size.width.toInt()
    val scale = min(rows.toDouble() / src.rows(), cols.toDouble() / src.cols())
    Imgproc.resize(src, src, Size(src.width() * scale, src.height() * scale))
    val dst = Mat.zeros(size, src.type())
    val x = (cols - src.cols()) / 2
    val y = (rows - src.rows()) / 2
    val mask = Mat.ones(src.size(), CvType.CV_8U)
    Core.copyTo(src, dst.submat(y, y + src.rows(), x, x + src.cols()), mask)
    return dst
}

fun resizeImageToFill(src: Mat, dst: Size): Mat {
    val rows = dst.height.toInt()
    val cols = dst.width.toInt()
    val scale = max(rows.toDouble() / src.rows(), cols.toDouble() / src.cols())
    Imgproc.resize(src, src, Size(src.width() * scale, src.height() * scale))
    val x = (src.cols() - cols) / 2
    val y = (src.rows() - rows) / 2
    return src.submat(y, y + rows, x, x + cols)
}

fun arr2mat(data: Array<FloatArray>): Mat {
    val m = Mat(data.size, data[0].size, CvType.CV_32F)
    var i = 0
    for (row in data) {
        m.put(i, 0, row)
        i += 1
    }
    return m
}