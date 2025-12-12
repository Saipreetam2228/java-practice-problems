package edu.sssihl.midi;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * MidiComposer - A one-file console-based mini MIDI composer.
 *
 * Single-file implementation for the Assignment: Mini MIDI Composer.
 *
 * Structure (all as nested static classes to keep one-file):
 * - Domain: Note, Instrument, TrackPart, Song
 * - Playback API: NotePlayer (interface), MidiNotePlayer (implementation)
 * - Exceptions: MusicException (checked)
 * - App: SongPlayer (plays TrackPart/Song with injected NotePlayer), CLI (user interface)
 *
 * Usage:
 *  - Run main() -> CLI menu to create notes, set instrument, and play.
 *
 * Important:
 *  - This code uses javax.sound.midi and will attempt to open the default Synthesizer.
 *  - Validate ranges: pitch 0-127, velocity 0-127, channel 0-15, program 0-127.
 *
 * Note: This is intentionally a single-file "one-shot" demo for quick testing/learning.
 */
public class MidiComposer {

    /* ----------------------------- DOMAIN ----------------------------- */

    /**
     * Simple immutable Note representation.
     */
    public static class Note {
        public final int pitch;     // 0 - 127
        public final int velocity;  // 0 - 127
        public final long duration; // milliseconds
        public final int channel;   // 0 - 15

        public Note(int pitch, int velocity, long duration, int channel) {
            this.pitch = pitch;
            this.velocity = velocity;
            this.duration = duration;
            this.channel = channel;
        }

        @Override
        public String toString() {
            return String.format("Note[p=%d, vel=%d, dur=%dms, ch=%d]", pitch, velocity, duration, channel);
        }
    }

    /**
     * Instrument - simple holder for program number and display name.
     */
    public static class Instrument {
        public final int program; // 0-127
        public final String name;

        public Instrument(int program, String name) {
            this.program = program;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("%s (%d)", name, program);
        }
    }

    /**
     * TrackPart - a sequence of notes.
     */
    public static class TrackPart {
        private final List<Note> notes = new ArrayList<>();
        public void add(Note n) { notes.add(n); }
        public List<Note> getNotes() { return notes; }
        public boolean isEmpty() { return notes.isEmpty(); }
        @Override
        public String toString() { return notes.toString(); }
    }

    /**
     * Song - optional container of title, bpm and trackparts.
     */
    public static class Song {
        public final String title;
        public final int bpm;
        private final List<TrackPart> parts = new ArrayList<>();

        public Song(String title, int bpm) {
            this.title = title;
            this.bpm = bpm;
        }

        public void addPart(TrackPart p) { parts.add(p); }
        public List<TrackPart> getParts() { return parts; }
    }

    /* ----------------------------- EXCEPTIONS ----------------------------- */

    /**
     * MusicException - custom checked exception for music-related validation.
     */
    public static class MusicException extends Exception {
        public MusicException(String message) { super(message); }
    }

    /* ----------------------------- PLAYBACK API ----------------------------- */

    /**
     * NotePlayer - playback API boundary. Interface segregation: this is small and testable.
     */
    public interface NotePlayer {
        void programChange(int channel, int program) throws MusicException;
        void noteOn(int channel, int pitch, int velocity) throws MusicException;
        void noteOff(int channel, int pitch) throws MusicException;
        void sleepMillis(long ms) throws InterruptedException;
        void close();
    }

    /**
     * MidiNotePlayer - real implementation using javax.sound.midi.
     * Uses a Synthesizer and MidiChannel[].
     */
    public static class MidiNotePlayer implements NotePlayer {
        private Synthesizer synth;
        private MidiChannel[] channels;
        private boolean open = false;

        public MidiNotePlayer() throws MusicException {
            try {
                synth = MidiSystem.getSynthesizer();
                synth.open();
                open = true;
                channels = synth.getChannels();
                // Optional: load predefined instruments from soundbank if needed.
            } catch (MidiUnavailableException e) {
                throw new MusicException("MIDI Synthesizer unavailable: " + e.getMessage());
            }
        }

