package com.example.speedtest.data.repository

import com.example.speedtest.data.models.SpeedInfoModel
import io.realm.Realm
import rx.Completable
import rx.Single

/**
 * Created by Sergey Panshyn on 13.02.2018.
 */
class SpeedRepositoryImpl: SpeedRepository {
    override fun setSpeedInfo(speedInfoModel: SpeedInfoModel): Completable {
        val realm = Realm.getDefaultInstance()

        realm.use {
            it.executeTransaction {
                it.copyToRealmOrUpdate(speedInfoModel)
            }
        }
        return Completable.complete()
    }

    override fun getSpeedInfo(): Single<SpeedInfoModel> {
        val realm = Realm.getDefaultInstance()
        val speedInfoModel = realm.where(SpeedInfoModel::class.java).findFirst() ?: return Single.just(SpeedInfoModel(0, 0, 0))

        realm.use {
            return Single.just(speedInfoModel)
        }
    }

}