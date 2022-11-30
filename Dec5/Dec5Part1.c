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

        // track start and end of line as well as a line where coordinate doesnt change
        int start = 0;
        int end = 0;
        int line = 0;

        // Depending on which numbers are similar choose start and end
        if (nums[0] == nums[2])
        {
            start = nums[1];
            end = nums[3];
            line = nums[0];
        } else if (nums[1] == nums[3])
        {
            start = nums[0];
            end = nums[2];
            line = nums[1];
        } else continue;

        // change start and end if end is less than start
        int temp = start;
        start = (end < start) ? end : start;
        end = (end > temp) ? end : temp;

        // loop over all points from start to end
        for (start; start <= end; start++)
        {
            // if straight line is x-coordinate
            if (line == nums[0])
            {
                // add to point
                grid[start][line]++;
                // add to intersections if the current cell has two so we only count the first intersection made
                if (grid[start][line] == 2) intersections++;
            }
            // if straight line is y-coordinate
            else
            {
                grid[line][start]++;
                if (grid[line][start] == 2) intersections++;
            }
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