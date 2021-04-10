                       XENON PROJECT LDI MAY 2020


To run the examples:

Option 1 - Run the 'runTests.bat' file
Option 2 - Use the terminal to navigate to the main folder (the folder where the
'runTest.bat' file is) and use the following code:

java -jar ./build/Xenon.jar ./Examples/XenonCode.xen
Example: java -jar ./build/Xenon.jar ./Examples/Test1_Sorting.xen

Important Note!!! All the relative paths are configured from the main folder
(where the folders 'Xenon', 'Build', 'Examples' are). You have to run the
examples from this folder or the paths will break.
Also, make sure to have the last version of java installed and the java path
correctly configured.
The test Number 4 is special (it uses the embedded interpreter), so instead of
start Xenon you have to start the Java project with this code:
java -jar ./Examples/TestCesarCode/TestCesarCode.jar



               Example 1 - Sorting Algorithms

The first example is very simple: a collection of the most popular sorting
algorithms: selection, insertion, bubble and quicksort.
In this example you can see how the function calling and recursivity works in
addition to the conditional and loops. You can also see how the vector structure
is accessed and modified.

               Example 2 - Game of life

The second test is a bit more fun: the Game of Life of John Conway. This program
open a file with an initial condition and then you can press 'enter' to pass a
turn.
This example shows how the data can be extracted from the file, how the
'foreach' loop can be used and how a bidimensional map can be stored and used.

               Example 3 - Card Game

This example is a little card game inspired in 'Slay the Spire'
(https://en.wikipedia.org/wiki/Slay_the_Spire). In this game,
you have a deck of card and in each turn you can take 5 cards from your deck and
you can use 3 of these cards.
In this example you can see how the objects are created and modified, with
classes with inheritance. Complex data structures (like maps and random
queues) are used. However, the most interesting thing is how the cards uses first
class functions to modify a method called 'action'. It is an elegant way of
creating different functionalities in different objects of the same class.

               Example 4 - Cesar Code

This simple code use the Cesar code to code and decode a message.
This example is a bit special, because it is a Java code that uses the embedded
interpreter of Xenon, that is to say, it is a Java code that executes a Xenon
code.
In this example it is essential to see how the data is exchanged between Java
code and Xenon code. As shown, Xenon can cooperate with other languages.

               Example 5 - Neural Network

To show how powerful is Xenon, this example is the implementation of a basic
functional neural network. This network uses the Mnist handwritten data to train
a network to classify handwritten numbers.
Of course, it is a simple design that only uses the CPU, so to speed up the
process, the network has only one hidden layer and a reduced number of neurons,
besides, it only uses 1000 samples of the 60000 samples of the dataset. Even so,
the accuracy reaches about 80% after 10 epochs.
This example proves that big projects are possible in Xenon and can be written
in a simple and intuitive way.
