package com.example.speedtest.data.repository

import android.util.Log
import com.example.speedtest.data.models.GraphPointModel
import io.realm.Realm
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class GraphRepositoryImpl: GraphRepository {
    override fun saveGraphPoint(graphPointModel: GraphPointModel) {
        val realm = Realm.getDefaultInstance()

        graphPointModel.assignPrimaryKey()
        realm.use {
            it.executeTransaction {
                it.copyToRealm(graphPointModel)
            }
        }
        Log.d("onxSetGraphPoint", "Saved")
    }

    override fun getGraphPoints(): Single<List<GraphPointModel>> {
        val realm = Realm.getDefaultInstance()

        realm.use {
            return Single.just(realm.copyFromRealm(realm.where(GraphPointModel::class.java).findAll()))
        }
    }
}