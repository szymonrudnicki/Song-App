package com.github.szymonrudnicki.songapp

import com.github.szymonrudnicki.songapp.domain.TestSchedulers
import com.github.szymonrudnicki.songapp.domain.songs.model.SongDomainModel
import com.github.szymonrudnicki.songapp.domain.songs.repositories.SongsRepository
import com.github.szymonrudnicki.songapp.domain.songs.usecases.GetSongsFromRemoteUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class GetSongsFromRemoteUseCaseTest {
    @MockK
    lateinit var songsRepository: SongsRepository

    private lateinit var testObserver: TestObserver<List<SongDomainModel>>
    private lateinit var useCase: GetSongsFromRemoteUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testObserver = TestObserver()
        useCase = GetSongsFromRemoteUseCase(songsRepository, TestSchedulers())
    }

    @Test
    fun `should return songs list when response is successful`() {
        val remoteResult = listOf(
                SongDomainModel("a", "a", "a"),
                SongDomainModel("b", "b", "b"),
                SongDomainModel("c", "c", "c")
        )
        every { songsRepository.getSongsFromRemote() } returns Single.just(remoteResult)

        useCase.execute().subscribe(testObserver)

        testObserver
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors()

        testObserver.dispose()
    }
}