        private void validateChannel(int channel) throws MusicException {
            if (!open) throw new MusicException("Synthesizer is closed.");
            if (channel < 0 || channel >= channels.length) {
                throw new MusicException("Channel out of range. Valid 0.."+(channels.length-1));
            }
        }

        @Override
        public void programChange(int channel, int program) throws MusicException {
            validateChannel(channel);
            if (program < 0 || program > 127) throw new MusicException("Program must be 0..127");
            channels[channel].programChange(program);
        }

        @Override
        public void noteOn(int channel, int pitch, int velocity) throws MusicException {
            validateChannel(channel);
            if (pitch < 0 || pitch > 127) throw new MusicException("Pitch must be 0..127");
            if (velocity < 0 || velocity > 127) throw new MusicException("Velocity must be 0..127");
            channels[channel].noteOn(pitch, velocity);
        }

        @Override
        public void noteOff(int channel, int pitch) throws MusicException {
            validateChannel(channel);
            if (pitch < 0 || pitch > 127) throw new MusicException("Pitch must be 0..127");
            channels[channel].noteOff(pitch);
        }

        @Override
        public void sleepMillis(long ms) throws InterruptedException {
            Thread.sleep(ms);
        }

        @Override
        public void close() {
            if (synth != null && open) {
                synth.close();
                open = false;
            }
        }
    }

    /* ----------------------------- SONG PLAYER ----------------------------- */

    /**
     * SongPlayer - plays a TrackPart or Song using an injected NotePlayer (DI).
     */
    public static class SongPlayer {
        private final NotePlayer player;

        public SongPlayer(NotePlayer player) {
            this.player = player;
        }

