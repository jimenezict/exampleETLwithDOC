# Functional Programming with ETL's and concurrent algorithms
Scheleton for real-time processing from files streaming

This projects aims to be an example of complet ETL based in:
* Streaming concurrent reading from N files
* Extraction done with functional syntaxs
* Saving of data on files for sets of ten minutes.

## ETL description

### Data source

As said it will be extracted from files. To have some low-load example you can run an instance of the program Sensor File Generator (already on this repository)

The data is composed by two colums CSV where first column is a timestamp meanwhile second column is a sensor register. In the scenario, different sensors capture and send data, so for the same timestamp we can have different values on each sensor, due to an error on the register.

The objective of this process then, is to get an average based on timestamp. Even this code has a more generic propse, to do it easy to understand, each line of the file we can consider as speed value (naming of the classes are aligned with it)

There is an additional restriction. We had to cover the case that sensors could had a black-out and send data some seconds later.

### Transformation

The manipulation of data is simple. For a unique key (or timestamp) we are going to calculate the average. As this part could be more deterministic and free of mocking, you can find some basic unitarian test done with Junit 4.

### Extraction

For each block on a separated thread. Each file will have a suffix with an incremental number starting from 1. Exceptionaly, the first loop will have all data from the beginning of the file until the instance had been rose. I had done the assumption that everything can be processed before the first loop ends.

## Algorithms

### File Read Processors

We could consider that there are 2 files we want to read from. This files get incremental data, recurrently. Then, we are forced to develop using threads. As the output should be save on shared data, the best is to have a Master Agent, corditing threads and storing on a Buffer the registers of the sensors.

I had call them FileReadAgent and FileReadMasterAgent. The second one is composed of a constructor that is able to instanciate N agents. The buffer is a Vector, so we can warranty by default the concurrent access. The function getAndCleanSpeedRegisterBuffer will allow to the main program to optain and clean all the added data from the last loop.

As we had allowed our code to have N threads reading N files, I had listed it on the variable agentList. This complement the pattern allowing the communication between threads, but it is not needed this time.

### Buffering with latency

We are running a loop each 10 minutes. If we process all data from the buffer comming from the Master Agent of the files readers, we could have some registers not yet, that will be included on the next loop, so we are not going to be able to have a unique average.
Or in other words, we can have two registers of the same timestamp on the different output files

The way of solving this problem is simple and better explained with an example. Imagine that we capture the average from the time stamps from the second 90 to second 100. So
* We are going to wait a litte bit longer, maybe, to the second 103 (it will depend of the business case Real-Time/Accuracy)
* We are going to extract the information from the buffer of the agent and keep it on main program.
* We are going to split the information coming by all what fits under the second 100 and all what goes after.
* Time for aggregating the samples between the 90 seconds and 100 with the ones that goes from the 90 to 93 of the previous loop-
* We are going to calculate the averages by key
* We are going to store the data from the second 100 to the 103 ready for the next round

### Threading the output.

It is not mandatory, but each time all average are calculated, it is better to generate the output writer on a unique thread. As writing on a external file could be a bottle neck or crash the program I had decided to move it on a paralel thread for each loop.

But this is not risky, as each loop is 10 seconds, it could be useful for shorter laces.

## Benefits

* Allows to scalate it to N sensors
* Each loop the memory intermediate memory is clean, so, we are not going to suffer for memory collapse for long time executions.
* As we are continusly reading from the files on separeted threads, it is very hard to have a delay on the flow or lose the last registers of the block.
* In case that some sensor doesn't send data for some seconds to the file, we are not going to lose it or have the same timestamp on different output files
* Lambda expression simplifies a lot over imperative language

