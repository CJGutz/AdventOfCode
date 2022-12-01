
calories = list()

with open("inputdec1.txt") as f:
    current_max = 0
    lines = f.readlines()

for line in lines:
    if line != '' and line != '\n':
        current_max += int(line)
    else:
        calories.append(current_max)
        current_max = 0

calories.sort()

print(sum(calories[-3:len(calories)]))
