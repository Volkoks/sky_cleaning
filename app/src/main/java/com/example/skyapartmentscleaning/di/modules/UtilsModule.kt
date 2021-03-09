package com.example.skyapartmentscleaning.di.modules

import com.example.skyapartmentscleaning.utils.generate_report.GenerateReport
import com.example.skyapartmentscleaning.utils.generate_report.IGenerateReport
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun provideGenereteReport(): IGenerateReport = GenerateReport()
}