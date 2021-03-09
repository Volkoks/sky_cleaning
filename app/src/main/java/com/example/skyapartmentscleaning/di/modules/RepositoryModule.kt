package com.example.skyapartmentscleaning.di.modules

import com.example.skyapartmentscleaning.data.checklist.DataPointCheckList
import com.example.skyapartmentscleaning.data.datasource.DatasourceHistoryChecklist
import com.example.skyapartmentscleaning.data.datasource.IDataSource
import com.example.skyapartmentscleaning.data.hystory_checklist.HistoryChecklistPoint
import com.example.skyapartmentscleaning.data.repository.*
import com.example.skyapartmentscleaning.data.room.database.ApartDatabase
import com.example.skycleaning.data.repository.ApartsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun localHistoryDatasource(db: ApartDatabase): IDataSource<List<HistoryChecklistPoint>> =
        DatasourceHistoryChecklist(db)

    @Provides
    @Singleton
    fun repoHistory(localHistoryDatasource: IDataSource<List<HistoryChecklistPoint>>)
            : IRepositoryHistory<List<HistoryChecklistPoint>> =
        HistoryChecklistRepository(localHistoryDatasource)

    @Provides
    @Singleton
    fun repoChecklist(): IRepository<MutableList<DataPointCheckList>> = CheckListPointRespository()

    @Provides
    @Singleton
    fun repoApart(): IReposirotyAparts = ApartsRepository()

}