        /**
         * Play a TrackPart sequentially.
         */
        public void playTrackPart(TrackPart part) {
            for (Note n : part.getNotes()) {
                try {
                    System.out.printf("Playing: pitch=%d vel=%d dur=%d ch=%d%n",
                            n.pitch, n.velocity, n.duration, n.channel);
                    player.noteOn(n.channel, n.pitch, n.velocity);
                    player.sleepMillis(n.duration);
                    player.noteOff(n.channel, n.pitch);
                    // Small gap between notes to separate them slightly
                    player.sleepMillis(50);
                } catch (MusicException me) {
                    System.err.println("Playback error: " + me.getMessage());
                } catch (InterruptedException ie) {
                    System.err.println("Playback interrupted.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        /**
         * Play a Song by playing each TrackPart sequentially.
         * (Polyphony stretch: not implemented here; sequential playback only).
         */
        public void playSong(Song song) {
            System.out.printf("Playing song: %s at %d BPM%n", song.title, song.bpm);
            for (TrackPart p : song.getParts()) playTrackPart(p);
        }
    }

    /* ----------------------------- CLI ----------------------------- */

    /**
     * CLI - simple console UI to create instruments, notes and play them.
     */
    public static class CLI {
        private final Scanner scanner = new Scanner(System.in);

        /**
         * Run the interactive menu.
         */
        public void run() {
            System.out.println("=== Mini MIDI Composer ===");
            MidiNotePlayer midi = null;
            try {
                midi = new MidiNotePlayer();
            } catch (MusicException me) {
                System.err.println("Cannot start MIDI: " + me.getMessage());
                return;
            }
            NotePlayer player = midi;
            SongPlayer songPlayer = new SongPlayer(player);

            try {
                boolean exit = false;
                Instrument currentInstrument = new Instrument(0, "Acoustic Grand Piano");
                TrackPart userPart = new TrackPart();

                while (!exit) {
                    printMenu(currentInstrument, userPart);
                    int choice = safeIntInput("Choose option: ");
                    switch (choice) {
                        case 1:
                            currentInstrument = chooseInstrument();
                            try {
                                player.programChange(0, currentInstrument.program);
                                System.out.println("Instrument changed: " + currentInstrument);
                            } catch (MusicException me) {
                                System.err.println("Instrument set failed: " + me.getMessage());
                            }
                            break;
                        case 2:
                            Note n = createNoteInteractively();
                            if (n != null) {
                                userPart.add(n);
                                System.out.println("Added: " + n);
                            }
                            break;
                        case 3:
                            if (userPart.isEmpty()) {
                                System.out.println("No notes added. Add notes first.");
                            } else {
                                System.out.println("Playing your TrackPart...");
                                songPlayer.playTrackPart(userPart);
                                System.out.println("Playback finished.");
                            }
                            break;
                        case 4:
                            if (!userPart.isEmpty()) {
                                System.out.println("Current notes: " + userPart);
                            } else {
                                System.out.println("No notes in current TrackPart.");
                            }
                            break;
                        case 5:
                            userPart = new TrackPart();
                            System.out.println("TrackPart cleared.");
                            break;
                        case 6:
                            // Demo: preset melody
                            System.out.println("Playing demo melody (C major arpeggio) on channel 0...");
                            TrackPart demo = demoArpeggio();
                            songPlayer.playTrackPart(demo);
                            break;
                        case 0:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }

            } finally {
                player.close();
                System.out.println("MIDI closed. Goodbye.");
            }
        }

        private void printMenu(Instrument instr, TrackPart part) {
            System.out.println("\nMenu:");
            System.out.println("1) Choose instrument (current: " + instr + ")");
            System.out.println("2) Add a note to TrackPart");
            System.out.println("3) Play TrackPart");
            System.out.println("4) Show TrackPart notes");
            System.out.println("5) Clear TrackPart");
            System.out.println("6) Play demo melody");
            System.out.println("0) Exit");
        }

        private Instrument chooseInstrument() {
            System.out.println("Select Instrument (enter program number 0-127). Common values:");
            System.out.println("0 = Acoustic Grand Piano");
            System.out.println("24 = Nylon Guitar");
            System.out.println("40 = Violin");
            System.out.println("56 = Trumpet");
            int program = safeIntInput("Enter program number: ");
            if (program < 0 || program > 127) {
                System.out.println("Invalid program. Using 0.");
                program = 0;
            }
            String name = "Instrument " + program;
            switch (program) {
                case 0: name = "Acoustic Grand Piano"; break;
                case 24: name = "Nylon Guitar"; break;
                case 40: name = "Violin"; break;
                case 56: name = "Trumpet"; break;
            }
            return new Instrument(program, name);
        }

        private Note createNoteInteractively() {
            try {
                int pitch = safeIntInput("Enter pitch (0-127) e.g., 60 for middle C: ");
                if (pitch < 0 || pitch > 127) { System.out.println("Pitch out of range."); return null; }
                int velocity = safeIntInput("Enter velocity (0-127): ");
                if (velocity < 0 || velocity > 127) { System.out.println("Velocity out of range."); return null; }
                long duration = safeLongInput("Enter duration ms (100 - 5000): ");
                if (duration <= 0) { System.out.println("Invalid duration."); return null; }
                int channel = safeIntInput("Enter channel (0-15): ");
                if (channel < 0 || channel > 15) { System.out.println("Channel out of range."); return null; }
                return new Note(pitch, velocity, duration, channel);
            } catch (InputMismatchException ime) {
                System.out.println("Input error: " + ime.getMessage());
                scanner.nextLine(); // consume
                return null;
            }
        }

        private TrackPart demoArpeggio() {
            TrackPart tp = new TrackPart();
            // C major arpeggio on channel 0
            tp.add(new Note(60, 100, 300, 0)); // C4
            tp.add(new Note(64, 100, 300, 0)); // E4
            tp.add(new Note(67, 100, 300, 0)); // G4
            tp.add(new Note(72, 100, 600, 0)); // C5
            return tp;
        }

        private int safeIntInput(String prompt) {
            while (true) {
                System.out.print(prompt);
                try {
                    String line = scanner.nextLine().trim();
                    return Integer.parseInt(line);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid integer.");
                }
            }
        }

        private long safeLongInput(String prompt) {
            while (true) {
                System.out.print(prompt);
                try {
                    String line = scanner.nextLine().trim();
                    return Long.parseLong(line);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid long integer (milliseconds).");
                }
            }
        }
    }

    /* ----------------------------- MAIN ----------------------------- */

    /**
     * Main entry point. Launches the CLI.
     */
    public static void main(String[] args) {
        // Print a short usage header and start CLI.
        CLI cli = new CLI();
        cli.run();
    }
}
