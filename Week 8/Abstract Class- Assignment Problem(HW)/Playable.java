interface Playable {
    void play();
    void pause();
}

class MusicPlayer implements Playable {
    public void play() {
        System.out.println("Music is playing...");
    }
    
    public void pause() {
        System.out.println("Music is paused");
    }
}

class VideoPlayer implements Playable {
    public void play() {
        System.out.println("Video is playing...");
    }
    
    public void pause() {
        System.out.println("Video is paused");
    }
}

class PlayableClass {
    public static void main(String[] args) {
        Playable music = new MusicPlayer();
        music.play();
        music.pause();
        
        System.out.println();
        
        Playable video = new VideoPlayer();
        video.play();
        video.pause();
        
        System.out.println();
        System.out.println("Demonstrating Polymorphism:");
        
        Playable[] players = {new MusicPlayer(), new VideoPlayer()};
        
        for(Playable player : players) {
            player.play();
        }
    }
}