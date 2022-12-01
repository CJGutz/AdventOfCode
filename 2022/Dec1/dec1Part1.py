
max_calories = 0

with open("inputdec1.txt") as f:
    current_max = 0
    lines = f.readlines()

for line in lines:
    if line != '' and line != '\n':
        current_max += int(line)
    else:
        if (current_max > max_calories):
            max_calories = current_max
        current_max = 0

print(max_calories)
