# TaskManager

TaskManager is a simple console-based task management application written in Java. It allows users to manage tasks, including adding, removing, listing, and saving tasks to a CSV file.

## Features

- **Add Task**: Add a new task with a description, due date, and importance.
- **Remove Task**: Remove a task by selecting its index from the list.
- **List Tasks**: Display a list of all tasks with their details.
- **Exit Program**: Save tasks to a CSV file and exit the application.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Apache Commons Lang library

### Usage

1. Clone the repository:

    ```bash
    git clone https://github.com/DanielKuzn/TaskManager.git
    cd TaskManager
    ```

2. Compile and run the program:

    ```bash
    javac -cp ".:path/to/commons-lang3-x.x.jar" TaskManager.java
    java -cp ".:path/to/commons-lang3-x.x.jar" pl.coderslab.TaskManager
    ```

### Commands

- To add a task: Enter `add` and follow the prompts.
- To remove a task: Enter `remove` and select the task number.
- To list all tasks: Enter `list`.
- To exit the program: Enter `exit`.

## File Format

The tasks are stored in a CSV file named `tasks.csv` with the following format:

task,due_date,importance(true/false)
Task 1,2024-01-15,true
Task 2,2024-01-20,false


## Contributing

Feel free to contribute to the project by opening issues or creating pull requests. Make sure to follow the [code of conduct](CODE_OF_CONDUCT.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
