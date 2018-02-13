package com.example.speedtest.data.repository

import com.example.speedtest.data.models.GraphPointModel
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
interface GraphRepository {

    fun saveGraphPoint(graphPointModel: GraphPointModel)

    fun getGraphPoints(): Single<List<GraphPointModel>>

}