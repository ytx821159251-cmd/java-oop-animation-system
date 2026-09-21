# Java OOP Animation System

A Java Swing animation application demonstrating object-oriented programming, inheritance, nested object structures, tree models, event handling, and multithreaded animation.

## Project Overview

This project implements an interactive animation system where geometric shapes can move within a GUI using different movement paths.

The application allows users to:

- Create rectangle, square, and nested shapes
- Display shape relationships using a `JTree`
- Add and remove shape nodes dynamically
- Animate shapes using multiple movement patterns
- Manage parent-child relationships between nested shapes
- Update the tree model when the shape structure changes

## Technologies

- Java
- Java Swing
- AWT
- JTree / TreeModel
- Multithreading
- Object-Oriented Programming

## Object-Oriented Design

The project uses an inheritance hierarchy:

```text
Shape
└── RectangleShape
    ├── SquareShape
    └── NestedShape

Shape is the abstract superclass containing shared properties such as position, size, colour, movement behaviour, and parent relationships.

RectangleShape implements rectangle rendering, while SquareShape extends it with equal width and height.

NestedShape can contain other shapes, creating a hierarchical object structure.

Key Features
Shape Hierarchy

The application supports:

Rectangle shapes
Square shapes
Nested shapes

Nested shapes maintain collections of child shapes and support recursive object structures.

Movement Paths

Two movement behaviours are implemented:

BOUNCING
DOWN_RIGHT

Each shape contains a movement-path object that determines how its position changes during animation.

Tree Model

The animation viewer implements Java's TreeModel interface so the hierarchy of nested shapes can be displayed in a JTree.

The application supports:

Adding nodes
Removing nodes
Updating the tree dynamically
Parent-child relationships
Multithreaded Animation

Animation runs on a separate thread using Java's Runnable interface.

The animation loop repeatedly repaints the drawing panel while shapes update their positions.

Project Structure
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


Skills Demonstrated:
  Object-oriented programming
  Inheritance and polymorphism
  Abstract classes
  Composition
  Recursive data structures
  Java Swing GUI development
  Event-driven programming
  Tree models
  Multithreading
