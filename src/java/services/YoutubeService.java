package services;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class YoutubeService implements IYoutubeService {

    // Set the application name for YouTube API requests
    private static final String APPLICATION_NAME = "OnlineLearn_SWP391";
    // Replace YOUR_API_KEY with your actual YouTube API key
    private static final String DEVELOPER_KEY = "YOUR_API_KEY"; 

    /**
     * Uploads a video to YouTube and returns the ID of the uploaded video.
     *
     * @param filePath The file path of the video to be uploaded.
     * @param videoTitle The title of the video.
     * @return The ID of the uploaded video on success, or null on failure.
     * @throws IOException If an error occurs while uploading the video.
     */
    @Override
    public String uploadVideo(String filePath, String videoTitle) throws IOException {
        try {
            // Initialize YouTube service
            YouTube youtubeService = getService();

            // Create InputStreamContent for the video file
            File mediaFile = new File(filePath);
            InputStreamContent mediaContent
                    = new InputStreamContent("application/octet-stream",
                            new BufferedInputStream(new FileInputStream(mediaFile)));
            mediaContent.setLength(mediaFile.length());

            // Create a new Video object and set its snippet with title
            Video video = new Video();
            VideoSnippet snippet = new VideoSnippet();
            snippet.setTitle(videoTitle);
            video.setSnippet(snippet);

            // Create a request to insert the video with its snippet content
            YouTube.Videos.Insert request = youtubeService.videos()
                    .insert(Arrays.asList("snippet"), video, mediaContent);

            // Execute the request with the developer key and get the response
            Video response = request.setKey(DEVELOPER_KEY).execute();
            return response.getId();    // Return the ID of the uploaded video
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;    // Return null if an error occurs
        }
    }

    // Helper method to initialize YouTube service
    private static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
