#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int getFileLength(const char *fileName)
{

    // creates a pointer to the given filepointer
    FILE *filepointer = fopen(fileName, "rb");

    // Represents the length of the file
    int length = 0;

    // nextChar is in this case the very first character in the file
    char nextChar = getc(filepointer);

    // We continue reading the file until we reach EOF (End Of File)
    while (nextChar != EOF) 
    {
        // If the character is a new-line character we know there is a new line
        if (nextChar == '\n')
            length+=1;
        // We get the next character regardless
        nextChar = getc(filepointer);
    }

    // Close the file to remove from memory
    fclose(filepointer);

    return length;

}

int main()
{
    // the filename with measurements. strings are char-arrays so we store the pointer.
    const char *fileName = "inputdec1.txt";
    // we open the file in read mode in a struct called FILE as a pointer
    FILE *filepointer = fopen(fileName, "r");

    // number of chars to be read at one line
    const int maxReadChar = 10;
    // array of characters that stores a single line
    char line[maxReadChar];
    // number of times a depth increased
    int totalIncreased = 0;
    // the previous depth. In this case more than the first since there is no measurement the first time
    int previous = 1000;
    // uses self made function to get file length
    int fileLength = getFileLength(fileName);
    // Loops over all lines
    for (int i = 0; i < fileLength; i++)
    {
        // stores the next line in a char array
        char *fileLine = fgets(line, maxReadChar, filepointer);
        // changes string to int
        int depth = atoi(fileLine);
        // increase totalIncreased if current depth is deeper
        if (depth > previous)
            totalIncreased++;

        // update previous
        previous = depth;
    }

    // print int
    printf("Total increased: %d\n", totalIncreased);

    // Close the file to remove from memory
    fclose(filepointer);

}