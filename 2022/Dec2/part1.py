with open("input.txt") as f: print(sum(map(lambda round: ord(round[1]) - 87 + (3 if round[2] == 0 else (6 if round[2] == -1 or round[2] == 2 else 0)), map(lambda round: [round[0], round[1], ord(round[0]) - ord(round[1]) + 23], [line.rstrip().rsplit(" ") for line in f.readlines()]))))