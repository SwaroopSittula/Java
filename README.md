# Java

This project is documentation of Java Concepts.



## Table of Contents

- [List of Chapters](#list-of-chapters)
- [Environment Setup](#environment-setup)
- [References](#references)



## List of Chapters

- [Data Types](src/main/java/DataTypes/DataTypes.ipynb)
  - Primitive Data Types
  - Wrapper Classes
  - Autoboxing and Unboxing
  - Wrapper Classes Advantages
  - [Type Conversion](src/main/java/DataTypes/TypeConversion.md)
- [Print](src/main/java/JavaPrint/Print.ipynb)
- [Java Variable Arguments](src/main/java/VariableArguments/Varargs.ipynb)
- [Streams](src/main/java/Streams/Streams.ipynb)
- OOPs
- [Java Reflection](src/main/java/JavaReflection)
- [Design Patterns](src/main/java/DesignPatterns)
- Logging
  - [MDC](src/main/java/Logging/MDC.ipynb)
- [Misc]
  - [Cryptographic Hash Functions](/src/main/java/Misc/HashFunctions.ipynb)
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

- https://docs.oracle.com/javase/tutorial/index.html
