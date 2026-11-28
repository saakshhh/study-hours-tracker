# Study Hours Tracker

A simple Java command-line program that lets students record their study sessions and see how much time they're spending on each course.

## Why I Built This

As a college student, I sometimes spend hours studying for different classes without really keeping track of where that time goes. With assignments, exams, and other coursework throughout the week, it's easy to lose track of how much time I'm spending on each subject.

I wanted to build something simple that would help me record my study sessions and look back at how I spent my time during the week. Instead of making a complicated application, I decided to build a command-line tracker with a few basic features that I could use regularly.

## What It Does

The current version of Study Hours Tracker lets users:

- **Record study sessions:** Enter a course name, date, and duration in minutes.
- **Save study sessions:** Store records in a local CSV file so they aren't lost when the program closes.
- **View weekly summaries:** See total study time for the current week, including a breakdown by course.

The weekly summary covers Monday through Sunday. If multiple sessions are recorded for the same course, their durations are added together. Courses are displayed alphabetically, and study time is shown in hours and minutes.

The program also checks for invalid inputs, such as empty course names, future dates, and negative study durations.

## Tech Stack

- **Language:** Java (developed using OpenJDK 21)
- **Interface:** Command-line interface (CLI)
- **Libraries:** Java's built-in libraries, including `Scanner`, `LocalDate`, `HashMap`, `Collections`, and `java.nio.file`
- **Data Storage:** Local CSV file (`study_sessions.csv`)
- **Development Tools:** Visual Studio Code, Git, and GitHub

The project doesn't require any external libraries, APIs, or databases.

## Getting Started

### Requirements

You'll need a Java Development Kit (JDK 11 or newer) and a terminal to run the program. Git is needed if you're cloning the repository.

You can check your Java installation using:

```bash
java -version
javac -version
```

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/saakshhh/study-hours-tracker.git
cd study-hours-tracker
```

**2. Compile the Java program**

```bash
mkdir -p out
javac -d out src/StudyHoursTracker.java
```

**3. Run the program**

```bash
java -cp out StudyHoursTracker
```

Once the program starts, you'll see the main menu:

```text
Welcome to Study Hours Tracker!

=== Main Menu ===
1. Record Study Session
2. View Weekly Summary
3. Exit
Choose an option:
```

## How to Use It

### 1. Record a Study Session

Choose Option `1` from the main menu.

You'll be asked to enter the course name, date, and number of minutes studied.

For example:

```text
=== Record Study Session ===
Course name: CS 193
Date (YYYY-MM-DD): 2026-11-24
Minutes studied: 45

Study session recorded!
Course: CS 193
Date: 2026-11-24
Duration: 45 minutes
```

Dates should be entered in `YYYY-MM-DD` format. The program accepts today's date or an earlier date, but not a future date. Study durations must be positive whole numbers.

After recording a session, the program returns to the main menu so you can continue using it.

### 2. View the Weekly Summary

Choose Option `2` to see how much time you've spent studying during the current week.

Here's an example from my testing during November 23–29, 2026:

```text
=== Weekly Study Summary ===
Week: 2026-11-23 to 2026-11-29

Study Time by Course
--------------------------------
CS 193               1 hr 15 min
CS 240               1 hr 0 min
--------------------------------
Total                2 hr 15 min
```

The program automatically calculates the current week's dates, so the summary changes as the weeks go by. Sessions from previous weeks are saved but aren't included in the current weekly total.

If no sessions have been recorded for the current week, the program displays:

```text
No study sessions recorded this week.
```

### 3. Exit the Program

Choose Option `3` to close the program.

Your saved study sessions will still be available the next time you run it.

## How the Data Is Stored

Study sessions are saved in a CSV file named `study_sessions.csv`.

The file is created automatically when the first valid session is recorded. It is stored in the directory where the program is run.

For example:

```csv
course,date,minutes
CS 193,2026-11-24,45
CS 193,2026-11-25,30
CS 240,2026-11-25,60
```

Each new session is added to the file without overwriting previous records. When the program starts, it loads the existing sessions so they can be used in the weekly calculations.

The CSV file is included in `.gitignore` because each user will have their own study records.

## Project Structure

```text
study-hours-tracker/
├── src/
│   └── StudyHoursTracker.java
├── tests/
│   └── MVP_TEST_RESULTS.md
├── docs/
│   ├── Study Hours Tracker.drawio
│   ├── Study Hours Tracker.drawio.png
│   └── eisenhower-matrix.md
├── Eisenhower Matrix.md
├── System Diagram.png
├── .gitignore
└── README.md
```

The `src` folder contains the Java program, `tests` contains the testing documentation, and `docs` contains the original project planning files.

The repository also includes the final Eisenhower Matrix and System Diagram in its main directory.

## Testing

I tested the MVP using a separate temporary directory so that my existing study records wouldn't be affected.

Some of the cases I tested included:

- Starting the program without an existing CSV file.
- Recording multiple study sessions.
- Entering invalid dates, empty course names, and invalid study durations.
- Checking the weekly calculations for multiple courses.
- Closing and restarting the program to verify that saved records were loaded correctly.

The tests I completed produced the expected results, and I didn't encounter any crashes.

The full testing notes and results are available here:

[MVP Test Results](tests/MVP_TEST_RESULTS.md)

## Future Plans

Now that the basic tracker is working, there are a few additional features I'd like to explore:

- [ ] Add weekly study goals and track progress toward them.
- [ ] Allow users to edit or delete previously recorded sessions.
- [ ] Compare study hours across multiple weeks.
- [ ] Display simple text-based bar charts showing study time by course.

These features weren't necessary for the initial MVP, but they would make the tracker more useful over time.

## Project Documentation

- [Eisenhower Matrix](Eisenhower%20Matrix.md)
- [System Diagram](System%20Diagram.png)
- [Editable System Diagram](docs/Study%20Hours%20Tracker.drawio)
- [MVP Test Results](tests/MVP_TEST_RESULTS.md)

### System Flowchart

![Study Hours Tracker System Diagram](System%20Diagram.png)

## Author

Saksham Singh

GitHub: https://github.com/saakshhh