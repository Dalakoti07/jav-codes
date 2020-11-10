package useCase.Solution2;

import useCase.Solution1.Executor;
import useCase.Solution2.model.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

public class RemoteService {
    private static int availableCores= Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService = Executors.newFixedThreadPool(availableCores+1);

    public void stop(){
        executorService.shutdown();
    }

    public void getUserCurrentActivities(ResultCallback callback){
        executorService.execute(()->{
            List<Like> likes= new ArrayList<>();
            List<Post> posts= new ArrayList<>();
            List<Comment> comments= new ArrayList<>();
            List<Friend> friends= new ArrayList<>();


            Future<List<Like>> futureLikes= executorService.submit(getLikesFromServer(""));
            Future<List<Post>> futurePosts= executorService.submit(getPostsFromServer(""));
            Future<List<Comment>> futureComments= executorService.submit(getCommentsFromServer(""));
            Future<List<Friend>> futureFriends= executorService.submit(getFriendsFromServer(""));

            try {
                likes = futureLikes.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                posts = futurePosts.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                comments = futureComments.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                friends = futureFriends.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            List<Activity> activities = new ArrayList<>();
            activities.addAll(likes);
            activities.addAll(posts);
            activities.addAll(comments);
            activities.addAll(friends);

            Collections.sort(activities,
                    (activity1, activity2) -> activity1.getDateCreate().compareTo(activity2.getDateCreate()));

            callback.onResult(activities);
        });
    }

    public Callable<List<Like>> getLikesFromServer(String url){
        System.out.println("fetching likes from server");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return () -> Arrays.asList(new Like(new Date(1105041184)), new Like(new Date(1205041284L)));
    }

    public Callable<List<Post>> getPostsFromServer(String url){
        System.out.println("fetching posts from server");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return () -> Arrays.asList(new Post(new Date(1602041184)), new Post(new Date(1621041284L)));
    }

    private Callable<List<Comment>> getCommentsFromServer(String url) {
        return () -> {
            System.out.println("fetching comments from server");

            Thread.sleep(2500);
            return Arrays.asList(new Comment(new Date(1533978248560L)), new Comment(new Date(1515957246460L)));
        };
    }

    private Callable<List<Friend>> getFriendsFromServer(String url) {
        return () -> {
            System.out.println("fetching friends from server");
            Thread.sleep(10);
            return Arrays.asList(new Friend(new Date(1531378248561L)), new Friend(new Date(1535998246462L)));
        };
    }
}
