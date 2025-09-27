class Course {
    protected String title;
    protected String instructor;
    protected String enrollmentDate;
    
    public Course(String title, String instructor, String enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }
    
    public void displayProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled: " + enrollmentDate);
    }
}

class VideoCourse extends Course {
    private int videosWatched;
    private int totalVideos;
    private int watchTime;
    
    public VideoCourse(String title, String instructor, String enrollmentDate, int totalVideos) {
        super(title, instructor, enrollmentDate);
        this.totalVideos = totalVideos;
        this.videosWatched = 0;
        this.watchTime = 0;
    }
    
    public void watchVideo(int minutes) {
        videosWatched++;
        watchTime += minutes;
    }
    
    public void displayProgress() {
        double completion = (double) videosWatched / totalVideos * 100;
        System.out.println("Video Course Progress");
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Completion: " + completion + "%");
        System.out.println("Watch time: " + watchTime + " minutes");
    }
}

class InteractiveCourse extends Course {
    private double quizScore;
    private int projectsCompleted;
    
    public InteractiveCourse(String title, String instructor, String enrollmentDate) {
        super(title, instructor, enrollmentDate);
        this.quizScore = 0.0;
        this.projectsCompleted = 0;
    }
    
    public void completeQuiz(double score) {
        this.quizScore = score;
    }
    
    public void completeProject() {
        projectsCompleted++;
    }
    
    public void displayProgress() {
        System.out.println("Interactive Course Progress");
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Quiz score: " + quizScore + "%");
        System.out.println("Projects completed: " + projectsCompleted);
    }
}

class ReadingCourse extends Course {
    private int pagesRead;
    private int totalPages;
    private int notesCreated;
    
    public ReadingCourse(String title, String instructor, String enrollmentDate, int totalPages) {
        super(title, instructor, enrollmentDate);
        this.totalPages = totalPages;
        this.pagesRead = 0;
        this.notesCreated = 0;
    }
    
    public void readPages(int pages) {
        pagesRead += pages;
    }
    
    public void createNote() {
        notesCreated++;
    }
    
    public void displayProgress() {
        double progress = (double) pagesRead / totalPages * 100;
        System.out.println("Reading Course Progress");
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Pages read: " + pagesRead + "/" + totalPages);
        System.out.println("Reading progress: " + progress + "%");
        System.out.println("Notes created: " + notesCreated);
    }
}

class CertificationCourse extends Course {
    private int examAttempts;
    private double bestScore;
    private boolean isCertified;
    
    public CertificationCourse(String title, String instructor, String enrollmentDate) {
        super(title, instructor, enrollmentDate);
        this.examAttempts = 0;
        this.bestScore = 0.0;
        this.isCertified = false;
    }
    
    public void takeExam(double score) {
        examAttempts++;
        if (score > bestScore) {
            bestScore = score;
        }
        if (score >= 70.0) {
            isCertified = true;
        }
    }
    
    public void displayProgress() {
        String status = isCertified ? "Certified" : "Not Certified";
        System.out.println("Certification Course Progress");
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Exam attempts: " + examAttempts);
        System.out.println("Best score: " + bestScore + "%");
        System.out.println("Status: " + status);
    }
}

public class LearningPlatformApp {
    public static void main(String[] args) {
        System.out.println("Online Learning Platform");
        System.out.println();
        
        VideoCourse video = new VideoCourse("Java Basics", "John Smith", "2024-01-15", 10);
        video.watchVideo(30);
        video.watchVideo(45);
        video.displayProgress();
        System.out.println();
        
        InteractiveCourse interactive = new InteractiveCourse("Python Programming", "Sarah Lee", "2024-02-01");
        interactive.completeQuiz(85.0);
        interactive.completeProject();
        interactive.completeProject();
        interactive.displayProgress();
        System.out.println();
        
        ReadingCourse reading = new ReadingCourse("Digital Marketing", "Mike Wilson", "2024-01-20", 200);
        reading.readPages(150);
        reading.createNote();
        reading.createNote();
        reading.displayProgress();
        System.out.println();
        
        CertificationCourse cert = new CertificationCourse("AWS Certification", "Lisa Chen", "2024-02-10");
        cert.takeExam(65.0);
        cert.takeExam(78.0);
        cert.displayProgress();
        System.out.println();
        
        System.out.println("Same method 'displayProgress' shows different information for each course type!");
    }
}