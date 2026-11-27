# Study Hours Tracker - MVP Test Results

## 1. Overview

I tested the Study Hours Tracker to make sure the main features were working correctly before moving on to additional features. I mainly focused on recording study sessions, saving and loading data from a CSV file, calculating weekly study hours, and handling invalid user inputs.

I ran the tests in a separate temporary directory so that my existing study records wouldn't be affected.

## 2. Test Cases and Results

| Test Case | Expected Result | Actual Result | Status |
|---|---|---|---|
| Start without a CSV file | Program should start normally | Program opened without any errors | Passed |
| View an empty weekly summary | Show a message when no sessions exist | Displayed "No study sessions recorded this week." | Passed |
| Empty course name | Reject an empty course name | Displayed "Course name cannot be empty." | Passed |
| Invalid date | Reject an incorrectly formatted or invalid date | Rejected 2026-15-45 | Passed |
| Future date | Prevent users from recording future study sessions | Rejected 2028-09-24 | Passed |
| Nonnumeric study duration | Reject letters entered instead of minutes | Rejected "abc" | Passed |
| Negative study duration | Reject negative minutes | Rejected -30 | Passed |
| Zero study duration | Reject a duration of zero | Rejected 0 | Passed |
| Comma in course name | Reject unsupported CSV characters | Rejected "cs,192" | Passed |
| Invalid menu selection | Display an error without crashing | Displayed the invalid option message | Passed |
| Record a valid session | Save the entered study details | Successfully recorded CS 193 for 45 minutes | Passed |
| Record multiple sessions | Save all sessions without overwriting earlier records | All three sessions appeared in the CSV file | Passed |
| Weekly calculation by course | Combine study time for the same course | CS 193: 75 minutes, CS 240: 60 minutes | Passed |
| Weekly total calculation | Display the correct overall total | Displayed 2 hr 15 min | Passed |
| CSV file storage | Save course, date, and minutes correctly | All three records were saved correctly | Passed |
| Load sessions after restarting | Load previously saved records | Displayed "Loaded 3 saved study session(s)." | Passed |
| Weekly summary after restarting | Previous totals should remain unchanged | Displayed the same course totals and overall total | Passed |
| Exit program | Exit normally without crashing | Displayed "Goodbye!" and closed | Passed |

## 3. Weekly Summary Testing

To test the weekly calculations, I recorded three study sessions for the week of November 23–29, 2026.

| Course | Date | Minutes Studied |
|---|---|---:|
| CS 193 | 2026-11-24 | 45 |
| CS 193 | 2026-11-25 | 30 |
| CS 240 | 2026-11-25 | 60 |

After recording these sessions, I selected Option 2 to view the weekly summary.

The program correctly combined both CS 193 sessions into 75 minutes and displayed the total study time as 135 minutes, or 2 hours and 15 minutes.

The final summary showed:

- **CS 193:** 1 hr 15 min
- **CS 240:** 1 hr 0 min
- **Total:** 2 hr 15 min

The courses also appeared in alphabetical order, and the correct weekly date range was displayed.

## 4. CSV Storage and Persistence

I checked the `study_sessions.csv` file after recording the three sessions.

The file contained:

```csv
course,date,minutes
CS 193,2026-11-24,45
CS 193,2026-11-25,30
CS 240,2026-11-25,60
```

I then closed and restarted the program. It displayed "Loaded 3 saved study session(s)."

I selected the weekly summary again and confirmed that the course totals and overall study time were unchanged. This showed that the program was reading the previously saved records rather than losing them when it closed.

## 5. Input Validation and Error Handling

I also tested several invalid inputs to check how the program would respond.

For example, entering an invalid date such as `2026-15-45` displayed an error message and returned to the main menu. The program also rejected negative study durations, zero minutes, empty course names, and nonnumeric inputs.

One thing I noticed was that the program returned to the main menu after invalid input rather than crashing or closing unexpectedly. This made it possible to try recording another session without restarting the application.

## 6. Final Observations

All the test cases I completed produced the expected results, and I didn't encounter any crashes during testing.

The main MVP features are working as intended. Users can record study sessions, save their records locally, and view weekly summaries broken down by course.

I also confirmed that previously saved sessions remain available after restarting the program. With the basic functionality tested, the next step is to finish the README documentation and work on an additional feature from the project roadmap.