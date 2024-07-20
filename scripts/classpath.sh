#!/bin/bash

# Step 1: Run `mvn clean install`
echo "Running mvn clean install..."
mvn clean install

# Check if mvn clean install succeeded
if [ $? -ne 0 ]; then
    echo "mvn clean install failed."
    exit 1
fi

# Step 2: Generate classpath
echo "Generating classpath..."
classpath=$(mvn dependency:build-classpath -Dmdep.outputScope=compile | grep -A 1 ".* Dependencies classpath:" | tail -n 1)
# mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt


# Check if classpath was generated successfully
if [ -z "$classpath" ]; then
    echo "Failed to generate classpath."
    exit 1
fi


# Step 3: Print out the classpath
echo "Classpath generated successfully."
echo "Copy the following command and run it in a Jupyter notebook cell with IJava kernel:"
echo ""
echo "%classpath add dynamic $classpath"
#echo "%classpath add jar $classpath"
echo ""