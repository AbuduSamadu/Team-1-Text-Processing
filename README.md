# Team1 Test Processing
## Overview
The `Team1 Test Processing` project contains utilities and services for processing and manipulating data using regular expressions and collections. This project includes various classes and services that allow you to filter, sort, and manipulate `DataModel` objects, as well as perform regex-based operations.

## Features

- **RegexService**: Provides methods for handling regular expressions, including validation, searching, and replacing matches.
- **TextProcessor**: Wraps around the `RegexService` to offer a higher-level API for searching, replacing, and checking for exact regex matches in text.
- **CollectionsFormatter**: Provides utilities to filter and sort lists of `DataModel` objects based on keywords and properties such as name.

## Services

### RegexService
Handles regular expressions and provides the following functionalities:
- **handleOtherRegexPatterns**: Returns a string of matches for a given text and regex pattern.
- **validateRegex**: Checks if a regex pattern is valid.
- **findAllMatches**: Finds all matches of a regex pattern in a given text.
- **isExactMatch**: Checks if the entire text matches the given regex.
- **replaceAllMatches**: Replaces all matches of a regex pattern in the text with a specified replacement string.

### TextProcessor
Simplifies the interaction with `RegexService` for processing text data:
- **handleOtherRegexPatterns**: Handles other regex patterns, using `RegexService`.
- **search**: Searches for all matches of a regex pattern in the provided text.
- **replace**: Replaces all regex pattern matches in the text with a replacement.
- **exactMatch**: Checks if the provided text exactly matches the given regex.

### CollectionsFormatter
Provides utility methods for processing lists of `DataModel` objects:
- **filterByKeyword**: Filters a list of `DataModel` objects by keyword, matching either the name or value.
- **sortByName**: Sorts a list of `DataModel` objects by name, either in normal or reversed name order.

##  Requirements
* Java 21 or higher

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/AbuduSamadu/Team-1-Text-Processing.git
    ```

2. Navigate into the project directory:
    ```bash
    cd Team-1-Text-Processing
    ```

3. Build and run the project using your preferred IDE or build tool (Maven/Gradle).

## Text processing page
![image](https://github.com/user-attachments/assets/1f0d13ee-3c5d-4b26-a354-2519be3cfaa4)

## Data management page
![image](https://github.com/user-attachments/assets/41a65cd8-4862-4121-83d3-232d7d211852)




