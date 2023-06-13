package id.ac.unpas.tubesppmkelompok6kelasd.di

import android.content.Context
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.tubesppmkelompok6kelasd.networks.DosenApi
import id.ac.unpas.tubesppmkelompok6kelasd.networks.MahasiswaApi
import id.ac.unpas.tubesppmkelompok6kelasd.networks.MatkulApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            //Hanya untuk development/debug. Tidak disarankan untuk produksi.
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "https://ppm-api.gusdya.net/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    //    dosen
    @Provides
    @Singleton
    fun provideDosenApi(retrofit: Retrofit): DosenApi {
        return retrofit.create(DosenApi::class.java)
    }
    //    mahasiswa
    @Provides
    @Singleton
    fun provideMahasiswaApi(retrofit: Retrofit): MahasiswaApi {
        return retrofit.create(MahasiswaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMatkulApi(retrofit: Retrofit): MatkulApi {
        return retrofit.create(MatkulApi::class.java)
    }
}