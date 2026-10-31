# Study Hours Tracker

A simple Java command-line program that helps students keep track of their study sessions and see how much time they're spending on each course.

## The Problem

As a college student, I often spend time studying for different classes without really keeping track of how much time I dedicate to each one. It's easy to lose track of those hours, especially when working on multiple assignments throughout the week. I wanted to create something simple that would help me record my study time and get a better idea of how I'm managing it.

## Project Goal

The goal of this project is to build a basic study tracker that lets students record their study sessions and review their weekly study hours. I want to keep it straightforward, with a few useful features that work reliably rather than adding unnecessary complexity.

## MVP Scope

For the first version, I'm focusing on three main features:

1. **Record Study Sessions:** Users can enter the course name, date, and number of minutes they spent studying.
2. **Save and Load Sessions:** The program saves each session to a local CSV file and loads previous records when it starts.
3. **Weekly Study Summary:** Users can see their total study hours for the current week, along with how much time they spent on each course.

These features will make up the initial working version of the project. I'll focus on getting them working properly before adding anything else.

## How It Works

The program will run through a simple menu in the terminal. Users will have three options:

1. Record a new study session.
2. View their weekly study summary.
3. Exit the program.

When adding a session, the user will enter the course name, date, and duration. The program will check that the information is valid before saving it to a CSV file.

When viewing the weekly summary, the program will read the saved sessions, calculate the total study time for the current week, and show a breakdown for each course.

### System Flowchart

![Study Hours Tracker Flowchart](docs/Study%20Hours%20Tracker.drawio.png)

## Tech Stack

- **Language:** Java
- **Libraries:** Java's built-in libraries
  - `Scanner` for reading user input
  - `LocalDate` for working with dates
  - `java.nio.file` for saving and reading CSV files
  - `HashMap` for organizing study hours by course
- **Data Storage:** A local CSV file (`study_sessions.csv`)
- **Interface:** Command-line interface (CLI)
- **Development Tools:** Visual Studio Code, Git, and GitHub
- **External APIs:** None. All study sessions will be entered manually.

## Project Structure

- `src/` - Java source code
- `tests/` - Testing files and notes
- `docs/` - Eisenhower Matrix and system flowchart
- `README.md` - Project information and documentation

## Setup and Usage

The project is currently in the planning stage. Once the main features are implemented, I'll add instructions on how to compile and run the program, along with examples of how to use it.

## Future Plans

Once the basic tracker is working, there are a few additional features I'd like to explore:

- [ ] Let users set weekly study goals and see how close they are to reaching them.
- [ ] Add an option to edit or delete previously recorded sessions.
- [ ] Show study trends across multiple weeks to compare progress.
- [ ] Include simple text-based bar charts to make the weekly summaries easier to read.

These aren't part of the initial MVP, but they're features I can work on once the core functionality is complete.

## Project Documentation

- [Eisenhower Matrix](docs/eisenhower-matrix.md)
- [Editable System Diagram](docs/Study%20Hours%20Tracker.drawio)

## Author

Saksham Singh

GitHub: https://github.com/saakshhh
