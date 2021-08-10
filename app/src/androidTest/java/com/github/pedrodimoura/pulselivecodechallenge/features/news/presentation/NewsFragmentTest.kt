package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.pedrodimoura.pulselivecodechallenge.common.NetworkingTest
import com.github.pedrodimoura.pulselivecodechallenge.common.di.NetworkModule
import com.github.pedrodimoura.pulselivecodechallenge.features.news.data.datasources.remote.service.NEWS_INFORMATION_ENDPOINT
import com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.activity.NewsActivity
import com.github.pedrodimoura.pulselivecodechallenge.features.news.robot.NewsRobot
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import java.net.HttpURLConnection
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(NetworkModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsFragmentTest : NetworkingTest() {

    private lateinit var activityScenario: ActivityScenario<NewsActivity>

    private val newsDispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val url = request.path.toString()
            return when {
                url.contains(NEWS_INFORMATION_ENDPOINT) -> MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readStringFile("responses/news.json"))
                else -> MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(readStringFile("responses/article-details.json"))
            }
        }
    }

    @Before
    override fun setup() {
        super.setup()
        super.setDispatcher(newsDispatcher)
    }

    @After
    fun tearDown() {
        activityScenario.close()
    }

    @Test
    fun shouldShowSubtitleDetailsWhenArticleIsClicked() {
        activityScenario = launchActivity(getDefaultIntent())

        NewsRobot()
            .clickArticle()
            .checkIfBodyIsDisplayed()
    }

    private fun getDefaultIntent(): Intent =
        Intent(ApplicationProvider.getApplicationContext(), NewsActivity::class.java)

}
