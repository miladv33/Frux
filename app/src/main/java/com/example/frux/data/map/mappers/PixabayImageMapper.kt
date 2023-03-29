package com.example.frux.data.map.mappers

import com.example.frux.data.map.base.ResponseMapper
import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegateImpl
import com.example.frux.data.model.Hit
import com.example.frux.data.model.PixabayImage
import com.example.frux.data.remote.model.PixabayImageDTO
import retrofit2.Response
import javax.inject.Inject


class PixarImageMapper @Inject constructor(override val failedMapperDelegate: FailedMapperDelegateImpl) :
    ResponseMapper<PixabayImageDTO, PixabayImage> {
    override fun createModelFromDTO(input: Response<PixabayImageDTO>): PixabayImage {
        val dto = input.body()!!
        // Map each HitDTO object to a Hit object
        val hits = dto.hits.map { hitDTO ->
            Hit(
                comments = hitDTO.comments,
                downloads = hitDTO.downloads,
                id = hitDTO.id,
                largeImageURL = hitDTO.largeImageURL,
                likes = hitDTO.likes,
                previewHeight = hitDTO.previewHeight,
                tags = hitDTO.tags,
                user = hitDTO.user,
                userImageURL = hitDTO.userImageURL,
                user_id = hitDTO.user_id,
                views = hitDTO.views
            )
        }
        // Return a new PixabayImage object with the mapped list
        return PixabayImage(hits)
    }
}