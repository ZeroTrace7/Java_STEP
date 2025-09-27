class Artwork {
    protected String artworkId;
    protected String title;
    protected String artist;
    protected double price;
    protected boolean isOnDisplay;
    
    public Artwork(String artworkId, String title, String artist, double price) {
        this.artworkId = artworkId;
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.isOnDisplay = false;
    }
    
    public void displayInfo() {
        String status = isOnDisplay ? "On Display" : "In Storage";
        System.out.println("Artwork: " + title + " by " + artist);
        System.out.println("ID: " + artworkId + " | Price: $" + price + " | Status: " + status);
    }
    
    public void setOnDisplay(boolean display) {
        isOnDisplay = display;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getArtworkId() {
        return artworkId;
    }
}

class Painting extends Artwork {
    private String brushTechnique;
    private String colorPalette;
    private String frameType;
    private String dimensions;
    
    public Painting(String artworkId, String title, String artist, double price, 
                   String brushTechnique, String colorPalette, String frameType) {
        super(artworkId, title, artist, price);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frameType = frameType;
        this.dimensions = "24x36 inches";
    }
    
    public void showPaintingDetails() {
        System.out.println("Painting Details:");
        System.out.println("Brush technique: " + brushTechnique);
        System.out.println("Color palette: " + colorPalette);
        System.out.println("Frame: " + frameType);
        System.out.println("Dimensions: " + dimensions);
    }
    
    public String getFrameType() {
        return frameType;
    }
    
    public String getBrushTechnique() {
        return brushTechnique;
    }
}

class Sculpture extends Artwork {
    private String material;
    private String dimensions;
    private String lightingRequirement;
    private double weight;
    
    public Sculpture(String artworkId, String title, String artist, double price,
                    String material, String dimensions, String lightingRequirement) {
        super(artworkId, title, artist, price);
        this.material = material;
        this.dimensions = dimensions;
        this.lightingRequirement = lightingRequirement;
        this.weight = 150.0;
    }
    
    public void showSculptureDetails() {
        System.out.println("Sculpture Details:");
        System.out.println("Material: " + material);
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Weight: " + weight + " lbs");
        System.out.println("Lighting: " + lightingRequirement);
    }
    
    public String getLightingRequirement() {
        return lightingRequirement;
    }
    
    public String getMaterial() {
        return material;
    }
}

class DigitalArt extends Artwork {
    private String resolution;
    private String fileFormat;
    private boolean hasInteractiveElements;
    private String displayTechnology;
    
    public DigitalArt(String artworkId, String title, String artist, double price,
                     String resolution, String fileFormat, boolean hasInteractiveElements) {
        super(artworkId, title, artist, price);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.hasInteractiveElements = hasInteractiveElements;
        this.displayTechnology = "4K Monitor";
    }
    
    public void showDigitalDetails() {
        String interactive = hasInteractiveElements ? "Yes" : "No";
        System.out.println("Digital Art Details:");
        System.out.println("Resolution: " + resolution);
        System.out.println("Format: " + fileFormat);
        System.out.println("Interactive: " + interactive);
        System.out.println("Display: " + displayTechnology);
    }
    
    public boolean hasInteractiveElements() {
        return hasInteractiveElements;
    }
    
    public String getResolution() {
        return resolution;
    }
}

class Photography extends Artwork {
    private String cameraSettings;
    private String editingSoftware;
    private String printSpecification;
    private String paperType;
    
    public Photography(String artworkId, String title, String artist, double price,
                      String cameraSettings, String editingSoftware, String printSpecification) {
        super(artworkId, title, artist, price);
        this.cameraSettings = cameraSettings;
        this.editingSoftware = editingSoftware;
        this.printSpecification = printSpecification;
        this.paperType = "Fine Art Paper";
    }
    
    public void showPhotographyDetails() {
        System.out.println("Photography Details:");
        System.out.println("Camera: " + cameraSettings);
        System.out.println("Editing: " + editingSoftware);
        System.out.println("Print: " + printSpecification);
        System.out.println("Paper: " + paperType);
    }
    
    public String getCameraSettings() {
        return cameraSettings;
    }
    
    public String getPrintSpecification() {
        return printSpecification;
    }
}

public class ArtGalleryApp {
    
    public static void planExhibition(Artwork[] artworks) {
        System.out.println("Exhibition Planning - Specific Requirements:");
        System.out.println();
        
        for (int i = 0; i < artworks.length; i++) {
            Artwork artwork = artworks[i];
            System.out.println("Planning for: " + artwork.getTitle());
            
            if (artwork instanceof Painting) {
                Painting painting = (Painting) artwork;
                painting.showPaintingDetails();
                System.out.println("Wall space and " + painting.getFrameType() + " mounting needed");
            }
            else if (artwork instanceof Sculpture) {
                Sculpture sculpture = (Sculpture) artwork;
                sculpture.showSculptureDetails();
                System.out.println("Floor space and " + sculpture.getLightingRequirement() + " setup needed");
            }
            else if (artwork instanceof DigitalArt) {
                DigitalArt digital = (DigitalArt) artwork;
                digital.showDigitalDetails();
                if (digital.hasInteractiveElements()) {
                    System.out.println("Interactive setup and visitor guidance required");
                }
            }
            else if (artwork instanceof Photography) {
                Photography photo = (Photography) artwork;
                photo.showPhotographyDetails();
                System.out.println("Professional lighting and print display setup needed");
            }
            else {
                System.out.println("Standard display setup");
            }
            
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Digital Art Gallery Management");
        System.out.println();
        
        Artwork[] collection = new Artwork[4];
        
        collection[0] = new Painting("P001", "Sunset Dreams", "Alice Johnson", 5000, 
                                   "Oil on Canvas", "Warm Tones", "Gold Frame");
        collection[1] = new Sculpture("S001", "Modern Form", "Bob Smith", 12000,
                                    "Bronze", "6ft x 3ft x 2ft", "Spotlighting");
        collection[2] = new DigitalArt("D001", "Interactive City", "Carol Davis", 3000,
                                     "4K UHD", "MP4", true);
        collection[3] = new Photography("PH001", "Nature's Light", "David Wilson", 2500,
                                      "Canon 5D, f/2.8, 1/60s", "Lightroom", "Large Format");
        
        System.out.println("Gallery Collection:");
        for (int i = 0; i < collection.length; i++) {
            collection[i].displayInfo();
            collection[i].setOnDisplay(true);
            System.out.println();
        }
        
        planExhibition(collection);
        
        System.out.println("Safe Downcasting Benefits:");
        System.out.println("- Check artwork type before accessing specific features");
        System.out.println("- Access specialized properties for exhibition planning");
        System.out.println("- Handle mixed artwork collections safely");
        System.out.println("- Provide type-specific setup requirements");
    }
}