package com.hadiyarajesh.week8.day3_testing

import com.hadiyarajesh.week8.day2_image_upload.Image
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadRepository
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadResponse

class TestImageUploadRepository : ImageUploadRepository {
    var throwError: Boolean = false
    var imageUrl: String = "https://example.com/image.jpg"

    override suspend fun uploadImage(imageUriString: String): ImageUploadResponse {
        if (throwError) {
            throw Exception("Something went wrong")
        }

        return ImageUploadResponse(
            statusCode = 200,
            Image(
                name = "image",
                extension = "jpeg",
                url = imageUrl
            )
        )
    }
}
