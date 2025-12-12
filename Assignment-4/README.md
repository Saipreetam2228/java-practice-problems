# 🎵 Assignment 4: Mini MIDI Composer

Build a **console-based mini music composer** that plays short melodies using `javax.sound.midi`.

**Target:** 4–6 hours  
**Mode:** Individual or pairs

---

## 🎯 Learning Objectives (Mapped to Syllabus)

• Create and use classes/objects; design with interfaces & abstraction  
• Apply packages, access modifiers, and exceptions  
• Use arrays/collections to represent notes and sequences  
• Use threads for simple polyphony (optional stretch)

---

## 🎼 Functional Requirements

### 1. Domain Model  
(This is the way the class structure should look potentially)

o **Note**  
   - pitch (0–127)  
   - velocity (0–127)  
   - duration (ms)  
   - channel (0–15)

o **Instrument**  
   - program number (0–127)  
   - display name

o **TrackPart**  
   - a list of Notes to be played

o **Song** *(optional)*  
   - title  
   - bpm  
   - one or more TrackParts  

---

### 2. Playback API (Testable)

Define an **interface `NotePlayer`** with:

1. `void programChange(int channel, int program);`  
2. `void noteOn(int channel, int pitch, int velocity);`  
3. `void noteOff(int channel, int pitch);`  
4. `void sleepMillis(long ms) throws InterruptedException;`  
5. `void close();`  

Provide implementation:

### 3. MidiNotePlayer  
Real implementation using `javax.sound.midi`.

---

## 🎹 CLI Requirements

• **Menu:**  
  o Choose instrument (e.g., Acoustic Grand = 0, Nylon Guitar = 24)  
  o Choose a Note (provide at least three options; store the user selection)  
  o Play and exit  

• Display what’s playing (note name, channel, duration)

---

## 🚫 Error Handling

• Custom checked exception: **MusicException**  
• Validate pitch/velocity/ranges  
• Never allow the program to crash — handle all errors gracefully

---

## 📦 Packaging Structure

Root package:  
`edu.sssihl.midi` (main method goes here)

Subpackages:  
• `.core` (domain classes)  
• `.playback` (players, e.g., MidiNotePlayer)  
• `.songs` (preset tracks)  
• Main class inside root package  

---

## 🧱 Coding Standards (Strict)

• **SOLID Principles**
  - Interface Segregation (audio boundary)
  - Single Responsibility: SongPlayer vs CLI separate
  - Dependency Injection: inject `NotePlayer`

• **Javadoc** for all public classes/methods

---

## 📝 Grading Rubric (100 Marks)

• **Design & OOP (30)**  
  Interfaces, SRP, clean packages, Javadoc

• **Correctness (30)**  
  Playback works, validation, custom exceptions

• **Testing (20)**  
  JUnit tests, coverage, meaningful extra test cases

• **CLI & UX (10)**  
  Clear menu, intuitive flow, proper messages

• **Stretch (10)**  
  Polyphony, BPM timing, persistence, extra features

---

## ⚠️ Plagiarism & Monitoring Note

Submissions must be **original**.

Cite any referenced sources or classmates involved in discussions.

Your **internet usage will be monitored** by Bhaskaran Sir.  
Any access to external resources (beyond provided links) will result in losing eligibility for full marks.  
Email traffic will also be monitored for external help.

---

## 🎧 Focus Areas (Important Imports)

```java
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
