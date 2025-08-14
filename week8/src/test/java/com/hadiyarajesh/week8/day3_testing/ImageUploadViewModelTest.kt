package com.hadiyarajesh.week8.day3_testing

import app.cash.turbine.test
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadViewModel
import com.hadiyarajesh.week8.day2_image_upload.ImageUploadViewState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ImageUploadViewModelTest {
    private lateinit var repository: TestImageUploadRepository
    private lateinit var workManagerWrapper: TestWorkManagerWrapper

    /**
     * There are 2 ways to provide dummy data
     * 1. Fake - Faking
     * 2. Mock - Mocking
     */
    @Before
    fun setup() {
        repository = TestImageUploadRepository()
        workManagerWrapper = TestWorkManagerWrapper()
    }

    @Test
    fun `verify-that-returned-image-url-is-correct`() {
        val viewModel = newViewModel()
        val myImageUrl = "https://example.com/hadiyarajesh.jpg"
        repository.imageUrl = myImageUrl

        runTest {
            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    true
                )

//                val item1 = awaitItem()
//                Assert.assertTrue(item1 is ImageUploadViewState.Initial)
                skipItems(1)

                val item2 = awaitItem()
                Assert.assertTrue(item2 is ImageUploadViewState.Loading)

                val item3 = awaitItem()
                Assert.assertTrue(item3 is ImageUploadViewState.Success)
                val retrievedImageUrl = (item3 as ImageUploadViewState.Success).imageUrl
                Assert.assertEquals(myImageUrl, retrievedImageUrl)
            }
        }
    }

    @Test
    fun `verify-that-stateflow-emits-error-when-exception-is-thrown`() {
        val viewModel = newViewModel()
        repository.throwError = true

        runTest {
            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    true
                )

                skipItems(2) // Skip Initial and Loading state

//                cancelAndConsumeRemainingEvents()
//                cancelAndIgnoreRemainingEvents()

                val item = awaitItem()
                Assert.assertTrue(item is ImageUploadViewState.Error)
                val message = (item as ImageUploadViewState.Error).message
                Assert.assertEquals("Something went wrong", message)
            }
        }
    }

    @Test
    fun `verify-that-work-manager-returns-correct-image-url`() {
        val viewModel = newViewModel()
        val myImageUrl = "https://example.com/hadiyarajesh.jpg"
        workManagerWrapper.imageUrl = myImageUrl

        runTest {
            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    false
                )

                skipItems(2) // Skip Initial and Loading state

                val item = awaitItem()
                Assert.assertTrue(item is ImageUploadViewState.Success)
                val retrievedImageUrl = (item as ImageUploadViewState.Success).imageUrl
                Assert.assertEquals(myImageUrl, retrievedImageUrl)
            }
        }
    }

    @Test
    fun `verify-that-error-state-is-trigger-when-work-manager-fails`() {
        val viewModel = newViewModel()
        workManagerWrapper.throwError = true

        runTest {
            viewModel.imageUploadResponse.test {
                viewModel.uploadImage(
                    "https://example.com/image.jpg",
                    false
                )

                skipItems(2) // Skip Initial and Loading state

                val item = awaitItem()
                Assert.assertTrue(item is ImageUploadViewState.Error)
                val message = (item as ImageUploadViewState.Error).message
                Assert.assertEquals("Background work failed.", message)
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
