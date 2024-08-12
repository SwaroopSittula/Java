# Java

This project is documentation of Java Concepts. 



## Table of Contents

- [List of Chapters](#list-of-chapters)
- [Environment Setup](#environment-setup)
- [References](#references)



## List of Chapters

<details>
<summary>

### 1. Fundamentals of Java
</summary>

- [Introduction to Java]
- Java Variables and Keywords
- [Data Types](src/main/java/DataTypes)
  - [Java Data Types Classification](src/main/java/DataTypes/DataTypes.ipynb)
    - Primitive
    - Non-Primitive
  - [Primitive Data Types vs Wrapper Classes](src/main/java/DataTypes/PrimitiveVsWrapper.ipynb)
    - Primitive Data Types vs. Wrapper Classes
    - Autoboxing and Unboxing
    - Wrapper Classes Advantages
  - [Type Conversion](src/main/java/DataTypes/TypeConversion.md)
  - [Abstract Data Types]()
- [Basic I/O in Java]
  - Input
  - [Output](src/main/java/JavaBasicIO/Output.ipynb)
- [Java Operators](src/main/java/Operators/Operators.ipynb)
- [Java Control Statements]
- [Methods in Java]
  - [Java Variable Arguments](src/main/java/JavaMethods/Varargs.ipynb)
</details>


<details>
<summary>

### 2. Basic Data Structures
</summary>

[//]: # (Basic intro + self implementations)
- Data Structures
  - Introduction
  - Strings
  - Arrays
  - Linked Lists
  - Stacks & Queues
</details>


<details>
<summary>

### 3. Object-Oriented Programming (OOPs)
</summary>

- OOPs
</details>


<details>
<summary>

### 4. Advanced Java
</summary>

- [JDBC](src/main/java/JDBC)
  - Introduction
- [Design Patterns](src/main/java/DesignPatterns/DesignPatterns.md)
    - Types of Design Patterns
    - List of Design Patterns
- [Java Build Tools](src/main/java/JavaBuildTools/JavaBuildTools.md)
- [Java Testing Tools]
  - Test Automation Frameworks
- [Java Frameworks]
  - Spring Boot
  - Hibernate
</details>


<details>
<summary>

### Others
</summary>

- [Streams](src/main/java/Streams/Streams.ipynb)
- [Java Reflection](src/main/java/JavaReflection)
- Logging
  - [MDC](src/main/java/Logging/MDC.ipynb)
- [Misc]
  - [Cryptographic Hash Functions](/src/main/java/Misc/HashFunctions.ipynb)
  - [Exponentiation]()
    - Binary Exponentiation
    - Matrix Exponentiation
</details>

### Codes
- [Problems](src/main/java/Problems)



## Environment Setup

This Project contains Jupyter notebook(ipynb), Markdown(md), and Java(java) Files and is created as a maven project.

### IDE Suggestions for Reading/Editing the files

- **Jupyter Notebooks**
  - Visual Studio code (free jupyter extensions available)
  - Jupyter Notebook/ Jupyter Labs (with/without anaconda)
  - Intellij/ Pycharm (only paid versions have free plugins)
- **Markdown**
  - Intellij/ Pycharm community (have better auto-completions)
- **Java**
  - Intellij (good for code inspecting)
  - Visual Studio code

### Setting up Jupyter Java Kernel to run code snippets in notebooks

- We can execute java code snippets in Jupyter notebooks using the [IJava framework](https://github.com/SpencerPark/IJava).
- Download and install Ijava latest release.
- Add Java in Jupyter kernels. (validate using `jupyter kernelspec list` command)
- Update the Java kernel.json to include all the jars in m2 to support code snippets with imported dependencies. Check the [classpath.ipynb](scripts/classpath.ipynb) notebook for reference.
- We can now select the java kernel and run the code snippets in Visual Studio Code/Jupyter Notebooks/Jupyter Labs.



## References

- Oracle Java Doc - https://docs.oracle.com/javase/tutorial/index.html
- HackerRank - https://www.hackerrank.com/domains/java?badge_type=java
- Code360 - https://www.naukri.com/code360/home/goals (learning path)
- Geeks for Geeks
  - [Learn Java in 20 days](https://www.geeksforgeeks.org/learn-java-on-your-own-in-20-days-free/)
- CMU School of Compute Science
  - [Teach Yourself Java in 21 days (LearnJava.pdf)](https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf)
- Coursera
  - https://www.coursera.org/learn/data-structures--backend-with-java (paid)
