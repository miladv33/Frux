package com.example.frux

import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.frux.data.map.mappers.PixarImageMapper
import com.example.frux.data.model.base.CustomException
import com.example.frux.data.remote.model.HitDTO
import com.example.frux.data.remote.model.PixabayImageDTO
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class PixarImageMapperTest {
    
    private lateinit var mapper: PixarImageMapper
    private val hitDTO = HitDTO(
        collections = 1,
        comments = 2,
        downloads = 3,
        id = 4,
        imageHeight = 5,
        imageSize = 6,
        imageWidth = 7,
        largeImageURL = "https://example.com/large_image.jpg",
        likes = 8,
        pageURL = "https://example.com/page",
        previewHeight = 9,
        previewURL = "https://example.com/preview.jpg",
        previewWidth = 10,
        tags = "tag1, tag2",
        type = "photo",
        user = "user",
        userImageURL = "https://example.com/user_image.jpg",
        user_id = 11,
        views = 12,
        webformatHeight = 13,
        webformatURL = "https://example.com/webformat.jpg",
        webformatWidth = 14
    )

    @Before
    fun setUp() {
        mapper = PixarImageMapper(FailedMapperDelegateImpl())
    }

    @Test
    fun `map should return success with valid input`() {
        // Arrange
        val hitsDTO = listOf(hitDTO)
        val pixabayImageDTO = PixabayImageDTO(hitsDTO, 1, 1)
        val responseDTO = Response.success(pixabayImageDTO)

        // Act
        val result = mapper.map(responseDTO)

        // Assert
        assertTrue(result.isSuccess)
        result.onSuccess { pixabayImage ->
            assertEquals(pixabayImage.hits.size, 1)
            assertEquals(pixabayImage.hits[0].id, hitDTO.id)
            assertEquals(pixabayImage.hits[0].comments, hitDTO.comments)
            assertEquals(pixabayImage.hits[0].downloads, hitDTO.downloads)
            assertEquals(pixabayImage.hits[0].largeImageURL, hitDTO.largeImageURL)
            assertEquals(pixabayImage.hits[0].likes, hitDTO.likes)
            assertEquals(pixabayImage.hits[0].previewURL, hitDTO.previewURL)
            assertEquals(pixabayImage.hits[0].tags, hitDTO.tags)
            assertEquals(pixabayImage.hits[0].user, hitDTO.user)
            assertEquals(pixabayImage.hits[0].userImageURL, hitDTO.userImageURL)
            assertEquals(pixabayImage.hits[0].user_id, hitDTO.user_id)
            assertEquals(pixabayImage.hits[0].views, hitDTO.views)
        }
    }

    @Test
    fun `map should return failure with error response`() {
        // Arrange
        val responseDTO = Response.error<PixabayImageDTO>(
            400, ResponseBody.create(
                "application/json".toMediaTypeOrNull(), "error"
            )
        )

        // Act
        val result = mapper.map(responseDTO)

        // Assert
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is CustomException)
    }
}
