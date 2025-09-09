public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;
    
    public AudioMixer() {
        this("StandardMix-8", 8, false);
    }
    
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);
    }
    
    public AudioMixer(String mixerModel, int numberOfChannels,
                     boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
    }
    
    public AudioMixer(String mixerModel, int numberOfChannels,
                     boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
        System.out.println("AudioMixer created: " + mixerModel + " with " + 
                          numberOfChannels + " channels");
    }
    
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }
    
    public void disconnectDevice(String deviceName) {
        for (int i = 0; i < deviceCount; i++) {
            if (connectedDevices[i].equals(deviceName)) {
                for (int j = i; j < deviceCount - 1; j++) {
                    connectedDevices[j] = connectedDevices[j + 1];
                }
                connectedDevices[deviceCount - 1] = null;
                deviceCount--;
                System.out.println("Disconnected: " + deviceName);
                return;
            }
        }
        System.out.println("Device not found: " + deviceName);
    }
    
    public void adjustVolume(double newVolume) {
        if (newVolume >= 0 && newVolume <= maxVolumeDecibels) {
            System.out.println("Volume set to: " + newVolume + " dB");
        } else {
            System.out.println("Invalid volume! Must be between 0 and " + 
                             maxVolumeDecibels + " dB");
        }
    }
    
    public void displayMixerStatus() {
        System.out.println();
        System.out.println("MIXER STATUS: " + mixerModel);
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
        System.out.println();
    }
    
    public String getMixerModel() {
        return mixerModel;
    }
    
    public int getNumberOfChannels() {
        return numberOfChannels;
    }
    
    public boolean hasBluetoothConnectivity() {
        return hasBluetoothConnectivity;
    }
    
    public double getMaxVolumeDecibels() {
        return maxVolumeDecibels;
    }
    
    public int getDeviceCount() {
        return deviceCount;
    }
    
    public static void main(String[] args) {
        System.out.println("MUSIC PRODUCTION STUDIO EQUIPMENT SYSTEM");
        System.out.println();
        
        System.out.println("1. Creating mixer with no-argument constructor:");
        AudioMixer mixer1 = new AudioMixer();
        mixer1.displayMixerStatus();
        
        System.out.println("2. Creating mixer with two-parameter constructor:");
        AudioMixer mixer2 = new AudioMixer("ProMix-12", 12);
        mixer2.displayMixerStatus();
        
        System.out.println("3. Creating mixer with three-parameter constructor:");
        AudioMixer mixer3 = new AudioMixer("StudioMaster-16", 16, true);
        mixer3.displayMixerStatus();
        
        System.out.println("4. Creating mixer with full constructor:");
        AudioMixer mixer4 = new AudioMixer("UltraMix-24", 24, true, 140.0);
        mixer4.displayMixerStatus();
        
        System.out.println("5. Connecting devices to mixer:");
        System.out.println("Testing device connections on ProMix-12:");
        mixer2.connectDevice("Electric Guitar");
        mixer2.connectDevice("Bass Guitar");
        mixer2.connectDevice("Keyboard");
        mixer2.connectDevice("Microphone 1");
        mixer2.connectDevice("Drum Kit");
        mixer2.displayMixerStatus();
        
        System.out.println("6. Testing channel capacity:");
        System.out.println("Trying to overfill StandardMix-8:");
        AudioMixer smallMixer = new AudioMixer();
        String[] instruments = {"Guitar", "Bass", "Drums", "Piano", "Violin", 
                               "Trumpet", "Saxophone", "Vocals", "Flute"};
        
        for (String instrument : instruments) {
            smallMixer.connectDevice(instrument);
        }
        smallMixer.displayMixerStatus();
        
        System.out.println("7. Device management operations:");
        System.out.println("Disconnecting and reconnecting devices:");
        mixer2.disconnectDevice("Bass Guitar");
        mixer2.connectDevice("Synthesizer");
        mixer2.disconnectDevice("NonExistent Device");
        mixer2.displayMixerStatus();
        
        System.out.println("8. Volume adjustment testing:");
        mixer4.adjustVolume(100.0);
        mixer4.adjustVolume(150.0);
        mixer4.adjustVolume(-10.0);
        mixer4.adjustVolume(140.0);
        
        System.out.println("9. Professional studio setup:");
        AudioMixer[] studioMixers = {
            new AudioMixer("MainConsole-32", 32, true, 160.0),
            new AudioMixer("MonitorMix-8", 8, false),
            new AudioMixer("EffectsMix-4", 4, true, 100.0),
            new AudioMixer()
        };
        
        String[][] studioSetup = {
            {"Lead Vocals", "Backup Vocals", "Electric Guitar", "Acoustic Guitar", 
             "Bass", "Kick Drum", "Snare", "Hi-Hat"},
            {"Monitor 1", "Monitor 2", "Headphones 1", "Headphones 2"},
            {"Reverb Send", "Delay Send", "Chorus Send"},
            {"Talkback Mic", "Click Track"}
        };
        
        System.out.println("Professional Studio Configuration:");
        for (int i = 0; i < studioMixers.length; i++) {
            System.out.println("Setting up " + studioMixers[i].getMixerModel() + ":");
            for (int j = 0; j < studioSetup[i].length && j < studioMixers[i].getNumberOfChannels(); j++) {
                studioMixers[i].connectDevice(studioSetup[i][j]);
            }
            studioMixers[i].displayMixerStatus();
        }
        
        System.out.println("CONSTRUCTOR CHAINING DEMONSTRATION:");
        System.out.println("No-argument -> calls three-parameter -> calls main constructor");
        System.out.println("Two-parameter -> calls three-parameter -> calls main constructor");
        System.out.println("Three-parameter -> calls main constructor");
        System.out.println("Main constructor -> initializes all fields");
        System.out.println();
        System.out.println("All constructor chains successfully demonstrated!");
    }
}