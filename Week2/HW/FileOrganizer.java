package HW;

import java.util.*;

public class FileOrganizer {
    static class FileInfo {
        String name;
        String extension;
        String category;
        String newName;
        String subCategory;
        int priority;

        FileInfo(String name, String extension) {
            this.name = name;
            this.extension = extension;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of files: ");
        int n = Integer.parseInt(sc.nextLine());
        FileInfo[] files = new FileInfo[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter file name with extension: ");
            String fileName = sc.nextLine();
            files[i] = extractFileComponents(fileName);
            categorizeFile(files[i]);
            files[i].newName = generateNewFileName(files[i], i + 1);
            simulateContentAnalysis(files[i]);
        }

        System.out.println("\n--- Organized Files ---");
        for (FileInfo file : files) {
            System.out.println("Original: " + file.name + "." + file.extension +
                    " | Category: " + file.category +
                    " | Subcategory: " + file.subCategory +
                    " | Priority: " + file.priority +
                    " | New Name: " + file.newName);
        }
    }

    public static FileInfo extractFileComponents(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == fileName.length() - 1) {
            return new FileInfo("InvalidFile", "unknown");
        }
        String name = fileName.substring(0, dotIndex);
        String ext = fileName.substring(dotIndex + 1).toLowerCase();
        return new FileInfo(name, ext);
    }

    public static void categorizeFile(FileInfo file) {
        String ext = file.extension;
        if (ext.equals("txt") || ext.equals("doc")) file.category = "Document";
        else if (ext.equals("jpg") || ext.equals("png")) file.category = "Image";
        else if (ext.equals("mp3") || ext.equals("wav")) file.category = "Audio";
        else if (ext.equals("mp4") || ext.equals("mkv")) file.category = "Video";
        else file.category = "Unknown";
    }

    public static String generateNewFileName(FileInfo file, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(file.category).append("_");
        sb.append(System.currentTimeMillis() / 1000).append("_").append(index);
        sb.append(".").append(file.extension);
        return sb.toString();
    }

    public static void simulateContentAnalysis(FileInfo file) {
        if (file.extension.equals("txt")) {
            String content = file.name.toLowerCase();
            if (content.contains("resume") || content.contains("cv")) {
                file.subCategory = "Resume";
                file.priority = 3;
            } else if (content.contains("report")) {
                file.subCategory = "Report";
                file.priority = 2;
            } else if (content.contains("code") || content.contains("java")) {
                file.subCategory = "Code";
                file.priority = 4;
            } else {
                file.subCategory = "General Text";
                file.priority = 1;
            }
        } else {
            file.subCategory = "N/A";
            file.priority = 0;
        }
    }
}
