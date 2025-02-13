// Aim: Design and implement a simple inventory control system for a small video rental store.

// Class representing a Video
class Video {
    private String title;
    private boolean checkedOut;
    private double rating;
    private int ratingCount;

    public Video(String title) {
        this.title = title;
        this.checkedOut = false;
        this.rating = 0;
        this.ratingCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkout() {
        if (!checkedOut) {
            checkedOut = true;
            System.out.println(title + " has been rented.");
        } else {
            System.out.println(title + " is already rented.");
        }
    }

    public void returnVideo() {
        if (checkedOut) {
            checkedOut = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not rented.");
        }
    }

    public void receiveRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = ((this.rating * ratingCount) + rating) / (++ratingCount);
            System.out.println("Rating received for " + title + ": " + rating);
        } else {
            System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
        }
    }

    public void showVideoInfo() {
        System.out.println("Title: " + title + ", Rating: " + rating + ", Checked Out: " + checkedOut);
    }
}

// Class representing a Video Store
class VideoStore {
    private Video[] videos;
    private int count;

    public VideoStore(int size) {
        videos = new Video[size];
        count = 0;
    }

    public void addVideo(String title) {
        if (count < videos.length) {
            videos[count++] = new Video(title);
            System.out.println("Video added: " + title);
        } else {
            System.out.println("Inventory full! Cannot add more videos.");
        }
    }

    public void checkout(String title) {
        for (Video video : videos) {
            if (video != null && video.getTitle().equals(title)) {
                video.checkout();
                return;
            }
        }
        System.out.println("Video not found!");
    }

    public void returnVideo(String title) {
        for (Video video : videos) {
            if (video != null && video.getTitle().equals(title)) {
                video.returnVideo();
                return;
            }
        }
        System.out.println("Video not found!");
    }

    public void receiveRating(String title, int rating) {
        for (Video video : videos) {
            if (video != null && video.getTitle().equals(title)) {
                video.receiveRating(rating);
                return;
            }
        }
        System.out.println("Video not found!");
    }

    public void listInventory() {
        System.out.println("Video Store Inventory:");
        for (Video video : videos) {
            if (video != null) {
                video.showVideoInfo();
            }
        }
    }
}

// Main class to test the functionalities
public class VideoLauncher {
    public static void main(String[] args) {
        VideoStore store = new VideoStore(5);
        
        store.addVideo("The Matrix");
        store.addVideo("Inception");
        store.addVideo("Interstellar");
        store.addVideo("The Dark Knight");
        store.addVideo("Avengers: Endgame");

        store.receiveRating("The Matrix", 5);
        store.receiveRating("The Matrix", 4);
        store.receiveRating("Inception", 5);
        store.receiveRating("Interstellar", 3);
        store.receiveRating("The Dark Knight", 5);

        store.listInventory();

        store.checkout("The Matrix");
        store.checkout("Inception");
        store.checkout("Interstellar");

        store.listInventory();

        store.returnVideo("The Matrix");
        store.returnVideo("Inception");

        store.listInventory();
    }
}
