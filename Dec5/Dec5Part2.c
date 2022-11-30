// Standard library
#include <stdlib.h>
// Library for file handling
#include <stdio.h>
// String library for strtok
#include <string.h>


// Declaring function
int file_size(const char *);

// Main function
int main()
{
    // Constant file name
    const char *P_FILE_NAME = "inputdec5.txt";
    // open the file in read mode and store in a pointer
    FILE *p_file = fopen(P_FILE_NAME, "r");
    // get file length from function
    int file_length = file_size(P_FILE_NAME);

    // make an array that stores each line.
    char file_line[30];

    // array representing the 2d space
    int grid[1000][1000];

    // Number of intersections
    int intersections = 0;
    
    // loop over all lines in the file. Since we know the file size
    // we dont have to look at each char
    for (int i = 0; i < file_length; i++)
    {
        // get the line and from get instruction and number from that
        char *p_instruction = fgets(file_line, 30, p_file);

        // Allocates memory for four integers from one line in a file
        int *nums = malloc(sizeof(int) * 4);
        for (int i = 0; i < 4; i++) {
            // splits by " -> " and "," and gets integer
            if (i == 0) nums[i] = atoi(strtok(p_instruction, " -> ,"));
            // strtok(NULL, delimiter) gets the next token of the previous string
            else nums[i] = atoi(strtok(NULL, " -> ,"));
        }

        // track start and end of x and y
        int startX = nums[0];
        int startY = nums[1];
        int endX = nums[2];
        int endY = nums[3];

        // change start and end if end is less than start
        int tempX = startX;
        int tempY = startY;
        startX = (endX < startX) ? endX : startX;
        endX = (endX > tempX) ? endX : tempX;
        startY = (endY < startY) ? endY : startY;
        endY = (endY > tempY) ? endY : tempY;

        // loop over all points from start to end
        for (startX; startX <= endX; startX++, startY++)
        {
            grid[startX][startY]++;
            if (grid[startX][startY] == 2) intersections++;
        }
    }

    // print number of intersections
    printf("Number of intersections: %d\n", intersections);

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