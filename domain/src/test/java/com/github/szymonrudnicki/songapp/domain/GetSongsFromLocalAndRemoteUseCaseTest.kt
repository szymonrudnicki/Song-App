package com.github.szymonrudnicki.songapp.domain

import com.github.szymonrudnicki.songapp.domain.common.TestSchedulers
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromLocalAndRemoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GetSongsFromLocalAndRemoteUseCaseTest {
    @MockK
    lateinit var songsRepository: SongsRepository

    private lateinit var testObserver: TestObserver<List<SongDomainModel>>
    private lateinit var useCase: GetSongsFromLocalAndRemoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testObserver = TestObserver()
        useCase = GetSongsFromLocalAndRemoteUseCase(songsRepository, TestSchedulers())
    }

    @Test
    fun `should return songs list when response is successful`() {
        val localResult = listOf(
                SongDomainModel("b", "b", "b"),
                SongDomainModel("c", "c", "c")
        )
        val remoteResult = listOf(
                SongDomainModel("a", "a", "a")
        )
        every { songsRepository.getSongsFromLocalAndRemote() } returns Single.just(localResult + remoteResult)

        useCase.execute().subscribe(testObserver)

        testObserver
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors()
                .assertValue(localResult + remoteResult)

        testObserver.dispose()
    }
}
