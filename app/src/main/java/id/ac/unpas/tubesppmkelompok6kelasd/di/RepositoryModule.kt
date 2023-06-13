package id.ac.unpas.tubesppmkelompok6kelasd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.tubesppmkelompok6kelasd.networks.DosenApi
import id.ac.unpas.tubesppmkelompok6kelasd.networks.MahasiswaApi
import id.ac.unpas.tubesppmkelompok6kelasd.networks.MatkulApi
import id.ac.unpas.tubesppmkelompok6kelasd.persistences.DosenDao
import id.ac.unpas.tubesppmkelompok6kelasd.persistences.MahasiswaDao
import id.ac.unpas.tubesppmkelompok6kelasd.persistences.MatkulDao
import id.ac.unpas.tubesppmkelompok6kelasd.repositories.DosenRepository
import id.ac.unpas.tubesppmkelompok6kelasd.repositories.MahasiswaRepository
import id.ac.unpas.tubesppmkelompok6kelasd.repositories.MatkulRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    //    dosen
    @Provides
    @ViewModelScoped
    fun provideDosenRepository(
        api: DosenApi,
        dao: DosenDao
    ): DosenRepository {
        return DosenRepository(api, dao)
    }

    //    mahasiswa
    @Provides
    @ViewModelScoped
    fun provideMahasiswaRepository(
        api: MahasiswaApi,
        dao: MahasiswaDao
    ): MahasiswaRepository {
        return MahasiswaRepository(api, dao)
    }

    //    matkul
    @Provides
    @ViewModelScoped
    fun provideMatkulRepository(
        api: MatkulApi,
        dao: MatkulDao
    ): MatkulRepository {
        return MatkulRepository(api, dao)
    }
}