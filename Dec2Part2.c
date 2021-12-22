// Standard library
#include <stdlib.h>
// Library for file handling
#include <stdio.h>
// String library for strtok
#include <string.h>

/*
using variable words soperated by underscore
p for pointers
const for file name as this will only be changed by me
tried to send file pointer as argument to file_size(),
but this didnt work. Maybe because getc read all lines
so that fgets didnt have any more lines to read.
deepcopying the file mght be an alternative.
*/

// Declaring method
int file_size(const char *);

// Main method
int main()
{
    // Constant file name
    const char *P_FILE_NAME = "inputdec2.txt";
    // open the file in read mode and store in a pointer
    FILE *p_file = fopen(P_FILE_NAME, "r");
    // get file length from function
    int file_length = file_size(P_FILE_NAME);

    // Keep track of horizontal position, depth and aim
    int depth = 0;
    int h_pos = 0;
    int aim = 0;

    // make an array that stores each line.
    char line[15];
    
    // loop over all lines in the file. Since we know the file size
    // we dont have to look at each char
    for (int i = 0; i < file_length; i++)
    {
        // get the line and from get instruction and number from that
        char *p_instruction = fgets(line, 15, p_file);
        char instruction = strtok(p_instruction, " ")[0];
        int steps = atoi(strtok(NULL, " "));

        // different position change with different instructions
        switch (instruction)
        {
            case 'f':
                h_pos += steps;
                depth += aim * steps;
                break;
            case 'u':
                aim -= steps;
                break;
            case 'd':
                aim += steps;
                break;
            default:
                break;
        }

    }

    // print results
    printf("Horizontal: %d and depth is %d\n", h_pos, depth);
    printf("Multiplied together you get: %d\n", h_pos * depth);

    fclose(p_file);

    return 0;
}

int file_size(const char *P_FILE_NAME)
{
    FILE *p_file = fopen(P_FILE_NAME, "r");
    int num_lines = 0;
    for (char c = getc(p_file); c != EOF; c = getc(p_file))
        if (c == '\n')
            num_lines++;

    fclose(p_file);

    return num_lines;

}