# Scoreboard

The scoreboard supports the following operations:

1. Start a new game, assuming an initial score of 0 - 0, and add it to the scoreboard. This operation requires the following parameters:
   - Home team
   - Away team

2. Update score: Update the score for a specific match (taken by id of the match) by providing the home team score and away team score.

3. Finish game currently in progress: Remove a match from the scoreboard.

4. Get a summary of games in progress: Get a list of matches in progress, ordered by their total score. If multiple matches have the same total score, they will be ordered based on the most recently started match in the scoreboard.

5. Exit: Exit the scoreboard.

## JDK Version

This project uses JDK 17.

## Test Cases

The following test cases are available in the `ScoreBoardTest` class:

- `testStartNewGame`: Test starting a new game and verifying the match details and initial scores.
- `testUpdateScore_ExistingMatch`: Test updating the score for an existing match and verifying the updated scores.
- `testUpdateScore_NonExistingMatch`: Test updating the score for a non-existing match and verifying that the match is not added.
- `testFinishGameInProgress`: Test finishing a game in progress and verifying that the match is removed from the scoreboard.
- `testGetSummaryOfGamesInProgress`: Test getting a summary of games in progress and verifying the order based on total score and start time.
