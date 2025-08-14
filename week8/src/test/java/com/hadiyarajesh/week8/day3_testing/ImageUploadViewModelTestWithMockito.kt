package com.hadiyarajesh.week8.day3_testing

import app.cash.turbine.test
import com.hadiyarajesh.week8.day2_image_upload.Image
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadRepository
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadResponse
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadViewModel
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadViewState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ImageUploadViewModelTestWithMockito {
    private lateinit var repository: ImageUploadRepository
    private lateinit var workManagerWrapper: TestWorkManagerWrapper

    @Before
    fun setup() {
        repository = mock(ImageUploadRepository::class.java)
        workManagerWrapper = TestWorkManagerWrapper()
    }

    @Test
    fun `verify-that-returned-image-url-is-correct`() {
        val viewModel = newViewModel()
        val imageUploadResponse = ImageUploadResponse(
            statusCode = 0, image = Image("image", "jpeg", "url")
        )

        runTest {
            `when`(repository.uploadImage(anyString())).thenReturn(imageUploadResponse)

            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    true
                )

                skipItems(1)

                val item2 = awaitItem()
                Assert.assertTrue(item2 is ImageUploadViewState.Loading)

                val item3 = awaitItem()
                Assert.assertTrue(item3 is ImageUploadViewState.Success)
                val retrievedImageUrl = (item3 as ImageUploadViewState.Success).imageUrl
                Assert.assertEquals(imageUploadResponse.image.url, retrievedImageUrl)
            }
        }
    }

    @Test
    fun `verify-that-stateflow-emits-error-when-exception-is-thrown`() {
        val viewModel = newViewModel()

        runTest {
            `when`(repository.uploadImage(anyString())).thenThrow(RuntimeException("Something went wrong"))

            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    true
                )

                skipItems(2) // Skip Initial and Loading state

                val item = awaitItem()
                Assert.assertTrue(item is ImageUploadViewState.Error)
                val message = (item as ImageUploadViewState.Error).message
                Assert.assertEquals("Something went wrong", message)
            }
        }
    }

    fun newViewModel(): ImageUploadViewModel {
        return ImageUploadViewModel(
            repository = repository,
            workManagerWrapper = workManagerWrapper,
        )
    }
}
