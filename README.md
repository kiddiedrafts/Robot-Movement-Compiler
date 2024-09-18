# Robot Movement Compiler

## Overview

This project is a **Robot Movement Compiler** designed to interpret a custom language for defining robot movements. The compiler processes the input commands, tokenizes them, and parses them into executable robot instructions.

The compiler supports both 2D and 3D environments, providing real-time graphical visualization of the robot’s movements. It uses a custom grammar to validate commands, ensuring they adhere to the defined syntax and semantics before execution.

The system is designed with robust error handling, efficiently detecting and addressing issues such as boundary violations, collisions with obstacles, and invalid commands. It also accurately tracks the robot’s position, orientation, and the distance traveled, offering a reliable simulation framework for robot navigation and motion planning.

## Features

- **Lexical Analysis**: Uses a lexer to break down the input into tokens.
- **Parsing**: Implements a parser to analyze the token structure based on the grammar.
- **Custom Grammar**: Includes a unique grammar defined in the `ProjectGrammar.pdf` file to control robot movements.
- **Modular Design**: The project is structured with separate components for lexing, parsing, and the main compiler logic.

## Project Structure

```bash
/src/                  # Contains source code
  ├── lexer/           # Tokenizing the input
  ├── parser/          # Analyzing token structure
  └── main/            # Main compiler logic

/out/                  # Contains compiled class files

ProjectGrammar.pdf     # Describes formal grammar used by the compiler
```

## Getting Started

### Prerequisites

* **Java Development Kit (JDK)**: Ensure that you have the latest version of JDK installed.

### How to Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd Robot-Movement-Compiler
   ```

3. Compile the project:
   ```bash
   javac -d out/ src/**/*.java
   ```

4. Run the main class:
   ```bash
   java -cp out/ main.Main <input-file>
   ```

Replace <input-file> with the path to the file containing robot movement commands.


## Example Input and Output
Here's an example of the input file that the compiler accepts:
```
Lx: -3
Ux: 2
Ly: 1
Uy: 4
Ox: 0
Oy: 2
N: 3
-2
2
-2
4
1
4
beGIn􀀀west WEST􀀀􀀀north// comment1􀀀
north􀀀north􀀀􀀀west􀀀East􀀀east􀀀south
{
comment2
}
east􀀀enD
```
Here's the output of the example:
```
(0, 2)
(-1, 2)
Error in instr 2
(-1, 3)
(-1, 4)
Error in instr 5
Error in instr 6
(0, 4)
Error in instr 8
(0, 3)
(1, 3)
L = 6
D = 1.4142
```

## Contribution
If you'd like to contribute to this project, feel free to open a pull request or report issues in the issue tracker.
