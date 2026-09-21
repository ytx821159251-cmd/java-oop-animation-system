# Java OOP Animation System

A Java Swing animation application demonstrating **object-oriented programming, inheritance, nested object structures, tree models, event handling, and multithreaded animation**.

## Overview

This project implements an interactive animation system where geometric shapes move within a graphical user interface using different movement behaviours.

The application supports hierarchical shape structures, allowing shapes to be nested inside other shapes and displayed through a Java `JTree`.

Users can:

- Create rectangle, square, and nested shapes
- Display shape relationships using a `JTree`
- Add and remove shape nodes dynamically
- Animate shapes using different movement paths
- Manage parent-child relationships between nested shapes
- Automatically update the tree when the shape hierarchy changes

## Technologies

- Java
- Java Swing
- Java AWT
- JTree / TreeModel
- Multithreading
- Object-Oriented Programming

## Object-Oriented Design

The application uses the following inheritance structure:

```text
Shape
└── RectangleShape
    ├── SquareShape
    └── NestedShape
```

### `Shape`

`Shape` is the abstract superclass for all shapes.

It defines shared properties and behaviour including:

- Position
- Width and height
- Fill and border colour
- Movement behaviour
- Shape labels
- Parent relationships

### `RectangleShape`

`RectangleShape` extends `Shape` and implements rectangle rendering using Java AWT.

### `SquareShape`

`SquareShape` extends `RectangleShape` and ensures that the width and height remain equal.

### `NestedShape`

`NestedShape` extends `RectangleShape` and maintains a collection of child `Shape` objects.

This allows the program to create hierarchical and recursively nested shape structures.

## Key Features

### Shape Hierarchy

The system supports three shape types:

- `RECTANGLE`
- `SQUARE`
- `NESTED`

Nested shapes can contain other shapes, creating parent-child relationships between objects.

### Movement Paths

Two movement behaviours are implemented:

- `BOUNCING`
- `DOWN_RIGHT`

Each shape contains a movement-path object that determines how its position changes during the animation.

### Tree Model

`AnimationViewer` implements Java's `TreeModel` interface so that the hierarchy of nested shapes can be displayed using a `JTree`.

The tree model supports:

- Adding child nodes
- Removing child nodes
- Finding parent-child relationships
- Updating the GUI when the hierarchy changes

### Event Handling

The GUI uses Java event listeners to handle user interaction.

Users can:

- Select a shape type
- Select a movement path
- Add nodes
- Remove nodes

### Multithreaded Animation

`AnimationViewer` also implements the `Runnable` interface.

The animation runs on a separate thread that continuously:

1. Updates shape positions
2. Repaints the animation panel
3. Pauses briefly between frames

## Project Structure

```text
java-oop-animation-system/
│
├── README.md
│
└── src/
    ├── A3.java
    ├── AnimationViewer.java
    ├── Shape.java
    ├── RectangleShape.java
    ├── SquareShape.java
    ├── NestedShape.java
    ├── ShapeType.java
    └── PathType.java
```

## Source Files

| File | Purpose |
|---|---|
| `A3.java` | Main application window and GUI controls |
| `AnimationViewer.java` | Animation rendering, tree model, and animation thread |
| `Shape.java` | Abstract superclass for all shapes |
| `RectangleShape.java` | Rectangle implementation |
| `SquareShape.java` | Square implementation |
| `NestedShape.java` | Hierarchical shape containing child shapes |
| `ShapeType.java` | Defines available shape types |
| `PathType.java` | Defines available movement paths |

## Skills Demonstrated

- Object-oriented programming
- Inheritance
- Polymorphism
- Abstract classes
- Composition
- Recursive object structures
- Java Swing GUI development
- Event-driven programming
- Tree data structures
- Java `TreeModel`
- Multithreading
- Java AWT graphics

## How to Run

Compile the Java source files:

```bash
javac src/*.java
```

Run the main application:

```bash
java -cp src A3
```

## Repository

This repository contains the Java source code for the complete animation system.
